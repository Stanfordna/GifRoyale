package com.example.gif_royale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Activity_Play extends AppCompatActivity {

    public static String makeRequest(String TagSearch) {
        final String WEB = "https://api.giphy.com/v1/gifs/random?api_key=";
        final String KEY = "SKBHjyYBvHxbfkDWGeak6jFMz9EXwwtX";
        final String TAG = "&tag=";
        final String SEARCH = TagSearch;
        final String RATING = "&rating=PG-13";

        String url = WEB + KEY + TAG + SEARCH + RATING;
        return url;
    }

    public static String sendGiphyRequest(String requestUrl) {
        BufferedReader in = null;
        StringBuffer response = new StringBuffer();
        try {
            URL obj = new URL(requestUrl);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + requestUrl);
            System.out.println("Response Code : " + responseCode);
            in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fixedWidthUrl = "";
        try {
            JSONObject jsonResponse = new JSONObject(response.toString());
            jsonResponse = new JSONObject(jsonResponse.getString("data"));
            jsonResponse = new JSONObject(jsonResponse.getString("images"));
            jsonResponse = new JSONObject(jsonResponse.getString("fixed_width"));
            fixedWidthUrl = jsonResponse.getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fixedWidthUrl;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__play);


        View.OnClickListener browseClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                if (button.getId() == findViewById(R.id.button_choose).getId()) {
                    startActivity(new Intent(Activity_Play.this, MatchmakingActivity.class));
                } else {
                    WebView nextGif = findViewById(R.id.WebView_gif);
                    nextGif.getSettings().setJavaScriptEnabled(true);
                    //String URL = "https://media2.giphy.com/media/eeZpO7bX9a3HGHcVie/200w.gif?cid=75b441445d23aa636b676256598c1785&rid=200w.gif";
                    String URL = sendGiphyRequest(makeRequest("Fat Cats"));
                    nextGif.loadUrl(URL);
                }
            }
        };

        Button buttonNextGif = findViewById(R.id.button_next);
        buttonNextGif.setOnClickListener(browseClickListener);
        Button buttonChooseGif = findViewById(R.id.button_choose);
        buttonChooseGif.setOnClickListener(browseClickListener);
    }
}



