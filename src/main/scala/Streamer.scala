import java.text.SimpleDateFormat

import com.datastax.spark.connector.streaming._
import com.datastax.spark.connector.SomeColumns
import org.apache.spark.streaming.StreamingContext

class Streamer {

  /**
   * Filters with keywords, extract post properties, save to cassandra and start StreamingContext
   * @param ssc
   * @param keyspace
   * @param table
   */
  def start(ssc: StreamingContext, keyspace: String, table: String) {
/*
    val filters = Seq("orange", "orange_france", "sosh", "sosh_fr", "orange_conseil")
    val stream = FacebookUtils.createStream(ssc, None, filters).filter(_.getLang == "fr")

    val timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    val post = stream
      .map { t => (
          t.getText,
          t.getUser.getId,
          t.getUser.getScreenName,
          t.getLang,
          timestampFormat.format(t.getCreatedAt),
          t.getFavoriteCount,
          t.getRetweetCount,
          t.getId,
          t.getUserMentionEntities.map(_.getScreenName).mkString(",").split(",").toList,
          t.getHashtagEntities.map(_.getText).mkString(",").split(",").toList,
          t.getURLEntities.map(_.getExpandedURL).mkString(",").split(",").toList
        )
      }

    tweet.print()
    tweet.saveToCassandra(keyspace, table, SomeColumns("body", "user_id", "user_screen_name", "lang", "created_at", "like_count", "share_count", "post_id", "user_mentions", "hashtags", "urls", "comments"))

    ssc.checkpoint("./checkpoint")
    ssc.start()
    ssc.awaitTermination()
  */
  }

}
