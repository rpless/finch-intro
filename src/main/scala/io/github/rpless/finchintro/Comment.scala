package io.github.rpless.finchintro

import _root_.argonaut._
import argonaut.Argonaut._

object Comment {
  implicit val commentCodec: CodecJson[Comment] =
    casecodec2(Comment.apply, Comment.unapply)("id", "text")
}

/**
 * Represents a comment
 * @param id The comment's identifier
 * @param text The text of the comment
 */
case class Comment(id: String, text: String)

