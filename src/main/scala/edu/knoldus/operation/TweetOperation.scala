package edu.knoldus.operation

import java.time.LocalDate

import edu.knoldus.utilities.TwitterConfig.Twitter._
import twitter4j._

import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


object TweetOperation {

  /** find average tweets for given hashTag for last 7 days **/
  def averageCount(hashTag: String): Future[Double] = {
    val days = 7
    countTweets(hashTag).map(x => (x / days).toDouble).recover { case _: Throwable => 0.toDouble }
  }

  /** find total number of tweets for given hashTag **/
  /**
    *
    * @param userName
    * @return
    */
  def countTweets(userName: String): Future[Int] = {
    tweetsOnHashTag(userName).map(x => x.length).recover { case _: Throwable => 0 }
  }

  /** find tweets on given hashTag* **/
  def tweetsOnHashTag(hashTag: String): Future[List[String]] = Future {
    val query = new Query(hashTag)
    val days = 7
    query.setSince(LocalDate.now().minusDays(days).toString)
    val result: QueryResult = search(query)
    val tweets = result.getTweets.asScala.toList
    tweets map (x => x.getText + "\n")
  }.recover { case ex: Throwable => List(ex.getMessage) }

/**likes and reTweets for all tweets **/
  def avgLikesAndReTweets(hashTag: String): Future[List[String]] = Future {
    val tweets: ResponseList[Status] = getHomeTimeline()
    tweets.asScala.toList.map(x => s"${x.getText} Likes:${x.getFavoriteCount} ReTweeted: ${x.getRetweetCount}")
  }
}


