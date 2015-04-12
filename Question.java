/**
 * A question for the quiz.
 * Comprises a question, four multiple choice answers, 
 * and an order number indicating the order this question asked in the quiz.
 */
public interface Question {

	/**
	 * @return the order in which this question is asked in the quiz.
	 */
	int getOrder();
	
	/**
	 * @return the question itself.
	 */
	String getQuestion();
	
	/**
	 * @return possibleAnswers for the quiz, the multiple choice part.
	 */
	String[] getPossIbleAnswers();
	
	/**
	 * @return an int which identifies the correct answer from the array of possible answers.
	 * The int represents the index of the array where the correct answer lies.
	 */
	int getCorrectAnswer();
	
	/**
	 * Sets the order number of the question within its parent Quiz.
	 * The order number should be assigned by the parent Quiz.
	 * 
	 * @param order.
	 * @throws IllegalArgumentException if order is negative or zero.
	 */
	void setOrder(int order);
}
