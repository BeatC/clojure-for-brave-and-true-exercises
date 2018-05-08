(ns com.braveclojure.chapter-3)

; Use the str, vector, list, hash-map, and hash-set functions.
(str "Hello" " " "world")
(vector 1 2 3 4 5)
(list 1 2 3 4 5)
(= (hash-map :first 1 :second 2 :third 3) {
    :first 1
    :second 2
    :third 3 })
(= (hash-set :unique :values) #{ :unique :values })

; Write a function that takes a number and adds 100 to it.
(defn add-100 [x]
  (+ 100 x))

(= (add-100 1) 101)

; Write a function, dec-maker, that works exactly
; like the function inc-maker except with subtraction.
(defn dec-maker [decrement-step]
  #(- % decrement-step))

(def dec-9 (dec-maker 9))
(= (dec-9 10) 1)

; Write a function, mapset, that works
; like map except the return value is a set.
(defn mapset [func coll]
  (reduce #((partial conj %1) (func %2)) #{} coll))

; Create a function that’s similar to symmetrize-body-parts
; except that it has to work with weird space aliens with
; radial symmetry. Instead of two eyes, arms, legs, and so
; on, they have five.
; Create a function that generalizes symmetrize-body-parts
; and the function you created in Exercise 5. The new function
; should take a collection of body parts and the number of
; matching body parts to add.
(def asym-body-parts [ {:name "head" :size 3}
                        {:name "1-eye" :size 1}
                        {:name "1-ear" :size 1}
                        {:name "mouth" :size 1}
                        {:name "nose" :size 1}
                        {:name "neck" :size 2}
                        {:name "1-shoulder" :size 3}
                        {:name "1-upper-arm" :size 3}
                        {:name "chest" :size 10}
                        {:name "back" :size 10}
                        {:name "1-forearm" :size 3}
                        {:name "abdomen" :size 6}
                        {:name "1-kidney" :size 1}
                        {:name "1-hand" :size 2}
                        {:name "1-knee" :size 2}
                        {:name "1-thigh" :size 4}
                        {:name "1-lower-leg" :size 3}
                        {:name "1-achilles" :size 1}
                        {:name "1-foot" :size 2} ])

(defn matching-part [replace-text part]
  {:name (clojure.string/replace (:name part) #"^1" (str replace-text))
   :size (:size part)})

(defn get-prefixes [num]
    (map inc (take num (range))))

(defn symmetrize-body-parts [asym-body-parts num-of-prefixes]
    (reduce (fn [final-body-parts part]
        (into final-body-parts
            (set
                (map #(matching-part % part)
                    (get-prefixes num-of-prefixes)))))
        []
        asym-body-parts))