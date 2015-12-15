import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.api.java.{JavaReceiverInputDStream, JavaDStream, JavaStreamingContext}
import org.apache.spark.streaming.dstream.{ReceiverInputDStream, DStream}
import facebook4j

object FacebookUtils {
  var lastTimestamp : Long = System.currentTimeMillis


  def getPosts(page : String) {

  }

  def setLastTimestamp() {
    lastTimestamp = System.currentTimeMillis
  }



  def createStream(
      jssc: JavaStreamingContext,
      facebookAuth: Authorization,
      filters: Array[String]
    ): JavaReceiverInputDStream[Status] = {
    createStream(jssc.ssc, Some(facebookAuth), filters)
  }

}