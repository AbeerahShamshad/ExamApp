package com.example.examapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderSummary extends AppCompatActivity {

    TextView name,email,category_,product_,Deli,Date_,Quant,price,Time_;
    Button Exit_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        name=findViewById(R.id.Name);
        email=findViewById(R.id.Email);
        category_=findViewById(R.id.category);
        product_=findViewById(R.id.Product);
        Deli=findViewById(R.id.Delivery_option);
        Date_=findViewById(R.id.Dat_e);
        Time_=findViewById(R.id.Time);
        Quant=findViewById(R.id.Quantity);
        price=findViewById(R.id.Amount);
        Exit_btn=findViewById(R.id.button);

        Intent intent =getIntent();
        name.setText(intent.getStringExtra(MainActivity.UserNameKey));
        email.setText(intent.getStringExtra(MainActivity.Eamil_address));
        category_.setText(intent.getStringExtra(DashBoard.C_a_t_e_g_o_r_y));
        product_.setText(intent.getStringExtra(DashBoard.P_r_o_d_u_c_t));
        Deli.setText(intent.getStringExtra(DashBoard.Delivery));
        Date_.setText(intent.getStringExtra(DashBoard.D_A_T_E));
        Time_.setText(intent.getStringExtra(DashBoard.T_I_M_E));
        Quant.setText(intent.getStringExtra(DashBoard.Q_u_a_n_t_i_t_y));
        price.setText(intent.getStringExtra(DashBoard.Amount));

        Exit_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onDestroy();
            }
        });

        Toast Final = Toast.makeText(OrderSummary.this,"Please click back to exit",Toast.LENGTH_SHORT);
        Final.show();

    }
}
