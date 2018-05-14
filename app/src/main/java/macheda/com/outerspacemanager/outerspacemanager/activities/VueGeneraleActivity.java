package macheda.com.outerspacemanager.outerspacemanager.activities;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import macheda.com.outerspacemanager.outerspacemanager.R;
import macheda.com.outerspacemanager.outerspacemanager.classes.Config;
import macheda.com.outerspacemanager.outerspacemanager.classes.CurrentUser;
import macheda.com.outerspacemanager.outerspacemanager.services.OuterSpaceService;
import macheda.com.outerspacemanager.outerspacemanager.services.requests.RegisterRequest;
import macheda.com.outerspacemanager.outerspacemanager.services.responses.RegisterResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VueGeneraleActivity extends AppCompatActivity {
    private Button vueGeneraleBouton;
    public static final String OSM_PREFS = "outerSpaceManagerPrefs";
    private String token;
    private TextView gasModifierNTextView;
    private TextView gasNTextView;
    private TextView mineralsNTextView;
    private TextView mineralsModifierTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vue_generale);

        gasModifierNTextView = (TextView) findViewById(R.id.gasModifierNTextView);
        gasNTextView = (TextView) findViewById(R.id.gasNTextView);
        mineralsNTextView = (TextView) findViewById(R.id.mineralsNTextView);
        mineralsModifierTextView = (TextView) findViewById(R.id.mineralsModifierNTextView);

        final ProgressDialog pDialog = ProgressDialog.show(VueGeneraleActivity.this, "",
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
                    gasModifierNTextView.setText(Integer.toString(response.body().getGasModifier()));
                    gasNTextView.setText(Float.toString(response.body().getGas()));
                    mineralsNTextView.setText(Float.toString(response.body().getMinerals()));
                    mineralsModifierTextView.setText(Integer.toString(response.body().getMineralsModifier()));
                } else {
                    Toast.makeText(getApplicationContext(), "Une erreur est survenue lors de la récupération des infos.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CurrentUser> call, Throwable t) {

            }
        });
    }
}
