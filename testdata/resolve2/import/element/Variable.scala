object O {
  var v = ""
}

import O.v

println(/* file: this, offset: 17 */ v.getClass)
println(classOf[/* resolved: false  */ v])