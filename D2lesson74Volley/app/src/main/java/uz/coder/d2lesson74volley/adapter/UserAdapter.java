package uz.coder.d2lesson74volley.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uz.coder.d2lesson74volley.databinding.ItemUserBinding;
import uz.coder.d2lesson74volley.model.UsersData;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.VH> {
    private List<UsersData> usersDataList;

    public UserAdapter(List<UsersData> usersDataList) {
        this.usersDataList = usersDataList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.binding.name.setText(usersDataList.get(position).getName());
        holder.binding.adrse.setText(usersDataList.get(position).getAddress().getStreet());
        holder.binding.email.setText(usersDataList.get(position).getEmail());
        holder.binding.phone.setText(usersDataList.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return usersDataList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        ItemUserBinding binding;
        public VH(@NonNull ItemUserBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
