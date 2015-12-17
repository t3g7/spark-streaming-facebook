package utils

//import org.apache.spark.storage.StorageLevel
//import org.apache.spark.streaming.api.java.{JavaReceiverInputDStream, JavaDStream, JavaStreamingContext}
//import org.apache.spark.streaming.dstream.{ReceiverInputDStream, DStream}

import facebook4j.FacebookFactory
import facebook4j.Facebook
import facebook4j.Reading
import facebook4j.auth.AccessToken

object FacebookUtils {
  // This value has to be in seconds as the faebook graph API uses epoch timestamps
  var lastTimestamp : Long = System.currentTimeMillis / 1000
  val facebook: Facebook = new FacebookFactory().getInstance()

  def facebookConfig (token : String) : Facebook = {
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