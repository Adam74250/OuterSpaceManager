package macheda.com.outerspacemanager.outerspacemanager.activities;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import macheda.com.outerspacemanager.outerspacemanager.R;
import macheda.com.outerspacemanager.outerspacemanager.classes.Config;
import macheda.com.outerspacemanager.outerspacemanager.classes.CurrentUser;
import macheda.com.outerspacemanager.outerspacemanager.services.OuterSpaceService;
import macheda.com.outerspacemanager.outerspacemanager.services.requests.BuildingsRequest;
import macheda.com.outerspacemanager.outerspacemanager.services.responses.BuildingsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BatimentsActivity extends AppCompatActivity {
    private String token;
    public static final String OSM_PREFS = "outerSpaceManagerPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batiments);


        final ProgressDialog pDialog = ProgressDialog.show(BatimentsActivity.this, "",
                "Récupération des informations...", true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SharedPreferences settings = getSharedPreferences(OSM_PREFS, 0);
        token = settings.getString("auth_token", "Username");

        OuterSpaceService service = retrofit.create(OuterSpaceService.class);
        Call<BuildingsResponse> rep = service.buildings(token);

        rep.enqueue(new Callback<BuildingsResponse>() {
            @Override
            public void onResponse(Call<BuildingsResponse> call, Response<BuildingsResponse> response) {
                pDialog.dismiss();
                if(response.code() == 200) {

                } else {
                    Toast.makeText(getApplicationContext(), "Une erreur est survenue lors de la récupération des infos.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BuildingsResponse> call, Throwable t) {

            }
        });
    }
}
