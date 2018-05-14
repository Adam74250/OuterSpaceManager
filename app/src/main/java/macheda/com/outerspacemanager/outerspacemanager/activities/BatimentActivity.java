package macheda.com.outerspacemanager.outerspacemanager.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

import macheda.com.outerspacemanager.outerspacemanager.R;
import macheda.com.outerspacemanager.outerspacemanager.classes.Building;
import macheda.com.outerspacemanager.outerspacemanager.fragments.BatimentFragment;

public class BatimentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batiment);

        Building leBatiment = new Gson().fromJson(getIntent().getStringExtra("leBatiment"), Building.class);
        BatimentFragment unBatiment = (BatimentFragment) getSupportFragmentManager().findFragmentById(R.id.batimentFragment);
        unBatiment.update(leBatiment);
    }

}
