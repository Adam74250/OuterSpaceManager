package macheda.com.outerspacemanager.outerspacemanager.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import macheda.com.outerspacemanager.outerspacemanager.R;
import macheda.com.outerspacemanager.outerspacemanager.classes.Building;

public class BatimentFragment extends Fragment {
    private TextView nomBatiment;
    private ImageView buildingImage;
    private TextView levelBuilding;
    private TextView costForNextLevel;
    private TextView effectByLevel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_batiment,container);
        nomBatiment = (TextView)v.findViewById(R.id.buildingName);
        buildingImage = (ImageView)v.findViewById(R.id.imageViewBuilding);
        levelBuilding = (TextView)v.findViewById(R.id.levelBuilding);
        costForNextLevel = (TextView)v.findViewById(R.id.costForNextLevel);
        effectByLevel = (TextView)v.findViewById(R.id.effectByLevel);

        return v;

    }

    public void update(Building leBatiment) {
        Picasso.get().load(leBatiment.getImageUrl()).resize(300, 300).centerCrop().into(buildingImage);
        nomBatiment.setText(leBatiment.getName());
        levelBuilding.setText("Niveau : "+leBatiment.getLevel());

        int IcostForNextLevel = (leBatiment.getLevel()+1)*leBatiment.getGasCostByLevel()+leBatiment.getGasCostByLevel();

        effectByLevel.setText("Effet escompté : "+leBatiment.getEffect());
        costForNextLevel.setText("Coût construction : "+IcostForNextLevel);
    }
}