import scala.io.Source._

object FacebookSettings {
	/**
	To do later because it is slightly different from twitter's config
	*/
  def configureFaebookCredentials() = {
    val file = getClass().getResourceAsStream("facebook_credentials.txt")
    for (line <- fromInputStream(file).getLines()) {
      val key :: value :: _ = line.replace(" ","").split("=").toList
      val fullKey = "facebook4j.auth." + key;
      System.setProperty(fullKey, value)
    }
  }
}
