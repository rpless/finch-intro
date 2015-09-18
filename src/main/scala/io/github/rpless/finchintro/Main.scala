package io.github.rpless.finchintro

import com.twitter.finagle.Httpx
import com.twitter.util.Await
import io.finch.argonaut._
import io.github.rpless.finchintro.endpoint.CommentThreadEndpoint
import io.github.rpless.finchintro.model.{CommentThread, Comment}
import io.github.rpless.finchintro.repository.CommentThreadRepository

object Main extends App {
  val commentThreadRepo = new CommentThreadRepository
  commentThreadRepo.put(CommentThread(0, Seq(Comment(Some(0), "Hello"))))

  val config: AppConfig = AppConfig(commentThreadRepo)
  val api = CommentThreadEndpoint(config).toService

  Await.ready(Httpx.serve(":8080", api))
}