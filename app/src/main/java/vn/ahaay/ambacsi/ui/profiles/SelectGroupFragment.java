package vn.ahaay.ambacsi.ui.profiles;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vn.ahaay.ambacsi.api.ambacsi.Constant;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectGroupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectGroupFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private Unbinder unbinder;

    public SelectGroupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SelectGroupFragment.
     */
    public static SelectGroupFragment newInstance() {
        SelectGroupFragment fragment = new SelectGroupFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View __view = inflater.inflate(vn.ahaay.ambacsi.R.layout.fragment_select_group, container, false);
        unbinder = ButterKnife.bind(this, __view);

        return __view;
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

    @OnClick(vn.ahaay.ambacsi.R.id.userClick) void selectGroupUser() {
        if (mListener != null) {
            mListener.selectGroup(Constant.UserGroupConstant.GROUP_USER);
        }
    }

    @OnClick(vn.ahaay.ambacsi.R.id.doctorClick) void selectGroupDoctor() {
        if (mListener != null) {
            mListener.selectGroup(Constant.UserGroupConstant.GROUP_DOCTOR);
        }
    }

    @OnClick(vn.ahaay.ambacsi.R.id.clinicalCenterClick) void selectGroupClinicalCenter() {
        if (mListener != null) {
            mListener.selectGroup(Constant.UserGroupConstant.GROUP_CLINICAL_CENTER);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        void selectGroup(int group);
    }
}
