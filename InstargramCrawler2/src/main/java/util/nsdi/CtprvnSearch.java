package util.nsdi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import com.google.gson.Gson;

import util.nsdi.dto.AdmListResponseDTO;


public class CtprvnSearch {
	private final String URL = "http://openapi.nsdi.go.kr/nsdi/eios/service/rest/AdmService/admCodeList.json";
	private final String KEY = "6829339fc9881fc8fe4f20";
	
	public CtprvnSearch() {
		// TODO Auto-generated constructor stub
	}
	
	public AdmListResponseDTO urlGetSend() throws IOException {
	
        URL url = new URL(URL+"?" + URLEncoder.encode("authkey","UTF-8")+"=" + KEY);
        
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json; ");
		
        //2
        con.setDoInput(true);
        int responseCode=con.getResponseCode();
        
		//3
        BufferedReader br;
        if(responseCode==200)  // 정상 호출
            br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        else throw new IOException(responseCode+" : "+con.getResponseMessage());

        //4
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = br.readLine()) != null) response.append(inputLine);
        br.close();
        return new Gson().fromJson(response.toString(),AdmListResponseDTO.class);
	}
}
