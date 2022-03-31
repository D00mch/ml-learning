(ns ml-hello.ml-book.draft3
  (:require [uncomplicate.commons.core :refer [Releaseable with-release let-release]]
            [uncomplicate.fluokitten.core :refer :all]
            [uncomplicate.clojurecuda.core :as cuda :refer
             [current-context default-stream synchronize!]]
            [uncomplicate.neanderthal
             [cuda :refer [cuda-float]]
             [vect-math :as vect-math]
             [math :refer :all]
             [linalg :as la]
             [native :refer :all]
             [core :refer :all]])
  (:import (clojure.lang IFn)))

(defprotocol Parameters
  (weights [this])
  (bias [this]))

(deftype FullyConnectedIference [w b activ-fn]
  Releaseable
  (release [_]
    (.release w)
    (.release b))
  Parameters
  (weights [this] w)
  (bias [this] b)
  IFn
  (invoke [_ x ones a]
    (activ-fn (rk! -1.0 b ones (mm! 1.0 w x 0.0 a)))))

(defn fully-connected [factory active-fn in-dim out-dim]
  (let-release [w    (ge factory out-dim in-dim)
                bias (vctr factory out-dim)]
    (->FullyConnectedIference w bias active-fn)))

(defn sigmoid! [x]
  (vect-math/linear-frac! 0.5 (vect-math/tanh! (scal! 0.5 x)) 0.5))


(defn this-particular-network [factory]
  (with-release [x (ge factory 2 2 [0.3 0.9 0.3 0.9])
                 ones (vctr factory 1 1)
                 layer-1 (fully-connected factory vect-math/tanh! 2 4)
                 a-1 (ge factory 4 2)
                 layer-2 (fully-connected factory sigmoid! 4 1)
                 a-2 (ge factory 1 2)]
    (transfer! [0.3 0.1 0.9 0.0 0.6 2.0 3.7 1.0] (weights layer-1))
    (transfer! [0.7 0.2 1.1 2] (bias layer-1))
    (transfer! [0.75 0.15 0.22 0.33] (weights layer-2))
    (transfer! [0.3] (bias layer-2))
    (transfer (layer-2 (layer-1 x ones a-1) ones a-2))))

(cuda/with-default
  (with-release [cuda-factory (cuda-float (current-context) default-stream)]
    (this-particular-network cuda-factory)))

(defn sigmoid-prim!
  ([x!]
   (let-release [x-raw (raw x!)]
     (sigmoid-prim! x! x-raw)))
  ([x! prim!]
   (let [x (sigmoid! x!)]
     (vect-math/mul! (vect-math/linear-frac! -1.0 x 1.0 prim!) x))))
