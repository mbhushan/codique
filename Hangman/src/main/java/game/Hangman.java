package main.java.game;

import main.java.api.Api;

class Hangman {

	private HangmanEngine engine;
	private Api.GameResponse response;
	public static final String ALIVE = "alive";
	
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			Hangman hman = new Hangman();
			hman.init();
			hman.playHangman();
		}

	}

	public void init() {
		this.response = Api.sendNewGameRequest("test@test.com");
		String phrase = response.phrase;
		this.engine = new HangmanEngine(phrase);
	}

	public void playHangman() {
		while (true) {
			char ch = this.engine.getNextChar(this.response.phrase);
			// System.out.println("ch:::" + ch);
			this.response = Api.sendGuessRequest(this.response.game_key, ch);
			System.out.println(this.response);
			if (!this.response.state.equals(ALIVE)) {
				break;
			}
		}

		System.out.println(this.response.state);
	}

}
