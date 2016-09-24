package vn.ahaay.ambacsi.api.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Calendar;

import vn.ahaay.ambacsi.api.ambacsi.constant.ServerFormatter;

/**
 * Created by Can on 10-Sep-16.
 */
public class Relationship {
    private String id;
    private String account1Id;
    private String account2Id;
    private Calendar acceptTime;
    private String relationshipRole;

    public Relationship() {

    }

    public Relationship(String _account2Id, Calendar _acceptTime, String _relationshipRole, String _account1Id) {
        account2Id = _account2Id;
        acceptTime = _acceptTime;
        relationshipRole = _relationshipRole;
        account1Id = _account1Id;
    }

    public Relationship(String _id, String _account1Id, String _account2Id, Calendar _acceptTime, String _relationshipRole) {
        id = _id;
        account1Id = _account1Id;
        account2Id = _account2Id;
        acceptTime = _acceptTime;
        relationshipRole = _relationshipRole;
    }

    public Relationship(JSONObject _schedule) throws JSONException, ParseException {
        id = _schedule.getString("id");
        account1Id = _schedule.getJSONObject("account_1").getString("account_id");
        account2Id = _schedule.getJSONObject("account_2").getString("account_id");

        Calendar __acceptTime = Calendar.getInstance();
        __acceptTime.setTime(ServerFormatter.DATETIME_FORMAT.parse(_schedule.getString("accept_time")));
        acceptTime = __acceptTime;

        relationshipRole = _schedule.getString("relationship_role");
    }

    public String getId() {
        return id;
    }

    public void setId(String _id) {
        id = _id;
    }

    public String getAccount1Id() {
        return account1Id;
    }

    public void setAccount1Id(String _account1Id) {
        account1Id = _account1Id;
    }

    public String getAccount2Id() {
        return account2Id;
    }

    public void setAccount2Id(String _account2Id) {
        account2Id = _account2Id;
    }

    public Calendar getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Calendar _acceptTime) {
        acceptTime = _acceptTime;
    }

    public String getRelationshipRole() {
        return relationshipRole;
    }

    public void setRelationshipRole(String _relationshipRole) {
        relationshipRole = _relationshipRole;
    }
}
