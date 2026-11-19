(ns immutable-ds-project.core
  (:gen-class))

(defn -main [& args]
  (case (first args)
    "1" (load-file "src/immutable_ds_project/question01.clj")
    "2" (load-file "src/immutable_ds_project/question02.clj")
    (println "Use: lein run 1 or lein run 2")))