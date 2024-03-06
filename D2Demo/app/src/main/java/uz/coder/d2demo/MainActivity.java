package uz.coder.d2demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<DemoModel> demoModelList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        loadData();
        DemoAdapter demoAdapter = new DemoAdapter(demoModelList);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(demoAdapter);

    }

    private void loadData() {
        demoModelList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            demoModelList.add(new DemoModel("hjadgsjagsh","B6 Chair","85,3$"));
        }
    }
}