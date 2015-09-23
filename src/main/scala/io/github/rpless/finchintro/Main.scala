package io.github.rpless.finchintro

import com.twitter.finagle.Httpx
import com.twitter.util.Await
import io.finch.argonaut._
import io.github.rpless.finchintro.endpoint.CommentThreadEndpoints
import io.github.rpless.finchintro.model.{CommentThread, Comment}
import io.github.rpless.finchintro.model.Comment._
import io.github.rpless.finchintro.repository.CommentThreadRepository

object Main extends App {
  import CommentThreadEndpoints._
  val commentThreadRepo = new CommentThreadRepository
  commentThreadRepo.put(CommentThread(0, Seq(Comment(Some(0), "Ryan", "Hello"))))
  val config: AppConfig = AppConfig(commentThreadRepo)

  var endpoints =
    listCommentThreads(config) :+:
    getCommentThread(config) :+:
    createComment(config)

  Await.ready(Httpx.serve(":8080", endpoints.toService))
}