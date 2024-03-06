package uz.coder.d2lesson74volley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import uz.coder.d2lesson74volley.adapter.UserAdapter;
import uz.coder.d2lesson74volley.databinding.ActivityMainBinding;
import uz.coder.d2lesson74volley.model.UsersData;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private RequestQueue requestQueue;
    private UserAdapter userAdapter;
    private List<UsersData> usersDataList;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadData();
        requestQueue = Volley.newRequestQueue(this);
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonArrayRequest arrayRequest = new JsonArrayRequest("https://jsonplaceholder.typicode.com/users", new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null) {
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<UsersData>>() {
                            }.getType();
                            usersDataList = gson.fromJson(response.toString(),type);
                            Log.d(TAG, "onResponse: " + usersDataList);
                            userAdapter = new UserAdapter(usersDataList);
                            binding.rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            binding.rv.setAdapter(userAdapter);

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: " + error.getMessage());
                        Toast.makeText(MainActivity.this, "malumot kelmadi", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(arrayRequest);
            }

        });
        Log.d(TAG, "onCreate: "+ usersDataList);






    }

    private void loadData() {
        usersDataList = new ArrayList<>();

    }
}