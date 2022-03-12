(ns ml-hello.linal.inverse-matrices
  (:require [uncomplicate.commons.core :refer [with-release]]
            [uncomplicate.neanderthal
             [math :refer :all]
             [linalg :as la]
             [native :refer :all]
             [core :refer :all]])
  (:import (java.util TreeMap)))

(let [e  (dge 3 3 [1 -5 0
                   0 1 0
                   0 0 1])
      ;; e inverse
      ei (dge 3 3 [1 5 0
                   0 1 0
                   0 0 1])
      f  (dge 3 3 [1 0 0
                   0 1 -4
                   0 0 1])
      fi (dge 3 3 [1 0 0
                   0 1 4
                   0 0 1])
      ]
  (mm (mm f e)
      (mm ei fi)))

;; 2
(let [N  (dge 3 3 [0 0 1
                   0 1 0
                   1 0 0] {:layout :row})
      R  (dge 3 3 [0 1 0
                   0 0 1
                   1 0 0] {:layout :row})
      R2 (dge 3 3 [0 0 1
                   1 0 0
                   0 1 0] {:layout :row})]
  (mm R R2))

;; 3
(let [N  (dge 2 2 [10 20
                   20 50] {:layout :row})
      R  (dge 2 1 [1 0])
      R2 (dge 2 1 [0 1])]
  (la/sv N R2))

;; 5
(let [U (dge 2 2 [-1 2
                  00 1] {:layout :row})]
  (mm U U))

;; 6b
(let [A (dge 2 2 [1 1
                  1 1] {:layout :row})
      B (dge 2 2 [1 1
                  -1 -1] {:layout :row})]
  (mm A B))

(let [A  (dge 3 3 [1 1 2
                   2 2 1
                   3 3 3] {:layout :row})
      A2 (dge 3 3 [1 2 3
                   4 5 6
                   5 7 9] {:layout :row})
      x  (dv 1 1 -1)]
  (mv A2 x))

;; 14

(let [M  (dge 2 2 [2 3 4 5])
      R  (dge 2 2 [1 1, 0 1])
      R2 (dge 2 2 [1 -1, 0 1])]
  (mm (mm M R) R2))

;; 17
(let [E21 (dge 3 3 [01 0 0
                    -1 1 0
                    00 0 1] {:layout :row})
      E31 (dge 3 3 [01 0 0
                    00 1 0
                    -1 0 1] {:layout :row})
      E32 (dge 3 3 [01 0 0
                    00 1 0
                    0 -1 1] {:layout :row})]
  (mm E32 (mm E31 E21)))

(let [M   (dge 4 4 [4 -1 -1 -1
                    -1 4 -1 -1
                    -1 -1 4 -1
                    -1 -1 -1 4])
      M-1 (dge 4 4 [2 1 1 1
                    1 2 1 1
                    1 1 2 1
                    1 1 1 2])]
  (mm M (scal (/ 1.0 5.0) M-1)))

;; 22
(comment
  1, 3, 1, 0 | -2 |1, 3, 1, 0
  2, 7, 0, 1 | |0, 1, -2, 1

  |1, 3, 1, 0 | |1, 0, 7, -3 |
  -3 |0, 1, -2, 1 | -3 |0, 1, -2, 1 |

  1 4 1 0 | 1 0 -3 -4/3
  3 9 0 1 4/3 | 0 1 1 -1/3
  )

;; 26
(let [A   (dge 2 2 [1 2
                    2 6] {:layout :row})
      E21 (dge 2 2 [1 0
                    -2 1] {:layout :row})]
  (mm E21 A))

;; 29
;; a) true; b) false; c) true
(mm (dge 2 2 [-1 1, 1 -1])
    (dge 2 2 [1 1 1 1]))

;; 32
(mm
  (dge 2 2 [0 1 1 0])
  (mm (dge 2 2 [1 1 1 1])
      (dge 2 2 [1 0 0 1])))

;; 35
(let [D  (dge 4 4 [1 0 0 0
                   0 -1 0 0
                   0 0 1 0
                   0 0 0 -1] {:layout :row})
      P  (dge 4 4 [1 0 0 0
                   1 1 0 0
                   1 2 1 0
                   1 3 3 1] {:layout :row})
      PI (mm (mm D P) D)]
  #_(mm P PI)
  (print P)
  (mm (mm P D)
      (mm P D)))

;; 40
(let [E1 (dge 4 4 [1 0 0 0
                   2 1 0 0
                   3 0 1 0
                   4 0 0 1] {:layout :row})
      E2 (dge 4 4 [1 0 0 0
                   0 1 0 0
                   0 5 1 0
                   0 6 0 1] {:layout :row})
      E3 (dge 4 4 [1 0 0 0
                   0 1 0 0
                   0 0 1 0
                   0 0 7 1] {:layout :row})]
  (->> E3 (mm E2) #_(mm E1)))

;; 41
(let [T (dge 4 4 [01 -1 00 00
                  -1 02 -1 00
                  00 -1 02 -1
                  00 00 -1 02] {:layout :row})
      L (dge 4 4 [1 0 0 0
                  -1 1 0 0
                  0 -1 1 0
                  0 0 -1 1] {:layout :row})
      U (dge 4 4 [1 -1 0 0
                  0 1 -1 0
                  0 0 1 -1
                  0 0 0 1] {:layout :row})
      ]
  (= T (mm L U)))

(let [L (dge 4 4 [1 0 0 0
                  1 1 0 0
                  1 1 1 0
                  1 1 1 1] {:layout :row})
      U (dge 4 4 [1 1 1 1
                  0 1 1 1
                  0 0 1 1
                  0 0 0 1] {:layout :row})]
  (mm U L))

