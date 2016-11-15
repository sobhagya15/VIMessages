package com.example.sobhagya.VeryImportantMessages;

import android.app.Fragment;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by NMZ on 06-07-2016.
 */
public class Frag4 extends Fragment {

    View rootv;
    EditText ed1, ed2;
    Button button;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootv=inflater.inflate(R.layout.frag4,container,false);
        return rootv;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        button = (Button) getActivity().findViewById(R.id.button);
        ed1 = (EditText) getActivity().findViewById(R.id.editText6);
        ed2 = (EditText) getActivity().findViewById(R.id.editText7);
        send();
    }

    public void send(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneno=ed1.getText().toString();
                String message=ed2.getText().toString();
                SmsManager manager=SmsManager.getDefault();
                manager.sendTextMessage(phoneno,null,message,null,null);
                Toast.makeText(getActivity(), "Message Sent", Toast.LENGTH_LONG).show();
            }
        });
    }
}
