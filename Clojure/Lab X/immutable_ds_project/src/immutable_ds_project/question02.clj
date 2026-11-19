(println "===== COLLECTION 1 =====")

(def numbers [1 2 3 4 5 6 7 8 9 10])

(println "Original:" numbers)
(println "Map (Square):" (map #(* % %) numbers))
(println "Filter (Even):" (filter even? numbers))
(println "Reduce (Sum):" (reduce + numbers))
(println "Take 5:" (take 5 numbers))

(println "Threading:"
         (->> numbers
              (filter even?)
              (map #(* % %))
              (take 3)))

(println "\n===== COLLECTION 2 =====")

(def numbers2 [12 15 20 25 30 35])

(println "Original:" numbers2)
(println "Map (Double):" (map #(* 2 %) numbers2))
(println "Filter (Greater than 20):" (filter #(> % 20) numbers2))
(println "Reduce (Maximum):" (reduce max numbers2))
(println "Take 3:" (take 3 numbers2))

(println "Threading:"
         (->> numbers2
              (filter #(> % 20))
              (map #(* 2 %))
              (take 2)))