package Models

import play.api.libs.json._
import play.api.libs.functional.syntax._
import anorm._
import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current


case class PricingRequest(
                         Key: Long,
                         DoubleSided: Boolean,
                         Quantity: Int,
                         widthIn: Int,
                         widthFt: Int,
                         heightIn: Int,
                         heightFt: Int) {

  val Price: String = Item.getPricing(Key)

  val squareFeet = {
    val total = (roundupInches(widthIn) + widthFt) * (roundupInches(heightIn) + heightFt)
    if (total == 0) {
      val total = 1
    }
    total.toInt
  }

  val priced: String = {
    val Total = (squareFeet * Price.toInt) * Quantity
    Total.toString
  }

  def roundupInches(a: Double): Int = math.ceil(a / 12).toInt



}

case class Item (id: Long, materials: String, qty: Int, price: Double)

object PricingRequest {
  implicit val pricingReads: Reads[PricingRequest] = (
    (JsPath \ "Key").read[Long] and
      (JsPath \ "DoubleSided").read[Boolean] and
      (JsPath \ "Quantity").read[Int] and
      (JsPath \ "widthIn").read[Int] and
      (JsPath \ "widthFt").read[Int] and
      (JsPath \ "heightIn").read[Int] and
      (JsPath \ "heightFt").read[Int]) (PricingRequest.apply _)
}

object Item {

  val parser = {
    get[Long]("id")~
    get[String]("materials")~
    get[Int]("qty")~
    get[Double]("price") map {
      case id~materials~qty~price => Item(id, materials, qty, price)
    }
  }


  def getItem(Id: Long): Item = DB.withConnection { implicit c =>
    SQL("SELECT price FROM pricing WHERE id = {Key}").on('id -> Id).using(parser).single()
  }

  def getPricing(Key: Long): String = { SQL("SELECT price FROM pricing  WHERE id = {Key}").on('id -> Key).toString
  }

}









