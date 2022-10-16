package com.ndphuc.dp.demo2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements RecyclerContactAdapter.Callback {

    private RecyclerContactAdapter adapter;
    private RecyclerView relist1;
    private ArrayList<Contact> arrContacts = new ArrayList<>();
    private Button btnAddPer;
    private static final String TAG = "MainActivity";
    SimpleDateFormat dd= new SimpleDateFormat("dd/MM/yyyy");

    public MainActivity() {
    }

    public final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==33){
                        SimpleDateFormat dd= new SimpleDateFormat("dd/MM/yyyy");
                        Intent data = result.getData();

                        String name = data.getStringExtra("NAME");
                        String avatar=data.getStringExtra("AVATAR");
                        String birth = data.getStringExtra("BIRTH");
                        String about = data.getStringExtra("ABOUT");
                        String id = "name"+birth;
                        Date ns = null;
                        try {
                            ns = dd.parse(birth);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        arrContacts.add(new Contact(id,name, avatar, about, ns));
                        chenvaolist();

                    }
                    else if(result.getResultCode()==66){
                        SimpleDateFormat dd= new SimpleDateFormat("dd/MM/yyyy");
                        Intent data = result.getData();
                        String id = data.getStringExtra("ID");
                        String name = data.getStringExtra("NAME");
                        String avatar=data.getStringExtra("AVATAR");
                        String birth = data.getStringExtra("BIRTH");
                        String about = data.getStringExtra("ABOUT");
                        Date ns = null;
                        try {
                            ns = dd.parse(birth);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        for (int i = 0 ; i< arrContacts.size(); i++){
                            if (arrContacts.get(i).getId().equals(id)){
                                arrContacts.get(i).setName(name);
                                arrContacts.get(i).setAvatar(avatar);
                                arrContacts.get(i).setBirthDay(ns);
                                arrContacts.get(i).setAbout(about);
                            }
                        }
                        chenvaolist();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addConTrols();
        addEvents();

    }

    private void addEvents() {

        themphantu();
        addFragment();
        chenvaolist();
        clickADD();

    }

    private void addFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new info();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }


    // tự động lấy kq trả về từ màn hình khác

    private void clickADD() {
        btnAddPer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewPer();
            }
        });
    }

    private void addNewPer() {
        Intent intent = new Intent(MainActivity.this,add_person.class);
        resultLauncher.launch(intent);
    }

    private void chenvaolist() {
        relist1.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerContactAdapter(this, arrContacts);

        adapter.setCallBack(this);
        relist1.setAdapter(adapter);

    }

    private void themphantu() {
        try {
            SimpleDateFormat  dd = new SimpleDateFormat("dd/MM/yyyy");
            arrContacts.add(new Contact("1","Tèo quang", "https://i.pinimg.com/originals/7c/c7/a6/7cc7a630624d20f7797cb4c8e93c09c1.png"
                    , "Một người năng động",dd.parse("30/2/1882")));
            arrContacts.add(new Contact("2","Quang", "https://cdn-icons-png.flaticon.com/128/4128/4128176.png"
                    , "Một người năng động",dd.parse("30/2/1882")));
            arrContacts.add(new Contact("3","Cường", "https://cdn.iconscout.com/icon/free/png-256/avatar-377-456329.png"
                    , "Một người năng động",dd.parse("30/2/1882")));
            arrContacts.add(new Contact("4","Nghĩa", "https://www.shareicon.net/data/256x256/2016/07/26/802001_man_512x512.png"
                    , "Một người năng động",dd.parse("30/2/1882")));
            arrContacts.add(new Contact("5","Châm", "https://www.shareicon.net/data/256x256/2016/07/26/802001_man_512x512.png"
                    , "Một người năng động",dd.parse("30/2/1882")));
            arrContacts.add(new Contact("6","Như", "https://www.shareicon.net/data/256x256/2016/07/26/802001_man_512x512.png"
                    , "Một người năng động",dd.parse("30/2/1882")));
            arrContacts.add(new Contact("7","Luận", "https://www.shareicon.net/data/256x256/2016/07/26/802001_man_512x512.png"
                    , "Một người năng động",dd.parse("30/2/1882")));
        }
        catch (Exception e){
            Toast.makeText(this,R.string.ErrorAddArray,Toast.LENGTH_SHORT).show();
        }
    }

    private void addConTrols() {
        Dialog dialog = new Dialog(MainActivity.this);
        relist1 = findViewById(R.id.relist1);

        btnAddPer = findViewById(R.id.btnNewPerson);
    }



    @Override
    public void callbackRemove(Contact contact) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog,null);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();

        Button btnCanCel = view.findViewById(R.id.btnNo);
        Button btnYes= view.findViewById(R.id.btnYes);
        ImageView btnNo = view.findViewById(R.id.btnCanCel);

        btnCanCel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("position", contact.getId());
                for (int i =0 ;i<arrContacts.size();i++){
                    if(arrContacts.get(i).getId().equals(contact.getId())){
                        arrContacts.remove(i);
                        adapter.notifyItemRemoved(i);
                        break;
                    }
                }
                alertDialog.dismiss();


            }
        });



        alertDialog.show();
    }

    @Override
    public void callbackUpdate(Contact contact) {
        for (int i =0 ;i<arrContacts.size();i++){
            if(arrContacts.get(i).getId().equals(contact.getId())){

                Intent intent = new Intent(this,updatePerson.class);
                intent.putExtra("ID",contact.getId());
                intent.putExtra("NAME",contact.getName());
                intent.putExtra("AVATAR",contact.getAvatar());
                intent.putExtra("ABOUT",contact.getAbout());
                String ns = dd.format(contact.getBirthDay());
                intent.putExtra("BIRTH",ns);
                this.resultLauncher.launch(intent);
                break;
            }
        }
    }

    @Override
    public void callbackInfo(Contact contact) {
        ImageView imgInf = findViewById(R.id.imgInf);
        TextView txtNameInf = findViewById(R.id.txtNameInf);
        TextView txtBirthInf = findViewById(R.id.txtBirthInf);
        TextView txtAboutInf = findViewById(R.id.txtAboutInf);
        txtNameInf.setText(contact.getName());
        txtBirthInf.setText(dd.format(contact.getBirthDay()));
        txtAboutInf.setText(contact.getAbout());

        //load image
        Glide.with(this)
                .load(contact.getAvatar())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imgInf);
    }
}