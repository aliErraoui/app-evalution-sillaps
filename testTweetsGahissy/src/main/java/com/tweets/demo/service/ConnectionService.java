package com.tweets.demo.service;




public interface ConnectionService {
	
	public void getTweetsByKeyWord(String gsm, String motCle, String countryCode) ;
	public void stpStream() ;

}
