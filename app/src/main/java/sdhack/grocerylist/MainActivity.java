package sdhack.grocerylist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.my_recycler_view);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
            }});

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        ListInfo[] lists = buildLists();
        mAdapter = new MainAdapter(lists);
        mRecyclerView.setAdapter(mAdapter);
    }
    public ListInfo[] buildLists() {
        ListInfo[] lists = null;
        try {
            JSONArray array = new JSONArray(open(new FileInputStream(new File(getFilesDir(), "GroceryLists.json"))));
            lists = new ListInfo[array.length()];
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            lists=new ListInfo[0];
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return lists;
    }

    protected String open(InputStream inputStream) {
        String json_trial = null;
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) sb.append(line).append("\n");
            json_trial = sb.toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return json_trial;
    }
}
