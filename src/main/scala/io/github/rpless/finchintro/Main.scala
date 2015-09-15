package io.github.rpless.finchintro

import com.twitter.finagle.Httpx
import com.twitter.util.Await
import io.finch.argonaut._
import io.finch.route._

object Main extends App {
  import CommentThread._
  val commentThreadRepo = new CommentThreadRepository
  commentThreadRepo.put(CommentThread("foo", Seq(Comment("bar", "Hello"))))

  val listComments: Router[Seq[CommentThread]] = get("thread") { commentThreadRepo.list() }
  val getCommentThread: Router[CommentThread] = get("thread" / string) { (str: String) => commentThreadRepo.get(str) }
  val api = (listComments :+: getCommentThread).toService

  Await.ready(Httpx.serve(":8080", api))
}
