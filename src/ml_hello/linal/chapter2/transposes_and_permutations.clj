(ns ml-hello.linal.chapter2.transposes-and-permutations
  (:require [uncomplicate.commons.core :refer [with-release]]
            [uncomplicate.neanderthal
             [math :refer :all]
             [linalg :as la]
             [native :refer :all]
             [core :refer :all]]))

(let [A  (dge 2 3 [-1 0, 1 -1, 0 1])
      At (dge 3 2 [-1 0, 1 -1, 0 1] {:layout :row})]
  (prn (mm A At))
  (prn (mm At A)))

;; 11
(let [A  (dge 3 3 [0 0 6
                   1 2 3
                   0 4 5] {:layout :row})
      P  (dge 3 3 [0 1 0
                   0 0 1
                   1 0 0] {:layout :row})
      P1 (dge 3 3 [1 0 0
                   0 0 1
                   0 1 0] {:layout :row})
      P2 (dge 3 3 [0 0 1
                   0 1 0
                   1 0 0] {:layout :row})]
  (mm P1 (mm A P2))
  #_(mm P A))

;; 12
(let [P (dge 3 3 [0 1 0
                  0 0 1
                  1 0 0] {:layout :row})
      x (dv [1 2 3])
      y (dv [1 4 2])
      ]
  #_(= (dot x y)
       (dot (mv P x) (mv P y)))
  (= (dot (mv P x) y)
     (dot x (mv P y)))
  )

;; 13a
(let [P (dge 3 3 [0 0 1
                  1 0 0
                  0 1 0] {:layout :row})]
  (-> P (mm P) (mm P)))

;; 13b
(let [P (dge 4 4 [1 0 0 0
                  0 0 0 1
                  0 1 0 0
                  0 0 1 0] {:layout :row})]
  (-> P (mm P) (mm P) (mm P)))

;; 16
(let [A (dge 3 3 [1 2 3
                  2 1 3
                  3 3 1] {:layout :row})
      B (dge 3 3 [1 -1 -1
                  -1 1 -1
                  -1 -1 1] {:layout :row})]
  (prn (mm A B))
  (prn (mm B (mm A B)))
  (prn (mm A (mm B (mm A B)))))

;; a true;
(let [Sa (dge 2 2 [1 1
                   1 1] {:layout :row})
      Sb (dge 2 2 [0 1
                   1 1] {:layout :row})
      Sc (dge 2 2 [-1 1
                   2 1] {:layout :row})
      ])

;; 19
(let [A  (dge 3 2 [1 2
                   3 4
                   5 6] {:layout :row})
      At (dge 2 3 [1 2
                   3 4
                   5 6])
      S  (dge 2 2 [2 2 2 2] {:layout :row})]
  (mm At (mm A S)))

;; 26

(let [A  (dge 3 3 [1 3 0
                   3 11 4
                   0 4 9] {:layout :row})
      L  (dge 3 3 [1 0 0
                   3 1 0
                   0 2 1] {:layout :row})
      Lt (dge 3 3 [1 3 0
                   0 1 2
                   0 0 1] {:layout :row})
      D  (dge 3 3 [1 0 0
                   0 2 0
                   0 0 1] {:layout :row})]
  (= A (mm L (mm D Lt))))

;; 37
(let [P (dge 5 5 [0 1 0 0 0
                  1 0 0 0 0
                  0 0 0 1 0
                  0 0 0 0 1
                  0 0 1 0 0] {:layout :row})]
  (-> P (mm P) (mm P) (mm P) (mm P) (mm P)))


(let [P2 (dge 3 3 [1 0 0
                   0 0 1
                   0 1 0] {:layout :row})
      P3 (dge 3 3 [0 0 1
                   1 0 0
                   0 1 0] {:layout :row})]
  (= (-> P2 (mm P2))
     (-> P2 (mm P2) (mm P2) (mm P2))))

(let [p (dge 4 4 [0 1 0 0
                  1 0 0 0
                  0 0 0 1
                  0 0 1 0] {:layout :row})]
  (-> p (mm p) (mm p) (mm p)))
