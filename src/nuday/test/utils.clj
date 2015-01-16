(ns nuday.test.utils
  (:require [clojure.test.check.generators :as gen]))

(def scalar-generators
  [gen/boolean gen/byte gen/bytes gen/char gen/int gen/keyword gen/keyword-ns gen/ratio gen/string])

(defn all-but [excluded]
  (->> (concat scalar-generators
               (when-not (= gen/map excluded)
                 [(gen/map (gen/one-of scalar-generators) (gen/one-of scalar-generators))])
               (when-not (= gen/vector excluded)
                 [(gen/vector (gen/one-of scalar-generators))]))
       (filter #(not= excluded %))))

(def any-generator
  (all-but nil))
