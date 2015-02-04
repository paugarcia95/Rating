package com.example.inlab.customadapter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.inlab.customadapter.model.Restaurants;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private ListView mListView;
    private EditText mEditText;
    private Button mButton;
    private MyCustomAdapter adapter;

    private ArrayList<Restaurants> restaurants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);
        mEditText = (EditText) findViewById(R.id.editText);
        mButton = (Button) findViewById(R.id.button);
        adapter = new MyCustomAdapter(getApplicationContext(), restaurants);

        fetchData();
//setAdapter executa automaticament el getView de la nostra classe MyCustomAdapter
        mListView.setAdapter(adapter);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = mEditText.getText().toString();
                mEditText.setText("");
                Restaurants r = new Restaurants(t, "");
                adapter.add(r);
            }
        });

    }
    private void fetchData(){
        restaurants.add(new Restaurants("Mc-Donalds","Low"));
        restaurants.add(new Restaurants("Pans","High"));
        restaurants.add(new Restaurants("BurgerKing", "Free"));

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
