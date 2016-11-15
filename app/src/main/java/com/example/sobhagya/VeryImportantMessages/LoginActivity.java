package com.example.sobhagya.VeryImportantMessages;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
    LoginData ld;
    EditText ed1;
    Button b1,b2;
//    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed1 = (EditText) findViewById(R.id.editText8);
        b1 = (Button) findViewById(R.id.button2);
        b2 = (Button) findViewById(R.id.button7);
        ld = new LoginData(this);
 //       tv = (TextView) findViewById(R.id.textView4);

        gotomain();
    }

    public void gotomain(){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pin = ed1.getText().toString();
                if (pin.isEmpty()) {
                    nmess("Error", "Pin field is empty!");
                } else if (pin.length() != 4) {
                    nmess("Error", "Enter a 4 digit pin!");
                } else if (pin.length() == 4) {
                    Boolean ch = ld.login(pin);
                    if (ch == true) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        nmess("Error", "Login Unsuccessful, wrong pin!");
                    }
 //                   tv.setText(ch);
                } else {
                    nmess("Error", "Please fill in again!");
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean ch = ld.del();
                if(ch == true){
                    nmess("Success","Table deleted successfully");
                }
                else {
                    nmess("Failure","Table not deleted");
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        ld.close();
        finish();
    }
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
    public void nmess(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }
}
