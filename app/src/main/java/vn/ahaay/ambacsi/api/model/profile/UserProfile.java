package vn.ahaay.ambacsi.api.model.profile;

import android.graphics.Bitmap;

import java.util.Calendar;

import vn.ahaay.ambacsi.api.ambacsi.constant.ServerFormatter;

/**
 * Status: UPDATED
 * Created by SONY on 13-Aug-16.
 * Last updated on 26-Sep-2016
 */
public final class UserProfile extends Profile {
    private String id;
    private String accountId;
    private String avatarPath;
    private String coverPath;
    private String firstName;
    private String lastName;
    private String nickName;
    private Calendar dob;
    private int gender;
    private int marriedStatus;
    private String homeZipcode;
    private String passport;
    private String idCardNo;
    private String homeAddress;
    private String homephone;
    private String mobilephone;
    private String workphone;
    private String fax;
    private int educationLevel;
    private String workingEmail;
    private String languages;
    private Calendar createdAt;
    private Calendar updatedAt;

    private Bitmap avatar;
    private Bitmap cover;

    public UserProfile() {
    }

    public UserProfile(String _id, String _accountId, String _avatarPath, String _coverPath, String _firstName, String _lastName, String _nickName, Calendar _dob, int _gender, int _marriedStatus, String _homeZipcode, String _passport, String _idCardNo, String _homeAddress, String _homephone, String _mobilephone, String _workphone, String _fax, int _educationLevel, String _workingEmail, String _languages, Calendar _createdAt, Calendar _updatedAt) {
        id = _id;
        accountId = _accountId;
        avatarPath = _avatarPath;
        coverPath = _coverPath;
        firstName = _firstName;
        lastName = _lastName;
        nickName = _nickName;
        dob = _dob;
        gender = _gender;
        marriedStatus = _marriedStatus;
        homeZipcode = _homeZipcode;
        passport = _passport;
        idCardNo = _idCardNo;
        homeAddress = _homeAddress;
        homephone = _homephone;
        mobilephone = _mobilephone;
        workphone = _workphone;
        fax = _fax;
        educationLevel = _educationLevel;
        workingEmail = _workingEmail;
        languages = _languages;
        createdAt = _createdAt;
        updatedAt = _createdAt;
    }

    public UserProfile(String _id, String _accountId, String _avatarPath, String _coverPath, String _firstName, String _lastName, String _nickName, Calendar _dob, int _gender, int _marriedStatus, String _homeZipcode, String _passport, String _idCardNo, String _homeAddress, String _homephone, String _mobilephone, String _workphone, String _fax, int _educationLevel, String _workingEmail, String _languages, Calendar _createdAt, Calendar _updatedAt, Bitmap _avatar, Bitmap _cover) {
        id = _id;
        accountId = _accountId;
        avatarPath = _avatarPath;
        coverPath = _coverPath;
        firstName = _firstName;
        lastName = _lastName;
        nickName = _nickName;
        dob = _dob;
        gender = _gender;
        marriedStatus = _marriedStatus;
        homeZipcode = _homeZipcode;
        passport = _passport;
        idCardNo = _idCardNo;
        homeAddress = _homeAddress;
        homephone = _homephone;
        mobilephone = _mobilephone;
        workphone = _workphone;
        fax = _fax;
        educationLevel = _educationLevel;
        workingEmail = _workingEmail;
        languages = _languages;
        createdAt = _createdAt;
        updatedAt = _createdAt;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String _firstName) {
        firstName = _firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String _lastName) {
        lastName = _lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String _nickName) {
        nickName = _nickName;
    }

    public Calendar getDob() {
        return dob;
    }

    public void setDob(Calendar _dob) {
        dob = _dob;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int _gender) {
        gender = _gender;
    }

    public int getMarriedStatus() {
        return marriedStatus;
    }

    public void setMarriedStatus(int _marriedStatus) {
        marriedStatus = _marriedStatus;
    }

    public String getHomeZipcode() {
        return homeZipcode;
    }

    public void setHomeZipcode(String _homeZipcode) {
        homeZipcode = _homeZipcode;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String _passport) {
        passport = _passport;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String _idCardNo) {
        idCardNo = _idCardNo;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String _homeAddress) {
        homeAddress = _homeAddress;
    }

    public String getHomephone() {
        return homephone;
    }

    public void setHomephone(String _homephone) {
        homephone = _homephone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String _mobilephone) {
        mobilephone = _mobilephone;
    }

    public String getWorkphone() {
        return workphone;
    }

    public void setWorkphone(String _workphone) {
        workphone = _workphone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String _fax) {
        fax = _fax;
    }

    public int getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(int _educationLevel) {
        educationLevel = _educationLevel;
    }

    public String getWorkingEmail() {
        return workingEmail;
    }

    public void setWorkingEmail(String _workingEmail) {
        workingEmail = _workingEmail;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String _languages) {
        languages = _languages;
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
                ", avatar_path='" + avatarPath + '\'' +
                ", cover_path='" + coverPath + '\'' +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", nick_name='" + nickName + '\'' +
                ", dob='" + ServerFormatter.DATE_FORMAT.format(dob) + '\'' +
                ", gender=" + gender +
                ", married_status=" + marriedStatus +
                ", home_zipcode='" + homeZipcode + '\'' +
                ", passport='" + passport + '\'' +
                ", id_card_no='" + idCardNo + '\'' +
                ", home_address='" + homeAddress + '\'' +
                ", homephone='" + homephone + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", workphone='" + workphone + '\'' +
                ", fax='" + fax + '\'' +
                ", education_level=" + educationLevel +
                ", working_email='" + workingEmail + '\'' +
                ", languages='" + languages + '\'' +
                ", created_at='" + ServerFormatter.DATETIME_FORMAT.format(createdAt) + '\'' +
                ", updated_at='" + ServerFormatter.DATETIME_FORMAT.format(updatedAt) + '\'' +
                '}';
    }
}
