package Models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

import scala.language.postfixOps



case class newOrder(name: String, address1: String, address2: String, city: String, state: String, zipcode: String)
case class placedOrder(id: Long, name: String, address1: String, address2: String, city: String, state: String, zipcode: String)

object Order {

  val parser = {
    get[Long]("id") ~
      get[String]("name") ~
      get[String]("address1") ~
      get[String]("address2") ~
      get[String]("city") ~
      get[String]("state") ~
      get[String]("zipcode") map {
      case id ~ name ~ address1 ~ address2 ~ city ~ state ~ zipcode => placedOrder(id, name, address1, address2, city, state, zipcode)
    }
  }

  def createOrder(
    name: Option[String],
    address1: Option[String],
    address2: Option[String],
    city: Option[String],
    state: Option[String],
    zipcode: Option[String]): Option[Long] = {

    DB.withConnection { implicit c =>
     SQL(
        """
          INSERT INTO placedorder(name, address1, address2, city, state, zipcode)
          VALUES({name}, {address1}, {address2}, {city}, {state}, {zipcode})
        """
      ).on(
        'name -> name.get,
        'address1 -> address1.get,
        'address2 -> address2.get,
        'city -> city.get,
        'state -> state.get,
        'zipcode -> zipcode.get
      )
        .executeInsert()
    }
  }

  def find(id: Long): placedOrder = {
    DB.withConnection { implicit c =>
      SQL("SELECT id, name, address1, address2, city, state, zipcode FROM person WHERE id = {id}").on('id -> id).using(Order.parser).single()
    }
  }

}