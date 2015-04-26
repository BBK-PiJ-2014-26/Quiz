import java.util.LinkedList;
import java.util.Scanner;
import java.util.List;

/**
 * Implements the interface SetupClient and extends QuizClientImpl.
 */
public class SetupClientImpl extends QuizClientImpl implements SetupClient {
	
	/**
	 * Overrides method in QuizClientImpl.	 
	 * Available options are Setup New Quiz or Terminate Quiz.
	 */
	@Override
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
		String name = sc.nextLine();
		List<Question> questions = new LinkedList<Question>();
		boolean finished = false;
		while (!finished) {
			questions.add(requestQuestion());
			System.out.print("Type y if you wish to add another question, type anything else if not: ");
			sc = new Scanner(System.in);
			String answer = sc.next();
			if (!answer.equals("y")) {
				finished = true;
			}
		}
		System.out.println();
		Quiz quiz = new QuizImpl(questions, userName, name);
		try { 
			int quizId = service.addNewQuiz(quiz);
			System.out.println("Congratulations, you made a quiz!\nYour quiz ID is " + quizId);
		} catch (Exception e) {
			System.out.println("There was an error. Please try again.");
		}
	}
	
	/**
	 * Request a Question object the Player.
	 *
	 * @param questions is the list of Question to which the new Question is to be added.
	 * @return a Question object.
	 */
	private Question requestQuestion() {
		Question result = null;
		System.out.print("Please enter the text of your question: ");
		Scanner sc = new Scanner(System.in);
		String question = sc.nextLine();
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
				String answer = sc.nextLine();					
				//If the user answers y , correct answer is set to the index of the array possibleAnswers.
				if (answer.equals("y")) {
					correctAnswer = i;
				}
			}
		}
		result = new QuestionImpl(question, possibleAnswers, correctAnswer);
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
				Attempt winner = service.getWinner(quizId);
				System.out.println("The winner is " + winner.getUserName() + " with a score of "
					+ winner.getScore() + ".\n");
			}
		} catch (Exception e) {
			//If an exception occurs, calls chooseOption().
			System.out.println("There was an error, please try again.");
		}
	}
}
