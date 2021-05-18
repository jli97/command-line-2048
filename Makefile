######################################
# Author: 	Jonathan Li				 #
# Revised: 	2021-04-12				 #
# Description:	"MAKEFILE"			 #
######################################

ifeq ($(OS),Windows_NT)
	SEP=;
else
	SEP=:
endif

JFLAGS = -g
JCLASS = -cp "src$(SEP).$(SEP)../junit-4.5.jar"
JC = javac
JVM = java

.PHONY: test doc expt

start:
	$(JC) $(JCLASS) $(JFLAGS) src/TestExpt.java
	$(JVM) src/TestExpt

clean:
	rm -rf html
	rm -rf latex
	cd src
	rm **/*.class
