

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
//This class compute the running median using the min max heap algorithm

public class RunningMedian {
	/*upperQueue is a heap that contains the upper half of the unique word count
	 * head of this queue: least element
	 */
	PriorityQueue<Integer> upperQueue;
	
	/*lowerQueue is a heap that contains the lower half of the unique word count
	 * head of this queue: greatest element
	 */
    PriorityQueue<Integer> lowerQueue;
	PrintWriter writer;
	HashSet<String> tweetUniqueWords;
	 
	public RunningMedian() throws FileNotFoundException, UnsupportedEncodingException{
		
		this.tweetUniqueWords=new HashSet<String>();
		File file = new File("tweet_output/ft2.txt");
		this.writer = new PrintWriter(file);
		/*By default the priority queue's comparator put the least element at the head of the queue,
		but for the lowerQueue we want the greatest element at the head.*/
		Comparator<Integer> comparatorLowerQueue=new Comparator<Integer>(){
			public int compare(Integer o1, Integer o2){
				//The comparator is reverse
				return -o1.compareTo(o2);
			}
		};
		//We instantiate the lowerQueue with the new comparator
		this.lowerQueue=new PriorityQueue<Integer>(20,comparatorLowerQueue);
		upperQueue=new PriorityQueue<Integer>();
		
		upperQueue.add(Integer.MAX_VALUE);
		lowerQueue.add(Integer.MIN_VALUE);
	}
	//Accessor for the writer, because the median is written in ft2.txt gradually
	public PrintWriter getWriter(){
		return this.writer;
	}
	//Accessor for tweetUniqueWords
	public HashSet<String> getTweetUniqueWords(){
		return this.tweetUniqueWords;
	}
	
	public void setTweetUniqueWords(HashSet<String> tweetUniqueWords){
		this.tweetUniqueWords=tweetUniqueWords;
	}
	//To compute the median we used a min max heap algorithm
	public double getMedian(int num)
    {
        //if num is greater than the upperQueue's head then add it, else add it to lower queue
        if(num>=upperQueue.peek())
               upperQueue.add(num);
           else
              lowerQueue.add(num);
        //balancing the heaps
        if(upperQueue.size()-lowerQueue.size()==2)
            lowerQueue.add(upperQueue.poll());
        else if(lowerQueue.size()-upperQueue.size()==2)
            upperQueue.add(lowerQueue.poll());
        //if the queues have the same size the median in the average between the heads queue
        if(upperQueue.size()==lowerQueue.size())
            return(upperQueue.peek()+lowerQueue.peek())/2.0;
        //if the queues haven't the same size, the median is the head of the largest one
        else if(upperQueue.size()>lowerQueue.size())
            return upperQueue.peek();
        else
            return lowerQueue.peek();
    }
	
	
	public void writeMedianUnique(double median) throws FileNotFoundException, UnsupportedEncodingException{
			//Format the output to align the number and print it in the file
			DecimalFormat df = new DecimalFormat("###.##");
		    writer.println(df.format(median));
	}
}
