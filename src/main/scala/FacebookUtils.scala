import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.api.java.{JavaReceiverInputDStream, JavaDStream, JavaStreamingContext}
import org.apache.spark.streaming.dstream.{ReceiverInputDStream, DStream}


class FacebookUtils {
  var lastTimestamp : Long = System.currentTimeMillis


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