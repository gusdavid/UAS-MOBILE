package com.va181.mahardika;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NegaraAdapter extends RecyclerView.Adapter<NegaraAdapter.NegaraViewHolder> {

    private Context context;
    private ArrayList<Negara> dataNegara;
    private SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy");

    public NegaraAdapter(Context context, ArrayList<Negara> dataNegara) {
        this.context = context;
        this.dataNegara = dataNegara;
    }

    @NonNull
    @Override
    public NegaraAdapter.NegaraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_negara, parent, false);
        return new NegaraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NegaraAdapter.NegaraViewHolder holder, int position) {
        Negara tempNegara = dataNegara.get(position);
        holder.idNegara = tempNegara.getIdNegara();
        holder.tvNama.setText(tempNegara.getNama());
        holder.tvProfile.setText(tempNegara.getProfile());
        holder.tanggal = sdFormat.format(tempNegara.getTanggal());
        holder.gambar = tempNegara.getGambar();
        holder.rank = tempNegara.getRank();
        holder.presiden = tempNegara.getPresiden();
        holder.benua = tempNegara.getBenua();

        try {
            File file = new File(holder.gambar);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            holder.imgNegara.setImageBitmap(bitmap);
            holder.imgNegara.setContentDescription(holder.gambar);
        } catch (FileNotFoundException er){
            er.printStackTrace();
            Toast.makeText(context, "Gagal mengambil gambar", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {

        return dataNegara.size();
    }

    public class NegaraViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        private ImageView imgNegara;
        private TextView tvNama, tvProfile;
        private int idNegara;
        private String tanggal, gambar, rank, presiden, benua;

        public NegaraViewHolder(@NonNull View itemView) {
            super(itemView);

            imgNegara = itemView.findViewById(R.id.iv_bendera);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvProfile = itemView.findViewById(R.id.tv_profile);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent bukaNegara = new Intent(context, TampilActivity.class);
            bukaNegara.putExtra("ID", idNegara);
            bukaNegara.putExtra("NAMA NEGARA", tvNama.getText().toString());
            bukaNegara.putExtra("TANGGAL MERDEKA", tanggal);
            bukaNegara.putExtra("BENDERA", gambar);
            bukaNegara.putExtra("BENUA",rank);
            bukaNegara.putExtra("PRESIDEN", presiden);
            bukaNegara.putExtra("BENUA", benua);
            bukaNegara.putExtra("PROFILE", tvProfile.getText().toString());
            context.startActivity(bukaNegara);
        }

        @Override
        public boolean onLongClick(View v) {

            Intent bukaInput = new Intent(context, InputActivity.class);
            bukaInput.putExtra("OPERASI", "update");
            bukaInput.putExtra("ID", idNegara);
            bukaInput.putExtra("NAMA NEGARA", tvNama.getText().toString());
            bukaInput.putExtra("TANGGAL MERDEKA", tanggal);
            bukaInput.putExtra("BENDERA", gambar);
            bukaInput.putExtra("RANK", rank);
            bukaInput.putExtra("PRESIDEN", presiden);
            bukaInput.putExtra("BENUA", benua);
            bukaInput.putExtra("PROFILE", tvProfile.getText().toString());
            context.startActivity(bukaInput);
            return true;
        }
    }
}
