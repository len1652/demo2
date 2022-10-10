package com.ndphuc.dp.demo2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class add_person extends AppCompatActivity {

    private EditText edtName,edtBirth,edtAbout,edtAvatar;
    private TextView lblbar;
    private Button btnDone;
    private ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);


        addConTrols();
        addEvents();
    }

    private void addEvents() {
        bamnutback();
        thongbaotrenapptionbar();

        clickDone();

    }



    private void clickDone() {
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean kt ;
                kt = kiemtra();
                if (kt==true){
                    add_new_person();

                }
                else{
                    Toast.makeText(add_person.this,R.string.LackOfInformation,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void add_new_person() {
        Intent intent = new Intent(add_person.this,MainActivity.class);
        intent.putExtra("NAME",edtName.getText().toString());
        intent.putExtra("BIRTH",edtBirth.getText().toString());
        intent.putExtra("AVATAR",edtAvatar.getText().toString());
        intent.putExtra("ABOUT",edtAbout.getText().toString());
        // đánh dấu trả về
        setResult(33,intent);
        // đóng màn hình này lại:
        // để màn hình Main trở thành Foregroud lifetime
        // vì nó chỉ tự động nhận được kết quả trả về ở Foregroud lifetime
        finish();

    }

    private Boolean kiemtra() {

        if(edtName.getText().length()==0
                ||edtAbout.getText().length()==0
                ||edtBirth.getText().length()==0
                ||edtAvatar.getText().length()==0){
            return false;
        }

        return true;
    }

    private void thongbaotrenapptionbar() {
        lblbar.setText("Add Infomation");
    }

    private void bamnutback() {
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addConTrols() {
        edtName = findViewById(R.id.txtName);
        edtBirth = findViewById(R.id.txtBirth);
        edtAvatar = findViewById(R.id.txtAvatar);
        edtAbout = findViewById(R.id.txtAbout);
        btnDone = findViewById(R.id.btnDone);

        btnback = findViewById(R.id.btnBack);
        lblbar = findViewById(R.id.lblBar);
    }
}