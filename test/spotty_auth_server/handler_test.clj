(ns spotty-auth-server.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [spotty-auth-server.handler :refer :all]))

(deftest test-routes
  (testing "waiting route"
    (let [response (app (mock/request :get "/token/abcd"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Waiting for token abcd"))))

  (testing "authorized route"
    (let [response (app (mock/request :get "/authorized?code=1234&state=5678"))]
      (is (= (:status response) 200))
      (is (= (:body response) "1234 5678")))))
