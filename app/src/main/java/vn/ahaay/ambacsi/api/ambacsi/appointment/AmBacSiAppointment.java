package vn.ahaay.ambacsi.api.ambacsi.appointment;

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
import vn.ahaay.ambacsi.api.ambacsi.helper.Synchronized;
import vn.ahaay.ambacsi.api.localdb.appointment_schedule.AppointmentDBHandler;
import vn.ahaay.ambacsi.api.localdb.profile.CacheProfileDBHandler;
import vn.ahaay.ambacsi.api.model.appointment_schedule.Appointment;
import vn.ahaay.ambacsi.api.ambacsi.profile.AmBacSiCacheProfile;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
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
public class AmBacSiAppointment {
    private static final String TAG = "AmBacSiAppointment";
    private static final String DIRECTION_PREV = "prev";
    private static final String DIRECTION_NEXT = "next";
    private static boolean nextAppointmentDone = false;
    private static boolean prevAppointmentDone = false;

    public static void downloadAppointment(@Nullable String _link, @Nullable String _direction) throws AmBacSiAuthException {
        final String __token = AmBacSiAuth.getLoginAccount().getToken();

        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... _strings) {
                if (_strings[0] == null) {
                    // URL
                    String __url = ApiUrl.PREFIX_URL + ApiUrl.URL_GET_APPOINTMENT;

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
                            AppointmentDBHandler __dbHandler = new AppointmentDBHandler(GlobalContext.getContext(), null);
                            CacheProfileDBHandler __cacheProfileDBHandler = new CacheProfileDBHandler(GlobalContext.getContext(), null);
                            for (int i = 0; i < __results.length(); i++) {
                                JSONObject __temp = __results.getJSONObject(i);
                                __dbHandler.addAppointment(__temp);

//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("account"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("account"));
//                                }
//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("doctor"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("doctor"));
//                                }
//                                if (__temp.getJSONObject("clinical_center") != null && __cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("clinical_center"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("clinical_center"));
//                                }
                            }

                            String __next =  __result.getString("next");
                            if (__next != null) {
                                downloadAppointment(__next, DIRECTION_NEXT);
                            } else {
                                nextAppointmentDone = true;
                            }

                            String __prev =  __result.getString("previous");
                            if (__prev != null) {
                                downloadAppointment(__prev, DIRECTION_PREV);
                            } else if (nextAppointmentDone) {
                                Synchronized.setScheduleSynchronized(true);
                            } else {
                                prevAppointmentDone = true;
                            }
                        }
                    } catch (IOException | JSONException | AmBacSiAuthException _e) {
                        // writing error to Log
                        Log.e(TAG + ":dApt", _e.getMessage());
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
                            AppointmentDBHandler __dbHandler = new AppointmentDBHandler(GlobalContext.getContext(), null);
                            CacheProfileDBHandler __cacheProfileDBHandler = new CacheProfileDBHandler(GlobalContext.getContext(), null);
                            for (int i = 0; i < __results.length(); i++) {
                                JSONObject __temp = __results.getJSONObject(i);
                                __dbHandler.addAppointment(__temp);

//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("account"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("account"));
//                                }
//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("doctor"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("doctor"));
//                                }
//                                if (__temp.getJSONObject("clinical_center") != null && __cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("clinical_center"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("clinical_center"));
//                                }
                            }

                            String __prev =  __result.getString("previous");
                            if (__prev != null) {
                                downloadAppointment(__prev, DIRECTION_PREV);
                            } else if (nextAppointmentDone) {
                                Synchronized.setScheduleSynchronized(true);
                            } else {
                                prevAppointmentDone = true;
                            }
                        }
                    } catch (IOException | JSONException | AmBacSiAuthException _e) {
                        // writing error to Log
                        Log.e(TAG + ":dApt", _e.getMessage());
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
                            AppointmentDBHandler __dbHandler = new AppointmentDBHandler(GlobalContext.getContext(), null);
                            CacheProfileDBHandler __cacheProfileDBHandler = new CacheProfileDBHandler(GlobalContext.getContext(), null);
                            for (int i = 0; i < __results.length(); i++) {
                                JSONObject __temp = __results.getJSONObject(i);
                                __dbHandler.addAppointment(__temp);

//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("account"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("account"));
//                                }
//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("doctor"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("doctor"));
//                                }
//                                if (__temp.getJSONObject("clinical_center") != null && __cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("clinical_center"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("clinical_center"));
//                                }
                            }

                            String __next =  __result.getString("next");
                            if (__next != null) {
                                downloadAppointment(__next, DIRECTION_NEXT);
                            } else if (prevAppointmentDone) {
                                Synchronized.setScheduleSynchronized(true);
                            }  else {
                                nextAppointmentDone = true;
                            }
                        }
                    } catch (IOException | JSONException | AmBacSiAuthException _e) {
                        // writing error to Log
                        Log.e(TAG + ":dApt", _e.getMessage());
                    }
                }
                return null;
            }
        }.execute(_link, _direction);
    }

    public static Task<Appointment> getAppointment(@NonNull final String _id) throws AmBacSiAuthException {
        final String __token = AmBacSiAuth.getLoginAccount().getToken();

        return new Task<Appointment>() {
            @Override
            protected Appointment doInBackground(Void... _voids) {
                // URL
                String __url = ApiUrl.PREFIX_URL + String.format(ApiUrl.URL_GET_APPOINTMENT_ID, _id);

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
                        return new Appointment(__result);
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
                    Log.e(TAG + ":dApt", _e.getMessage());

                    this.isComplete = false;
                    this.mException = _e;
                    return null;
                }
            }
        };
    }

    public static Task<String> createAppointment(@NonNull final Appointment _appointment) throws AmBacSiAuthException {
        final String __token = AmBacSiAuth.getLoginAccount().getToken();

        return new Task<String>() {
            @Override
            protected String doInBackground(Void... _voids) {
                // URL
                String __url = ApiUrl.PREFIX_URL + ApiUrl.URL_CREATE_APPOINTMENT;

                // Creating HTTP client
                HttpClient __httpClient = new DefaultHttpClient();

                // Creating HTTP Post
                HttpPost __httpPost = new HttpPost(__url);

                // Url Encoding the POST parameters
                __httpPost.addHeader("Content-Type", "application/json");
                __httpPost.addHeader("Authorization", "Token " + __token);

                // Making HTTP Request
                try {
                    __httpPost.setEntity(new StringEntity(_appointment.toString()));
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
                    Log.e(TAG + ":gApt", _e.getMessage());

                    this.isComplete = false;
                    this.mException = _e;
                    return null;
                }
            }
        };
    }

    public static Task<Void> deleteAppointment(@NonNull final String _id) throws AmBacSiAuthException {
        final String __token = AmBacSiAuth.getLoginAccount().getToken();

        return new Task<Void>() {
            @Override
            protected Void doInBackground(Void... _voids) {
                // URL
                String __url = ApiUrl.PREFIX_URL + String.format(ApiUrl.URL_DELETE_APPOINTMENT, _id);

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
                    Log.e(TAG + ":del", _e.getMessage());

                    this.isComplete = false;
                    this.mException = _e;
                }

                return null;
            }
        };
    }
}
