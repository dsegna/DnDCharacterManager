package dps924.ddcharactermanager;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CharacterListAdapter extends ArrayAdapter<DDCharacter>{

    private Context context;

    public CharacterListAdapter(Context context, int resourceId, List<DDCharacter> characters) {
        super(context, resourceId, characters);
        this.context = context;
    }

    private class ViewHolder {
        TextView txtName;
        TextView txtLevel;
        TextView txtRace;
        TextView txtClass;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = null;
        DDCharacter character = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.character_list_item, null);

            holder = new ViewHolder();
            holder.txtName = (TextView)convertView.findViewById(R.id.charName);
            holder.txtLevel = (TextView)convertView.findViewById(R.id.charLevel);
            holder.txtRace = (TextView)convertView.findViewById(R.id.charRace);
            holder.txtClass = (TextView)convertView.findViewById(R.id.charClass);
            convertView.setTag(holder);
        }else
            holder = (ViewHolder) convertView.getTag();
        ImageView iv;
        holder.txtName.setText(character.charName);
        holder.txtLevel.setText(Integer.toString(character.charLevel));
        holder.txtRace.setText(character.charRace);
        holder.txtClass.setText(character.charClass);

        return convertView;
    }
}
