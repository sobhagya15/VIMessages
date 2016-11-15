package com.example.sobhagya.VeryImportantMessages;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Sobhagya on 30-06-2016.
 */
public class Frag1 extends Fragment implements AdapterView.OnItemClickListener {
    View rootv;
    Datat dt;
    String SmsMessageStr;
    ListView lv,lv1;
    StringBuilder buffer;
    String mess;
//    public static final String MESSAGE_KEY = "com.example.sobhagya.veryimportantmsg.messagekey";

//    Button btn,btn1;
    private static Frag1 inst;
    ArrayList<String> smsMessagesList = new ArrayList<String>();
    ListView smsListView;
    ArrayAdapter arrayAdapter;
//    TextView tv6;
    public static Frag1 instance()
    {
        return inst;
    }
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        rootv=inflater.inflate(R.layout.frag1,container,false);
        return rootv;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //  tv=(TextView)findViewById(R.id.textView2);
        //       btn=(Button)getActivity().findViewById(R.id.button);
        //       btn1=(Button)getActivity().findViewById(R.id.button2);
        // lv1=(ListView)findViewById(R.id.listView3);
 //       tv6 = (TextView) getActivity().findViewById(R.id.textView6);
        lv=(ListView)getActivity().findViewById(R.id.SMSList);
        smsListView = (ListView) getActivity().findViewById(R.id.SMSList);
        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, smsMessagesList);
        smsListView.setAdapter(arrayAdapter);
        smsListView.setOnItemClickListener(this);
        refreshSmsInbox();

        dt=new Datat(getActivity());
        //      retrive();
        insertt();
        Cursor res = dt.fetch();
        buffer = new StringBuilder();
        while (res.moveToNext()) {
            buffer.append(res.getString(0) + "\n-------------------------\n");
        }
        //               tv6.setText(buffer.toString());
        mess = buffer.toString();
        DataHolderClass.getInstance().setDistributor_id(mess);
        //      delete();
    }

    public void refreshSmsInbox() {
        ContentResolver contentResolver = getActivity().getContentResolver();
        Cursor smsInboxCursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);
        int indexBody = smsInboxCursor.getColumnIndex("body");
        int indexAddress = smsInboxCursor.getColumnIndex("address");
        if (indexBody < 0 || !smsInboxCursor.moveToFirst()) return;
        arrayAdapter.clear();
        do {
            String str = "SMS From: " + smsInboxCursor.getString(indexAddress) +
                    "\n\n" + smsInboxCursor.getString(indexBody) + "\n";
            arrayAdapter.add(str);
        } while (smsInboxCursor.moveToNext());
    }

    public void updateList(final String smsMessage) {
        arrayAdapter.insert(smsMessage, 0);
        arrayAdapter.notifyDataSetChanged();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        try {
            String[] smsMessages = smsMessagesList.get(pos).split("\n");
            String address = smsMessages[0];
            String smsMessage = "";
            for (int i = 1; i < smsMessages.length; ++i) {
                smsMessage += smsMessages[i];
            }
            String smsMessageStr = address + "\n";
            smsMessageStr += smsMessage;
            Toast.makeText(getActivity(), smsMessageStr, Toast.LENGTH_SHORT).show();
            String SmsMessageStr = smsMessageStr;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertt(){
        smsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean inserted = dt.ins(smsListView.getItemAtPosition(position).toString());
                if (inserted == true) {
                    Toast.makeText(getActivity(), "data inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "data not inserted", Toast.LENGTH_SHORT).show();
                }
                Cursor res = dt.fetch();
                buffer = new StringBuilder();
                while (res.moveToNext()) {
                    buffer.append(res.getString(0) + "\n-------------------------\n");
                }
                //               tv6.setText(buffer.toString());
                mess = buffer.toString();
                DataHolderClass.getInstance().setDistributor_id(mess);
            }
        });
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

    /*    public void retrive(){
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {

                    Cursor res = dt.fetch();
                    if (res.getCount() == 0) {
                        nmess("sorry", "no data");
                    } else {
                        StringBuilder buffer = new StringBuilder();
                        while (res.moveToNext()) {
                            buffer.append(res.getString(0) + "\n-------------------------\n");
                        }
                        Intent intent = new Intent(getActivity(), Frag2.class);
                        intent.putExtra(MESSAGE_KEY, buffer.toString());
                        startActivity(intent);
                        //      tv.setText(buffer);
                    }

                }
            });

        }
    */
public void nmess(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }

}


