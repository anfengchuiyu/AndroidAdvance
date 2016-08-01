package com.example.touch.swiperefreshlayout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XH on 2016/7/30.
 */
public class SwipeRefreshActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private MyAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        swipeRefreshLayout = new SwipeRefreshLayout(this);
        recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout.addView(recyclerView);

        setContentView(swipeRefreshLayout);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(""+i);
        }
        adapter = new MyAdapter(this, data);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    private class MyAdapter extends RecyclerView.Adapter{

        private List<String> data;
        private Context context;

        public MyAdapter(Context context, List<String> data) {
            this.data = data;
            this.context = context;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,
                    parent, false);
            return new MyVH(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyVH vh = (MyVH) holder;
            String str = data.get(position);
            vh.tv.setText(str);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        private class MyVH extends RecyclerView.ViewHolder{

            TextView tv;

            public MyVH(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(android.R.id.text1);
            }
        }
    }

}
