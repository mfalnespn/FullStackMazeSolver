Setup:
	Download and install latest Java JDK. (http://www.oracle.com/technetwork/java/javase/downloads/index.html)	
	Download and install a copy of Gradle. (https://gradle.org/install/)

	Go to <PROJECT_ROOT>/gradle.properties
		Set org.gradle.java.home to point to the copy of your jdk.
	Open command prompt.
		Navigate to your project root folder.
		Run (Path/to/gradle/download)/gradle build
		Run java -jar build\libs\MazeSolverBackEnd-0.0.1-SNAPSHOT.jar
The web application should now be running. Open a browser and enter the url localhost:8080.