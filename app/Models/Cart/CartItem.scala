//package Models.Cart
//
//import java.io.File
//
//import scalikejdbc._
//import anorm._
//import play.api.db.DB
//
//
//
///**
//  * Created by James on 9/6/16.
//  */
//
//case class CartItem(User: Long, Image: File, Quantity: Int, Price: Double, Description: String)
//
//object CartItem {
//
//
//  def cartSubmit(AccountID: Long, Image: File, Quantity: Int, Price: Double, Description: String) =
//    DB.withConnection { implicit c => SQL(
//      """
//          INSERT INTO cartitem(id, image, qty, price, description)
//          VALUES({AccountID}, {Image}, {Quantity}, {Price}, {Description})
//      """
//    ).on(
//      'id -> AccountID.get,
//      'image -> Image.get,
//      'qty -> Quantity.get,
//      'price -> Price.get,
//      'description -> Description.get
//    )
//      .executeInsert()
//    }
//}





