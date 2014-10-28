(ns playground
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :refer :all]
            [playground.extra :refer :all]
            [clojure.tools.macro :as macro]))

;; 1. There are four sets of five things. For example, 5 people, 5
;;    magazines, 5 reservation times and 5 cheeses.
;; 2. There are rules that connect them together, like, "Jason likes
;;    mozzarella."
;; 3. The rules are exclusive. If we're told Jason likes mozzarella,
;;    then Jason likes no other cheese, and nobody else likes
;;    mozzarella.
;; 4. You are given enough clues about the relationships between these
;;    things that only one possible configuration can exist. Findit.

;; * People: Amaya, Bailey, Jamari, Jason & Landon.
;; * Cheeses: Asiago, Blue Cheese, Mascarpone, Mozzarella & Muenster
;; * Magazines: Fortune, Time, Cosmopolitan, US Weekly & Vogue
;; * Reservation times: 5pm, 6pm, 7pm, 7:30pm, 8:30pm.

;;  1. Of Landon and Jason, one has the 7:30pm reservation and the
;;     other loves mozzarella.
;;  2. The blue cheese enthusiast subscribed to Fortune.
;;  3. The muenster enthusiast didn't subscribe to Vogue.
;;  4. The 5 people were the Fortune subscriber, Landon, the person
;;     with a reservation at 5:00pm, the mascarpone enthusiast, and the
;;     Vogue subscriber.
;;  5. The person with a reservation at 5:00pm didn't subscribe to
;;     Time.
;;  6. The Cosmopolitan subscriber has an earlier reservation than the
;;     mascarpone enthusiast.
;;  7. Bailey has a later reservation than the blue cheese enthusiast.
;;  8. Either the person with a reservation at 7:00pm or the person
;;     with a reservation at 7:30pm subscribed to Fortune.
;;  9. Landon has a later reservation than the Time subscriber.
;; 10. The Fortune subscriber is not Jamari.
;; 11. The person with a reservation at 5:00pm loves mozzarella.

(defn rule-1
  "Of Landon and Jason, one has the 7:30pm reservation and the other loves mozzarella."
  [answers]
  (macro/symbol-macrolet
   [_ (lvar)]
   (fresh [c1 r1 c2 r2]
     (membero [:landon _ c1 r1] answers)
     (membero [:jason _ c2 r2] answers)
     (conde
      [(== r1 7.5)
       (== c2 :mozzarella)]
      [(== r2 7.5)
       (== c1 :mozzarella)]))))

(defn rule-2
  "The blue cheese enthusiast subscribed to Fortune."
  [answers]
  (macro/symbol-macrolet
   [_ (lvar)]
   (membero [_ :fortune :blue-cheese _] answers)))

(defn rule-3
  "The muenster enthusiast didn't subscribe to Vogue."
  [answers]
  (macro/symbol-macrolet
   [_ (lvar)]
   (fresh [s1 s2]
     (== [_ :vogue _ _] s1)
     (== [_ _ :muenster _] s2)
     (membero s1 answers)
     (membero s2 answers)
     (!= s1 s2))))

(defn rule-4
  "The 5 people were the Fortune subscriber, Landon, the person with a
  reservation at 5:00pm, the mascarpone enthusiast, and the Vogue
  subscriber."
  [answers]
  (macro/symbol-macrolet
   [_ (lvar)]
   (permuteo [[_ :fortune _ _]
              [:landon _ _ _]
              [_ _ _ 5]
              [_ _ :mascarpone _]
              [_ :vogue _ _]]
             answers)))

(defn rule-5
  "The person with a reservation at 5:00pm didn't subscribe to Time."
  [answers]
  (macro/symbol-macrolet
   [_ (lvar)]
   (fresh [s1 s2]
     (== [_ _ _ 5] s1)
     (== [_ :time _ _] s2)
     (membero s1 answers)
     (membero s2 answers)
     (!= s1 s2))))

(defn rule-6
  "The Cosmopolitan subscriber has an earlier reservation than the mascarpone enthusiast."
  [answers]
  (macro/symbol-macrolet
   [_ (lvar)]
   (fresh [r1 r2]
     (membero [_ :cosmopolitan _ r1] answers)
     (membero [_ _ :mascarpone r2] answers)
     (lefto r1 r2 [5 6 7 7.5 8.5]))))

(defn rule-7
  "Bailey has a later reservation than the blue cheese enthusiast."
  [answers]
  (macro/symbol-macrolet
   [_ (lvar)]
   (fresh [r1 r2]
     (membero [_ _ :blue-cheese r1] answers)
     (membero [:bailey _ _ r2] answers)
     (lefto r1 r2 [5 6 7 7.5 8.5]))))

(defn rule-8
  "Either the person with a reservation at 7:00pm or the person with a
   reservation at 7:30pm subscribed to Fortune."
  [answers]
  (macro/symbol-macrolet
   [_ (lvar)]
   (fresh [r]
     (membero [_ :fortune _ r] answers)
     (conde [(== r 7)]
            [(== r 7.5)]))))

(defn rule-9
  "Landon has a later reservation than the Time subscriber."
  [answers]
  (macro/symbol-macrolet
   [_ (lvar)]
   (fresh [r1 r2]
     (membero [_ :time _ r1] answers)
     (membero [:landon _ _ r2] answers)
     (lefto r1 r2 [5 6 7 7.5 8.5]))))

(defn rule-10
  "The Fortune subscriber is not Jamari."
  [answers]
  (macro/symbol-macrolet
   [_ (lvar)]
   (fresh [s1 s2]
     (== [_ :fortune _ _] s1)
     (== [:jamari _ _ _] s2)
     (membero s1 answers)
     (membero s2 answers)
     (!= s1 s2))))

(defn rule-11
  "The person with a reservation at 5:00pm loves mozzarella."
  [answers]
  (macro/symbol-macrolet
   [_ (lvar)]
   (membero [_ _ :mozzarella 5] answers)))

(vec (first
      (let [people       (repeatedly 5 lvar)
            magazines    (repeatedly 5 lvar)
            cheeses      (repeatedly 5 lvar)
            reservations (repeatedly 5 lvar)
            answers (map vector people magazines cheeses reservations)]

        (run 1 [q]
          (== q answers)
          (== people [:amaya :bailey :jamari :jason :landon])
          (rule-1 answers)
          (rule-2 answers)
          (rule-3 answers)
          (rule-4 answers)
          (rule-5 answers)
          (rule-6 answers)
          (rule-7 answers)
          (rule-8 answers)
          (rule-9 answers)
          (rule-10 answers)
          (rule-11 answers)
          (permuteo magazines [:fortune :time :cosmopolitan
                               :us-weekly :vogue])
          (permuteo cheeses [:asiago :blue-cheese :mascarpone
                             :mozzarella :muenster])
          (permuteo reservations [5 6 7 7.5 8.5])))))
