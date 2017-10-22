package com.ms.twitterauto;

import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TweetSearch 

{

	static String consumerKeyStr = "<your acoount specific>";
	static String consumerSecretStr = "<your acoount specific>";
	static String accessTokenStr = "<your acoount specific>";
	static String accessTokenSecretStr = "<your acoount specific>";

	public static void main(String[] args) throws Exception {
		
		
		Twitter twitter = new TwitterFactory().getInstance();

		AccessToken accessToken = new AccessToken(accessTokenStr,accessTokenSecretStr);
	    twitter.setOAuthConsumer(consumerKeyStr, consumerSecretStr);
	    twitter.setOAuthAccessToken(accessToken);

	    try {
	        Query query = new Query("mamidi");
	        QueryResult result;
	        result = twitter.search(query);
	        List<Status> tweets = result.getTweets();
	        for (Status tweet : tweets) {
	            System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
	        }
	    }
	    catch (TwitterException te) {
	        te.printStackTrace();
	        System.out.println("Failed to search tweets: " + te.getMessage());
	        System.exit(-1);
	    }
		
		

	}
}