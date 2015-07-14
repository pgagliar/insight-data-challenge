

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;


public class Tweet_Stat_Launcher {

	public static void main(String[] args) throws IOException {
		String pathToInputFile="tweet_input/high_ascii_free_tweet_sentiment_140.txt";
		long startTime = 0;
		long endTime = 0;
		double duration = 0;
		startTime = System.currentTimeMillis();
		//We create an InputStream, an InputStreamReader and a buffer to read the file line by line
		InputStream is = new FileInputStream(new File(pathToInputFile));
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader buffer = new BufferedReader(isr);
		String tweet;
		//We instantiate two object RunningMedian and WordCount
		RunningMedian runningMedian=new RunningMedian();
		WordCount wordCount=new WordCount();
		
		//Iterate over the tweet file, line by line
		while ((tweet = buffer.readLine()) != null){
			//We use an hashset to compute the second feature because it doesn't allow duplicate word.
			HashSet<String> tweetUniqueWords=runningMedian.getTweetUniqueWords();
			//We iterate over every word in a tweet by splitting the tweet on whitespace
		     for (String word:tweet.split(" ")){
		    	 //We check if the word is not a whitespace
		    	 if (word.trim().length() > 0){
		    		 /*We add a word in the hashset tweetUniqueWords, if the word is already in it,
		    		  *  nothing happen, otherwise the word is added 
			    	  */
		    		 tweetUniqueWords.add(word);
		    		 //We pass a word to the method count
		    		 wordCount.count(word);
		    	 }
		     }
		     /*In the Hashset tweetUniqueWords, there are all the unique words of the tweet, 
			 *so the size of the hashset tells the number of unique word in the tweet,
			 *the we pass the number to the method getMedian.
			 */
		     double median=runningMedian.getMedian(tweetUniqueWords.size());
		     //The output running median is written on ft2.txt
		     runningMedian.writeMedianUnique(median);
		     //The Hashset is reinitialize
		     runningMedian.setTweetUniqueWords(new HashSet<String>());
		     
		}
		wordCount.writeWordCount();
		runningMedian.getWriter().close();
		isr.close();
		is.close();
		buffer.close();
		endTime = System.currentTimeMillis();
		duration = (endTime - startTime)/1000;
		System.out.println("Running time of word count and running median: " + duration+" secondes");
	}

}
