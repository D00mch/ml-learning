(ns ml-hello.linal.chapter3.dimensions-of-the-four-subspaces
  (:require [uncomplicate.commons.core :refer [with-release]]
            [uncomplicate.neanderthal
             [math :refer :all]
             [linalg :as la]
             [native :refer :all]
             [core :refer :all]]))

;; 4
(comment
  a
  1 2
  1 0
  2 5

  b - impossible
  1 -1 -2
  1 -1 -2
  3 -3 -6
  n = 3 = (n - r) + r
  r = 1
  n(a) = 3 - 1 = 2
  n = c (a) + n (a) = 2 != 3

  c
  n(A) = 2 | = 1 + n(At)
  n(At) = 1

  1 1 1
  2 2 2

  d
  N (A) = [1 3]
  C (A) = [3 1]

  3 -1
  1 -1/3

  e - impossible
  1 2
  2
  )

;; 5
(comment
  V is spanned by [11, 1], [21, 0]

  A
  1 1 1
  2 1 0

  B
  -1
   2
  -1

  AB
  0
  0
  )

;; 6
(comment
  A
  0 3 3 3
  0 0 0 0
  0 1 0 1

  r = 2

  C (A) = [3 0 1], [3 0 0]
  C (At) = [0 3 3 3], [0 1 0 1]
  N (A) = [1 0 0 0], [0 -1 0 1]
  N (At) = [0 1 0]

  B
  1
  4
  5

  C (A) = [1 4 5]
  C (At) = [1]
  N (A) = Z
  N (At) = [-4 1 0], [-5 0 1]
  )

;; 7
(comment
  A
  1 0 0
  0 1 0
  0 0 1


  B = [A A]
  1 0 0 1 0 0
  0 1 0 0 1 0
  0 0 1 0 0 1
  )

;; 8
(comment
  0
  0 0
  0 0
  0 0

  A
  1 0 0  0 0
  0 1 0  0 0
  0 0 1  0 0
 3 C (A) = C (I)
 3 C (At) = A
 2 N (A) = [0 0 0 1 0], [0 0 0 0 1]
 0 N (At) = 0

  B (5 x 6)
  I I
  0 0
 3 C (A)
 3 C (At)
 3 N (A)
 2 N (At)

  C (3 2)
  0 C (A)
  0 C (At)
  2 N (A)
  3 N (At)
  )

;; 9
(comment
  a
  C (At)
  N (A)

  b
  C (A)
  N (At)
  )

;; 11
(comment
  a
  r < m,

  r < m =>
  At | r < n
  )

;; 12
(comment
  1 1 1
  1 2 0
  1 0 1

  (let [A (dge 3 2 [1 1
                    0 2
                    1 0] {:layout :row})
        At (dge 2 3 [1 0 1
                     1 2 0] {:layout :row})]
    (mm A At))
  )

;; 13
(comment
  a. false
  b. true
  c. false
  )

;; 14
(comment
  C (A) = I
  C (At) = 1 2 3 4, 0 1 2 3, 0 0 1 2
  N (A) = [0 1 -2 1]
  N (At) = 0
  )

;; 15
(comment
  C(At), N(A)
  2134
  )


;; 18
(comment
  b3 - 2b2 + b1
  )

;; 19
(comment
  a
  1 2 b1
  3 4 b2
  4 6 b3

  1  2 b1
  0 -2 b2 - 3b1
  0  0 -1b1 - b2 + b3

  -1 -1 1

  b
  1 2 b1
  2 3 b2
  2 4 b3
  2 5 b4

  1  2 b1
  0 -1 b2-2b1
  0  0 -2b1+0b2+b3+0b4
  0  0 -4b1+b2+0b3+b4

  ;; zero 2x2 matrix
  (let [N (dge 2 4 [-2 0 1 0
                    -4 1 0 1] {:layout :row})
        A (dge 4 2 [1 2
                    2 3
                    2 4
                    2 5] {:layout :row})]
    (mm N A))
  )

;; 20
(comment
  N (A) = [-1 2 0 0], [-0.25 0 -3 1]

  (let [E (dge 3 3 [1 0 0
                    2 1 0
                    3 4 1] {:layout :row})
        R (dge 3 4 [4 2 0 1
                    0 0 1 3
                    0 0 0 0] {:layout :row})]
    (mm E R)
    )

  At
  1 0 -5
  0 1 4
  0 0 0
  0 0 0

  [5 -4 1]

   4 2 0 1   1 0 0
   8 4 1 5   0 1 0
  12 6 4 15  0 0 1

   4 2 0  1  1 0 0
   0 0 1  3 -2 1 0
   0 0 4 12 -3 0 1

   4 2 0  1  1  0 0
   0 0 1  3 -2  1 0
   0 0 0  0  5 -4 1

  A = ER
  E^-1 A = R

  last row E^-1 = [0 0 0 0]
  )

;; 21
(comment
  a.
  1 3 1
  2

  3 1
  6 2

  1 2 2
  3

  2 2
  6 6

  5 3
  12 8
  )

;; 22
(let [u (dge 3 1 [1 2 4])
      w (dge 3 1 [2 2 1])
      vt (dge 1 2 [1 0])
      zt (dge 1 2 [1 1])]
  (xpy (mm u vt) (mm w zt)))

;; 25
(comment
  a true
  b false
  c false
  d true
  )
