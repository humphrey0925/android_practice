package com.example.kebohan.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    public ProgressDialog PDialog = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

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
                                sleep(5000);
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

            }
        });
    }
    public void sign_up_page()
    {
        Intent sign_up = new Intent(LoginActivity.this,sign.class);
        startActivity(sign_up);
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
