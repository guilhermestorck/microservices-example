.PHONY : devserver run

run : devserver
	gradle :app:run

devserver : dependencies/mockserver.jar
	java -jar dependencies/mockserver.jar -serverPort 1080 -logLevel INFO

dependencies/mockserver.jar :
	mkdir -p dependencies
	wget -O - http://search.maven.org/remotecontent?filepath=org/mock-server/mockserver-netty/5.5.4/mockserver-netty-5.5.4-jar-with-dependencies.jar >> dependencies/mockserver.jar
