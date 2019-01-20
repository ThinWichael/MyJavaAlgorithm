import java.io.*;
import java.net.*;
import java.util.*;

import com.google.gson.*;

public class HTTP_SERVICE {

	public HTTP_SERVICE() {
		// TODO Auto-generated constructor stub
	}

	public class Client {
		private String url = null;

		// public Client(String iurl) {
		// url = iurl;
		// }

		public LinkedHashMap<String, Object> getHTTP_GET() {
			LinkedHashMap<String, Object> linkedMap = null;

			try {
//				BufferedWriter obw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

				HttpURLConnection connection = null;

				URL url_ = new URL(this.url);

				connection = (HttpURLConnection) url_.openConnection();
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Authorization", "hmac username='FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF', algorithm='hmac-sha1', headers='x-date', signature='AbIywL0jXCNg8ugqOub/HRJdhJU='");
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestProperty("x-date" , new Date().toGMTString());
                connection.setRequestProperty("Accept-Encoding", "gzip");
                connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
                
				InputStream input = connection.getInputStream();
				StringBuffer buffer = new StringBuffer();
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));

				String inputLine;
				while ((inputLine = reader.readLine()) != null) {
					buffer.append(inputLine + "\n");
				}

				String jsonRes = buffer.toString();

				Gson gson = new Gson();

				linkedMap = gson.fromJson(jsonRes, LinkedHashMap.class);

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return linkedMap;

		}

		public void setUrl(String url) {
			this.url = url;
		}
	}

	public static class HSRservice {
		static HTTP_SERVICE hs = new HTTP_SERVICE();
		static final Client client = hs.new Client();

		public LinkedHashMap getHSR_News() {
			client.setUrl("https://ptx.transportdata.tw/MOTC/v2/Rail/THSR/News?$top=30&$format=JSON");

			return client.getHTTP_GET();
		}

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.print("Hello ! ");

		// Scanner in = new Scanner(System.in);
		// final String fileName =
		// "C:/Users/airko/workspace/MJ_JAVA_Libery/output/output.txt";
		// BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
		// String subStr = null;
		//
		// try {
		// System.out.println("請輸入字串：");
		// subStr = in.nextLine();
		// System.out.println("您輸入的字串：" + subStr);
		// } catch (Exception e) {
		// e.printStackTrace();
		// subStr = null;
		// }
		HSRservice hs = new HSRservice();
		LinkedHashMap map = hs.getHSR_News();
		System.out.println(map);

	}

}
