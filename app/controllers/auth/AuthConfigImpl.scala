package controllers.auth

import jp.t2v.lab.play2.auth.{AuthConfig, CookieTokenAccessor}
import play.api.mvc.{RequestHeader, Result}
import play.api.mvc.Results._
import Models._
import Models.Role._

import scala.concurrent.{ExecutionContext, Future}
import scala.reflect.{ClassTag, classTag}

trait AuthConfigImpl extends AuthConfig {

  type Id = Account
  type User = Account
  type Authority = Role

  val idTag: ClassTag[Id] = classTag[Id]
  val sessionTimeoutInSeconds = 3600

  def resolveUser(id: Id)(implicit ctx: ExecutionContext) = Future.successful(Some(id))

  def loginSucceeded(request: RequestHeader)(implicit ctx: ExecutionContext): Future[Result] =
    Future.successful(Redirect("/index"))

  def logoutSucceeded(request: RequestHeader)(implicit ctx: ExecutionContext): Future[Result] =
    Future.successful(Redirect("/login"))

  def authenticationFailed(request: RequestHeader)(implicit ctx: ExecutionContext): Future[Result] =
    Future.successful(Redirect("/login"))

  override def authorizationFailed(request: RequestHeader, user: User, authority: Option[Authority])(implicit context: ExecutionContext): Future[Result] = {
    Future.successful(Forbidden(routes.auth.Messages.login(formWithErrors)))
  }

  def authorize(user: User, authority: Authority)(implicit ctx: ExecutionContext) = Future.successful((user.role, authority) match {
    case (Administrator, _) => true
    case (NormalUser, NormalUser) => true
    case _ => false
  })

  override lazy val idContainer = new BasicAuthIdContainer

  override lazy val tokenAccessor = new BasicAuthTokenAccessor

  override lazy val tokenAccessor = new CookieTokenAccessor(
    /*
     * Whether use the secure option or not use it in the cookie.
     * Following code is default.
     */
    cookieSecureOption = play.api.Play.isProd(play.api.Play.current),
    cookieMaxAge       = Some(sessionTimeoutInSeconds)
  )
}