package io.github.rpless.finchintro.endpoint

import io.finch._
import io.finch.Endpoint
import io.github.rpless.finchintro.AppConfig
import io.github.rpless.finchintro.model.{Comment, CommentThread}
import io.github.rpless.finchintro.readers._

object CommentThreadEndpoints {

  def listCommentThreads(config: AppConfig): Endpoint[Seq[CommentThread]] =
    get("thread") { Ok(config.commentThreadRepo.list()) }

  def getCommentThread(config: AppConfig): Endpoint[CommentThread] =
    get("thread" / long) { (id: Long) =>
      Ok(config.commentThreadRepo.get(id))
    }

  def createComment(config: AppConfig): Endpoint[Comment] = {
    post("thread" / long / "comment" ? commentParamReader) {
      (threadId: Long, comment: Comment) =>
      Created(config.commentThreadRepo.createComment(threadId, comment))
    }
  }
}