package com.example.test1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Item> list;
    private ItemDAO itemDAO;

    public ItemAdapter(Context context, ArrayList<Item> list) {
        this.context = context;
        this.list = list;
        itemDAO = new ItemDAO(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Integer index = holder.getAdapterPosition();
        Item item = list.get(position);
        holder.tvTen.setText(item.getName());
        holder.tvEmail.setText(item.getEmail());
        holder.tvSdt.setText(item.getSdt());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog(item);
            }
        });


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog(item, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTen, tvEmail, tvSdt;
        Button btnEdit, btnDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvSdt = itemView.findViewById(R.id.tvSdt);
            btnEdit = itemView.findViewById(R.id.btnEditDig);
            btnDelete = itemView.findViewById(R.id.btnDeletelDig);
        }
    }

    private void showEditDialog(Item item, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        builder.setView(view);
        Dialog dialog = builder.create();

        EditText edtTen, edtEmail, edtSdt;
        Button btnSubmit, btnCancel;

        edtTen = view.findViewById(R.id.edtTen);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtSdt = view.findViewById(R.id.edtSdt);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnCancel = view.findViewById(R.id.btnCancel);

        edtTen.setText(item.getName());
        edtEmail.setText(item.getEmail());
        edtSdt.setText(item.getSdt());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edtTen.getText().toString();
                String email = edtEmail.getText().toString();
                String sdt = edtSdt.getText().toString();
                Item updatedItem = new Item(item.getId(), ten, email, sdt);
                if (itemDAO.update(updatedItem)) {
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(itemDAO.getAll());
                    notifyItemChanged(position);
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showDeleteConfirmationDialog(Item item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc chắn muốn xóa mục này?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (itemDAO.delete(item.getId())) {
                    list.remove(item);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


}
