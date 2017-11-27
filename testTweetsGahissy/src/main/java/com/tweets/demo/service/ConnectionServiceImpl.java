package com.tweets.demo.service;

import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

@Service("connectionService")
public class ConnectionServiceImpl implements ConnectionService {

	private final static String ACCOUNT_SID = "AC3fd14259286af3b4fc14bc4b0fb406cc"; 
    private final static String AUTH_TOKEN = "f0202aaa4073d7738f573aee27d1a54e";
    private  static TwitterStream twitterStream ;
	@Override
	public void getTweetsByKeyWord(String gsm, String motCle, String countryCode) {
		// TODO Auto-generated method stub
		
		
		ConfigurationBuilder cb=new ConfigurationBuilder() ;
		cb.setDebugEnabled(true)
		.setOAuthConsumerSecret("QxwkTwOeJTMx3GyhXOxODtq3x9RLDb8h9peC5g7uEqWZ9rxRjV")
		.setOAuthConsumerKey("7cfsLFzm2aUvTqkSLJmW00PSC")
		.setOAuthAccessToken("933148248881156097-IcpAExCVbwnukj6quyVpVn24gqHYCxX")
		.setOAuthAccessTokenSecret("AZGDC2bcicUpQdq8csc7jR5E4lUAHUCMIJHbXv0W1Aqc4") ;
		
		  twitterStream = new TwitterStreamFactory(cb.build()).getInstance();

	        StatusListener listener = new StatusListener() {

	            @Override
	            public void onException(Exception arg0) {
	                // TODO Auto-generated method stub

	            }

	            

	            @Override
	            public void onScrubGeo(long arg0, long arg1) {
	                // TODO Auto-generated method stub

	            }

	            @Override
	            public void onStatus(Status status) {
	                User user = status.getUser();
	                
	                // gets Username
	                String username = status.getUser().getScreenName();
	                System.out.println(username);
	                String profileLocation = user.getLocation();
	                System.out.println(profileLocation);
	                long tweetId = status.getId(); 
	                System.out.println(tweetId);
	                String content = status.getText();
	                System.out.println(content +"\n");
	                
	                
	                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	              @SuppressWarnings("unused")
					Message message = Message
	                      .creator(new PhoneNumber("+"+countryCode+gsm), // to
	                               new PhoneNumber("+13608462268"), // from
	                               content)
	                      .create();
	                
	               

	            }

	            @Override
	            public void onTrackLimitationNotice(int arg0) {
	                // TODO Auto-generated method stub

	            }

				
				@Override
				public void onDeletionNotice(StatusDeletionNotice arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onStallWarning(StallWarning arg0) {
					// TODO Auto-generated method stub
					
				}

	        };
	        FilterQuery fq = new FilterQuery();
	    
	        String keywords[] = {motCle};

	        fq.track(keywords);

	        twitterStream.addListener(listener);
	        twitterStream.filter(fq);  
	        //******************************************************************************
	
	}
	
	
	
	
	@Override
	public void stpStream() {
		// TODO Auto-generated method stub
		if(twitterStream!=null) {
		            twitterStream.cleanUp(); 
		            twitterStream.shutdown();
		                       }
		
	}
	

}
