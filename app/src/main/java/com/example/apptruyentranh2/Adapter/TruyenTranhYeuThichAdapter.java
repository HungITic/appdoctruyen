package com.example.apptruyentranh2.Adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apptruyentranh2.DanhSachYeuThichActivity;
import com.example.apptruyentranh2.R;
import com.example.apptruyentranh2.object.TruyenTranh;

import java.util.ArrayList;

public class TruyenTranhYeuThichAdapter extends RecyclerView.Adapter<TruyenTranhYeuThichAdapter.ViewHolder>{
    private Context ct;
    private ArrayList<TruyenTranh> arr;
    private ClickListeners clickListeners;

    public TruyenTranhYeuThichAdapter(ArrayList<TruyenTranh> arrayList, DanhSachYeuThichActivity activity, ClickListeners clickListeners){
        this.arr = arrayList;
        this.ct = activity;
        this.clickListeners = clickListeners;
    }
    public void sortTruyenYeuThich(String s) {
        s = s.toUpperCase();
        int k = 0;
        for(int i = 0; i < arr.size(); i++){
            TruyenTranh t = arr.get(i);
            String ten = t.getTenTruyen().toUpperCase();
            if(ten.indexOf(s) >= 0){
                arr.set(i, arr.get(k));
                arr.set(k, t);
                k++;
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_row_danh_sach_yeu_thich, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TruyenTranh truyenTranh = arr.get(position);
        if (truyenTranh == null) {
            return;
        }

        holder.txvTenTruyenYeuThich.setText(truyenTranh.getTenTruyen());
        holder.txvTenChapYeuThich.setText(truyenTranh.getTenChap());
        Glide.with(this.ct).load(truyenTranh.getLinkAnh()).into(holder.imgAnhTruyenYeuThich);


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(ct, truyenTranh.getTenTruyen(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if(arr!=null && arr.size()>0)
            return arr.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        ImageView imgAnhTruyenYeuThich;
        TextView txvTenTruyenYeuThich;
        TextView txvTenChapYeuThich;
        TextView txvTenTruyenDelete;
        public LinearLayout layout_ForeGround;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAnhTruyenYeuThich = itemView.findViewById(R.id.imgAnhTruyenYeuThich);
            txvTenTruyenYeuThich = itemView.findViewById(R.id.txvTenTruyenYeuThich);
            txvTenChapYeuThich = itemView.findViewById(R.id.txvTenChapYeuThich);
            txvTenTruyenDelete = itemView.findViewById(R.id.txvTenTruyenDelete);
            layout_ForeGround = itemView.findViewById(R.id.layout_foreground);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListeners.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListeners.onItemLongClick(getAdapterPosition(),v);
            return true;
        }
    }

    public void undoTruyenTranhYeuThich(TruyenTranh truyenTranh, int id){
        arr.add(id, truyenTranh);
        notifyItemInserted(id);
    }

    public void removeTruyenTranhYeuThich(int id){
        arr.remove(id);
        notifyItemRemoved(id);
    }

    public interface ClickListeners{
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }

}
