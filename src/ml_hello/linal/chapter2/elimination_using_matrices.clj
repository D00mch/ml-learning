(ns ml-hello.linal.elimination-using-matrices
  (:require [uncomplicate.commons.core :refer [with-release]]
            [uncomplicate.neanderthal
             [math :refer :all]
             [linalg :as la]
             [native :refer :all]
             [core :refer :all]]))

;; Problem set 2.3
;; 3
(let [m   (dge 3 3 [01 1 0
                    04 6 1
                    -2 2 0] {:layout :row})
      e21 (dge 3 3 [01 0 0
                    -4 1 0
                    00 0 1] {:layout :row})
      e31 (dge 3 3 [1 0 0
                    0 1 0
                    2 0 1] {:layout :row})
      e32 (dge 3 3 [1 0 0
                    0 1 0
                    0 -2 1] {:layout :row})
      e   (->> e21 (mm e31) (mm e32))
      ;e   (->> e21 (mm (mm e32 e31)))
      ]
  (mm e m))

;; 4
(let [m  (dge 3 3 [01 1 0
                   04 6 1
                   -2 2 0] {:layout :row})
      v  (dge 3 1 [1 0 0])
      e  (dge 3 3 [1 0 0
                   -4 1 0
                   10 -2 1] {:layout :row})
      m0 (mm e m)
      m1 (mm e v)]
  (la/sv m0 m1))

;; 7a
(let [e  (dge 3 3 [1 0 0
                   0 1 0
                   -7 0 1] {:layout :row})
      er (dge 3 3 [1 0 0
                   0 1 0
                   7 0 1] {:layout :row})
      m  (dge 3 3 [01 1 0
                   04 6 1
                   -2 2 0] {:layout :row})]
  (mm er (mm e m)))

;; 9a
(let [r (dge 3 3 [0 0 1
                  0 1 0
                  1 0 0] {:layout :row})
      m (dge 3 3 [1 2 3
                  4 5 6
                  7 8 9] {:layout :row})]
  (mm r (mm m r)))

(let [r (dge 3 3 [1 0 0
                  -1 1 0
                  -1 0 1] {:layout :row})
      m (dge 3 3 [1 2 3
                  1 3 1
                  1 4 0] {:layout :row})]
  (mm r m))

;; 16a
(let [m (dge 2 2 [2 -1
                  1 1] {:layout :row})
      r (dge 2 1 [0 33])]
  (la/sv m r))

;; 16b
(let [m (dge 2 2 [2 3, 1 1])
      r (dge 2 1 [5 7])]
  (la/sv m r))

(comment
  5 = 2m + c
  7 = 3m + c)

;; 17
(comment
  y = a + bx + cx ^2
  (x, y) (1, 4) (2, 8) (3, 14)

  4 = a + b + c
  8 = a +2b +4c
  14 = a + 3b + 9c
  )

(let [m (dge 3 3 [1 1 1
                  1 2 3
                  1 4 9])
      r (dge 3 1 [4 8 14])]
  (la/sv m r))

;; 19
(let [p (dge 3 3 [0 1 0
                  1 0 0
                  0 0 1] {:layout :row})
      q (dge 3 3 [0 0 1
                  0 1 0
                  1 0 0] {:layout :row})]
  (mm p q)
  (mm q p)
  (mm p p))

;; 25
(comment
  [1 2 3 1
   2 3 4 2
   3 5 7 6] -2

  [1 02 03 1
   0 -1 -2 0
   3 05 07 6] -3

  [1 02 03 1
   0 -1 -2 0
   0 -1 -2 3] -1

  [1 02 03 1
   0 -1 -2 0
   0 00 00 3] => should be 0, if it's 3 at the beginning
  )

;; 28
(comment
  ab = i as Identity matrix
  bc = i

  a = ai = a (bc) = ab (c) = i (c) = c
  )