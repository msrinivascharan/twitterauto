package com.ms.twitterauto;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class Tweet {

	static String consumerKeyStr = "<your acoount specific>";
	static String consumerSecretStr = "<your acoount specific>";
	static String accessTokenStr = "<your acoount specific>";
	static String accessTokenSecretStr = "<your acoount specific>";

	public static void main(String[] args) throws Exception {
		OAuthConsumer oAuthConsumer = new CommonsHttpOAuthConsumer(consumerKeyStr, consumerSecretStr);
		oAuthConsumer.setTokenWithSecret(accessTokenStr, accessTokenSecretStr);

		HttpPost httpPost = new HttpPost(
				"https://api.twitter.com/1.1/statuses/update.json?status=Hello%20Twitter%20your%20REST%20API%20used%202nd%20time.");

		oAuthConsumer.sign(httpPost);

		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse httpResponse = httpClient.execute(httpPost);

		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println(statusCode + ':' + httpResponse.getStatusLine().getReasonPhrase());
		System.out.println(IOUtils.toString(httpResponse.getEntity().getContent()));

	}
}