package controllers

import play.api.libs.json._
import play.api.libs.functional.syntax._

import scala.math.BigDecimal.RoundingMode

/**
  * Created by James on 8/24/16.
  */
case class Measurements(width1: Int, width2: Int, height1: Int, height2: Int, Qty: Int)

class Pricing {

  implicit val measurementReads: Reads[Measurements] = (
    (JsPath  \ "widthIn").read[Int] and
      (JsPath \ "widthFt").read[Int] and
      (JsPath \ "heightIn").read[Int] and
      (JsPath \ "heightFt").read[Int] and
      (JsPath \ "QUANTITY").read[Int])(Measurements.apply _)



  val BCardQuantities = Map(("0",0), ("1",10), ("2",15), ("3",20), ("4",30))

  def addFeet(widthInches: Int, widthFeet: Int, heightInches: Int, heightFeet: Int): Int = {
    val widthIn = roundupInches(widthInches)
    val widthFt = math.ceil(widthFeet)
    val heightIn = roundupInches(heightInches)
    val heightFt = math.ceil(heightFeet)
    val Width = widthIn + widthFt
    val Height = heightIn + heightFt
    val totalFeet = Width * Height
    totalFeet.toInt
  }

  def roundupInches(a: Double): Int = math.ceil(a / 12).toInt





}


