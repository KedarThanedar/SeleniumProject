package Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtils {

	public static String ReadDataFromJson(String path, String key) throws IOException, ParseException
	{
		FileReader reader = new FileReader(".\\DataAndConfigs\\TestData.json");
		JSONParser parser = new JSONParser();
		JSONObject requestObj = (JSONObject) parser.parse(reader);
		return requestObj.get(key).toString();
		
	}
}
