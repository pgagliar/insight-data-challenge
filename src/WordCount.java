

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.TreeMap;
import java.util.Map.Entry;


public class WordCount {
	/*Use a TreeMap to automatically sort entries (value,key)=(word, wordcount) 
	according to the (Ascii code)*/
	TreeMap<String, int[]> datasetCounter;
	
	public WordCount(){
	 this.datasetCounter = new TreeMap<String, int[]>();
	}
	//This function looks for the word inside datasetCounter
	public void count(String word){
	//If word is inside datasetCounter: datasetCounter.get() return the index else it returns null.
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
	//This function write the content of datasetCounter in ft1.txt
	public void writeWordCount() throws FileNotFoundException, UnsupportedEncodingException{
		File file = new File("tweet_output/ft1.txt");
		PrintWriter writer = new PrintWriter(file);
		for (Entry<String, int[]> entry : this.datasetCounter.entrySet()) {
				String word=entry.getKey();
			    int[] value=entry.getValue();
				/*We format the output to align the word count and print it in the file, 
			    we use 100 characters of space */
			    writer.println(String.format("%-100s%d",word,value[0]));
		}
		writer.close();
	}
}
