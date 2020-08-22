package com.example.cafeoda.LoginJoin;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cafeoda.Mainpage.MainPageActivity;
import com.example.cafeoda.R;
import com.example.cafeoda.zzzNetwork.StringURLHttpHandler;


public class LoginAcitvity extends AppCompatActivity {
    Button loginbtn;
    Button joinbtn;
    EditText phoneText;
    EditText passText;
    //public static String ip = "70.12.231.173";
    public static final int SIGNUP_INTENT = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);
        loginbtn = findViewById(R.id.Btn_login);
        phoneText = findViewById(R.id.Txt_phone);
        passText = findViewById(R.id.Txt_pass);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guestphone = phoneText.getText().toString();
                String guestpass = passText.getText().toString();
                loginGuestHttpTask task = new loginGuestHttpTask(guestphone,guestpass);
                task.execute();

            }
        });


    }

    class loginGuestHttpTask extends AsyncTask<Void,Void,String>{
        String url;

        public loginGuestHttpTask(String guestphone, String guestpass){
            url = "http://"+getString(R.string.ipaddress)+":8088/cafeoda/guestlogin.do?";
            url += "guestphone="+guestphone+"&guestpass="+guestpass;
        }

        @Override
        protected String doInBackground(Void... voids) {
            //커넥션 시작

            return StringURLHttpHandler.requestData(url);
        }

        @Override
        protected void onPostExecute(String s) {
            if(s!=null) {
                Log.d("---", "포스트익스큐트스트링:" + s);
                if (s.length() > 10) {
                    Log.d("---", "포스트익스큐트if/받아온데이터:" + s);
                    Toast.makeText(LoginAcitvity.this, "게스트 로그인 성공", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginAcitvity.this, MainPageActivity.class);
                    intent.putExtra("guestinfo", s);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginAcitvity.this, "로그인 실패. 입력정보를 확인하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

