package com.example.mdenker.interestup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by tsengjonathan on 3/29/18.
 */

public class DiscussionMessageFragment extends Fragment implements View.OnClickListener{

    private RecyclerView messageRecycler;
    private DiscussionMessageAdapter messageAdapter;
    private Context context;
    private EditText editText;
    private Button sendButton;
    private ArrayList<DiscussionMessage> messageList;

    public DiscussionMessageFragment() {
    }

    @SuppressLint("ValidFragment")
    public DiscussionMessageFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discussion, container, false);

        messageList = new ArrayList<>();
        messageList.add(new DiscussionMessage("Hello!", "Jack", "04:40"));
        messageList.add(new DiscussionMessage("Let's talk about the event!", "Jill", "05:24"));

        messageRecycler = (RecyclerView) view.findViewById(R.id.recyclerview_message_list);
        messageRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        messageAdapter = new DiscussionMessageAdapter(messageList);
        messageRecycler.setAdapter(messageAdapter);

        editText = (EditText) view.findViewById(R.id.edittext_chatbox);
        sendButton = (Button) view.findViewById(R.id.button_chatbox_send);
        sendButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        messageList.add(new DiscussionMessage(editText.getText().toString(), "You", sdf.format(cal.getTime())));

        messageAdapter = new DiscussionMessageAdapter(messageList);
        messageRecycler.setAdapter(messageAdapter);
        editText.setText("");
    }
}
