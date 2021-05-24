package com.parkho.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PhMainActivity extends AppCompatActivity {

    // List item
    private List<PhRecyclerItem> mItemList = new ArrayList<>();

    // Recycler view adapter
    private PhRecyclerViewAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // List 설정
        bindList();

        // 삽입 설정
        bindInsert();

        // 수정 설정
        bindModify();

        // 삭제 설정
        bindDelete();
    }

    /**
     * List 설정
     */
    private void bindList() {
        // List item 생성

        mItemList.add(new PhRecyclerItem(R.drawable.emoji_u1f600, "emoji_u1f600"));
        mItemList.add(new PhRecyclerItem(R.drawable.emoji_u1f601, "emoji_u1f601"));
        mItemList.add(new PhRecyclerItem(R.drawable.emoji_u1f602, "emoji_u1f602"));
        mItemList.add(new PhRecyclerItem(R.drawable.emoji_u1f603, "emoji_u1f603"));
        mItemList.add(new PhRecyclerItem(R.drawable.emoji_u1f604, "emoji_u1f604"));

        // Recycler view
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        // Adapter 설정
        mRecyclerAdapter = new PhRecyclerViewAdapter(mItemList);
        recyclerView.setAdapter(mRecyclerAdapter);

        // Layout manager 추가
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Animation 설정

        /* DefaultItemAnimator
        DefaultItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setChangeDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        */

        // SimpleItemAnimator
        PhItemAnimator itemAnimator = new PhItemAnimator(this);
        recyclerView.setItemAnimator(itemAnimator);
    }

    /**
     * 삽입 설정
     */
    private void bindInsert() {
        findViewById(R.id.btn_insert).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Item 추가
                mItemList.add(new PhRecyclerItem(R.drawable.emoji_u1f605, "emoji_u1f605 " + mItemList.size()));

                // List 반영
                mRecyclerAdapter.notifyItemInserted(mItemList.size());
            }
        });
    }

    /**
     * 수정 설정
     */
    private void bindModify() {
        findViewById(R.id.btn_modify).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final PhRecyclerItem recyclerItem = mRecyclerAdapter.getSelected();
                if (recyclerItem == null) {
                    Toast.makeText(PhMainActivity.this, R.string.err_no_selected_item, Toast.LENGTH_SHORT).show();
                    return;
                }

                // Recycler item 수정
                recyclerItem.setName(recyclerItem.getName() + " is modified");

                // 선택 항목 초기화
                mRecyclerAdapter.clearSelected();

                // List 반영
                mRecyclerAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 삭제 설정
     */
    private void bindDelete() {
        findViewById(R.id.btn_delete).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final PhRecyclerItem recyclerItem = mRecyclerAdapter.getSelected();
                if (recyclerItem == null) {
                    Toast.makeText(PhMainActivity.this, R.string.err_no_selected_item, Toast.LENGTH_SHORT).show();
                    return;
                }

                // 선택한 item 삭제
                mItemList.remove(recyclerItem);

                // List 반영
                final int checkedPosition = mRecyclerAdapter.getCheckedPosition();
                mRecyclerAdapter.notifyItemRemoved(checkedPosition);

                // 선택 항목 초기화
                mRecyclerAdapter.clearSelected();
            }
        });
    }
}