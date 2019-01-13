(ns spotty-auth-server.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))


(def server-state (atom {}))

(defn get-promise [state k]
  (if-let [p (get @state k)]
    p
    (let [p (promise)]
      (swap! state assoc k p)
      p)))

(defn clear-promise [state k]
  (swap! state dissoc k))


(defn wait-for-token [db id]
  (let [code @(get-promise db id)]
    (clear-promise db id)
    code))


(defn handle-authorization [db req]
  (let [{{:keys [code state]} :params} req
        p (get-promise db state)]
    (deliver p code)
    (str code " " state)))


(defroutes app-routes

  (GET "/token/:id" [id] (wait-for-token server-state id))
  (GET "/authorized" request (handle-authorization server-state request))

  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

