

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
		//We create flux of tweet that will read the file line by line
		InputStream is = new FileInputStream(new File(pathToInputFile));
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader buffer = new BufferedReader(isr);
		String tweet;
		RunningMedian medianUnique=new RunningMedian();
		WordCount wordCount=new WordCount();
		
		//Iterate over the tweet file, line by line
		while ((tweet = buffer.readLine()) != null){
			HashSet<String> tweetUniqueWords=medianUnique.getTweetUniqueWords();
			//We pass the entire tweet to the method computeTfAndDf, to find a query term in it
			//We iterate over every word in a tweet
		     for (String word:tweet.split(" ")){
		    	 //We check if the word is not a whitespace
		    	 if (word.trim().length() > 0){
		    		 /*We add a word in the hashset, if the word is already in the hashset nothing happen
			    	  otherwise the word is added in the hashset
			    	  */
		    		 tweetUniqueWords.add(word);
		    		 wordCount.count(word);
		    	 }
		     }
		     /*In the Hashset tweetUniqueWords, there are all the unique words of the tweet, 
			 *so the size of the hashset tells the number of unique word in the tweet
			 */
		     double median=medianUnique.getMedian(tweetUniqueWords.size());
		     medianUnique.writeMedianUnique(median);
		     //The Hashset is reinitialize
		     medianUnique.setTweetUniqueWords(new HashSet<String>());
		     
		}
		wordCount.writeWordCount();
		medianUnique.getWriter().close();
		isr.close();
		is.close();
		buffer.close();
		endTime = System.currentTimeMillis();
		duration = (endTime - startTime)/1000;
		System.out.println("Running time of word count and running median: " + duration+" secondes");
	}

}
