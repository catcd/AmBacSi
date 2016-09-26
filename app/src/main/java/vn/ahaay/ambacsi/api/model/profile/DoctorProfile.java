package vn.ahaay.ambacsi.api.model.profile;

import android.graphics.Bitmap;

import java.util.Calendar;

import vn.ahaay.ambacsi.api.ambacsi.constant.ServerFormatter;

/**
 * Status: UPDATED
 * Created by SONY on 13-Aug-16.
 * Last updated on 26-Sep-2016
 */
public final class DoctorProfile extends Profile {
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
    private String professionalStatement;
    private String homeZipcode;
    private String passport;
    private String idCardNo;
    private String homeAddress;
    private String homephone;
    private String mobilephone;
    private String workphone;
    private String fax;
    private String extNumber;
    private String workingEmail;
    private int educationLevel;
    private int medicineEducationLevel;
    private int experienceYear;
    private boolean havingPrivatePractice;
    private boolean workingForPublicHospital;
    private boolean acceptInsurance;
    private String languages;
    private boolean isValidated;
    private boolean onlineConsult;
    private boolean needReservation;
    private boolean acceptOnlinePayment;
    private boolean acceptCard;
    private double dailyPrice;
    private double weekendPrice;
    private String insuranceIdList;
    private String specialtyIdList;
    private Calendar createdAt;
    private Calendar updatedAt;

    private Bitmap avatar;
    private Bitmap cover;

    public DoctorProfile() {
    }

    public DoctorProfile(String _id, String _accountId, String _avatarPath, String _coverPath, String _firstName, String _lastName, String _nickName, Calendar _dob, int _gender, int _marriedStatus, String _professionalStatement, String _homeZipcode, String _passport, String _idCardNo, String _homeAddress, String _homephone, String _mobilephone, String _workphone, String _fax, String _extNumber, String _workingEmail, int _educationLevel, int _medicineEducationLevel, int _experienceYear, boolean _havingPrivatePractice, boolean _workingForPublicHospital, boolean _acceptInsurance, String _languages, boolean _isValidated, boolean _onlineConsult, boolean _needReservation, boolean _acceptOnlinePayment, boolean _acceptCard, double _dailyPrice, double _weekendPrice, String _insuranceIdList, String _specialtyIdList, Calendar _createdAt, Calendar _updatedAt) {
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
        professionalStatement = _professionalStatement;
        homeZipcode = _homeZipcode;
        passport = _passport;
        idCardNo = _idCardNo;
        homeAddress = _homeAddress;
        homephone = _homephone;
        mobilephone = _mobilephone;
        workphone = _workphone;
        fax = _fax;
        extNumber = _extNumber;
        workingEmail = _workingEmail;
        educationLevel = _educationLevel;
        medicineEducationLevel = _medicineEducationLevel;
        experienceYear = _experienceYear;
        havingPrivatePractice = _havingPrivatePractice;
        workingForPublicHospital = _workingForPublicHospital;
        acceptInsurance = _acceptInsurance;
        languages = _languages;
        isValidated = _isValidated;
        onlineConsult = _onlineConsult;
        needReservation = _needReservation;
        acceptOnlinePayment = _acceptOnlinePayment;
        acceptCard = _acceptCard;
        dailyPrice = _dailyPrice;
        weekendPrice = _weekendPrice;
        insuranceIdList = _insuranceIdList;
        specialtyIdList = _specialtyIdList;
        createdAt = _createdAt;
        updatedAt = _updatedAt;
    }

    public DoctorProfile(String _id, String _accountId, String _avatarPath, String _coverPath, String _firstName, String _lastName, String _nickName, Calendar _dob, int _gender, int _marriedStatus, String _professionalStatement, String _homeZipcode, String _passport, String _idCardNo, String _homeAddress, String _homephone, String _mobilephone, String _workphone, String _fax, String _extNumber, String _workingEmail, int _educationLevel, int _medicineEducationLevel, int _experienceYear, boolean _havingPrivatePractice, boolean _workingForPublicHospital, boolean _acceptInsurance, String _languages, boolean _isValidated, boolean _onlineConsult, boolean _needReservation, boolean _acceptOnlinePayment, boolean _acceptCard, double _dailyPrice, double _weekendPrice, String _insuranceIdList, String _specialtyIdList, Calendar _createdAt, Calendar _updatedAt, Bitmap _avatar, Bitmap _cover) {
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
        professionalStatement = _professionalStatement;
        homeZipcode = _homeZipcode;
        passport = _passport;
        idCardNo = _idCardNo;
        homeAddress = _homeAddress;
        homephone = _homephone;
        mobilephone = _mobilephone;
        workphone = _workphone;
        fax = _fax;
        extNumber = _extNumber;
        workingEmail = _workingEmail;
        educationLevel = _educationLevel;
        medicineEducationLevel = _medicineEducationLevel;
        experienceYear = _experienceYear;
        havingPrivatePractice = _havingPrivatePractice;
        workingForPublicHospital = _workingForPublicHospital;
        acceptInsurance = _acceptInsurance;
        languages = _languages;
        isValidated = _isValidated;
        onlineConsult = _onlineConsult;
        needReservation = _needReservation;
        acceptOnlinePayment = _acceptOnlinePayment;
        acceptCard = _acceptCard;
        dailyPrice = _dailyPrice;
        weekendPrice = _weekendPrice;
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

    public String getProfessionalStatement() {
        return professionalStatement;
    }

    public void setProfessionalStatement(String _professionalStatement) {
        professionalStatement = _professionalStatement;
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

    public String getExtNumber() {
        return extNumber;
    }

    public void setExtNumber(String _extNumber) {
        extNumber = _extNumber;
    }

    public String getWorkingEmail() {
        return workingEmail;
    }

    public void setWorkingEmail(String _workingEmail) {
        workingEmail = _workingEmail;
    }

    public int getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(int _educationLevel) {
        educationLevel = _educationLevel;
    }

    public int getMedicineEducationLevel() {
        return medicineEducationLevel;
    }

    public void setMedicineEducationLevel(int _medicineEducationLevel) {
        medicineEducationLevel = _medicineEducationLevel;
    }

    public int getExperienceYear() {
        return experienceYear;
    }

    public void setExperienceYear(int _experienceYear) {
        experienceYear = _experienceYear;
    }

    public boolean isHavingPrivatePractice() {
        return havingPrivatePractice;
    }

    public void setHavingPrivatePractice(boolean _havingPrivatePractice) {
        havingPrivatePractice = _havingPrivatePractice;
    }

    public boolean isWorkingForPublicHospital() {
        return workingForPublicHospital;
    }

    public void setWorkingForPublicHospital(boolean _workingForPublicHospital) {
        workingForPublicHospital = _workingForPublicHospital;
    }

    public boolean isAcceptInsurance() {
        return acceptInsurance;
    }

    public void setAcceptInsurance(boolean _acceptInsurance) {
        acceptInsurance = _acceptInsurance;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String _languages) {
        languages = _languages;
    }

    public boolean isValidated() {
        return isValidated;
    }

    public void setValidated(boolean _validated) {
        isValidated = _validated;
    }

    public boolean isOnlineConsult() {
        return onlineConsult;
    }

    public void setOnlineConsult(boolean _onlineConsult) {
        onlineConsult = _onlineConsult;
    }

    public boolean isNeedReservation() {
        return needReservation;
    }

    public void setNeedReservation(boolean _needReservation) {
        needReservation = _needReservation;
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

    public double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(double _dailyPrice) {
        dailyPrice = _dailyPrice;
    }

    public double getWeekendPrice() {
        return weekendPrice;
    }

    public void setWeekendPrice(double _weekendPrice) {
        weekendPrice = _weekendPrice;
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
        return "DoctorProfile{" +
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
                ", professional_statement='" + professionalStatement + '\'' +
                ", home_zipcode='" + homeZipcode + '\'' +
                ", passport='" + passport + '\'' +
                ", id_card_no='" + idCardNo + '\'' +
                ", home_address='" + homeAddress + '\'' +
                ", homephone='" + homephone + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", workphone='" + workphone + '\'' +
                ", fax='" + fax + '\'' +
                ", ext_number='" + extNumber + '\'' +
                ", working_email='" + workingEmail + '\'' +
                ", education_level=" + educationLevel +
                ", medicine_education_level=" + medicineEducationLevel +
                ", experience_year=" + experienceYear +
                ", having_private_practice=" + havingPrivatePractice +
                ", working_for_public_hospital=" + workingForPublicHospital +
                ", accept_insurance=" + acceptInsurance +
                ", languages='" + languages + '\'' +
                ", is_validated=" + isValidated +
                ", online_consult=" + onlineConsult +
                ", need_reservation=" + needReservation +
                ", accept_online_payment=" + acceptOnlinePayment +
                ", accept_card=" + acceptCard +
                ", daily_price=" + dailyPrice +
                ", weekend_price=" + weekendPrice +
                ", insurance_id_list='" + insuranceIdList + '\'' +
                ", specialty_id_list='" + specialtyIdList + '\'' +
                ", created_at='" + ServerFormatter.DATETIME_FORMAT.format(createdAt) + '\'' +
                ", updated_at='" + ServerFormatter.DATETIME_FORMAT.format(updatedAt) + '\'' +
                '}';
    }
}
