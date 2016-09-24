package vn.ahaay.ambacsi.api.model;

import com.alamkanak.weekview.WeekViewEvent;

import java.util.Calendar;

/**
 * Created by SONY on 13-Aug-16.
 */
public class SimpleSchedule extends WeekViewEvent {
    private String localId;
    private String serverId;

    public SimpleSchedule() {
    }

    public SimpleSchedule(String name, int startYear, int startMonth, int startDay, int startHour, int startMinute, int endYear, int endMonth, int endDay, int endHour, int endMinute, String localId, String serverId) {
        super(localId.hashCode(), name, startYear, startMonth, startDay, startHour, startMinute, endYear, endMonth, endDay, endHour, endMinute);
        this.localId = localId;
        this.serverId = serverId;
    }

    public SimpleSchedule(String name, String location, Calendar startTime, Calendar endTime, String localId, String serverId) {
        super(localId.hashCode(), name, location, startTime, endTime);
        this.localId = localId;
        this.serverId = serverId;
    }

    public SimpleSchedule(String name, Calendar startTime, Calendar endTime, String localId, String serverId) {
        super(localId.hashCode(), name, startTime, endTime);
        this.localId = localId;
        this.serverId = serverId;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }
}
