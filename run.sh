#!/bin/bash

echo ""
echo "Compiling program from src/Tweet_Stat_Launcher.java ..."
echo ""

javac -d bin/ src/*.java

echo ""
echo "Running program"
echo ""

java -cp bin Tweet_Stat_Launcher 



