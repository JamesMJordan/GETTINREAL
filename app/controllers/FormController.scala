package controllers

import javax.inject.Inject

import play.api.data.Forms._
import play.api.data._
import play.api.mvc.{Action, Controller}
import views._

/**
  * Created by James Jordan on 7/18/2016.
  */


class FormController @Inject() extends Controller {

  case class ShippingData(
                           name: Option[String],
                           address1: Option[String],
                           address2: Option[String],
                           city: Option[String],
                           state: Option[String],
                           zipcode: Option[String]
                         )

  case class Registration(
                           email: Option[String],
                           password: Option[String],
                           name: Option[String]
                         )


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

  def saveCheckout = Action { implicit request =>
  shippingForm.bindFromRequest.fold(
    formWithErrors => {
      println(formWithErrors)
      BadRequest(html.checkout(formWithErrors))
    },
    shippingInfo => {
      println(shippingInfo)
      val id = Models.Order.createOrder(shippingInfo.name, shippingInfo.address1, shippingInfo.address2, shippingInfo.city, shippingInfo.state, shippingInfo.zipcode)
      Ok(html.order(shippingInfo))
    }
  )}

}