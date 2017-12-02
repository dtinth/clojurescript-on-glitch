ClojureScript demo on Glitch.com with code reloading
====================================================

This Glitch demonstrates that it’s possible to evaluate ClojureScript inside Glitch.

Each time a request is received, `my_app/core.cljs` is evaluated.
Then, this request is forwarded to `my-app.core/router`, which should be an Express router.


## Known issues

- When there is an error, it is hard to debug right now (no source map).

- Cannot use 3rd party libs on clojars (e.g. garden, reagent, etc. don’t know how to install them in glitch).
