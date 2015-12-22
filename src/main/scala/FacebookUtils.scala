package utils

//import org.apache.spark.storage.StorageLevel
//import org.apache.spark.streaming.api.java.{JavaReceiverInputDStream, JavaDStream, JavaStreamingContext}
//import org.apache.spark.streaming.dstream.{ReceiverInputDStream, DStream}

import facebook4j._
import facebook4j.auth.AccessToken

import java.util.concurrent._

import scala.io.Source._


object FacebookUtils {
  /**
    * This object/class/singleton [rayez la (les) mention(s) inutile(s)] is where we gonna use Facebook4j functions.
    * It configs, streams and even do some more :)
    */

  val facebook : Facebook = new FacebookFactory().getInstance()

  // This value has to be in seconds as the Facebook graph API uses epoch timestamps
  var lastTimestamp : Long = System.currentTimeMillis / 1000

  // Get the list of the pages to monitor : see if there's a page named Orange Conseil
  // You can change the list of the accounts on the file src/main/resources/facebookPages.txt
  val accounts = fromInputStream(getClass().getResourceAsStream("/facebookPages.txt")).getLines.toList

  def facebookConfig () : Facebook = {
    /**
      * This function sets the necessary tokens to be authorized on Facebook.
      * No particular permission token is required.
      * Just make sure you sat properly the token in the file : src/main/resources/token.txt
      * To get a new token : https://developers.facebook.com/tools/explorer/
      */
    // Get token from the token.txt file
    val token = fromInputStream(getClass().getResourceAsStream("/token.txt")).getLines().next

    facebook.setOAuthAppId("", "")
    facebook.setOAuthAccessToken(new AccessToken(token))

    // TODO : Automatic renewage of the token
    facebook
  }


  def getPosts(page : String) ={
    /**
      * This function sets all the parameters and the desired fields before calling the Facebook API to recover the feed (page's post + other users' posts) from the desired page.
      * @param page : The name of the page for the feed to be recovered (as it appears on your browser's address bar)
      */
    // Tofix : comments in the reading object to take nested fields
    val parameters = new Reading().addParameter("since", lastTimestamp)
      .fields("from", "message", "created_time", "comments", "story_tags")

    setLastTimestamp()
    facebook.getFeed(page, parameters)
  }

  def createStream() = {
    /**
      * Creates a thread that will be ran every X seconds to recover the new posts from all the pages we are monitoring.
      * This is an erzast for a streaming API call as it does'nt exist in Facebook4j
      */

    val ex = new ScheduledThreadPoolExecutor(1)
    val task = new Runnable {
      def run() = {
        /**
          * Il faut faire en sorte qu'une ResponseList[Post] vide soit initialisée puis à chaque fois qu'on a un compte concatener les posts.
          * Ou bien, pour chaque compte recuperer les posts puis lancer les méthodes de data mining, enregistrement sur Cassandra
          */

//        val posts : ResponseList[Post] = new ResponseList().super.empty
        // See if we can recover all the pages in one request, at worst do it like a batch request
        for (account <- accounts) {
          println(account)
          println(getPosts(account))
        }
        println(posts)

        // Process those fucking posts
      }
    }

    // Adjust the value of the period to the rate of users publications on the walls of Orange.France and sosh
    val streamThread = ex.scheduleAtFixedRate(task, 1, 15, TimeUnit.SECONDS)
  }

  def setLastTimestamp() = {
    /**
      * No comment :p
      */
    lastTimestamp = System.currentTimeMillis / 1000
  }

}
