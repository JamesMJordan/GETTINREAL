package object util {
  implicit class SmarterRequest(request: play.api.mvc.Request[Map[String,Seq[String]]]) {
    def get(paramName:String) = request.body.get(paramName).map(_.head).getOrElse("")
  }
}
