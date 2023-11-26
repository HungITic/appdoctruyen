package com.example.apptruyentranh2.api;

import android.os.AsyncTask;

import com.example.apptruyentranh2.interfaceobject.LayTruyenVe;
import com.example.apptruyentranh2.interfaceobject.LayTruyenYeuThichVe;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class ApiLayTruyen extends AsyncTask<Void,Void,Void> {
    String data;
    LayTruyenVe layTruyenVe;
    LayTruyenYeuThichVe layTruyenYeuThichVe;

    public ApiLayTruyen(LayTruyenVe layTruyenVe) {
        this.layTruyenVe = layTruyenVe;
        this.layTruyenVe.batDau();
    }
    public ApiLayTruyen(LayTruyenYeuThichVe layTruyenYeuThichVe) {
        this.layTruyenYeuThichVe = layTruyenYeuThichVe;
        this.layTruyenYeuThichVe.batDau();
    }
//    Dulieu@02102002
//    https://www.myjsons.com/v/74371648
//    https://www.flaticon.com/search?word=update
//    https://databases-auth.000webhost.com/index.php?route=/database/structure&db=id21380293_dulieu2002
    /*
        [
             {
                  "linkAnh": "https://i.truyenvua.com/ebook/190x247/maou-no-ore-ga-dorei-elf-wo-yome-ni-shitanda-ga-dou-medereba-ii_1695736249.jpg?gt=hdfgdfg&mobile=2",
                  "tenChap": "Chương 51",
                  "tenTruyen": "Maou No Ore Ga Dorei Elf Wo Yome Ni Shitanda Ga, Dou Medereba Ii?"
             },
             {
                  "linkAnh": "https://i.truyenvua.com/ebook/190x247/linh-khi-khoi-phuc-ta-moi-ngay-get-mot-ky-nang-moi_1674481289.jpg?gt=hdfgdfg&mobile=2",
                  "tenChap": "Chương 152",
                  "tenTruyen": "Linh Khí Khôi Phục: Ta Mỗi Ngày Thu Được Một Cái Kỹ Năng Mới"
             },
             {
                  "linkAnh": "https://i.truyenvua.com/ebook/190x247/nhat-dang-gia-dinh_1574248612.jpg?gt=hdfgdfg&mobile=2",
                  "tenChap": "Chương 303",
                  "tenTruyen": "Nhất Đẳng Gia Đinh"
             },
             {
                  "linkAnh": "https://i.truyenvua.com/ebook/190x247/tro-thanh-co-vo-khe-uoc-cua-nhan-vat-phan-dien_1644161862.jpg?gt=hdfgdfg&mobile=2",
                  "tenChap": "Chương 92.2",
                  "tenTruyen": "Trở Thành Cô Vợ Khế Ước Của Nhân Vật Phản Diện"
             },
             {
                  "linkAnh": "https://i.truyenvua.com/ebook/190x247/huyen-thoai-game-thu-tai-xuat_1628700084.jpg?gt=hdfgdfg&mobile=2",
                  "tenChap": "Chương 123",
                  "tenTruyen": "Huyền Thoại Game Thủ - Tái Xuất"
             },
             {
                  "linkAnh": "https://i.truyenvua.com/ebook/190x247/toi-cuong-vo-hon-he-thong_1653056929.jpg?gt=hdfgdfg&mobile=2",
                  "tenChap": "Chương 136",
                  "tenTruyen": "Tối Cường Võ Hồn Hệ Thống"
             }
        ]
     */
     /*
              <?php
                      $host='localhost';
                      $username='id21380293_dulieu2002';
                      $pass='Dulieu@02102002';

                      class Truyen{
                              function __construct($id,$ten,$chap,$anh){
                                   $this->id=$id;
                                   $this->tenTruyen=$ten;
                                   $this->tenChap=$chap;
                                   $this->linkAnh=$anh;
                              }
                      }


                      $conn=mysqli_connect("$host","$username","$pass","$username");
                      if (mysqli_connect_errno()){
                               echo "Failed to connect to MySQL: " . mysqli_connect_error();
                      }
                      mysqli_set_charset($conn,'utf8');

                      $sql='SELECT * FROM `Truyen`';
                      $query=mysqli_query($conn,$sql);

                      $arr=array();

                      while($row=mysqli_fetch_assoc($query)){
                             $id=$row['id'];
                             $ten=$row['tenTruyen'];
                             $chap=$row['chapMoi'];
                             $anh=$row['anhTruyen'];

                             array_push($arr, new Truyen($id,$ten,$chap,$anh));

                      }

                      $json=json_encode($arr);
                      echo $json;

             ?>

     */
    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://dulieu2002.000webhostapp.com/layTruyen.php")
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
            this.layTruyenVe.biLoi();
        }
        else {
            this.layTruyenVe.ketThuc(data);
        }
    }
}
