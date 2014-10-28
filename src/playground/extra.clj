(ns playground.extra
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :refer :all]))

(defne righto [x y l]
  ([_ _ [x y . ?r]])
  ([_ _ [_ . ?r]] (righto x y ?r)))

(defn nexto [x y l]
  (conde
   ((righto x y l))
   ((righto y x l))))

(defne lefto
  [x y l]
  ([_ _ [x . tail]] (membero y tail))
  ([_ _ [_ . tail]] (lefto x y tail)))
