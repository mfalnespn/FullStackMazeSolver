Setup for Windows:
	Download and install Java JDK. (http://www.oracle.com/technetwork/java/javase/downloads/index.html)	
	Download and install Gradle. (https://gradle.org/install/)
	Go to <PROJECT_ROOT>/gradle.properties
		Set org.gradle.java.home to point to your jdk.
	Open a command prompt.
		Navigate to your project root folder.
		From your project root folder, run (Path/to/gradle/bin)/gradle build
		Run "java -jar build\libs\MazeSolverBackEnd-0.0.1-SNAPSHOT.jar"
The web application should now be running. Open a browser and enter the url localhost:8080.
