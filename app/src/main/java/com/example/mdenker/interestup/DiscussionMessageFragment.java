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
import java.util.ArrayList;

/**
 * Created by tsengjonathan on 3/29/18.
 */

public class DiscussionMessageFragment extends Fragment {

    private RecyclerView messageRecycler;
    private DiscussionMessageAdapter messageAdapter;
    private Context context;

    public DiscussionMessageFragment() {
    }

    @SuppressLint("ValidFragment")
    public DiscussionMessageFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discussion, container, false);

        ArrayList<DiscussionMessage> messageList = new ArrayList<>();
        messageList.add(new DiscussionMessage("Hello!", "Jack", "17:40"));

        messageRecycler = (RecyclerView) view.findViewById(R.id.recyclerview_message_list);
        messageRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        messageAdapter = new DiscussionMessageAdapter(messageList);
        messageRecycler.setAdapter(messageAdapter);


        return view;
    }
}
