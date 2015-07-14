#!/bin/bash

echo "Looking for Java..."
which java > /dev/null 2> /dev/null

echo "Found Java!"
export JAVA_PATH=`which java`

echo "Using Java at $JAVA_PATH. Current Java version:"
$JAVA_PATH -version


echo ""
echo "Compiling program from src/main/java/Tweet_Stat_Launcher.java ..."
echo ""

javac -d bin/ src/main/java/*.java

echo ""
echo "Running program"
echo ""

java -cp bin main.java.Tweet_Stat_Launcher 



