(ns playground.puzzle.db
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :refer :all]
            [clojure.core.logic.pldb :as pldb]
            [playground.extra :refer :all]
            [clojure.tools.macro :as macro]))

(pldb/db-rel rps move)
(pldb/db-rel beats who whom)

(def game-db
  (pldb/db
   [rps :rock]
   [rps :paper]
   [rps :scissors]

   [beats :paper :rock]
   [beats :scissors :paper]
   [beats :rock :scissors]))

(defn beats?
  [who whom]

  (conde
   [(beats who whom)]
   [(== who whom)]))

(comment

  (pldb/with-db game-db
    (run* [q]
      (fresh [player-1 player-2 player-3]
        (rps player-1)
        (rps player-2)
        (rps player-3)

        (beats player-1 player-2)
        (beats player-1 player-3)
        (beats? player-2 player-3)

        (== q [player-1 player-2 player-3]))))
  )
