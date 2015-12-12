import com.datastax.spark.connector.cql.CassandraConnector
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Set Facebook credentials in src/main/resources/twitter_credentials.txt
 *
 * or export as environment variables: !!! TO DO !!!
 *    export TWITTER_CONSUMER_KEY="value"
 *    export TWITTER_CONSUMER_SECRET="value"
 *    export TWITTER_ACCESS_TOKEN="value"
 *    export TWITTER_ACCESS_TOKEN_SECRET="value"
 *
 * or pass them as -D system properties:
 *    -Dtwitter4j.oauth.consumerKey="value"
 *    -Dtwitter4j.oauth.consumerSecret="value"
 *    -Dtwitter4j.oauth.accessToken="value"
 *    -Dtwitter4j.oauth.accessTokenSecret="value"
 *
 * Tweets are saved to a Cassandra instance. To verify persisted data with cqlsh:
 * cqlsh> SELECT * FROM twitter_streaming.tweets
 *
 */

object FacebookStreamingApp {

  // Set Facebook credentials
  FacebookSettings.configureFacebookCredentials()

  // Set Spark configuration and context
  val conf = new SparkConf()
    .setMaster("local[2]")
    .setAppName("FacebookStreamingApp")
    .set("spark.cassandra.connection.host", "localhost")
  val sc = new SparkContext(conf)
  val ssc = new StreamingContext(sc, Seconds(1))

  def main(args: Array[String]): Unit = {
    setUpCassandra()

    val stream = new Streamer
    stream.start(ssc, "facebook_streaming", "posts")
  }

  /**
   * Creates the keyspace and table schema for facebook posts
   */
  def setUpCassandra(): Unit = {
    CassandraConnector(conf).withSessionDo { session =>
      session.execute("CREATE KEYSPACE IF NOT EXISTS facebook_streaming WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1}")
      session.execute("""
        CREATE TABLE IF NOT EXISTS facebook_streaming.posts (
          body text,
          user_id bigint,
          user_screen_name text,
          lang text,
          created_at timestamp,
          like_count int,
          share_count int,
          post_id bigint,
          user_mentions list<text>,
          hashtags list<text>,
          urls list<text>,
          comments list<text>,
          PRIMARY KEY (body, user_id, post_id, user_screen_name)
        )"""
      )
    }
  }
}
