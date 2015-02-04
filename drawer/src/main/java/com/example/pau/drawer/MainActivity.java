package com.example.pau.drawer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pau.drawer.fragments.BasicFragment;


public class MainActivity extends ActionBarActivity {
    private String[] leftMenu = new String[] {"Main", "Top", "Flamers"};
    private DrawerLayout drawerLayout;
    private ListView listView;
    //private ActionBarDrawerToggle mDrawerToggle; //v4 - Lu que està comentat no funciona, és per posar un botó a dalt a l'esquerra

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        /*mDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.drawable.ic_drawer,
                R.string.open,
                R.string.close
        ) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);*/

        listView = (ListView) findViewById(R.id.left_drawer);

        //el adapter serveix perquè posi els elements al menú
        //una llista
        listView.setAdapter(
                new ArrayAdapter<String>(
                        getApplicationContext(),
                        android.R.layout.simple_list_item_1, //Es un tipus de llista ja feta
                        leftMenu                             //Posem el nostre array a la llista
                ));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
    }

    private void selectItem(int position) {
// Create a new fragment and specify the planet to show based on position
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new BasicFragment(true);
                break;
            case 1:
                fragment = new BasicFragment(false);
                break;
            default:
                fragment = new BasicFragment(true);
        }
// Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
// Highlight the selected item, update the title, and close the drawer
        listView.setItemChecked(position, true);
        setTitle(leftMenu[position]);
        drawerLayout.closeDrawer(listView);
    }
    @Override
    public void setTitle(CharSequence title) {
//getActionBar().setTitle(title.subSequence(0, 2));
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
