package dps924.ddcharactermanager.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import dps924.ddcharactermanager.rules.ClassRule;

public class ClassSpinnerAdapter extends ArrayAdapter<String> {

    public ClassSpinnerAdapter(Context context, int resourceId, List<ClassRule> classRules) {
        super(context, resourceId, new ArrayList<String>());
        for(ClassRule classRule : classRules) {
            add(classRule.getName());
        }
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
}
