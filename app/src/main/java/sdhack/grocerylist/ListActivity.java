package sdhack.grocerylist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ListActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_creator);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        GroceryList list = new GroceryList();
        list.add(new GroceryItem("apple", 4.02, 2));
        list.add(new GroceryItem("banana", 3.99, 4));
        list.add(new GroceryItem("cookies", 2.99, 44));
        ListAdapter adapter = new ListAdapter(list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Camera.class);
                startActivity(intent);
            }});

    }
}
