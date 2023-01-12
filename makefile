all : serveurFTP

doc :
	javadoc src/*.java -d docs;

serveurFTP : 
	cd src;javac ServeurFTP.java 

clean : 
	rm -rf docs; cd src; rm -f *.class; cd command; rm -f *.class; cd ../tool; rm -f *.class;

.PHONY : all clean
