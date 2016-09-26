package vn.ahaay.ambacsi.api.model.profile;

import android.graphics.Bitmap;

import java.util.Calendar;

import vn.ahaay.ambacsi.api.ambacsi.constant.ServerFormatter;

/**
 * Status: UPDATED
 * Created by SONY on 13-Aug-16.
 * Last updated on 26-Sep-2016
 */
public final class ClinicalCenterProfile extends Profile {
    private String id;
    private String accountId;
    private int type;
    private String name;
    private String avatarPath;
    private String coverPath;
    private String location;
    private String zipcode;
    private String tel;
    private String extNumber;
    private String fax;
    private int numOfDoc;
    private int numOfRoom;
    private int numOfBed;
    private int numOfNurse;
    private Calendar foundDate;
    private String director;
    private String directorId;
    private int rate;
    private double area;
    private boolean acceptInsurance;
    private boolean acceptOnlinePayment;
    private boolean acceptCard;
    private boolean workingOnSat;
    private boolean workingOnSun;
    private boolean needReservation;
    private String insuranceIdList;
    private String specialtyIdList;
    private Calendar createdAt;
    private Calendar updatedAt;

    private Bitmap avatar;
    private Bitmap cover;

    public ClinicalCenterProfile() {
    }

    public ClinicalCenterProfile(String _id, String _accountId, int _type, String _name, String _avatarPath, String _coverPath, String _location, String _zipcode, String _tel, String _extNumber, String _fax, int _numOfDoc, int _numOfRoom, int _numOfBed, int _numOfNurse, Calendar _foundDate, String _director, String _directorId, int _rate, double _area, boolean _acceptInsurance, boolean _acceptOnlinePayment, boolean _acceptCard, boolean _workingOnSat, boolean _workingOnSun, boolean _needReservation, String _insuranceIdList, String _specialtyIdList, Calendar _createdAt, Calendar _updatedAt) {
        id = _id;
        accountId = _accountId;
        type = _type;
        name = _name;
        avatarPath = _avatarPath;
        coverPath = _coverPath;
        location = _location;
        zipcode = _zipcode;
        tel = _tel;
        extNumber = _extNumber;
        fax = _fax;
        numOfDoc = _numOfDoc;
        numOfRoom = _numOfRoom;
        numOfBed = _numOfBed;
        numOfNurse = _numOfNurse;
        foundDate = _foundDate;
        director = _director;
        directorId = _directorId;
        rate = _rate;
        area = _area;
        acceptInsurance = _acceptInsurance;
        acceptOnlinePayment = _acceptOnlinePayment;
        acceptCard = _acceptCard;
        workingOnSat = _workingOnSat;
        workingOnSun = _workingOnSun;
        needReservation = _needReservation;
        insuranceIdList = _insuranceIdList;
        specialtyIdList = _specialtyIdList;
        createdAt = _createdAt;
        updatedAt = _updatedAt;
    }

    public ClinicalCenterProfile(String _id, String _accountId, int _type, String _name, String _avatarPath, String _coverPath, String _location, String _zipcode, String _tel, String _extNumber, String _fax, int _numOfDoc, int _numOfRoom, int _numOfBed, int _numOfNurse, Calendar _foundDate, String _director, String _directorId, int _rate, double _area, boolean _acceptInsurance, boolean _acceptOnlinePayment, boolean _acceptCard, boolean _workingOnSat, boolean _workingOnSun, boolean _needReservation, String _insuranceIdList, String _specialtyIdList, Calendar _createdAt, Calendar _updatedAt, Bitmap _avatar, Bitmap _cover) {
        id = _id;
        accountId = _accountId;
        type = _type;
        name = _name;
        avatarPath = _avatarPath;
        coverPath = _coverPath;
        location = _location;
        zipcode = _zipcode;
        tel = _tel;
        extNumber = _extNumber;
        fax = _fax;
        numOfDoc = _numOfDoc;
        numOfRoom = _numOfRoom;
        numOfBed = _numOfBed;
        numOfNurse = _numOfNurse;
        foundDate = _foundDate;
        director = _director;
        directorId = _directorId;
        rate = _rate;
        area = _area;
        acceptInsurance = _acceptInsurance;
        acceptOnlinePayment = _acceptOnlinePayment;
        acceptCard = _acceptCard;
        workingOnSat = _workingOnSat;
        workingOnSun = _workingOnSun;
        needReservation = _needReservation;
        insuranceIdList = _insuranceIdList;
        specialtyIdList = _specialtyIdList;
        createdAt = _createdAt;
        updatedAt = _updatedAt;
        avatar = _avatar;
        cover = _cover;
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

    public int getType() {
        return type;
    }

    public void setType(int _type) {
        type = _type;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String _avatarPath) {
        avatarPath = _avatarPath;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String _coverPath) {
        coverPath = _coverPath;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String _location) {
        location = _location;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String _zipcode) {
        zipcode = _zipcode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String _tel) {
        tel = _tel;
    }

    public String getExtNumber() {
        return extNumber;
    }

    public void setExtNumber(String _extNumber) {
        extNumber = _extNumber;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String _fax) {
        fax = _fax;
    }

    public int getNumOfDoc() {
        return numOfDoc;
    }

    public void setNumOfDoc(int _numOfDoc) {
        numOfDoc = _numOfDoc;
    }

    public int getNumOfRoom() {
        return numOfRoom;
    }

    public void setNumOfRoom(int _numOfRoom) {
        numOfRoom = _numOfRoom;
    }

    public int getNumOfBed() {
        return numOfBed;
    }

    public void setNumOfBed(int _numOfBed) {
        numOfBed = _numOfBed;
    }

    public int getNumOfNurse() {
        return numOfNurse;
    }

    public void setNumOfNurse(int _numOfNurse) {
        numOfNurse = _numOfNurse;
    }

    public Calendar getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(Calendar _foundDate) {
        foundDate = _foundDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String _director) {
        director = _director;
    }

    public String getDirectorId() {
        return directorId;
    }

    public void setDirectorId(String _directorId) {
        directorId = _directorId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int _rate) {
        rate = _rate;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double _area) {
        area = _area;
    }

    public boolean isAcceptInsurance() {
        return acceptInsurance;
    }

    public void setAcceptInsurance(boolean _acceptInsurance) {
        acceptInsurance = _acceptInsurance;
    }

    public boolean isAcceptOnlinePayment() {
        return acceptOnlinePayment;
    }

    public void setAcceptOnlinePayment(boolean _acceptOnlinePayment) {
        acceptOnlinePayment = _acceptOnlinePayment;
    }

    public boolean isAcceptCard() {
        return acceptCard;
    }

    public void setAcceptCard(boolean _acceptCard) {
        acceptCard = _acceptCard;
    }

    public boolean isWorkingOnSat() {
        return workingOnSat;
    }

    public void setWorkingOnSat(boolean _workingOnSat) {
        workingOnSat = _workingOnSat;
    }

    public boolean isWorkingOnSun() {
        return workingOnSun;
    }

    public void setWorkingOnSun(boolean _workingOnSun) {
        workingOnSun = _workingOnSun;
    }

    public boolean isNeedReservation() {
        return needReservation;
    }

    public void setNeedReservation(boolean _needReservation) {
        needReservation = _needReservation;
    }

    public String getInsuranceIdList() {
        return insuranceIdList;
    }

    public void setInsuranceIdList(String _insuranceIdList) {
        insuranceIdList = _insuranceIdList;
    }

    public String getSpecialtyIdList() {
        return specialtyIdList;
    }

    public void setSpecialtyIdList(String _specialtyIdList) {
        specialtyIdList = _specialtyIdList;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar _createdAt) {
        createdAt = _createdAt;
    }

    public Calendar getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Calendar _updatedAt) {
        updatedAt = _updatedAt;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap _avatar) {
        avatar = _avatar;
    }

    public Bitmap getCover() {
        return cover;
    }

    public void setCover(Bitmap _cover) {
        cover = _cover;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", account_id='" + accountId + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", avatar_path='" + avatarPath + '\'' +
                ", cover_path='" + coverPath + '\'' +
                ", location='" + location + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", tel='" + tel + '\'' +
                ", ext_number='" + extNumber + '\'' +
                ", fax='" + fax + '\'' +
                ", num_of_doc=" + numOfDoc +
                ", num_of_room=" + numOfRoom +
                ", num_of_bed=" + numOfBed +
                ", num_of_nurse=" + numOfNurse +
                ", found_date='" + ServerFormatter.DATE_FORMAT.format(foundDate) + '\'' +
                ", director='" + director + '\'' +
                ", director_id='" + directorId + '\'' +
                ", rate=" + rate +
                ", area=" + area +
                ", accept_insurance=" + acceptInsurance +
                ", accept_online_payment=" + acceptOnlinePayment +
                ", accept_card=" + acceptCard +
                ", working_on_sat=" + workingOnSat +
                ", working_on_sun=" + workingOnSun +
                ", need_reservation=" + needReservation +
                ", insurance_id_list='" + insuranceIdList + '\'' +
                ", specialty_id_list='" + specialtyIdList + '\'' +
                ", updated_at='" + ServerFormatter.DATETIME_FORMAT.format(createdAt) + '\'' +
                ", created_at='" + ServerFormatter.DATETIME_FORMAT.format(updatedAt) + '\'' +
                '}';
    }
}
