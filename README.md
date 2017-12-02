ClojureScript demo with code reloading
======================================

This Glitch demonstrates that it’s possible to evaluate ClojureScript inside Glitch.

Each time a request is received, `my_app/core.cljs` is evaluated.
Then, this request is forwarded to `my-app.core.app`, which should be an Express router.