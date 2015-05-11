package dps924.ddcharactermanager.adapters;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import dps924.ddcharactermanager.CharacterActivity;
import dps924.ddcharactermanager.DDCharacter;
import dps924.ddcharactermanager.rules.RaceRule;

public class RaceSpinnerAdapter extends ArrayAdapter<String> {
    public AdapterView.OnItemSelectedListener onItemSelectedListener;

    public RaceSpinnerAdapter(Context context, int resourceId, List<RaceRule> raceRules) {
        super(context, resourceId, new ArrayList<String>());
        for (RaceRule raceRule : raceRules) {
            add(raceRule.getName());
        }
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final CharacterActivity characterActivity = (CharacterActivity) context;
        final DDCharacter character = characterActivity.getCharacter();
        onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                character.setRace(character.getRuleDB().getRaceRules().get(getItem(position)));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        };
    }
}
