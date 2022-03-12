(ns ml-hello.linal.chapter5.permutations-cofactors
  (:require [uncomplicate.commons.core :refer [with-release]]
            [uncomplicate.neanderthal
             [math :refer :all]
             [linalg :as la]
             [native :refer :all]
             [core :refer :all]]))

;;2
(let [C (dge 6 6 [1 1 0 0 0 0
                  1 0 1 0 0 0
                  0 1 1 0 0 0
                  0 0 0 1 1 0
                  0 0 0 1 0 1
                  0 0 0 0 1 1] {:layout :row})]
  (la/det (la/trf! C)))

(let [A (dge 3 3 [1 1 0
                  1 0 1
                  0 1 1] {:layout :row})]
  (la/det (la/trf! A)))

(let [I (dge 3 3 [1 0 0
                  0 1 0
                  0 0 1])]
  (la/det (la/trf! I)))

(let [A (dge 3 3 [2 -1 0
                  -1 2 -1
                  0 -1 2] {:layout :row})]
  (la/det (la/trf A)))
