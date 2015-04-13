package dps924.ddcharactermanager.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import dps924.ddcharactermanager.CharacterActivity;
import dps924.ddcharactermanager.DDCharacter;
import dps924.ddcharactermanager.R;
import dps924.ddcharactermanager.rules.SkillRule;

public class SkillListAdapter extends ArrayAdapter<SkillRule> {
    private CharacterActivity context;

    public SkillListAdapter(CharacterActivity context, int resourceId, List<SkillRule> skills) {
        super(context, resourceId, skills);
        this.context = context;
    }
    private class ViewHolder {
        TextView txtName;
        TextView txtAttribute;
        CheckBox trained;
        TextView txtValue;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        SkillRule skill = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.skills_list_item, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.skillName);
            holder.txtAttribute = (TextView) convertView.findViewById(R.id.skillModifier);
            holder.trained = (CheckBox) convertView.findViewById(R.id.skillTrained);
            holder.txtValue = (TextView) convertView.findViewById(R.id.skillValue);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtName.setText(skill.getName());
        holder.txtAttribute.setText(skill.getAbilityModKey());
        DDCharacter character = context.getCharacter();
        holder.trained.setChecked(character.getIsTrained(skill.getName()));
        holder.txtValue.setText("" + character.getSkillValue(skill.getName()));
        return convertView;
    }
}
