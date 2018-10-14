package sdhack.grocerylist;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class DescriptionSelectActivity extends AppCompatActivity {
    ResultReceiver communicator;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView recyclerView = new RecyclerView(this);
        setContentView(recyclerView);
        communicator = (ResultReceiver) getIntent().getParcelableExtra("reply");
        //Log.d("sdfjlflkdsjfkl", ""+(communicator == null)+" "+communicator.toString());
        recyclerView.setAdapter(new BasicAdapter((ArrayList<String>)getIntent().getSerializableExtra("array"), communicator, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
