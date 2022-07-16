package com.example.resolvex2.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.example.resolvex2.Historico;
import com.example.resolvex2.R;

public class Conexao extends SQLiteOpenHelper {

//**********************Criando o banco de dados
    private static final String DATABASE_NAME = "calculadora";
    private static final String TABLE_HISTORICO = "historico";

    //**********************Criando uma tabela no banco de dados
    private static final String CREATE_TABLE_HISTORICO = "CREATE TABLE " + TABLE_HISTORICO + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "expressao DOUBLE, " +
            "resultado DOUBLE);";
    //*********** Primary Key Define a chave primária do banco de dados
    //*********** AutoIncrement Auto incrementa a id. Ele vai somando de 1 em 1 cada adição no banco de dados
    private static final String DROP_TABLE_HISTORICO = "DROP TABLE IF EXISTS " + TABLE_HISTORICO;

    public Conexao(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_HISTORICO);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_HISTORICO);
        onCreate(db);
    }//*****Método para criar o histórico
    public long criarHistorico(Historico hist) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("expressao", hist.getExpressao());
        cv.put("resultado", hist.getResultado());
        long id = db.insert(TABLE_HISTORICO , null, cv);
        db.close();
        return id;
    }//Método para pegar o histórico
    public void getAllHistorico(Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "expressao", "resultado",};
        Cursor data = db.query(TABLE_HISTORICO, columns, null, null,
                null, null, "expressao");
        int[] to = {R.id.textViewIdListarHistorico, R.id.textViewExpressaoListarHistorico,
                R.id.textViewResultadoListarHistorico};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.historico_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }
}