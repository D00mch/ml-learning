(ns ml-hello.linal.matrices
  (:require [uncomplicate.commons.core :refer [with-release]]
            [uncomplicate.neanderthal
             [math :refer :all]
             [linalg :as la]
             [native :refer :all]
             [core :refer :all]]))

;; 1.3 Matrices

(def m (dge 3 2 [1 3 5
                 2 4 6]))

(mv m (dv 7 8))

(def mdiff (dge 3 3 [1 -1 0
                     0 1 -1
                     0 0 1]))

(mv mdiff (dv 1 4 9))
(mv mdiff (dv 2 2 2))

(def mcyclic (dge 3 3 [1 -1 0
                       0 1 -1
                       -1 0 1]))

(mv mcyclic (dv 1 3 5))

;; problem set 1.3
;; 4
(let [m (dge 3 3 [1 2 3
                  4 5 6
                  7 8 9])
      r (dge 3 1 [0 0 0])]
  #_(la/sv m r))

;; 5
(comment
  x + 2y + 3z = 0
  x = -2y - 3z
  x = 4z-3z
  x = z

  4x + 5y + 6z = 0
  4 (-2y -3z) + 5y + 6z = 0
  -8y -12z + 5y + 6z = 0
  -3y = 6z
  y = -2z

  7x + 8y + 9z = 0
  )

;; 8
#_(let [m (dge 4 4 [1 0 0 0
                    -1 1 0 0
                    0 -1 1 0
                    0 0 -1 1] {:layout :row})
        r (dge 4 1)]
    (la/sv m r))

(comment
  1 0 0 0 | b1 = x1
  1 1 0 0
  1 1 1 0
  1 1 1 1

  -b1 x2 0 0 = b2
  x2 = b2 + b1
  )

;; 9
(comment
  1 0 0 -1
  -1 1 0 0
  0 -1 1 1
  0 0 -1 1)

;; 14
#_[3 6
   1 2]