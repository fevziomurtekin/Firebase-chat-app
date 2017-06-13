package com.example.asus.basicchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements ValueEventListener{
    EditText editText;
    Button button;
    TextView textView;
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference root=firebaseDatabase.getReference();
     private DatabaseReference heading=root.child("heading");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText= (EditText) findViewById(R.id.editText);
        button= (Button) findViewById(R.id.button2);
        textView= (TextView) findViewById(R.id.textView);

    }
    public  void submit(View view)
    {
        String mesaj=editText.getText().toString();
        heading.setValue(mesaj);

    }
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if(dataSnapshot.getValue(String.class)!=null)
        {
            String key=dataSnapshot.getKey();
            if(key.equals("heading")) /*eğer ileti yazıyorsanız*/
            {
                String mesaj=dataSnapshot.getValue(String.class);
                textView.setText(mesaj);
            /*yazdığınız iletiyi alıp  textview e yazdırıyor.*/
            }

        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        heading.addValueEventListener(this); // değeri alıp heading denilen firebase objesinde tutuyor.
    }
}
