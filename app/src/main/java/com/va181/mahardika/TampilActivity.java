package com.va181.mahardika;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

public class TampilActivity extends AppCompatActivity {

    private ImageView imgNegara;
    private TextView tvNama, tvTanggal, tvRank, tvPresiden, tvBenua, tvProfile;
    private SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        imgNegara = findViewById(R.id.iv_bendera);
        tvNama = findViewById(R.id.tv_nama);
        tvTanggal = findViewById(R.id.tv_tanggal);
        tvRank = findViewById(R.id.tv_peringkat);
        tvPresiden = findViewById(R.id.tv_presiden);
        tvBenua = findViewById(R.id.tv_benua);
        tvProfile = findViewById(R.id.tv_profile);

        Intent terimaData = getIntent();
        tvNama.setText(terimaData.getStringExtra("NAMA NEGARA"));
        tvTanggal.setText(terimaData.getStringExtra("TANGGAL MERDEKA"));
        tvRank.setText(terimaData.getStringExtra("RANK"));
        tvPresiden.setText(terimaData.getStringExtra("PRESIDEN"));
        tvBenua.setText(terimaData.getStringExtra("BENUA"));
        tvProfile.setText(terimaData.getStringExtra("PROFILE"));
        String imgLocation = terimaData.getStringExtra("BENDERA");
        try {
            File file = new File(imgLocation);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            imgNegara.setImageBitmap(bitmap);
            imgNegara.setContentDescription(imgLocation);
        } catch (FileNotFoundException er){
            er.printStackTrace();
            Toast.makeText(this, "Gagal mengambil gambar", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tampil_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.item_bagikan){
            Intent bagikan = new Intent(Intent.ACTION_SEND);
            bagikan.putExtra(Intent.EXTRA_SUBJECT, tvNama.getText().toString());
            bagikan.setType("text/plain");
            startActivity(Intent.createChooser(bagikan, "Bagikan"));
        }

        return super.onOptionsItemSelected(item);
    }
}
