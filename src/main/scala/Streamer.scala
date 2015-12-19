import java.text.SimpleDateFormat

import com.datastax.spark.connector.streaming._
import com.datastax.spark.connector.SomeColumns
import org.apache.spark.streaming.StreamingContext

import facebook4j.Post

class Streamer {

  /**
    * Ok, this class ain't finished and I still don't know if it will keep its name for the next stable version.
    * Anyway, its role (for the moment) is to process the posts recovered and insert them into Cassandra.
   */

  val timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

  def processPosts( posts : List[Post]) = {
    /**
      * Although it is not it's final version, this function maps the reovered posts and filters their attrbutes
      * to turn them into objects to be inserted in our Cassandra database.
      */
    val results = posts.map { p : Post => (
        p.getMessage, // done
        p.getFrom.getId, // done
        p.getFrom.getName, // done
        timestampFormat.format(p.getCreatedTime), // done
        p.getLikes.getCount, //done
        p.getSharesCount, //done
        p.getId, //done Post_id
//        p.getUserMentions, // todo : maybe theres no such a method
//        p.getHashtags, //todo : maybe there no such a method
//        p.getUrls, //todo : maybe theres no such a method
        p.getComments //done

      )}

    print(results)

    // Put them one by one into the database


    /**
      * If that shit works let's see how to give Spark some streamed data, e.g. as if it were Twitter Streaming
      * Then this part will be over
      */
  }

  //  def start(ssc: StreamingContext, keyspace: String, table: String) {

    /*
    val filters = Seq("orange", "orange_france", "sosh", "sosh_fr", "orange_conseil")
    val stream = FacebookUtils.createStream(ssc, None, filters).filter(_.getLang == "fr")


    tweet.print()
    tweet.saveToCassandra(keyspace, table, SomeColumns("body", "user_id", "user_screen_name", "lang", "created_at", "like_count", "share_count", "post_id", "user_mentions", "hashtags", "urls", "comments"))

    ssc.checkpoint("./checkpoint")
    ssc.start()
    ssc.awaitTermination()
  */
  //}

}
