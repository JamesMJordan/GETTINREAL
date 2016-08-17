package controllers

import javax.inject.Inject

import Models.Account
import play.api.data.Forms._
import play.api.data.{Form, _}
import play.api.mvc.Controller

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
                           email: String,
                           password: String,
                           confirmpassword: String,
                           name: String
                         )

  case class Login(
                  email: Option[String],
                  password: Option[String]
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
      "confirmpassword" -> nonEmptyText,
      "name" -> nonEmptyText
    )(Registration.apply)(Registration.unapply)
  }
}