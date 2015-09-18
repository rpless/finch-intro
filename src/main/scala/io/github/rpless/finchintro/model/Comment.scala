package io.github.rpless.finchintro.model

import argonaut._, Argonaut._

object Comment {
  implicit val commentCodec: CodecJson[Comment] =
    casecodec2(Comment.apply, Comment.unapply)("id", "text")
}

/**
 * Represents a comment
 * @param id The comment's identifier
 * @param text The text of the comment
 */
case class Comment(id: Option[Long], text: String)
