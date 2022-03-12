(ns ml-hello.linal.chapter4.least-squares-approximations
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
(defn get-P [A] (mm A (inv (mm (trans A) A)) (trans A)))

;; 1
(let [B (dge 4 1 [0 8 8 20])
      A (dge 4 2 [1 1 1 1
                  0 1 3 4])
      x (la/sv (mm (trans A) A)
               (mm (trans A) B))
      P (get-P A)
      ; p (mm A x)
      p (mm P B)        #_(1 5 13 17)
      e (axpy -1 p B)]  #_(-1 3 -5 3)
  x)

;; 2
(comment
  C + 0D = 0
  C + 1D = 8
  C + 3D = 8
  C + 4D = 20

  C + 0D = 1 | c = 1
  C + 1D = 5 | d = 4
  C + 3D = 13 | good
  C + 4D = 17 | good)

;; 3
(let [e (dv -1 3 -5 3)
      A (dge 4 2 [1 1 1 1
                  0 1 3 4])]
  (mm (dge 1 4 e) A))

;; 4
(comment
  E = | Ax - b | ^2

  E = C ^2 + (C + D - 8) ^2 + (C + 3D - 8) ^2 + (C + 4D - 20) ^2
  dE/dC =C + (C + D - 8) + (C + 3D - 8) + (C + 4D - 20) = 0 | ??
  dE/dD = (C + D - 8) + 3 (C + 3D - 8) + 4 (C + 4D - 20) = 0 |??)


;; 6
(let [b (dv 0 8 8 20)
      a (dv 1 1 1 1)
      p (get-p a b) #_(9 9 9 9)
      e (axpy -1 p b) #_(-9 -1 -1 11)]
  (sqrt (dot e e)))

;; 8
(let [b (dv 0 8 8 20)
      a (dv 0 1 3 4)
      p (get-p a b) #_(0 4.31 12.92 17.23)
      e (axpy -1 p b) #_(0 3.69 -4.92 2.77)]

  (dot e a))

;; 9
(let [A   (dge 4 3 [1 1 1 1
                    0 1 3 4
                    0 1 9 16])
      B   (dge 4 1 [0 8 8 20])
      AtA (mm (trans A) A)
      AtB (mm (trans A) B)
      x   (la/sv AtA AtB) #_(2 4/3 2/3)]

  x)

(comment
  c + 0t + 0t ^2 = 0
  c + 1t + 1t ^2 = 8
  c + 3t + 9t ^2 = 8
  c + 4t + 16t ^2 = 20)


;; 10
(let [A   (dge 4 4 [1 1 1 1
                    0 1 3 4
                    0 1 9 16
                    0 1 27 64])
      B   (dge 4 1 [0 8 8 20])
      AtA (mm (trans A) A)
      AtB (mm (trans A) B)
      x   (la/sv AtA AtB) #_(2 4/3 2/3)
      P   (get-P A)
      p   (mm P B)]
  p)

;; 12

(let [a (dv [1 1 1])
      b (dv [1 2 6])
      p (get-p a b)
      e (axpy -1 p b)]
  p)

;; 17, 18
(let [A   (dge 3 2 [1 1 1, -1 1 2])
      B   (dge 3 1 [7 7 21])
      AtA (mm (trans A) A)
      AtB (mm (trans A) B)
      xh  (la/sv AtA AtB)
      P   (get-P A)
      p   (mm P B)
      e   (axpy -1 p B)]

  xh)


;; 19
(let [A   (dge 3 2 [1 1 1, -1 1 2])
      B   (dge 3 1 [2 -6 4])
      AtA (mm (trans A) A)
      AtB (mm (trans A) B)
      xh  (la/sv AtA AtB)
      P   (get-P A)
      p   (mm P B)
      e   (axpy -1 p B)]

  (prn "e " e, "p" p, "xh " xh))

;; 20
(let [A   (dge 3 2 [1 1 1, -1 1 2])
      B   (dge 3 1 [5 13 17])
      AtA (mm (trans A) A)
      AtB (mm (trans A) B)
      xh  (la/sv AtA AtB)
      p   (mm A xh)
      e   (axpy -1 p B)]

  (prn "e " e, "p" p, "xh " xh))

;; 22
(let [A (dge 5 2 [1 1 1 1 1
                  -2 -1 0 1 2])
      B (dge 5 1 [4 2 -1 0 0])
      xh (la/sv (mm (trans A) A)
                (mm (trans A) B)) #_(1, -1)]

  xh)

;; 23

;; At Ax = At b
;; e = -p + b
;; p = A (At A)^-1 At b
;; p = (A At / At A) b = A (At b / At A)
