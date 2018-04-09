package macheda.com.outerspacemanager.outerspacemanager.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import macheda.com.outerspacemanager.outerspacemanager.Services.Requests.LoginRequest;
import macheda.com.outerspacemanager.outerspacemanager.Services.Responses.LoginResponse;
import macheda.com.outerspacemanager.outerspacemanager.Services.OuterSpaceService;
import macheda.com.outerspacemanager.outerspacemanager.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private Button createAccount;
    private Button loginToAccount;
    private EditText usernameText;
    private EditText passwordText;
    public static final String OSM_PREFS = "outerSpaceManagerPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createAccount = (Button) findViewById(R.id.createAccount);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SignUpActivity = new Intent(getApplicationContext(), macheda.com.outerspacemanager.outerspacemanager.Activities.SignUpActivity.class);
                startActivity(SignUpActivity);
            }
        });

        loginToAccount = (Button) findViewById(R.id.loginToAccount);

        loginToAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameText = findViewById(R.id.editText3);
                passwordText = findViewById(R.id.editText2);

                final ProgressDialog pDialog = ProgressDialog.show(LoginActivity.this, "",
                        "Connexion en cours...", true);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://outer-space-manager.herokuapp.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                OuterSpaceService service = retrofit.create(OuterSpaceService.class);

                Call<LoginResponse> rep = service.login(new LoginRequest(usernameText.getText().toString(), passwordText.getText().toString()));

                rep.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        pDialog.dismiss();
                        if(response.code() == 200) {
                            SharedPreferences settings = getSharedPreferences(OSM_PREFS, 0);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("auth_token", response.body().getToken());
                            editor.commit();

                            Intent MainActivity = new Intent(getApplicationContext(), macheda.com.outerspacemanager.outerspacemanager.Activities.MainActivity.class);
                            startActivity(MainActivity);

                            Toast.makeText(LoginActivity.this, "Bienvenue !" , Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Une erreur est survenue lors de la connexion : " + response.message(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                    }
                });
            }
        });
    }
}
