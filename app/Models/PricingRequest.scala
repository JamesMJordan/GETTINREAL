package Models

import play.api.libs.json._
import play.api.libs.functional.syntax._
import anorm._
import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

import scala.concurrent.Future


case class PricingRequest(
                         Key: Long,
                         DoubleSided: Boolean,
                         Quantity: Int,
                         widthIn: Int,
                         widthFt: Int,
                         heightIn: Int,
                         heightFt: Int){
  val squareFeet = {
    val total = (roundupInches(widthIn) + widthFt) * (roundupInches(heightIn) + heightFt)
    if (total == 0 ) {
      val total = 1
    }
    total.toInt
  }

  val Price: Double = DB.withConnection { implicit c =>
    SQL("SELECT price FROM pricing WHERE id = {Key}").on('Key -> Key) as scalar[Double].single
  }

  val priced: String = {
    val Total = (squareFeet * Price) * Quantity
    Total.toString
  }

  def roundupInches(a: Double) = math.ceil(a / 12).toInt

}




case class DBItem(id: Long, materials: String, qty: Int, price: Double)

object Item {


  val parser = {
    get[Long]("id")~
    get[String]("materials")~
    get[Int]("qty")~
    get[Double]("price") map {
      case id~materials~qty~price => DBItem(id, materials, qty, price)
    }
  }

}









