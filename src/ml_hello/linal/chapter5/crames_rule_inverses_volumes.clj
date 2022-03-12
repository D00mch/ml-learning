(ns ml-hello.linal.chapter5.crames-rule-inverses-volumes
  (:require [uncomplicate.commons.core :refer [with-release]]
            [uncomplicate.neanderthal
             [math :refer :all]
             [linalg :as la]
             [native :refer :all]
             [core :refer :all]]))

(defn inv [A] (la/tri (la/trf A)))

(let [a (dv 1 2 3)
      b (dv 4 5 6)
      c (dv -3 6 -3) #_(cross product)]
  (= 0.0 (dot b c) (dot a c)))

(let [A (dge 3 3 [2 6 2,
                  1 4 2,
                  5 9 0] {:layout :row})
      B (dge 3 3 [-9 9 2,
                  5 -5 -1
                  -5.5 6 1] {:layout :row})
      x (dv 2 -1 1)
      ]
  #_(mm A B)
  (mv A x)
  )

(let [A (dge 3 3 [3 1 4, 2 4 6, 1 1 1])]
  (la/det (la/trf A)))

;; 20
(let [H (dge 4 4 [1 1 1 1
                  1 1 -1 -1
                  1 -1 -1 1
                  1 -1 1 -1])]
  (la/det (la/trf! H)))

;; 40

(let [A (dge 5 5 [])])
