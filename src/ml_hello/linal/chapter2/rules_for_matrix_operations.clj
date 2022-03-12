(ns ml-hello.linal.chapter2.rules-for-matrix-operations
  (:require [uncomplicate.commons.core :refer [with-release]]
            [uncomplicate.neanderthal
             [math :refer :all]
             [linalg :as la]
             [native :refer :all]
             [core :refer :all]]))

(let [m1 (dge 3 2 [1 3 0
                   2 4 0])
      m2 (dge 3 2 [2 4 9
                   2 4 9])]
  (xpy m1 m2))

(let [m1 (dge 3 2 [1 3 0
                   2 4 0])]
  (scal 2 m1))

(let [m1 (dge 3 1 [0 1 2])
      m2 (dge 1 3 [0 1 2, 0 2 4, 0 3 6])]
  #_(mm m1 m2) (mm m2 m1))

(mm (dge 2 1 [2 1]) (dge 1 2 [1 2]))

(let [ci (dge 2 2 [2 0
                   0 2])
      m  (dge 2 2 [1 2
                   3 4])]
  (= (mm m ci) (mm ci m)) #_true)

(let [ee (dge 4 4 [1 0 1 0
                   0 1 0 1
                   1 0 1 0
                   0 1 0 1] {:layout :row})
      m  (dge 4 4 [1 0 1 0
                   0 1 0 1
                   1 0 1 0
                   0 1 0 1] {:layout :row})]
  (mm ee m))

(let [e (dge 2 2 [1 0, 0 1])
      m (dge 2 2 [1 0, 0 1])]
  (mm e m))

;; Problem set 2.4
;; 1
(let [A (dge 3 5)
      B (dge 5 3)
      C (dge 5 1)
      D (dge 3 1)]
  [(mm B A)
   (mm A B)
   (mm (mm A B) D)]
  (mm (mm A B) D))

;; 3
(let [A (dge 2 2 [1 5
                  2 3] {:layout :row})
      B (dge 2 2 [0 2
                  0 1] {:layout :row})
      C (dge 2 2 [3 1
                  0 0] {:layout :row})]
  [(= (xpy (mm A B) (mm A C))
      (mm A (xpy B C)))
   (= (mm A (mm B C))
      (mm (mm A B) C))])

;; 6
(let [A     (dge 2 2 [1 2
                      0 0] {:layout :row})
      B     (dge 2 2 [1 0
                      3 0] {:layout :row})
      ApB   (xpy A B)
      ApB2  (mm ApB ApB)
      A2    (mm A A)
      B2    (mm B B)
      AB    (mm A B)
      BA    (mm B A)
      A2B2  (mm A2 B2)
      A2pB2 (xpy A2 B2)]
  )

;; 12 a)true; b)false; c)true; d)false
;; 15 a)true; b)false; d)true; d)false

;; 17
(let [A   (dge 2 2 [2 -1
                    3 -2] {:layout :row})
      B   (dge 2 3 [1 0 4
                    1 0 6] {:layout :row})
      AB  (mm A B)
      AA  (mm A A)
      AAA (mm A AA)]
  AB #_(a [0 0], b [1 0 0])
  AA #_(c [0 1])
  AAA #_(3 -2)
  )

;; 22
(let [m (dge 2 2 [0 -1
                  1 0])]
  (mm m m))

;; 24
(let [a (dge 2 2 [2 1
                  0 1] {:layout :row})]
  (mm a (mm a a)))

;; 35
(let [a (dge 4 4 [0 1 0 1
                  1 0 1 0
                  0 1 0 1
                  1 0 1 0])
      aa (mm a a)
      aaa (mm aa a)]
  aaa)
