package sdhack.grocerylist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainAdapter extends RecyclerView.Adapter <MainAdapter.MyViewHolder> {

    ListInfo[] lists;

    public MainAdapter(ListInfo[] lists) {
        super();
        this.lists = lists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grocery_list_overview, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        viewHolder.name.setText(lists[i].name);
        viewHolder.itemCount.setText("" + lists[i].itemCount);
        if (lists[i].dateModfied != null)
            viewHolder.lastModified.setText(lists[i].dateModfied);
        else
            viewHolder.lastModified.setText("No Date Modified!");
    }

    @Override
    public int getItemCount() {
        return lists.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, itemCount, lastModified;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            itemCount = itemView.findViewById(R.id.itemCount);
            lastModified = itemView.findViewById(R.id.lastModified);
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
