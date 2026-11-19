(println "===== JAVA STATIC METHODS =====")

(println "Absolute Value:" (Math/abs -25))
(println "Square Root:" (Math/sqrt 64))
(println "Current Time:" (System/currentTimeMillis))

(println "\n===== JAVA ARRAYLIST =====")

(def java-list (java.util.ArrayList.))
(.add java-list "Apple")
(.add java-list "Banana")
(.add java-list "Mango")

(println "ArrayList:" java-list)
(println "Size:" (.size java-list))
(println "Element at index 1:" (.get java-list 1))

(println "\n===== JAVA HASHMAP =====")

(def java-map (java.util.HashMap.))
(.put java-map "Name" "Badal")
(.put java-map "Course" "CSE")
(.put java-map "City" "Delhi")

(println "HashMap:" java-map)
(println "Name:" (.get java-map "Name"))
(println "Size:" (.size java-map))

(println "\n===== COLLECTION CONVERSION =====")

(def clojure-vector [10 20 30 40])
(def java-arraylist (java.util.ArrayList. clojure-vector))

(println "Clojure Vector:" clojure-vector)
(println "Java ArrayList:" java-arraylist)

(def converted-vector (vec java-arraylist))
(println "Java to Clojure Vector:" converted-vector)

(def clojure-map {:name "Badal" :age 21})
(def java-hashmap (java.util.HashMap. clojure-map))

(println "Clojure Map:" clojure-map)
(println "Java HashMap:" java-hashmap)

(def converted-clojure-map (into {} java-hashmap))
(println "Java to Clojure Map:" converted-clojure-map)