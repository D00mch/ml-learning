(ns ml-hello.linal.chapter4.orthonormal-bases-and-gram-schmidt
  (:require [uncomplicate.commons.core :refer [with-release]]
            [uncomplicate.neanderthal
             [math :refer :all]
             [linalg :as la]
             [native :refer :all]
             [core :refer :all]]))

(def I (dge 3 3 [1 0 0, 0 1 0, 0 0 1]))
(def I4 (dge 4 4 [1 0 0 0, 0 1 0 0, 0 0 1 0, 0 0 0 1]))
(defn inv [A] (la/tri (la/trf A)))
(defn get-p [a b] (scal (/ (dot a b) (dot a a)) a))
(defn ->sqrt [A] (mm (trans A) A))
(defn get-P [A] (mm A (inv (->sqrt A)) (trans A)))

(defn find-q [a1 a2 a3]
  (let [q1 (scal (/ 1 (nrm2 a1)) a1)
        B  (axpy -1 (scal (dot q1 a2) q1) a2)
        q2 (scal (/ 1 (nrm2 B)) B)
        C* (axpy -1 (scal (dot q1 a3) q1) a3)
        C  (axpy -1 (scal (dot q2 C*) q2) C*)
        q3 (scal (/ 1 (nrm2 C)) C)]
    [q1 q2 q3]))

(let [Q (dge 3 3 [0 1 0
                  0 0 1
                  1 0 0])]
  (mm (trans Q) Q))

(let [Q (scal! 0.5 (dge 4 4 [1 1 1 1
                             1 -1 1 -1
                             1 1 -1 -1
                             1 -1 -1 1]))]
  (mm (trans Q) Q))

(let [Q (scal! 1/3 (dge 3 3 [1 2 2
                             -2 -1 2
                             2 -2 1]))]
  (->sqrt Q))

;; 4a
(let [Q (dge 3 2 [1 0 0
                  0 1 0])]
  (mm (trans Q) Q))

;; 4c
(let [q1 (dv 1 1 1)
      q2 (dv -1 0 1)
      q3 (dv -1 2 -1)]
  (dot q1 q2))

;; 9
(let [q1 (dv 0.8, 0.6 0)
      q2 (dv -0.6 0.8 0)
      Q  (dge 3 2 (concat q1 q2))
      P  (mm Q (trans Q))
      ]
  (= P (mm P P))
  )

;; 11
(let [a    (dv 1 3 4 5 7)
      b    (dv -7 3 4 -5 1)
      ab   (dot a b)
      aa   (dot a a)
      p    (scal (/ ab aa) (dge 5 1 a))
      B    (axpy -1 p (dge 5 1 b))
      blen (nrm2 B)]
  blen)



;; 15
(let [a   (dv 1 2 -2)
      b   (dv 1 -1 4)
      c   (dv 0 0 1)
      A   (dge 3 2 (concat a b))
      Q   (la/org! (la/qrf A))
      B   (dge 3 1 [1 2 7])
      AtA (mm (trans A) A)
      AtB (mm (trans A) B)
      xh  (la/sv AtA AtB)
      ]
  (mm (trans Q) B)
  Q)

;; 16
(let [a    (dv 4 5 2 2)
      b    (dv 1 2 0 0)
      p_ab (get-p a b)
      A    (dge 4 2 (concat a b))
      Q    (la/org! (la/qrf A))]
  Q)

;; 17
(let [a (dv 1 1 1)
      b (dv 1 3 5)
      p (get-p a b)
      e (axpy -1 p b)
      Q (la/org! (la/qrf (dge 3 2 (concat a b))))
      ]
  [p e]
  Q)

;; 18
(let [a (dv 1 -1 0 0)
      b (dv 0 1 -1 0)
      c (dv 0 0 1 -1)
      Q (la/org! (la/qrf (dge 4 3 (concat a b c))))
      ]
  Q)

;; 21
(let [A (dge 4 2 [1 1 1 1, -2 0 1 3])
      B (dge 4 1 [-4 -3 3 0])
      Q (la/org! (la/qrf A))
      p (mm Q (trans Q) B)
      e (axpy -1 p B)]
  e)

;; 22
(let [A (dge 3 3 [1 1 1, 1 -1 0, 1 0 4])
      Q (la/org! (la/qrf A))
      ]
  Q)

;; 24
(let [
      A   (dge 4 3 [-1 -1 1, 1 0 0, 0 1 0, 0 0 1] {:layout :row})
      B   (dge 4 1 [1 1 1 1])
      P   (get-P A)
      AtA (mm (trans A) A)
      AtB (mm (trans A) B)
      x   (la/sv AtA AtB)
      p   (mm A x)
      ]
  p)

;; 26
(find-q (dv 1 0 0) (dv 0 0 3) (dv 1 2 3))

;; 29
(let [a (dv 2 2 -1)
      b (dv 0 -3 3)
      c (dv 1 0 0)
      A (dge 3 3 (concat a b c))]
  (dge 3 3 (apply concat (find-q a b c)))
  (class (la/org! (la/qrf A)))
  )

;; 30
(let [sqrt2 (sqrt 2)
      W     (scal 0.5 (dge 4 4 [1 1 1 1,
                                1 1 -1 -1,
                                sqrt2 (- sqrt2) 0 0
                                0 0 sqrt2 (- sqrt2)]))]
  (inv W))

;; 31
(let [c    0.5
      q1   (scal c (dv 1 -1 -1 -1))
      q2   (scal c (dv -1 1 -1 -1))
      Q    (scal c (dge 4 4 (concat q1 q2 [-1 -1 1 -1, -1 -1 -1 1])))
      b    (dv 1 1 1 1)
      Q12  (scal c (dge 4 2 (concat q1 q2)))
      pq1  (get-p q1 b)
      pq2  (get-p q2 b)
      pq12 (la/org! (la/qlf Q12))
      ]
  ;; projection to the plane =
  (axpy 1 pq1 pq2)
  )

;; 35
(let [I (dge 4 4 [1 0 0 0, 0 1 0 0, 0 0 1 0, 0 0 0 1])
      D (dge 4 4 [0 0 0 0
                  1 0 0 0
                  0 1 0 0
                  0 0 1 0] {:layout :row})
      A (scal 1 (axpy -1 D I))
      Q (la/org! (la/qlf A))
      ]
  Q)

(la/det (la/trf I))

;; At AX̂ = At b         |  X̂ = Q b
;; e = -p + b
;; p = A (At A)^-1 At b |  p = Q Qt b
;; p = (A At / At A) b = A (At b / At A)
