(ns ml-hello.core
  (:require [uncomplicate.commons.core :refer [with-release]]
            [uncomplicate.neanderthal
             [native :refer :all #_[dv dge]]
             [core :refer :all #_[mv mv!]]])
  (:import (java.util Locale)))

(def v1 (dv -1 2 5.2 0))
(def v2 (dv (range 22)))
(def v3 (dv -2 -3 1 0))


; dimension
(dim v1)

(xpy v1 v3)
(scal 2.5 v1)
(axpy! 2.5 v1 v3)

(def v4 (scal -1 v1))
(axpy 1 v1 -1 v3 2 v1)

(let [u (dv 2 5 -3)
      v (dv -4 1 9)
      w (dv 4 0 2)]
  (axpy 2 u -3 v 1 w))

(let [u (dv 1 -2 4)
      v (dv 3 0 2)]
  (dot u v))

(let [v (dv 1 3 5)]
  (scal (/ (nrm2 v)) v))

(let [u (dv 1 0 0)
      v (dv 1 0 1)]
  (/ (dot u v) (nrm2 u) (nrm2 v)))

;; distance
(let [x (dv 4 0 -3 5)
      y (dv 1 -2 3 0)]
  (nrm2 (axpby! 1 x -1 y)))

;; matrix
(def m (dge 2 2 [3 4, 0 0]))

(nrm2 m)

(let [x (dv 2 -1 3)
      y (dv 4 -2 6)]
  (/ (dot x y) (nrm2 x) (nrm2 y)))

;; linear equation
(require '[uncomplicate.neanderthal.linalg :as l])

(let [cs (dge 3 1)]
  (l/sv! (dge 3 3 [1 2 0
                   0 1 -1
                   1 1 2])
         cs)
  cs)

(let [a (dge 4 4 [1 0 0 0
                  2 0 0 0
                  0 1 0 0
                  0 0 1 0])]
  (l/svd a))

;; first nn
(comment

  (def x (dv 0.3 0.9))

  (def w1 (dge 4 2 [0.3 0.6
                    0.1 2.0
                    0.9 3.7
                    0.0 1.0]
               {:layout :row}))
  (def w2 (dge 1 4 [0.75 0.15 0.22 0.33]))
  (def h1 (dv 4))
  (def y (dv 1))

  (mv! w2 (mv! w1 x h1) y)

  )

(comment


  (require '[uncomplicate.neanderthal
             [core :refer :all]
             [native :refer :all]])

  (require '[uncomplicate.commons.core :refer [with-release]])

  (require '[uncomplicate.neanderthal
             [core :refer [asum]]
             [cuda :refer [cuv with-default-engine]]])

  (require '[uncomplicate.clojurecl
             [core :refer [with-platform platforms with-context context with-queue
                           sort-by-cl-version devices with-default-1 command-queue-1
                           set-default-1! release-context!]]])

  (ns dragan.rocks.dlfp.part-2.representing-layers-and-connections
    (:require [uncomplicate.commons.core :refer [with-release]]
              [uncomplicate.neanderthal
               [native :refer [dv dge]]
               [core :refer [mv mv!]]]))

  ;;; We create two matrices...
  (def a (dge 2 3 [1 2 3 4 5 6]))
  (def b (dge 3 2 [1 3 5 7 9 11]))
  ;;; ... and multiply them
  (mm a b)
  )

;(require '[uncomplicate.clojurecuda.core :refer [with-default]])
