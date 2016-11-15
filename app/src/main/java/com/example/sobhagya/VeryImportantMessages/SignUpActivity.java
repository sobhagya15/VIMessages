package com.example.sobhagya.VeryImportantMessages;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SignUpActivity extends Activity {

    EditText ed1,ed2,ed3;
    Button b1,b2;
    LoginData ld;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        b1 = (Button) findViewById(R.id.button3);
        b2 = (Button) findViewById(R.id.button5);
        ld = new LoginData(this);
 //       tv = (TextView) findViewById(R.id.textView10);

        signup();
    }

    public void signup(){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String us = ed1.getText().toString();
                String pa = ed2.getText().toString();
                String co = ed3.getText().toString();
                if (us.isEmpty()) {
                    nmess("Error", "Fill in username!");
                } else if (pa.isEmpty()) {
                    nmess("Error", "Fill in password!");
                } else if (!pa.equals(co)) {
                    nmess("Error", "Passwords do not match!");
                } else if (pa.equals(co)) {
                    Boolean conf = ld.signup(us, pa);
                    if (conf == true) {
                        Intent intent = new Intent(SignUpActivity.this, PinActivity.class);
                        startActivity(intent);
                    }
                    else {
                        nmess("Error","Database error!");
                    }
                }
                else {
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
    protected void onResume() {
        super.onResume();
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
