package com.example.elif.eliff;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

//import android.util.Log;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button btn;
    Button listele;

    Button Btnlogin,Btnregister,Btnlist;


    TextView tw;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference referans=storage.getReference();
    DatabaseReference oku = database.getInstance().getReference().child("Ogrenci").child("300300322");  //örnekleri getir (instance)
    private Intent data;


    private void writeNewUser(String numara, String adsoy, String bolum) {
        User ogrenciler = new User(adsoy,bolum);

        myRef.child("Ogrenci").child(numara).setValue(ogrenciler);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tanımlanan butonlar ve textview
        listele=(Button)findViewById(R.id.button2);
        btn=(Button)findViewById(R.id.button);
        tw=(TextView)findViewById(R.id.textView);
        Btnregister=(Button)findViewById(R.id.Buttonkayit);
        Btnlogin=(Button)findViewById(R.id.Buttonlogin);



      /*  @Override
        protected void onActivityResult(int requestCode , int resultCode , Intent  data ) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==1 && resultCode==RESULT_OK){


                Uri uri=data.getData();
                StorageReference ref=referans.child(uri.getLastPathSegment());
                ref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Toast.makeText(MainActivity.this,"yükleme tamamlandı",Toast.LENGTH_LONG).show();
                    }
                });


            }

        }*/
        Btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

        Btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

        listele.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ValueEventListener dinle=new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User key=new User();//key adında bir nesne türettik
                        key=dataSnapshot.getValue(User.class);
                        tw.setText(key.adsoy+" "+key.bolum);//snapshot içindeki verileri aldım, yeni oluşturduğum nesnenin içine yükledim


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) { //herhangi bir hatayla karşılaşırsak, yapılması gerekenler burada yapılır

                    }
                };
                oku.addValueEventListener(dinle);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                writeNewUser("300300322","Sefa Korkmaz","Bilgisayar Müh");



            }

        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_PICK);
                i.setType("image/*");// "*" işareti, tüm fotoğraf formatlarını alabilmemizi sağlar
                startActivityForResult(i,1);
            }
        });



    }

}
