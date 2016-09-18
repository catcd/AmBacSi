package vn.ahaay.ambacsi.api.ambacsi.profile;

import org.json.JSONObject;

/**
 * Created by SONY on 21-Jul-16.
 */
public abstract class ProfileChangeRequest {
    protected JSONObject request;

    public static final String KEY_ROLE = "role";

    public ProfileChangeRequest() {
        request = new JSONObject();
    }

    public String getHttpRequestBody() {
        return request.toString();
    }

    abstract public String getDisplayName();
}
