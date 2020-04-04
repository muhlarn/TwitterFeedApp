# TwitterFeedApp
Reads text from two files and output a twitter-like feed

## How to use:

Create two files called user.txt and tweet.txt.

The first file (user.txt) contains a list of users and their followers. The second file (tweet.txt) contains tweets. Given the users, followers and tweets, the objective is to display a simulated twitter feed for each user to the console. Create a folder and put the two files there.

- Go the root directory of the project
- Run the following command: ***mvn clean compile assembly:single***
- Finally execute by typing: ***java -jar target/twitter-app-jar-with-dependencies.jar /Path/to/your/folder***

## Assumptions

The validation of the application will always return a generic message even though it validates correctlt. Future releases will impprove some of the features.

## Technologies

1. This is a java application written using Java 8
2. Mockito for unit testing due to it's mocking abilities
3. This was done using Test-Driven Development (TDD)
4. This project is using Maven for built automation
