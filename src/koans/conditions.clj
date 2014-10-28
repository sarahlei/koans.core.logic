(meditations
 "If a question is false, it does not succeed, but true does."
 (= (run* [q]
          (conde
           [u# s#]
           [s# (== q true)])) __)

 "All questions shall be answered."
 (= (run* [q]
          (conde
           [(== q :apple)]
           [(== q :orange)])) __)

 "When asked a single question, we shall receive a single answer."
 (= (run 1 [q]
         (conde
          [(== q :apple)]
          [(== q :orange)])) __)

 "Failures do not matter, but succeess always does!"
 (= (run* [q]
          (conde
           [(== q :pear) u#]
           [(== q :apple)]
           [s# s#]
           [(== q :orange)])) __)

 "Every part of a question needs an answer, or the question itself
 shall fail."
 (= (run* [q]
      (conde
       [(== q :logic)]
       [(== q :is) fail]
       [(== q :fun)]))
    __)
 )
