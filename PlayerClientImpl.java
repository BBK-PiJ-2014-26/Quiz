import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Implements the interface PlayerClient.
 */
public class PlayerClientImpl extends QuizClientImpl implements PlayerClient {

	/**
	 * Overrides method in QuizClientImpl.	 
	 * Calls selectQuiz();
	 */
	@Override
	public void chooseOption(String userName) {
		selectQuiz(userName);
	}
	
	public void selectQuiz(String userName) {
		System.out.println("Currently active quizzes:\n\n");
		List<Quiz> quizzes = null;
		try {
			quizzes = service.getAllActiveQuizzes();
		} catch (Exception e) {
			System.out.println("There was an error. Exiting application.");
		}
		//Tests whether the list is empty. 
		//If empty, prints an appropriate message.
		if (quizzes.isEmpty()) {
			System.out.println("There are no active quizzes.\nPlease come back later.");
		} else {
			//If not empty, prints the details of active quizzes.
			System.out.println("Quiz ID\tQuiz Name");
			//Creates an iterator so the programme can print the details of each quiz.
			ListIterator<Quiz> iterator = quizzes.listIterator();
			while (iterator.hasNext()) {
				Quiz temp = iterator.next();
				System.out.println(temp.getQuizId() + "\t" + temp.getName());
			}
			selectQuizOptions(userName);
		}
	}

	/**
	 * Offers users options to select a quiz or exit.
	 */
	private void selectQuizOptions(String userName) {
		System.out.println("\nType 1 to play a quiz.\nType 2 to exit.\nSelection: ");
		Scanner sc = new Scanner(System.in);
		String selection = sc.next();
		//If user has inputted 1, call requestQuizId().
		if (selection.equals("1")) {
			int quizId = requestQuizId();
			try {
				List<Question> questions = service.playQuiz(userName, quizId);
				playQuiz(questions, userName);
			} catch (Exception e) {
				System.out.println("There was an error. Please try again.\n");
				selectQuizOptions(userName);
			}
		//If user has inputted 2, exits application.
		} else if (selection.equals("2")) {
		//If none of the conditions have been fulfilled, calls selectQuizOptions().
		} else { 
			System.out.println("There was an error. Please try again.\n");
			selectQuizOptions(userName);
		}	
	}
	
	/**
	 * Requests a quizId from the user.
	 * 
	 * @return the quizId.
	 */
	private int requestQuizId() {
		//Initialises quizId to a negative number because this will throw an exception on the QuizService.
		int quizId = -1;
	 	System.out.print("Enter Quiz ID to play quiz: ");
		Scanner sc = new Scanner(System.in);
		//Tests if the user input contains an int.
		if (sc.hasNextInt()) {
			quizId = sc.nextInt();
		//If user input does not contain an int,
		//prints error, then calls requestQuizId() again.
		} else {
			System.out.println("There was an error. Please try again.\n");
			quizId = requestQuizId();
		}
		return quizId;
	}
	
	public void playQuiz(List<Question> questions, String userName) {}
	
}
