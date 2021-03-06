package com.example.myapplication;


import static com.example.myapplication.SuperUser.myId;
import static com.example.myapplication.SuperUser.name;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;

public class PMList extends AppCompatActivity {

    static ArrayList<User> users = new ArrayList<>();
    private UserAdapter userAdapter;
    static HashMap<String, Chat> chatsAdapters = new HashMap<>();
    public static GlobalListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("CYCLE_2", "PM LIST CREATE");
        System.out.println(Thread.currentThread());
        setContentView(R.layout.pm_list);
        TextView username = findViewById(R.id.username);
        username.setText("You: " + name + "(" + myId + ")");
        System.out.println("im here");
        ListView usersList = findViewById(R.id.users);
        userAdapter = new UserAdapter(this, R.layout.user, users);
        usersList.setAdapter(userAdapter);
        listener = new GlobalListener(this, this, users, userAdapter);
        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(PMList.this, Chat.class);
                intent.putExtra("user", position);


                startActivity(intent);

            }
        });
    }

    public void updateAdapter(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                userAdapter.notifyDataSetChanged();
            }
        });
    }


    protected void quit() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();

    }
}
