#!/bin/bash
# Compile java files in terminal

echo Compile script
echo Enter:
echo - server to only compile Server
echo - client to only compile Client
echo - config to only compile Config
echo - all compile all programs

while [ true ]
do
    read userIn
    if [ $userIn == "server" ]; then
        echo Compiling Server
        javac Server/src/*.java
        break
    elif [ $userIn == "client" ]; then
        echo Compiling Client
        javac Client/src/*.java
        break
    elif [ $userIn == "config" ]; then
        echo Compiling Config
        javac Config/src/*.java
        break
    elif [ $userIn == "all" ]; then
        echo Compiling all programs
        javac Server/src/*.java
        javac Client/src/*.java
        javac Config/src/*.java
        break
    else
        echo Incorrect input
    fi
done