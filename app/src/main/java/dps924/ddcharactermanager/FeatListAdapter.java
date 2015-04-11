package dps924.ddcharactermanager;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import dps924.ddcharactermanager.rules.FeatRule;

public class FeatListAdapter extends ArrayAdapter<FeatRule> {

    private Context context;

    public FeatListAdapter(Context context, int resourceId, List<FeatRule> feats) {
        super(context, resourceId, feats);
        this.context = context;
    }
    private class ViewHolder {
        TextView txtName;
        TextView txtEffect;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        FeatRule feat = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.feats_list_item, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.featName);
            holder.txtEffect = (TextView) convertView.findViewById(R.id.featEffect);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtName.setText(feat.getName());
        holder.txtEffect.setText(feat.getEffect());
        return convertView;
    }
}
