import java.util.List;

/**
 * Implements the interface Quiz.
 */
public class QuizImpl implements Quiz {
	/**
	 * A list of questions to be asked in the quiz. Is stored in the order they are to be asked.
	 */
	private List<Question> questions;
	/**
	 * The author who wrote the quiz. 
	 * Must be a Player who is registered on the QuizService.
	 */
	private String author;
	/**
	 * The name of the quiz.
	 */
	private String name;
	/**
	 * A boolean flag which indicates if a quiz is active or not.
	 * If true, then the Quiz has been terminated.
	 */
	private boolean terminated;
	/**
	 * quizId identifies each quiz uniquely.
	 * The field is set by the QuizService to ensure uniqueness.
	 */
	private int quizId;
	
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
	}
		
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	
	public void terminate() {
		this.terminated = true;
	}

	boolean isTerminated();

	void addNewQuestion(String question, String[] possibleAnswers, int correctAnswer);

	String getAuthor();

	int getQuizId();

	String getName();
	
	List<Question> getQuestions(); 

	void addNewAttempt(String userName, int score, Calendar date);
	

	List<Attempt> getLeaderboard();
}
