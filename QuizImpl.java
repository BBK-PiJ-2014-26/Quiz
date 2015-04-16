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
	 * A list of Attempt objects.
	 * Organised by score.
	 * Highest being first, lowest being last.
	 */
	private List<Attempt> leaderboard;
	
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

	public boolean isTerminated() {
		boolean result = false;
		if (terminated) {
			result = true;
		}
		return result;
	}

	void addNewQuestion(String question, String[] possibleAnswers, int correctAnswer)
		throws NullPointerException, IllegalArgumentException {
			//Checks if either question or possibleAnswers is null.
			//If either is truw, an exception is thrown.
			if (question.equals(null) || possibleAnswers.equals(null)) {
				throw new NullPointerException();
			//Checks if possibleAnswers contains 4 elements.
			//If it doesn't, an exception is thrown.
			
			} else if (possibleAnswers.size() != 4) {
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

	public void addNewAttempt(String userName, int score, Calendar date)
		throws NullPointerException, IllegalArgumentException {
			//Checks to see if userName or date is null.
			//If either is true, an excpetion is thrown.
			if (userName.equals(null) || date.equals(null)) {
				throw new NullPointerException();
			//Checks to see if score is a negative number.
			//If it is, an exception is thrown.
			} else if (score < 0) {
				throw new IllegalArgumentException();
			} else {
				//Creates a new Attempt object,
				//then adds it to the leaderboard.
				leaderboard.add(new AttemptImpl(userName, score, date);
			}
	}
	

	public List<Attempt> getLeaderboard() {
		return leaderboard;
	}
}
