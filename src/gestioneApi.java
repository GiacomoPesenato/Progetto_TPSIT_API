import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;;
import java.net.URL;
import java.util.ArrayList;

public class gestioneApi extends HttpServlet {

    private String apiToBuild= "https://ergast.com/api/f1/";
    private String anno;
    private URL url;
    private HttpURLConnection connessione;
    private JsonParser parser = new JsonParser();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println(req.getParameter("ricercaInformazioniTipologia"));
        if(req.getParameter("tipologiaSelect").equals("races")){
            visualizzaGPAnno(req, resp);
        }else if(req.getParameter("tipologiaSelect").equals("constructors")){
            visualizzaCostruttoriAnno(req,resp);
        }else if(req.getParameter("tipologiaSelect").equals("drivers")){
            visualizzaPilotiAnno(req,resp);
        }
    }

    public void visualizzaGPAnno(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        JSONObject jsonObj = connessione(req);
        //System.out.println(jsonObj.getJSONObject("MRData").getJSONObject("RaceTable").getJSONArray("Races").length());
        ArrayList<visualizzaGPAnno> al = new ArrayList<>();
        JSONArray jsonArray = jsonObj.getJSONObject("MRData").getJSONObject("RaceTable").getJSONArray("Races");
        for(int i = 0; i< jsonArray.length(); i++) {
            visualizzaGPAnno vGPA = new visualizzaGPAnno(jsonArray.getJSONObject(i));
            al.add(vGPA);
        }
        //System.out.println(al.size());
        req.setAttribute("visualizzaGPAnno", al);
        req.getRequestDispatcher("/viewGPAnno.jsp").forward(req, resp);
    }

    public void visualizzaCostruttoriAnno(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        JSONObject jsonObject = connessione(req);
        ArrayList<visualizzaCostruttoriAnno> al = new ArrayList<visualizzaCostruttoriAnno>();
        JSONArray jsonArray = jsonObject.getJSONObject("MRData").getJSONObject("ConstructorTable").getJSONArray("Constructors");
        for(int i = 0; i<jsonArray.length(); i++){
            visualizzaCostruttoriAnno vCA = new visualizzaCostruttoriAnno(jsonArray.getJSONObject(i),i+1);
            al.add(vCA);
        }
        //System.out.println();
        req.setAttribute("visualizzaCostruttoriAnno", al);
        req.getRequestDispatcher("/viewCostruttoriAnno.jsp").forward(req, resp);

    }

    public void visualizzaPilotiAnno(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        JSONObject jsonObject = connessione(req);
        ArrayList<visualizzaPilotiAnno> al = new ArrayList<visualizzaPilotiAnno>();
        JSONArray jsonArray = jsonObject.getJSONObject("MRData").getJSONObject("DriverTable").getJSONArray("Drivers");
        for(int i = 0; i<jsonArray.length(); i++){
            visualizzaPilotiAnno vPA = new visualizzaPilotiAnno(jsonArray.getJSONObject(i),i+1);
            al.add(vPA);
        }
        req.setAttribute("visualizzaPilotiAnno", al);
        req.getRequestDispatcher("/viewPilotiAnno.jsp").forward(req, resp);
    }

    public JSONObject connessione(HttpServletRequest req) throws IOException {
        String anno = req.getParameter("anno");
        String tipologia = req.getParameter("tipologiaSelect");
        url = new URL(apiToBuild+anno+"/"+tipologia+".json?limit=200");
        //System.out.println(url);
        connessione = (HttpURLConnection) url.openConnection();
        connessione.setRequestMethod("GET");
        connessione.setRequestProperty("Accept", "application/json");
        if (connessione.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP Error code : "
                    + connessione.getResponseCode());
        }
        String json = buildJSON(connessione.getInputStream());
        return new JSONObject(json);
    }

    public String buildJSON(InputStream inS) throws IOException {
        InputStreamReader in = new InputStreamReader(inS);
        BufferedReader br = new BufferedReader(in);
        //System.out.println(br);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            //System.out.println(sb);
            //System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
        //System.out.println(sb);
        br.close();
        return sb.toString();
    }
}
