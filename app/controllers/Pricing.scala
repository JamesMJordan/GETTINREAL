package controllers

import play.api.routing.JavaScriptReverseRouter
import play.api.mvc.{Action, Controller}

/**
  * Created by James on 8/24/16.
  */
class Pricing extends Controller  {

  def javascriptRoutes = Action { implicit request =>
    Ok(
      JavaScriptReverseRouter("jsRoutes")(
        routes.javascript.Pricing.bannerPricing
      )
    ).as("text/javascript")
  }

  def addFeet(widthInches: Int, widthFeet: Int, heightInches: Int, heightFeet: Int): Int = {
    val widthIn = Math.round(widthInches/12 + .42)
    val widthFt = Math.round(widthFeet)
    val heightIn = Math.round(heightInches/12 +.42)
    val heightFt = Math.round(heightFeet)
    val totalFeet = (widthIn + widthFt) * (heightIn + heightFt)
    totalFeet.toInt
  }

  def bannerPricing(widthInches: Int, widthFeet: Int, heightInches: Int, heightFeet: Int, Quantity: Int) =
    Action { implicit request =>
    val SquareFeet = addFeet(widthInches, widthFeet, heightInches, heightFeet)
    val PRICE_SQFT = 2.75
    val SQFT = (SquareFeet * PRICE_SQFT) * Quantity
    val result = SQFT.toString
    Ok(result)
  }


}
