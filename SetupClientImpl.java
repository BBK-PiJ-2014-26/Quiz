import java.util.LinkedList;
import java.util.Scanner;
import java.util.List;

/**
 * Implements the interface SetupClient and extends QuizClientImpl.
 */
public class SetupClientImpl extends QuizClientImpl implements SetupClient {
	
	public void welcome() {
		System.out.println("\n\n~~~Welcome to Quizzes of the Ancients~~~");
		System.out.print("\n" + "SETUP" + "\n");
		selectWelcomeOption();		
	}
	
	/**
	 * Prompts the user for a selection within welcome.
	 * Private because this method should only be used within the context of welcome().
	 */
	private void selectWelcomeOption() {
		System.out.print("\nPlease select an option.\n" + "\n");
		System.out.print("Type 1 to login.\n" + "Type 2 to register new player.\n" + "Type 3 to exit\n"+ "Selection: ");
		Scanner sc = new Scanner(System.in);
		String selection = sc.next();
		//Checks if the user has selected 1.
		//If true, runs login().
		if (selection.equals("1")) {
			String userName = requestUserName();
			//Attempts to login the user.
			//If login succeeds, the user proceeds.			
			if (login(userName)) {
				chooseOption(userName);
			//If login fails, selectWelcomeOption is called again.
			} else {
				System.out.println("Username does not exist. Please try again.");
				selectWelcomeOption();
			}
		//Checks if the user has selected 2.
		//If true, runs register().
		} else if (selection.equals("2")) {
			String userName = requestUserName();
			//Attempts to register a new Player.
			//If registration fails, calls selectWelcomeOption().
			if (!register(userName)) {
				System.out.println("There was an error. Please try again.");
				selectWelcomeOption();
			//If registration succeeds, the user proceeds.
			} else {
				chooseOption(userName);
			}
		//Checks if user has selected 3.
		//If true, the programme will terminate.
		} else if (selection.equals("3")) {
		//If none of these conditions are true, an error has occurred.
		} else {
			selectWelcomeOption();
		}
	}
	
	/**
	 * Request a user userName from the user.
	 *
	 * @return the userName submitted by the user.
	 */
	private String requestUserName() {
		System.out.print("Please enter your username: ");
		Scanner sc = new Scanner(System.in);
		String userName = sc.next();
		return userName;
	}
	
	public void chooseOption(String userName) {
		System.out.println("\nPlease select an option.");
		System.out.print("\n" + "Type 1 to setup a new quiz.\n" + "Type 2 to terminate a quiz.\n");
		System.out.print("Type 3 to exit.\n" + "Selection: ");
		Scanner sc = new Scanner(System.in);
		String selection = sc.next();
		//If the user has typed 1, calls setupNewQuiz().
		if (selection.equals("1")) {
			setupNewQuiz(userName);
			chooseOption(userName);
		//If user has typed 2, calls terminateQuiz().
		} else if (selection.equals("2")) {
			terminateQuiz(userName);
			chooseOption(userName);
		//If user has typed 3, the programme completes its execution.
		} else if (selection.equals("3")) {
		//Otherwise there was an error input, so chooseOption is called again.
		} else {
			chooseOption(userName);
		}
	}
	
	public void setupNewQuiz(String userName) {
		System.out.print("Please enter the name of your quiz: ");
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		List<Question> questions = requestQuestion();
		Quiz quiz = new QuizImpl(questions, userName, name);
		try { 
			int quizId = service.addNewQuiz(quiz);
			System.out.println("Congratulations, you made a quiz!\nYour quiz ID is " + quizId);
		} catch (Exception e) {
			System.out.println("There was an error. Please try again.");
		}
		chooseOption(userName);
	}
	
	/**
	 * Is called recursively until the user terminates.
	 *
	 * @param questions is the list of Question to which the new Question is to be added.
	 */
	private List<Question> requestQuestion() {
		List<Question> result = new LinkedList<Question>();
		System.out.print("Please enter the text of your question: ");
		Scanner sc = new Scanner(System.in);
		String question = sc.next();
		String[] possibleAnswers = new String[4];
		//Initialises correctAnswer to 10 because this is not a valid answer.
		//Should therefore not interfere with user input.
		int correctAnswer = 10;
		for (int i = 0; i < 4; i++) {
			System.out.print("Please enter possible answer: ");
			sc = new Scanner(System.in);
			possibleAnswers[i] = sc.next();
			//Checks correctAnswer. If it's between 0 and 3 then correctAnswer is already set.
			if (correctAnswer < 0 || correctAnswer > 3) {
				System.out.print("Type y if this is the correct answer, type anything else if not: ");
				sc = new Scanner(System.in);
				String answer = sc.next();					
				//If the user answers y , correct answer is set to the index of the array possibleAnswers.
				if (answer.equals("y")) {
					correctAnswer = i;
				}
			}
		}
		result.add(new QuestionImpl(question, possibleAnswers, correctAnswer));
		System.out.print("Type y if you wish to add another question, type anything else if not: ");
		sc = new Scanner(System.in);
		String answer = sc.next();
		//If the user answers y , requestQuestion is called again.
		if (answer.equals("y")) {
			result = requestQuestion();
		}
		System.out.println();
		return result;
	}
	
	public void terminateQuiz(String userName) {
		System.out.print("Type the ID of the Quiz you wish to terminate: ");
		Scanner sc = new Scanner(System.in);
		int quizId = sc.nextInt();
		try {
			//Checks whether the supplied quizId exists on the quizService.
			//If false, terminateQuiz() is called again.
			if (!service.quizIdExists(quizId)) {
				System.out.println("Invalid ID, please try again.\n");
				terminateQuiz(userName);
			} else {	
				service.terminateQuiz(userName, quizId);
			}
		} catch (Exception e) {
			//If an exception occurs, calls chooseOption().
			System.out.println("There was an error, please try again.");
		}
	}
}
