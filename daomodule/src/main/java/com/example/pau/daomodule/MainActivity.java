package com.example.pau.daomodule;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pau.daomodule.models.RestaurantORMDao;
import com.example.pau.daomodule.models.RestaurantORMHelper;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;


public class MainActivity extends OrmLiteBaseActivity<RestaurantORMHelper> {

    Button llegirBBDD;
    Button desarBBDD;
    Button borrarBBDD;
    TextView llistat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llegirBBDD = (Button) findViewById(R.id.llegir);
        desarBBDD = (Button) findViewById(R.id.desar);
        borrarBBDD = (Button) findViewById(R.id.borrar);
        llistat = (TextView) findViewById(R.id.llistat);

        //assignem el listener al boto de desar
        desarBBDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();

                int numeroRandom = random.nextInt(100);
                String nomRest = "" + numeroRandom*44 + "";
                String tipus = "fast";
                int valoracio = numeroRandom;

                RestaurantORMDao retaurantORMDao = new RestaurantORMDao(nomRest, valoracio, tipus);

                try {
                    Dao<RestaurantORMDao, Integer> dao = getHelper().getDao();
                    dao.create(retaurantORMDao);
                    llistat.setText("Restaurant: " + nomRest + " Inserit bé");

                } catch (SQLException e) {
                    llistat.setText("Error al inserir: " + nomRest);
                    e.printStackTrace();
                }
            }
        });

        llegirBBDD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                String frase = "";
                try {
                    Dao<RestaurantORMDao, Integer> restaurantDao = getHelper().getDao();
                    List<RestaurantORMDao> llista =
                            restaurantDao.queryForAll();

                    frase += "Numero de Restaurants: " +
                            llista.size() + "\n";

                    for (RestaurantORMDao rest : llista) {
                        frase += rest.getName() + " " + rest.getRate()
                                + " " + rest.getType() + "\n";
                    }

                    llistat.setText(frase);


                } catch (SQLException e) {
                    Log.i("onCreate", "Exception");
                }
            }
        });

        borrarBBDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    TableUtils.clearTable(getConnectionSource(), RestaurantORMDao.class);
                    llistat.setText("Base de dades esborrada correctament");
                }
                catch (SQLException e) {
                    Log.i("onCreate", "Exception");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close this activity?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
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
