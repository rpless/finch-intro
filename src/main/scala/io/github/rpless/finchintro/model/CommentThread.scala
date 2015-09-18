package io.github.rpless.finchintro.model

import argonaut.Argonaut._
import argonaut._

object CommentThread {
  implicit val commentThreadCodec: CodecJson[CommentThread] =
    casecodec2(CommentThread.apply, CommentThread.unapply)("id", "comments")
}
/**
 * A Thread of comments
 * @param id The id of the CommentThread
 * @param comments The comments in the CommentThread
 */
case class CommentThread(id: Long, comments: Seq[Comment])
