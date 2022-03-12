(ns ml-hello.linal.length-and-dot-products
  (:require [uncomplicate.commons.core :refer [with-release]]
            [uncomplicate.neanderthal
             [math :refer :all]
             [linalg :as la]
             [native :refer :all]
             [core :refer :all]]))

(defn angle [a] (Math/toDegrees (Math/acos a)))

;; 1.2
;; dot product
(let [v (dv 1 3 2)
      w (dv 4 -4 4)]
  (dot v w))

;; length
(let [v (dv 1 3 2)]
  (nrm2 v))

;; unit vector
(let [v    (dv 1 3 2)
      unit (scal (/ (nrm2 v)) v)]
  (dot unit unit))

;; angle uv / |v||u|
(let [v (dv 1 0)
      u (dv 1 1)]
  (angle (/ (dot v u) (nrm2 v) (nrm2 u))))



;; angle between u & v is positive, because dot.p > 0
(let [v (dv 3 1)
      u (dv 1 3)]
  (dot v u))

(let [v (dv 2 1)
      u (dv 1 2)]
  (/ (dot v u) (nrm2 v) (nrm2 u)))

;; 1.2A
(let [v      (dv 3 4)
      w      (dv 4 3)
      vw     (dot v w)
      vw-nrm (* (nrm2 v) (nrm2 w))
      vpw    (xpy w v)
      ]
  [(and
     (<= vw vw-nrm)
     (<= (nrm2 vpw) (+ (nrm2 v) (nrm2 w))))
   (/ vw vw-nrm)])

;; 1.2B
(let [v (dv 3 4)
      u (dv -4 3)
      w (dv 4 -3)]
  (= 0.0 (dot v w) (dot v u)))

(let [a (dge 2 2 [2 -1
                  -1 2] {:layout :row})
      r (dge 2 1 [1 0] {:layout :row})]
  (la/sv a r))

;; problem set 1.2
;; 1
(let [u (dv -0.6 0.8), v (dv 4 3), w (dv 1 2)]
  [(int (dot v u))
   (int (dot u w))
   (int (dot u (xpy v w)))
   (int (dot w v))])

;; 2
(let [u   (dv -0.6 0.8), v (dv 4 3), w (dv 1 2)
      |u| (nrm2 u), |v| (nrm2 v) |w| (nrm2 w)]
  [|u|, |v|, |w|
   (<= (dot u v) (* |u| |v|))
   (<= (dot v w) (* |v| |w|))])

;; 3
(let [u      (dv -0.6 0.8), v (dv 4 3), w (dv 1 2)
      |u|    (nrm2 u), |v| (nrm2 v) |w| (nrm2 w)
      unit-v (scal (/ |v|) v), unit-w (scal (/ |w|) w)]
  [unit-v, unit-w, (angle (/ (dot v w) |v| |w|))])

;; 5
(let [v  (dv 1 3),
      w  (dv 2 1 2)
      uv (scal (/ (nrm2 v)) v)
      uw (scal (/ (nrm2 w)) w)]
  [uv uw])

;; 7
(let [v   (dv 1 (Math/sqrt 3))
      w   (dv 1 0)
      |v| (nrm2 v)
      |w| (nrm2 w)]
  (angle (/ (dot v w) |v| |w|)))

(let [v (dv 2 2 -1)
      w (dv 2 -1 2)]
  90)

(let [v   (dv 1 (sqrt 3))
      w   (dv -1 (sqrt 3))
      |v| (nrm2 v)
      |w| (nrm2 w)]
  (angle (/ (dot v w) |v| |w|)))

(let [v   (dv 3 1), w (dv -1 -2),
      |v| (nrm2 v) |w| (nrm2 w)]
  (angle (/ (dot v w) |v| |w|)))

;; 8(c)
;u v = 0
;|u - v| = 2^0.5
;(u - v) (u - v)  = 2
;1 + 1 = 2
