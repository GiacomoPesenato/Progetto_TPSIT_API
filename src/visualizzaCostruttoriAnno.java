import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class visualizzaCostruttoriAnno {
    String urlWikipediaNomeSquadra, nomeSquadra, nazionalitàSquadra, nazione;
    int codice;
    private HttpURLConnection connessione;
    private URL url;
    public visualizzaCostruttoriAnno(JSONObject jsonObject, int codiceSquadra) throws IOException {
        codice = codiceSquadra;
        urlWikipediaNomeSquadra = jsonObject.getString("url");
        nomeSquadra = jsonObject.getString("name");
        nazionalitàSquadra= jsonObject.getString("nationality");
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(nazionalitàSquadra);
        if(nazionalitàSquadra.equalsIgnoreCase("french")){
            nazione = "France";
        }else if(nazionalitàSquadra.equalsIgnoreCase("indian")) {
            nazione = "India";
        }else if(nazionalitàSquadra.equalsIgnoreCase("South African")) {
            nazione = "South Africa";
        }else if(nazionalitàSquadra.equalsIgnoreCase("Rhodesian")) {
            nazione = "United Kingdom";
        }else if(nazionalitàSquadra.contains(" ") || m.find() && !nazionalitàSquadra.equalsIgnoreCase("South African")) {
            String[] parts;
            parts = nazionalitàSquadra.contains(" ") ? nazionalitàSquadra.split(" ") : nazionalitàSquadra.split("[^a-z0-9A-Z ]");
            nazione = parts[parts.length-1];
            System.out.println("[Scuderia]"+nazione);
            nazione = ricercaNazione(nazione);
            System.out.println("[Scuderia]"+nazione);
        }else if(nazionalitàSquadra.equalsIgnoreCase("American")) {
            nazione = "United States";
        }else{
            nazione = ricercaNazione(nazionalitàSquadra);
        }
        System.out.println("[Scuderia]"+nazione);

    }

    public String ricercaNazione(String n) throws IOException {
        url = new URL("https://restcountries.com/v3.1/demonym/"+n);
        connessione = (HttpURLConnection) url.openConnection();
        gestioneApi gA = new gestioneApi();
        String json = gA.buildJSON(connessione.getInputStream());
        json = json.substring(1);
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.getJSONObject("name").getString("common");
    }

    public int getCodice() {
        return codice;
    }

    public String getUrlWikipediaNomeSquadra() {
        return urlWikipediaNomeSquadra;
    }

    public String getNomeSquadra() {
        return nomeSquadra;
    }

    public String getNazionalitàSquadra() {
        return nazionalitàSquadra;
    }

    public String getNazione() {
        return nazione;
    }
}
