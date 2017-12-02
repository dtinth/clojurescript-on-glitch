; Glitch has got proper support for Clojure-style indentation!
(ns my-app.core)

(declare render-root)

; -------------
; Define routes
;
(def express (js/require "express"))
(def router (express.Router.))
(-> router
    (.get "/" (fn [req res]
                (.send res (render-root (my-page))))))

(defonce hit-counter (atom 0))

(defn my-page []
  [:html {:lang "en"}
   [:head
    [:title "Hello from ClojureScript!"]]
   [:body
    [:h1 "Hello from ClojureScript in Glitch"]
    [:p "You can change this file, and it hot-reloads (the hit counter doesn’t reset)!"]
    [:a {:href "https://glitch.com/edit/#!/clojurescript"} "Remix me!"]
    [:p "Hit counter: " (swap! hit-counter inc)]]])

; ---------------------------
; A very simple HTML renderer
;
(declare render-tag)

(def escape-html (js/require "escape-html"))

(defn render-node [node]
  (cond
    (string? node) (escape-html node)
    (vector? node) (render-tag (name (first node))
                               (rest node))
    :otherwise (escape-html (str node))))

(defn render-attrs [attrs]
  (clojure.string/join
    (for [[k v] attrs]
      (str " " (name k) "=\"" (escape-html v) "\""))))

(defn render-tag [name stuffs]
  (let [[attrs children] (if (map? (first stuffs))
                           [(first stuffs) (rest stuffs)]
                           [{} stuffs])]
    (str
      "<" name (render-attrs attrs) ">"
      (apply str (map render-node children))
      "</" name ">")))

(defn render-root [node]
  (str "<!doctype html>" (render-node node)))
