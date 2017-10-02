(ns inktobot.core
  (:gen-class
    :methods [^:static [handler [] String]])
  (:require [org.httpkit.client :as http]
            [cheshire.core :as json])
  (:import [java.time MonthDay]))

(def webhook (or (System/getenv "PC_WEBHOOK") "http://localhost"))
(def themes
  ["" "swift" "divided" "poison" "underwater" "long" "sword" "shy" "crooked"
   "screech" "gigantic" "run" "shattered" "teeming" "fierce" "mysterious" "fat"
   "graceful" "filthy" "cloud" "deep" "furious" "trail" "juicy" "blind" "ship"
   "squeak" "climb" "fall" "united" "found" "mask"])

(defn day-of-month []
  (.getDayOfMonth (MonthDay/now)))

(defn month-of-year []
  (.getMonthValue (MonthDay/now)))

(defn october? []
  (= (month-of-year) 10))

(defn post-to-slack [message]
  (let [payload (json/encode {:text message})]
    (let [{:keys [status header body error] :as resp}
          @(http/post webhook {:body payload})]
      (if error (str error) body))))

(defn message-for [day]
  (let [theme (get themes day)]
    (str ":sparkles: It's day " day " of Inktober, and today's theme is *" theme "* :sparkles:\n\n"
         "Short of inspiration? <https://unsplash.com/search/photos/" theme "|Try this>.")))

(defn -handler []
  (let [today (day-of-month)]
    (if (october?)
      (post-to-slack
        (message-for today))
      "It's not October.")))

