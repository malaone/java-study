package com.malaone.leetcode;

import java.util.HashMap;
import java.util.Random;

/**
 * TinyURL is a URL shortening service where you enter a URL such as 
 * https://leetcode.com/problems/design-tinyurl and it returns a short 
 * URL such as http://tinyurl.com/4e9iAk.
 * Design the encode and decode methods for the TinyURL service. 
 * There is no restriction on how your encode/decode algorithm should work. 
 * You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL 
 * can be decoded to the original URL.
 * @author lifeix
 *
 */
public class Codec {
	HashMap<String,String> hashUrl = new HashMap<>();
	HashMap<String,String> urlHash = new HashMap<>();
	
	String tinyUrlBase = "http://tinyurl.com/";
	String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	int length = 6;
	Random random = new Random();
	
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if(urlHash.containsKey(longUrl)) {
        	return tinyUrlBase + urlHash.get(longUrl);
        }
        
        StringBuilder hash = new StringBuilder();
        do {
        	for(int i=0;i<length;i++) {
        		hash.append(characters.charAt(random.nextInt(characters.length())));
        	}
        } while(hashUrl.containsKey(hash.toString()));
    	
        urlHash.put(longUrl, hash.toString());
        hashUrl.put(hash.toString(), longUrl);
        
    	return tinyUrlBase + urlHash.get(longUrl);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
    	return hashUrl.get(shortUrl.substring(tinyUrlBase.length()));
    }
}
