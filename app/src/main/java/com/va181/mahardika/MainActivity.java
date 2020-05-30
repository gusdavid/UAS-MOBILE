package com.va181.mahardika;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Negara> dataNegara = new ArrayList<>();
    private RecyclerView rvNegara;
    private NegaraAdapter NegaraAdapter;
    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvNegara = findViewById(R.id.rv_tampil_negara);
        dbHandler = new DatabaseHandler(this);
        tampilDataNegara();
    }

    private void tampilDataNegara(){
        dataNegara = dbHandler.getAllNegara();
        NegaraAdapter = new NegaraAdapter(this, dataNegara);
        RecyclerView.LayoutManager layManager = new LinearLayoutManager(MainActivity.this);
        rvNegara.setLayoutManager(layManager);
        rvNegara.setAdapter(NegaraAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id== R.id.item_menu_tambah){
            Intent bukaInput = new Intent(getApplicationContext(), InputActivity.class);
            bukaInput.putExtra("OPERASI", "insert");
            startActivity(bukaInput);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tampilDataNegara();
    }
}
