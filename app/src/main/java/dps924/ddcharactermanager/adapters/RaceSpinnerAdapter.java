package dps924.ddcharactermanager.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import dps924.ddcharactermanager.rules.RaceRule;

public class RaceSpinnerAdapter extends ArrayAdapter<String> {

    public RaceSpinnerAdapter(Context context, int resourceId, List<RaceRule> raceRules) {
        super(context, resourceId, new ArrayList<String>());
        for(RaceRule raceRule : raceRules) {
            add(raceRule.getName());
        }
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
}
