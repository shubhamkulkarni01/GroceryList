package sdhack.grocerylist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    GroceryList list;

    public ListAdapter(GroceryList list) {
        super();
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ListViewHolder holder = new ListViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grocery_list_detail, viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder viewHolder, int i) {

    }
    class ListViewHolder extends RecyclerView.ViewHolder{

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}

