(ns ml-hello.linal.chapter3.independence-basis-dimension
  (:require [uncomplicate.commons.core :refer [with-release]]
            [uncomplicate.neanderthal
             [math :refer :all]
             [linalg :as la]
             [native :refer :all]
             [core :refer :all]]))

;; 2
(comment
  1 1 0 0
  -1 0 1 0
  0 0 -1 1
  0 -1 0 -1
  )

;; 5
(comment
  1 2 3
  0 -5 -7
  0 0 x!=0

  1 2 -3
  0 7 -7
  0 0 0
  )

;; 7
(comment
  w1, w2, w3

  v1 = w2-w3
  v2 = w1-w3
  v3 = w1-w2

  c1 (w2-w3) - c2 (w1-w3) + c3 (w1-w2) = 0
  s = [1 -1 1]
  a
  0 1 -1
  1 0 -1
  1 -1 0
  )

;; 8
(comment
  1 0 0
  0 1 0
  0 0 1
  )

;; 11
(comment
  a. line
  b. plane
  c. r3 space
  d. rn space
  )

;; 13
(comment
  1 1 0
  1 3 1
  3 1 -1

  C (A) = [1 1 3], [1 3 1]
  C (At) = [1 1 0], [1 3 1]

  1 1 0
  0 2 1
  0 0 0
  d = r = 2
  C (U) = [1 0 0], [0 1 0]
  C (Ut) = [1 1 0], [0 2 1]
  )

;; 14
(comment
  v + w
  v - w

  w = 0.5 (v + w - (v - w))
  v = 0.5 (v + w + v - w)
  )

;; 15
(comment
  d = n
  base
  m >= n
  invertible
  )

;; 16
(comment
  a. [1 1 1 1]
  b. [1 -1 0 0] [0 0 1 -1] [0 1 -1 0]
  c.
  1 -1 1
  1 1 -1
  0 -1 1
  0 1 -1

  1
  0
  1
  1
  )

;; 18
(comment
  a. might not span r4
  b. are not
  c. might be a basis
  )


;; 19
(comment
  r = n
  r = m
  r = m = n
  independent/pivot
  )


;; 20
(comment
  x -2y + 3z = 0
  Ax = 0
  r = 1

  N (A)
  2 -3
  1 0
  0 1

  xy = 2 1 0

  p = [1 -2 3]
  )

;; 21
(comment
  a. r = n = m, A is invertable,
  b. spans R5
  )

;; 22
(comment
  a. true
  b. false??
  )


;; 23
(comment
  1, 2 or 1, 3

  row spaces
  1, 2

  1 0 -1
  0 1 1
  0 0 0
  N (A) = [1 -1 1]

  row space stay fixed in elim..
  )

;; 24
(comment
  a. false: [1 1]
  b. false
  c. true comes from a: r is the same for At
  d. false: only when they are independent
  )

;; 25
(comment
  1 2 4 0 5
  0 0 c 2 2
  0 0 0 d 2

  c = 0, d= 2
  )


;; 26
(comment
  a
  1 0 0
  0 0 0
  0 0 0

  0 0 0
  0 1 0
  0 0 0

  0 0 0
  0 0 0
  0 0 1

  b
  a +
  0 1 0
  1 0 0
  0 0 0

  0 0 1
  0 0 0
  1 0 0

  0 0 0
  0 0 1
  0 1 0
  )

;; 29
(comment

  a. all
  b. all
  c. cI
  )

;; 31
(comment
  a. y (x) = c
  b. y (x) = 3x
  c. y (x) = 3x + c
  )

;; 41
(comment
  1 0 0  0  0 1  0 1  0
  0 1 0  1  0 0  0 0  1
  0 0 1  0  1 0  1 0  0
  0 0 0  1 -1 0 -1 1  0
  0 0 0  0 -1 1  0 1 -1
  )

(let [p1 (dge 3 3 [0 1 0
                   0 0 1
                   1 0 0] {:layout :row})
      p2 (dge 3 3 [1 0 0
                   0 0 1
                   0 1 0] {:layout :row})
      p3 (dge 3 3 [0 1 0
                   1 0 0
                   0 0 1] {:layout :row})
      p4 (dge 3 3 [0 0 1
                   0 1 0
                   1 0 0] {:layout :row})
      p5 (dge 3 3 [0 0 1
                   1 0 0
                   0 1 0] {:layout :row})
      ]
  (xpy p2 p4 p3 ))

;; 42
(comment
  a. x = 0
  b. 1 0 0 0
  c. 1 1 -1 -1, 1 -1 -1 1, -1 1 1 -1
  d. -1 1 1 1

  -1  1  1  1
   0  2  0  2
   0  0  2 -2
   0  0  0  4
  )
