package vn.ahaay.ambacsi.api.model;

import java.util.Calendar;

/**
 * Created by Can on 10-Sep-16.
 */
public class FriendRequest {
    private String id;
    private String senderId;
    private String receiverId;
    private Calendar requestTime;
    private String relationshipRole;
    private boolean senderIsRole;

    public FriendRequest() {
    }

    public FriendRequest(String _senderId, String _receiverId, Calendar _requestTime, String _relationshipRole, boolean _senderIsRole) {
        senderId = _senderId;
        receiverId = _receiverId;
        requestTime = _requestTime;
        relationshipRole = _relationshipRole;
        senderIsRole = _senderIsRole;
    }

    public FriendRequest(String _id, String _senderId, String _receiverId, Calendar _requestTime, String _relationshipRole, boolean _senderIsRole) {
        id = _id;
        senderId = _senderId;
        receiverId = _receiverId;
        requestTime = _requestTime;
        relationshipRole = _relationshipRole;
        senderIsRole = _senderIsRole;
    }

    public String getId() {
        return id;
    }

    public void setId(String _id) {
        id = _id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String _senderId) {
        senderId = _senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String _receiverId) {
        receiverId = _receiverId;
    }

    public Calendar getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Calendar _requestTime) {
        requestTime = _requestTime;
    }

    public String getRelationshipRole() {
        return relationshipRole;
    }

    public void setRelationshipRole(String _relationshipRole) {
        relationshipRole = _relationshipRole;
    }

    public boolean isSenderIsRole() {
        return senderIsRole;
    }

    public void setSenderIsRole(boolean _senderIsRole) {
        senderIsRole = _senderIsRole;
    }

    public String toCreateString() {
        return "{" +
                "receiver_id='" + receiverId + '\'' +
                ", relationship_role='" + relationshipRole + '\'' +
                ", sender_is_role=" + (senderIsRole ? 1 : 0) +
                '}';
    }
}
