package io.github.rpless.finchintro.repository

import com.twitter.util.Future
import io.github.rpless.finchintro.model.{Comment, CommentThread}
import java.util.concurrent.ConcurrentHashMap
import scala.collection.JavaConverters._

class CommentThreadRepository {
  private[this] val commentThreads = new ConcurrentHashMap[String, CommentThread]().asScala

  def createComment(threadId: String, comment: Comment): Future[Comment] = Future {
    val uuid = java.util.UUID.randomUUID.toString
    val updatedComment = comment.copy(id = Some(uuid))
    commentThreads.get(threadId) match {
      case Some(thread) =>
        val updatedComments = updatedComment +: thread.comments
        commentThreads.put(threadId, thread.copy(comments = updatedComments)) match {
          case Some(_) => updatedComment
          case None => throw new Exception
        }
      case None => throw new Exception
    }
  }

  def get(id: String): Future[CommentThread] = Future { this.commentThreads.get(id).get }

  def list(): Future[Seq[CommentThread]] = Future { this.commentThreads.values.toSeq }

  def put(commentThread: CommentThread): Future[CommentThread] = Future {
    this.commentThreads.put(commentThread.id, commentThread)
    commentThread
  }
}
