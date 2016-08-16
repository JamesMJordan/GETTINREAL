package controllers.auth

import Models.Role._
import jp.t2v.lab.play2.auth.AuthElement
import play.twirl.api.Html
import views._
import play.api.mvc.{Action, Controller}

trait Messages extends Controller with controllers.FormController with AuthElement with AuthConfigImpl {

  def main = StackAction(AuthorityKey -> NormalUser) { implicit request =>
     Ok(html.main("title"))
  }

  def checkout = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok(html.checkout(shippingForm))
  }

  def index = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok(html.index("Butts."))
  }

  def businesscards = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok(html.businesscards("Butts, the Revenge"))
  }

  def banners = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok(html.banners("BANNERS"))
  }

  def testpage = StackAction(AuthorityKey -> NormalUser) { implicit request =>
    Ok(html.testpage("Boobs."))
  }

  def checkout = Action {
    Ok(views.html.checkout(shippingForm))
  }

  def order = Action {
    Ok(views.html.order(ShippingData(Option.empty, Option.empty, Option.empty, Option.empty, Option.empty, Option.empty)))

  }

  def submit = Action {
    Ok(html.submit("dongs"))
  }

  protected implicit def template(implicit user: User): String => Html => Html = html.basic.fullTemplate(user)

}
object Messages extends Messages