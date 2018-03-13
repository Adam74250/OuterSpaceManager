package macheda.com.outerspacemanager.outerspacemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    private Button createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createAccount = (Button) findViewById(R.id.createAccount);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SignUpActivity = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(SignUpActivity);
            }
        });
    }
}
