package Models

import play.api.libs.json._
import play.api.libs.functional.syntax._
import anorm._


case class PricingRequest(
                         Key: Long,
                         DoubleSided: Boolean,
                         Quantity: Int,
                         widthIn: Int,
                         widthFt: Int,
                         heightIn: Int,
                         heightFt: Int) {

  implicit val pricingReads: Reads[PricingRequest] = (
    (JsPath \ "Key").read[Long] and
      (JsPath \ "DoubleSided").read[Boolean] and
      (JsPath \ "Quantity").read[Int] and
      (JsPath \ "widthIn").read[Int] and
      (JsPath \ "widthFt").read[Int] and
      (JsPath \ "heightIn").read[Int] and
      (JsPath \ "heightFt").read[Int]) (PricingRequest.apply _)

  val Price: String = getPricing(Key)

  val squareFeet = {
    val total = (roundupInches(widthIn) + widthFt) * (roundupInches(heightIn) + heightFt)
    if (total == 0) {
      val total = 1
    }
    total.toInt
  }

  def priced: String = {
    val Total = (squareFeet * Price.toInt) * Quantity
    Total.toString
  }

  def roundupInches(a: Double): Int = math.ceil(a / 12).toInt

  def getPricing(Key: Long): String = { SQL("SELECT price FROM pricing  WHERE id = {Key}").on('id -> Key).toString
  }

}









