package com.example.kebohan.myapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    public ProgressDialog PDialog = null;
    public EditText username,userpassword;
    public JSONArray userinfo;
    private int userlogin_status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.username);
        userpassword = (EditText) findViewById(R.id.userpassword);
        Button sign_in = (Button) findViewById(R.id.sign_in_button);
        Button login = (Button) findViewById(R.id.login_button);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_up_page();

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                final CharSequence strDialogTitle = getString(R.string.str_dialog_title);
                final CharSequence strDialogBody = getString(R.string.str_dialog_body);
                int auth = login_auth();
//顯示Progress對話視窗
                if(auth==1)
                {
                    PDialog = ProgressDialog.show(LoginActivity.this, strDialogTitle, strDialogBody, true);
                    new Thread(){
                        public void run(){
                            try{
                                new TransTask().execute("192.168.43.253/login.php?username="+username.getText().toString()+"&password="+userpassword.getText().toString());

                            }
                            catch(Exception e){
                                e.printStackTrace();
                            }
                            finally{
                                PDialog.dismiss();
                            }
                        }
                    }.start();
                }
                else
                {
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle(R.string.password_error_dialog_title)
                            .setMessage(R.string.password_error_dialog_text)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }
                switch (userlogin_status)
                {
                    case 200:
                        MainAct();
                        break;
                    case 403:

                        break;
                    case 404:
                        break;

                }

            }
        });

    }
    class TransTask extends AsyncTask<String, Void,String>
    {

        @Override
        protected String doInBackground(String... params) {
            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL(params[0]);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(url.openStream()));
                String line = in.readLine();
                while(line!=null){
                    Log.d("HTTP", line);
                    sb.append(line);
                    line = in.readLine();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("JSON", s);
            user_login_auth(s);
        }


    }
    private void user_login_auth(String s){
        try {
            userinfo = new JSONArray(s);
            JSONObject userinfo_item = userinfo.getJSONObject(0);
            if (userinfo_item!=null)
            {
                if(userinfo_item.getString("status")!="403")
                {
                    userlogin_status=200;
                }
                else
                {
                    userlogin_status=403;
                }
            }
            else
            {
                userlogin_status=404;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public void pop_out_error()
    {
        String status_title = null,status_message=null;
        switch (userlogin_status)
        {
            case 403:
                status_title=getString(R.string.login_error_403_title);
                status_message=getString(R.string.login_error_403_text);
                break;
            case 404:
                status_title=getString(R.string.login_error_404_title);
                status_message=getString(R.string.login_error_404_text);
                break;

        }
        new AlertDialog.Builder(LoginActivity.this)


                .setTitle(status_title)
                .setMessage(status_message)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
    public void sign_up_page()
    {
        Intent sign_up = new Intent(LoginActivity.this,sign.class);
        startActivity(sign_up);
    }
    public void MainAct()
    {
        Intent main = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(main);
    }
    public int login_auth()
    {

        EditText username = (EditText) findViewById(R.id.username);
        EditText userpassword = (EditText) findViewById(R.id.userpassword);
        String username_str = username.getText().toString();
        String userpassword_str = userpassword.getText().toString();
        if (username_str.length()>0&&userpassword_str.length()>0)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
