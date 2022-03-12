(ns ml-hello.linal.chapter3.nullspace-of-a
  (:require [uncomplicate.commons.core :refer [with-release]]
            [uncomplicate.neanderthal
             [math :refer :all]
             [linalg :as la]
             [native :refer :all]
             [core :refer :all]]))

(let [a (dge 3 4 [1 1 2 4
                  1 2 2 5
                  1 3 2 6] {:layout :row})]
  (:sigma (la/svd a)))

;; 3.2
;; 4
(comment
  a) -1 3 5
-2 6 10

1 -3 -5
0 0 0

s [3 1 0]
s [5 0 1]

b ) -1 3 5
-2 6 7

1 -3 0
0 0 1

s [3 1 0]
)

;; 5
; a) false —> 3 column is a multiple of 1 and 2 or 3rd row is a multiple of 1+2
; b) true —> has n pivots, so r = n - p = 0
; c) true
; d) true

;; 6
(comment
  1 2 3 4 5 6 7

  c
  0 0 0 1 1 1 1
  0 0 0 0 0 1 1
  0 0 0 0 0 0 0
  0 0 0 0 0 0 0

  b
  1 1 1 1 1 1 1
  0 0 1 1 1 1 1
  0 0 0 0 0 1 1
  0 0 0 0 0 0 1

  a
  0 1 1 1 1 1 1
  0 0 0 1 1 1 1
  0 0 0 0 1 1 1
  0 0 0 0 0 0 0
  )

;; 7
(comment
  1 2 3 4 5 6 7 8

  b
  0 1 1 0 0 1 1 1
  0 0 0 1 0 1 1 1
  0 0 0 0 1 1 1 1
  0 0 0 0 0 0 0 0

  a
  1 1 0 1 1 1 0 0
  0 0 1 1 1 1 0 0
  0 0 0 0 0 0 1 0
  0 0 0 0 0 0 0 1
  )

;; 8
(comment

  1 2 3 0 5
  1 2 3 0 5
  1 2 3 0 5
  1 2 3 0 5
  s [0 0 0 1 0]
  )

;; 12
(comment
  x - 3y - z = 0

  [1 3 1]

  x = 3y + z

  | 3 | | 1 |
  x = y| 1 | + z| 0 |
  | 0 | | 1 |

  s1 = [3 1 0]
  s2 = [1 0 1]
  )

;; 13
(comment

  x - 3y - z = 12

  [1 -3 -1]

  x = 12 + 3y +z

  |12| | 3 | | 1 |
  x = | 0| + y| 1 | + z| 0 |
  | 0| | 0 | | 1 |
  )

;; 18
(comment
  1 0 x
  1 1 y
  0 1 z
  )

;; 19
(comments
  1 1 3 -5
  1 2 2 -5
  1 3 1 -5
  )

;; 20
(comment
  0 1
  0 0
  s1 = 0 0
  s2 = 1 0

  f = 1
  r = 1
  n - f = 1
  n - r = 1
  )

;; 21
(comment
  0 0 1
  0 0 0
  1 0 0

  n - f = r
  n - r = r
  )

;; 24
(comment
  a. only if A is symmetric
  1 2
  1 2, s [-2 1]
  vs
  1 1
  2 2 s [-1 1]

  b. example above ^.

  c. example a.
  1 2
  0 0

  Rt = 1 0
  2 0
  )

;; 25
(comment
  s [2 1 0 1]
  1 0 0 -2
  0 1 0 -1
  0 0 1 0
  )

;; 26
(comment

  1 -2 -3

  1 0 0
  0 1 0

  I
  )

;; 27
(comment
  1 1
  0 1

  1 0
  0 1

  1 0
  0 0

  1 1
  0 0

  0 0
  0 0

  b.
  1 1 1
  1 1 0
  1 0 0
  1 0 1
  0 1 1
  0 1 0
  0 0 0
  0 0 1 yes
  )

;; 31
(comment
  1 2 3 4
  2 3 4 5
  3 4 5 6

  1 1 1 1
  0 1 2 3
  0 0 0 0
  )

;; 33
; a true
; b false
; c true
; d false

;; 34
(comment
  a
  1 2 0
  0 0 1
  0 0 0

  b
  1 2 0 1 2 0
  0 0 1 0 0 1
  0 0 0 0 0 0

  C correct
  1 2 0 0 0 0
  0 0 1 0 0 1
  0 0 0 0 0 0
  0 0 0 1 2 0
  0 0 0 0 0 1
  0 0 0 0 0 0
  )

;; 36
(comment
  2 0 0 2
  0 0 0 0
  )

;; 37
(comment

  1 1 2
  1 2 3
  1 3 4

  1 0 1
  0 1 1
  0 0 0


  1 1 1
  1 2 3
  2 3 4

  1 0 -1
  0 1 2
  0 0 0
  )

;; 38
(let [R  (dge 3 4 [1 0 2 3
                   0 1 4 5
                   0 0 0 0] {:layout :row})
      s1 (dv -2 -4 1 0)
      s2 (dv -3 -5 0 1)
      ]
  (= (mv R s2) (mv R s1))
  )

(let [R  (dge 3 3 [0 1 2
                   0 0 0
                   0 0 0] {:layout :row})
      s1 (dv 1 0 0)
      s2 (dv 0 -2 1)]
  (= (mv R s2) (mv R s1)))

;; 39
(let [a (dge 3 3 [1 2 4
                  2 4 8
                  4 8 16] {:layout :row})
      b (dge 3 3 [3 9 -4.5
                  1 3 -1.5
                  2 6 -3] {:layout :row})
      ]
  (:sigma (la/svd b)))

;; 41
(let [a (dge 3 3 [3 6 6
                  1 2 2
                  4 8 8] {:layout :row})
      u (dge 3 1 [3
                  1
                  4] {:layout :row})
      v (dge 1 3 [1 2 2] {:layout :row})]
  (= a (mm u v)))

(let [a (dge 2 4 [02 02 06 04
                  -1 -1 -3 -2] {:layout :row})
      u (dge 2 1 [02
                  -1] {:layout :row})
      v (dge 1 4 [1 1 3 2] {:layout :row})]
  (= a (mm u v)))

;; 46
(let [a (dge 2 2 [1 2
                  2 4] {:layout :row})
      b (dge 2 3 [2 1 4
                  3 1.5 6] {:layout :row})]
  (mm a b))

;; 48
(let [a (dge 2 2 [-1 1 -1 1] {:layout :row})
      b (dge 2 2 [1 1 1 1] {:layout :row})]
  (mm a b))

;; 51
(let [a (dge 2 3 [2 -1 -6
                  1 -2 -2] {:layout :row})
      b (dge 3 2 [4 3
                  1 0
                  1 1] {:layout :row})]
  (mm a b)
  (mm b a))

;; 58
(comment

  I F
  0 0

  I
  0
  )