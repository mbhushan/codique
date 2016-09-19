import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BoggleGame {

	private String dictionary[] = { "GEEKS", "FOR", "QUIZ", "GO" };
	private Set<String> dict = new HashSet<String>();

	public BoggleGame() {
		Collections.addAll(dict, dictionary);
	}

	public static void main(String[] args) {
		BoggleGame bg = new BoggleGame();

		char boggle[][] = { { 'G', 'I', 'Z' }, { 'U', 'E', 'K' },
				{ 'Q', 'S', 'E' } };

		bg.playBoggle(boggle);
	}

	public void playBoggle(char[][] boggle) {
		if (boggle == null) {
			return;
		}
		int row = boggle.length;
		int col = boggle[0].length;
		boolean[][] visited = new boolean[row][col];

		List<String> result = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				findWords(boggle, i, j, visited, sb, result);
			}
		}
		System.out.println(result.toString());
	}

	private void findWords(char[][] boggle, int i, int j, boolean[][] visited,
			StringBuffer sb, List<String> result) {
		if (i < 0 || i >= boggle.length || j < 0 || j >= boggle[0].length) {
			return;
		}
		if (visited[i][j]) {
			return;
		}

		sb.append(boggle[i][j]);
		visited[i][j] = true;
		if (dict.contains(sb.toString())) {
			result.add(sb.toString());
		}
		for (int r = -1; r <= 1; r++) {
			for (int c = -1; c <= 1; c++) {
				if (r == 0 && c == 0) {
					continue;
				}

				findWords(boggle, i + r, j + c, visited, sb, result);
			}
		}
		visited[i][j] = false;
		sb.deleteCharAt(sb.length() - 1);
	}

}
