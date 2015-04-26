import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.io.Serializable;

/**
 * Implements the interface Quiz.
 */
public class QuizImpl implements Quiz,Serializable  {
	/**
	 * A list of questions to be asked in the quiz. Is stored in the order they are to be asked.
	 * @serial
	 */
	private List<Question> questions;
	/**
	 * The author who wrote the quiz. 
	 * Must be a Player who is registered on the QuizService.
	 * @serial
	 */
	private String author;
	/**
	 * The name of the quiz.
	 * @serial
	 */
	private String name;
	/**
	 * A boolean flag which indicates if a quiz is active or not.
	 * If true, then the Quiz has been terminated.
	 * @serial
	 */
	private boolean terminated;
	/**
	 * quizId identifies each quiz uniquely.
	 * The field is set by the QuizService to ensure uniqueness.
	 * @serial
	 */
	private int quizId;
	/**
	 * A list of Attempt objects.
	 * Organised by score.
	 * Highest being first, lowest being last.
	 * @serial
	 */
	private Leaderboard leaderboard;
	
	/**
	 * @param questions is a List of ordered question to be asked in the quiz.
	 * @param author is the Player who wrote the quiz.
	 * @param name for this quiz.
	 */
	public QuizImpl(List<Question> questions, String author, String name) {
		this.questions = questions;
		this.author = author;
		this.name = name;
		//Initialises the boolean flag to false.
		//The quiz is active until the method terminate() is called.
		this.terminated = false;
		//quizId is set to 0 initially.
		//The QuizService controls this field to ensure uniqueness.
		this.quizId = 0;
		this.leaderboard = new LeaderboardImpl();
	}
		
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	
	public void terminate() {
		this.terminated = true;
	}

	public boolean isTerminated() {
		boolean result = false;
		if (terminated) {
			result = true;
		}
		return result;
	}

	public void addNewQuestion(String question, String[] possibleAnswers, int correctAnswer)
		throws NullPointerException, IllegalArgumentException {
			//Checks if either question or possibleAnswers is null.
			//If either is truw, an exception is thrown.
			if (question.equals(null) || possibleAnswers.equals(null)) {
				throw new NullPointerException();
			//Checks if possibleAnswers contains 4 elements.
			//If it doesn't, an exception is thrown.
			
			} else if (possibleAnswers.length != 4) {
				throw new IllegalArgumentException();
			//Checks if correctAnswer is less than zero or greater than 3.
			//This is the range which corresponds with the array possibleAnswers.
			//If it is not in this range, an exception is thrown.
			} else if (correctAnswer > 3 || correctAnswer < 0) {
				throw new IllegalArgumentException();
			} else {				
				//Creates a new Question object, 
				//then adds it to the variable instance questions.
				questions.add(new QuestionImpl(question, possibleAnswers, correctAnswer));
			}
	}

	public String getAuthor() {
		return author;
	}

	public int getQuizId() {
		return quizId;
	}

	public String getName() {
		return name;
	}
	
	public List<Question> getQuestions() {
		return questions;
	}

	public void addNewAttempt(Attempt attempt)
		throws NullPointerException {
			//Checks to see if attempt is null.
			//If true, an excpetion is thrown.
			if (attempt.equals(null)) {
				throw new NullPointerException();
			} else {
				//Creates a new Attempt object,
				//then adds it to the leaderboard.
				leaderboard.add(attempt);
			}
	}
	

	public Leaderboard getLeaderboard() {
		return leaderboard;
	}
}
