package dps924.ddcharactermanager.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import dps924.ddcharactermanager.rules.DeityRule;

public class DeitySpinnerAdapter extends ArrayAdapter<String> {

    public DeitySpinnerAdapter(Context context, int resourceId, List<DeityRule> deityRules) {
        super(context, resourceId, new ArrayList<String>());
        for(DeityRule deityRule : deityRules) {
            add(deityRule.getName());
        }
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
}
