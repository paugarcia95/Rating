package com.example.inlab.rating;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {


    private TextView mTextView;
    private EditText mEditTextMarca;
    private EditText mEditTextModel;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView2);
        mEditTextMarca = (EditText) findViewById(R.id.editTextMarca);
        mEditTextModel = (EditText) findViewById(R.id.editTextModel);
        mButton = (Button) findViewById(R.id.button);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(), SecondActivity.class);
                if(mEditTextModel.getText().toString().equals("") || mEditTextMarca.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Edit Text empty", Toast.LENGTH_SHORT).show();
                }
                else {

                    mIntent.putExtra("marca", mEditTextMarca.getText().toString());
                    mIntent.putExtra("model", mEditTextModel.getText().toString());
                    startActivity(mIntent);
                }
            }
        });
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
