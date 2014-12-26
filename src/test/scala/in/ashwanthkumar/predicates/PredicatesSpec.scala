package in.ashwanthkumar.predicates

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class PredicatesSpec extends FlatSpec with ShouldMatchers with TestData {
  val validator = new ModelValidator
  "Predicates" should "chain predicates with and clause" in {
    val model = Model(1, "Foo")
    validator.all(model) should be(true)
  }

  it should "chain predicates with or clause" in {
    val idCheck = Model(1, null)
    validator.any(idCheck) should be(true)
    val nameCheck = Model(-1, "Bar")
    validator.any(nameCheck) should be(true)
  }

  it should "return Success if validation passes or Failure when it fails" in {
    val success = Model(2, "FooBar")
    validator.validate(success) should be(Success)

    val failure = Model(-1, null)
    validator.validate(failure) should be(Failure)
  }
}

