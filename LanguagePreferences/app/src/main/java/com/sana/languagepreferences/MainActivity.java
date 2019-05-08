package com.sana.languagepreferences;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    SharedPreferences sharedPreferences;

    public void setLangauge(String language){
        //Toast.makeText(this, language, Toast.LENGTH_SHORT).show();
        sharedPreferences.edit().putString("language", language).apply();

        textView.setText(language);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        setLangauge(item.getTitle().toString());
        return true;

        /*switch (item.getItemId()){
            case R.id.english:
                setLangauge(item.getTitle().toString());
                return true;
            case R.id.spanish:
                setLangauge(item.getTitle().toString());
                return true;
            default:
                return false;
        }*/

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences= this.getSharedPreferences("com.sana.languagepreferences", Context.MODE_PRIVATE);
        textView=(TextView) findViewById(R.id.textView);
        String langauge=sharedPreferences.getString("language", "" );
        if(langauge=="") {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_launcher_background)
                    .setTitle("Language choice")
                    .setMessage("Choose a language")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLangauge("English");
                        }
                    })
                    .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLangauge("Spanish");
                        }
                    })
                    .show();
        }
        else {
            textView.setText(langauge);
        }
    }
}
