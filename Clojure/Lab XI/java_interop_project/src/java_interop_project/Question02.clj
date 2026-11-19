(println "===== ARITHMETIC EXCEPTION =====")

(try
  (println "Result:" (/ 10 0))
  (catch ArithmeticException e
    (println "Caught ArithmeticException:" (ex-message e)))
  (finally
    (println "Arithmetic block finished")))

(println "\n===== NULL POINTER EXCEPTION =====")

(try
  (let [value nil]
    (.length value))
  (catch NullPointerException e
    (println "Caught NullPointerException:" (ex-message e)))
  (finally
    (println "Null block finished")))

(println "\n===== CUSTOM EXCEPTION =====")

(defn check-age [age]
  (when (< age 18)
    (throw (ex-info "Age must be 18 or above"
                    {:age age :type :invalid-age})))
  (println "Age accepted:" age))

(try
  (check-age 16)
  (catch Exception e
    (println "Caught Custom Exception:" (ex-message e))
    (println "Exception Data:" (ex-data e)))
  (finally
    (println "Custom exception block finished")))

(println "\n===== GRACEFUL RECOVERY =====")

(try
  (check-age 20)
  (println "Program continued successfully")
  (catch Exception e
    (println "Error:" (ex-message e)
             "Data:" (ex-data e)))
  (finally
    (println "Recovery block finished")))