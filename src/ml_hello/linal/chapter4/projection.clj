(ns ml-hello.linal.chapter4.projection
  (:require [uncomplicate.commons.core :refer [with-release]]
            [uncomplicate.neanderthal
             [math :refer :all]
             [linalg :as la]
             [native :refer :all]
             [core :refer :all]]))


(def I (dge 3 3 [1 0 0, 0 1 0, 0 0 1]))
(def I4 (dge 4 4 [1 0 0 0, 0 1 0 0, 0 0 1 0, 0 0 0 1]))

(defn inv [A] (la/tri (la/trf A)))

(defn get-p [a b]
  (scal (/ (dot a b) (dot a a)) a))

(defn get-P [A]
  (mm A
      (inv (mm (trans A) A))
      (trans A)))

(let [a (dge 3 3 [1 2 2
                  2 4 4
                  2 4 4] {:layout :row})]
  (scal 1/9 (mm a a)))

(let [A (dge 3 2 [1 1 1, 0 1 2])
      b (dv 6 0 0)
      P (get-P A)
      ]
  (mm  P (dge 3 1 b)))

;; p = A (At A)^-1 At b
;; e = -p + b
;; p = (A At / At A) b = A (At b / At A)

(mm
  (dge 3 3 [1 0 0
            0 1 0
            0 0 0])
  (dge 3 3 [0 0 0
            0 0 0
            0 0 1] {:layout :row})
  )

;; 4.2 a
(let [b   (dv 3 4 4)
      a   (dv 2 2 1)
      atb (dot a b)
      ata (dot a a)
      p   (scal (/ atb ata) a)
      e   (axpy -1 p b)]
  (prn "p =" p)
  (prn "e =" e)
  (dot p e)
  )

(let [A       (dge 3 2 [2 2 1
                        1 0 0])
      b       (dv 3 4 4)
      At      (trans A)
      AtA     (mm At A)
      AtA_inv (inv AtA)                                     ;; inverse
      P       (mm A AtA_inv At)
      p*      (mv P b)
      e       (axpy -1 p* b)]
  (prn "p* =" p*)
  (prn "e = " (axpy -1 p* b))
  (mm (dge 1 3 e) A)
  )

;; 4.2 b
(let [a     (dv -1 -3 -1)
      b     (dv 1 3 1)
      x-hat (/ (dot a b) (dot a a))
      p     (scal x-hat a)
      e     (axpy -1 p b)]
  (prn "x-hat: " x-hat ", p: " p ", e: " e))

;; 4.1 a
(let [a     (dv 1 1 1)
      b     (dv 1 2 2)
      x-hat (/ (dot a b) (dot a a))
      p     (scal x-hat a)
      e     (axpy -1 p b)
      ]
  (prn "x-hat: " x-hat)
  (prn "projection: " p)
  (prn "e = " e)
  )

;; 4.2a
(comment
  A At = [1] [1 0] = 1 0
  [0] 0 0
  At A = 1

  x-hat = [1 0]
  [0 0]

  p = [1 0] * [cos]
  [0 0] [sin]

  p [cos 0]
  )


;; 4.2 b
(let [a  (dv 1 -1)
      b  (dv 1 1)
      xh (/ (dot a b) (dot a a))
      p  (scal xh a)
      e  (axpy -1 p b)]
  (prn "p " p ", xh " xh ", e " e)
  )


;; 4.3a
(let [a  (dv 1 1 1)
      b  (dv 1 2 2)
      A  (dge 3 1 a)
      At (dge 1 3 a)
      P  (scal (/ 1 (dot a a)) (mm A At))]
  (prn "P: " P)
  (prn "P^2 = P " (= P (mm P P)))
  (prn "p = " (mv P b)))

;; 4.3b
(let [a  (dv -1 -3 -1)
      b  (dv 1 3 1)
      A  (dge 3 1 a)
      At (dge 1 3 a)
      P  (scal (/ 1 (dot a a)) (mm A At))]
  (prn "P: " P)
  (prn "P^2 " (mm P P))
  (prn "p = " (mv P b))
  )

;; 5 ... ading up 7
(let [a1  (dv -1 2 2)
      a2  (dv 2 2 -1)
      a3  (dv 2 -1 2)

      A1  (dge 3 1 a1)
      A2  (dge 3 1 a2)
      A3  (dge 3 1 a3)

      A1T (dge 1 3 a1)
      A2T (dge 1 3 a2)
      A3T (dge 1 3 a3)

      P1  (scal (/ 1 (dot a1 a1))
                (mm A1 A1T))
      P2  (scal (/ 1 (dot a2 a2))
                (mm A2 A2T))
      P3  (scal (/ 1 (dot a3 a3))
                (mm A3 A3T))
      ]
  (xpy P1 P2 P3))

;; 6
(let [b  (dv 1 0 0)
      a1 (dv -1 2 2)
      a2 (dv 2 2 -1)
      a3 (dv 2 -1 2)
      p1 (get-p a1 b)
      p2 (get-p a2 b)
      p3 (get-p a3 b)]
  (xpy p1 p2 p3))

;; 8...9
(let [b    (dv 1 1)
      a1   (dv 1 0)
      a2   (dv 1 2)
      1   (get-p a1 b)
      p2   (get-p a2 b)
      A    (dge 2 2 (concat (seq a1) (seq a2)))
      At   (dge 2 2 (flatten (seq A)) {:layout :row})
      AtAi (inv (mm At A))
      ]
  #_(xpy p1 p2)

  ;; P = A (At A)^-1 At  = A A^-1 At^-1 At  = I * I = I
  ;; basically if A is invertible square matrix, it covers whole R
  (mm (mm A AtAi) At)
  )

;; 10
(let [a1 (dv 1 0)
      a2 (dv 1 2)
      p1 (get-p a2 a1)
      p2 (get-p a1 p1)

      ;; projection onto a2
      P1 (scal (/ 1 (dot a2 a2))
               (mm (dge 2 1 a2) (dge 1 2 a2)))
      ;; projection onto a1
      P2 (scal (/ 1 (dot a1 a1))
               (mm (dge 2 1 a1) (dge 1 2 a1)))]
  (= p2 (mv P2 (mv P1 a1)))
  #_(mm (mm P1 P2) (mm P1 P2))
  )

;; 11
(let [A    (dge 3 2 [1 0 0
                     1 1 0])
      b    (dv 2 3 4)
      AtAi (inv (mm (trans A) A))
      P    (mm A (mm AtAi (trans A)))
      p    (mv P b)
      e    (axpy -1 p b)]
  (prn "p " p)
  (prn "e " e)
  (mv (trans A) e))

(let [A  (dge 3 2 [1 1 0, 1 1 1])
      At (trans A)
      b  (dv 4 4 6)
      P  (mm A (mm (inv (mm At A)) At))
      p  (mv P b)
      e  (axpy -1 p b)
      ]
  (prn "p " p)
  (prn "e " e)
  (mv (trans A) e)
  P)

;; 12
(let [P1 (dge 3 3 [1 0 0
                   0 1 0
                   0 0 0])
      P2 (dge 3 3 [0.5 0.5 0
                   0.5 0.5 0
                   0 0 1])
      ]
  (and (= P1 (mm P1 P1))
       (= P2 (mm P2 P2)))
  )

;; 13
(let [A    (dge 4 3 [1 0 0
                     0 1 0
                     0 0 1
                     0 0 0] {:layout :row})
      b    (dv 1 2 3 4)
      AtAi (inv (mm (trans A) A))
      P    (mm A (mm AtAi (trans A)))
      p (mv P b)]
  (prn "p " p ", P" P))

;; 14
(let [b (dv 0 2 4)
      A (dge 3 2 [0 1 2, 1 2 0])
      AtA-inverse (inv (mm (trans A) A))
      P (mm A (mm AtA-inverse (trans A)))
      p (mv P b)]
  (prn "p " p ", P" P))

;; 16
(let [A (dge 3 2 [1 2 -1, 1 0 1])
      b (dv 2 1 1)
      AtA-inv (inv (mm (trans A) A))
      P (mm A (mm AtA-inv (trans A)))
      p (mv P b)
      ]
  ;(la/sv A (dge 3 1 b))
  (= P (get-P A)))

;; 17
; (I - P) (I - P)
; I^2 - P - P +  P
; I - P

;; 19
; N(A) = 1 -1 -2
; C(A) = 1 1 0, 2 0 1
(let [A (dge 3 2 [1 1 0, 2 0 1])
      P (get-P A)
      ;; 20
      e (dv 1 -1 -2)
      E (dge 3 1 e)
      Q (scal (/ 1 (dot e e)) (mm E (trans E)))
      ]
  (axpy -1 Q I)
  )

;; 26
;; A^2 = A
;; A^2 * A^-1 = A * A^-1
;; A = I cat

;; 28
(let [p2    (scal (/ 1 6.0) (dv 2 2 2))
      p-len (nrm2 p2)]
  (* p-len p-len))

;; 30a
(let [a (dv 3 4)
      A (dge 2 1 a)
      At (trans A)
      P (scal (/ 1 (dot a a))
              (mm A At))]
  P)

;; 30a
(let [a (dv 3 6 6)
      A (dge 3 1 a)
      At (trans A)
      P (scal (/ 1 (dot a a))
              (mm A At))
      B (mm P
            (dge 2 3 [3 4, 6 8, 6 8])
            (dge 2 2 [0.36 0.48, 0.48 0.64]))
      ]
   B)

;; p = A (At A)^-1 At b
;; e = -p + b
;; p = (A At / At A) b = A (At b / At A)
