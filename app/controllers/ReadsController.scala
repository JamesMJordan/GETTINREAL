package controllers

import Models.Item
import play.api.libs.json.{JsPath, Reads}
import play.api.mvc.Controller
import play.api.libs.functional.syntax._


trait ReadsController extends Controller {
     implicit val pricingReads: Reads[Item] = (
       (JsPath \ "Key").read[Long] and
        (JsPath \ "DoubleSided").read[Boolean] and
        (JsPath \ "Quantity").read[Int] and
        (JsPath \ "widthIn").read[Int] and
        (JsPath \ "widthFt").read[Int] and
        (JsPath \ "heightIn").read[Int] and
        (JsPath \ "heightFt").read[Int])(Item.apply _)

}