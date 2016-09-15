package Models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current




case class Item(Key: Long, DoubleSided: Boolean, Quantity: Int, widthIn: Int, widthFt: Int, heightIn: Int, heightFt: Int)

object Pricing {
    def squareFeet(i: Item): Int = {
      var totalFeet = (roundupInches(i.widthIn) + i.widthFt) * (roundupInches(i.heightIn) + i.heightFt)
    if (totalFeet == 0){
      totalFeet = 1
    }
      totalFeet
  }

  def Price(i: Item): Double = DB.withConnection { implicit c =>
    SQL("SELECT price FROM pricing WHERE id = {Key}").on('Key -> i.Key) as scalar[Double].single
  }

  def priced(i: Item): String = {
    var Total: Double = squareFeet(i) * Price(i) * i.Quantity
    if (i.DoubleSided) {
      Total = Price(i) * i.Quantity * 1.25
    }
    Total.toString
  }

  def roundupInches(a: Double): Int = math.ceil(a / 12).toInt
}










