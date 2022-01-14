package com.example.proje_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText txt_username, txt_password;
    TextView txt_class, txt_queueofStudent;
    Button btn_login, btn_here;
    TextView txt_usernameforsql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        btn_login.setOnClickListener(view -> checkValidate());

    }
    private void checkValidate(){
        Teachers teacher1 =new Teachers("Seth","Delaney","T_Seth","123"); //Yetkili Kaydı
        Teachers teacher2 =new Teachers("Brittany","Mcgowan","T_Brit","456");//Yetkili Kaydı
        ArrayList<Teachers> teachersArrayList = new ArrayList<>();//Yetkili kişileri bir listeye kaydetme
        teachersArrayList.add(teacher1);//listeye ekleme
        teachersArrayList.add(teacher2);//listeye ekleme
        Intent intent = new Intent(this,activityYoklama.class);


        for(int i = 0 ; i<teachersArrayList.size();i++)//Listeyi döngüyle döndürerek listeye ne kadar yekili girişi
                                                        // yapılırsa yapılsın yeni denetim yapmaya gerek kalmıyor
        {
            if(txt_username.getText().toString().equals(teachersArrayList.get(i).getUserName()))
            {
                if(txt_password.getText().toString().equals(teachersArrayList.get(i).getPassword().toString())){
                    Toast.makeText(MainActivity.this,getString(R.string.loginSucces), Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this,getString(R.string.FailureLogin),Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


   /* private void checkValidate() {
        Teachers teacher1 =new Teachers("Seth","Delaney","T_Seth","123");
        Teachers teacher2 =new Teachers("Brittany","Mcgowan","T_Brit","456");
        Intent intent = new Intent(this,activityYoklama.class);

        if(txt_username.getText().toString().equals(teacher1.getUserName()))
        {
            if(txt_password.getText().toString().equals(teacher1.getPassword().toString())){
                Toast.makeText(MainActivity.this,getString(R.string.loginSucces), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
            else {
                Toast.makeText(MainActivity.this,getString(R.string.FailureLogin),Toast.LENGTH_SHORT).show();
            }
        }
        else
            {
                if(txt_username.getText().toString().equals(teacher2.getName())){
                    if(txt_password.getText().toString().equals(teacher2.getPassword())){
                        Toast.makeText(MainActivity.this,getString(R.string.loginSucces), Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this,getString(R.string.FailureLogin),Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,getString(R.string.FailureLogin),Toast.LENGTH_SHORT).show();
                }
        }
    }*/


    private void initView() {
        txt_password = findViewById(R.id.txt_password);
        txt_username = findViewById(R.id.txt_userName);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_here = (Button) findViewById(R.id.btn_here);
        btn_login = (Button) findViewById(R.id.btn_login);
        txt_class = findViewById(R.id.txt_class);
        txt_queueofStudent = findViewById(R.id.txt_queueofStudent);
        txt_usernameforsql = findViewById(R.id.txt_usernameforsql);
    }
}
































