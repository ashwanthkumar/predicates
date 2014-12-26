[![Build Status](https://snap-ci.com/ashwanthkumar/predicates/branch/master/build_image)](https://snap-ci.com/ashwanthkumar/predicates/branch/master)

# predicates
Wouldn't it be so nice if we can do something like `(validUserName and validPassword and validCaptcha) thenDo Success` where `validUserName`, `validPassword` and `validCaptcha` are functions of type `T => Boolean`. `predicates` helps you do exactly that. It provides simple wrapper methods to pimp your `T => Boolean` functions with `and`, `or` and `not` so that we can write expressive code. 

## Usage
I prefer to use predicates while writing data validators.

```
import in.ashwanthkumar.predicates._
case class Model(id: Int, name: String)

class ModelValidator {
    def hasId = (model: Model) => model.id > 0
    def hasName = (model: Model) => model.name != null && !model.name.isEmpty

    def all = hasId and hasName
    def any = hasId or hasName

    def validate = (all thenDo Success) orElse (not(all) thenDo Failure)
}
```
