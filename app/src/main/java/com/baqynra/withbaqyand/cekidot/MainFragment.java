package com.baqynra.withbaqyand.cekidot;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;


public class MainFragment extends Fragment implements View.OnClickListener, dataAdapter.OnUserClickListener {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Context context;
    List<modelPost> listPostInfo;

    public MainFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();
        recyclerView = view.findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        Databasehelper db = new Databasehelper(context);
        listPostInfo = db.selectPostData();

        dataAdapter adapter = new dataAdapter(context,listPostInfo,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onUserClick(modelPost currentPost, String action) {
        if(action.equals("Update")){
            String title = currentPost.getTitle();
            String desc = currentPost.getDesc();
            Intent intent = new Intent(getActivity(), UpdateActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("DESC", desc);
            startActivity(intent);
        } if(action.equals("Delete")){
            Databasehelper db=new Databasehelper(context);
            db.delete(currentPost.getTitle());
            Toast.makeText(context, "Delete post successfull", Toast.LENGTH_LONG).show();
            setupRecyclerView();
        }


    }

}