package com.gdjt.gyanoday.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import com.gdjt.gyanoday.AppData;
import com.gdjt.gyanoday.Constant;
import com.gdjt.gyanoday.R;
import com.gdjt.gyanoday.adapters.NotificationListAdapter;
import com.gdjt.gyanoday.beans.DataBean;
import com.gdjt.gyanoday.beans.NotificationBean;
import com.gdjt.gyanoday.callbacks.IRowClickCallback;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

public class NotificationActivity extends AppCompatActivity implements IRowClickCallback {


    private RecyclerView mNotificationRv;
    private NotificationListAdapter mAdapter;
    private ArrayList<NotificationBean> mDataList;
    private FirebaseFirestore mFireStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setSubtitle(R.string.title_activity_notification);
            getSupportActionBar().setTitle(R.string.menu_information_hindi);
        }
        mDataList = new ArrayList<>();

        mNotificationRv = (RecyclerView) findViewById(R.id.rv_notification);
        mFireStore = FirebaseFirestore.getInstance();
        setupFireStore();
        mAdapter = new NotificationListAdapter(this, mDataList, this);
        mNotificationRv.setAdapter(mAdapter);
        LinearLayoutManager lLayout = new LinearLayoutManager(this);
        mNotificationRv.setLayoutManager(lLayout);

    }

    private void setupFireStore() {
        mFireStore.collection("Notification").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e != null){
                    Log.d("NotificationActivity","Error in fetching record"+e.getMessage());
                }else {
                    for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                        if(doc.getType() == DocumentChange.Type.ADDED) {
                            String title = doc.getDocument().getString("title");
                            String message = doc.getDocument().getString("message");
                            String detailsMessage = doc.getDocument().getString("detailsMessage");
                            String note = doc.getDocument().getString("note");
                            String imageUrl = doc.getDocument().getString("imageUrl");
                            String createDate = doc.getDocument().getString("createDate");

                            NotificationBean notificationBean = new NotificationBean(title, message, detailsMessage, imageUrl, note, createDate);
                            mDataList.add(notificationBean);
                        }
                    }
                    setData();
                }
            }
        });
    }

    private void setData(){
        if(!Constant.DUMMY_DATA_ON){
            mDataList.addAll(AppData.getInstance().getNotificationData());
            mAdapter.notifyDataSetChanged();
        }else{
            mAdapter.notifyDataSetChanged();

        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(int aPosition) {
        NotificationBean curBean = mDataList.get(aPosition);
        Intent intent = new Intent(NotificationActivity.this, NotificationDetailsActivity.class);
        intent.putExtra(Constant.INTENT_NOTIFICATION_DATA, curBean);
        startActivity(intent);
    }

    @Override
    public void onClick(DataBean aBean) {

    }
}
