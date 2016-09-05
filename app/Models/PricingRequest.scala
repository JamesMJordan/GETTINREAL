package Models

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
                         heightFt: Int){

  val squareFeet: Double = {
    var totalFeet: Double = (roundupInches(widthIn) + widthFt) * (roundupInches(heightIn) + heightFt)
    if (totalFeet == 0){
      totalFeet = 1
    }

    println(totalFeet)
    totalFeet
  }

  val Price: Double = DB.withConnection { implicit c =>
    SQL("SELECT price FROM pricing WHERE id = {Key}").on('Key -> Key) as scalar[Double].single
  }

  val priced = {
    var Total: Double = (squareFeet * Price) * Quantity
    if (DoubleSided) {
      Total = Price * Quantity * 1.25
    }
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









