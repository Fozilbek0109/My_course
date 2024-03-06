package uz.coder.d2demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.MyViewHolder> {
    List<DemoModel>demoModelList;

    public DemoAdapter(List<DemoModel> demoModelList) {
        this.demoModelList = demoModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
        MyViewHolder myViewHolder =  new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txtn.setText(demoModelList.get(position).getTxt());
        holder.txtp.setText(demoModelList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return demoModelList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txtn,txtp;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            txtn = itemView.findViewById(R.id.txtn);
            txtp = itemView.findViewById(R.id.txtp);
        }
    }
}
