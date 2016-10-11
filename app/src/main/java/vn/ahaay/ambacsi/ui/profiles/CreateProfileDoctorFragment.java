package vn.ahaay.ambacsi.ui.profiles;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;

import vn.ahaay.ambacsi.R;
import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiAuthException;
import vn.ahaay.ambacsi.api.ambacsi.profile.DoctorProfileChangeRequest;
import vn.ahaay.ambacsi.api.ambacsi.profile.ProfileChangeRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateProfileDoctorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateProfileDoctorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateProfileDoctorFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_USERNAME = "param_username";
    private static final String ARG_PARAM_EMAIL = "param_email";
    private Unbinder unbinder;

    @BindView(vn.ahaay.ambacsi.R.id.createDoctorUsername) EditText createDoctorUsername;
    @BindView(vn.ahaay.ambacsi.R.id.createDoctorEmail) EditText createDoctorEmail;
    @BindView(vn.ahaay.ambacsi.R.id.createDoctorFirstName) EditText createDoctorFirstName;
    @BindView(vn.ahaay.ambacsi.R.id.createDoctorLastName) EditText createDoctorLastName;
    @BindView(vn.ahaay.ambacsi.R.id.createDoctorGender) EditText createDoctorGender;
    @BindView(vn.ahaay.ambacsi.R.id.createDoctorDob) EditText createDoctorDob;
    @BindView(vn.ahaay.ambacsi.R.id.createDoctorIdCardNo) EditText createDoctorIdCardNo;
    @BindView(vn.ahaay.ambacsi.R.id.createDoctorHomeAddress) EditText createDoctorHomeAddress;
    @BindView(vn.ahaay.ambacsi.R.id.createDoctorExperienceYear) EditText createDoctorExperienceYear;
    @BindView(R.id.progressBarCreateDoctor) ProgressBar progressBarCreateDoctor;
    @BindView(R.id.submitCreateDoctor) Button submitCreateDoctor;

    private String username;
    private String email;

    private boolean onLoading = false;

    private OnFragmentInteractionListener mListener;

    public CreateProfileDoctorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param _username Parameter 1.
     * @param _email Parameter 2.
     *
     * @return A new instance of fragment CreateProfileUserFragment.
     */
    public static CreateProfileDoctorFragment newInstance(@Nullable String _username, @Nullable String _email) {
        CreateProfileDoctorFragment fragment = new CreateProfileDoctorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_USERNAME, _username != null ? _username : "");
        args.putString(ARG_PARAM_EMAIL, _email != null ? _email : "");
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString(ARG_PARAM_USERNAME);
            email = getArguments().getString(ARG_PARAM_EMAIL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View __view = inflater.inflate(vn.ahaay.ambacsi.R.layout.fragment_create_profile_doctor, container, false);
        unbinder = ButterKnife.bind(this, __view);

        createDoctorUsername.setText(username);
        createDoctorEmail.setText(email);

        return __view;
    }

    @OnClick(R.id.submitCreateDoctor) void submit() {
        String __firstName = createDoctorFirstName.getText().toString();
        String __lastName = createDoctorLastName.getText().toString();
        String __genderString = createDoctorGender.getText().toString();
        String __dobString = createDoctorDob.getText().toString();
        String __idCardNo = createDoctorIdCardNo.getText().toString();
        String __homeAddress = createDoctorHomeAddress.getText().toString();
        String __experienceYear = createDoctorExperienceYear.getText().toString();


        if (TextUtils.isEmpty(__firstName)) {
            createDoctorFirstName.requestFocus();
            createDoctorFirstName.setError(getResources().getString(vn.ahaay.ambacsi.R.string.create_profile_error_field_required));
            return;
        } else if (TextUtils.isEmpty(__lastName)) {
            createDoctorLastName.requestFocus();
            createDoctorLastName.setError(getResources().getString(vn.ahaay.ambacsi.R.string.create_profile_error_field_required));
            return;
        } else if (TextUtils.isEmpty(__genderString)) {
            createDoctorGender.requestFocus();
            createDoctorGender.setError(getResources().getString(vn.ahaay.ambacsi.R.string.create_profile_error_field_required));
            return;
        } else if (TextUtils.isEmpty(__dobString)) {
            createDoctorDob.requestFocus();
            createDoctorDob.setError(getResources().getString(vn.ahaay.ambacsi.R.string.create_profile_error_field_required));
            return;
        } else if (TextUtils.isEmpty(__idCardNo)) {
            createDoctorIdCardNo.requestFocus();
            createDoctorIdCardNo.setError(getResources().getString(vn.ahaay.ambacsi.R.string.create_profile_error_field_required));
            return;
        } else if (TextUtils.isEmpty(__homeAddress)) {
            createDoctorHomeAddress.requestFocus();
            createDoctorHomeAddress.setError(getResources().getString(vn.ahaay.ambacsi.R.string.create_profile_error_field_required));
            return;
        } else if (TextUtils.isEmpty(__experienceYear)) {
            createDoctorExperienceYear.requestFocus();
            createDoctorExperienceYear.setError(getResources().getString(vn.ahaay.ambacsi.R.string.create_profile_error_field_required));
            return;
        }

        int __gender = __genderString.equals(getResources().getString(vn.ahaay.ambacsi.R.string.gender_male)) ? 0 :
                __genderString.equals(getResources().getString(vn.ahaay.ambacsi.R.string.gender_female))? 1 : 2;

        Calendar __dob = Calendar.getInstance();
        String myFormat = getResources().getString(vn.ahaay.ambacsi.R.string.date_format);
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        try {
            __dob.setTime(sdf.parse(__dobString));
        } catch (ParseException _e) {
            _e.printStackTrace();
        }

        DoctorProfileChangeRequest __request = new DoctorProfileChangeRequest()
                .setFirstName(__firstName)
                .setLastName(__lastName)
                .setGender(__gender)
                .setDob(__dob)
                .setIdCardNo(__idCardNo)
                .setHomeAddress(__homeAddress)
                .setExperienceYear(Integer.parseInt(__experienceYear));

        if (mListener != null) {
            try {
                mListener.createDoctorProfile(__request);
            } catch (AmBacSiAuthException _e) {
                _e.printStackTrace();
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @OnClick(vn.ahaay.ambacsi.R.id.createDoctorGender) void selectGender() {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // 2. Chain together various setter methods to set the dialog characteristics
        // get checked item
        int checkedItem = 0;
        if (createDoctorGender.getText().toString().equals(getResources().getString(vn.ahaay.ambacsi.R.string.gender_male))) {
            checkedItem = 1;
        } else if (createDoctorGender.getText().toString().equals(getResources().getString(vn.ahaay.ambacsi.R.string.gender_female))) {
            checkedItem = 2;
        } else if (createDoctorGender.getText().toString().equals(getResources().getString(vn.ahaay.ambacsi.R.string.gender_other))) {
            checkedItem = 3;
        }
        // Specify the list array, the items to be selected by default (null for none),
        // and the listener through which to receive callbacks when items are selected
        builder.setSingleChoiceItems(vn.ahaay.ambacsi.R.array.gender_select, checkedItem,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialogInterface, int _i) {
                        switch (_i) {
                            case 1:
                                createDoctorGender.setText(getResources().getString(vn.ahaay.ambacsi.R.string.gender_male));
                                break;
                            case 2:
                                createDoctorGender.setText(getResources().getString(vn.ahaay.ambacsi.R.string.gender_female));
                                break;
                            case 3:
                                createDoctorGender.setText(getResources().getString(vn.ahaay.ambacsi.R.string.gender_other));
                                break;
                            default:
                                createDoctorGender.setText("");
                        }
                        _dialogInterface.dismiss();
                    }
                });

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @OnClick(vn.ahaay.ambacsi.R.id.createDoctorDob) void selectDob() {
        final Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(myCalendar);
            }
        };

        new DatePickerDialog(getContext(), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel(Calendar myCalendar) {
        String myFormat = getResources().getString(vn.ahaay.ambacsi.R.string.date_format);
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        createDoctorDob.setText(sdf.format(myCalendar.getTime()));
    }

    public void startLoading() {
        onLoading = true;
        submitCreateDoctor.setVisibility(View.GONE);
        progressBarCreateDoctor.setVisibility(View.VISIBLE);
    }

    public void stopLoading() {
        onLoading = false;
        submitCreateDoctor.setVisibility(View.VISIBLE);
        progressBarCreateDoctor.setVisibility(View.GONE);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        void createDoctorProfile(DoctorProfileChangeRequest _request) throws AmBacSiAuthException;
    }
}
