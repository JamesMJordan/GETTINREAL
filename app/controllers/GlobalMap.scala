package controllers

import scala.collection.concurrent._

/**
  * Created by James on 8/19/16.
  */
object GlobalMap {
  private[controllers] val container: TrieMap[String, AnyRef] = new TrieMap[String, AnyRef]()
}
