;; this file should contain a vector of 2-vectors; each 2-vector must
;; contain a koan name and a map of koan answers, in that order.

[["basics" {"__" ['()
                  (== q true)
                  '()
                  '(true)
                  '(:logic-fun)
                  '()
                  '(true)
                  '(_0)
                  '([_0 _1])
                  '([_0 _1 _0])
                  '()
                  '(false)
                  '(true)
                  '(true)]}]
 ["conditions" {"__" ['(true)
                      '(:apple :orange)
                      '(:apple)
                      '(:apple _0 :orange)
                      '(:logic :fun)]}]

 ["goals" {"__" ['(a)
                 'a
                 '((b c))
                 '(a)
                 [(lcons '_0 '_1)]
                 '(cat dog bird)
                 '(dog bird)
                 (lcons 'cat '_0)
                 (lcons '_0 (lcons 'cat '_1))]}]]
