package Models.JSON

import Models.DatabaseServices.ProductDB
import play.api.libs.json.{Json, Reads}
import play.api.libs.functional.syntax._


case class Measurements(widthIn: Int, widthFt: Int, heightIn: Int, heightFt: Int)
case class Price(Key: Int, DoubleSided: Boolean, Quantity: Int)
case class SizePriceCombo(Size: Measurements, Price: Price)

object JSONRequests {

  implicit val MeasurementReads =  Json.reads[Measurements]
  implicit val pricingReads =  Json.reads[Price]
  implicit val comboReads = Json.reads[SizePriceCombo]

  def JsonValidate(Request: String) = {
    val price: Price = Json.parse(Request).validate[Price].get
    val measurements: Measurements = Json.parse(Request).validate[Measurements].get
    val combined: SizePriceCombo = new SizePriceCombo(measurements, price)
    priced(combined)
  }

  def totalWidth(m: Measurements): Int = {
    roundupInches(m.widthIn) + m.widthFt
  }

  def totalHeight(m: Measurements): Int = {
    roundupInches(m.heightIn) + m.heightFt
  }

  def roundupInches(a: Int): Int = math.ceil(a / 12).toInt

  def squareFeet(m: Measurements): Int = {
    var totalFeet = totalWidth(m) * totalHeight(m)
    if (totalFeet == 0) {
      totalFeet = 1
    }
    totalFeet
  }

  def size(m: Measurements): String = {
    println(totalWidth(m) + " " + totalHeight(m)).toString
  }

  def priced(s: SizePriceCombo): String = {
    var price = ProductDB.getPrice(s.Price.Key)
    var Total: Double = squareFeet(s.Size) * price * s.Price.Quantity
    if (s.Price.DoubleSided) {
      Total = price * s.Price.Quantity * 1.25
    }
    Total.toString
  }
}



