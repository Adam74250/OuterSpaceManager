package macheda.com.outerspacemanager.outerspacemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://outer-space-manager.herokuapp.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                OuterSpaceService service = retrofit.create(OuterSpaceService.class);

                Call<ConnectionResponse> rep = service.signUp(new ConnectionRequest("adam.macheda74@gmail.com", "Adam74", "test"));

                rep.enqueue(new Callback<ConnectionResponse>() {
                    @Override
                    public void onResponse(Call<ConnectionResponse> call, Response<ConnectionResponse> response) {
                        Toast.makeText(getApplicationContext(), response.body().getToken(), Toast.LENGTH_LONG);
                    }

                    @Override
                    public void onFailure(Call<ConnectionResponse> call, Throwable t) {
                    }
                });
            }
        });
    }
}
