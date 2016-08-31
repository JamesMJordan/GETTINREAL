//package Models
//
///**
//  * Created by James on 8/30/16.
//  */
//abstract class Pricing
//
//case class Measurements (
//                     widthInches: Int,
//                     widthFeet: Int,
//                     heightInches: Int,
//                     heightFeet: Int
//                   ) extends Pricing
//
//case class Quantities (
//                      Quantity: Int,
//                      Doublesided: Boolean
//                      ) extends Pricing
//
//object Pricing {
//
//  val SQFT = (SquareFeet)
//
//  def squareFeet(widthInches: Int, widthFeet: Int, heightInches: Int, heightFeet: Int): Int = {
//    val widthIn = roundupInches(widthInches)
//    val widthFt = math.ceil(widthFeet)
//    val heightIn = roundupInches(heightInches)
//    val heightFt = math.ceil(heightFeet)
//    val Width = widthIn + widthFt
//    val Height = heightIn + heightFt
//    val totalFeet = Width * Height
//    totalFeet.toInt
//  }
//
//  def roundupInches(a: Double): Int = math.ceil(a / 12).toInt
//
//  def bannerPricing(newMeasurements: Seq[Int] => Measurements)  =
//    Action { implicit request =>
//
//      val PRICE_SQFT = 2.75
//      val SQFT = (SquareFeet * PRICE_SQFT) * Quantity
//      val result = SQFT.toString
//      Ok(result)
//    }
//
//  def businesscardPricing(Qty: String, DoubleSided: Boolean): String =  {
//
//    var pricing = BCardQuantities(Qty)
//    val DSpricing = pricing * 1.25
//    var Priced = pricing.toString
//
//    if (DoubleSided) {
//      Priced = DSpricing.toString
//    }
//
//    Priced
//  }
//
//  def coroplastPricing(Qty: Int, DoubleSided: Boolean): String = {
//
//    var pricing = Qty * 90
//    val DSpricing = Qty * 100
//    var Priced = pricing.toString
//    if (DoubleSided) {
//      Priced = DSpricing.toString
//    }
//
//    Priced
//  }
//
//
//}
