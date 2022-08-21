package com.example.app_doc_truyen_tranh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ThongTinActivity extends AppCompatActivity {
    TextView txtThongTin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);
        txtThongTin = findViewById(R.id.textviewthongtin);

        String thongtin = " Ứng dụng được phát hành bởi Nhóm 9\n"
                +"1,Phạm Văn Cương (Trưởng Nhóm)\n"
                +"2,Nguyễn Đức Anh Cường\n"
                +"3,Phạm Ngọc Đại\n"
                +"4,Hoàng Ngọc Đại\n"
                +"5,Nguyễn Thanh Hải\n";

        txtThongTin.setText(thongtin);
    }
}