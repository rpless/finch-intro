package io.github.rpless.finchintro.model

import argonaut._, Argonaut._

object Comment {
  implicit val decodeComment: DecodeJson[Comment] =
    jdecode3L(Comment.apply)("id", "commenter", "text")

  implicit val encodeComment: EncodeJson[Comment] =
    jencode3L((c: Comment) => (c.id, c.commenter, c.text))("id", "commenter", "text")
}

/**
 * Represents a comment
 * @param id The comment's identifier
 * @param commenter The name of the commenter
 * @param text The text of the comment
 */
case class Comment(id: Option[Long], commenter: String, text: String)
