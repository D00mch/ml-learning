(ns ml-hello.linal.elimination-factorization
  (:require [uncomplicate.commons.core :refer [with-release]]
            [uncomplicate.neanderthal.vect-math :as vm]
            [uncomplicate.neanderthal
             [math :refer :all]
             [linalg :as la]
             [native :refer :all]
             [core :refer :all]]))

(mm (dge 3 3 [1 0 0
              2 1 0
              3 4 1] {:layout :row})
    (dge 3 3 [1 0 0
              0 1 0
              0 0 1]))


(let [a (dtr 3 [2 1 3 5 2 1])
      b (dge 3 1 [6 3 4])]
  (la/sv a b))

(let [L (dge 2 2 [1 2, 0 1])
      U (dge 2 2 [3 0, 5 -3])
      A (mm L U)]
  A)


(let [a  (dge 3 3 [1 0 1 1 -1 1 3 1 4])
      lu (la/trf a)]
  (prn (:lu lu))
  (prn (view-tr (:lu lu) {:uplo :lower :diag :unit}))
  (prn (view-tr (:lu lu) {:uplo :upper})))

;; 12
(let [A (dge 2 2 [2 4
                  4 11] {:layout :row})
      LU (la/trf A)]
  (prn (view-tr (:lu LU) {:uplo :lower :diag :unit}))
  (prn (view-tr (:lu LU) {:uplo :upper}))
  (prn (la/tri LU))
  (mm (la/tri LU) A)
  )

(mm (dge 2 2 [1 2, 0 1])
    (dge 2 2 [2 0, 4 3]))

(let [A  (dge 3 3 [1 4 0
                   4 12 4
                   0 4 0] {:layout :row})
      E1 (dge 3 3 [1 0 0
                   -4 1 0
                   0 0 1] {:layout :row})
      E2 (dge 3 3 [1 0 0
                   0 1 0
                   0 1 1] {:layout :row})
      E  (mm E2 E1)
      EA (mm E A)]
  EA)

;; 14
(let [L (dtr 2 [1 4 1])
      U (dge 2 2 [2 0, 4 1])
      A (mm L U)
      b (dge 2 1 [2 11])]
  (la/sv A b))

;; 15
(let [L (dtr 3 [1 1 1 1 1 1])
      ;U (dtr 3 [1 1 1 1 1 1] {:uplo :upper})
      U (dge 3 3 [1 0 0, 1 1 0, 1 1 1])
      A (mm L U)
      b (dge 3 1 [4 5 6])
      ]
  (la/sv A b))

;; 25
(let [K (dge 6 6 [2 -1 0 0 0 0
                  -1 2 -1 0 0 0
                  0 -1 2 -1 0 0
                  0 0 -1 2 -1 0
                  0 0 0 -1 2 -1
                  0 0 0 0 -1 2] {:layout :row})
      LU (la/trf K)
      Ki (la/tri LU)
      ]
  (prn (scal 7 Ki))
  (prn (mm K (scal 7 Ki)))
  )
