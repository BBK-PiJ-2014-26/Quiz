import java.io.Serializable;

/**
 * Implements the interface Question.
 * @author Gareth Moore
 */
public class QuestionImpl implements Question, Serializable {
	/**
	 * The question itself.
	 * @serial
	 */
	private String question;
	/**
	 * An array containing possible solutions to the question.
	 * The array must contain 4 elements.
	 * @serial
	 */
	private String[] possibleAnswers;
	/**
	 * An int which represents the solution to the question.
	 * The int matches the index of possibleAnswers where the solution lies.
	 * @serial
	 */
	private int correctAnswer;
	
	/**
	 * @throws IllegalArgumentException if the length of the array is not 4.
	 */
	public QuestionImpl(String question, String[] possibleAnswers, int correctAnswer) throws IllegalArgumentException {
		if (possibleAnswers.length != 4) {
			throw new IllegalArgumentException();
		} else {
			this.question = question;
			this.possibleAnswers = possibleAnswers;
			this.correctAnswer = correctAnswer;
		}
	}
	
	public String getQuestion() {
		return question;
	}
	
	public String[] getPossibleAnswers() {
		return possibleAnswers;
	}
	
	public int getCorrectAnswer() {
		return correctAnswer;
	}
}
