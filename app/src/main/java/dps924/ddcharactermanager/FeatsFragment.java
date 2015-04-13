package dps924.ddcharactermanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import dps924.ddcharactermanager.rules.FeatRule;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FeatsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FeatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeatsFragment extends Fragment {
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
     * @return A new instance of fragment FeatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeatsFragment newInstance(String param1, String param2) {
        FeatsFragment fragment = new FeatsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FeatsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_feats, container, false);
        ListView featsListView = (ListView) view.findViewById(R.id.featList);
        final ArrayList<FeatRule> feats = characterActivity.getCharacter().getFeats();
        final FeatListAdapter featListAdapter = new FeatListAdapter(characterActivity, R.layout.feats_list_item, feats);
        featsListView.setAdapter(featListAdapter);
        featsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, final int i, long l) {
                FeatRule f = (FeatRule) av.getItemAtPosition(i);

                final View featEditDialog = LayoutInflater.from(characterActivity).inflate(R.layout.feat_edit_dialog, null);
                final EditText fName = (EditText) featEditDialog.findViewById(R.id.fName);
                final EditText fEffect = (EditText) featEditDialog.findViewById(R.id.fEffect);

                fName.setText(f.getName());
                fEffect.setText(f.getEffect());

                new AlertDialog.Builder(characterActivity)
                        .setTitle("Edit feat")
                        .setView(featEditDialog)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Closes dialog
                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                feats.get(i).setName(fName.getText().toString());
                                feats.get(i).setEffect(fEffect.getText().toString());
                                //May need to update character objects feat data
                                //Reload Feats ListView using FeatListAdapter
                                featListAdapter.notifyDataSetChanged();
                            }
                        })
                        .show();
            }
        });
        return view;
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
