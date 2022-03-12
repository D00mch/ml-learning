(ns ml-hello.linal.chapter5.determinants
  (:require [uncomplicate.commons.core :refer [with-release]]
            [uncomplicate.neanderthal
             [math :refer :all]
             [linalg :as la]
             [native :refer :all]
             [core :refer :all]]))

(let [A (dge 2 2 [1 2
                  3 4] {:layout :row})

      B (dge 4 4 [1 2 0 0
                  3 4 0 0
                  0 0 1 2
                  0 0 3 4])
      ]
  (= (la/det (la/trf! B))
    (* 2 (la/det (la/trf! A))))
  )

(let [A (dge 2 2 [1 0, 0 1])]
  (la/det (la/trf! A)))


(let [A   (dge 2 2 [4 1
                    2 3] {:layout :row})
      A-1 (scal 1/10 (dge 2 2 [3 -1
                               -2 4] {:layout :row}))]
  (mm A A-1))

(let [A (dge 3 3 [3 6 -3, 3 8 5, 4 7 -9])]
  (la/det (la/trf A))
  )

;; 31
(defn hilbert-element [i j]
  (/ 1 (+ i j -1)))

(defn hilbert [n]
  (loop [i 0
         j 0
         r []]
    (if (= j n)
      r
      (if (= i n)
        (recur 0 (inc j) r)
        (recur (inc i) j (conj r (hilbert-element (inc i) (inc j))))
        ))))

(doseq [n (range 1 11)]
  (println
    (la/det (la/trf
              (dge n n (hilbert n))))))


