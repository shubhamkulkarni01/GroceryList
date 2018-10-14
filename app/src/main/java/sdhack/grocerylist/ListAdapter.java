package sdhack.grocerylist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

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
        viewHolder.checkbox.setChecked(false);
        viewHolder.name.setText(list.get(i).name);
        viewHolder.quantity.setText("Quantity: "+list.get(i).quantity);
        if(list.get(i).price>0.01) {
            viewHolder.price.setText(String.format("$%.2f", +list.get(i).price));
        }
        else{
            viewHolder.price.setText("Cannot get price");
            viewHolder.price.setTextSize(20);
        }
        if(list.get(i).previousPurchase != null) viewHolder.date.setText(list.get(i).previousPurchase.toString());
    }


    class ListViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkbox;
        TextView name, price, date, quantity;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.checkbox);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            date = itemView.findViewById(R.id.date);
            quantity = itemView.findViewById(R.id.quantity);
        }
    }
}

