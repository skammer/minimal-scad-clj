(ns openscad-demo
  (:require [scad-clj.model :as m]
            [scad-clj.scad :as s]))

(def height 10)
(def width  100)

(m/with-fa 1
  (def surrounding-half-circles
    (m/difference
     (m/cylinder width height)
     (m/cylinder (- width height) (* height 2))
     (m/cube height (* 2 width) (* height 2))))

  (def text
    (m/extrude-linear {:height height}
                      (m/text "cljs"
                              :size (/ width 2)
                              :font "Fira Code"
                              :halign "center"
                              :valign "center")))
  (def base
    (m/translate
     [0 0 (- height)]
     (m/cylinder width height))))

(defn cljs-logo []
    (m/union
     text
     surrounding-half-circles
     base))

(defn spit-scad []
  (spit "resources/out.scad" (s/write-scad (cljs-logo))))

;; Poor man's live reload. Regenerates the file every one second.
;; OpenSCAD does get exponentially slower the more stuff you have going on in
;; your project, so you might have to disable it once you understand what you 
;; are doing and start calling spit-scad directly.

(defn set-interval [callback ms]
  (future (while true (do (Thread/sleep ms) (callback)))))

(defonce scad-job (atom nil))

(defn start-poor-mans-autoreload []
  (reset! scad-job (set-interval spit-scad 1000)))

(defn stop-poor-mans-autoreload []
  (future-cancel @scad-job))

(defn -main []
  (spit-scad))

(comment
 (start-poor-mans-autoreload)
 (spit-scad))
