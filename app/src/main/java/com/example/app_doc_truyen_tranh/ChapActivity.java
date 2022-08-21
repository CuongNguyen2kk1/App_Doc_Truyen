package com.example.app_doc_truyen_tranh;

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
import com.example.app_doc_truyen_tranh.adapter.ChapTruyenAdapter;
import com.example.app_doc_truyen_tranh.api.ApiChapTruyen;
import com.example.app_doc_truyen_tranh.interfaces.LayChapVe;
import com.example.app_doc_truyen_tranh.object.ChapTruyen;
import com.example.app_doc_truyen_tranh.object.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ChapActivity extends AppCompatActivity implements LayChapVe {

TextView txtTenTruyens;
    ImageView imgAnhTruyens;
    TruyenTranh truyenTranh;
    ListView gdvDSChap;
    ArrayList<ChapTruyen> arrChap;
    ChapTruyenAdapter chapTruyenAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiChapTruyen(this,truyenTranh.getId()).execute();
    }
    private void init(){
        Bundle b = getIntent().getBundleExtra("data");
        truyenTranh = (TruyenTranh)b.getSerializable("truyen");
        //tao du lieu ao
        arrChap = new ArrayList<>();
//        for (int i=0;i<20;i++){
//            arrChap.add(new ChapTruyen("chapter"+i,"10-06-2022"));
//        }
//        chapTruyenAdapter = new ChapTruyenAdapter(this,0,arrChap);
    }
    private void anhXa(){
        imgAnhTruyens = findViewById(R.id.imgAnhTruyens);
        txtTenTruyens = findViewById(R.id.txvTenTruyens);
        gdvDSChap = findViewById(R.id.lsvDanhSachChap);
    }
    private void setUp(){
        txtTenTruyens.setText(truyenTranh.getTenTruyen());
        Glide.with(this).load(truyenTranh.getLinkAnh()).into(imgAnhTruyens);
        //gdvDSChap.setAdapter(chapTruyenAdapter);
    }
    private void setClick(){
        gdvDSChap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle b = new Bundle();
                b.putString("idChap",arrChap.get(i).getId());
                Intent intent = new Intent(ChapActivity.this,DocTruyenActivity.class);
                intent.putExtra("data",b);
                startActivity(intent);
            }
        });
    }
    @Override
    public void batDau() {
        Toast.makeText(this, "Lay Chap Ve", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void ketThuc(String data) {
        try {
            JSONArray array = new JSONArray(data);
            for (int i=0;i<array.length();i++)
            {
                ChapTruyen chapTruyen = new ChapTruyen(array.getJSONObject(i));
                arrChap.add(chapTruyen);
            }
            chapTruyenAdapter = new ChapTruyenAdapter(this,0,arrChap);
            gdvDSChap.setAdapter(chapTruyenAdapter);
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void biLoi() {
        Toast.makeText(this, "Bi Loi", Toast.LENGTH_SHORT).show();
    }
}