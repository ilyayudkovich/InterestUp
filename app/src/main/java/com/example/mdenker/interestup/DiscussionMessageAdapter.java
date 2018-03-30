package com.example.mdenker.interestup;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tsengjonathan on 3/29/18.
 */

public class DiscussionMessageAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private List<DiscussionMessage> messageList;

    public DiscussionMessageAdapter(List<DiscussionMessage> messageList) {
        this.messageList = messageList;
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        DiscussionMessage message = (DiscussionMessage) messageList.get(position);

        if (message.sender == "") {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    // Inflates the appropriate layout according to the ViewType.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.discussion_message_sent, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.discussion_message_received, parent, false);
        }

        return new MessageHolder(view);
    }

    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DiscussionMessage message = (DiscussionMessage) messageList.get(position);
        ((MessageHolder) holder).bind(message);
    }

    private class MessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, nameText;

        MessageHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
            nameText = (TextView) itemView.findViewById(R.id.text_message_name);
        }

        void bind(DiscussionMessage message) {
            messageText.setText(message.message);
            timeText.setText(message.createdAt);
            nameText.setText(message.sender);
        }
    }
}

