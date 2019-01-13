(ns spotty-auth-server.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [spotty-auth-server.handler :refer :all]))


(deftest test-state
  
  (testing "retrieving a promise from empty state should place the same promise into the state atom"
    (let [state (atom {})
          p (get-promise state :foo)]
      (comment "It turns out there is no 'promise?'"
               (is (promise? p) "returned object should be a promise"))
      (is (identical? p (get-promise state :foo)) "state should continue to return the same promise object each time"))))


(deftest test-routes

  (testing "authorized route"
    (let [response (app (mock/request :get "/authorized?code=1234&state=5678"))
          p (get @server-state "5678")]
      
      (is (= (:status response) 200) "response should be OK")
      (is (= (:body response) "1234 5678"))
      (is (not (nil? p)) "server state should contain an entry for state param")
      (is (= "1234" @p) "dereferencig server state should return the code param")))

  (testing "waiting route"
    (let [p (get-promise server-state "0123")]
      (deliver p "token-0123") ;; assume this was already done by an earlier call to authorized
      (let [response (app (mock/request :get "/token/0123"))]
        (is (= (:status response) 200))
        (is (= (:body response) "token-0123"))
        (is (nil? (get @server-state "0123")) "Server state should no longer hold an entry for the state param")))))
