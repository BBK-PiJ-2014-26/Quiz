public class SetupClientImpl implements SetupClient {
	
	public void welcome() {
		System.out.println("Welcome to Quizzes of the Ancients.");
		System.out.print("\n" + "SETUP\n" + "\n");

		
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
			register(requestUserName());
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
	
	public boolean login(String userName) {
		boolean result = false;
		//Checks whether the userName is registered on the QuizService.
		if (service.userNameExists(userName)) {
			result = true;
		}
		return result;
	}
	
	boolean register(String userName) {
		boolean result = false;
		//Checks whether the userName exists on the QuizService.
		//If false, the userName is unique and the QuizService registers it.
		if (!service.userNameExists(userName)) {
			try {
				service.registerNewPlayer(userName);
				result = true;
			} catch (Exception e) {
				//If there is an exception, no action is required,
				//because result has been initialised to false.
			}
		}
		return result;
	}
}
