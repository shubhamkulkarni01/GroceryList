package sdhack.grocerylist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class BasicAdapter extends RecyclerView.Adapter<BasicViewHolder> {
    @Override
    public int getItemCount() {
        return 0;
    }

    public BasicAdapter() {
        super();
    }

    @NonNull
    @Override
    public BasicViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BasicViewHolder basicViewHolder, int i) {

    }
}
class BasicViewHolder extends RecyclerView.ViewHolder{
    public BasicViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}