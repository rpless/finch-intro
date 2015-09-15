package io.github.rpless.finchintro

import com.twitter.util.Future
import java.util.concurrent.ConcurrentHashMap
import scala.collection.JavaConverters._

class CommentThreadRepository {
  private[this] val commentThreads = new ConcurrentHashMap[String, CommentThread]().asScala

  def get(id: String): Future[CommentThread] = Future { this.commentThreads.get(id).get }

  def list(): Future[Seq[CommentThread]] = Future { this.commentThreads.values.toSeq }

  def put(commentThread: CommentThread): Future[(CommentThread, CommentThreadRepository)] = Future {
    this.commentThreads.put(commentThread.id, commentThread)
    (commentThread, this)
  }
}
