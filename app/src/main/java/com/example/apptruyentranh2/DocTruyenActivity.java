package com.example.apptruyentranh2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.apptruyentranh2.Adapter.TruyenTranhAdapter;
import com.example.apptruyentranh2.api.ApiLayAnh;
import com.example.apptruyentranh2.api.ApiLayTruyen;
import com.example.apptruyentranh2.interfaceobject.LayAnhVe;
import com.example.apptruyentranh2.object.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DocTruyenActivity extends AppCompatActivity implements LayAnhVe {

    ImageView imgAnh;
    int soTrang;
    int soTrangDangDoc;
    TextView txvSoTrang;
    ArrayList<String> arrAnh;
    String idChap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen);
        init();
        anhXa();
        setUp();
        setClik();
        new ApiLayAnh(this, idChap).execute();

    }

    public void left(View view) {
        docTheoTrang(-1);
    }

    public void right(View view) {
        docTheoTrang(1);
    }
    public void init(){
          Bundle b = getIntent().getBundleExtra("data");
          idChap = b.getString("idChap");
//        arrAnh = new ArrayList<>();
//        arrAnh.add("https://3.bp.blogspot.com/-L3TgvlW50Ts/UhnZKuO3JjI/AAAAAAAAEwA/rofqML34GVw/s1600/be442_002.jpg?imgmax=0");
//        arrAnh.add("https://3.bp.blogspot.com/-wiiW0Yn7X2A/UhnZKiHjS_I/AAAAAAAAEv8/dJ9JvRIsALI/s1600/be442_003.jpg?imgmax=0");
//        arrAnh.add("https://4.bp.blogspot.com/-ECukD1Ag0dA/UhnZPoiDnlI/AAAAAAAAEwI/SSSmO9Plx9U/s1600/be442_004.jpg?imgmax=0");
//        arrAnh.add("https://2.bp.blogspot.com/-IH7wAZTsEF4/UhnZSfqnNWI/AAAAAAAAEwQ/dwnXiXI0OjY/s1600/be442_005.jpg?imgmax=0");
//        arrAnh.add("https://1.bp.blogspot.com/-DIPnsRD6vLY/UhnZUN8s57I/AAAAAAAAEwY/nIeIUFOY-JU/s1600/be442_007.jpg?imgmax=0");
//
//        soTrangDangDoc = 1;
//        soTrang = arrAnh.size();
    }
    public void anhXa(){
        imgAnh = findViewById(R.id.imgAnh);
        txvSoTrang = findViewById(R.id.txvSoTrang);
    }
    public void setUp(){
        //docTheoTrang(0);
    }
    public void setClik(){

    }

    private void docTheoTrang(int i){
        soTrangDangDoc = soTrangDangDoc + i;
        if (soTrangDangDoc == 0){
            soTrangDangDoc = 1;
            Toast.makeText(this, "Trang đầu", Toast.LENGTH_SHORT).show();
        }
        if (soTrangDangDoc > soTrang){
            soTrangDangDoc = soTrang;
            Toast.makeText(this, "Trang cuối", Toast.LENGTH_SHORT).show();
        }
        txvSoTrang.setText(soTrangDangDoc + " / " + soTrang);
        Glide.with(this).load(arrAnh.get(soTrangDangDoc - 1)).into(imgAnh);
    }

    @Override
    public void batDau() {
        Toast.makeText(this, "Dang lay ve", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        arrAnh = new ArrayList<>();
        try {
            JSONArray arr = new JSONArray(data);
            for(int i = 0; i < arr.length(); i++){
                arrAnh.add(arr.getString(i));
            }
            soTrangDangDoc = 1;
            soTrang = arrAnh.size();
            docTheoTrang(0);
        }catch (JSONException ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void biLoi() {
        Toast.makeText(this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
    }
}