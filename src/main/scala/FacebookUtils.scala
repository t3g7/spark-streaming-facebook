import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.api.java.{JavaReceiverInputDStream, JavaDStream, JavaStreamingContext}
import org.apache.spark.streaming.dstream.{ReceiverInputDStream, DStream}

object FacebookUtils {
  // À completer 




  /**
   * Create a input stream that returns tweets received from Facebook.
   * Storage level of the data will be the default StorageLevel.MEMORY_AND_DISK_SER_2.
   * @param jssc          JavaStreamingContext object
   * @param facebookAuth  Facebook4J Authorization
   * @param filters       Set of filter strings to get only those posts that match them
   */
  def createStream(
      jssc: JavaStreamingContext,
      facebookAuth: Authorization,
      filters: Array[String]
    ): JavaReceiverInputDStream[Status] = {
    createStream(jssc.ssc, Some(facebookAuth), filters)
  }

}