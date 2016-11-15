package com.example.sobhagya.VeryImportantMessages;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class PinActivity extends Activity {
    EditText ed1,ed2;
    Button b1;
    LoginData ld;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        ed1 = (EditText) findViewById(R.id.editText4);
        ed2 = (EditText) findViewById(R.id.editText5);
        b1 = (Button) findViewById(R.id.button4);
        ld = new LoginData(this);
        tv = (TextView) findViewById(R.id.textView9);

        gotomain();
    }

   public void gotomain(){
       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String us_pin = ed1.getText().toString();
               String conf = ed2.getText().toString();
               if (us_pin.isEmpty()) {
                   nmess("Error", "Fill in pin!");
               }
               else if(us_pin.startsWith("0")){
                   nmess("Error","Do not start pin with 0!");
               }
               else if (us_pin.length() != 4) {
                   nmess("Error", "Enter 4 digit pin!");
               } else if (!us_pin.equals(conf)) {
                   nmess("Error", "Pins do not match");
               } else if (us_pin.equals(conf)) {
                   Boolean c = ld.pin(us_pin);
                   if (c == true) {
                       Intent intent = new Intent(PinActivity.this, MainActivity.class);
                       startActivity(intent);
                   }
                   else {
                       nmess("Error","Database Error!");
                   }
//                   tv.setText(c);
               }
               else {
                   nmess("Error","Please fill in again!");
               }
           }
       });

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

    @Override
    protected void onStop() {
        super.onStop();
        ld.close();
        finish();
    }
    public void nmess(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }
}
