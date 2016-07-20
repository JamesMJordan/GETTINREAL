package controllers

import javax.inject.Inject

import play.api.data._
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}

/**
  * Created by James Jordan on 7/18/2016.
  */
case class ShippingData(name: String, address1: String, address2: String)

class FormController @Inject() extends Controller {

  val shippingForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "address1" -> nonEmptyText,
      "address2" -> nonEmptyText
    )(ShippingData.apply)(ShippingData.unapply)
  )

  def checkout = Action { implicit request =>
    Ok(views.html.checkout(shippingForm))
  }

  def saveCheckout = Action {implicit request =>
  shippingForm.bindFromRequest.fold(
    formWithErrors => {
      BadRequest(views.html.checkout(formWithErrors))
    },
    shippingInfo => {
      println(shippingInfo)
      Redirect(routes.FormController.checkout)
    }
  )}



}