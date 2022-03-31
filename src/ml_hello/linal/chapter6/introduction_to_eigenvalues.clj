(ns ml-hello.linal.chapter6.introduction-to-eigenvalues
  (:require [uncomplicate.commons.core :refer [with-release]]
            [uncomplicate.neanderthal
             [math :refer :all]
             [linalg :as la]
             [native :refer :all]
             [core :refer :all]]))

(let [A (dge 2 2 [0.8 0.3
                  0.2 0.7] {:layout :row})]
  (mv A (dv 0.8 0.2)))


;; 1
(with-release [A  (dge 2 2 [0.8 0.3
                            0.2 0.7] {:layout :row})
               AA (mm A A)]

  AA)


;; 4

(with-release
  [A (dge 2 2 [-1 2, 3 0])]
  (mm A A))

;; 12
(with-release
  [P (dge 3 3 [0.2 0.4 0
               0.4 0.8 0
               0 0 1])]
  (mm P P P P))

;; 13
(with-release [u (fv 1/6 1/6 3/6 5/6)]
  (rk u u))


;; 20
(with-release [C (dge 3 3 [0 0 6, 1 0 -11, 0 1 6])]
  (la/ev C))

(with-release [A (dge 2 2 [0 -28, 1 11])]
  (la/ev! A (dge 2 2)))

(with-release [A     (dge 2 2 [1 2, 4 3])
               e-val (la/ev A)
               e-vec-l (dge 2 2)
               e-vec-r (dge 2 2)]
  (la/ev! A e-val e-vec-l e-vec-r)
  (println e-val)
  (println e-vec-l)
  (println e-vec-r))

(with-release [A (dge 3 3 [2 4 2, 1 2 1, 2 4 2])
               x (dv 1 -4 1)
               x2 (dv 0 -2 1)
               x3 (dv -1 2 0)]
  (mv A x3))

