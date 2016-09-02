package controllers

import Models.{PricingRequest, Role}
import Models.Role._
import jp.t2v.lab.play2.auth.AuthElement
import jp.t2v.lab.play2.auth.LoginLogout
import play.api.mvc.{Action, Controller}
import play.api.routing.JavaScriptReverseRouter
import play.api.libs.json._
import play.api.libs.functional.syntax._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class Messages extends Controller with FormController with LoginLogout with AuthElement with AuthConfigImpl {

  def javascriptRoutes = Action { implicit request =>
    Ok(
      JavaScriptReverseRouter("jsRoutes")(
        routes.javascript.Messages.pricing
      )
    ).as("text/javascript")
  }


  def login = Action { implicit request =>
    Ok(views.html.login(loginForm))
  }

  def authenticate = Action.async { implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => Future.successful(BadRequest(views.html.login(formWithErrors))),
      user           => gotoLoginSucceeded(user.get.id)
    )
  }

  def registration = Action { implicit request =>
    Ok(views.html.registration(registrationForm))
  }

  def newAccount = Action { implicit request =>
    registrationForm.bindFromRequest.fold(
      formWithErrors => {
        println(formWithErrors)
        BadRequest(views.html.registration(formWithErrors))
      },
      registrationInfo => {
        println(registrationInfo)
        val id = Models.Account.addNewAccount( registrationInfo.email, registrationInfo.password, registrationInfo.name, Role.NormalUser)
        Ok(views.html.login(loginForm))
      }
    )

  }

  def banners = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok(views.html.banners("wet"))
  }

  def businesscards = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok(views.html.businesscards("bit"))
  }

  def checkout = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok(views.html.checkout(shippingForm))
  }

  def coroplast = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok(views.html.coroplast("we"))
  }

  def saveCheckout = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    shippingForm.bindFromRequest.fold(
      formWithErrors => {
        println(formWithErrors)
        BadRequest(views.html.order(ShippingData(Option.empty, Option.empty, Option.empty, Option.empty, Option.empty, Option.empty)))
      },
      shippingInfo => {
        println(shippingInfo)
        val id = Models.Order.createOrder(
          shippingInfo.name, shippingInfo.address1, shippingInfo.address2, shippingInfo.city, shippingInfo.state, shippingInfo.zipcode)
        Ok(views.html.order(shippingInfo))
      }
    )
  }

  def index = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok(views.html.index("/index"))
  }

  def order = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok(views.html.order(ShippingData(Option.empty, Option.empty, Option.empty, Option.empty, Option.empty, Option.empty)))
  }

  def pricing(PricingRequest: String) = Action { implicit request =>
    implicit val pricingReads: Reads[PricingRequest] = (
      (JsPath \ "Key").read[Long] and
        (JsPath \ "DoubleSided").read[Boolean] and
        (JsPath \ "Quantity").read[Int] and
        (JsPath \ "widthIn").read[Int] and
        (JsPath \ "widthFt").read[Int] and
        (JsPath \ "heightIn").read[Int] and
        (JsPath \ "heightFt").read[Int]) (PricingRequest.apply)

    println(Json.parse(PricingRequest))
    val lol = Json.parse(PricingRequest).validate[PricingRequest]
    println(lol)
    val priced = priced(lol)
    Ok(priced)
  }

  def submit = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok(views.html.submit("/submit"))
  }

}

