package dps924.ddcharactermanager;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import dps924.ddcharactermanager.rules.RuleDatabase;
import dps924.ddcharactermanager.rules.SkillRule;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SkillsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SkillsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SkillsFragment extends Fragment {
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
     * @return A new instance of fragment SkillsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SkillsFragment newInstance(String param1, String param2) {
        SkillsFragment fragment = new SkillsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SkillsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_skills, container, false);
        initSkillsFragment(view);
        return view;
    }

    private void initSkillsFragment(View view) {
        TableRow.LayoutParams col1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1);
        //col1.gravity = Gravity.LEFT; //col1.span = 1;
        TableRow.LayoutParams col2 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1);
        //col2.gravity = Gravity.CENTER_HORIZONTAL;
        TableRow.LayoutParams col3 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1);
        //col3.gravity = Gravity.CENTER_HORIZONTAL;
        TableRow.LayoutParams col4 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1);
        //col4.gravity = Gravity.CENTER_HORIZONTAL;

        TableRow.LayoutParams  centerHorizontalGravity = new TableRow.LayoutParams();
        centerHorizontalGravity.gravity = Gravity.CENTER_HORIZONTAL;
        TableRow.LayoutParams  centerGravity = new TableRow.LayoutParams();
        centerGravity.gravity = Gravity.CENTER;
        LinearLayout.LayoutParams wrapContent = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams();
        rowParams.gravity = Gravity.CENTER;


        RuleDatabase ruleDB = characterActivity.getCharacter().getRuleDB();
        TableLayout table = (TableLayout) view.findViewById(R.id.skillsTable);
        for(SkillRule rule : ruleDB.getSkillRules().values()) {
            // Create a new row
            TableRow row = new TableRow(characterActivity);
            row.setLayoutParams(rowParams);
            //COLUMN 1
            TextView test1 = new TextView(characterActivity);
            test1.setText("Skill");
            test1.setLayoutParams(col1);
            row.addView(test1);
            //COLUMN 2
            TextView test2 = new TextView(characterActivity);
            test2.setText(rule.getAbilityModKey());
            test2.setLayoutParams(col2);
            row.addView(test2);
            //COLUMN 3
            TextView test3 = new TextView(characterActivity);
            test3.setText("Trained");
            test3.setLayoutParams(col3);
            row.addView(test3);
            //COLUMN 4
            TextView test4 = new TextView(characterActivity);
            test4.setText("Value");
            test4.setLayoutParams(col4);
            row.addView(test4);
            /*
            //Add skill name
            TextView skillName = new TextView(characterActivity);
            skillName.setText(rule.getName());
            skillName.setLayoutParams(leftGravity);
            row.addView(skillName);
            //Add attribute shortcode
            TextView attribute = new TextView(characterActivity);
            attribute.setText(rule.getAbilityModKey());
            attribute.setLayoutParams(centerHorizontalGravity);
            row.addView(attribute);
            //Add trained checkbox
            LinearLayout ll = new LinearLayout(characterActivity);
            ll.setLayoutParams(centerGravity);
            CheckBox trained = new CheckBox(characterActivity);
            trained.setLayoutParams(wrapContent);
            ll.addView(trained);
            row.addView(ll);
            //Add value text
            TextView value = new TextView(characterActivity);
            value.setText("0");
            value.setLayoutParams(centerHorizontalGravity);
            row.addView(value);
            */
            //Add the created row
            table.addView(row);
        }
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
