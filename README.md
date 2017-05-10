# spark-streaming-facebook
## /!\ This is NOT a stable version yet. For a stable package, take a look at [streaming-facebook](https://github.com/CatalystCode/streaming-facebook).

### Configuration
Get a user access token (https://developers.facebook.com/tools/explorer/) and copy it into ```src/main/resources/token.txt```
Set the accounts you want feed to be recovered from in ```src/main/resources/facebookPages.txt```.
The account names have to be written as they appear in the browser's address bar, one account per line.

### Building
Create the JAR with ```sbt assembly```, which is now located in ```target/scala-2.10/spark-streaming-twitter-assembly-$VERSION.jar```.

### Running the app (not tested yet )
Note: a Cassandra instance must be running.
From the ```$SPARK_HOME``` folder, run the following:

    ./bin/spark-submit --class FacebookStreamingApp $PATH_TO_JAR/spark-streaming-facebook-assembly-$VERSION.jar
