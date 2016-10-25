package Models.DatabaseServices

import Models.ProductSYW
import anorm.SqlParser._
import anorm._
import play.api.db._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

object ProductDB extends ProductSYW {

  def getId(Key: Int): Int = DB.withConnection { implicit c =>
    SQL("SELECT id FROM products WHERE id = {Key}").on('Key -> Key) as scalar[Int].single
  }

  def getMaterial(Key: Int): String = DB.withConnection { implicit c =>
    SQL("SELECT materials FROM products WHERE id = {Key}").on('Key -> Key) as scalar[String].single
  }

  def getQuantity(Key: Int): Int = DB.withConnection { implicit c =>
    SQL("SELECT qty FROM products WHERE id = {Key}").on('Key -> Key) as scalar[Int].single
  }

  def getPrice(Key: Int): Double = DB.withConnection { implicit c =>
    SQL("SELECT price FROM products WHERE id = {Key}").on('Key -> Key) as scalar[Double].single
  }

}
