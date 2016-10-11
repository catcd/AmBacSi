package vn.ahaay.ambacsi.api.ambacsi.notification;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiAuth;
import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiAuthException;
import vn.ahaay.ambacsi.api.ambacsi.constant.ApiUrl;

/**
 * Created by Can on 17-Sep-16.
 */
public class AmbacsiNotification {
    private static final String TAG = "ANotification";
    private static final String DIRECTION_PREV = "prev";
    private static final String DIRECTION_NEXT = "next";
    private static boolean nextNotificationDone = false;
    private static boolean prevNotificationDone = false;

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param _fcmToken The new token.
     */
    public static void sendRegistrationToServer(String _fcmToken) throws AmBacSiAuthException {
        final String __userToken = AmBacSiAuth.getLoginAccount().getToken();
        // URL
        String __url = ApiUrl.PREFIX_URL + ApiUrl.URL_REGISTER_FCM_TOKEN;

        // Creating HTTP client
        HttpClient __httpClient = new DefaultHttpClient();

        // Creating HTTP Post
        HttpPost __httpPost = new HttpPost(__url);

        // Url Encoding the POST parameters
        __httpPost.addHeader("Content-Type", "application/json");
        __httpPost.addHeader("Authorization", "Token " + __userToken);

        // Making HTTP Request
        try {
            JSONObject __json = new JSONObject();
            __json.put("token", _fcmToken);
            __httpPost.setEntity(new StringEntity(__json.toString()));
            HttpResponse __httpResponse = __httpClient.execute(__httpPost);

            if (__httpResponse.getStatusLine().getStatusCode() != 201) {
                throw new AmBacSiAuthException(
                        AmBacSiAuthException.ERROR_CODE_SERVER_STATUS_CODE,
                        AmBacSiAuthException.ERROR_CODE_SERVER_STATUS_CODE
                );
            }
        } catch (IOException | JSONException _e) {
            // writing error to Log
            Log.e(TAG + ":fcmToken", _e.getMessage());
        }
    }

}
