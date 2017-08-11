(ns assignment.core
 (:require [clojure.string :as str]
           [clojure.java.io :as io])
 (:gen-class :main true))

(defn to-square
  "Takes a number x and returns the square of it"
  [x]
  (* x x))

(def operations
  {"ADD" +
   "SUB" -
   "MUL" *
   "DIV" quot
   "SQR" to-square})

(defn load-file-as-reader
  [file-path]
  (try
    (let [file (io/reader file-path)]
      file)

    (catch Exception e
      (println (str "File " file-path " not Found!"))
      nil)))

(defn parse-int 
  "Takes a string and parses its integer"
  [s]
  (if-let [n  (re-find #"\A-?\d+" (str s))]
    (Integer/parseInt n)
    nil))

(defn string-eval
  "Takes an number and a string, evaluates the string into 
  the mathematic operation and returns the result"
  [acc arg]
  (let [[op val] (str/split arg #" ")
        op-fn (get operations op)
        int-val (parse-int val)]
    (cond
      (and op-fn int-val) (op-fn acc int-val)
      op-fn (op-fn acc)
      :else acc)))


(defn eval-list
  "Evaluate a list of strings in the format
  'OPERATION OPERAND'"
  [list]
  (reduce string-eval 0 list))


(defn -main
  [& args]
  (when-let [rdr (load-file-as-reader (apply str args))]
    (let [result (with-open [r rdr]
                   (eval-list (line-seq r)))]
      (println result))))
