package id.true_http

import zio.*
import zio.http.*
import scalatags.Text
import scalatags.Text.all._

def htmlPage = 
  html(
    head(
      scalatags.Text.tags2.title("test aja deh"),
      link(
        rel := "stylesheet",
        href := "/assets/css/light.css"
        ),
      script(
        src := "/assets/js/shoelace.js"
        ),
      script(src := "https://unpkg.com/htmx.org@2.0.2")
    ),
    body(
      h1(style:="background-color: blue; color: red;")("This is my title"),
      div(style:="background-color: blue; color: red;")(
        p(`class`:="contentpara first")(
          "This is my first paragraph"
        ),
        a(style:="opacity: 0.9;")(
          p(cls := "contentpara")("Goooogle")
        ),
      )
    )
  )

object Main extends ZIOAppDefault {
  val routes =
    Routes(
      Method.GET / Root -> handler {(req: Request) => 
        scalatagsToResponse(htmlPage)},
      Method.GET / "greet" -> handler { (req: Request) =>
        val name = req.queryParamToOrElse("name", "World")
        Response.text(s"Hello $name!")
      }
    )
    @@ Middleware.serveResources(Path.empty / "assets")

  def run = Server.serve(routes).provide(Server.default)

  private def scalatagsToResponse(view: Text.TypedTag[String]): Response = Response(
    Status.Ok,
    Headers(Header.ContentType(MediaType.text.html).untyped),
    Body.fromString(view.render)
  )
}