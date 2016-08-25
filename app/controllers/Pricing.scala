package controllers

import play.api.mvc.{Action, Controller}
import play.api.routing.JavaScriptReverseRouter

/**
  * Created by James on 8/24/16.
  */
class Pricing extends Controller  {

  def javascriptRoutes = Action { implicit request =>
    Ok(
      JavaScriptReverseRouter("jsRoutes")(
        routes.javascript.Pricing.addFeet,
        routes.javascript.Pricing.bannerPricing
      )
    ).as("text/javascript")
  }

  def addFeet(Feet: Int, Inches: Int): Int = {
    val inches = Math.round((Inches/12) + .42)
    val feet = Math.round(Feet)
    val totalFeet = inches + feet
    totalFeet.toInt
  }

  def bannerPricing(SquareFeet: Int, Quantity: Int): Unit =  {

    val TAX = 1.08125
    val PRICE_SQFT = 2.75
    val SQFT = (((SquareFeet * PRICE_SQFT) * Quantity) * TAX) + 15

  }


}
