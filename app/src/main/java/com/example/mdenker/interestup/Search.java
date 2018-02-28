package com.example.mdenker.interestup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

/**
 * Created by tsengjonathan on 2/28/18.
 */

public class Search extends AppCompatActivity implements SearchView.OnQueryTextListener{

    ArrayAdapter adapter;
    ListView listView;
    SearchView searchView;
    String[] events = {"Hiking Trip", "Go Hiking", "Another Event"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        adapter = new ArrayAdapter<String>(this, R.layout.activity_list_view, new String[] {});

        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
        searchView.setOnQueryTextListener(this);
    }


    protected void eventFilter(String search) {
        ArrayList<String> result = new ArrayList<>();

        for (String s : events) {
            if (s.contains(search)) {
                result.add(s);
            }
        }

        System.out.println(result.toString());

        adapter = new ArrayAdapter<String>(this, R.layout.activity_list_view, result.toArray(new String[result.size()]));
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
}
