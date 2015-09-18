package io.github.rpless.finchintro.repository

import java.util.concurrent.atomic.AtomicLong

import com.twitter.util.Future
import io.github.rpless.finchintro.model.{Comment, CommentThread}
import java.util.concurrent.ConcurrentHashMap
import scala.collection.JavaConverters._

class CommentThreadRepository {
  private[this] val commentThreads = new ConcurrentHashMap[Long, CommentThread]().asScala
  private[this] val commentCounter = new AtomicLong(1)

  def createComment(threadId: Long, comment: Comment): Future[Comment] = {
    val uuid = java.util.UUID.randomUUID.toString
    val updatedComment = comment.copy(id = Some(commentCounter.getAndIncrement()))
    commentThreads.get(threadId) match {
      case Some(thread) =>
        val updatedComments = updatedComment +: thread.comments
        commentThreads.put(threadId, thread.copy(comments = updatedComments)) match {
          case Some(_) => Future(updatedComment)
          case None => Future.exception(DatabaseExpection("Failed to Create Comment."))
        }
      case None => Future.exception(DatabaseExpection("CommentThread does not Exist."))
    }
  }

  def get(id: Long): Future[CommentThread] = Future { this.commentThreads.get(id).get }

  def list(): Future[Seq[CommentThread]] = Future { this.commentThreads.values.toSeq }

  private[finchintro] def put(commentThread: CommentThread): Future[CommentThread] = Future {
    this.commentThreads.put(commentThread.id, commentThread)
    commentThread
  }
}


case class DatabaseExpection(msg: String) extends Exception(msg)