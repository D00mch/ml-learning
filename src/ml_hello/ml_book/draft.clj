(ns ml-hello.ml-book.draft
  (:require [uncomplicate.commons.core :refer [Releaseable with-release let-release]]
            [uncomplicate.fluokitten.core :refer :all]
            [uncomplicate.neanderthal
             [vect-math :as vect-math]
             [math :refer :all]
             [linalg :as la]
             [native :refer :all]
             [core :refer :all]])
  (:import (clojure.lang IFn)))

(with-release [x  (dv 0.3 0.9)
               w1 (dge 4 2 [0.3 0.1 0.9 0.0
                            0.6 2.0 3.7 1.0])
               w2 (dge 1 4 [0.75 0.15 0.22 0.33])
               h1 (dv 4)
               y  (dv 1)]
  (println (mv! w2 (mv! w1 x h1) y)))

;; ACTIVATION FUNCTIONS

;; step function
(defn step! [threshold x]
  (fmap signum (axpy! -1.0 threshold (vect-math/fmax! threshold x x))))

(let [threshold (dv [1 2 3])
      x         (dv [0 2 7])]
  (step! threshold x))

;; relu

(defn relu! [threshold x]
  (axpy! -1.0 threshold (vect-math/fmax! threshold x x)))

;; hyperbolic tangent (tanh)

(with-release [x         (dv 0.3 0.9)
               w1        (dge 4 2 [0.3 0.1 0.9 0.0,
                                   0.6 2.0 3.7 1.0])
               threshold (dv 0.7 0.2 1.1 2)]
  (vect-math/tanh! (axpy! -1.0 threshold (mv w1 x))))

;; sigmoid
(defn sigmoid! [x]
  (vect-math/linear-frac! 0.5 (vect-math/tanh! (scal! 0.5 x)) 0.5))

(with-release [x         (dv 0.3 0.9)
               w1        (dge 4 2 [0.3 0.1 0.9
                                   0.6 2.0 3.7 1.0])
               threshold (dv 0.7 0.2 1.1 2)]
  (sigmoid! (axpy! -1.0 threshold (mv w1 x))))


;; with activation f
(with-release [x     (dv 0.3 0.9)
               w1    (dge 4 2 [0.3 0.1 0.9 0.0
                               0.6 2.0 3.7 1.0])
               w2    (dge 1 4 [0.75 0.15 0.22 0.33])
               bias1 (dv 0.7 0.2 1.1 2)
               bias2 (dv 0.3)
               h1    (dv 4)
               y     (dv 1)]
  (vect-math/tanh! (axpy! -1.0 bias1 (mv! w1 x h1)))
  (println (sigmoid! (axpy! -1.0 bias2 (mv! w2 h1 y)))))

;; LAYER TYPE

(defprotocol Parameters
  (weights [this])
  (bias [this]))

(deftype FullyConnectedIference [w b h activ-fn]
  Releaseable
  (release [_]
    (.release w)
    (.release b)
    (.release h))
  Parameters
  (weights [this] w)
  (bias [this] b)
  IFn
  (invoke [_ x]
    (activ-fn b (mv! w x h)))
  (invoke [_ x ones a]
    (activ-fn (rk! -1.0 b ones (mm! 1.0 w x 0.0 a)))))

(defn fully-connected [active-fn in-dim out-dim]
  (let-release [w    (dge out-dim in-dim)
                bias (dv out-dim)
                h    (dv out-dim)]
    (->FullyConnectedIference w bias h active-fn)))

(defn activ-sigmoid! [bias x]
  (axpy! -1.0 bias x)
  (vect-math/linear-frac! 0.5 (vect-math/tanh! (scal! 0.5 x)) 0.5))

(defn activ-tanh! [bias x]
  (vect-math/tanh! (axpy! -1.0 bias x)))

(with-release [x       (dv 0.3 0.9)
               layer-1 (fully-connected activ-tanh! 2 4)
               layer-2 (fully-connected activ-sigmoid! 4 1)]
  (transfer! [0.3 0.1 0.9 0.0 0.6 2.0 3.7 1.0] (weights layer-1))
  (transfer! [0.7 0.2 1.1 2] (bias layer-1))
  (transfer! [0.75 0.15 0.22 0.33] (weights layer-2))
  (transfer! [0.3] (bias layer-2))
  (println (-> x layer-1 layer-2)))

;; MM INSTEAD OF MV FOR PERFORMANCE

;; todo: remove
(defn activ-sigmoid! [x]
  (vect-math/linear-frac! 0.5 (vect-math/tanh! (scal! 0.5 x)) 0.5))

(let-release [a (dge 4 1)]
  (with-release [x           (dge 2 1 [0.3 0.9])
                 layer-1     (fully-connected sigmoid! 2 4)
                 w1          (dge 4 2 [0.3 0.1 0.9 0.0
                                       0.6 2.0 3.7 1.0])
                 bias-vector (dv 0.7 0.2 1.1 2)
                 ones        (dv [1])]
    (copy! w1 (weights layer-1))
    (copy! bias-vector (bias layer-1))
    (layer-1 x ones a)))
