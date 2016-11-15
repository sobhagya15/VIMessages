package com.example.sobhagya.VeryImportantMessages;


import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Sobhagya on 30-06-2016.
 */
public class Frag2 extends Fragment {
    View rootv;
 //   public static final String MESSAGE_KEY = "com.example.sobhagya.veryimportantmsg.messagekey";
    TextView tv6;
    Button button;
    Datat dt;
    String mess;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootv=inflater.inflate(R.layout.frag2,container,false);
        return rootv;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv6 = (TextView)getActivity().findViewById(R.id.textView6);
        dt = new Datat(getActivity());
        button = (Button) getActivity().findViewById(R.id.button6);
 /*       Frag1 frag1=new Frag1();
        Cursor res = frag1.dt.fetch();
        Cursor res = dt.fetch();
        if (res.getCount() == 0) {
            nmess("sorry", "no data");
        } else {
            StringBuilder buffer = new StringBuilder();
            while (res.moveToNext()) {
                buffer.append(res.getString(0) + "\n-------------------------\n");
            }
            mess = buffer.toString();
        }
*/
        mess = DataHolderClass.getInstance().getDistributor_id();
       tv6.setText(mess);
        delete();
    }
    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        dt.close();
        onDestroyView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onDestroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onDetach();
    }

    public void nmess(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }
    public void delete() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedrow = dt.dlt();
                if (deletedrow > 0) {
                    Toast.makeText(getActivity(), "Saved messages deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "No Saved Messages", Toast.LENGTH_SHORT).show();
                }
                tv6.setText(null);
            }
        });
    }
}
