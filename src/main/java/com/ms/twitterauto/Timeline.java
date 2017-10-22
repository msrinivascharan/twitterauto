package com.ms.twitterauto;


import java.util.List;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

public class Timeline {
	static String consumerKeyStr = "<your acoount specific>";
	static String consumerSecretStr = "<your acoount specific>";
	static String accessTokenStr = "<your acoount specific>";
	static String accessTokenSecretStr = "<your acoount specific>";
	

	public static void main(String[] args) throws Exception {
		Twitter twitter = new TwitterFactory().getInstance();
		AccessToken accessToken = new AccessToken(accessTokenStr, accessTokenSecretStr);
		twitter.setOAuthConsumer(consumerKeyStr, consumerSecretStr);
		twitter.setOAuthAccessToken(accessToken);
		try {
			User user = twitter.verifyCredentials();
			Paging paging = new Paging(1, 9900);
			List<Status> statuses = twitter.getHomeTimeline(paging);
			System.out.println("Showing @" + user.getScreenName() + "'s home timeline.");
			int i = 0;
			for (Status status : statuses) {
			if (status.getText().matches(
						".*(?i)(agile|devops|DevOps|automation|risk|security|target|microservices|agile|test|vulnerability|continuous|delivery|docker|infrastructure|code|cloud|organization|continuousdelivery|devsecops|containers|machine|Learning).*")) {
					System.out.println(
							"Tweet " + ++i + " " + status.getCreatedAt() + "|" + "@" + status.getUser().getScreenName()
									+ " - " + status.getText() + "                                              ");
			}
			}

		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get timeline: " + te.getMessage());
			System.exit(-1);
		}
	}
}