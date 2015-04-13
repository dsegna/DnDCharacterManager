package dps924.ddcharactermanager.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

import dps924.ddcharactermanager.rules.AlignmentRule;

public class AlignmentSpinnerAdapter extends ArrayAdapter<String> {

    public AlignmentSpinnerAdapter(Context context, int resourceId, List<AlignmentRule> alignmentRules) {
        super(context, resourceId, new ArrayList<String>());
        for(AlignmentRule alignmentRule : alignmentRules) {
            add(alignmentRule.getName());
        }
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
}
