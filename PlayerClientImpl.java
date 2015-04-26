import java.util.List;
import java.util.ListIterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Calendar;
import java.io.Serializable;

/**
 * Implements the interface PlayerClient.
 */
public class PlayerClientImpl extends QuizClientImpl implements PlayerClient, Serializable {

	/**
	 * Overrides method in QuizClientImpl.	 
	 * Calls selectQuiz();
	 */
	@Override
	public void chooseOption(String userName) {
		selectQuiz(userName);
	}
	
	public void selectQuiz(String userName) {
		List<Quiz> quizzes = new LinkedList<Quiz>();;
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
			System.out.println("\nCurrently active quizzes:\n");
			//If not empty, prints the details of active quizzes.
			System.out.println("Quiz ID\t\tQuiz Name");
			//Creates an iterator so the programme can print the details of each quiz.
			ListIterator<Quiz> iterator = quizzes.listIterator();
			while (iterator.hasNext()) {
				Quiz temp = iterator.next();
				System.out.println(temp.getQuizId() + "\t\t" + temp.getName());
			}
			selectQuizOptions(userName);
		}
	}

	/**
	 * Offers users options to select a quiz or exit.
	 */
	private void selectQuizOptions(String userName) {
		System.out.print("\nType 1 to play a quiz.\nType 2 to see top 3.\nType 3 to exit.\nSelection: ");
		Scanner sc = new Scanner(System.in);
		String selection = sc.next();
		//If user has inputted 1, call requestQuizId().
		if (selection.equals("1")) {
			int quizId = requestQuizId();
			try {
				List<Question> questions = service.playQuiz(userName, quizId);
				playQuiz(questions, userName, quizId);
			} catch (Exception e) {
				System.out.println("There was an error. Please try again.\n");
				selectQuizOptions(userName);
			}
		//If user has inputted 3, calls displayTop3().
		} else if (selection.equals("2")) {
			int quizId = requestQuizId();
			try {
				Leaderboard top3 = service.getTop3(quizId);
				displayTop3(userName, top3);
			} catch (Exception e) {
				System.out.println("There was an error. Please try again.\n");
				selectQuizOptions(userName);
			}
		//If user has inputted 3, exits application.
		} else if (selection.equals("3")) {
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
			System.out.println();
		//If user input does not contain an int,
		//prints error, then calls requestQuizId() again.
		} else {
			System.out.println("There was an error. Please try again.\n");
			quizId = requestQuizId();
		}
		return quizId;
	}
	
	public void playQuiz(List<Question> questions, String userName, int quizId) {
		int score = 0;
		//Creates an iterator to cycle through the questions.
		ListIterator<Question> iterator = questions.listIterator();
		while (iterator.hasNext()) {
			Question temp = iterator.next();
			System.out.println(temp.getQuestion());
			String[] possibleAnswers = temp.getPossibleAnswers();
			System.out.println("A: " + possibleAnswers[0]);
			System.out.println("B: " + possibleAnswers[1]);
			System.out.println("C: " + possibleAnswers[2]);
			System.out.println("D: " + possibleAnswers[3] + "\n");
			int answer = requestAnswer();
			//Tests whether the Player has submitted the correctAnswer.
			//If true, increments the score.
			if (answer == temp.getCorrectAnswer()) {
				score++;
			}
		}
		try {
			Attempt a = new AttemptImpl(userName, score);
			service.addNewAttempt(a, quizId);
		} catch (Exception e) {
			System.out.println("There was an error. Please try again.\n");
			selectQuizOptions(userName);
		}
		System.out.println("You scored " + score + ".");
	}
	
	/**
	 * Requests an answer from the Player.
	 *
	 * @return the index of possibleAnswers where the Player guesses the answer.
	 */
	private int requestAnswer() {
		//Initialised 
		int index = -1;
		System.out.print("What is your answer: ");
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		//If the answer is A, the index is set to 0.
		if (s.equals("A")) {
			index = 0;
		//If the answer is B, the index is set to 1.
		} else if (s.equals("B")) {
			index = 1;
		//If the answer is C, the index is set to 2.
		} else if (s.equals("C")) {
			index = 2;
		//If the answer is D, the index is set to 3.
		} else if (s.equals("D")) {
			index = 3;
		} else {
			System.out.println("Invalid answer, please try again.");
			index = requestAnswer();
		}
		System.out.println();
		return index;
	}
	
	public void displayTop3(String userName, Leaderboard top3) {
		for (int i = 0; i < top3.size(); i++) {
			Attempt temp = top3.get(i);
			System.out.println("\n" + (i + 1) + ": " + temp.getUserName() + " " + temp.getScore());
		}
	}		
}
