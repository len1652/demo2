package com.ndphuc.dp.demo2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder> {
    Context context;
    ArrayList<Contact> arrContacts;

    private Callback callback;


    void setCallBack(Callback callback) {
        this.callback=callback;
    }

    interface Callback {
        void callbackRemove(Contact contact);
        void callbackUpdate(Contact contact);
        void callbackInfo(Contact contact);
    }
    public RecyclerContactAdapter(Context context, ArrayList<Contact> arrContacts) {
        this.context = context;
        this.arrContacts = arrContacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_one_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Contact model1 = arrContacts.get(position);
        holder.lblName.setText(model1.getName());

        // loading avatar
        Glide.with(context)
                .load(model1.getAvatar())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.imgAvatar);

        // click on delete
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showDiaLog(model1.getId());
                callback.callbackRemove(model1);

            }
        });

        // click long update
        holder.llrow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                callback.callbackUpdate(model1);
                return true;
            }
        });
        holder.llrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.callbackInfo(model1);
            }
        });
    }




    @Override
    public int getItemCount() {
        return arrContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView lblName;
        ImageView imgAvatar;
        ImageButton btnDelete;
        LinearLayout llrow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lblName = itemView.findViewById(R.id.lblName);
            imgAvatar = itemView.findViewById(R.id.imgAtvatar);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            llrow = itemView.findViewById(R.id.llRow);
        }
    }

}
