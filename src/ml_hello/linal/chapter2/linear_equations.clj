(ns ml-hello.linal.linear-equations
  (:require [uncomplicate.commons.core :refer [with-release]]
            [uncomplicate.neanderthal
             [math :refer :all]
             [linalg :as la]
             [native :refer :all]
             [core :refer :all]]))

(let [a (dv 1 3)
      b (dv -2 2)]
  (axpy 3 a b))

(let [m (dge 3 3 [1 2 6
                  2 5 -3
                  3 2 1])
      r (dge 3 1 [6 4 2])
      ]
  (la/sv m r))

(let [m (dge 3 3 [1 1 1
                  0 0 0
                  0 0 0])
      v (dv 4 5 6)]
  (mv m v))

(let [m (dge 3 3 [1 0 0
                  0 1 0
                  0 0 1])
      v (dv 4 5 6)]
  (mm m m))

(let [m (dge 3 3 [1 1 2
                  3 2 5
                  5 -3 2])
      v (dv 1 1 -1)]
  (mv m v))

;; problem set 2.1
;; 4
(comment
  x + y = 3z = 6
  x - y + z = 4

  x = -y
  -y - y = -2

  1, -1)

;; 5
(comment x = 2 - y - z
         (2 -y - z) + 2y + z = 3
         y = 1
         2 - 1 - z + 3 + 2z = 5
         z = 1
         (0, 1, 1)

         (1, 1, 0))

(let [m (dge 3 3 [1 1 2
                  1 2 3
                  1 1 2])
      r (dge 3 1 [2 3 5])]
  (la/sv m r))

;;8
(let [m (dge 4 4 [1 0 0 0
                  1 1 0 0
                  1 1 1 0
                  1 1 1 1])
      r (dge 4 1 [3 3 3 2])]
  (la/sv m r))

;;9
(let [m (dge 3 3 [1 -2 -4
                  2 3 1
                  4 1 2])
      v (dv 2 2 3)]
  (mv m v))

(let [m (dge 4 4 [2 1 0 0
                  1 2 1 0
                  0 1 2 1
                  0 0 1 2])
      v (dv 1 1 1 2)]
  (mv m v))

;;11.3
(let [m (dge 2 3 [1 2
                  2 0
                  4 1])
      v (dv 3 1 1)]
  (mv m v))

;; 15
(let [identity-matrix (dge 2 2 [1 0
                                0 1])
      exhcange-matrix (dge 2 2 [0 1
                                1 0])
      v               (dv 2 3)
      v2              (dv 4 -6)]
  [(mv identity-matrix v), (mv exhcange-matrix v2)])

;;16
(let [rotate-90-matrix (dge 2 2 [0 1
                                 -1 0])
      v                (dv 2 3)]
  (mv rotate-90-matrix v))

(let [rotate-180-matrix (dge 2 2 [-1 0
                                  0 -1])
      v                 (dv 2 3)]
  (mv rotate-180-matrix v))

;;18
(let [m (dge 2 2 [1 0
                  -1 1] {:layout :row})
      v (dv 3 5)]
  (mv m v))

(let [m (dge 3 3 [01 0 0
                  -1 1 0
                  00 0 1] {:layout :row})
      v (dv 3 5 7)]
  (mv m v))

;;19
(let [e  (dge 3 3 [1 0 0
                   0 1 0
                   1 0 1] {:layout :row})
      e- (dge 3 3 [1 0 0
                   0 1 0
                   -1 0 1] {:layout :row})
      v  (dv 3 4 5)]
  (mv e- (mv e v)))

;;20
(let [m (dge 2 2 [1 0
                  0 0] {:layout :row})
      v (dv 5 7)]
  (mv m v))

(let [m (dge 2 2 [0 0
                  0 1] {:layout :row})
      v (dv 5 7)]
  (mv m v))

;;30
(let [markov (dge 2 2 [0.8 0.2
                       0.3 0.7])
      v      (dv 1 0)
      v2     (dv 0 1)
      v3     (dv 0.6 0.4)]
  [(reduce #(mv %2 %1) v (repeat 7 markov))
   (reduce #(mv %2 %1) v2 (repeat 7 markov))
   (reduce #(mv %2 %1) v3 (repeat 7 markov))])


