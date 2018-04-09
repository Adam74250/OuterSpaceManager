package macheda.com.outerspacemanager.outerspacemanager.Activities;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import macheda.com.outerspacemanager.outerspacemanager.R;
import macheda.com.outerspacemanager.outerspacemanager.Services.OuterSpaceService;
import macheda.com.outerspacemanager.outerspacemanager.Services.Requests.ConnectionRequest;
import macheda.com.outerspacemanager.outerspacemanager.Services.Responses.ConnectionResponse;
import macheda.com.outerspacemanager.outerspacemanager.Services.Responses.CurrentUserResponse;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pointsText = findViewById(R.id.pointsTextView);
        usernameText = findViewById(R.id.usernameTextView);

        final ProgressDialog pDialog = ProgressDialog.show(MainActivity.this, "",
                "Récupération des informations...", true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SharedPreferences settings = getSharedPreferences(OSM_PREFS, 0);
        token = settings.getString("auth_token", "Username");

        OuterSpaceService service = retrofit.create(OuterSpaceService.class);
        Call<CurrentUserResponse> rep = service.currentUser(token);

        rep.enqueue(new Callback<CurrentUserResponse>() {
            @Override
            public void onResponse(Call<CurrentUserResponse> call, Response<CurrentUserResponse> response) {
                pDialog.dismiss();
                if(response.code() == 200) {
                    usernameText.setText(response.body().getUsername());
                    pointsText.setText("Points: " + response.body().getPoints());
                } else {
                    Toast.makeText(getApplicationContext(), "Une erreur est survenue lors de la récupération des infos.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CurrentUserResponse> call, Throwable t) {

            }
        });

    }
}
