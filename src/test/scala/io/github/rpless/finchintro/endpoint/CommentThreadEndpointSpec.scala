package io.github.rpless.finchintro.endpoint

import com.twitter.util.Await
import io.github.rpless.finchintro.model.{Comment, CommentThread}
import io.github.rpless.finchintro.repository.CommentThreadRepository
import org.scalatest.{FlatSpec, Matchers}

class CommentThreadEndpointSpec extends FlatSpec with Matchers {
  val fooThread = CommentThread("foo", Seq(Comment(Some("bar"), "Hello")))
  val entries = Seq(fooThread)
  val commentThreadRepo = new CommentThreadRepository
  entries.foreach(commentThreadRepo.put)

  "A CommentThreadEndpoint" should "list all entries" in {
    val res = Await.result(commentThreadRepo.list())
    res shouldEqual entries
  }

  it should "get a CommentThread by id" in {
    val res = Await.result(commentThreadRepo.get("foo"))
    res shouldEqual fooThread
  }
}