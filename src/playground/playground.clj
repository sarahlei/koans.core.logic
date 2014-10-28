(ns playground
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :refer :all]
            [clojure.core.logic.fd :as fd]
            [clojure.core.logic.pldb :as pldb]
            [playground.extra :refer :all]
            [clojure.tools.macro :as macro]))
