package vn.ahaay.ambacsi.api.ambacsi.relationship;

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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;

import vn.ahaay.ambacsi.api.ambacsi.Task;
import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiAuth;
import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiAuthException;
import vn.ahaay.ambacsi.api.ambacsi.constant.ApiUrlConstant;
import vn.ahaay.ambacsi.api.ambacsi.helper.JSONParse;
import vn.ahaay.ambacsi.api.ambacsi.model.FriendGroup;
import vn.ahaay.ambacsi.api.ambacsi.model.FriendRequest;
import vn.ahaay.ambacsi.api.ambacsi.model.Relationship;

/**
 * Created by Can on 9/2/2016.
 */
public class AmBacSiRelationship {
    private static final String TAG = "Relationship";
//    private static final String DIRECTION_PREV = "prev";
//    private static final String DIRECTION_NEXT = "next";
//    private static boolean nextRelationshipDone = false;
//    private static boolean prevRelationshipDone = false;

//    public static void downloadFriendRequest(@Nullable String _link, @Nullable String _direction) throws AmBacSiAuthException {
//        final String __token = AmBacSiAuth.getLoginUser().getToken();
//
//        new AsyncTask<String, Void, Void>() {
//            @Override
//            protected Void doInBackground(String... _strings) {
//                if (_strings[0] == null) {
//                    // URL
//                    String __url = ApiUrlConstant.PREFIX_URL + ApiUrlConstant.URL_GET_FRIEND_REQUEST;
//
//                    // Creating HTTP client
//                    HttpClient __httpClient = new DefaultHttpClient();
//
//                    // Creating HTTP Post
//                    HttpGet __httpGet = new HttpGet(__url);
//
//                    // Url Encoding the POST parameters
//                    __httpGet.addHeader("Authorization", "Token " + __token);
//
//                    // Making HTTP Request
//                    try {
//                        HttpResponse __httpResponse = __httpClient.execute(__httpGet);
//
//                        if (__httpResponse.getStatusLine().getStatusCode() == 200) {
//                            JSONObject __result = JSONParse.parse(__httpResponse);
//
//                            JSONArray __results = __result.getJSONArray("results");
//                            AppointmentDBHandler __dbHandler = new AppointmentDBHandler(GlobalContext.getContext(), null);
//                            CacheProfileDBHandler __cacheProfileDBHandler = new CacheProfileDBHandler(GlobalContext.getContext(), null);
//                            for (int i = 0; i < __results.length(); i++) {
//                                JSONObject __temp = __results.getJSONObject(i);
//                                __dbHandler.addAppointment(__temp);
//
//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("account"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("account"));
//                                }
//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("doctor"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("doctor"));
//                                }
//                                if (__temp.getJSONObject("clinical_center") != null && __cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("clinical_center"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("clinical_center"));
//                                }
//                            }
//
//                            String __next =  __result.getString("next");
//                            if (__next != null) {
//                                downloadFriendRequest(__next, DIRECTION_NEXT);
//                            } else {
//                                nextAppointmentDone = true;
//                            }
//
//                            String __prev =  __result.getString("previous");
//                            if (__prev != null) {
//                                downloadFriendRequest(__prev, DIRECTION_PREV);
//                            } else if (nextAppointmentDone) {
//                                Synchronized.setScheduleSynchronized(true);
//                            } else {
//                                prevAppointmentDone = true;
//                            }
//                        }
//                    } catch (IOException | JSONException | AmBacSiAuthException _e) {
//                        // writing error to Log
//                        Log.e(TAG + ":dApt", _e.getMessage());
//                    }
//                } else if (_strings[1].equals(DIRECTION_PREV)) {
//                    // URL
//                    String __url = _strings[0];
//
//                    // Creating HTTP client
//                    HttpClient __httpClient = new DefaultHttpClient();
//
//                    // Creating HTTP Post
//                    HttpGet __httpGet = new HttpGet(__url);
//
//                    // Url Encoding the POST parameters
//                    __httpGet.addHeader("Authorization", "Token " + __token);
//
//                    // Making HTTP Request
//                    try {
//                        HttpResponse __httpResponse = __httpClient.execute(__httpGet);
//
//                        if (__httpResponse.getStatusLine().getStatusCode() == 200) {
//                            JSONObject __result = JSONParse.parse(__httpResponse);
//
//                            JSONArray __results = __result.getJSONArray("results");
//                            AppointmentDBHandler __dbHandler = new AppointmentDBHandler(GlobalContext.getContext(), null);
//                            CacheProfileDBHandler __cacheProfileDBHandler = new CacheProfileDBHandler(GlobalContext.getContext(), null);
//                            for (int i = 0; i < __results.length(); i++) {
//                                JSONObject __temp = __results.getJSONObject(i);
//                                __dbHandler.addAppointment(__temp);
//
//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("account"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("account"));
//                                }
//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("doctor"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("doctor"));
//                                }
//                                if (__temp.getJSONObject("clinical_center") != null && __cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("clinical_center"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("clinical_center"));
//                                }
//                            }
//
//                            String __prev =  __result.getString("previous");
//                            if (__prev != null) {
//                                downloadAppointment(__prev, DIRECTION_PREV);
//                            } else if (nextAppointmentDone) {
//                                Synchronized.setScheduleSynchronized(true);
//                            } else {
//                                prevAppointmentDone = true;
//                            }
//                        }
//                    } catch (IOException | JSONException | AmBacSiAuthException _e) {
//                        // writing error to Log
//                        Log.e(TAG + ":dApt", _e.getMessage());
//                    }
//                } else {
//                    // URL
//                    String __url = _strings[0];
//
//                    // Creating HTTP client
//                    HttpClient __httpClient = new DefaultHttpClient();
//
//                    // Creating HTTP Post
//                    HttpGet __httpGet = new HttpGet(__url);
//
//                    // Url Encoding the POST parameters
//                    __httpGet.addHeader("Authorization", "Token " + __token);
//
//                    // Making HTTP Request
//                    try {
//                        HttpResponse __httpResponse = __httpClient.execute(__httpGet);
//
//                        if (__httpResponse.getStatusLine().getStatusCode() == 200) {
//                            JSONObject __result = JSONParse.parse(__httpResponse);
//
//                            JSONArray __results = __result.getJSONArray("results");
//                            AppointmentDBHandler __dbHandler = new AppointmentDBHandler(GlobalContext.getContext(), null);
//                            CacheProfileDBHandler __cacheProfileDBHandler = new CacheProfileDBHandler(GlobalContext.getContext(), null);
//                            for (int i = 0; i < __results.length(); i++) {
//                                JSONObject __temp = __results.getJSONObject(i);
//                                __dbHandler.addAppointment(__temp);
//
//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("account"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("account"));
//                                }
//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("doctor"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("doctor"));
//                                }
//                                if (__temp.getJSONObject("clinical_center") != null && __cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("clinical_center"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("clinical_center"));
//                                }
//                            }
//
//                            String __next =  __result.getString("next");
//                            if (__next != null) {
//                                downloadAppointment(__next, DIRECTION_NEXT);
//                            } else if (prevAppointmentDone) {
//                                Synchronized.setScheduleSynchronized(true);
//                            }  else {
//                                nextAppointmentDone = true;
//                            }
//                        }
//                    } catch (IOException | JSONException | AmBacSiAuthException _e) {
//                        // writing error to Log
//                        Log.e(TAG + ":dApt", _e.getMessage());
//                    }
//                }
//                return null;
//            }
//        }.execute(_link, _direction);
//    }

    public static Task<String> createFriendRequest(@NonNull final FriendRequest _friendRequest) throws AmBacSiAuthException {
        final String __token = AmBacSiAuth.getLoginUser().getToken();

        return new Task<String>() {
            @Override
            protected String doInBackground(Void... _voids) {
                // URL
                String __url = ApiUrlConstant.PREFIX_URL + ApiUrlConstant.URL_CREATE_FRIEND_REQUEST;

                // Creating HTTP client
                HttpClient __httpClient = new DefaultHttpClient();

                // Creating HTTP Post
                HttpPost __httpPost = new HttpPost(__url);

                // Url Encoding the POST parameters
                __httpPost.addHeader("Content-Type", "application/json");
                __httpPost.addHeader("Authorization", "Token " + __token);

                // Making HTTP Request
                try {
                    __httpPost.setEntity(new StringEntity(_friendRequest.toCreateString()));
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
                    Log.e(TAG + ":createFR", _e.getMessage());

                    this.isComplete = false;
                    this.mException = _e;
                    return null;
                }
            }
        };
    }

    public static Task<Void> deleteFriendRequest(@NonNull final String _id) throws AmBacSiAuthException {
        final String __token = AmBacSiAuth.getLoginUser().getToken();

        return new Task<Void>() {
            @Override
            protected Void doInBackground(Void... _voids) {
                // URL
                String __url = ApiUrlConstant.PREFIX_URL + String.format(ApiUrlConstant.URL_DELETE_FRIEND_REQUEST, _id);

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
                    Log.e(TAG + ":deleteFR", _e.getMessage());

                    this.isComplete = false;
                    this.mException = _e;
                }

                return null;
            }
        };
    }

//    public static void downloadRelationship(@Nullable String _link, @Nullable String _direction) throws AmBacSiAuthException {
//        final String __token = AmBacSiAuth.getLoginUser().getToken();
//
//        new AsyncTask<String, Void, Void>() {
//            @Override
//            protected Void doInBackground(String... _strings) {
//                if (_strings[0] == null) {
//                    // URL
//                    String __url = ApiUrlConstant.PREFIX_URL + ApiUrlConstant.URL_GET_FRIEND_REQUEST;
//
//                    // Creating HTTP client
//                    HttpClient __httpClient = new DefaultHttpClient();
//
//                    // Creating HTTP Post
//                    HttpGet __httpGet = new HttpGet(__url);
//
//                    // Url Encoding the POST parameters
//                    __httpGet.addHeader("Authorization", "Token " + __token);
//
//                    // Making HTTP Request
//                    try {
//                        HttpResponse __httpResponse = __httpClient.execute(__httpGet);
//
//                        if (__httpResponse.getStatusLine().getStatusCode() == 200) {
//                            JSONObject __result = JSONParse.parse(__httpResponse);
//
//                            JSONArray __results = __result.getJSONArray("results");
//                            AppointmentDBHandler __dbHandler = new AppointmentDBHandler(GlobalContext.getContext(), null);
//                            CacheProfileDBHandler __cacheProfileDBHandler = new CacheProfileDBHandler(GlobalContext.getContext(), null);
//                            for (int i = 0; i < __results.length(); i++) {
//                                JSONObject __temp = __results.getJSONObject(i);
//                                __dbHandler.addAppointment(__temp);
//
//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("account"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("account"));
//                                }
//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("doctor"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("doctor"));
//                                }
//                                if (__temp.getJSONObject("clinical_center") != null && __cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("clinical_center"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("clinical_center"));
//                                }
//                            }
//
//                            String __next =  __result.getString("next");
//                            if (__next != null) {
//                                downloadRelationship(__next, DIRECTION_NEXT);
//                            } else {
//                                nextAppointmentDone = true;
//                            }
//
//                            String __prev =  __result.getString("previous");
//                            if (__prev != null) {
//                                downloadRelationship(__prev, DIRECTION_PREV);
//                            } else if (nextAppointmentDone) {
//                                Synchronized.setScheduleSynchronized(true);
//                            } else {
//                                prevAppointmentDone = true;
//                            }
//                        }
//                    } catch (IOException | JSONException | AmBacSiAuthException _e) {
//                        // writing error to Log
//                        Log.e(TAG + ":dApt", _e.getMessage());
//                    }
//                } else if (_strings[1].equals(DIRECTION_PREV)) {
//                    // URL
//                    String __url = _strings[0];
//
//                    // Creating HTTP client
//                    HttpClient __httpClient = new DefaultHttpClient();
//
//                    // Creating HTTP Post
//                    HttpGet __httpGet = new HttpGet(__url);
//
//                    // Url Encoding the POST parameters
//                    __httpGet.addHeader("Authorization", "Token " + __token);
//
//                    // Making HTTP Request
//                    try {
//                        HttpResponse __httpResponse = __httpClient.execute(__httpGet);
//
//                        if (__httpResponse.getStatusLine().getStatusCode() == 200) {
//                            JSONObject __result = JSONParse.parse(__httpResponse);
//
//                            JSONArray __results = __result.getJSONArray("results");
//                            AppointmentDBHandler __dbHandler = new AppointmentDBHandler(GlobalContext.getContext(), null);
//                            CacheProfileDBHandler __cacheProfileDBHandler = new CacheProfileDBHandler(GlobalContext.getContext(), null);
//                            for (int i = 0; i < __results.length(); i++) {
//                                JSONObject __temp = __results.getJSONObject(i);
//                                __dbHandler.addAppointment(__temp);
//
//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("account"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("account"));
//                                }
//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("doctor"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("doctor"));
//                                }
//                                if (__temp.getJSONObject("clinical_center") != null && __cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("clinical_center"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("clinical_center"));
//                                }
//                            }
//
//                            String __prev =  __result.getString("previous");
//                            if (__prev != null) {
//                                downloadAppointment(__prev, DIRECTION_PREV);
//                            } else if (nextAppointmentDone) {
//                                Synchronized.setScheduleSynchronized(true);
//                            } else {
//                                prevAppointmentDone = true;
//                            }
//                        }
//                    } catch (IOException | JSONException | AmBacSiAuthException _e) {
//                        // writing error to Log
//                        Log.e(TAG + ":dApt", _e.getMessage());
//                    }
//                } else {
//                    // URL
//                    String __url = _strings[0];
//
//                    // Creating HTTP client
//                    HttpClient __httpClient = new DefaultHttpClient();
//
//                    // Creating HTTP Post
//                    HttpGet __httpGet = new HttpGet(__url);
//
//                    // Url Encoding the POST parameters
//                    __httpGet.addHeader("Authorization", "Token " + __token);
//
//                    // Making HTTP Request
//                    try {
//                        HttpResponse __httpResponse = __httpClient.execute(__httpGet);
//
//                        if (__httpResponse.getStatusLine().getStatusCode() == 200) {
//                            JSONObject __result = JSONParse.parse(__httpResponse);
//
//                            JSONArray __results = __result.getJSONArray("results");
//                            AppointmentDBHandler __dbHandler = new AppointmentDBHandler(GlobalContext.getContext(), null);
//                            CacheProfileDBHandler __cacheProfileDBHandler = new CacheProfileDBHandler(GlobalContext.getContext(), null);
//                            for (int i = 0; i < __results.length(); i++) {
//                                JSONObject __temp = __results.getJSONObject(i);
//                                __dbHandler.addAppointment(__temp);
//
//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("account"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("account"));
//                                }
//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("doctor"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("doctor"));
//                                }
//                                if (__temp.getJSONObject("clinical_center") != null && __cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("clinical_center"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("clinical_center"));
//                                }
//                            }
//
//                            String __next =  __result.getString("next");
//                            if (__next != null) {
//                                downloadAppointment(__next, DIRECTION_NEXT);
//                            } else if (prevAppointmentDone) {
//                                Synchronized.setScheduleSynchronized(true);
//                            }  else {
//                                nextAppointmentDone = true;
//                            }
//                        }
//                    } catch (IOException | JSONException | AmBacSiAuthException _e) {
//                        // writing error to Log
//                        Log.e(TAG + ":dApt", _e.getMessage());
//                    }
//                }
//                return null;
//            }
//        }.execute(_link, _direction);
//    }

    public static Task<Relationship> getRelationship(@NonNull final String _id) throws AmBacSiAuthException {
        final String __token = AmBacSiAuth.getLoginUser().getToken();

        return new Task<Relationship>() {
            @Override
            protected Relationship doInBackground(Void... _voids) {
                // URL
                String __url = ApiUrlConstant.PREFIX_URL + String.format(ApiUrlConstant.URL_GET_RELATIONSHIP_ID, _id);

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
                        return new Relationship(__result);
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
                    Log.e(TAG + ":getR", _e.getMessage());

                    this.isComplete = false;
                    this.mException = _e;
                    return null;
                }
            }
        };
    }

    // accept friend request
    public static Task<String> createRelationship(@NonNull final String _friendRequestId) throws AmBacSiAuthException {
        final String __token = AmBacSiAuth.getLoginUser().getToken();

        return new Task<String>() {
            @Override
            protected String doInBackground(Void... _voids) {
                // URL
                String __url = ApiUrlConstant.PREFIX_URL + ApiUrlConstant.URL_CREATE_RELATIONSHIP;

                // Creating HTTP client
                HttpClient __httpClient = new DefaultHttpClient();

                // Creating HTTP Post
                HttpPost __httpPost = new HttpPost(__url);

                // Url Encoding the POST parameters
                __httpPost.addHeader("Content-Type", "application/json");
                __httpPost.addHeader("Authorization", "Token " + __token);

                // Making HTTP Request
                try {
                    JSONObject __json = new JSONObject();
                    __json.put("friend_request_id", _friendRequestId);
                    __httpPost.setEntity(new StringEntity(__json.toString()));
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
                    Log.e(TAG + ":acceptFR", _e.getMessage());

                    this.isComplete = false;
                    this.mException = _e;
                    return null;
                }
            }
        };
    }

    public static Task<Void> deleteRelationship(@NonNull final String _id) throws AmBacSiAuthException {
        final String __token = AmBacSiAuth.getLoginUser().getToken();

        return new Task<Void>() {
            @Override
            protected Void doInBackground(Void... _voids) {
                // URL
                String __url = ApiUrlConstant.PREFIX_URL + String.format(ApiUrlConstant.URL_DELETE_RELATIONSHIP, _id);

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
                    Log.e(TAG + ":deleteR", _e.getMessage());

                    this.isComplete = false;
                    this.mException = _e;
                }

                return null;
            }
        };
    }

//    public static void downloadFriendGroup(@Nullable String _link, @Nullable String _direction) throws AmBacSiAuthException {
//        final String __token = AmBacSiAuth.getLoginUser().getToken();
//
//        new AsyncTask<String, Void, Void>() {
//            @Override
//            protected Void doInBackground(String... _strings) {
//                if (_strings[0] == null) {
//                    // URL
//                    String __url = ApiUrlConstant.PREFIX_URL + ApiUrlConstant.URL_GET_FRIEND_REQUEST;
//
//                    // Creating HTTP client
//                    HttpClient __httpClient = new DefaultHttpClient();
//
//                    // Creating HTTP Post
//                    HttpGet __httpGet = new HttpGet(__url);
//
//                    // Url Encoding the POST parameters
//                    __httpGet.addHeader("Authorization", "Token " + __token);
//
//                    // Making HTTP Request
//                    try {
//                        HttpResponse __httpResponse = __httpClient.execute(__httpGet);
//
//                        if (__httpResponse.getStatusLine().getStatusCode() == 200) {
//                            JSONObject __result = JSONParse.parse(__httpResponse);
//
//                            JSONArray __results = __result.getJSONArray("results");
//                            AppointmentDBHandler __dbHandler = new AppointmentDBHandler(GlobalContext.getContext(), null);
//                            CacheProfileDBHandler __cacheProfileDBHandler = new CacheProfileDBHandler(GlobalContext.getContext(), null);
//                            for (int i = 0; i < __results.length(); i++) {
//                                JSONObject __temp = __results.getJSONObject(i);
//                                __dbHandler.addAppointment(__temp);
//
//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("account"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("account"));
//                                }
//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("doctor"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("doctor"));
//                                }
//                                if (__temp.getJSONObject("clinical_center") != null && __cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("clinical_center"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("clinical_center"));
//                                }
//                            }
//
//                            String __next =  __result.getString("next");
//                            if (__next != null) {
//                                downloadFriendGroup(__next, DIRECTION_NEXT);
//                            } else {
//                                nextAppointmentDone = true;
//                            }
//
//                            String __prev =  __result.getString("previous");
//                            if (__prev != null) {
//                                downloadFriendGroup(__prev, DIRECTION_PREV);
//                            } else if (nextAppointmentDone) {
//                                Synchronized.setScheduleSynchronized(true);
//                            } else {
//                                prevAppointmentDone = true;
//                            }
//                        }
//                    } catch (IOException | JSONException | AmBacSiAuthException _e) {
//                        // writing error to Log
//                        Log.e(TAG + ":dApt", _e.getMessage());
//                    }
//                } else if (_strings[1].equals(DIRECTION_PREV)) {
//                    // URL
//                    String __url = _strings[0];
//
//                    // Creating HTTP client
//                    HttpClient __httpClient = new DefaultHttpClient();
//
//                    // Creating HTTP Post
//                    HttpGet __httpGet = new HttpGet(__url);
//
//                    // Url Encoding the POST parameters
//                    __httpGet.addHeader("Authorization", "Token " + __token);
//
//                    // Making HTTP Request
//                    try {
//                        HttpResponse __httpResponse = __httpClient.execute(__httpGet);
//
//                        if (__httpResponse.getStatusLine().getStatusCode() == 200) {
//                            JSONObject __result = JSONParse.parse(__httpResponse);
//
//                            JSONArray __results = __result.getJSONArray("results");
//                            AppointmentDBHandler __dbHandler = new AppointmentDBHandler(GlobalContext.getContext(), null);
//                            CacheProfileDBHandler __cacheProfileDBHandler = new CacheProfileDBHandler(GlobalContext.getContext(), null);
//                            for (int i = 0; i < __results.length(); i++) {
//                                JSONObject __temp = __results.getJSONObject(i);
//                                __dbHandler.addAppointment(__temp);
//
//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("account"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("account"));
//                                }
//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("doctor"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("doctor"));
//                                }
//                                if (__temp.getJSONObject("clinical_center") != null && __cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("clinical_center"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("clinical_center"));
//                                }
//                            }
//
//                            String __prev =  __result.getString("previous");
//                            if (__prev != null) {
//                                downloadAppointment(__prev, DIRECTION_PREV);
//                            } else if (nextAppointmentDone) {
//                                Synchronized.setScheduleSynchronized(true);
//                            } else {
//                                prevAppointmentDone = true;
//                            }
//                        }
//                    } catch (IOException | JSONException | AmBacSiAuthException _e) {
//                        // writing error to Log
//                        Log.e(TAG + ":dApt", _e.getMessage());
//                    }
//                } else {
//                    // URL
//                    String __url = _strings[0];
//
//                    // Creating HTTP client
//                    HttpClient __httpClient = new DefaultHttpClient();
//
//                    // Creating HTTP Post
//                    HttpGet __httpGet = new HttpGet(__url);
//
//                    // Url Encoding the POST parameters
//                    __httpGet.addHeader("Authorization", "Token " + __token);
//
//                    // Making HTTP Request
//                    try {
//                        HttpResponse __httpResponse = __httpClient.execute(__httpGet);
//
//                        if (__httpResponse.getStatusLine().getStatusCode() == 200) {
//                            JSONObject __result = JSONParse.parse(__httpResponse);
//
//                            JSONArray __results = __result.getJSONArray("results");
//                            AppointmentDBHandler __dbHandler = new AppointmentDBHandler(GlobalContext.getContext(), null);
//                            CacheProfileDBHandler __cacheProfileDBHandler = new CacheProfileDBHandler(GlobalContext.getContext(), null);
//                            for (int i = 0; i < __results.length(); i++) {
//                                JSONObject __temp = __results.getJSONObject(i);
//                                __dbHandler.addAppointment(__temp);
//
//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("account"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("account"));
//                                }
//                                if (__cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("doctor"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("doctor"));
//                                }
//                                if (__temp.getJSONObject("clinical_center") != null && __cacheProfileDBHandler.addCacheProfile(__temp.getJSONObject("clinical_center"))) {
//                                    AmBacSiCacheProfile.loadCacheThumb(__temp.getJSONObject("clinical_center"));
//                                }
//                            }
//
//                            String __next =  __result.getString("next");
//                            if (__next != null) {
//                                downloadAppointment(__next, DIRECTION_NEXT);
//                            } else if (prevAppointmentDone) {
//                                Synchronized.setScheduleSynchronized(true);
//                            }  else {
//                                nextAppointmentDone = true;
//                            }
//                        }
//                    } catch (IOException | JSONException | AmBacSiAuthException _e) {
//                        // writing error to Log
//                        Log.e(TAG + ":dApt", _e.getMessage());
//                    }
//                }
//                return null;
//            }
//        }.execute(_link, _direction);
//    }

    public static Task<FriendGroup> getFriendGroup(@NonNull final String _id) throws AmBacSiAuthException {
        final String __token = AmBacSiAuth.getLoginUser().getToken();

        return new Task<FriendGroup>() {
            @Override
            protected FriendGroup doInBackground(Void... _voids) {
                // URL
                String __url = ApiUrlConstant.PREFIX_URL + String.format(ApiUrlConstant.URL_GET_FRIEND_GROUP_ID, _id);

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
                        return new FriendGroup(__result);
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
                    Log.e(TAG + ":getFG", _e.getMessage());

                    this.isComplete = false;
                    this.mException = _e;
                    return null;
                }
            }
        };
    }

    public static Task<String> createFriendGroup(@NonNull final FriendGroup _friendGroup) throws AmBacSiAuthException {
        final String __token = AmBacSiAuth.getLoginUser().getToken();

        return new Task<String>() {
            @Override
            protected String doInBackground(Void... _voids) {
                // URL
                String __url = ApiUrlConstant.PREFIX_URL + ApiUrlConstant.URL_CREATE_FRIEND_GROUP;

                // Creating HTTP client
                HttpClient __httpClient = new DefaultHttpClient();

                // Creating HTTP Post
                HttpPost __httpPost = new HttpPost(__url);

                // Url Encoding the POST parameters
                __httpPost.addHeader("Content-Type", "application/json");
                __httpPost.addHeader("Authorization", "Token " + __token);

                // Making HTTP Request
                try {
                    __httpPost.setEntity(new StringEntity(_friendGroup.toCreateString()));
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
                    Log.e(TAG + ":createFG", _e.getMessage());

                    this.isComplete = false;
                    this.mException = _e;
                    return null;
                }
            }
        };
    }

    public static Task<Void> updateFriendGroup(@NonNull final FriendGroup _friendGroup) throws AmBacSiAuthException {
        final String __token = AmBacSiAuth.getLoginUser().getToken();

        return new Task<Void>() {
            @Override
            protected Void doInBackground(Void... _voids) {
                // URL
                String __url = ApiUrlConstant.PREFIX_URL + String.format(ApiUrlConstant.URL_UPDATE_FRIEND_GROUP, _friendGroup.getId());

                // Creating HTTP client
                HttpClient __httpClient = new DefaultHttpClient();

                // Creating HTTP Put
                HttpPut __httpPut = new HttpPut(__url);

                // Url Encoding the Put parameters
                __httpPut.addHeader("Content-Type", "application/json");
                __httpPut.addHeader("Authorization", "Token " + __token);

                // Making HTTP Request
                try {
                    __httpPut.setEntity(new StringEntity(_friendGroup.toString()));
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
                    Log.e(TAG + ":updateFG", _e.getMessage());

                    this.isComplete = false;
                    this.mException = _e;
                }

                return null;
            }
        };
    }

    public static Task<Void> addFriendGroupMember(@NonNull final String _friendGroupId, @NonNull final String _friendIdList) throws AmBacSiAuthException {
        final String __token = AmBacSiAuth.getLoginUser().getToken();

        return new Task<Void>() {
            @Override
            protected Void doInBackground(Void... _voids) {
                // URL
                String __url = ApiUrlConstant.PREFIX_URL + String.format(ApiUrlConstant.URL_FRIEND_GROUP_ADD, _friendGroupId);

                // Creating HTTP client
                HttpClient __httpClient = new DefaultHttpClient();

                // Creating HTTP Put
                HttpPut __httpPut = new HttpPut(__url);

                // Url Encoding the Put parameters
                __httpPut.addHeader("Content-Type", "application/json");
                __httpPut.addHeader("Authorization", "Token " + __token);

                // Making HTTP Request
                try {
                    JSONObject __entity = new JSONObject();
                    __entity.put("friend_id_list", _friendGroupId);
                    __httpPut.setEntity(new StringEntity(__entity.toString()));
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
                } catch (IOException | JSONException _e) {
                    // writing error to Log
                    Log.e(TAG + ":addFGMem", _e.getMessage());

                    this.isComplete = false;
                    this.mException = _e;
                }

                return null;
            }
        };
    }

    public static Task<Void> removeFriendGroupMember(@NonNull final String _friendGroupId, @NonNull final String _friendIdList) throws AmBacSiAuthException {
        final String __token = AmBacSiAuth.getLoginUser().getToken();

        return new Task<Void>() {
            @Override
            protected Void doInBackground(Void... _voids) {
                // URL
                String __url = ApiUrlConstant.PREFIX_URL + String.format(ApiUrlConstant.URL_FRIEND_GROUP_REMOVE, _friendGroupId);

                // Creating HTTP client
                HttpClient __httpClient = new DefaultHttpClient();

                // Creating HTTP Put
                HttpPut __httpPut = new HttpPut(__url);

                // Url Encoding the Put parameters
                __httpPut.addHeader("Content-Type", "application/json");
                __httpPut.addHeader("Authorization", "Token " + __token);

                // Making HTTP Request
                try {
                    JSONObject __entity = new JSONObject();
                    __entity.put("friend_id_list", _friendGroupId);
                    __httpPut.setEntity(new StringEntity(__entity.toString()));
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
                } catch (IOException | JSONException _e) {
                    // writing error to Log
                    Log.e(TAG + ":rmFGMem", _e.getMessage());

                    this.isComplete = false;
                    this.mException = _e;
                }

                return null;
            }
        };
    }

    public static Task<Void> deleteFriendGroup(@NonNull final String _id) throws AmBacSiAuthException {
        final String __token = AmBacSiAuth.getLoginUser().getToken();

        return new Task<Void>() {
            @Override
            protected Void doInBackground(Void... _voids) {
                // URL
                String __url = ApiUrlConstant.PREFIX_URL + String.format(ApiUrlConstant.URL_DELETE_FRIEND_GROUP, _id);

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
                    Log.e(TAG + ":deleteFG", _e.getMessage());

                    this.isComplete = false;
                    this.mException = _e;
                }

                return null;
            }
        };
    }
}
