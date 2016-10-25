package Models.Upload

import java.io.File
import play.api.Logger
import play.api.libs.Files.TemporaryFile
import play.api.mvc.MultipartFormData
import play.api.mvc.Request

object UploadService extends UploadService

trait UploadService {

  private val log: Logger = Logger(this.getClass)

  /**
    * Get file from the request and move it in your location
    *
    * @param request
    * @return
    */
  def uploadFile(request: Request[MultipartFormData[TemporaryFile]]): String = {
    log.error("Called uploadFile function" + request)
    request.body.file("file").map { picture =>
      val filename = picture.filename
      val contentType = picture.contentType
      picture.ref.moveTo(new File("""C:\GETTINREAL\""" + filename))
      println("""C:\GETTINREAL\""" + filename).toString
    }.getOrElse {
      "Missing file"
    }
  }

}