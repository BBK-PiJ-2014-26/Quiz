/**
 * A question for the quiz.
 * Comprises a question, four multiple choice answers, 
 * and an int representing the index of the correct Answer in the array storing possible answers.
 * @author Gareth Moore
 */
public interface Question {

	/**
	 * @return the question itself.
	 */
	String getQuestion();
	
	/**
	 * @return possibleAnswers for the quiz, the multiple choice part.
	 */
	String[] getPossibleAnswers();
	
	/**
	 * @return an int which identifies the correct answer from the array of possible answers.
	 * The int represents the index of the array where the correct answer lies.
	 */
	int getCorrectAnswer();
}
