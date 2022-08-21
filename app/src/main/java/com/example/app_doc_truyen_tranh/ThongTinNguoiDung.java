package com.example.app_doc_truyen_tranh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.app_doc_truyen_tranh.adapter.ThongTinNguoiDungAdapter;
import com.example.app_doc_truyen_tranh.object.TaiKhoan;

import java.util.ArrayList;

public class ThongTinNguoiDung extends AppCompatActivity {

    ArrayList<TaiKhoan> taiKhoanArrayList;
    String email;
    String tentaikhoan;
    TextView Text_Name, Text_Gmail;
    Button btnBack;

    private ThongTinNguoiDungAdapter ThongTinNguoiDungAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_nguoi_dung);
        Text_Name = (TextView) findViewById(R.id.Text_Name);
        Text_Gmail = (TextView) findViewById(R.id.Text_Gmail);
        btnBack = (Button) findViewById(R.id.btnBack);
btnBack.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i = new Intent(ThongTinNguoiDung.this,MainActivity.class);
        startActivity(i);
    }
});
        Bundle bundle=getIntent().getExtras();
        String tentk=bundle.getString("tentaikhoan");
        Text_Name.setText(tentk);
        String email=bundle.getString("email");
        Text_Gmail.setText(email);
//        //Thong tin
//        taiKhoanArrayList = new ArrayList<>();
//        taiKhoanArrayList.add(new TaiKhoan(tentaikhoan,email));
//        ThongTinNguoiDungAdapter = new ThongTinNguoiDungAdapter(this,R.layout.activity_thong_tin_nguoi_dung,taiKhoanArrayList);
    }
}