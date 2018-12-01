package com.filc.czatprojekt;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessegesRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Message> messeges;
    private FirebaseUser user;

    public MessegesRecycleViewAdapter(List<Message> messeges, FirebaseUser user) {
        this.messeges = messeges;
        this.user = user;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case 1:
                View view1 = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.logged_user_message, viewGroup, false);
                return new Message1ViewHolder(view1);
            case 2:
                View view2 = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.another_user_message, viewGroup, false);
                return new Message2ViewHolder(view2);
            default:
                return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messeges.get(position);

        if (message.getMessageUser() != null) {
            if (message.getMessageUser().equals(user.getEmail())) {
                return 1; //To my wyslalismy ta wiadomosc
            } else {
                return 2; //To nie my wyslalismy ta wiadomosc
            }
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Message message = messeges.get(i);

        switch (viewHolder.getItemViewType()) {
            case 1:
                Message1ViewHolder message1ViewHolder = (Message1ViewHolder) viewHolder;

                message1ViewHolder.message.setText(message.getMessageText());
                message1ViewHolder.date.setText(TimestampConverter.convertTimeStamp(message.getTimestamp()));
                message1ViewHolder.email.setText(user.getEmail());
                break;
            case 2:
                Message2ViewHolder message2ViewHolder = (Message2ViewHolder) viewHolder;

                message2ViewHolder.message.setText(message.getMessageText());
                message2ViewHolder.date.setText(TimestampConverter.convertTimeStamp(message.getTimestamp()));
                message2ViewHolder.email.setText(message.getMessageUser());
                break;
        }
    }

    public void addMessage(Message message) {
        messeges.add(message);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return messeges != null? messeges.size() : 0;
    }

    class Message1ViewHolder extends RecyclerView.ViewHolder {
        TextView email;
        TextView message;
        TextView date;


        public Message1ViewHolder(@NonNull View itemView) {
            super(itemView);

            email = itemView.findViewById(R.id.logged_user_email);
            message = itemView.findViewById(R.id.logged_user_message);
            date = itemView.findViewById(R.id.logged_user_date);
        }
    }

    class Message2ViewHolder extends RecyclerView.ViewHolder {
        TextView email;
        TextView message;
        TextView date;

        public Message2ViewHolder(@NonNull View itemView) {
            super(itemView);

            email = itemView.findViewById(R.id.another_user_email);
            message = itemView.findViewById(R.id.another_user_message);
            date = itemView.findViewById(R.id.another_user_date);
        }
    }


}
