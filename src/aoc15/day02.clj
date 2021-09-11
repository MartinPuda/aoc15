(ns aoc15.day02
  (:require [clojure.string :as str])
  (:gen-class))

(def file (->> (-> (slurp "resources/input02.txt")
                   (str/replace #"\n" " ")
                   (str/split #" "))
               (map (fn [s] (->> (str/split s #"x")
                                 (map read-string))))))

(defn part1 [file]
  (->> file
       (map (fn [[l w h]]
         (+ (* 2 l w)
            (* 2 w h)
            (* 2 h l)
            (apply * (take 2 (sort [l w h]))))))
       (apply +)))

(defn part2 [file]
  (->> file
       (map (fn [triplet]
              (+ (* 2 (apply + (take 2 (sort triplet))))
                 (apply * triplet))))
       (apply +)))
