(ns playground
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :refer :all]
            [playground.extra :refer :all]
            [clojure.tools.macro :as macro]))

;;  1. There are five houses.
;;  2. The Englishman lives in the red house.
;;  3. The Spaniard owns the dog.
;;  4. Coffee is drunk in the green house.
;;  5. The Ukrainian drinks tea.
;;  6. The green house is immediately to the right of the ivory house.
;;  7. The Old Gold smoker owns snails.
;;  8. Kools are smoked in the yellow house.
;;  9. Milk is drunk in the middle house.
;; 10. The Norwegian lives in the first house.
;; 11. The man who smokes Chesterfields lives in the house next to the man with the fox.
;; 12. Kools are smoked in the house next to the house where the horse is kept.
;; 13. The Lucky Strike smoker drinks orange juice.
;; 14. The Japanese smokes Parliaments.
;; 15. The Norwegian lives next to the blue house.
;;
;; Now, who drinks water? Who owns the zebra?
;;
;; In the interest of clarity, it must be added that each of the five
;; houses is painted a different color, and their inhabitants are of
;; different national extractions, own different pets, drink different
;; beverages and smoke different brands of American cigarets [sic].
;; One other thing: in statement 6, right means your right.

(defn zebrao [hs]
  (macro/symbol-macrolet
   [_ (lvar)]

   (all
    (== [_ _ _ _ _ ] hs)
    (membero ['englishman _ _ _ 'red] hs)
    (membero ['spaniard _ _ 'dog _] hs)
    (membero [_ _ 'coffee _ 'green] hs)
    (membero ['ukrainian _ 'tea _ _] hs)
    (righto [_ _ _ _ 'ivory] [_ _ _ _ 'green] hs)
    (membero [_ 'oldgolds _ 'snails _] hs)
    (membero [_ 'kools _ _ 'yellow] hs)
    (== [_ _ [_ _ 'milk _ _] _ _] hs)
    (firsto hs ['norwegian _ _ _ _])
    (nexto [_ _ _ 'fox _] [_ 'chesterfields _ _ _] hs)
    (nexto [_ _ _ 'horse _] [_ 'kools _ _ _] hs)
    (membero [_ 'lucky-strikes 'oj _ _] hs)
    (membero ['japanese 'parliaments _ _ _] hs)
    (nexto ['norwegian _ _ _ _] [_ _ _ _ 'blue] hs))))

(defn zebrap
  [water-drinker zebra-owner]

  (macro/symbol-macrolet
   [_ (lvar)]

   (fresh [hs]
     (zebrao hs)
     (membero [water-drinker _ 'water _ _] hs)
     (membero [zebra-owner _ _ 'zebra _] hs))))

(comment

  (run* [q]
    (fresh [w z]
      (zebrap w z)
      (== q {:water-drinker w
             :zebra-owner z})))

  )
