package com.example.apptruyentranh2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptruyentranh2.Adapter.TruyenTranhYeuThichAdapter;
import com.example.apptruyentranh2.api.APiLayTruyenYeuThich;
//import com.example.apptruyentranh2.api.ApiLayTruyen;
import com.example.apptruyentranh2.api.APiXoaTruyenYeuThich;
import com.example.apptruyentranh2.interfaceobject.ItemTouchHelperListeners;
import com.example.apptruyentranh2.interfaceobject.LayTruyenYeuThichVe;
import com.example.apptruyentranh2.object.TruyenTranh;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DanhSachYeuThichActivity extends AppCompatActivity  implements LayTruyenYeuThichVe, ItemTouchHelperListeners {
    RecyclerView rcvDSTruyen;
    TruyenTranhYeuThichAdapter adapter;
    FloatingActionButton btnAdd;
    ArrayList<TruyenTranh> truyenTranhArrayList;
    EditText edtTimKiem;
    RelativeLayout truyenTranhYeuThichActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_yeu_thich);

        rcvDSTruyen = findViewById(R.id.rcvDanhSachYeuThich);
        truyenTranhYeuThichActivity = findViewById(R.id.TruyenYeuThichActivity);
        edtTimKiem = findViewById(R.id.edtTimKiemYeuThich);
        rcvDSTruyen.setHasFixedSize(true);
        rcvDSTruyen.setLayoutManager(new LinearLayoutManager(this));

        truyenTranhArrayList = new ArrayList<>();
        adapter = new TruyenTranhYeuThichAdapter(truyenTranhArrayList, DanhSachYeuThichActivity.this, new TruyenTranhYeuThichAdapter.ClickListeners() {
            @Override
            public void onItemClick(int position, View v) {
                TruyenTranh truyenTranh = truyenTranhArrayList.get(position);
                Bundle b = new Bundle();
                b.putSerializable("truyen", truyenTranh);
                Intent intent = new Intent(DanhSachYeuThichActivity.this, ChapActivity.class);
                intent.putExtra("data", b);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(int position, View v) {
                String tenTruyenTranhDelete = truyenTranhArrayList.get(position).getTenTruyen();
                final TruyenTranh truyenTranhDelete = truyenTranhArrayList.get(position);
                final int idDelete = position;

                adapter.removeTruyenTranhYeuThich(idDelete);
                Snackbar snackbar = Snackbar.make(truyenTranhYeuThichActivity, tenTruyenTranhDelete + " remove", Snackbar.LENGTH_LONG);
                snackbar.setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.undoTruyenTranhYeuThich(truyenTranhDelete, idDelete);
                    }
                });
                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
            }
        });

        rcvDSTruyen.setAdapter(adapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvDSTruyen.addItemDecoration(itemDecoration);
        ItemTouchHelper.SimpleCallback simpleCallback = new RecycleViewItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rcvDSTruyen);
        setClik();
        new APiLayTruyenYeuThich(this).execute();
    }

    public void setClik(){
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editable = edtTimKiem.getText().toString();
                adapter.sortTruyenYeuThich(editable);

            }
        });


    }

    @Override
    public void batDau() {
        Toast.makeText(this, "Dang lay ve", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        try {
            truyenTranhArrayList.clear();
            JSONArray arr = new JSONArray(data);
            for(int i = 0; i < arr.length(); i++){
                JSONObject o = arr.getJSONObject(i);
                truyenTranhArrayList.add(new TruyenTranh(o));
            }
            adapter = new TruyenTranhYeuThichAdapter(truyenTranhArrayList, DanhSachYeuThichActivity.this, new TruyenTranhYeuThichAdapter.ClickListeners() {
                @Override
                public void onItemClick(int position, View v) {
                    TruyenTranh truyenTranh = truyenTranhArrayList.get(position);
                    Bundle b = new Bundle();
                    b.putSerializable("truyen", truyenTranh);
                    Intent intent = new Intent(DanhSachYeuThichActivity.this, ChapActivity.class);
                    intent.putExtra("data", b);
                    startActivity(intent);
                }

                @Override
                public void onItemLongClick(int position, View v) {
                    final int[] a = {1};
                    a[0] = 1;
                    String tenTruyenTranhDelete = truyenTranhArrayList.get(position).getTenTruyen();
                    final TruyenTranh truyenTranhDelete = truyenTranhArrayList.get(position);
                    final int idDelete = position;

                    adapter.removeTruyenTranhYeuThich(idDelete);
                    Snackbar snackbar = Snackbar.make(truyenTranhYeuThichActivity, tenTruyenTranhDelete + " remove", Snackbar.LENGTH_LONG);
                    snackbar.setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            adapter.undoTruyenTranhYeuThich(truyenTranhDelete, idDelete);
                            a[0] = 0;
                        }
                    });
                    snackbar.setActionTextColor(Color.YELLOW);
                    snackbar.show();
                    if(a[0] == 0){

                    }else {
                        new APiXoaTruyenYeuThich(truyenTranhDelete.getId());
                    }
                }
            });
            rcvDSTruyen.setAdapter(adapter);
        }catch (JSONException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void biLoi() {
        Toast.makeText(this, "Loi ket noi", Toast.LENGTH_SHORT).show();
    }

    public void updateYeuThich(View view) {
        new APiLayTruyenYeuThich(this).execute();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder) {
          if(viewHolder instanceof TruyenTranhYeuThichAdapter.ViewHolder){
              String tenTruyenTranhDelete = truyenTranhArrayList.get(viewHolder.getAdapterPosition()).getTenTruyen();
              final TruyenTranh truyenTranhDelete = truyenTranhArrayList.get(viewHolder.getAdapterPosition());
              final int idDelete = viewHolder.getAdapterPosition();

              adapter.removeTruyenTranhYeuThich(idDelete);
              Snackbar snackbar = Snackbar.make(truyenTranhYeuThichActivity, tenTruyenTranhDelete + " remove", Snackbar.LENGTH_LONG);
              snackbar.setAction("Undo", new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      adapter.undoTruyenTranhYeuThich(truyenTranhDelete, idDelete);
                  }
              });
              snackbar.setActionTextColor(Color.YELLOW);
              snackbar.show();

          }
    }
}
