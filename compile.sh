#!/bin/bash
# Compile java files in terminal

echo Compile script
echo Enter:
echo - server to only compile Server
echo - client to only compile Client
echo - all compile both Server and Client

while [ true ]
do
    read userIn
    if [ $userIn == "server" ]; then
        echo Compiling Server
        javac Server/src/Main.java
        break
    elif [ $userIn == "client" ]; then
        echo Compiling Client
        javac Client/src/Main.java
        break
    elif [ $userIn == "all" ]; then
        echo Compiling both Server and Client
        javac Server/src/Main.java
        javac Client/src/Main.java
        break
    else
        echo Incorrect input
    fi
done