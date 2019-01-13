(ns spotty-auth-server.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn wait-for-token [id]
  (str "Waiting for token " id))

(defn handle-authorization [req]
  (let [{{:keys [code state]} :params} req]
    (str code " " state)))

(defroutes app-routes

  (GET "/token/:id" [id] (wait-for-token id))
  (GET "/authorized" request (handle-authorization request))

  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
