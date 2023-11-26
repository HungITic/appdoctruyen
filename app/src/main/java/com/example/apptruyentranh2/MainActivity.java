package com.example.apptruyentranh2;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;


import com.example.apptruyentranh2.Adapter.TruyenTranhAdapter;
import com.example.apptruyentranh2.api.ApiLayTruyen;
import com.example.apptruyentranh2.interfaceobject.LayTruyenVe;
import com.example.apptruyentranh2.object.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LayTruyenVe {
    GridView gdvDSTruyen;
    TruyenTranhAdapter adapter;
    ArrayList<TruyenTranh> truyenTranhArrayList;
    EditText edtTimKiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        anhXa();
        setUp();
        setClik();
        new ApiLayTruyen(this).execute();

    }

    public void init(){
        truyenTranhArrayList = new ArrayList<>();

        adapter = new TruyenTranhAdapter(this, 0, truyenTranhArrayList);
    }
    public void anhXa(){
        gdvDSTruyen = findViewById(R.id.gdvDSTruyen);
        edtTimKiem = findViewById(R.id.edtTimKiem);
    }
    public void setUp(){
        gdvDSTruyen.setAdapter(adapter);
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
                  adapter.sortTruyen(editable);

            }
        });
        gdvDSTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TruyenTranh truyenTranh = truyenTranhArrayList.get(position);
                Bundle b = new Bundle();
                b.putSerializable("truyen", truyenTranh);
                Intent intent = new Intent(MainActivity.this, ChapActivity.class);
                intent.putExtra("data", b);
                startActivity(intent);
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
              adapter = new TruyenTranhAdapter(this, 0, truyenTranhArrayList);
              gdvDSTruyen.setAdapter(adapter);
          }catch (JSONException ex){
              ex.printStackTrace();
          }
    }

    @Override
    public void biLoi() {
        Toast.makeText(this, "Loi ket noi", Toast.LENGTH_SHORT).show();
    }

    public void update(View view) {
        new ApiLayTruyen(this).execute();
    }
}