import org.json.*;

public class visualizzaGPAnno {
    String round, urlWikipediaNomeGara, nomeGara, urlWikipediaNomeGranPremio, nomeGranPremio, località, paese, data, ora;
    public visualizzaGPAnno(JSONObject jsO){
        round =  jsO.getString("round");
        urlWikipediaNomeGara = jsO.getString("url");
        nomeGara = jsO.getString("raceName");
        urlWikipediaNomeGranPremio = jsO.getJSONObject("Circuit").getString("url");
        nomeGranPremio = jsO.getJSONObject("Circuit").getString("circuitName");
        località = jsO.getJSONObject("Circuit").getJSONObject("Location").getString("locality");
        paese = jsO.getJSONObject("Circuit").getJSONObject("Location").getString("country");
        data = jsO.getString("date");
        try{
            ora = jsO.getString("time");
            ora = ora.substring(0, ora.length() -1);
        }catch(JSONException e){
            ora = "/";
        }
        data = invertiData(data);
    }

    public String getRound() {
        return round;
    }

    public String getUrlWikipediaNomeGara() {
        return urlWikipediaNomeGara;
    }

    public String getNomeGara() {
        return nomeGara;
    }

    public String getUrlWikipediaNomeGranPremio() {
        return urlWikipediaNomeGranPremio;
    }

    public String getNomeGranPremio() {
        return nomeGranPremio;
    }

    public String getLocalità() {
        return località;
    }

    public String getPaese() {
        return paese;
    }

    public String getData() {
        return data;
    }

    public String getOra() {
        return ora;
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
}
