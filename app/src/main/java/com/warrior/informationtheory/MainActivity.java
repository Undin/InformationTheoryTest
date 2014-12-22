package com.warrior.informationtheory;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by warrior on 18.12.14.
 */
public class MainActivity extends ListActivity {

    private static final String[] TASKS = {
            "Entropy",
            "Redundancy",
            "Average length",
            "Arithmetic",
            "Arithmetic Decode"};
    private static final Class[] ACTIVITIES = {
            EntropyActivity.class,
            RedundancyActivity.class,
            AverageLength.class,
            ArithmeticActivity.class,
            ArithmeticDecodeActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, TASKS);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Class activityClass = ACTIVITIES[position];
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }
}
