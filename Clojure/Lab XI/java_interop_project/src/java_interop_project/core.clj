(ns java-interop-project.core
  (:gen-class))

(defn -main [& args]
  (case (first args)
    "1" (load-file "src/java_interop_project/question01.clj")
    "2" (load-file "src/java_interop_project/question02.clj")
    (println "Use: lein run 1 or lein run 2")))