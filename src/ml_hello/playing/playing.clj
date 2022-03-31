(ns ml-hello.playing.playing
  (:require
    [clojure.string :as str]))

`(map even? [1 2 3])

(class 0.000000000000000000000000000000000091M)

(class (+ 0.1M 0.1M 0.1M 0.1M 0.1M 0.1M 0.1M 0.1M 0.1M 0.1M))

(assoc [1 2 3] 3 4)
(replace {1 0, 2 0} [1 1 2])

(def matrix [[1 2 3]
             [4 5 6]
             [7 8 9]])

(defn neighbors
  ([size yx]
   (neighbors [[-1 0] [1 0] [0 -1] [0 1]]
              size
              yx))
  ([deltas size yx]
   (filter (fn [new-yx]
             (every? #(< -1 % size) new-yx))
           (map #(vec (map + yx %))
                deltas))))

(neighbors 3 [1 1])

;; stacks
(def tmp-stack [1 2 3])
(peek tmp-stack)                                            ;; (last v) is linear, (peek v) is 1
(peek (pop (conj tmp-stack 4)))                             ;; conj, pop is better than assoc, dissoc

(counted? "abc")

;; queues
(defmethod print-method PersistentQueue
  [q, w]
  (print-method '<- w)
  (print-method (seq q) w)
  (print-method '-< w))

(def q (conj PersistentQueue/EMPTY :a :b :c))

;; sets

(some #{:b} [:a :b :c])

(def tmp-sorted-set (sorted-set :b :c :d :a))
(conj tmp-sorted-set :e)

(require 'clojure.set)

(clojure.set/intersection #{:a :b} #{:c :b})
(clojure.set/union #{:a :b} #{:c :b})
(clojure.set/difference #{:a :b} #{:c :b})

;; map
(apply hash-map [:a 1 :b 2])
(hash-map :a 1 :b 2)

(take 5 (range))

(defn index [col]
  (cond
    (map? col) (seq col)
    (set? col) (map vector col col)
    :else (map vector (range) col)))

(defn pos [pred col]
  (for [[i v] (index col) :when (pred v)] i))

(pos even? [2 3 4 5])
(pos #{3} [2 3 4 5])

;; lazy

(def very-lazy (-> #(do (print \.) (inc %) 1)))

(take 3 (iterate inc 1))

(def very-lazy
  (-> (iterate #(do (print \.) (inc %)) 1)
      rest rest rest))

(def less-lazy
  (-> (iterate #(do (print \.) (inc %)) 1)
      next next next))

(defn rand-ints [n]
  (take n (repeatedly #(rand-int n))))

(defn sort-parts [work]
  (lazy-seq
    (loop [[part & parts] work]
      (if-let [[pivot & xs] (seq part)]
        (recur (list*
                 (filter #(< % pivot) xs)
                 pivot
                 (remove #(< % pivot) xs)
                 parts))
        (when-let [[x & parts] parts]
          (cons x (sort-parts parts)))))))

;; function composition

(map #(-> % name (.toLowerCase) keyword) '(a B C))
(map (comp keyword #(.toLowerCase %) name) '(a B C))

((partial print 1) 2)

(defn times-n [n]
  (fn [y] (* y n)))

((times-n 4) 2)

;; A*
(def world
  [[1 1 1 1 1]
   [99 99 99 99 1]
   [1 1 1 1 1]
   [1 99 99 99 99]
   [1 1 1 1 1]])

(defn neighbors
  ([size yx]
   (neighbors [[-1 0] [1 0] [0 -1] [0 1]]
              size
              yx))
  ([deltas size yx]
   (filter (fn [new-yx]
             (every? #(< -1 % size) new-yx))
           (map #(vec (map + yx %))
                deltas))))

(defn estimate-cost [step-cost-est size y x]
  (* step-cost-est
     (- (+ size size) y x 2)))

(defn path-cost [node-cost cheapest-nbr]
  (+ node-cost
     (or (:cost cheapest-nbr) 0)))

(defn total-cost
  [newcost step-cost-est size y x]
  (+ newcost
     (estimate-cost step-cost-est size y x)))

(defn min-by [f coll]
  (when (seq coll)
    (reduce (fn [min other]
              (if (> (f min) (f other))
                other
                min))
            coll)))

(defn astar [start-yx step-est cell-costs]
  (let [size (count cell-costs)]
    (loop [steps 0
           routes (vec (repeat size (vec (repeat size nil))))
           work-todo (sorted-set [0 start-yx])]
      (if (empty? work-todo)
        [(peek (peek routes)) :steps steps]
        (let [[_ yx :as work-item] (first work-todo)
              rest-work-todo (disj work-todo work-item)
              nbr-yxs (neighbors size yx)
              cheapest-nbr (min-by :cost
                                   (keep #(get-in routes %)
                                         nbr-yxs))
              newcost (path-cost (get-in cell-costs yx)
                                 cheapest-nbr)
              oldcost (:cost (get-in routes yx))]
          (if (and oldcost (>= newcost oldcost))
            (recur (inc steps) routes rest-work-todo)
            (recur (inc steps)
                   (assoc-in routes yx
                             {:cost newcost
                              :yxs (conj (:yxs cheapest-nbr [])
                                         yx)})
                   (into rest-work-todo
                         (map (fn [w]
                                (let [[y x] w]
                                  [(total-cost newcost step-est size y x) w]))
                              nbr-yxs)))))))))



(clojure.pprint/pprint
  (astar [0 0] 90 world))


(defprotocol FIXO
  (fixo-push [fixo value])
  (fixo-pop [fixo])
  (fixo-peek [fixo]))

(extend-type clojure.lang.IPersistentVector
  FIXO
  (fixo-pop [vector] (pop vector))
  (fixo-peek [vector] (peek vector))
  (fixo-push [vector value]
    (conj vector value)))

;; mixin

(defprotocol StringOps
  (rev [s])
  (upp [s]))

(extend String StringOps
  {:rev clojure.string/reverse
   :upp #(.toUpperCase %)})

;; refs
(def light-phases [:green :yellow :red :yellow-red])

(def next-phase (zipmap light-phases (rest (cycle light-phases))))

(def lights (atom [:green :red]))

(swap! lights #(mapv next-phase %))

































































































































