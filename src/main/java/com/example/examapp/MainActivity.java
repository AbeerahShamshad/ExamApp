package com.example.examapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String UserNameKey="User___";
    public static final String Eamil_address="Eamil___";


    Button Signup;
    EditText Fname;
    EditText Lname;
    EditText Email;
    EditText Phone;
    EditText Gender;
    DatePicker DOB;
    String PhonePattern= "[0-9]{12}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fname = findViewById(R.id.Fname);
        Lname = findViewById(R.id.Lname);
        Email = findViewById(R.id.Signup_Email);
        Phone = findViewById(R.id.phone);
        Gender = findViewById(R.id.Gender);
        Signup = findViewById(R.id.Signup_btnsignup);
        DOB = findViewById(R.id.datePicker1);



        Signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(Fname.getText().toString())) {
                    Fname.setError("Fill First name.");
                } else {
                    if (Fname.getText().toString().length() < 3) {
                        Fname.setError("First name letter(s) should be more then 3.");
                    } else {
                        if (TextUtils.isEmpty(Lname.getText().toString())) {
                            Lname.setError("Fill Last name.");
                        } else {
                            if (Lname.getText().toString().length() < 3) {
                                Lname.setError("Last name letter(s) should be more then 3.");
                            } else {
                                if (TextUtils.isEmpty(Email.getText().toString())) {
                                    Email.setError("Fill Email.");
                                } else {
                                    if (TextUtils.isEmpty(Phone.getText().toString())) {
                                        Phone.setError("Fill phone.");
                                    } else {
                                        if (Phone.getText().toString().matches(PhonePattern)) {
                                            Phone.setError("Invalid phone number.");
                                        } else {
                                            if (TextUtils.isEmpty(Gender.getText().toString())) {
                                                Gender.setError("Fill gender.");
                                            } else {
                                                if (!Patterns.EMAIL_ADDRESS.matcher(Email.getText().toString()).matches()) {
                                                    Toast t = Toast.makeText(MainActivity.this, "Invalid Email", Toast.LENGTH_SHORT);
                                                    t.show();
                                                } else {
                                                    Intent intent = new Intent(MainActivity.this, DashBoard.class);
                                                    String Name = Fname.getText().toString()+Lname.getText().toString();

                                                    intent.putExtra(UserNameKey ,Name);
                                                    intent.putExtra(Eamil_address,Email.getText().toString());
                                                    startActivity(intent);
                                                }

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

}
}