package utils

//import org.apache.spark.storage.StorageLevel
//import org.apache.spark.streaming.api.java.{JavaReceiverInputDStream, JavaDStream, JavaStreamingContext}
//import org.apache.spark.streaming.dstream.{ReceiverInputDStream, DStream}

import facebook4j.FacebookFactory
import facebook4j.Facebook
import facebook4j.auth.AccessToken

object FacebookUtils {
  var lastTimestamp : Long = System.currentTimeMillis

  def facebookConfig (token : String) : Facebook = {
    val facebook: Facebook = new FacebookFactory().getInstance()
    facebook.setOAuthAppId("", "")

    facebook.setOAuthAccessToken(new AccessToken(token))

    facebook
  }


  def getPosts(page : String) ={

  }

  def setLastTimestamp() = {
    lastTimestamp = System.currentTimeMillis
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