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

 "Succeed no matter how much, the questions asked shall be answered, and no more."
 (= (run __ [q]
         (conde
          [(== q :extra) s#]
          [(== q :virgin) u#]
          [(== q :olive) s#]
          [(== q :oil) s#])
         '(:extra :olive)))
 )
