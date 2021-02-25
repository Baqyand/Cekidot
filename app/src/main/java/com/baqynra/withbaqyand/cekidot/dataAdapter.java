package com.baqynra.withbaqyand.cekidot;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class dataAdapter extends RecyclerView.Adapter<dataAdapter.UserViewHolder>{
    Context context;
    OnUserClickListener listener;
    List<modelPost> listPostInfo;
    private SQLiteDatabase db;

    public dataAdapter(Context context, List<modelPost> listPostInfo, OnUserClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.listPostInfo = listPostInfo;
    }

    public	interface OnUserClickListener{
        void onUserClick(modelPost currentPost, String action);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post,parent,false);
        UserViewHolder postViewHolder = new UserViewHolder(view);

        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        final modelPost currentPost = listPostInfo.get(position);
        holder.ctxtDate.setText(currentPost.getDatetime());
        holder.ctxtTitle.setText(currentPost.getTitle());
        holder.ctxtdesc.setText(currentPost.getDesc());
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(currentPost, "Update");
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            listener.onUserClick(currentPost,"Delete");
        }
        });

    }

    @Override
    public int getItemCount() {
        return listPostInfo.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView ctxtTitle,ctxtdesc, ctxtDate;
        ImageView imgDelete,imgEdit;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ctxtTitle=itemView.findViewById(R.id.txtTitle);
            ctxtdesc=itemView.findViewById(R.id.txtDesc);
            ctxtDate=itemView.findViewById(R.id.txtDate);
            imgEdit=itemView.findViewById(R.id.imgedit);
            imgDelete=itemView.findViewById(R.id.imgdelete);
        }
    }
}
