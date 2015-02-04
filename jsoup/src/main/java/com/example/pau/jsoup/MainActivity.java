package com.example.pau.jsoup;

import android.os.AsyncTask;
import android.renderscript.Element;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RedditTask redditTask = new RedditTask();
        redditTask.execute();
    }

    private class RedditTask extends AsyncTask<Void, Void, List<String>> {

        @Override
        protected List<String> doInBackground(Void... params) {
            List<String> result = new ArrayList<>();
            Log.w("RedditTask", "Something was wrong");
            try {
                Document doc = Jsoup.connect("http://www.reddit.com/").get(); //Aqui tenim tot l'html de la pàgina
                Elements redditTitles = doc.select("a.title"); //De l'html selecciones les etiquetes "a" que siguin "title"
                Log.i("RedditElements size", "SIZE: " + redditTitles.size());

                for (org.jsoup.nodes.Element title : redditTitles) {
                    Log.i("RedditTitle", title.text());
                }

            } catch (IOException e) {
                Log.w("RedditTask", "Something was wrong");
                cancel(true);
            }


            return result;
        }
    }

}
