package controllers.auth

import Models.Role._
import jp.t2v.lab.play2.auth.AuthElement
import jp.t2v.lab.play2.auth.LoginLogout
import play.api.mvc.{Action, Controller}
import play.twirl.api.Html
import views._

trait Messages extends Controller with LoginLogout with controllers.FormController with AuthElement with AuthConfigImpl {


  def login = Action { implicit request =>
    Ok(html.login(loginForm))
  }

  def registration = Action { implicit request =>
    Ok(html.registration(registrationForm))
  }

  def newAccount = Action { implicit request =>
    registrationForm.bindFromRequest.fold(
      formWithErrors => {
        println(formWithErrors)
        BadRequest(html.registration(formWithErrors))
      },
      registrationInfo => {
        println(account)
        val id = Models.Account.create(registrationInfo)
        Ok(hmtl.login(loginForm))
      }
    )

  }

  def banners = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok(html.banners("BANNERS"))
  }

  def businesscards = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok(html.businesscards("Butts, the Revenge"))
  }

  def checkout = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok(html.checkout(shippingForm))
  }

  def index = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok(html.index("Butts."))
  }

  def main = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok(html.main("title"))
  }

  def order = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok(views.html.order(ShippingData(Option.empty, Option.empty, Option.empty, Option.empty, Option.empty, Option.empty)))
  }

  def saveCheckout = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    shippingForm.bindFromRequest.fold(
      formWithErrors => {
        println(formWithErrors)
        BadRequest(html.checkout(formWithErrors))
      },
      shippingInfo => {
        println(shippingInfo)
        val id = Models.Order.createOrder(
          shippingInfo.name, shippingInfo.address1, shippingInfo.address2, shippingInfo.city, shippingInfo.state, shippingInfo.zipcode)
        Ok(html.order(shippingInfo))
      }
    )}

  def submit = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok(html.submit("dongs"))
  }

  def testpage = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok(html.testpage("Boobs."))
  }

  def logout = Action.async { implicit request =>
    // do something...
    gotoLogoutSucceeded
  }

  protected implicit def template(implicit user: User): String => Html => Html = html.basic.fullTemplate(user)

}
object Messages extends Messages