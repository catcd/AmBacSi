package vn.ahaay.ambacsi.api.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Can on 10-Sep-16.
 */
public class FriendGroup {
    private String id;
    private String accountId;
    private String name;
    private String friendIdList;
    private String description;

    public FriendGroup() {
    }

    public FriendGroup(String _accountId, String _name, String _friendIdList, String _description) {
        accountId = _accountId;
        name = _name;
        friendIdList = _friendIdList;
        description = _description;
    }

    public FriendGroup(String _id, String _accountId, String _name, String _friendIdList, String _description) {
        id = _id;
        accountId = _accountId;
        name = _name;
        friendIdList = _friendIdList;
        description = _description;
    }

    public FriendGroup(JSONObject _schedule) throws JSONException {
        id = _schedule.getString("id");
        accountId = _schedule.getJSONObject("account").getString("account_id");
        name = _schedule.getString("name");
        friendIdList = _schedule.getString("friend_id_list");
        description = _schedule.getString("description");
    }

    public String getId() {
        return id;
    }

    public void setId(String _id) {
        id = _id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String _accountId) {
        accountId = _accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getFriendIdList() {
        return friendIdList;
    }

    public void setFriendIdList(String _friendIdList) {
        friendIdList = _friendIdList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String _description) {
        description = _description;
    }

    public String toCreateString() {
        return "{" +
                "name='" + name + '\'' +
                ", friend_id_list='" + friendIdList + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "FriendGroup{" +
                "name='" + name + '\'' +
                ", friend_id_list='" + friendIdList + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
