package vn.ahaay.ambacsi.api.ambacsi.schedule;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import vn.ahaay.ambacsi.api.ambacsi.constant.ApiUrl;
import vn.ahaay.ambacsi.api.GlobalContext;
import vn.ahaay.ambacsi.api.ambacsi.Task;
import vn.ahaay.ambacsi.api.ambacsi.helper.JSONParse;
import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiAuth;
import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiAuthException;
import vn.ahaay.ambacsi.api.localdb.profile.CacheProfileDBHandler;
import vn.ahaay.ambacsi.api.localdb.appointment_schedule.ScheduleDBHandler;
import vn.ahaay.ambacsi.api.model.appointment_schedule.Schedule;
import vn.ahaay.ambacsi.api.ambacsi.profile.AmBacSiCacheProfile;
import vn.ahaay.ambacsi.api.ambacsi.helper.Synchronized;

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

/**
 * Created by Can on 9/2/2016.
 */
public class AmBacSiSchedule {
    private static final String TAG = "AmBacSiSchedule";
    private static final String DIRECTION_PREV = "prev";
    private static final String DIRECTION_NEXT = "next";
    private static boolean nextScheduleDone = false;
    private static boolean prevScheduleDone = false;

    public static void downloadSchedule(@Nullable String _link, @Nullable String _direction) throws AmBacSiAuthException {
        final String __token = AmBacSiAuth.getLoginAccount().getToken();

        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... _strings) {
                if (_strings[0] == null) {
                    // URL
                    String __url = ApiUrl.PREFIX_URL + ApiUrl.URL_GET_SCHEDULE;

                    // Creating HTTP client
                    HttpClient __httpClient = new DefaultHttpClient();

                    // Creating HTTP Post
                    HttpGet __httpGet = new HttpGet(__url);

                    // Url Encoding the POST parameters
                    __httpGet.addHeader("Authorization", "Token " + __token);

                    // Making HTTP Request
                    try {
                        HttpResponse __httpResponse = __httpClient.execute(__httpGet);

                        if (__httpResponse.getStatusLine().getStatusCode() == 200) {
                            JSONObject __result = JSONParse.parse(__httpResponse);

                            JSONArray __results = __result.getJSONArray("results");
                            ScheduleDBHandler __dbHandler = new ScheduleDBHandler(GlobalContext.getContext(), null);
                            CacheProfileDBHandler __cacheProfileDBHandler = new CacheProfileDBHandler(GlobalContext.getContext(), null);
                            for (int i = 0; i < __results.length(); i++) {
                                JSONObject __temp = __results.getJSONObject(i);
                                __dbHandler.addSchedule(__temp);

//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("doctor"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("doctor"));
//                                }
//                                if (__temp.getJSONObject("clinical_center") != null && __cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("clinical_center"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("clinical_center"));
//                                }
                            }

                            String __next =  __result.getString("next");
                            if (__next != null) {
                                downloadSchedule(__next, DIRECTION_NEXT);
                            } else {
                                nextScheduleDone = true;
                            }

                            String __prev =  __result.getString("previous");
                            if (__prev != null) {
                                downloadSchedule(__prev, DIRECTION_PREV);
                            } else if (nextScheduleDone) {
                                Synchronized.setScheduleSynchronized(true);
                            } else {
                                prevScheduleDone = true;
                            }
                        }
                    } catch (IOException | JSONException | AmBacSiAuthException _e) {
                        // writing error to Log
                        Log.e(TAG + ":dScd", _e.getMessage());
                    }
                } else if (_strings[1].equals(DIRECTION_PREV)) {
                    // URL
                    String __url = _strings[0];

                    // Creating HTTP client
                    HttpClient __httpClient = new DefaultHttpClient();

                    // Creating HTTP Post
                    HttpGet __httpGet = new HttpGet(__url);

                    // Url Encoding the POST parameters
                    __httpGet.addHeader("Authorization", "Token " + __token);

                    // Making HTTP Request
                    try {
                        HttpResponse __httpResponse = __httpClient.execute(__httpGet);

                        if (__httpResponse.getStatusLine().getStatusCode() == 200) {
                            JSONObject __result = JSONParse.parse(__httpResponse);

                            JSONArray __results = __result.getJSONArray("results");
                            ScheduleDBHandler __dbHandler = new ScheduleDBHandler(GlobalContext.getContext(), null);
                            CacheProfileDBHandler __cacheProfileDBHandler = new CacheProfileDBHandler(GlobalContext.getContext(), null);
                            for (int i = 0; i < __results.length(); i++) {
                                JSONObject __temp = __results.getJSONObject(i);
                                __dbHandler.addSchedule(__temp);

//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("doctor"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("doctor"));
//                                }
//                                if (__temp.getJSONObject("clinical_center") != null && __cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("clinical_center"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("clinical_center"));
//                                }
                            }

                            String __prev =  __result.getString("previous");
                            if (__prev != null) {
                                downloadSchedule(__prev, DIRECTION_PREV);
                            } else if (nextScheduleDone) {
                                Synchronized.setScheduleSynchronized(true);
                            } else {
                                prevScheduleDone = true;
                            }
                        }
                    } catch (IOException | JSONException | AmBacSiAuthException _e) {
                        // writing error to Log
                        Log.e(TAG + ":dScd", _e.getMessage());
                    }
                } else {
                    // URL
                    String __url = _strings[0];

                    // Creating HTTP client
                    HttpClient __httpClient = new DefaultHttpClient();

                    // Creating HTTP Post
                    HttpGet __httpGet = new HttpGet(__url);

                    // Url Encoding the POST parameters
                    __httpGet.addHeader("Authorization", "Token " + __token);

                    // Making HTTP Request
                    try {
                        HttpResponse __httpResponse = __httpClient.execute(__httpGet);

                        if (__httpResponse.getStatusLine().getStatusCode() == 200) {
                            JSONObject __result = JSONParse.parse(__httpResponse);

                            JSONArray __results = __result.getJSONArray("results");
                            ScheduleDBHandler __dbHandler = new ScheduleDBHandler(GlobalContext.getContext(), null);
                            CacheProfileDBHandler __cacheProfileDBHandler = new CacheProfileDBHandler(GlobalContext.getContext(), null);
                            for (int i = 0; i < __results.length(); i++) {
                                JSONObject __temp = __results.getJSONObject(i);
                                __dbHandler.addSchedule(__temp);

//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("doctor"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("doctor"));
//                                }
//                                if (__temp.getJSONObject("clinical_center") != null && __cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("clinical_center"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("clinical_center"));
//                                }
                            }

                            String __next =  __result.getString("next");
                            if (__next != null) {
                                    downloadSchedule(__next, DIRECTION_NEXT);
                            } else if (prevScheduleDone) {
                                Synchronized.setScheduleSynchronized(true);
                            }  else {
                                nextScheduleDone = true;
                            }
                        }
                    } catch (IOException | JSONException | AmBacSiAuthException _e) {
                        // writing error to Log
                        Log.e(TAG + ":dScd", _e.getMessage());
                    }
                }
                return null;
            }
        }.execute(_link, _direction);
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

    public static Task<String> createSchedule(@NonNull final Schedule _schedule) throws AmBacSiAuthException {
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
                    __httpPost.setEntity(new StringEntity(_schedule.toString()));
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

    public static Task<Void> updateSchedule(@NonNull final Schedule _schedule) throws AmBacSiAuthException {
        final String __token = AmBacSiAuth.getLoginAccount().getToken();

        return new Task<Void>() {
            @Override
            protected Void doInBackground(Void... _voids) {
                // URL
                String __url = ApiUrl.PREFIX_URL + String.format(ApiUrl.URL_UPDATE_SCHEDULE, _schedule.getServerId());

                // Creating HTTP client
                HttpClient __httpClient = new DefaultHttpClient();

                // Creating HTTP Put
                HttpPut __httpPut = new HttpPut(__url);

                // Url Encoding the Put parameters
                __httpPut.addHeader("Content-Type", "application/json");
                __httpPut.addHeader("Authorization", "Token " + __token);

                // Making HTTP Request
                try {
                    __httpPut.setEntity(new StringEntity(_schedule.toString()));
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
