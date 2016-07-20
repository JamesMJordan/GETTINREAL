package controllers

import javax.inject._
import play.api._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index("Butts."))
  }

  def businesscards = Action {
    Ok(views.html.businesscards("Butts, the Revenge"))
  }

  def banners = Action {
    Ok(views.html.banners("BANNERS"))
  }

  def testpage = Action {
    Ok(views.html.testpage("Boobs."))
  }

  def checkout = Action {
    Ok(views.html.checkout("BOOBS"))
  }
}