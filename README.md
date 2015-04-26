# Quiz
Coursework 5

Version 1.0 26/04/2015
Author: Gareth Moore
GitHub: BBL-PiJ-2014-26

Quiz is a server side and client side application which allows users to create a quiz or play quizzes.

GENERALS USAGE NOTES
--------------------

- Open the server via your system's command line. Once in Quiz's root directory, type the following command to open a server 
  "java QuizServiceLauncher".
  
- The client side application has two options: QuizSetup and QuizPlayer. 

- QuizSetup allows a user to terminate a quiz or setup a new quiz. QuizSetup is opened via the command line. 
  Once in Quiz's root directory, type the following command "java QuizSetup url", 
  where url is the IP address of the server. For example, to connect to a server running on a local machine, type
  "java QuizSetup //localhost". Do not include a line separator as the last character in the url.
  
- QuizPlayer allows a user to play a quiz or view the top 3 scores of a quiz. QuizPlayer is opened via the command line. 
  Once in Quiz's root directory, type the following command "java QuizSetup url", 
  where url is the IP address of the server. For example, to connect to a server running on a local machine, type,
  "java QuizSetup //localhost". Do not include a line separator as the last character in the url.
  
- To use either QuizSetup or QuizPlayer, users must register a username. Usernames must be unique.
  


