(ns ml-hello.linal.chapter3.vector-spaces-&-subspaces
  (:require [uncomplicate.commons.core :refer [with-release]]
            [uncomplicate.neanderthal
             [math :refer :all]
             [linalg :as la]
             [native :refer :all]
             [core :refer :all]]))

;; 3
(comment
  1 3 3 1
  2 6 9 5 | -2, 1
  -1 -3 3 5 | +1, 1

  p1 f p2
  1 3 3 1
  0 0 3 3
  0 0 0 0
  xp = [-2 0 1]

  1 3 0 -2
  0 0 1 1
  0 0 0 0
  s = [-3 1 0]

  x = xp + xs
  )

;; 4
(comment
  1 3 1 2 1
  2 6 4 8 3 | -2: 1
  0 0 2 4 1
  p p2
  1 3 1 2 1 | -.5: 2
  0 0 2 4 1
  0 0 0 0 0
  xp [.5 0 .5 0]

  1 3 0 0 1/2
  0 0 1 2 1/2
  0 0 0 0 0
  s1 [-3 1 0 0]
  s2 [0 0 -2 1])

;; 5
(comment
  1 2 -2 b1
  2 5 -4 b2 | -2:1
  4 9 -8 b3 | -4:1

  1 2 -2 b1
  0 1 0 b2-2b1
  0 1 0 b3-4b1 | -1:2

  1 2 -2 b1
  0 1 0 b2-2b1
  0 0 0 b3-2b1-b2

  b3-2b1-b2=0

  xp = [5b1-2b2, b2-2b1, 0]
  x + 2 (b2-2b1) = b1
  x = 5b1-2b2

  1 2 -2 b1 |-2:2
  0 1 0 b2-2b1
  0 0 0 b3-2b1-b2

  1 0 -2 0
  0 1 0 0
  0 0 0 0

  s = [2 0 1])

;; 6a
(comment
  1 2 b1
  2 4 b2 |-2:1
  2 5 b3 |-2:1
  3 9 b4 |-3:1

  1 2 b1
  0 1 b3-2b1
  0 0 b2-2b1
  0 3 b4-3b1 | -3:2

  1 2 b1
  0 1 b3-2b1
  0 0 b2-2b1
  0 0 b4+3b1-3b3

  b2 = 2b1
  3b1-3b3+b4 = 0

  xp = [5b1-2b3, b3-2b1]
  x + 2 (b3-2b1) = b1
  x = 5b1-2b3)

; 6b
(comment
  1 2 3 b1
  2 4 6 b2
  2 5 7 b3
  3 9 12 b4

  1 2 3 b1
  0 0 0 b2-2b1
  0 1 1 b3-2b1
  0 3 3 b4-3b1

  1 2 3 b1
  0 1 1 b3-2b1
  0 0 0 b2-2b1
  0 0 0 b4-3b3+3b1

  b2=2b1
  3b1-3b3+b4=0

  p1 p2 f
  1 0 1 5b1-2b3
  0 1 1 b3-2b1
  0 0 0 b2-2b1
  0 0 0 b4-3b3+3b1

  xp = [5b1-2b3, b3-2b1, 0]
  s = [-1 -1 1])

;; 7
(comment
  1 3 1 b1
  0 -1 -1 b2-3b1
  0 -2 -2 b3-2b2+4b1)

;; 8
(comment
  1 1 1 b1
  0 1 3 b2-b1
  0 0 0 b3-2b2
  b3=2b2

  1 0 -2
  0 1 3
  0 0 0

  s= [2 -3 1])

;; 10
(comment
  xp = [2, 4, 0]
  s = [1 1 1]

  1 0 -1 2
  0 1 -1 4
  )

;; 12
(comment
  Ax1 = b
  Ax2 = b
  Ax1 - Ax2 = b - b
  A (x1 - x2) = 0
  x = 0

  A (2x1-x2) = b
  A (2x1 - 2x2) = 0
  )

;; 18
(comment
  1 4 0
  0 3 5
  0 0 0

  1 2 -1
  0 3 6
  0 0 0
  )

;; 19
(comment
  a
  1 1 5
  1 0 1

  1 0 1 | r = 2
  0 1 4 |

  1  1  5
  0 -1 -4
  0 -1 -4

  b
  2 0   2 1 1   4 2 2
  1 1   0 1 2   2 2 3
  1 2           2 3 5

  4 2 2
  2 2 3 | -0.5:1
  2 3 5 | -0.5:1

  4 2 2
  0 1 2
  0 0 0
  )

(let [a (dge 3 2 [2 0
                  1 1
                  1 2] {:layout :row})
      b (dge 2 3 [2 1 1
                  0 1 2] {:layout :row})]
  (mm a b))

(let [a  (dge 2 3 [1 1 5
                   1 0 1] {:layout :row})
      at (dge 3 2 [1 1
                   1 0
                   5 1] {:layout :row})]
  (mm at a))


;; 20
(comment
  3 4 1 0
  6 5 2 1

  3  4 1 0
  0 -3 0 1

  1
  2 1

  1 0 1 0
  2 2 0 3
  0 6 5 4

  1 0  1 0
  0 2 -2 3
  0 6  5 4

  1 0  1  0
  0 2 -2  3
  0 0 11 -5

  1 0 0
  2 1 0
  0 3 1
  )

;; 21
(comment
  1 1 1  4

  x = xp + s1 + s2
  xp [4 0 0]
  s1 [-1 1 0]
  s2 [-1 0 1]

  1  1 1 4
  1 -2 1 4

  1 0 1 4
  0 1 0 0

  xp [4 0 0]
  s  [-1 0 1]
  )

;; 23
(comment
   6  4  2
  -3 -2 -1
   9  6  q
   a| q = 3
   b| q != 3

   3 1 3
   q 2 q
   a| q = 6
   b| q != 6
  )

;; 25
; a| r <= n < m
; b| r = m < n
; c| r = n < m
; d| r = n = m

;; 26
(comment
  1 0 -2
  0 1  2
  0 0  0

  1 0 0
  0 1 0
  0 0 1
  )

;; 28
(comment
  p   p
  1 2 0 | 0
  0 0 1 | 0

  s1 [-2 1 0]

  p   p
  1 2 0 | -1
  0 0 1 |  2

  xp [-1 0 2]
  s  [-3 1 2]
  )

;; 29
(comment
  1 0 0 -1
  0 0 1  2
  0 0 0  5

  xp [-1 1 2]
  s [0 1 0]
  )

;; 30
(comment
  p p f  p
  1 0 2  3 |0 2
  0 1 0 -1 |0 1
  0 0 0  1 |0 1

  x = xp + cs
  xp [-1 2 0 1]
  s [-2 0 1 0]
  )

;; 31
(comment
  Ax [1 2 3]
  x [0 1]

  1 1  0 1
  1 2
  0 3


  Bx [0 1]
  x [1 2 3]

  B   1 2 3    [0 1]
  )

;; 32
(comment
  1  3  1  1
  0 -1  2  2
  0  0  0  0
  0  0  0  0

  1 0 0 0
  1 1 0 0
  2 2 1 0
  1 2 0 1

  xp [7 -2 0]
  xs [-7 2 1]


  1  3  1   1
  0 -1  2  -1
  0 -2  4  -2
  0  0  0   1

  L
  1 0 0 0
  1 1 0 0
  2 0 1 0
  1 0 1 1

  )

;; 33
(comment
  Ax = [1 3]
  xp = [1 0]
  xs = [0 1]

  1 0  1
  3 0  3
  )

;; 34
(comment

  Ax = 0
  xs = [2 3 1 0]

  a. r = 3
  b.
  1 0 -2 0
  0 1 -3 0
  0 0  0 1

  c.
  )

;; 36
(comment
  Ax = b
  Cx = b
  no)