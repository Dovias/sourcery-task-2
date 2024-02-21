# sourcery-task-2
My humble attempt to solve Sourcery Academy For Full-Stack 2024 bonus task for the upcoming admission test. This solution uses Java's functional programming and generics to make it as much future-proof and dynamic as possible.

# Building 🛠️
First of all clone the git project from this git repository using `git clone https://github.com/Dovias/sourcery-task-2.git` command. Make sure you have `git` installed on your computer.
This project uses Java 17 (may be compatible with Java 8+) and gradle build system to build this small java application. If you want to build a runnable jar file for this project execute `./gradlew jar` task to build the jar, or else just run the `./gradlew run` to run this application directly from gradle build system.

# Running 💿
After building jar file for this application, the usage is simple: there's only one argument to specify the length of fizzbuzz sequence.
To run this application from jar file with specified sequence length you need to type:
`java -jar <built jar file path> <sequence length>`

Otherwise type this below in root directory to run the application from the gradle build system:
`./gradlew run --args <sequence length>`
