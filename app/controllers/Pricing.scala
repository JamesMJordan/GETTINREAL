package controllers

import play.api.routing.JavaScriptReverseRouter
import play.api.mvc.{Action, Controller}

import scala.math.BigDecimal.RoundingMode

/**
  * Created by James on 8/24/16.
  */
class Pricing extends Controller {

  def javascriptRoutes = Action { implicit request =>
    Ok(
      JavaScriptReverseRouter("jsRoutes")(
        routes.javascript.Pricing.bannerPricing,
        routes.javascript.Pricing.businesscardPricing
      )
    ).as("text/javascript")
  }
  val BCardQuantities = Map(("0",0), ("1",10), ("2",15), ("3",20), ("4",30))

  def addFeet(widthInches: Int, widthFeet: Int, heightInches: Int, heightFeet: Int): Int = {
    val widthIn = roundupInches(widthInches)
    val widthFt = math.ceil(widthFeet)
    val heightIn = roundupInches(heightInches)
    val heightFt = math.ceil(heightFeet)
    val Width = widthIn + widthFt
    val Height = heightIn + heightFt
    val totalFeet = Width * Height
    totalFeet.toInt
  }


  def roundupInches(a: Double): Int = math.ceil(a / 12).toInt



  def bannerPricing(widthInches: Int, widthFeet: Int, heightInches: Int, heightFeet: Int, Quantity: Int) =
    Action { implicit request =>
      val SquareFeet = addFeet(widthInches, widthFeet, heightInches, heightFeet)
      val PRICE_SQFT = 2.75
      val SQFT = (SquareFeet * PRICE_SQFT) * Quantity
      val result = SQFT.toString
      Ok(result)
    }



  def businesscardPricing(Qty: String, DoubleSided: Boolean) = Action { implicit request =>

      var pricing = BCardQuantities(Qty)
      val DSpricing = pricing * 1.25
      var Priced = pricing.toString

      if (DoubleSided) {
        Priced = DSpricing.toString
      }


      Ok(Priced)
  }
}


