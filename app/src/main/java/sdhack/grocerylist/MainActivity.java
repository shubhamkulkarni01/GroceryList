package sdhack.grocerylist;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements Serializable, MyResultReceiver.Receiver {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton fab;

    private GroceryList list;

    private ArrayList<String> description = null;
ResultReceiver resultReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = buildLists();
        resultReceiver = new MyResultReceiver(new Handler());
        ((MyResultReceiver) resultReceiver).setReceiver(this);
        Log.i("class", "mainactivity");
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.my_recycler_view);
        fab = findViewById(R.id.fab);

        if (checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    1);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    Log.d("started", "the camera");
                    startActivityForResult(takePictureIntent, 1);
                }
            }});

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ListAdapter(list);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("IMAGE", "we got an image!!");
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Intent intent = new Intent(this, VisionIntentService.class);
            intent.putExtra("IMAGE", imageBitmap);
            intent.putExtra("reply", resultReceiver);
            Log.d("sdkljflkdsjflkds", resultReceiver.toString());
            Log.d("starting service", "starting service!!!");
            startService(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("class", "mainactivity");

    }

    public GroceryList buildLists() {
        GroceryList list = null;
        try {
            JSONArray array = new JSONArray(open(new FileInputStream(new File(getFilesDir(), "GroceryLists.json"))));
            list = new GroceryList();
            for(int i = 0; i<array.length(); i++){
                list.add(new GroceryItem(array.optJSONObject(i).optString("name"), array.optJSONObject(i).optInt("price"), array.optJSONObject(i).optInt("quantity")));
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            list=new GroceryList();
            JSONArray array = new JSONArray();
            try{
                FileOutputStream f = new FileOutputStream(new File(getFilesDir(), "GroceryLists.json"));
                f.write(array.toString().getBytes());
                f.close();
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
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


    @Override
    public void onReceiveResult(int result, Bundle bundle) {
        if(result == 0) {
            description= (ArrayList<String>) bundle.getSerializable("");
            Log.d("it worked", description.toString());
            Intent intent = new Intent(this, DescriptionSelectActivity.class);
            intent.putExtra("array",description);
            intent.putExtra("reply", resultReceiver);
            startActivity(intent);
        }
        if(result == 1){
            Log.d("dsjfkdls", "message from descriptionselect");
            list.add(new GroceryItem(bundle.getString(""), 0.0, 1));
            mAdapter.notifyDataSetChanged();
        }
    }
}
