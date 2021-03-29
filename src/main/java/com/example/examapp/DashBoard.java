package com.example.examapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import static android.widget.Toast.LENGTH_LONG;

public class DashBoard extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public static final String UserNameKey="User___";
    public static final String Eamil_address="Eamil___";
    public static final String C_a_t_e_g_o_r_y="Category___";
    public static final String P_r_o_d_u_c_t="Product___";
    public static final String Delivery="Delivery___";
    public static final String D_A_T_E="Date___";
    public static final String T_I_M_E="Time___";
    public static final String Q_u_a_n_t_i_t_y="Quantity___";
    public static final String Amount="Amount___";




    TextView User_Name;
    TextView Quantity_Value,price_value;
    TextView E;
    RadioGroup RG;
    RadioButton R_btn;
    Button Order;
    DatePicker DP;
    TimePicker TP;
    Button Increment, Decrement;

    private Spinner spinner,spinner1;
    private static final String[] paths = {"","Men's wear", "Women wear", "Children"};
    private static final String[] Men = {"","T-Shirt", "Wrist Watch", "Jogger's"};
    private static final String[] Women = {"","Saree", "Bracelets", "Sandals"};
    private static final String[] Children = {"","Frok's", "HAT wear", "Gean's pants"};
    private static final String[] price = {"","100", "120", "140"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Increment = findViewById(R.id.btn_Quantity_increment);
        Decrement = findViewById(R.id.btn_Quantity_decrement);
        Quantity_Value = findViewById(R.id.Quantity_value);
        price_value = findViewById(R.id.price_value);
        Order = findViewById(R.id.Order_palcement);
        RG = findViewById(R.id.RG_);
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner1 = (Spinner)findViewById(R.id.spinner_product);
        DP = (DatePicker) findViewById(R.id.datePicker1);
        TP = (TimePicker) findViewById(R.id.timePicker1);
        TP.setIs24HourView(true);
        int radio_id = RG.getCheckedRadioButtonId();
        R_btn = findViewById(radio_id);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DashBoard.this,
                android.R.layout.simple_spinner_item,paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);


        User_Name = findViewById(R.id.textView_Name);
        E = findViewById(R.id.textView_Eamil);
        Intent intent =getIntent();
        String User = intent.getStringExtra(MainActivity.UserNameKey);
        String Email= intent.getStringExtra(MainActivity.Eamil_address);
        User_Name.setText(User);
        E.setText(Email);

        Order.setOnClickListener(new View.OnClickListener(){

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(spinner.getSelectedItem().toString().length()<=0){
                    Toast t = Toast.makeText(DashBoard.this,"Category must be filled",LENGTH_LONG);
                    t.show();
                }
                else{
                    if(spinner1.getSelectedItem().toString().length()<=0){
                        Toast t = Toast.makeText(DashBoard.this,"Product must be filled",LENGTH_LONG);
                        t.show();
                    }else{
                        try{
                            if(R_btn.getText().toString().contains("Delivery")|| R_btn.getText().toString().contains("Self-pick") ){
                                if(Quantity_Value.getText().toString().contains("0") ){
                                    Toast t = Toast.makeText(DashBoard.this,"Fill quantity.",LENGTH_LONG);
                                    t.show();

                                }else{
                                    int Day = DP.getDayOfMonth();
                                    int Month = DP.getMonth()+1;
                                    int Year = DP.getYear();
                                    int Hour, Minute;
                                    String am_pm;
                                    if (Build.VERSION.SDK_INT >= 23 ){
                                        Hour = TP.getHour();
                                        Minute = TP.getMinute();
                                    }
                                    else{
                                        Hour = TP.getCurrentHour();
                                       Minute = TP.getCurrentMinute();
                                    }
                                    if(Hour > 12) {
                                        am_pm = "PM";
                                        Hour = Hour - 12;
                                    }
                                    else
                                    {
                                        am_pm="AM";
                                    }

                                    String DeliverDate = String.valueOf(Day)+"/"+String.valueOf(Month)+"/"+String.valueOf(Year);
                                    String DeliverTime = String.valueOf(Hour)+":"+String.valueOf(Minute)+" "+am_pm;


                                    Intent intent2 = new Intent(DashBoard.this , OrderSummary.class);
                                    intent2.putExtra(UserNameKey ,User_Name.getText().toString());
                                    intent2.putExtra(Eamil_address,E.getText().toString());
                                    intent2.putExtra(C_a_t_e_g_o_r_y,spinner.getSelectedItem().toString());
                                    intent2.putExtra(P_r_o_d_u_c_t,spinner1.getSelectedItem().toString());
                                    intent2.putExtra(Delivery,R_btn.getText());
                                    intent2.putExtra(D_A_T_E,DeliverDate);
                                    intent2.putExtra(T_I_M_E,DeliverTime);
                                    intent2.putExtra(Q_u_a_n_t_i_t_y,Quantity_Value.getText().toString());
                                    intent2.putExtra(Amount,price_value.getText().toString());
                                    startActivity(intent2);
                                }

                            }
                        }catch (Throwable e) {
                            Toast t = Toast.makeText(DashBoard.this,"Delivery option must be select",LENGTH_LONG);
                            t.show();
                        }
                    }
                }

            }
        });

        Increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Value.setText(String.valueOf(Integer.parseInt(Quantity_Value.getText().toString())+1));
                String text = spinner1.getSelectedItem().toString();
                if(text.length()>0){
                    if(text == Men[1] || text == Women[1] || text == Children[1] ){
                        price_value.setText(String.valueOf(Integer.parseInt(Quantity_Value.getText().toString())*100));
                    }else{
                        if(text == Men[2] || text == Women[2] || text == Children[2] ){
                            price_value.setText(String.valueOf(Integer.parseInt(Quantity_Value.getText().toString())*120));
                        }else{
                            if(text == Men[3] || text == Women[3] || text == Children[3] ){
                                price_value.setText(String.valueOf(Integer.parseInt(Quantity_Value.getText().toString())*140));
                            }
                        }
                    }
                }

            }
        });
        Decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(Quantity_Value.getText().toString())>0){
                    Quantity_Value.setText(String.valueOf(Integer.parseInt(Quantity_Value.getText().toString())-1));
                    String text = spinner1.getSelectedItem().toString();
                    if(text.length()>0){
                        if((text == Men[1] || text == Women[1] || text == Children[1]) && (Integer.parseInt(price_value.getText().toString())>=100)){
                            price_value.setText(String.valueOf(Integer.parseInt(price_value.getText().toString())-100));
                        }else{
                            if((text == Men[2] || text == Women[2] || text == Children[2]) && (Integer.parseInt(price_value.getText().toString())>=120)){
                                price_value.setText(String.valueOf(Integer.parseInt(price_value.getText().toString())-120));
                            }else{
                                if((text == Men[3] || text == Women[3] || text == Children[3]) && (Integer.parseInt(price_value.getText().toString())>=140)){
                                    price_value.setText(String.valueOf(Integer.parseInt(price_value.getText().toString())-140));
                                }
                            }
                        }
                    }


                }
            }
        });


    }

    public void Check_fun(View v){
        int radio_id = RG.getCheckedRadioButtonId();
        R_btn = findViewById(radio_id);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:

                break;
            case 1:
//                String text = spinner1.getSelectedItem().toString();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DashBoard.this,
                        android.R.layout.simple_spinner_item,Men);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter);
                break;
            case 2:
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(DashBoard.this,
                        android.R.layout.simple_spinner_item,Women);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter1);
                break;
            case 3:
                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(DashBoard.this,
                        android.R.layout.simple_spinner_item,Children);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter3);
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}