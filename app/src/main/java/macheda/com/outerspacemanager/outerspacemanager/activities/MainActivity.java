package macheda.com.outerspacemanager.outerspacemanager.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import macheda.com.outerspacemanager.outerspacemanager.R;
import macheda.com.outerspacemanager.outerspacemanager.classes.Config;
import macheda.com.outerspacemanager.outerspacemanager.classes.CurrentUser;
import macheda.com.outerspacemanager.outerspacemanager.services.OuterSpaceService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView usernameText;
    private String token;
    public static final String OSM_PREFS = "outerSpaceManagerPrefs";
    private TextView pointsText;
    private Button vueGeneraleBouton;
    private Button batimentsBouton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pointsText = findViewById(R.id.pointsTextView);
        usernameText = findViewById(R.id.usernameTextView);
        vueGeneraleBouton = findViewById(R.id.vueGeneraleBouton);
        batimentsBouton = findViewById(R.id.batimentsBouton);

        final ProgressDialog pDialog = ProgressDialog.show(MainActivity.this, "",
                "Récupération des informations...", true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SharedPreferences settings = getSharedPreferences(OSM_PREFS, 0);
        token = settings.getString("auth_token", "Username");

        OuterSpaceService service = retrofit.create(OuterSpaceService.class);
        Call<CurrentUser> rep = service.currentUser(token);

        rep.enqueue(new Callback<CurrentUser>() {
            @Override
            public void onResponse(Call<CurrentUser> call, Response<CurrentUser> response) {
                pDialog.dismiss();
                if(response.code() == 200) {
                    usernameText.setText(response.body().getUsername());
                    pointsText.setText("Points: " + response.body().getPoints());
                } else {
                    Toast.makeText(getApplicationContext(), "Une erreur est survenue lors de la récupération des infos.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CurrentUser> call, Throwable t) {

            }
        });

        this.vueGeneraleBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent VueGeneraleActivity = new Intent(getApplicationContext(), macheda.com.outerspacemanager.outerspacemanager.activities.VueGeneraleActivity.class);
                startActivity(VueGeneraleActivity);
            }
        });


        this.batimentsBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BatimentsActivity = new Intent(getApplicationContext(), macheda.com.outerspacemanager.outerspacemanager.activities.BatimentsActivity.class);
                startActivity(BatimentsActivity);
            }
        });
    }
}
