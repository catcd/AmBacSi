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
import android.widget.DatePicker;
import android.widget.EditText;

import vn.ahaay.ambacsi.api.ambacsi.profile.ClinicalCenterProfileChangeRequest;
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
 * {@link CreateProfileClinicalCenterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateProfileClinicalCenterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateProfileClinicalCenterFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_USERNAME = "param_username";
    private static final String ARG_PARAM_EMAIL = "param_email";
    private Unbinder unbinder;

    @BindView(vn.ahaay.ambacsi.R.id.createClinicalCenterUsername) EditText createClinicalCenterUsername;
    @BindView(vn.ahaay.ambacsi.R.id.createClinicalCenterEmail) EditText createClinicalCenterEmail;
    @BindView(vn.ahaay.ambacsi.R.id.createClinicalCenterName) EditText createClinicalCenterName;
    @BindView(vn.ahaay.ambacsi.R.id.createClinicalCenterType) EditText createClinicalCenterType;
    @BindView(vn.ahaay.ambacsi.R.id.createClinicalCenterFoundYear) EditText createClinicalCenterFoundYear;
    @BindView(vn.ahaay.ambacsi.R.id.createClinicalCenterTel) EditText createClinicalCenterTel;
    @BindView(vn.ahaay.ambacsi.R.id.createClinicalCenterLocation) EditText createClinicalCenterLocation;

    private String username;
    private String email;

    private OnFragmentInteractionListener mListener;

    public CreateProfileClinicalCenterFragment() {
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
    public static CreateProfileClinicalCenterFragment newInstance(@Nullable String _username, @Nullable String _email) {
        CreateProfileClinicalCenterFragment fragment = new CreateProfileClinicalCenterFragment();
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
        View view = inflater.inflate(vn.ahaay.ambacsi.R.layout.fragment_create_profile_clinical_center, container, false);
        unbinder = ButterKnife.bind(this, view);

        createClinicalCenterUsername.setText(username);
        createClinicalCenterEmail.setText(email);

        return view;
    }

    @OnClick(vn.ahaay.ambacsi.R.id.submit) void submit() {
        String __name = createClinicalCenterName.getText().toString();
        String __typeString = createClinicalCenterType.getText().toString();
        String __fYearString = createClinicalCenterFoundYear.getText().toString();
        String __tel = createClinicalCenterTel.getText().toString();
        String __location = createClinicalCenterLocation.getText().toString();


        if (TextUtils.isEmpty(__name)) {
            createClinicalCenterName.requestFocus();
            createClinicalCenterName.setError(getResources().getString(vn.ahaay.ambacsi.R.string.create_profile_error_field_required));
            return;
        } else if (TextUtils.isEmpty(__typeString)) {
            createClinicalCenterType.requestFocus();
            createClinicalCenterType.setError(getResources().getString(vn.ahaay.ambacsi.R.string.create_profile_error_field_required));
            return;
        } else if (TextUtils.isEmpty(__fYearString)) {
            createClinicalCenterFoundYear.requestFocus();
            createClinicalCenterFoundYear.setError(getResources().getString(vn.ahaay.ambacsi.R.string.create_profile_error_field_required));
            return;
        } else if (TextUtils.isEmpty(__tel)) {
            createClinicalCenterTel.requestFocus();
            createClinicalCenterTel.setError(getResources().getString(vn.ahaay.ambacsi.R.string.create_profile_error_field_required));
            return;
        } else if (TextUtils.isEmpty(__location)) {
            createClinicalCenterLocation.requestFocus();
            createClinicalCenterLocation.setError(getResources().getString(vn.ahaay.ambacsi.R.string.create_profile_error_field_required));
            return;
        }

        int __type = __typeString.equals(getResources().getString(vn.ahaay.ambacsi.R.string.clinical_center_type_private)) ? 0 : 1;

        Calendar __fYear = Calendar.getInstance();
        String myFormat = getResources().getString(vn.ahaay.ambacsi.R.string.date_format);
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        try {
            __fYear.setTime(sdf.parse(__fYearString));
        } catch (ParseException _e) {
            _e.printStackTrace();
        }

        ClinicalCenterProfileChangeRequest __request = new ClinicalCenterProfileChangeRequest()
                .setName(__name)
                .setType(__type)
                .setFoundYear(__fYear)
                .setTel(__tel)
                .setLocation(__location);

        if (mListener != null) {
            mListener.createProfile(__request);
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

    @OnClick(vn.ahaay.ambacsi.R.id.createClinicalCenterType) void selectType() {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // 2. Chain together various setter methods to set the dialog characteristics
        // get checked item
        int checkedItem = 0;
        if (createClinicalCenterType.getText().toString().equals(getResources().getString(vn.ahaay.ambacsi.R.string.clinical_center_type_private))) {
            checkedItem = 1;
        } else if (createClinicalCenterType.getText().toString().equals(getResources().getString(vn.ahaay.ambacsi.R.string.clinical_center_type_hospital))) {
            checkedItem = 2;
        }
        // Specify the list array, the items to be selected by default (null for none),
        // and the listener through which to receive callbacks when items are selected
        builder.setSingleChoiceItems(vn.ahaay.ambacsi.R.array.clinical_center_type_select, checkedItem,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialogInterface, int _i) {
                        switch (_i) {
                            case 1:
                                createClinicalCenterType.setText(getResources().getString(vn.ahaay.ambacsi.R.string.clinical_center_type_private));
                                break;
                            case 2:
                                createClinicalCenterType.setText(getResources().getString(vn.ahaay.ambacsi.R.string.clinical_center_type_hospital));
                                break;
                            default:
                                createClinicalCenterType.setText("");
                        }
                        _dialogInterface.dismiss();
                    }
                });

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @OnClick(vn.ahaay.ambacsi.R.id.createClinicalCenterFoundYear) void selectFoundYear() {
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
        createClinicalCenterFoundYear.setText(sdf.format(myCalendar.getTime()));
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        void createProfile(ProfileChangeRequest _request);
    }
}
