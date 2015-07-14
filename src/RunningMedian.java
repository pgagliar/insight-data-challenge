

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
/*This class compute the running median using the min max heap algorithm to
 * find the median of a list.
 * 
 */
public class RunningMedian {
	/*tweetsCount is a list that contains 
	the number of unique words of each tweet in the dataset
	*/
	PriorityQueue<Integer> upperQueue;
    PriorityQueue<Integer> lowerQueue;
	PrintWriter writer;
	/*We use an hashset to compute the second feature because it doesn't allow duplicate word.
	 *So in the Hashset tweetCounter, there are all the unique words of the tweet, 
     *and the size of the hashset tells the number of unique word in the tweet
     */
	HashSet<String> tweetUniqueWords;
	 
	public RunningMedian() throws FileNotFoundException, UnsupportedEncodingException{
		this.tweetUniqueWords=new HashSet<String>();
		File file = new File("tweet_output/ft2.txt");
		this.writer = new PrintWriter(file);
		Comparator<Integer> comparatorLowerQueue=new Comparator<Integer>(){
			public int compare(Integer o1, Integer o2){
				return -o1.compareTo(o2);
			}
		};
		this.lowerQueue=new PriorityQueue<Integer>(20,comparatorLowerQueue);
		upperQueue=new PriorityQueue<Integer>();
	
		upperQueue.add(Integer.MAX_VALUE);
		lowerQueue.add(Integer.MIN_VALUE);
	}
	public PrintWriter getWriter(){
		return this.writer;
	}
	public HashSet<String> getTweetUniqueWords(){
		return this.tweetUniqueWords;
	}
	
	public void setTweetUniqueWords(HashSet<String> tweetUniqueWords){
		this.tweetUniqueWords=tweetUniqueWords;
	}
	//To compute the median we used a min max heap algorithm
	public double getMedian(int num)
    {
        //adding the number to proper heap
        if(num>=upperQueue.peek())
               upperQueue.add(num);
           else
              lowerQueue.add(num);
        //balancing the heaps
        if(upperQueue.size()-lowerQueue.size()==2)
            lowerQueue.add(upperQueue.poll());
        else if(lowerQueue.size()-upperQueue.size()==2)
            upperQueue.add(lowerQueue.poll());
        //returning the median
        if(upperQueue.size()==lowerQueue.size())
            return(upperQueue.peek()+lowerQueue.peek())/2.0;
        else if(upperQueue.size()>lowerQueue.size())
            return upperQueue.peek();
        else
            return lowerQueue.peek();
    }
	
	
	public void writeMedianUnique(double median) throws FileNotFoundException, UnsupportedEncodingException{

			//Format the output to align the number and print it in the file, 
			//there can only be 3 number after the decimal because a tweet is 140 characters max.
			DecimalFormat df = new DecimalFormat("###.##");
		    writer.println(df.format(median));
	}
}
