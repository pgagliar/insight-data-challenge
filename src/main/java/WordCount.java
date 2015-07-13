package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map.Entry;


public class WordCount {
	//Use a TreeMap to automatically sort entries by value
	TreeMap<String, int[]> datasetCounter;
	HashMap<String,int[]> documentFrequencies;
	
	public WordCount(){
	 this.datasetCounter = new TreeMap<String, int[]>();
	}
	/*This function try to see if a word is inside datasetCounter, 
	*if it's not the function get will return null, 
	*otherwise it returns the count of the word
	*/
	public void count(String word){
	 int[] wordCounter = datasetCounter.get(word);
	 
		if (wordCounter == null) {
			/*We use a int array instead of a int to store the count, 
			*because it used less memory
			*/
			datasetCounter.put(word, new int[] { 1 });
		} else {
			wordCounter[0]++;
		}
	}
	public void writeWordCount() throws FileNotFoundException, UnsupportedEncodingException{
		File file = new File("tweet_output/ft1.txt");
		PrintWriter writer = new PrintWriter(file);
		for (Entry<String, int[]> entry : this.datasetCounter.entrySet()) {
				String word=entry.getKey();
			    int[] value=entry.getValue();
				//Format the output to align the number and print it in the file, we use 60 characters for a word max because a tweet can only be 140 characters
			    writer.println(String.format("%-100s%d",word,value[0]));
		}
		writer.close();
	}
}
