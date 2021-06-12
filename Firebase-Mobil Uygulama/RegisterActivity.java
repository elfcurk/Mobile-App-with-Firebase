package com.example.elif.eliff;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    FirebaseDatabase db;
    Button btnkayitol;
    EditText ad;
    EditText password;
    EditText email;
    TextView Girisyap;
    ProgressDialog ProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ProgressDialog = new ProgressDialog(this);

        db=FirebaseDatabase.getInstance(); //database tanımlandı


        btnkayitol = (Button) findViewById(R.id.Buttonkayitol);
        ad = (EditText) findViewById(R.id.ad);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        Girisyap = (TextView) findViewById(R.id.Girisyap);


        btnkayitol.setOnClickListener(this);
        Girisyap.setOnClickListener(this);



    }

    private void register( ){

        String isim = ad.getText().toString().trim();
        String sifre = password.getText().toString().trim();
        String mail = email.getText().toString().trim();

        if(TextUtils.isEmpty(isim)){
            //isim gir uyarısı
            Toast.makeText(this,"Lutfen isimi bos birakmayiniz",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(sifre)){
            //sifre gir uyarısı
            Toast.makeText(this,"Lutfen sifreyi bos birakmayiniz",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(mail)){
            //mail gir uyarısı
            Toast.makeText(this,"Lutfen emaili bos birakmayiniz",Toast.LENGTH_SHORT).show();
            return;
        }
        //bu alanada veritabanına yazdırma işlemi yapılmalı

        DatabaseReference dbref = db.getReference("kullanıcılar");
        String key = dbref.push().getKey();
        DatabaseReference dbrefkey = db.getReference("Kullanıcı/"+key);
        dbrefkey.setValue(new kullanıcı(isim,mail,sifre));

        //burada kayıt olurken doldurulması gereken alanlar bos degılse çalışır
        ProgressDialog.setMessage("Kayit Basarili,Sisteme Giris Yapabilirsiniz");
        ProgressDialog.show();


    }

    @Override
    public void onClick(View view) {
        if(view == btnkayitol){
            register();
            Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(i);


        }
        if(view == Girisyap){
            Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(i);
        }

    }





}