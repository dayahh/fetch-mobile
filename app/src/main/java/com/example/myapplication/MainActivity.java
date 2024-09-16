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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TextView items = findViewById(R.id.items);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadJson(items);
    }

    private void loadJson(TextView textView){

        try{
            InputStream inputStream = getAssets().open("fetchData.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, StandardCharsets.UTF_8);
            String id, listid, name;
            JSONArray jsonArray = new JSONArray(json);

            String value = "";

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                id = jsonObject.getString("id");
                listid = jsonObject.getString("listId");
                name = jsonObject.getString("name");
                //TODO: sort by "listid" in increasing order

                    if(!name.isBlank() && !name.equals("null")){
                        value += (" listid: " + listid + ", name: " + name + ", id: " + id +  "\n" + "\n");
                    }
            }

            textView.setText(value);

        } catch (Exception e) {
            Log.e("TAG", "loadJson: error" + e);
        }
    }


}