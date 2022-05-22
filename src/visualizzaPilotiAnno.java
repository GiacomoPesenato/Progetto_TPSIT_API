import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class visualizzaPilotiAnno {

    int numero;
    String urlWikipediaNomePilota, nome, cognome, dataNascita, nazionalità, nazione;
    URL url;
    HttpURLConnection connessione;


    public visualizzaPilotiAnno(JSONObject jsonObject, int codicePilota) throws IOException {
        numero = codicePilota;
        urlWikipediaNomePilota = jsonObject.getString("url");
        nome = jsonObject.getString("givenName");
        cognome = jsonObject.getString("familyName");
        dataNascita = jsonObject.getString("dateOfBirth");
        nazionalità = jsonObject.getString("nationality");
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(nazionalità);
        if(nazionalità.equalsIgnoreCase("french")){
            nazione = "France";
        }else if(nazionalità.equalsIgnoreCase("indian")) {
            nazione = "India";
        }else if(nazionalità.equalsIgnoreCase("South African")) {
                nazione = "South Africa";
        }else if(nazionalità.equalsIgnoreCase("Rhodesian")) {
            nazione = "United Kingdom";
        }else if(nazionalità.contains(" ") || m.find() && !nazionalità.equalsIgnoreCase("South African")) {
            String[] parts;
            parts = nazionalità.contains(" ") ? nazionalità.split(" ") : nazionalità.split("[^a-z0-9A-Z ]");
            nazione = parts[parts.length-1];
            System.out.println("[Pilota]"+nazione);
            nazione = ricercaNazione(nazione);
            System.out.println("[Pilota]"+nazione);
        }else if(nazionalità.equalsIgnoreCase("American")) {
            nazione = "United States";
        }else{
            nazione = ricercaNazione(nazionalità);
        }
        System.out.println("[Pilota]"+nazione);
        dataNascita = invertiData(dataNascita);
    }

    public String ricercaNazione(String n) throws IOException {
        try {
            url = new URL("https://restcountries.com/v3.1/demonym/" + n);
            connessione = (HttpURLConnection) url.openConnection();
            gestioneApi gA = new gestioneApi();
            String json = gA.buildJSON(connessione.getInputStream());
            json = json.substring(1);
            JSONObject jsonObject = new JSONObject(json);
            return jsonObject.getJSONObject("name").getString("common");
        }catch(Exception e){
            e.printStackTrace();
            return "Immagine non disponibile";
        }
    }

    public String invertiData(String data){
        String[] parts =  data.split("-");
        StringBuilder dataBuilder = new StringBuilder();
        for(int i = parts.length-1; i>=0; i--){
            if(i == 0){
                dataBuilder.append(parts[i]);
            }else{
                dataBuilder.append(parts[i]).append("-");
            }
        }
        return dataBuilder.toString();
    }

    public int getNumero() {
        return numero;
    }

    public String getUrlWikipediaNomePilota() {
        return urlWikipediaNomePilota;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public String getNazionalità() {
        return nazionalità;
    }

    public String getNazione() {
        return nazione;
    }
}
