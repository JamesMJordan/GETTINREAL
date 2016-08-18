package controllers.auth

import Models.{Account, Role}
import Models.Role._
import controllers.ShippingData
import jp.t2v.lab.play2.auth.AuthElement
import jp.t2v.lab.play2.auth.LoginLogout
import play.api.mvc.{Action, Controller}

import scala.concurrent.Future


trait Messages extends Controller with controllers.FormController with LoginLogout with AuthElement with AuthConfigImpl {


  def login = Action { implicit request =>
    Ok(views.html.login(loginForm))
  }

  def authenticate = Action.async { implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => Future.successful(BadRequest(views.html.login(loginForm))),
      user           => gotoLoginSucceeded(Account.get.id)
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
        val id = Models.Account.addNewAccount(registrationInfo.email, registrationInfo.password, registrationInfo.name, Role.NormalUser)
        Ok("/login")
      }
    )

  }

  def banners = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok("/banners")
  }

  def businesscards = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok("/businesscards")
  }

  def checkout = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok(views.html.checkout(shippingForm))
  }

  def index = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok("/index")
  }

  def main = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok("/main")
  }

  def order = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok(views.html.order(ShippingData(Option.empty, Option.empty, Option.empty, Option.empty, Option.empty, Option.empty)))
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

  def submit = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok("/submit")
  }





}
object Messages extends Messages