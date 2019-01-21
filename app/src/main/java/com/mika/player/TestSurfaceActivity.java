package com.mika.player;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Author: mika
 * @Time: 2019/1/8 3:18 PM
 * @Description:
 */
public class TestSurfaceActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_surface);

//        recyclerView = findViewById(R.id.recyclerView);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        recyclerView.setAdapter(new ListAdapter());
    }

    class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

        @NonNull
        @Override
        public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ListViewHolder(LayoutInflater.from(TestSurfaceActivity.this).inflate(R.layout.item_surface, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return 10;
        }

        class ListViewHolder extends RecyclerView.ViewHolder {

            public ListViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }

    }
}
