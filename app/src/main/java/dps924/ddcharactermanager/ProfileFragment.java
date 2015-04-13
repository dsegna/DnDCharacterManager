package dps924.ddcharactermanager;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import dps924.ddcharactermanager.adapters.AlignmentSpinnerAdapter;
import dps924.ddcharactermanager.adapters.DeitySpinnerAdapter;
import dps924.ddcharactermanager.adapters.RaceSpinnerAdapter;
import dps924.ddcharactermanager.rules.AlignmentRule;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private CharacterActivity characterActivity;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        characterActivity = (CharacterActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initProfileFragment(view);
        return view;
    }
    private void initProfileFragment(View view) {
        DDCharacter character = characterActivity.getCharacter();
        //Set name
        EditText tmpField = (EditText) view.findViewById(R.id.editName);
        tmpField.setText(character.getName());
        //Set level
        tmpField = (EditText) view.findViewById(R.id.editLevel);
        tmpField.setText(String.valueOf(character.getLevel()));
        //Set exp
        tmpField = (EditText) view.findViewById(R.id.editExp);
        tmpField.setText(String.valueOf(character.getExp()));
        //Set paragon
        tmpField = (EditText) view.findViewById(R.id.editParagon);
        tmpField.setText(character.getParagon());
        //Set epic
        tmpField = (EditText) view.findViewById(R.id.editEpic);
        tmpField.setText(character.getEpic());
        //Set description
        tmpField = (EditText) view.findViewById(R.id.editDesc);
        tmpField.setText(character.getDesc());
        //Fill Race Spinner
        Spinner raceSpinner = (Spinner) view.findViewById(R.id.raceSpinner);
        RaceSpinnerAdapter raceSpinnerAdapter = new RaceSpinnerAdapter(characterActivity,
                android.R.layout.simple_spinner_item, character.getRuleDB().getRaceRulesList());
        raceSpinner.setAdapter(raceSpinnerAdapter);
        //Fill Alignments Spinner
        Spinner alignmentSpinner = (Spinner) view.findViewById(R.id.alignmentSpinner);
        AlignmentSpinnerAdapter alignmentSpinnerAdapter = new AlignmentSpinnerAdapter(characterActivity,
                android.R.layout.simple_spinner_item, character.getRuleDB().getAlignmentRulesList());
        alignmentSpinner.setAdapter(alignmentSpinnerAdapter);
        //Fill Deity Spinner
        Spinner deitySpinner = (Spinner) view.findViewById(R.id.deitySpinner);
        DeitySpinnerAdapter deitySpinnerAdapter = new DeitySpinnerAdapter(characterActivity,
                android.R.layout.simple_spinner_item, character.getRuleDB().getDeityRulesList());
        deitySpinner.setAdapter(deitySpinnerAdapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
