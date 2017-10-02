(defproject inktobot "1.0"
  :description "I post a new word to Slack every day via AWS Lambda"
  :url "http://github.com/philipnrmn/inktobot"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [cheshire "5.8.0"]
                 [http-kit "2.2.0"]]
  :java-source-paths ["src/java"]
  :aot :all)
