package dps924.ddcharactermanager;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import dps924.ddcharactermanager.characters.CharacterDatabase;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CharactersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CharactersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CharactersFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private ListView characterList;
    private List<DDCharacter> characters;
    private View view;
    private CharacterActivity characterActivity;
    private CharacterDatabase charDB;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CharactersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CharactersFragment newInstance(String param1, String param2) {
        CharactersFragment fragment = new CharactersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CharactersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        view = getView();
        characterActivity = (CharacterActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_characters, container, false);
        characterList = (ListView) view.findViewById(R.id.characterList);
        characters = new ArrayList<DDCharacter>();
        /*
        characters.add(new DDCharacter("Mark", 1, "Dwarf", "Fighter"));
        characters.add(new DDCharacter("John", 2, "Human", "Paladin"));
        characters.add(new DDCharacter("Bell", 3, "Elf", "Wizard"));
        characters.add(new DDCharacter("Gnar", 4, "Gnome", "Artificer"));
        characters.add(new DDCharacter("Towelie", 5, "Towel", "Mesmer"));
        */
        charDB = characterActivity.getCharacterDatabase(); //Should get from characterActivity
        characters = charDB.getCharactersList();

        CharacterListAdapter characterAdapter = new CharacterListAdapter(this.getActivity(), R.layout.character_list_item, characters);
        characterList.setAdapter(characterAdapter);
        final CharacterActivity charActivityHolder = characterActivity;
        characterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DDCharacter c = (DDCharacter) parent.getItemAtPosition(position);
                charActivityHolder.selectCharacter(c);
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
