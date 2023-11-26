package com.example.apptruyentranh2.api;

import android.os.AsyncTask;

import com.example.apptruyentranh2.interfaceobject.LayAnhVe;
import com.example.apptruyentranh2.interfaceobject.LayTruyenVe;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class ApiLayAnh extends AsyncTask<Void,Void,Void> {
    String data;
    String idChap;
    LayAnhVe layAnhVe;

    public ApiLayAnh(LayAnhVe layAnhVe,String idChap) {
        this.layAnhVe = layAnhVe;
        this.idChap = idChap;
        this.layAnhVe.batDau();
    }
//    https://www.myjsons.com/v/cefa1756
//    https://dulieu2002.000webhostapp.com/layAnh.php?idChap=
    /*
        [
    "https://3.bp.blogspot.com/-L3TgvlW50Ts/UhnZKuO3JjI/AAAAAAAAEwA/rofqML34GVw/s1600/be442_002.jpg?imgmax=0",
    "https://3.bp.blogspot.com/-wiiW0Yn7X2A/UhnZKiHjS_I/AAAAAAAAEv8/dJ9JvRIsALI/s1600/be442_003.jpg?imgmax=0",
    "https://4.bp.blogspot.com/-ECukD1Ag0dA/UhnZPoiDnlI/AAAAAAAAEwI/SSSmO9Plx9U/s1600/be442_004.jpg?imgmax=0",
    "https://2.bp.blogspot.com/-IH7wAZTsEF4/UhnZSfqnNWI/AAAAAAAAEwQ/dwnXiXI0OjY/s1600/be442_005.jpg?imgmax=0",
    "https://1.bp.blogspot.com/-DIPnsRD6vLY/UhnZUN8s57I/AAAAAAAAEwY/nIeIUFOY-JU/s1600/be442_007.jpg?imgmax=0",
    "https://1.bp.blogspot.com/-D4ekH-0LxGY/UhnZWRgH1FI/AAAAAAAAEwg/k1UFQe7Da_0/s1600/be442_008.jpg?imgmax=0",
    "https://1.bp.blogspot.com/-5d1P-63lIA0/UhnZZSKHK1I/AAAAAAAAEwo/T8oXvu8rAqQ/s1600/be442_009.jpg?imgmax=0",
    "https://1.bp.blogspot.com/-TYor5CuOvX4/UhnZbjv_UfI/AAAAAAAAEww/6ORMVF6rMPQ/s1600/be442_010.jpg?imgmax=0",
    "https://2.bp.blogspot.com/-91PamNNX_u0/UhnZcCZ3ANI/AAAAAAAAEw4/EdkNBnJkvyA/s1600/be442_011.jpg?imgmax=0",
    "https://4.bp.blogspot.com/-amiQIPuWSU4/UhnZhNa_zSI/AAAAAAAAExA/9q5ye9G1KPo/s1600/be442_012.jpg?imgmax=0",
    "https://2.bp.blogspot.com/-BZG10O-imKo/UhnZh4Txg1I/AAAAAAAAExQ/tf7Gcxig55Y/s1600/be442_013.jpg?imgmax=0",
    "https://4.bp.blogspot.com/-UlAXR8ADjo0/UhnZh4bIGwI/AAAAAAAAExM/x7hSbuSVF4w/s1600/be442_014.jpg?imgmax=0",
    "https://2.bp.blogspot.com/-EZMsXMbSn-k/UhnZl-doHeI/AAAAAAAAExY/mTYYZiUOHrc/s1600/be442_015.jpg?imgmax=0",
    "https://4.bp.blogspot.com/-NqXj623hPaQ/UhnZpBZSIOI/AAAAAAAAExo/CVAS8I9PFpg/s1600/be442_016.jpg?imgmax=0",
    "https://2.bp.blogspot.com/-IM7_HgrrhwE/UhnZo8ZdHTI/AAAAAAAAExk/MpVV3-9hejI/s1600/be442_017.jpg?imgmax=0",
    "https://3.bp.blogspot.com/-QDASXxPkBEA/UhnZrBtm4BI/AAAAAAAAExw/1-DB2wKC0Qg/s1600/be442_018.jpg?imgmax=0",
    "https://2.bp.blogspot.com/-nBV_cO24ZEc/UhnZuMVDj6I/AAAAAAAAEx4/vXbkoYMmnoc/s1600/be442_019.jpg?imgmax=0",
    "https://3.bp.blogspot.com/-cQpm1bXHpSs/UhnZw9nW4bI/AAAAAAAAEyA/4agDHIxQ7nM/s1600/be442_020.jpg?imgmax=0",
    "https://2.bp.blogspot.com/-w5FmDvGp_5U/UhnZxYcft2I/AAAAAAAAEyI/mEqD4p3meHU/s1600/be442_021.jpg?imgmax=0",
    "https://1.bp.blogspot.com/-0aI07wiUuag/UhnZ1PwJbMI/AAAAAAAAEyQ/s7Cqqas-TQ8/s1600/be442_022.jpg?imgmax=0",
    "https://1.bp.blogspot.com/-67GNRxAdH2Q/UhnZ2Pn74nI/AAAAAAAAEyY/qz2BfYGRP7s/s1600/be442_023.jpg?imgmax=0",
    "https://2.bp.blogspot.com/-VZhKh6w-iXM/UhnZ5H9FRZI/AAAAAAAAEyg/GjWd2eFLdMU/s1600/be442_024.jpg?imgmax=0",
    "https://1.bp.blogspot.com/-leNqboxMex4/UhnZ9GCCwGI/AAAAAAAAEyw/48pFZ7irF7Y/s1600/be442_025.jpg?imgmax=0",
    "https://3.bp.blogspot.com/-3F1pbCwK_gk/UhnZ81B9jbI/AAAAAAAAEys/haKVkVPXUuw/s1600/be442_026.jpg?imgmax=0",
    "https://3.bp.blogspot.com/-2qG0LdqURac/UhnZ-6E1AGI/AAAAAAAAEy4/1PqgP8jXfkQ/s1600/be442_027.jpg?imgmax=0",
    "https://2.bp.blogspot.com/-ehAz5pHwzkU/UhnaGB_ClaI/AAAAAAAAEzI/vE8HLq_f-HE/s1600/be442_028.jpg?imgmax=0",
    "https://1.bp.blogspot.com/-B_0oe2tGMJw/UhnaCRWQeYI/AAAAAAAAEzA/9Gr1mXJCFmE/s1600/be442_029.jpg?imgmax=0",
    "https://4.bp.blogspot.com/-K27xhFlOIi0/UhnaKU0XlnI/AAAAAAAAEzQ/99DcMArlDl0/s1600/be442_030.jpg?imgmax=0",
    "https://3.bp.blogspot.com/-1TgtGun_39M/UhnaPEPqO5I/AAAAAAAAEzw/FK0zNZC9XHQ/s1600/be442_031.jpg?imgmax=0"
]
     */
     /*
              <?php
    $host='localhost';
    $username='id21380293_dulieu2002';
    $pass='Dulieu@02102002';


    $idChap=$_GET["idChap"];

    $conn=mysqli_connect("$host","$username","$pass","$username");
    if (mysqli_connect_errno()){
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    mysqli_set_charset($conn,'utf8');

    $sql="SELECT * FROM `Anh` WHERE `idChap` = '$idChap'";
    $query=mysqli_query($conn,$sql);

    $arr=array();

    while($row=mysqli_fetch_assoc($query)){
        array_push($arr, $row['anh']);

    }

    $json=json_encode($arr);
    echo $json;

?>

     */
    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://dulieu2002.000webhostapp.com/layAnh.php?idChap="+idChap)
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
            this.layAnhVe.biLoi();
        }
        else {
            this.layAnhVe.ketThuc(data);
        }
    }
}
