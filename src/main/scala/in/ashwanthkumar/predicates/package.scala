package in.ashwanthkumar

package object predicates {

  case class Predicates[T](existing: T => Boolean) {
    def and(another: T => Boolean) = (datum: T) => existing(datum) && another(datum)
    def and[Q](another: Q => Boolean)(tuple: (T, Q)) = {
      val (first, second) = tuple
      existing(first) && another(second)
    }

    def or(another: T => Boolean) = (datum: T) => existing(datum) || another(datum)
    def or[Q](another: Q => Boolean)(tuple: (T, Q)) = {
      val (first, second) = tuple
      existing(first) || another(second)
    }

    def not = (datum: T) => !existing(datum)
    def thenDo[Q](result: Q): PartialFunction[T, Q] = {
      case datum if existing(datum) => result
    }
  }

  def not[T](predicate: T => Boolean) = predicate.not

  implicit def pimpToPredicates[T](predicate: T => Boolean): Predicates[T] = Predicates[T](predicate)
}
