package brhchess;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


class ChessHttpConn {

    private final String USER_AGENT = "Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/71.0.3578.98 Chrome/71.0.3578.98 Safari/537.36";

    // HTTP GET request
    ArrayList<String> sendGet(String route, String query) throws Exception {
    String url = "http://www.hidegver.nhely.hu/chess/";        
    ArrayList<String> response = new ArrayList<>(); 

        url += route + query; 
        System.out.println(url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);
       // con.setRequestProperty("Accept-Language", "en-US,en;q=0.9");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null && !inputLine.contains("EOF")) {
            response.add(inputLine);
        }
        in.close();
        System.out.println("");
    return response;
    }

    
    
    String sendGetFile(String query, boolean write) throws Exception {
        
        String route = (write) ? "index.php/": "savedGameStates/";
        StringBuilder strBldr = new StringBuilder();
        String url = "http://www.hidegver.nhely.hu/chess/" +route+query;

        System.out.println(url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
       //con.setRequestProperty("Accept-Language", "en-US,en;q=0.9");
 
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null && !inputLine.contains("EOF")) {
            strBldr.append(inputLine);
        }
        in.close();
        System.out.println("");
    return strBldr.toString();
    }    




    
}


