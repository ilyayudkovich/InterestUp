package com.example.mdenker.interestup;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class Search extends AppCompatActivity implements SearchView.OnQueryTextListener{

    EventAdapter adapter;
    ListView listView;
    SearchView searchView;
    Event[] events = {new Event("Hiking Trip", "2018/03/21", "Blue Mountains", Collections.<String>emptyList())
            , new Event("Go Hiking", "2018/04/01", "Red Hills", Collections.<String>emptyList())
            , new Event("Cats the Musical", "2018/06/17", "Boston Opera House", Collections.<String>emptyList())};


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        adapter = new EventAdapter(this, R.layout.activity_list_view);

        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
        searchView.setOnQueryTextListener(this);
    }


    protected void eventFilter(String search) {
        ArrayList<Event> eventResult = new ArrayList<>();

        for (int i = 0; i < events.length; i++) {
            if (events[i].getName().toLowerCase().contains(search.toLowerCase())) {
                eventResult.add(events[i]);
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
            eventDetail.setText(event.getEndDate() + " | " + event.getLocation());

            return convertView;
        }
    }
}
