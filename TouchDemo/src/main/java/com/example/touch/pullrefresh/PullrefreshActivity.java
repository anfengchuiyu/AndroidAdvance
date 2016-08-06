package com.example.touch.pullrefresh;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.touch.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhe on 2016/8/5.
 */
public class PullrefreshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_pullrefresh);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(""+i);
        }
        MyAdapter adapter = new MyAdapter(this, data);
        recyclerView.setAdapter(adapter);


        final PullToRefreshLayout pullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.pullToRefreshLayout);
        pullToRefreshLayout.setmDragMode(PullToRefreshLayout.UNLIMIT_DRAG_HEIGHT);
        View headerView = pullToRefreshLayout.setRefreshView(android.R.layout.simple_list_item_1);
        final TextView header_tv = (TextView) headerView.findViewById(android.R.id.text1);
        header_tv.setPadding(10, 100, 10, 100);
        header_tv.setBackgroundColor(Color.parseColor("#cdcdcd"));
        header_tv.setGravity(Gravity.CENTER);
        header_tv.setText("下拉刷新");

        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListenerAdapter(){
            @Override
            public void onRefresh() {
                header_tv.setText("正在刷新");
                pullToRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }

            @Override
            public void onFinish() {
                header_tv.setText("刷新完成");
            }

            @Override
            public void onDragDistanceChange(float distance, float percent, float offset) {
                super.onDragDistanceChange(distance, percent, offset);
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
