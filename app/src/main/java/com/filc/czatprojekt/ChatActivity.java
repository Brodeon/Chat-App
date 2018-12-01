package com.filc.czatprojekt;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements MessegesRecycleViewAdapter.MessageAdded {

    EditText messegeEditText;
    Button sendButton;
    DatabaseReference databaseMesseges;
    FirebaseAuth auth;
    FirebaseUser user;
    RecyclerView chatRecycleView;
    MessegesRecycleViewAdapter messegesRecycleViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messegeEditText = findViewById(R.id.message_et);
        sendButton = findViewById(R.id.send_button);

        databaseMesseges = FirebaseDatabase.getInstance().getReference("messeges");
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        List<Message> messages = new ArrayList<>();
        messegesRecycleViewAdapter = new MessegesRecycleViewAdapter(messages, user, this);
        chatRecycleView = findViewById(R.id.chat_rv);
        chatRecycleView.setAdapter(messegesRecycleViewAdapter);
        chatRecycleView.setLayoutManager(new LinearLayoutManager(this));

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messegeEditText.getText().toString();
                if (validateMessageText(messageText)) {
                    sendMessage(messageText);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseMesseges.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Message message = dataSnapshot.getValue(Message.class);
                messegesRecycleViewAdapter.addMessage(message);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private boolean validateMessageText(String messageText) {
        return !messageText.isEmpty();
    }

    private void sendMessage(String messageText) {
        String id = databaseMesseges.push().getKey();
        if (id != null) {
            Message newMessage = new Message(id, messageText, user.getEmail(), TimestampConverter.getCurrentTimestamp());
            databaseMesseges.child(id).setValue(newMessage);
            messegeEditText.setText("");
        }
    }

    @Override
    public void onMessageAdded(int position) {
        RecyclerView.LayoutManager layoutManager = chatRecycleView.getLayoutManager();
        if (layoutManager != null) {
            layoutManager.scrollToPosition(position);
        }
    }
}
