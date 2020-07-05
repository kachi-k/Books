package com.kachi.Books;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class BooksListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        try {
            URL bookUrl = ApiUtil.buildUrl("cooking");
            new BooksQueryTask().execute(bookUrl);
        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }
    }
   public class BooksQueryTask extends AsyncTask<URL, Void, String>{

       @Override
       protected String doInBackground(URL... urls) {
           URL searchUrl = urls[0];
           String result = null;
           try {
               result = ApiUtil.getJson(searchUrl);
           }
           catch (IOException e){
               Log.d("Error", e.getMessage());
           }
           return result;
       }

       @Override
       protected void onPostExecute (String result) {
           TextView tvResult = findViewById(R.id.tvResponse);
           tvResult.setText(result);
       }
   }
}