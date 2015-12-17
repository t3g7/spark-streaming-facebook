package utils

//import org.apache.spark.storage.StorageLevel
//import org.apache.spark.streaming.api.java.{JavaReceiverInputDStream, JavaDStream, JavaStreamingContext}
//import org.apache.spark.streaming.dstream.{ReceiverInputDStream, DStream}

import facebook4j.FacebookFactory
import facebook4j.Facebook
import facebook4j.Reading
import facebook4j.auth.AccessToken

import java.util.concurrent._

import scala.io.Source._


object FacebookUtils {
  // This value has to be in seconds as the faebook graph API uses epoch timestamps
  var lastTimestamp : Long = System.currentTimeMillis / 1000
  val facebook: Facebook = new FacebookFactory().getInstance()

  // Get the list of the pages to monitor
  val accounts = fromInputStream(getClass().getResourceAsStream("/facebookPages.txt")).getLines.toList

  def facebookConfig () : Facebook = {
    // Get token from the token.txt file
    val token = fromInputStream(getClass().getResourceAsStream("/token.txt")).getLines().next

    facebook.setOAuthAppId("", "")
    facebook.setOAuthAccessToken(new AccessToken(token))

    facebook
  }


  def getPosts(page : String) ={
    // TODO : Reading object that gets the parameters required
    // - 1000 is just to test in the wait for a periodic call
    val parameters = new Reading().addParameter("since", lastTimestamp)
    setLastTimestamp()
    facebook.getFeed(page, parameters)
  }

  def setLastTimestamp() = {
    lastTimestamp = System.currentTimeMillis / 1000
  }

  def createStream() = {
    val ex = new ScheduledThreadPoolExecutor(1)
    val task = new Runnable {
      def run() = {
        val posts : List[facebook4j.Post] = List.empty
        for (account <- accounts) {
          print(account)
          posts ++ getPosts(account).asInstanceOf[List[facebook4j.Post]]
          println(posts)
        }
      }
    }
    val f = ex.scheduleAtFixedRate(task, 15, 15, TimeUnit.SECONDS)
  }

/*


  def createStream(
      facebook: Facebook,
      facebookAuth: Authorization,
      filters: Array[String]
    ): JavaReceiverInputDStream[Status] = {
    createStream(jssc.ssc, Some(facebookAuth), filters)
  }
*/
}