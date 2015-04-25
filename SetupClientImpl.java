public class SetupClientImpl extends QuizClientImpl implements SetupClient {
	
	public void welcome() {
		System.out.println("Welcome to Quizzes of the Ancients.");
		System.out.print("\n" + "SETUP\n" + "\n");
		selectWelcomeSelection();		
	}
	
	/**
	 * Prompts the user for a selection within welcome.
	 * Private because this method should only be used within the context of welcome().
	 */
	private void selectWelcomeOption() {
		System.out.print("Please select an option.\n" + "\n");
		System.out.print("Type 1 to login.\n" + "Type 2 to register new player.\n" + "Type 3 to exit\n"+ "Selection: ");
		Scanner sc = new Scanner(System.in);
		String selection = sc.next();
		//Checks if the user has selected 1.
		//If true, runs login().
		if (selection.equals("1")) {
			//Attempts to login the user.
			//If login succeeds, the user proceeds.			
			if (login(requestUserName())) {
				chooseOption();
			//If login fails, selectWelcomeOption is called again.
			} else {
				System.out.println("Username does not exist. Please try again.");
				selectWelcomeOption();
			}
		//Checks if the user has selected 2.
		//If true, runs register().
		} else if (selection.equals("2")) {
			//Attempts to register a new Player.
			//If registration succeeds, the user proceeds.
			if (register(requestUserName())) {
				System.out.println("There was an error. Please try again.");
				selectWelcomeOption();
			}
		//Checks if user has selected 3.
		//If true, the programme will terminate.
		} else if (selection.equals("3") {}
		//If none of these conditions are true, an error has occurred.
		} else {
			selectWelcomeOption(requestUserName());
		}
	}
	
	/**
	 * Request a user userName from the user.
	 *
	 * @return the userName submitted by the user.
	 */
	private String requestUserName() {
		System.out.print("Please enter you username: ");
		Scanner sc = new Scanner(System.in);
		String userName = sc.next();
		return userName;
	}
	
	public void chooseOption() {
		System.out.println("Please select an option.");
		System.out.print("\n" + "Type 1 to setup a new quiz.\n" + "Type 2 to terminate a quiz.\n");
		System.out.print("Type 3 to exit.\n" + "Selection: ");
		Scanner sc = new Scanner(System.in);
		String selection = sc.next();
		//If the user has typed 1, calls setupNewQuiz().
		if (selection.equals("1")) {
			setupNewQuiz();
		//If user has typed 2, calls terminateQuiz().
		} else if (selection.equals("2")) {
			terminateQuiz();
		//If user has typed 3, the programme completes its execution.
		} else if (selection.equals("3")) {
		//Otherwise there was an error input, so chooseOption is called again.
		} else {
			chooseOption();
		}
	}
}
