package io.github.rpless.finchintro

import _root_.argonaut._
import argonaut.Argonaut._

object CommentThread {
  implicit val commentThreadCodec: CodecJson[CommentThread] =
    casecodec2(CommentThread.apply, CommentThread.unapply)("id", "comments")
}
/**
 * A Thread of comments
 * @param id The id of the CommentThread
 * @param comments The comments in the CommentThread
 */
case class CommentThread(id: String, comments: Seq[Comment])