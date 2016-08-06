package com.example.touch;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.touch.aidl.MyAidlActivity;
import com.example.touch.jni.JniTest;
import com.example.touch.jni.JniTestActivity;
import com.example.touch.pullrefresh.PullrefreshActivity;
import com.example.touch.swiperefreshlayout.SwipeRefreshActivity;
import com.example.touch.touch_delegate.TouchDelegateActivity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String[] data = new String[]{
                "Touch Delegate Demo",
                "Jni Test",
                "SwipeRefreshLayout源码分析",
                "AIDL Demo",
                "Custom PullRefresh"
        };
        RecyclerAdapter adapter = new RecyclerAdapter(this, Arrays.asList(data));
        recyclerView.setAdapter(adapter);
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    private static class RecyclerAdapter extends RecyclerView.Adapter implements ItemClickListener {


        private Context context;
        private List<String> data;

        public RecyclerAdapter(Context context, List<String> data) {
            this.context = context;
            this.data = data;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
            RecyclerVH vh = new RecyclerVH(view);
            vh.setItemClickListener(this);
            return vh;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            RecyclerVH vh = (RecyclerVH) holder;
            String str = data.get(position);
            vh.tv.setText(str);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public void onItemClick(View view, int position) {
            Intent intent = null;
            switch (position){
                case 0:
                    //touch事件的委托
                    intent = new Intent(context, TouchDelegateActivity.class);
                    context.startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(context, JniTestActivity.class);
                    context.startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(context, SwipeRefreshActivity.class);
                    context.startActivity(intent);
                    break;
                case 3:
                    intent = new Intent(context, MyAidlActivity.class);
                    context.startActivity(intent);
                    break;
                case 4:
                    intent = new Intent(context, PullrefreshActivity.class);
                    context.startActivity(intent);
                    break;
            }
            //Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
        }

        private class RecyclerVH extends RecyclerView.ViewHolder implements View.OnClickListener {

            private TextView tv;
            private ItemClickListener itemClickListener;

            public RecyclerVH(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.text);
                itemView.setOnClickListener(this);
            }

            public void setItemClickListener(ItemClickListener itemClickListener) {
                this.itemClickListener = itemClickListener;
            }

            @Override
            public void onClick(View v) {
                if (itemClickListener != null)
                    itemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

}
