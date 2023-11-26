package com.example.apptruyentranh2.api;

import android.os.AsyncTask;

import com.example.apptruyentranh2.interfaceobject.LayChapVe;
import com.example.apptruyentranh2.interfaceobject.LayTruyenVe;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class ApiChapTruyen extends AsyncTask<Void,Void,Void> {
    String data;
    LayChapVe layChapVe;
    String idTruyen;

    public ApiChapTruyen(LayChapVe layChapVe, String idTruyen) {
        this.layChapVe = layChapVe;
        this.idTruyen = idTruyen;
        this.layChapVe.batDau();
    }
// https://www.myjsons.com/v/c0d01736
//  https://dulieu2002.000webhostapp.com/layChap.php?id=1
    /*
        [
             {
        "tenchap": "Chapter 1",
        "ngaynhap": "15-10-2023"
    },
    {
        "tenchap": "Chapter 2",
        "ngaynhap": "15-10-2023"
    },
    {
        "tenchap": "Chapter 3",
        "ngaynhap": "15-10-2023"
    },
    {
        "tenchap": "Chapter 4",
        "ngaynhap": "15-10-2023"
    },
    {
        "tenchap": "Chapter 5",
        "ngaynhap": "15-10-2023"
    },
    {
        "tenchap": "Chapter 6",
        "ngaynhap": "15-10-2023"
    },
    {
        "tenchap": "Chapter 7",
        "ngaynhap": "15-10-2023"
    },
    {
        "tenchap": "Chapter 8",
        "ngaynhap": "15-10-2023"
    },
    {
        "tenchap": "Chapter 9",
        "ngaynhap": "15-10-2023"
    },
    {
        "tenchap": "Chapter 10",
        "ngaynhap": "15-10-2023"
    },
    {
        "tenchap": "Chapter 11",
        "ngaynhap": "15-10-2023"
    },
    {
        "tenchap": "Chapter 12",
        "ngaynhap": "15-10-2023"
    },
    {
        "tenchap": "Chapter 13",
        "ngaynhap": "15-10-2023"
    },
    {
        "tenchap": "Chapter 14",
        "ngaynhap": "15-10-2023"
    },
    {
        "tenchap": "Chapter 15",
        "ngaynhap": "15-10-2023"
    },
    {
        "tenchap": "Chapter 16",
        "ngaynhap": "15-10-2023"
    }
        ]
     */

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://dulieu2002.000webhostapp.com/layChap.php?id="+idTruyen)
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
            this.layChapVe.biLoi();
        }
        else {
            this.layChapVe.ketThuc(data);
        }
    }
}
