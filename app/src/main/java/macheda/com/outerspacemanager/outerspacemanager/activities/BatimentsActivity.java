package macheda.com.outerspacemanager.outerspacemanager.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import macheda.com.outerspacemanager.outerspacemanager.R;
import macheda.com.outerspacemanager.outerspacemanager.classes.Config;
import macheda.com.outerspacemanager.outerspacemanager.classes.CurrentUser;
import macheda.com.outerspacemanager.outerspacemanager.fragments.BatimentFragment;
import macheda.com.outerspacemanager.outerspacemanager.fragments.ListBatimentsFragment;
import macheda.com.outerspacemanager.outerspacemanager.services.OuterSpaceService;
import macheda.com.outerspacemanager.outerspacemanager.services.requests.BuildingsRequest;
import macheda.com.outerspacemanager.outerspacemanager.services.responses.BuildingsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BatimentsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batiments);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListBatimentsFragment listeBatiments = (ListBatimentsFragment)getSupportFragmentManager().findFragmentById(R.id.listeBatiments);
        BatimentFragment unBatiment = (BatimentFragment)getSupportFragmentManager().findFragmentById(R.id.batimentFragment);
        if(unBatiment == null|| !unBatiment.isInLayout()){
            Intent i = new Intent(getApplicationContext(),BatimentActivity.class);
            i.putExtra("leBatiment", new Gson().toJson(listeBatiments.listItems.get(position)));
            startActivity(i);
        } else {
            unBatiment.update(listeBatiments.listItems.get(position));
        }
    }
}
