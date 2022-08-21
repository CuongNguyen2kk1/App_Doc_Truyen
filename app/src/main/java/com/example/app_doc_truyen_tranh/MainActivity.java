package com.example.app_doc_truyen_tranh;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
//
//import com.example.app_doc_truyen_tranh.Fragment.DangNhap;
import com.example.app_doc_truyen_tranh.adapter.ThongTinNguoiDungAdapter;
import com.example.app_doc_truyen_tranh.adapter.TruyenTranhAdapter;
import com.example.app_doc_truyen_tranh.api.ApiLayTruyen;
import com.example.app_doc_truyen_tranh.interfaces.LayTruyenVe;
import com.example.app_doc_truyen_tranh.object.TaiKhoan;
import com.example.app_doc_truyen_tranh.object.TruyenTranh;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener,LayTruyenVe{

    GridView  gdvDSTruyen;
    TruyenTranhAdapter adapter;
    ArrayList<TruyenTranh> truyenTranhArrayList;
    EditText edtTimKiem;
    Button btnButton;

    //---------------------------------------
//    private static final int activity_main = 0;

//    private static final int activity_dang_nhap = 1;
    private static final int fragment_dang_nhap = 0;

    private static final int ACTIVITY_DANG_XUAT = 1;
//    private static final int activity_dang_xuat = 2;
    private int mCurrentFragment = fragment_dang_nhap;
    //---------------------------------------

    private DrawerLayout mDrawerLayout;
    private ThongTinNguoiDungAdapter ThongTinNguoiDungAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        anhXa();
        setUp();
        setClick();
        btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMenu();
            }
        });
//--------------------------------------
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout,toolbar,
//                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
//        mDrawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = findViewById(R.id.navigation_view);
//        navigationView.setNavigationItemSelectedListener(this);

//--------------------------------------

        new ApiLayTruyen(this).execute();
    }

    private void init(){
        truyenTranhArrayList = new ArrayList<>();
        adapter = new TruyenTranhAdapter(this,0,truyenTranhArrayList);
    }

    private void anhXa(){
        gdvDSTruyen = findViewById(R.id.gdvDSTruyen);
        btnButton = findViewById(R.id.btnButton);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        edtTimKiem = findViewById(R.id.edtTimKiem);

    }

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
    }

    private void setUp(){
        gdvDSTruyen.setAdapter(adapter);
    }
    private void setClick(){
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = edtTimKiem.getText().toString();
                adapter.sortTruyen(s);
            }
        });
        gdvDSTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TruyenTranh truyenTranh = truyenTranhArrayList.get(i);
                Bundle b = new Bundle();
                b.putSerializable("truyen",truyenTranh);
                Intent intent = new Intent(MainActivity.this,ChapActivity.class);
                intent.putExtra("data",b);
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
            for (int i =0;i<arr.length();i++){
                JSONObject o = arr.getJSONObject(i);
                truyenTranhArrayList.add(new TruyenTranh(o));
            }
            adapter = new TruyenTranhAdapter(this,0,truyenTranhArrayList);
            gdvDSTruyen.setAdapter(adapter);
        }catch (JSONException e){

        }
    }

    @Override
    public void biLoi() {
        Toast.makeText(this, "Loi ket noi", Toast.LENGTH_SHORT).show();

    }
    public void update(View view) {
        new ApiLayTruyen(this).execute();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
    private void ShowMenu(){
        PopupMenu popupMenu = new PopupMenu(this, btnButton);
        popupMenu.getMenuInflater().inflate(R.menu.menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menuNguoiDung:
                        Intent i1 = new Intent(MainActivity.this,ThongTinNguoiDung.class);
                        startActivity(i1);
                        break;
                    case R.id.menuNhaPhatTrien:
                        Intent i2 = new Intent(MainActivity.this,ThongTinActivity.class);
                        startActivity(i2);
                        break;
                    case R.id.menuDangXuat:
                        Intent i3 = new Intent(MainActivity.this,DangNhapActivity.class);
                        startActivity(i3);
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }
}