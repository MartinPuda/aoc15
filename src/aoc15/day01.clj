(ns aoc15.day01
  (:gen-class))

(def file (slurp "resources/input01.txt"))

(defn part1 [file]
  (let [freq (frequencies file)]
    (- (freq \() (freq \)))))

(defn part2 [file]
    (->> file
         (map-indexed #(vector %1 %2))
         (reduce (fn [floor [num c]]
                   (let [nf (if (= c \() (inc floor) (dec floor))]
                     (if (= nf -1) (reduced (inc num)) nf))) 0)))
