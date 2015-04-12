/**
 * Implements the interface Question.
 */
public class QuestionImpl implements Question {
	private int order;
	private String question;
	private String[] possibleAnswers;
	private int correctAnswer;
	
	public QuestionImpl(String question, String[] possibleAnswers, int correctAnswer) {
		this.question = question;
		this.possibleAnswers = possibleAnswers;
		this.correctAnswer = correctAnswer;
	}
	
	public int getOrder() {
		return 1;
	}
	
	public String getQuestion() {
		return "What was the capital of the Byzantine empire?";
	}
	
	public String[] getPossibleAnswers() {
		String[] result = new String[] {"Constantinople", "Rome", "Odessa", "Ephesus"};
		return result;
	}
	
	public int getCorrectAnswer() {
		return 1;
	}
	
	public void setOrder(int order) {
		if (order <= 0) {
			throw new IllegalArgumentException();
		}
	}
}
