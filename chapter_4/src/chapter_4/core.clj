(ns chapter-4.core
  (:gen-class))

(def filename "suspects.csv")

(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity
                  :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

(defn mapify
  "Return a seq of maps like {:name \"Edwar Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
          (reduce (fn [row-map [vamp-key value]]
                    (assoc row-map vamp-key (convert vamp-key value)))
                  {}
                  (map vector vamp-keys unmapped-row)))
        rows))

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

(defn list-names
  [records]
  (map #(:name %) records))

(defn
  [filename]
  (get-results (comp (partial glitter-filter 3) mapify parse slurp) filename))

(defn append
  []
  nil)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (get-results filename)))
