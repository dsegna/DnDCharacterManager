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
import dps924.ddcharactermanager.rules.ItemRule;

public class ItemListAdapter extends ArrayAdapter<ItemRule> {

    private Context context;

    public ItemListAdapter(Context context, int resourceId, List<ItemRule> items) {
        super(context, resourceId, items);
        this.context = context;
    }
    private class ViewHolder {
        TextView txtName;
        TextView txtDetails;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ItemRule item = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.feats_list_item, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.itemName);
            holder.txtDetails = (TextView) convertView.findViewById(R.id.itemDetails);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtName.setText(item.getName());
        holder.txtDetails.setText(item.getDetails());
        return convertView;
    }
}
