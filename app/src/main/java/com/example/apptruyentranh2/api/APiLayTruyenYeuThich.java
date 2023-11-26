package com.example.apptruyentranh2.api;

import android.os.AsyncTask;

import com.example.apptruyentranh2.interfaceobject.LayTruyenYeuThichVe;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class APiLayTruyenYeuThich extends AsyncTask<Void,Void,Void> {
    String data;
    LayTruyenYeuThichVe layTruyenYeuThichVe;

//    https://dulieu2002.000webhostapp.com/layTruyenYeuThich.php
    public APiLayTruyenYeuThich(LayTruyenYeuThichVe layTruyenYeuThichVe) {
        this.layTruyenYeuThichVe = layTruyenYeuThichVe;
        this.layTruyenYeuThichVe.batDau();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://dulieu2002.000webhostapp.com/layTruyenYeuThich.php")
                .build();
        data = null;
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            data = body.string();
        }catch (IOException ex){
            data = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        if(data == null){
            this.layTruyenYeuThichVe.biLoi();
        }
        else {
            this.layTruyenYeuThichVe.ketThuc(data);
        }
    }
}
