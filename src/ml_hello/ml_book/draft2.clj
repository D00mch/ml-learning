(ns ml-hello.ml-book.draft2
  (:require [uncomplicate.commons.core :refer [Releaseable with-release let-release]]
            [uncomplicate.fluokitten.core :refer :all]
            [uncomplicate.neanderthal
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

(defn fully-connected [active-fn in-dim out-dim]
  (let-release [w    (dge out-dim in-dim)
                bias (dv out-dim)]
    (->FullyConnectedIference w bias active-fn)))

(defn sigmoid! [x]
  (vect-math/linear-frac! 0.5 (vect-math/tanh! (scal! 0.5 x)) 0.5))


(let-release [temp-a (dv 8)]
  (with-release [x       (dge 2 2 [0.3 0.9,
                                   0.3 0.9])
                 ones    (dv 1 1)
                 layer-1 (fully-connected vect-math/tanh! 2 4)
                 a-1     (view-ge temp-a 4 2)
                 layer-2 (fully-connected sigmoid! 4 1)
                 a-2     (dge 1 2)
                 ]
    (transfer! [0.3 0.1 0.9 0.0 0.6 2.0 3.7 1.0] (weights layer-1))
    (transfer! [0.7 0.2 1.1 2] (bias layer-1))
    (transfer! [0.75 0.15 0.22 0.33] (weights layer-2))
    (transfer! [0.3] (bias layer-2))
    (println (layer-2 (layer-1 x ones a-1) ones a-2))
    ))
