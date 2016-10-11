package vn.ahaay.ambacsi.api.ambacsi.schedule;

import android.support.annotation.NonNull;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import vn.ahaay.ambacsi.api.ambacsi.Task;
import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiAuth;
import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiAuthException;
import vn.ahaay.ambacsi.api.ambacsi.constant.ApiUrl;
import vn.ahaay.ambacsi.api.ambacsi.helper.JSONParse;
import vn.ahaay.ambacsi.api.model.appointment_schedule.Schedule;

/**
 * Status: ON DEBUGGING
 * Created by SONY on 10-Sep-16.
 * Last updated by Cat Can on 11-Oct-16.
 */
public class AmBacSiSchedule {
    private static final String TAG = "AmBacSiSchedule";

    public static Task<List<Schedule>> downloadSchedule() throws AmBacSiAuthException {
        final String __token = AmBacSiAuth.getLoginAccount().getToken();

        return new Task<List<Schedule>>() {
            @Override
            protected List<Schedule> doInBackground(Void... _voids) {
                // URL
                String __url = ApiUrl.PREFIX_URL + ApiUrl.URL_GET_SCHEDULE;

                // Creating HTTP client
                HttpClient __httpClient = new DefaultHttpClient();

                // Creating HTTP Post
                HttpGet __httpGet = new HttpGet(__url);

                // Url Encoding the POST parameters
                __httpGet.addHeader("Authorization", "Token " + __token);

                ArrayList<Schedule> __schedules = new ArrayList<>();

                // Making HTTP Request
                try {
                    HttpResponse __httpResponse = __httpClient.execute(__httpGet);

                    if (__httpResponse.getStatusLine().getStatusCode() == 200) {
                        JSONObject __result = JSONParse.parse(__httpResponse);

                        JSONArray __results = __result.getJSONArray("results");
                        for (int i = 0; i < __results.length(); i++) {
                            JSONObject __temp = __results.getJSONObject(i);
                            __schedules.add(new Schedule(__temp));

//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("doctor"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("doctor"));
//                                }
//                                if (__temp.getJSONObject("clinical_center") != null && __cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("clinical_center"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("clinical_center"));
//                                }
                        }
                    }
                } catch (IOException | JSONException | ParseException _e) {
                    // writing error to Log
                    Log.e(TAG + ":dScd", _e.getMessage());
                }

                return __schedules;
            }
        };
    }

    public static Task<Schedule> getSchedule(@NonNull final String _id) throws AmBacSiAuthException {
        final String __token = AmBacSiAuth.getLoginAccount().getToken();

        return new Task<Schedule>() {
            @Override
            protected Schedule doInBackground(Void... _voids) {
                // URL
                String __url = ApiUrl.PREFIX_URL + String.format(ApiUrl.URL_GET_SCHEDULE_ID, _id);

                // Creating HTTP client
                HttpClient __httpClient = new DefaultHttpClient();

                // Creating HTTP Get
                HttpGet __httpGet = new HttpGet(__url);

                // Url Encoding the POST parameters
                __httpGet.addHeader("Authorization", "Token " + __token);

                // Making HTTP Request
                try {
                    HttpResponse __httpResponse = __httpClient.execute(__httpGet);

                    if (__httpResponse.getStatusLine().getStatusCode() == 200) {
                        this.isComplete = true;
                        JSONObject __result = JSONParse.parse(__httpResponse);
                        return new Schedule(__result);
                    } else {
                        this.isComplete = false;
                        this.mException = new AmBacSiAuthException(
                                AmBacSiAuthException.ERROR_CODE_SERVER_STATUS_CODE,
                                AmBacSiAuthException.ERROR_CODE_SERVER_STATUS_CODE
                        );
                        return null;
                    }
                } catch (IOException | JSONException | ParseException _e) {
                    // writing error to Log
                    Log.e(TAG + ":gScdId", _e.getMessage());

                    this.isComplete = false;
                    this.mException = _e;
                    return null;
                }
            }
        };
    }

    public static Task<String> createSchedule(@NonNull final ScheduleChangeRequest _schedule) throws AmBacSiAuthException {
        final String __token = AmBacSiAuth.getLoginAccount().getToken();

        return new Task<String>() {
            @Override
            protected String doInBackground(Void... _voids) {
                // URL
                String __url = ApiUrl.PREFIX_URL + ApiUrl.URL_CREATE_SCHEDULE;

                // Creating HTTP client
                HttpClient __httpClient = new DefaultHttpClient();

                // Creating HTTP Post
                HttpPost __httpPost = new HttpPost(__url);

                // Url Encoding the POST parameters
                __httpPost.addHeader("Content-Type", "application/json");
                __httpPost.addHeader("Authorization", "Token " + __token);

                // Making HTTP Request
                try {
                    __httpPost.setEntity(new StringEntity(_schedule.getHttpRequestBody()));
                    HttpResponse __httpResponse = __httpClient.execute(__httpPost);

                    if (__httpResponse.getStatusLine().getStatusCode() == 201) {
                        this.isComplete = true;

                        JSONObject __result = JSONParse.parse(__httpResponse);
                        return __result.getString("id");
                    } else {
                        this.isComplete = false;
                        this.mException = new AmBacSiAuthException(
                                AmBacSiAuthException.ERROR_CODE_SERVER_STATUS_CODE,
                                AmBacSiAuthException.ERROR_CODE_SERVER_STATUS_CODE
                        );
                        return null;
                    }
                } catch (IOException | JSONException _e) {
                    // writing error to Log
                    Log.e(TAG + ":cScd", _e.getMessage());

                    this.isComplete = false;
                    this.mException = _e;
                    return null;
                }
            }
        };
    }

    public static Task<Void> deleteSchedule(@NonNull final String _id) throws AmBacSiAuthException {
        final String __token = AmBacSiAuth.getLoginAccount().getToken();

        return new Task<Void>() {
            @Override
            protected Void doInBackground(Void... _voids) {
                // URL
                String __url = ApiUrl.PREFIX_URL + String.format(ApiUrl.URL_DELETE_SCHEDULE, _id);

                // Creating HTTP client
                HttpClient __httpClient = new DefaultHttpClient();

                // Creating HTTP Delete
                HttpDelete __httpDelete = new HttpDelete(__url);

                // Url Encoding the POST parameters
                __httpDelete.addHeader("Authorization", "Token " + __token);

                // Making HTTP Request
                try {
                    HttpResponse __httpResponse = __httpClient.execute(__httpDelete);

                    if (__httpResponse.getStatusLine().getStatusCode() == 204) {
                        this.isComplete = true;
                    } else {
                        this.isComplete = false;
                        this.mException = new AmBacSiAuthException(
                                AmBacSiAuthException.ERROR_CODE_SERVER_STATUS_CODE,
                                AmBacSiAuthException.ERROR_CODE_SERVER_STATUS_CODE
                        );
                    }
                } catch (IOException _e) {
                    // writing error to Log
                    Log.e(TAG + ":delScd", _e.getMessage());

                    this.isComplete = false;
                    this.mException = _e;
                }

                return null;
            }
        };
    }

    public static Task<Void> updateSchedule(@NonNull final ScheduleChangeRequest _schedule) throws AmBacSiAuthException {
        final String __token = AmBacSiAuth.getLoginAccount().getToken();

        return new Task<Void>() {
            @Override
            protected Void doInBackground(Void... _voids) {
                // URL
                String __url = ApiUrl.PREFIX_URL + String.format(ApiUrl.URL_UPDATE_SCHEDULE, _schedule.getId());

                // Creating HTTP client
                HttpClient __httpClient = new DefaultHttpClient();

                // Creating HTTP Put
                HttpPut __httpPut = new HttpPut(__url);

                // Url Encoding the Put parameters
                __httpPut.addHeader("Content-Type", "application/json");
                __httpPut.addHeader("Authorization", "Token " + __token);

                // Making HTTP Request
                try {
                    __httpPut.setEntity(new StringEntity(_schedule.getHttpRequestBody()));
                    HttpResponse __httpResponse = __httpClient.execute(__httpPut);

                    if (__httpResponse.getStatusLine().getStatusCode() == 200) {
                        this.isComplete = true;
                    } else {
                        this.isComplete = false;
                        this.mException = new AmBacSiAuthException(
                                AmBacSiAuthException.ERROR_CODE_SERVER_STATUS_CODE,
                                AmBacSiAuthException.ERROR_CODE_SERVER_STATUS_CODE
                        );
                    }
                } catch (IOException _e) {
                    // writing error to Log
                    Log.e(TAG + ":uScd", _e.getMessage());

                    this.isComplete = false;
                    this.mException = _e;
                }

                return null;
            }
        };
    }
}
