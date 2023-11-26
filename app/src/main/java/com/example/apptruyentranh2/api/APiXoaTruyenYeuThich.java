package com.example.apptruyentranh2.api;

import android.os.AsyncTask;

import com.example.apptruyentranh2.Adapter.TruyenTranhYeuThichAdapter;
import com.example.apptruyentranh2.interfaceobject.LayTruyenYeuThichVe;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class APiXoaTruyenYeuThich extends AsyncTask<Void,Void,Void> {
    TruyenTranhYeuThichAdapter.ClickListeners clickListeners;
    String idTruyen;

    public APiXoaTruyenYeuThich(String idTruyen) {
        this.idTruyen = idTruyen;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://dulieu2002.000webhostapp.com/xoaTruyenYeuThich.php?id="+idTruyen)
                .build();
        try {
            client.newCall(request).execute();
        } catch (Exception e){

        }
        return null;
    }
}
