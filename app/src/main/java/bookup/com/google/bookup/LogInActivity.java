package bookup.com.google.bookup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class LogInActivity extends AppCompatActivity {

    EditText login_email, login_password;
    Button cmdLogin;
    TextView forgotPassword, createAccount;
    RadioGroup rGroupUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        refs();
    }

    public void refs()
    {
        login_email = findViewById(R.id.EdTxtLogIn_Email);
        login_password = findViewById(R.id.EdTxtLogIn_Password);
        forgotPassword = findViewById(R.id.TxtVwLogIn_ForgotPass);
        createAccount = findViewById(R.id.TxtVwLogIn_CreateAccount);
        rGroupUsers = findViewById(R.id.RdGrp_Users);
        cmdLogin = findViewById(R.id.BtnLogIn_LogIn);
    }
}
