(ns playground.puzzle.db
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :refer :all]
            [clojure.core.logic.pldb :as pldb]
            [playground.extra :refer :all]
            [clojure.tools.macro :as macro]))

(pldb/db-rel man p)
(pldb/db-rel woman p)
(pldb/db-rel likes p1 p2)
(pldb/db-rel fun p)

(def peopledb
  (pldb/db
   [man 'Bob]
   [man 'John]
   [man 'Ricky]
   [man 'George]
   [man 'Jack]

   [woman 'Mary]
   [woman 'Martha]
   [woman 'Lucy]

   [likes 'Bob 'Mary]
   [likes 'John 'Martha]
   [likes 'Ricky 'Lucy]
   [likes 'George 'Jack]
   [likes 'Lucy 'Ricky]
   [likes 'Mary 'Martha]

   [fun 'Lucy]))

(comment

  ;; Who likes a funny person?
  (pldb/with-db peopledb
    (run* [q]
      (fresh [x y]
        (fun y)
        (likes x y)
        (== q [x y]))))

  ;; Who likes a person of the same gender?
  (pldb/with-db peopledb
    (run* [q]
      (fresh [x y]
        (likes x y)
        (conde
         [(man x) (man y)]
         [(woman x) (woman y)])
        (== q [x y]))))

  ;; Are there any people who like each other?
  (pldb/with-db peopledb
    (run* [q]
      (fresh [x y]
        (likes x y)
        (likes y x)
        (== q x))))
  )
