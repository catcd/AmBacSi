package vn.ahaay.ambacsi.api.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Calendar;

import vn.ahaay.ambacsi.api.ambacsi.constant.ServerFormatter;

/**
 * Created by Can on 9/2/2016.
 */
public class Appointment {

    private String localId;
    private String serverId;
    private String accountId;
    private String doctorId;
    private String clinicalCenterId;
    private Calendar appointmentTime;
    private String insuranceId;
    private boolean deposited;
    private String specialtyIdList;
    private String note;
    private int paymentMethod;
    private double paidAmount;
    private double totalPayment;
    private boolean isVisitBefore;
    private int status;
    private String cancelBy;
    private Calendar updatedAt;
    private Calendar createdAt;

    public Appointment() {
    }

    // new
    public Appointment(String _accountId, String _doctorId, String _clinicalCenterId, Calendar _appointmentTime, String _insuranceId, boolean _deposited, String _specialtyIdList, String _note, int _paymentMethod, double _paidAmount, double _totalPayment, int _status, String _cancelBy, Calendar _updatedAt, boolean _isVisitBefore) {
        accountId = _accountId;
        doctorId = _doctorId;
        clinicalCenterId = _clinicalCenterId;
        appointmentTime = _appointmentTime;
        insuranceId = _insuranceId;
        deposited = _deposited;
        specialtyIdList = _specialtyIdList;
        note = _note;
        paymentMethod = _paymentMethod;
        paidAmount = _paidAmount;
        totalPayment = _totalPayment;
        status = _status;
        cancelBy = _cancelBy;
        updatedAt = _updatedAt;
        isVisitBefore = _isVisitBefore;
    }

    // load from server
    public Appointment(String _serverId, String _accountId, String _doctorId, String _clinicalCenterId, Calendar _appointmentTime, String _insuranceId, boolean _deposited, String _specialtyIdList, String _note, int _paymentMethod, double _paidAmount, double _totalPayment, boolean _isVisitBefore, int _status, String _cancelBy, Calendar _updatedAt, Calendar _createdAt) {
        serverId = _serverId;
        accountId = _accountId;
        doctorId = _doctorId;
        clinicalCenterId = _clinicalCenterId;
        appointmentTime = _appointmentTime;
        insuranceId = _insuranceId;
        deposited = _deposited;
        specialtyIdList = _specialtyIdList;
        note = _note;
        paymentMethod = _paymentMethod;
        paidAmount = _paidAmount;
        totalPayment = _totalPayment;
        isVisitBefore = _isVisitBefore;
        status = _status;
        cancelBy = _cancelBy;
        updatedAt = _updatedAt;
        createdAt = _createdAt;
    }

    // load from local db
    public Appointment(String _localId, String _serverId, String _accountId, String _doctorId, String _clinicalCenterId, Calendar _appointmentTime, String _insuranceId, boolean _deposited, String _specialtyIdList, String _note, int _paymentMethod, double _paidAmount, double _totalPayment, boolean _isVisitBefore, int _status, String _cancelBy, Calendar _updatedAt, Calendar _createdAt) {
        localId = _localId;
        serverId = _serverId;
        accountId = _accountId;
        doctorId = _doctorId;
        clinicalCenterId = _clinicalCenterId;
        appointmentTime = _appointmentTime;
        insuranceId = _insuranceId;
        deposited = _deposited;
        specialtyIdList = _specialtyIdList;
        note = _note;
        paymentMethod = _paymentMethod;
        paidAmount = _paidAmount;
        totalPayment = _totalPayment;
        isVisitBefore = _isVisitBefore;
        status = _status;
        cancelBy = _cancelBy;
        updatedAt = _updatedAt;
        createdAt = _createdAt;
    }

    public Appointment(JSONObject _schedule) throws JSONException, ParseException {
        serverId = _schedule.getString("id");
        doctorId = _schedule.getJSONObject("account").getString("account_id");
        doctorId = _schedule.getJSONObject("doctor").getString("account_id");
        clinicalCenterId = _schedule.getJSONObject("clinical_center").getString("account_id");

        Calendar __appointmentTime = Calendar.getInstance();
        __appointmentTime.setTime(ServerFormatter.DATETIME_FORMAT.parse(_schedule.getString("appointment_time")));
        appointmentTime = __appointmentTime;

        insuranceId = _schedule.getString("insurance_id");
        deposited = _schedule.getInt("deposited") != 0;
        specialtyIdList = _schedule.getString("insurance_id");
        note = _schedule.getString("note");
        paymentMethod = _schedule.getInt("payment_method");
        paidAmount = _schedule.getDouble("paid_amount");
        totalPayment = _schedule.getDouble("total_payment");
        isVisitBefore = _schedule.getInt("is_visit_before") != 0;
        status = _schedule.getInt("status");
        cancelBy = _schedule.getString("cancel_by");

        Calendar __updatedAt = Calendar.getInstance();
        __updatedAt.setTime(ServerFormatter.DATETIME_FORMAT.parse(_schedule.getString("updated_at")));
        updatedAt = __updatedAt;


        Calendar __createdAt = Calendar.getInstance();
        __createdAt.setTime(ServerFormatter.DATETIME_FORMAT.parse(_schedule.getString("created_at")));
        createdAt = __createdAt;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String _localId) {
        localId = _localId;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String _serverId) {
        serverId = _serverId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String _accountId) {
        accountId = _accountId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String _doctorId) {
        doctorId = _doctorId;
    }

    public String getClinicalCenterId() {
        return clinicalCenterId;
    }

    public void setClinicalCenterId(String _clinicalCenterId) {
        clinicalCenterId = _clinicalCenterId;
    }

    public Calendar getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Calendar _appointmentTime) {
        appointmentTime = _appointmentTime;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String _insuranceId) {
        insuranceId = _insuranceId;
    }

    public boolean isDeposited() {
        return deposited;
    }

    public void setDeposited(boolean _deposited) {
        deposited = _deposited;
    }

    public String getSpecialtyIdList() {
        return specialtyIdList;
    }

    public void setSpecialtyIdList(String _specialtyIdList) {
        specialtyIdList = _specialtyIdList;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String _note) {
        note = _note;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int _paymentMethod) {
        paymentMethod = _paymentMethod;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double _paidAmount) {
        paidAmount = _paidAmount;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double _totalPayment) {
        totalPayment = _totalPayment;
    }

    public boolean isVisitBefore() {
        return isVisitBefore;
    }

    public void setVisitBefore(boolean _visitBefore) {
        isVisitBefore = _visitBefore;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int _status) {
        status = _status;
    }

    public String getCancelBy() {
        return cancelBy;
    }

    public void setCancelBy(String _cancelBy) {
        cancelBy = _cancelBy;
    }

    public Calendar getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Calendar _updatedAt) {
        updatedAt = _updatedAt;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar _createdAt) {
        createdAt = _createdAt;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + serverId + '\'' +
                ", account_d='" + accountId + '\'' +
                ", doctor_id='" + doctorId + '\'' +
                ", clinical_center_id='" + clinicalCenterId + '\'' +
                ", appointment_time=" + ServerFormatter.DATETIME_FORMAT.format(appointmentTime) +
                ", insurance_id='" + insuranceId + '\'' +
                ", deposited=" + deposited +
                ", specialty_id_list='" + specialtyIdList + '\'' +
                ", note='" + note + '\'' +
                ", payment_method=" + paymentMethod +
                ", paid_amount=" + paidAmount +
                ", total_payment=" + totalPayment +
                ", isVisit_before=" + (isVisitBefore ? 1 : 0) +
                ", status=" + status +
                ", cancel_by='" + cancelBy + '\'' +
                ", updated_at=" + ServerFormatter.DATETIME_FORMAT.format(updatedAt) +
                ", created_at=" + ServerFormatter.DATETIME_FORMAT.format(createdAt) +
                '}';
    }
}
