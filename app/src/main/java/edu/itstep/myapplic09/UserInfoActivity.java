package edu.itstep.myapplic09;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        TextView tvUserName = findViewById(R.id.tvUserName);
        TextView tvUserPhone = findViewById(R.id.tvUserPhone);

        // Retrieve user information from the intent
        String userName = getIntent().getStringExtra("userName");
        String userPhone = getIntent().getStringExtra("userPhone");

        // Display user information
        tvUserName.setText("User Name: " + userName);
        tvUserPhone.setText("User Phone: " + userPhone);
    }
}
