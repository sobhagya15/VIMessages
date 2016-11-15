package com.example.sobhagya.VeryImportantMessages;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends Activity {
ImageView iv, iv1;
//    TextView tv;
    LoginData ld;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        iv = (ImageView) findViewById(R.id.imageView);
        iv1 = (ImageView) findViewById(R.id.imageView2);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.a1);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.a2);
        iv.startAnimation(animation);
        iv1.startAnimation(animation1);
//        tv = (TextView) findViewById(R.id.textView4);
        ld = new LoginData(this);

        Thread thread = new Thread(){
          public void run()  {
              try{
                  sleep(5500);
              }
              catch(Exception e){

              }
              finally{
                 Boolean ch = ld.checkpin();
                  if(ch == false) {
                      Intent intent = new Intent(Splash.this, SignUpActivity.class);
                      startActivity(intent);
                  }
                  else {
                      Intent intent = new Intent(Splash.this, LoginActivity.class);
                      startActivity(intent);
                  }
//                  tv.setText(ch);
              }
          }
        };
        thread.start();

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
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }
}
