# minimal-scad-clj

A minimal project to get you started with `scad-clj`.

Run the following to start the repl:

```
clj -A:nrepl
```

Now you can connect to `localhost:60606` with your favorite editor.

To just run the build once do:

```
clj -m openscad-demo
```

When using repl, I suggest you open the resulting `resources/out.scad` file in
OpenSCAD and disable it's text editor by checking `View > Hide Editor` in the menu.

That way you can run `start-poor-mans-autoreload` and get something very closely resembling
OpenSCAD repl, where you would evel clojure code and get updated rendering automagically.
