package com.deeppomal.basistask;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.wenchao.cardstack.CardAnimator;
import com.wenchao.cardstack.CardStack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CardStack mCardStack;
    private CardAdapter mCardAdapter;
    private Button undo,reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCardStack = (CardStack) findViewById(R.id.container);
        undo = (Button) findViewById(R.id.undo);
        reset = (Button) findViewById(R.id.reset);

        mCardStack.setContentResource(R.layout.card_items);
        mCardAdapter = new CardAdapter(getApplicationContext());

        //Using GetData interface's method, RetrofitClient will call the API to get the data
        GetData service = RetrofitClient.getRetrofitInstance().create(GetData.class);
        Call<String> call = service.getAllData();
        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {


                //Here we are getting response in form of String, we need to convert it to JSON for us to display it
                //So String is converted to JSONObject
                JSONObject jsnobject = null;
                try {
                    jsnobject = new JSONObject(response.body().substring(1));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONArray jsonArray = null;
                //From JSONObject we will create JSONArray object which has an array called "data"
                try {
                    jsonArray = jsnobject.getJSONArray("data");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //From that object we will retrieve the value of a pair whose key is "text" and send it to our adapter
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        mCardAdapter.add(explrObject.getString("text"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //After retrieving every value of "text" and sending to adapter class, we will set that adapter to our cardstack,
                //which will display cards
                mCardStack.setAdapter(mCardAdapter);
            }

            //If there is a failure in receiving the response this method will be called
            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Log.e("response", throwable.getMessage());
                Toast.makeText(MainActivity.this, "Unable to load items", Toast.LENGTH_SHORT).show();
            }
        });

        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCardStack.undo();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCardStack.reset(true);

            }
        });
    }
}

