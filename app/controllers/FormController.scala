package controllers

import javax.inject.Inject

import play.api.data._
import play.api.data.Forms._
import play.api.mvc.Controller

/**
  * Created by James Jordan on 7/18/2016.
  */

class FormController @Inject() extends Controller {

  case class ShippingData(name: String, address1: String, address2: String) {

    val shippingForm = Form(
      mapping(
        "name" -> text,
        "address1" -> text,
        "address2" -> text
      )(ShippingData.apply)(ShippingData.unapply)
        println shippingForm
    )
  }
}