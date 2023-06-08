package connection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Connect_Database {
	public static String[] parseSudoku(String level) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/data/Sudoku_database.json"));
		JSONObject jsonObject = (JSONObject)obj;
		JSONArray sudoku = (JSONArray)jsonObject.get(level);
		String S[] = new String[sudoku.size()];
		for(int i=0;i<S.length;i++) {
			S[i] = (String)sudoku.get(i);
		}
		return S;
	}
}
