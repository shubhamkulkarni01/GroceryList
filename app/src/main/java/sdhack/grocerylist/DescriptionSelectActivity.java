package sdhack.grocerylist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class DescriptionSelectActivity extends AppCompatActivity implements Communicator {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView recyclerView = new RecyclerView(this);
        setContentView(recyclerView);
        recyclerView.setAdapter(new BasicAdapter((ArrayList<String>)getIntent().getSerializableExtra("array")));


    }

    @Override
    public void sendResult(Object o, int result) {

    }
}
