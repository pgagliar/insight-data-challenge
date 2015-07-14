#Insight Data Engineering coding challenge:

The coding challenge consists of implementing a word count and a running median on unique words, for a tweet dataset.

##Dataset:
I used java for my solution and tested it on the dataset sentiment140 from Standford.<br />
Originally, this dataset contains 1 600 000 tweets. Tweets containing characters outside [the hexadceimal range [x00-x7E]](http://www.ascii-code.com/) were stripped off from the dataset.<br />
In the end, the dataset contains 1,242,776.

##Solution:
For the word count, i stocked all the entries (word,count) inside a treemap that automatically sorts the entries with respect to the Ascii code.<br />
Min Max heap algorithm find median:
At each loop, we have to insert one element in O(log(n)) and in the worst case to also balance the two heap so another O(log(n)).
In the end complexity is O(log(n))
insert in  with n number of element in the stack.
