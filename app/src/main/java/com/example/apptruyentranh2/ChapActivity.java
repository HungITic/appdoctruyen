package com.example.apptruyentranh2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.apptruyentranh2.Adapter.ChapTruyenAdapter;
import com.example.apptruyentranh2.api.ApiChapTruyen;
import com.example.apptruyentranh2.interfaceobject.LayChapVe;
import com.example.apptruyentranh2.object.ChapTruyen;
import com.example.apptruyentranh2.object.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ChapActivity extends AppCompatActivity implements LayChapVe {

    TextView txvTenTruyens;
    ImageView imgAnhTruyens;
    TruyenTranh truyenTranh;
    ListView lsvDanhSachChap;
    ArrayList<ChapTruyen> arrChap;
    ChapTruyenAdapter chapTruyenAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);
        init();
        anhXa();
        setUp();
        setClik();
        new ApiChapTruyen(this, truyenTranh.getId()).execute();
    }

    private void init(){
        Bundle b = getIntent().getBundleExtra("data");
        truyenTranh = (TruyenTranh) b.getSerializable("truyen");

        // tao du lieu ao
        arrChap = new ArrayList<>();
        //for (int i = 0; i < 20; i++){
            //arrChap.add(new ChapTruyen("Chapter " + i, "10-14-2023"));
        //}
        //chapTruyenAdapter = new ChapTruyenAdapter(this, 0, arrChap);

    }
    private void anhXa(){
        imgAnhTruyens = findViewById(R.id.imgAnhTruyens);
        txvTenTruyens = findViewById(R.id.txvTenTruyens);
        lsvDanhSachChap = findViewById(R.id.lsvDanhSachChap);
    }
    private void setUp(){
        txvTenTruyens.setText(truyenTranh.getTenTruyen());
        Glide.with(this).load(truyenTranh.getLinkAnh()).into(imgAnhTruyens);

        //lsvDanhSachChap.setAdapter(chapTruyenAdapter);
    }
    private void setClik(){
        lsvDanhSachChap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle b = new Bundle();
                b.putString("idChap", arrChap.get(position).getId());
                Intent intent = new Intent(ChapActivity.this, DocTruyenActivity.class);
                intent.putExtra("data", b);
                startActivity(intent);
            }
        });
    }

    @Override
    public void batDau() {
        Toast.makeText(this, "Lấy chap về", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        try {
            JSONArray array = new JSONArray(data);
            for(int i = 0; i < array.length(); i++){
                ChapTruyen chapTruyen = new ChapTruyen(array.getJSONObject(i));
                arrChap.add(chapTruyen);
            }
            chapTruyenAdapter = new ChapTruyenAdapter(this, 0, arrChap);
            lsvDanhSachChap.setAdapter(chapTruyenAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void biLoi() {
        Toast.makeText(this, "Loi ket noi", Toast.LENGTH_SHORT).show();
    }
}