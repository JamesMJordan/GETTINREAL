package controllers

import Models.Account
import Models.JSON.{Measurements, Price}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.Controller

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

case class Registration(
                         email: String,
                         password: String,
                         name: String
                       )

case class Login(
                  email: String,
                  password: String
                  )

case class Item(
                 User: String,
                 File: String,
                 Quantity: Int,
                 Size: Measurements,
                 Price: Price
               )

trait FormController extends Controller {

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

  val loginForm = Form {
    mapping(
      "email" -> email,
      "password" -> text
    )(Account.authenticate)(_.map(u => (u.email, ""))).verifying("Invalid email or password", result => result.isDefined)
  }

  val registrationForm = Form {

    mapping(
      "email" -> nonEmptyText,
      "password" -> nonEmptyText,
      "name" -> nonEmptyText
    )(Registration.apply)(Registration.unapply)
  }

  val sizeForm = Form {
    mapping(
      "widthIn" -> number,
      "widthFt" -> number,
      "heighIn" -> number,
      "heightFt" -> number
    )(Measurements.apply)(Measurements.unapply)
  }

  val priceForm = Form {
    mapping(
      "Key" -> number,
      "DoubleSided" -> boolean,
      "Quantity" -> number
    )(Price.apply)(Price.unapply)
  }

  val itemForm = Form {
    mapping(
      "User" -> text,
      "File" -> text,
      "Quantity" -> number,
      "Size" -> sizeForm.mapping,
      "Price" -> priceForm.mapping
    )(Item.apply)(Item.unapply)
  }
}

