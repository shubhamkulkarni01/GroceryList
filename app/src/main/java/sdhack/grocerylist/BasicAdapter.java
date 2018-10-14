package sdhack.grocerylist;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BasicAdapter extends RecyclerView.Adapter<BasicAdapter.BasicViewHolder> {

    ArrayList<String> array;
    ResultReceiver communicator;
    DescriptionSelectActivity a;

    @Override
    public int getItemCount() {
        return array.size();
    }

    public BasicAdapter(ArrayList<String> description, ResultReceiver communicator, DescriptionSelectActivity a) {
        super();
        this.a=a;
        this.communicator=communicator;
        array = description;
    }

    @NonNull
    @Override
    public BasicViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BasicViewHolder(new TextView(viewGroup.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull BasicViewHolder basicViewHolder, int i) {
        basicViewHolder.t.setText(array.get(i));
    }
    class BasicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView t;
        public BasicViewHolder(@NonNull View itemView) {
            super(itemView);
            t = (TextView) itemView;
            t.setTextSize(40);
            t.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Bundle b = new Bundle();
            b.putString("", ((TextView)v).getText().toString());
            communicator.send(1, b);
            a.finish();
        }
    }
}
