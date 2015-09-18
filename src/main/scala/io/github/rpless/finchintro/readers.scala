package io.github.rpless.finchintro

import io.finch.argonaut._
import io.finch.request._
import io.github.rpless.finchintro.model.Comment
import io.github.rpless.finchintro.model.Comment._

object readers {
  val commentBodyReader = body.as[Comment]

  val commentForReader = for {
    commenter <- param("commenter")
    text <- body
  } yield Comment(None, commenter, text)

  val commentParamReader = (
    paramOption("id").as[Long].should("Be Empty") { _.isEmpty } ::
    param("commenter") ::
    body
  ).as[Comment]
}