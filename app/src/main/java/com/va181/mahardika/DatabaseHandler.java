package com.va181.mahardika;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.va181.mahardika.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 2;
    private final static String DATABASE_NAME = "db_negara";
    private final static String TABLE_NEGARA  = "tb_negara";
    private final static String KEY_ID_NEGARA = "ID_negara";
    private final static String KEY_NAMA = "nama_negara";
    private final static String KEY_TGL = "Tanggal";
    private final static String KEY_BENDERA = "bendera";
    private final static String KEY_PERINGKAT = "rank";
    private final static String KEY_PRESIDEN = "presiden_negara";
    private final static String KEY_BENUA = "benua_negara";
    private final static String KEY_PROFILE = "profile";
    private SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private Context context;

    public DatabaseHandler(Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_NEGARA = "CREATE TABLE " + TABLE_NEGARA
                + "(" + KEY_ID_NEGARA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAMA + " TEXT, " + KEY_TGL + " DATE, "
                + KEY_BENDERA + " TEXT, " + KEY_PERINGKAT + " TEXT, "
                + KEY_PRESIDEN + " TEXT, " + KEY_BENUA + " TEXT, "
                + KEY_PROFILE + " TEXT);";

        db.execSQL(CREATE_TABLE_NEGARA);
        inisialisasiNegaraAwalAwal(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NEGARA;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void tambahNegara(Negara dataNegara){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_NAMA, dataNegara.getNama());
        cv.put(KEY_TGL, sdFormat.format(dataNegara.getTanggal()));
        cv.put(KEY_BENDERA, dataNegara.getGambar());
        cv.put(KEY_PERINGKAT, dataNegara.getRank());
        cv.put(KEY_PRESIDEN, dataNegara.getPresiden());
        cv.put(KEY_BENUA, dataNegara.getBenua());
        cv.put(KEY_PROFILE, dataNegara.getProfile());

        db.insert(TABLE_NEGARA, null, cv);
        db.close();
    }

    public void tambahNegara(Negara dataNegara, SQLiteDatabase db){
        ContentValues cv = new ContentValues();


        cv.put(KEY_NAMA, dataNegara.getNama());
        cv.put(KEY_TGL, sdFormat.format(dataNegara.getTanggal()));
        cv.put(KEY_BENDERA, dataNegara.getGambar());
        cv.put(KEY_PERINGKAT, dataNegara.getRank());
        cv.put(KEY_PRESIDEN, dataNegara.getPresiden());
        cv.put(KEY_BENUA, dataNegara.getBenua());
        cv.put(KEY_PROFILE, dataNegara.getProfile());

        db.insert(TABLE_NEGARA, null, cv);
    }

    public void editNegara(Negara dataNegara){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_NAMA, dataNegara.getNama());
        cv.put(KEY_TGL, sdFormat.format(dataNegara.getTanggal()));
        cv.put(KEY_BENDERA, dataNegara.getGambar());
        cv.put(KEY_PERINGKAT, dataNegara.getRank());
        cv.put(KEY_PRESIDEN, dataNegara.getPresiden());
        cv.put(KEY_BENUA, dataNegara.getBenua());
        cv.put(KEY_PROFILE, dataNegara.getProfile());



        db.update(TABLE_NEGARA, cv, KEY_ID_NEGARA + "=?", new String[]{String.valueOf(dataNegara.getIdNegara())});
        db.close();
    }

    public void hapusNegara(int idNegara){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NEGARA, KEY_ID_NEGARA + "=?", new String[]{String.valueOf(idNegara)});
        db.close();
    }

    public ArrayList<Negara> getAllNegara(){
        ArrayList<Negara> dataNegara = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NEGARA;
        SQLiteDatabase db = getReadableDatabase();
        Cursor csr = db.rawQuery(query, null);
        if(csr.moveToFirst()){
            do{
                Date tempDate = new Date();
                try {
                    tempDate = sdFormat.parse(csr.getString(2));
                } catch (ParseException er){
                    er.printStackTrace();
                }

                Negara tempNegara = new Negara(
                        csr.getInt(0),
                        csr.getString(1),
                        tempDate,
                        csr.getString(3),
                        csr.getString(4),
                        csr.getString(5),
                        csr.getString(6),
                        csr.getString(7)
                );

                dataNegara.add(tempNegara);
            } while (csr.moveToNext());
        }
        return dataNegara;
    }

    private String storeImageFile(int id){
        String location;
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), id);
        location = InputActivity.saveImageToInternalStorage(image, context);
        return location;
    }

    private void inisialisasiNegaraAwalAwal(SQLiteDatabase db){
        int idNegara = 0;
        Date tempDate = new Date();
        Date tempDate1 = new Date();

        try {
            tempDate = sdFormat.parse("01/01/1800");
        } catch (ParseException er){
            er.printStackTrace();
        }

        Negara Negara1 = new Negara(
                idNegara,
                "UNITED STATES AMERIKA",
                tempDate,
                storeImageFile(R.drawable.usa),
                "1 dari 138 Negara",
                "Donald Trump",
                "AMERIKA",
                "Tidak perlu diragukan lagi Amerika Serikat memiliki kekuatan militer nomer satu di dunia.  Negara Paman Sam ini memiliki peran besar dalam masalah internasional. Selain itu, militer AS dilengkapi dengan persenjataan modern dan canggih.\n"+
                        "Militer negara ini diperkirakan memiliki jumlah pasukan sebanyak 2.083.100. AS memiliki 13.362 unit pesawat militer dengan 1.962 pesawat tempur. Militer AS juga memiliki power index rating sebesar 0.0615, dengan ukuran Power Index Rating sempurna 0.0000. Artinya sudah tidak diragukan AS memiliki militer terkuat didunia."
        );

        tambahNegara(Negara1, db);
        idNegara++;

        try {
            tempDate = sdFormat.parse("01/01/1800");
        } catch (ParseException er){
            er.printStackTrace();
        }

        Negara Negara2 = new Negara(
                idNegara,
                "RUSSIA",
                tempDate,
                storeImageFile(R.drawable.rus),
                "2 dari 138",
                "Vladimir Vladimirovich Putin",
                "EROPA",
                "Urutan kedua militer terkuat diisi oleh Rusia. Rusia merupakan anggota tetap Dewan Keamanan PBB dengan kekuatan militer yang disegani. Rusia memiliki prajurit militer terbanyak di dunia, jumlah yang dimiliki sebanyak 3.586.128 personel. Militer negara ini di dukung oleh 3.914 pesawat militer, 21.932 unit tank tempur, dan 352 aset angkatan laut."

        );

        tambahNegara(Negara2, db);
        idNegara++;

        try {
            tempDate = sdFormat.parse("01/01/1800");
        } catch (ParseException er){
            er.printStackTrace();
        }

        Negara Negara3 = new Negara(
                idNegara,
                "CHINA",
                tempDate,
                storeImageFile(R.drawable.chn),
                "3 dari 138",
                "Xi Jinping",
                "ASIA",
                "China diperkirakan memiliki jumlah prajurit militer sebanyak 2.693.000. Selain itu China memiliki armada pesawat sebanyak 3.187, dengan 1.222 pesawat tempur. Militer China juga dilengkapi tank tempur sebanyak 13.052, terbanyak kedua setelah Rusia, dan 741 aset angkatan laut."
        );

        tambahNegara(Negara3, db);
        idNegara++;

        try {
            tempDate = sdFormat.parse("01/01/1800");
        } catch (ParseException er){
            er.printStackTrace();
        }

        Negara Negara4 = new Negara(
                idNegara,
                "INDIA",
                tempDate,
                storeImageFile(R.drawable.ind),
                "4 dari 138",
                "Ram Nath Kovind",
                "ASIA",
                "Militer India menempati urutan keempat menurut Global Fire Power. India memiliki penduduk terbesar ketiga di dunia dengan populasi 1.281.935,911. Personel militer di India mencapai 3.462.500. Militer India memiliki 248 tranportasi militer udara diantaranya 692 helikopter, tank tempur sebanyak 4.184 dan aset angkatan laut sebanyak 292."
        );

        tambahNegara(Negara4, db);
        idNegara++;

        try {
            tempDate = sdFormat.parse("01/01/1800");
        } catch (ParseException er){
            er.printStackTrace();
        }

        Negara Negara5 = new Negara(
                idNegara,
                "JEPANG",
                tempDate,
                storeImageFile(R.drawable.jpn),
                "5 dari 138",
                "Kaisar Reiwa (Naruhito)",
                "ASIA",
                "Jepang merupakan salah satu negara dengan bidang teknologi terbaik. Tidak salah jika militer Jepang memiliki kekuatan yang tidak bisa dianggap remeh. Jepang memiliki total kekuatan pesawat 1.572, menduduki urutan keenam dari 137 negara. Miiter Jepang juga memiliki tank tempur sebanyak 1.004, menduduki urutan ke-25 dari 137 negara."
        );

        tambahNegara(Negara5, db);
        idNegara++;

        try {
            tempDate = sdFormat.parse("01/01/1800");
        } catch (ParseException er){
            er.printStackTrace();
        }

        Negara Negara6 = new Negara(
                idNegara,
                "KOREA SELATAN",
                tempDate,
                storeImageFile(R.drawable.kra),
                "6 dari 138",
                "Moon Jae-in",
                "ASIA",
                "Korea Selatan secara teknis masih berada dalam keadaan perang dengan Korea Utara. Negara yang mewajibkan warganya untuk wajib militer ini memiliki personel militer sebanyak 5.827.250 orang. Militer Korea Selatan memiliki total kekuatan pesawat 1.614 dengan menduduki urutan ke-5 dari 137 negara. Militer negara gingseng ini memiliki 2.654 tank tempur dan 166 aset angkatan laut. "
        );

        tambahNegara(Negara6, db);
        idNegara++;

        try {
            tempDate = sdFormat.parse("01/01/1800");
        } catch (ParseException er){
            er.printStackTrace();
        }

        Negara Negara7 = new Negara(
                idNegara,
                "PRANCIS",
                tempDate,
                storeImageFile(R.drawable.fra),
                "7 dari 138",
                "Emmanuel Macron",
                "EROPA",
                "Prancis masih menduduki urutan kelima peringkat militer terkuat di dunia menurut indeks Global Firepower. Miiter Perancis memiliki 3888.635 personel militer dan termasuk dalam anggota North Atlantic Treaty Orrganization (NATO). Total aset angkatan laut Prancis sebanyak 118 dan 273 pesawat tempur."
        );

        tambahNegara(Negara7, db);
    }

}
