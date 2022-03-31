(ns ml-hello.playing.udp
  (:refer-clojure :exclude [get]))

(defn beget [this proto]
  (assoc this ::prototype proto))

(defn get [m k]
  (when m
    (if-let [[_ v] (find m k)]
      v
      (recur (::prototype m) k))))

(def put assoc)


(def cat {:likes-dogs true, :ocd-bathing true})
(def morris (beget {:likes-9lives true} cat))
(def post-traumatic-morris (beget {:likes-dogs nil} morris))

(get cat :likes-dogs)
(get morris :likes-dogs)
(get post-traumatic-morris :likes-dogs)


(defmulti compiler :os)
(defmulti home :os)

(defmethod compiler ::unix [m] (get m :c-compiler))
(defmethod compiler ::osx [m] (get m :llvm-compiler))

(defmethod home ::unix [m] (get m :home))
(defmethod home ::bsd [m] "/home")
(derive ::osx ::unix)
(derive ::osx ::bsd)
(prefer-method home ::unix ::bsd)
(prefers home)

(def clone (partial beget {}))
(def unix {:os ::unix, :c-compiler "cc", :home "/home", :dev "/dev"})
(def osx (-> (clone unix)
             (put :os ::osx)
             (put :llvm-compiler "clang")
             (put :home "/Users")))

(compiler osx)
(home osx)

(isa? ::unix ::osx)                                         ; false
(isa? ::osx ::unix)                                         ; true
(parents ::osx)
(ancestors ::osx)
(descendants ::unix)

(defmulti compile-cmd (juxt :os compiler))
(defmethod compile-cmd [::osx "clang"] [m]
  (str "/usr/bin" (get m :c-compiler)))
(defmethod compile-cmd :default [m]
  (str "Unsure where to locate " (get m :c-compiler)))

(compile-cmd osx)

