(ns aoc15.day03
  (:require [clojure.string :as str])
  (:gen-class))

(def file (slurp "resources/input03.txt"))

(defn part1 [file]
  (let [locations (atom [[0 0]])]
    (->> file
         (reduce (fn [[x y] c]
                   (let [newloc (case c
                                  \^ [x (inc y)]
                                  \v [x (dec y)]
                                  \< [(dec x) y]
                                  \> [(inc x) y])]
                     (when (not (.contains @locations newloc))
                       (swap! locations conj newloc))
                     newloc)) [0 0]))
    (count @locations)))

(defn part2 [file]
  (let [locations (atom [[0 0]])
        follow-string (fn [s]
                        (reduce (fn [[x y] c]
                                  (let [newloc (case c
                                                 \^ [x (inc y)]
                                                 \v [x (dec y)]
                                                 \< [(dec x) y]
                                                 \> [(inc x) y])]
                                    (when (not (.contains @locations newloc))
                                      (swap! locations conj newloc))
                                    newloc)) [0 0] s))]
    (follow-string (str/join (take-nth 2 file)))
    (follow-string (str/join (take-nth 2 (rest file))))
    (count @locations)))
