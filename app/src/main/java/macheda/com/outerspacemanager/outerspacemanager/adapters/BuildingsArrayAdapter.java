package macheda.com.outerspacemanager.outerspacemanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import macheda.com.outerspacemanager.outerspacemanager.R;
import macheda.com.outerspacemanager.outerspacemanager.classes.Building;

/**
 * Created by adammacheda on 14/05/2018.
 */

public class BuildingsArrayAdapter extends ArrayAdapter<Building> {
    private final Context context;
    public BuildingsArrayAdapter(Context context, List<Building> values) {
        super(context, R.layout.row_building, values);
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_building, parent, false);
        TextView buildingName = (TextView) rowView.findViewById(R.id.buildingName);
        ImageView buildingImage = (ImageView) rowView.findViewById(R.id.buildingImageView);
        TextView levelBuilding = (TextView) rowView.findViewById(R.id.buildingLevel);

        buildingName.setText(getItem(position).getName());
        levelBuilding.setText("Niveau "+getItem(position).getLevel());
        Picasso.get().load(getItem(position).getImageUrl()).resize(150, 150).centerCrop().into(buildingImage);
        return rowView;
    }
}