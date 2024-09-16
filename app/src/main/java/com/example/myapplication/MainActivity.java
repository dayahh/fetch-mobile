package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadJson(textView);
    }

    private void loadJson(TextView textView){
        String[] listToBePopulated;

        try{
            InputStream inputStream = getAssets().open("fetchData.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json;
            int maxLength;
            String id, listid, name;

            json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);
            maxLength = jsonArray.length();

            String value = "";

            for(int i = 0; i < maxLength; i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                id = jsonObject.getString("id");
                listid = jsonObject.getString("listId");
                name = jsonObject.getString("name");
                //TODO: Log.e does print the values properly, so far the variable "items", the textview,
                // is not showing any values and it's also not appearing on screen.
                // If worse comes to worse, just organize the list in the
                // logcat instead of displaying on screen.
                value += ("id: " + id + " listid: " + listid + " name: " + name + "\n");
            }

            textView.setText(value);

        } catch (Exception e) {
            Log.e("TAG", "loadJson: error" + e);
        }
    }


}