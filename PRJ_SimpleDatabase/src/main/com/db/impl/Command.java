package main.com.db.impl;

public class Command {

	private static final String SET = "SET";
	private static final String GET = "GET";
	private static final String UNSET = "UNSET";
	private static final String NUMEQUALTO = "NUMEQUALTO";

	private Database<String> db;

	public Command() {
		db = new Database<String>();
	}

	public void execute(String cmd) {

		String[] tokens = parse(cmd);
		if (tokens.length < 1) {
			return;
		}

		String name = "";
		String value = "";

		switch (tokens[0].toUpperCase()) {
		case SET:
			name = tokens[1].trim();
			value = tokens[2].trim();
			db.set(name, value);
			break;

		case GET:
			name = tokens[1];
			value = db.get(name);
			System.out.println(value);
			break;

		case UNSET:
			name = tokens[1];
			db.unset(name);
			break;

		case NUMEQUALTO:
			value = tokens[1];
			int count = db.numequalto(value);
			System.out.println(count);
			break;
			
		default:
				break;
		}
	}

	private String[] parse(String input) {
		input = input.replaceAll(" +", " ");
		return input.split(" ");
	}

}
