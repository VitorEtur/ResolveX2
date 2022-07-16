package com.example.resolvex2;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.resolvex2.database.Conexao;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//Declarando as variaveis
    private Button numeroZero,numeroUm,numeroDois,numeroTres,numeroQuatro,numeroCinco,numeroSeis,numeroSete,numeroOito,numeroNove,
    ponto,soma,subtracao,multiplicacao,divisao,igual,botao_limpar,backspace,parentesquerdo,parentdireito,percentual, calculadora, historic;

    private TextView txtExpressao,txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide(); //Comando para ocultar o ActionBar

        IniciarComponentes(); //Iniciar Componentes
//Atribuindo o metodo SetOnClickListener para os botões
        numeroZero.setOnClickListener(this);
        numeroUm.setOnClickListener(this);
        numeroDois.setOnClickListener(this);
        numeroTres.setOnClickListener(this);
        numeroQuatro.setOnClickListener(this);
        numeroCinco.setOnClickListener(this);
        numeroSeis.setOnClickListener(this);
        numeroSete.setOnClickListener(this);
        numeroOito.setOnClickListener(this);
        numeroNove.setOnClickListener(this);
        ponto.setOnClickListener(this);
        soma.setOnClickListener(this);
        subtracao.setOnClickListener(this);
        multiplicacao.setOnClickListener(this);
        divisao.setOnClickListener(this);
        parentesquerdo.setOnClickListener(this);
        parentdireito.setOnClickListener(this);
        percentual.setOnClickListener(this);
        calculadora.setOnClickListener(this);
        historic.setOnClickListener(this);



    //    botao_limpar.setOnClickListener(new View.OnClickListener() { //Metodo de limpar todo o txtview
    //        @Override
    //        public void onClick(View v) {
//
    //            txtExpressao.setText("");
     //           txtResultado.setText("");

   //         }
   //     });

        backspace.setOnClickListener(new View.OnClickListener() {  //Criando o metodo de limpar dados
            @Override
            public void onClick(View v) {

                TextView expressao = findViewById(R.id.txt_expressao);
                String string = expressao.getText().toString();

                if (!string.isEmpty()){

                    byte var0 = 0;
                    int var1 = string.length()-1;
                    String txtExpressao = string.substring(var0,var1);
                    expressao.setText(txtExpressao);
                }
                txtResultado.setText("");
            }
        });
///******************8 Criando método para o botão igual além de mostrar o resultado, salvar o histórico
        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Expression expressao = new ExpressionBuilder(txtExpressao.getText().toString()).build();
                    double resultado =  expressao.evaluate();
                    long longResult = (long) resultado;

                    if (resultado == (double)longResult){
                        txtResultado.setText((CharSequence) String.valueOf(longResult));
                    }else{
                        txtResultado.setText((CharSequence) String.valueOf(resultado));
                    }
                }catch (Exception e){

                }
                adicionar(); ///Método para adicionar no histórico assim o calculo


            }
        });

    }

    private void IniciarComponentes(){    //Iniciando os componentes e atribuindo funções aos botões
        numeroZero = findViewById(R.id.numero_zero);
        numeroUm = findViewById(R.id.numero_um);
        numeroDois = findViewById(R.id.numero_dois);
        numeroTres = findViewById(R.id.numero_tres);
        numeroQuatro = findViewById(R.id.numero_quatro);
        numeroCinco = findViewById(R.id.numero_cinco);
        numeroSeis = findViewById(R.id.numero_seis);
        numeroSete = findViewById(R.id.numero_sete);
        numeroOito = findViewById(R.id.numero_oito);
        numeroNove = findViewById(R.id.numero_nove);
        ponto = findViewById(R.id.ponto);
        soma = findViewById(R.id.soma);
        subtracao = findViewById(R.id.subtracao);
        multiplicacao = findViewById(R.id.multiplicacao);
        divisao = findViewById(R.id.divisao);
        igual = findViewById(R.id.igual);
        //botao_limpar = findViewById(R.id.bt_limpar);
        txtExpressao = findViewById(R.id.txt_expressao);
        txtResultado = findViewById(R.id.txt_resultado);
        backspace = findViewById(R.id.backspace);
        parentesquerdo = findViewById(R.id.parentesesesquerdo);
        parentdireito = findViewById(R.id.parentesesdireito);
        percentual = findViewById(R.id.porcentagem);
        calculadora = findViewById(R.id.bt_calc);
        historic = findViewById(R.id.bt_hist);


    }
//*****************8 Criando método limpar dados
    public void AcrescentarUmaExpressao(String string,boolean limpar_dados){

        if (txtResultado.getText().equals("")){
            txtExpressao.setText(" ");
        }

        if (limpar_dados){
            txtResultado.setText(" ");
            txtExpressao.append(string);
        }else{
            txtExpressao.append(txtResultado.getText());
            txtExpressao.append(string);
            txtResultado.setText(" ");
        }
    }

    @Override
    public void onClick(View view) {   //Atribuindo o evento OnClick para os botões
        switch (view.getId()){
            case R.id.numero_zero:
                AcrescentarUmaExpressao("0",true);
                break;

            case R.id.numero_um:
                AcrescentarUmaExpressao("1",true);
                break;

            case R.id.numero_dois:
                AcrescentarUmaExpressao("2",true);
                break;

            case R.id.numero_tres:
                AcrescentarUmaExpressao("3",true);
                break;

            case R.id.numero_quatro:
                AcrescentarUmaExpressao("4",true);
                break;

            case R.id.numero_cinco:
                AcrescentarUmaExpressao("5",true);
                break;

            case R.id.numero_seis:
                AcrescentarUmaExpressao("6",true);
                break;

            case R.id.numero_sete:
                AcrescentarUmaExpressao("7",true);
                break;

            case R.id.numero_oito:
                AcrescentarUmaExpressao("8",true);
                break;

            case R.id.numero_nove:
                AcrescentarUmaExpressao("9",true);
                break;

            case R.id.ponto:
                AcrescentarUmaExpressao(".",true);
                break;

            case R.id.parentesesesquerdo:
                AcrescentarUmaExpressao("(", true);
                break;

            case R.id.parentesesdireito:
                AcrescentarUmaExpressao(")", true);
                break;

            case R.id.porcentagem:
                AcrescentarUmaExpressao("%", true);
                break;

            case R.id.soma:
                AcrescentarUmaExpressao("+",false);
                break;

            case R.id.subtracao:
                AcrescentarUmaExpressao("-",false);
                break;

            case R.id.multiplicacao:
                AcrescentarUmaExpressao("*",false);
                break;

            case R.id.divisao:
                AcrescentarUmaExpressao("/",false);
                break;
            case R.id.bt_hist:
                Navegar(view);
                break;

        }
    }
/////********* metódo para salvar os calculos no historico
    private void adicionar(){

//***************8 Método para pegar os dados dos textviews e armazenar no banco de daddos
        Conexao databaseHelper = new Conexao(getApplicationContext());
        Historico h = new Historico();
        h.setExpressao(txtExpressao.getText().toString());
        h.setResultado(txtResultado.getText().toString());
        databaseHelper.criarHistorico(h);

    }
    //*****************88 Método Intent para navegar entre os menus
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_Historico:
                Intent intent = new Intent(getApplicationContext(), ListarHistorico.class);

                startActivity(intent);

                return true;

            default:
                break;
        }

        return false;
    }

////////////////**********8Método para navegar entre as telas
    public void Navegar(View v) {
        Intent intent = new Intent(getApplicationContext(), ListarHistorico.class);
        startActivity(intent);
    }

}
