Setup for Windows:  
1. Download and install Java JDK. (http://www.oracle.com/technetwork/java/javase/downloads/index.html)  
2. Download and install Gradle. (https://gradle.org/install/)  
3. Using a text editor, open gradle.properties, located in the project root folder.  
    * Set org.gradle.java.home to point to your jdk. This is currently set to "C:\\Program Files\\Java\\jdk1.8.0_102"  
4. Open a command prompt.  
    * Navigate to your project root folder.  
    * From your project root folder, run (Path/to/gradle/bin)/gradle build. Wait for this to complete.  
    * Run "java -jar build\libs\MazeSolverBackEnd-0.0.1-SNAPSHOT.jar"  

The web application should now be running. Open a browser and enter the url http://localhost:8080.  
