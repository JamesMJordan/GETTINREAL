package models
import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import play.api.libs.ws._

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

  def create(name: String, address1: String, address2: String, city: String, state: String, zipcode: String): Unit = {
    DB.withConnection { implicit c =>
      val id: Long = SQL("INSERT INTO placedOrder(name, address1, address2, city, state, zipcode) VALUES({name}, {address1}," +
        "{address2}, {city}, {state}, {zipcode)").on('name -> name, 'address1 -> address1, 'address2 -> address2,
        'city -> city, 'state -> state, 'zipcode -> zipcode)
        .executeInsert(scalar[Long] single)

    }
  }

  def find(id: Long): placedOrder = {
    DB.withConnection { implicit c =>
      SQL("SELECT id, name, address1, address2, city, state, zipcode FROM person WHERE id = {id}").on('id -> id).using(Order.parser).single()
    }
  }

}