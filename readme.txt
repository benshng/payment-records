Assumption
- It is assumed that if the user has entered an invalid input, an error message will be shown on the screen.

- Assume that by default it will not load the payment data file when the application is starting up. The optional file loading is configured via the properties file of DataFileConfig.properties

- The data file is assumed to be located under the resources folder 

- Assume that the payment records will be retained only while the program is running


How to build and run the program
- In command line, navigate to the directory where the source files are located
- Type "mvn clean install".
- Then navigate to the target directory where the jar file is located and type "java -jar <jar file name>
- The program will be loaded in the console.