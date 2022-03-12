(ns ml-hello.linal.chapter4.orthogonality
  (:require [uncomplicate.commons.core :refer [with-release]]
            [uncomplicate.neanderthal
             [math :refer :all]
             [linalg :as la]
             [native :refer :all]
             [core :refer :all]]))

(let [a (dge 3 5 [1 2 3 4 5
                  1 2 4 5 6
                  1 2 4 5 6] {:layout :row})
      b (dge 5 3 [1 2 3 4 5
                  1 2 4 5 6
                  1 2 4 5 6])]
  (mm a b))

(let [a (dge 2 2 [1 2
                  3 6] {:layout :row})
      x (dv 4 3)]
  (mv a x))

(comment
  1 -3 -4
  )

;; 3
(comment
  a
  C (A) = [1 2 -3], [2 -3 5]
  N (A) = [1 1 1]
  r = 2, n = 3
  —————————
  1 2 3
  2 -3 -1
  -3 5 2

  b
  R (A) = [1 2 -3], [2 -3 5]
  N (A) = 1 1 1
  wrong N

  c
  A (x) = [1 1 1]
  At (x) [1 0 0] = [0 0 0]

  N (At) [1 0 0]
  0 - - 1 0
  0 - - 0 = 0
  0 - - 0 0

  impossible ->
  0 0 0 x 1
  - - - y = 1
  - - - z 1

  d
  1 -1
  1 -1

  e
  sum of 1s are not 0
  )

;; 4
(comment
  AB = 0
  C (B) is in the N (A)
  row of A are in th N (Bt) | LN (B)

  if A & B are R ^3 & r=2 —>
  N (A) = 1
  N (B) = 1
  but B has 2 independent columns that should be in the null space of A
  )

;; 5
(comment
  Ax = b
  Aty = 0
  y _|_ b

  bt y = (Ax) t y = xt (At y) = xt * 0 = 0
  yt b = 0

  At y = 1 1 1
  A x = 0
  )

;; 6
(comment
  1 2 2 5
  2 2 3 5
  3 4 5 9

  1 0 1 0
  0 2 1 5
  0 0 0 1

  yt = 1 1 -1
  in L nullspace, N (At)

  yt b = 1
  )

;; 7
(comment
  Ax = b | At y = 0 with yt b = 1

  either we have a solution to Ax = b |
  or there is a solution to At y = 0 (N (At)) &
  there are b (or C (A)) that is not perpendicular to yt (or N (At))

  x - y = 1
  y - z = 1
  x - z = 1

  1 -1 0
  0 1 -1
  1 0 -1

  N (At) = -1 -1 1
  )


;; 8
(comment
  A x
  1 1 1
  1 1 0

  xn = [0.5 -0.5]
  rx = 0.5 0.5
  x = 1 0

  xn = 1 -1
  xr = 0 1
  x = 1 0
  )

;; 9
(comment
  AtAx = 0
  At b = 0
  At perp b
  A perp b
  b perp b
  )

(let [a  (dge 2 2 [1 1
                   2 2] {:layout :row})
      at (dge 2 2 [1 1
                   2 2])
      x  (dv 1 -1)]
  (prn (mm at a))
  (mv (mm at a) x))

;; 16
(comment
  At y = 0
  (Ax) t y = 0
  Ax perp y
  )

;; 25
(let [a  (dge 3 3 [0 1 0
                   1 0 0
                   0 0 1] {:layout :row})
      at (dge 3 3 [0 1 0
                   1 0 0
                   0 0 1])]
  (mm a at))

;; 26
(let [m [2 -1 2
         2 2 -1
         -1 2 2]
      a (dge 3 3 m {:layout :row})
      at (dge 3 3 m)]
  (mm at a))

;; 27
(comment
  3 1
  6 2
  )

;; 28
(comment
  1 1 0 0 0
  0 0 0 1 1
  C (A) = [1 0], [0 1]
  R (A) = A
  N (A) = [-1 1 0 0 0], [0 0 1 0 0], [0 0 0 -1 1]
  N (At) = Z


  1 -1 0 0 0
  2 -2 3 4 -4

  1 -1 0 0 0
  0  0 3 4 -4
  C (A) = [1 2], [0, 3]
  R (A) = A
  N (A) = [1 1 0 0 0], [0 0 4/3 1 0], [0 0 0 1 1]
  )

;; 29
(comment
  1 2 3
  2 1 0
  3 0 0

  1 1 -1
  2 -1 0
  3 0 -3
  )

;; 30
(comment
  A (3x4)
  B (4x5)
  AB = 0
  N (A) contains C (B)

  B has r = 3
  )