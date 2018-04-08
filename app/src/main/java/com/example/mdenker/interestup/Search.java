package com.example.mdenker.interestup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by tsengjonathan on 2/28/18.
 */

public class Search extends AppCompatActivity implements SearchView.OnQueryTextListener {

    EventAdapter adapter;
    ListView listView;
    SearchView searchView;
    ArrayList<Event> eventResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        adapter = new EventAdapter(this, R.layout.activity_list_view);

        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Search.this, EventDisplay.class);
                intent.putExtra("event", eventResult.get(i).getID());
                startActivity(intent);
            }
        });

        searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search Events");
        searchView.setOnQueryTextListener(this);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });
    }


    protected void eventFilter(String search) {
        eventResult = new ArrayList<>();

        for (Event event : Database.events) {
            if (event.getName().toLowerCase().contains(search.toLowerCase()) ||
                    containsIgnoreCase(event.getInterests(), search)) {
                eventResult.add(event);
            }
        }

        adapter = new EventAdapter(this, R.layout.activity_list_view, eventResult);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        eventFilter(s);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    public void OnBackClick(View view) {
        this.finish();
    }

    public void OnEventClick(View view) {
        System.out.println(listView.getSelectedItem());
    }


    public class EventAdapter extends ArrayAdapter<Event> {


        public EventAdapter(@NonNull Context context, int resource) {
            super(context, resource);
        }

        public EventAdapter(@NonNull Context context, int resource, List<Event> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Event event = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_view, parent, false);
            }

            TextView eventName = (TextView) convertView.findViewById(R.id.eventName);
            TextView eventDetail = (TextView) convertView.findViewById(R.id.eventDetail);

            eventName.setText(event.getName());
            eventDetail.setText(event.getEndDateTime().getTime().toString().substring(0, 10) + " | " + event.getLocation());

            return convertView;
        }
    }

    private boolean containsIgnoreCase(List<String> interests, String input) {
        for (String s : interests) {
            if (s.toLowerCase().contains(input.toLowerCase())) {
                return true;
            }
        }

        return false;
    }
}
