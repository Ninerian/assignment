(ns assignment.core-test
  (:require [clojure.test :refer :all]
            [assignment.core :refer :all]))

(import '(java.io BufferedReader StringReader))

(def text-list "ADD 5\nADD 7\nSUB 9\nSQR")
(def text-list-2 "ADD 10\nADD 6\nSUB 9\nSQR\nDIV 2\nMUL 10")

(defn list-seq
  [text]
  (line-seq (BufferedReader. (StringReader. text))))

(deftest square-op
  (testing "Should return the square of a number"
    (is (= 4 (to-square 2)))
    (is (= 0 (to-square 0)))))

(deftest int-parsing
  (testing "Should parse an string into an integer"
    (is (= nil (parse-int "x")))
    (is (= 5 (parse-int "5")))
    (is  (= nil (parse-int "x5")))))

(deftest operation-execution
  (testing "Should evaluate the operation and return the correct sum."
    (is (= 5 (string-eval 0 "ADD 5")))
    (is (= 0 (string-eval 5 "SUB 5")))
    (is (= 10 (string-eval 2 "MUL 5")))
    (is (= 2 (string-eval 4 "DIV 2")))
    (is (= 4 (string-eval 2 "SQR")))))

(deftest evaluate-list
  (testing "Should return the result of all operations in a list"
    (is (= 9 (eval-list (list-seq text-list))))
    (is (= 240 (eval-list (list-seq text-list-2))))))






