package com.example.app_doc_truyen_tranh.api;

import android.os.AsyncTask;

import com.example.app_doc_truyen_tranh.interfaces.LayAnhVe;
import com.example.app_doc_truyen_tranh.interfaces.LayChapVe;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class ApiLayAnh extends AsyncTask<Void,Void,Void> {
    String data;
    String idChap;
    LayAnhVe layAnhVe;
    public ApiLayAnh(LayAnhVe layAnhVe, String idChap) {
        this.layAnhVe = layAnhVe;
        this.layAnhVe.batDau();
        this.idChap = idChap;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://november-web.000webhostapp.com/web_service/apiLayAnh.php?idchap="+ idChap)
                .build();
        data = null;
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            data = body.string();
        }catch (IOException e) {
            data = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (data == null){
            this.layAnhVe.biLoi();
        }else {
            this.layAnhVe.ketThuc(data);
        }
    }
}
