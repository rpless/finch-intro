package io.github.rpless.finchintro

import io.finch.argonaut._
import io.finch.request._
import io.github.rpless.finchintro.model.Comment
import io.github.rpless.finchintro.model.Comment._

object readers {
  val commentReader = body.as[Comment]
}