package vn.ahaay.ambacsi.api.ambacsi.helper;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by SONY on 28-Jul-16.
 */
public final class JSONParse {
    private static final String TAG = "JSONParse";
    public static JSONObject parse(HttpResponse _httpResponse){
        String result = "";
        JSONObject jsonObject = null;

        //convert response to string
        try{
            HttpEntity __httpEntity = _httpResponse.getEntity();
            InputStream __inputStream = __httpEntity.getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(__inputStream, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            __inputStream.close();
            result=sb.toString();
        }catch(Exception e){
            Log.e(TAG + ":parse", "Error converting result " + e.toString());
        }

        //try parse the string to a JSON object
        try{
            jsonObject = new JSONObject(result);
        }catch(JSONException e){
            Log.e(TAG + ":parse", "Error parsing data "+e.toString());
        }

        return jsonObject;
    }
}
