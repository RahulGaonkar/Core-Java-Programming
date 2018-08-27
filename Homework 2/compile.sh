#!/usr/bin/env bash

# TODO - ensure `target/classes` exists (creating it if it doesn't)
# TODO - compile all the Java files within the project and output them into `target/classes`

mkdir target/classes
cd src/main/java
javac -d ../../../target/classes edu/nyu/cs9053/homework2/*.java
