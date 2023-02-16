all : serverFTP 

doc :
	javadoc src/*.java -d docs;

serverFTP : 
	cd src;javac ServerFTP.java 

clean : 
	rm -rf docs; cd src; rm -f *.class; cd ../tool; rm -f *.class;

.PHONY : all clean
