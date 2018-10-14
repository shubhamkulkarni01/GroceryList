package sdhack.grocerylist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BasicAdapter extends RecyclerView.Adapter<BasicAdapter.BasicViewHolder> {

    ArrayList<String> array;
    Communicator communicator;

    @Override
    public int getItemCount() {
        return array.size();
    }

    public BasicAdapter(ArrayList<String> description, Communicator communicator) {
        super();
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
            t.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            communicator.sendResult(((TextView)v).getText(), 0);
        }
    }
}
