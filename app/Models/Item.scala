package Models

import java.io.File

import Models.JSON.{Measurements, Price}
import play.api.data.Form
import play.api.data.Forms._
import play.twirl.api.Html


case class Item(User: String, File: String, Quantity: Int, Size: Measurements, Price: Price)

object Item {
  val sizeForm = Form {
    mapping(
      "widthIn" -> number,
      "widthFt" -> number,
      "heighIn" -> number,
      "heightFt" -> number
    )(Measurements.apply)(Measurements.unapply)
  }

  val priceForm = Form {
    mapping(
      "Key" -> number,
      "DoubleSided" -> boolean,
      "Quantity" -> number
    )(Price.apply)(Price.unapply)
  }

  val itemForm = Form {
    mapping(
      "User" -> text,
      "File" -> text,
      "Quantity" -> number,
      "Size" -> sizeForm.mapping,
      "Price" -> priceForm.mapping
    )(Item.apply)(Item.unapply)
  }


}
