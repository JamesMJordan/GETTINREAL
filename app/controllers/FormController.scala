package controllers

import javax.inject.Inject
import play.api.data._
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import models.Order._

/**
  * Created by James Jordan on 7/18/2016.
  */

case class ShippingData(
                         name: Option[String],
                         address1: Option[String],
                         address2: Option[String],
                         city: Option[String],
                         state: Option[String],
                         zipcode: Option[String]
                       )

class FormController @Inject() extends Controller {



  val shippingForm  = Form(

    mapping(
      "name" -> optional(nonEmptyText),
      "address1" -> optional(nonEmptyText),
      "address2" -> optional(nonEmptyText),
      "city" -> optional(nonEmptyText),
      "state" -> optional(nonEmptyText),
      "zipcode" -> optional(nonEmptyText)
    )(ShippingData.apply)(ShippingData.unapply)
  )

  def saveCheckout = Action {implicit request =>
  shippingForm.bindFromRequest.fold(
    formWithErrors => {
      println(formWithErrors)
      BadRequest(views.html.checkout(formWithErrors))
    },
    shippingInfo => {
      println(shippingInfo)
      val id = models.Order.create(shippingInfo.name, shippingInfo.address1, shippingInfo.address2, shippingInfo.city, shippingInfo.state, shippingInfo.zipcode)
      Ok(views.html.order(shippingInfo))
    }
  )}

  def checkout = Action {
    Ok(views.html.checkout(shippingForm))
  }

  def order = Action {
    Ok(views.html.order(ShippingData(Option.empty, Option.empty, Option.empty, Option.empty, Option.empty, Option.empty)))

  }

  def submit = Action {
    Ok(views.html.submit("dongs"))
  }

}