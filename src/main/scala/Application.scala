import edu.knoldus.operation.TweetOperation._
import org.apache.log4j.Logger

import scala.concurrent.ExecutionContext.Implicits.global

object Application {

  def main(args: Array[String]): Unit = {
    val log = Logger.getLogger(this.getClass)
    val hashTag = "#cool"
    val user = "@sunny"
    countTweets(user).foreach(x => log.info(s"\n\n\n\ncount of Tweets: $x\n\n\n\n"))
    averageCount(user).foreach(x => log.info(s"\n\n\n\navg count of tweets $x\n\n\n\n\n"))
    tweetsOnHashTag(hashTag).foreach(x => log.info(s"Tweets : $x\n\n\n\n"))

    val sleepTime = 6000
    Thread.sleep(sleepTime)

  }
}
