package main.java.api;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class Api {

	/**
	 * The base URL for Coursera's hangman API.
	 */
	private static final String URL_BASE = "http://hangman.coursera.org/hangman/game";
	
	/**
	 * Instances of this class hold the responses from the hangman API.
	 */
	public static class GameResponse {
		/**
		 * A unique string identifying the game.
		 */
		public String game_key;

		/**
		 * The partially revealed phrase.  The phrase will be in English and
		 * may contain punctuation (which do not need to be guessed). Hidden
		 * letters are indicated by an underscore (“_”) character.
		 *
		 * For example, this may be: "_e __e___".
		 */
		public String phrase;

		/**
		 * The current state of the game.  This can be one of {"alive", "won",
		 * "lost"}.  "alive" means that the game is in progress.  "won" means
		 * that you have won.  "lost" means that you have lost.
		 */
		public String state;

		/**
		 * The number of incorrect guesses that you can make before you lose.
		 * For example, if this is 0, and you have not yet won, you will lose
		 * on your next incorrect guess.
		 */
		public int num_tries_left;

		@Override
		public String toString() {
			return String.format(
					"response{game_key: %s, phrase: %s, state: %s: num_tries_left: %d}",
					this.game_key,
					this.phrase,
					this.state,
					this.num_tries_left);
		}
	}

	/**
	 * Instances of this class hold the data to POST to the hangman API when
	 * creating a new game.
	 */
	private static class NewGameRequest {
		public String email;
	}

	/**
	 * Instances of this class hold the data to POST to the hangman API when
	 * making a guess in an existing game.
	 */
	private static class GuessRequest {
		public char guess;
	}

	/**
	 * Create a new game.
	 *
	 * @param email
	 *		The e-mail address with which the game is associated.  An e-mail
	 *		address may be associated with multiple games.
	 * @return
	 *		The state of the new game.
	 */
	public static GameResponse sendNewGameRequest(String email) {
		NewGameRequest request = new NewGameRequest();
		request.email = email;
		
		return post("", request, GameResponse.class);
	}

	/**
	 * Send a guess for an existing game.
	 *
	 * @param gameKey
	 *		The game key for the game for which you want to send a guess.  This
	 *		corresponds to the game_key of the GameResponse returned by
	 *		sendNewGameRequest(..).
	 * @param guess
	 *		The character to guess.
	 * @return
	 *		The new state of the game.
	 *
	 * @see sendNewGameRequest(String)
	 */
	public static GameResponse sendGuessRequest(String gameKey, char guess) {
		GuessRequest request = new GuessRequest();
		request.guess = guess;
		
		return post("/" + gameKey, request, GameResponse.class);
	}
	
	private static <T> T post(String relPath, Object request, Class<T> responseType) {
		try {
			URL url = new URL(URL_BASE + relPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			Gson gson = new Gson();
			PrintStream o = new PrintStream(conn.getOutputStream());
			o.print(gson.toJson(request));
			
			int responseCode = conn.getResponseCode(); 
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			StringBuilder builder = new StringBuilder();
			while (reader.ready()) {
				builder.append(reader.readLine());
			}
			String responseBody = builder.toString();

			if (responseCode != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException(String.format(
						"error response (%d): %s",
						responseCode,
						responseBody));
			}

			return gson.fromJson(responseBody, responseType);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
