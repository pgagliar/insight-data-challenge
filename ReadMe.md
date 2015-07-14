#Insight Data Engineering coding challenge:

The coding challenge consists of implementing a word count and a running median on unique words, for a tweet dataset.

##Dataset:
I used java for my solution and tested it on the dataset sentiment140 from Standford.<br />
Originally, this dataset contains 1 600 000 tweets. Tweets containing characters outside [the hexadceimal range [x00-x7E]](http://www.ascii-code.com/) were stripped off from the dataset.<br />
In the end, the dataset contains 1,242,776.

##Solution:
###Word Count:
For the word count, i stocked all the entries (word,count) inside a treemap.<br />
It automatically sorts the word with respect to the Ascii code.<br />
Then the entire treemap is written on the file ft1.txt

###Running Median:
To compute the running median, we used a lower half queue and an upper half queue.<br />
-Number lower than the head of the lowerQueue goes in <br />
-Number greater than the head of the upperQueue goes in it

Finally,
-if the queues have same length then: median is (upperqueue head +lowerQueue head)/2
-else the median is the head of the longest queue.

At each loop, we have to insert one element in O(log(n)).<br />
In the worst case, we have to also balance the two heap, so another O(log(n)).
