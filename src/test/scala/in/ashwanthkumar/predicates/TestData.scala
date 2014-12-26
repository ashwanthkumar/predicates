package in.ashwanthkumar.predicates

trait TestData {
  case class Model(id: Int, name: String)

  case object Success
  case object Failure

  class ModelValidator {
    def hasId = (model: Model) => model.id > 0
    def hasName = (model: Model) => model.name != null && !model.name.isEmpty

    def all = hasId and hasName
    def any = hasId or hasName

    def validate = (all thenDo Success) orElse (not(all) thenDo Failure)
  }
}
