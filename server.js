// Load clojurescript evaluator
const cljs = require('clojurescript')

// Register the clojurescript filetype...
require('clojurescript/lib/register')

// A require function that does not cache the module...
const live = require('uncache')(require)

// Create HTTP server
const app = require('express')()
app.use(function (req, res, next) {
  live('./my_app/core.cljs')
  const router = cljs.eval('my-app.core.router', cljs.defaultContext)
  router(req, res, next)
})

// Listen!
app.listen(process.env.PORT, () => { console.log('Server started!') })
