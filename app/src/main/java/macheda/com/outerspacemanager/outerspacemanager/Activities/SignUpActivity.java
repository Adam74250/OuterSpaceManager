package macheda.com.outerspacemanager.outerspacemanager.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import macheda.com.outerspacemanager.outerspacemanager.Services.Requests.ConnectionRequest;
import macheda.com.outerspacemanager.outerspacemanager.Services.Responses.ConnectionResponse;
import macheda.com.outerspacemanager.outerspacemanager.Services.OuterSpaceService;
import macheda.com.outerspacemanager.outerspacemanager.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    private Button registerButton;
    private EditText emailText;
    private EditText usernameText;
    private EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailText = findViewById(R.id.editText1);
                usernameText = findViewById(R.id.editText2);
                passwordText = findViewById(R.id.editText3);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://outer-space-manager.herokuapp.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                OuterSpaceService service = retrofit.create(OuterSpaceService.class);

                Call<ConnectionResponse> rep = service.signUp(new ConnectionRequest(usernameText.toString(), emailText.toString(), passwordText.toString()));

                rep.enqueue(new Callback<ConnectionResponse>() {
                    @Override
                    public void onResponse(Call<ConnectionResponse> call, Response<ConnectionResponse> response) {
                        if(response.code() == 401)
                            Toast.makeText(getApplicationContext(), "Erreur : " + response.message(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Inscription terminée ", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ConnectionResponse> call, Throwable t) {
                    }
                });
            }
        });
    }
}
