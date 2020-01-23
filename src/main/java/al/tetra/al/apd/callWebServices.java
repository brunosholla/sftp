package al.tetra.al.apd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("callWebServices")
public class callWebServices {

	// Logger logger = LoggerFactory.getLogger(callWebServices.class);

	private static final Logger LOGGER = LoggerFactory.getLogger(callWebServices.class);

	@Value("${csv.path}") // This properties is not available so it will return default value
	private String csvPath;
	@Value("${csv.name}") // This properties is not available so it will return default value
	private String csvName;

	public void callLejeDitore() throws IOException {
		URL urlForGetRequest = new URL("http://192.168.1.40/bpm/exportAPI.php?search_term=LEJE_DITORE");
		String readLine = null;
		HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
		conection.setRequestMethod("POST");
		conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here
		int responseCode = conection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
			StringBuffer response = new StringBuffer();
			while ((readLine = in.readLine()) != null) {
				response.append(readLine);
			}
			in.close();
			// print result

			JSONObject jsonObj = new JSONObject(response.toString().substring(11));
			JSONArray arrayList = jsonObj.getJSONArray("LEJE_DITORE");

			File csvFile = new File(csvPath, csvName);

			FileWriter csvWriter = new FileWriter(csvFile);

			for (int i = 0; i < arrayList.length(); i++) {
				csvWriter.append(arrayList.get(i).toString());
				csvWriter.append("\n");
			}

			csvWriter.flush();
			csvWriter.close();

			LOGGER.warn("File u shkruajt ne " + new Date() + "\n");
		} else {
			LOGGER.error("Ndhodhi nje gabim ne " + new Date() + "\n");

		}
	}
	
	public void writeLog(String name) {
		LOGGER.warn(name+"\n");
	}
}
