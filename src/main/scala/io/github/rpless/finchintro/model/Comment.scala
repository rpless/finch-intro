package io.github.rpless.finchintro.model

import argonaut._, Argonaut._

object Comment {
  implicit val commentCodec: CodecJson[Comment] =
    casecodec3(Comment.apply, Comment.unapply)("id", "commenter", "text")
}

/**
 * Represents a comment
 * @param id The comment's identifier
 * @param commenter The name of the commenter
 * @param text The text of the comment
 */
case class Comment(id: Option[Long], commenter: String, text: String)
