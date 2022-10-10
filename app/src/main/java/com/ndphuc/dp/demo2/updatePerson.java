package com.ndphuc.dp.demo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class updatePerson extends AppCompatActivity {
    private EditText edtUpName,edtUpBirth,edtUpAbout,edtUpAvatar;
    private TextView lblUpbar;
    private Button btnUpdate;
    private ImageView btnback;
    private String id=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_person);

        addConTrols();
        addEvents();

    }
    private void viewIntent() {
        Bundle extrasData = getIntent().getExtras();
        id=extrasData.getString("ID","Id");
        edtUpName.setText(extrasData.getString("NAME","Name"));
        edtUpBirth.setText(extrasData.getString("BIRTH","Birth"));
        edtUpAvatar.setText(extrasData.getString("AVATAR","Avatar"));
        edtUpAbout.setText(extrasData.getString("ABOUT","About"));
    }

    private void clickUpdate() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean kt ;
                kt = kiemtra();
                if (kt==true){
                    update();
                }
                else{
                    Toast.makeText(updatePerson.this,R.string.LackOfInformation,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void update() {

        Intent intent = new Intent(updatePerson.this,MainActivity.class);
        intent.putExtra("ID",id);
        intent.putExtra("NAME",edtUpName.getText().toString());
        intent.putExtra("BIRTH",edtUpBirth.getText().toString());
        intent.putExtra("AVATAR",edtUpAvatar.getText().toString());
        intent.putExtra("ABOUT",edtUpAbout.getText().toString());
        // đánh dấu trả về
        setResult(66,intent);
        // đóng màn hình này lại:
        // để màn hình Main trở thành Foregroud lifetime
        // vì nó chỉ tự động nhận được kết quả trả về ở Foregroud lifetime
        finish();
    }

    private Boolean kiemtra() {

        if(edtUpName.getText().length()==0
                ||edtUpAbout.getText().length()==0
                ||edtUpBirth.getText().length()==0
                ||edtUpAvatar.getText().length()==0){
            return false;
        }

        return true;
    }

    private void addEvents() {
        bamnutback();
        thongbaotrenapptionbar();
        viewIntent();
        clickUpdate();
    }
    private void thongbaotrenapptionbar() {
        lblUpbar.setText("Update Infomation");
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
        edtUpName = findViewById(R.id.edtUpName);
        edtUpBirth = findViewById(R.id.edtUpBirth);
        edtUpAvatar = findViewById(R.id.edtUpAvatar);
        edtUpAbout = findViewById(R.id.edtUpAbout);
        btnUpdate = findViewById(R.id.btnupdate);

        btnback = findViewById(R.id.btnBack);
        lblUpbar = findViewById(R.id.lblBar);
    }
}
