package com.example.resolvex2;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.example.resolvex2.database.Conexao;
//********Criando o ListarHistorico. Esse Activity é ligado ao layout activity_historico_lista
public class ListarHistorico extends AppCompatActivity {
    private Conexao databaseHelper;
    private Historico h;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_lista);
        getSupportActionBar().hide(); //Comando para ocultar o ActionBar
        back = findViewById(R.id.button_voltar); //****Atribuindo função ao botão para voltar a outra tela
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class); //***Método intent para navegar entre telas
                startActivity(intent);
            }
        });
//****************Instanciando a classe de banco de dados
        Conexao conexao = new Conexao(getApplicationContext());
        ListView lv = findViewById(R.id.list_View_listar_historico);
        conexao.getAllHistorico(getApplicationContext(),lv);

        //***************configurando a listagem de dados
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvId = view.findViewById(R.id.textViewIdListarHistorico);
                Bundle b = new Bundle();
                b.putInt("id", Integer.parseInt(tvId.getText().toString()));

            }
        });
    }
}