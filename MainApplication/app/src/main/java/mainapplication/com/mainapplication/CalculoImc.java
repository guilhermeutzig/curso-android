package mainapplication.com.mainapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculoImc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_imc);

        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbarTop);
        toolbarTop.setTitle("Menu");
        setSupportActionBar(toolbarTop);

        // Recebendo texto do TelaPrincipal
        TextView textoParametro = (TextView) findViewById(R.id.parameterText);
        Intent objetoIntentNovo = getIntent();
        String texto = objetoIntentNovo.getStringExtra("meuTexto");
        textoParametro.setText(texto);


        final Button botaoImc = (Button) findViewById(R.id.calcularImc);
        final Button backHome = (Button) findViewById(R.id.backHomeBtn);

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(CalculoImc.this, TelaPrincipal.class);
                startActivity(homeIntent);
            }
        });

        // Evento de click para calcular IMC (Peso ideal)
        botaoImc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Valor do input de peso
                final EditText pesoInput = (EditText) findViewById(R.id.inputPeso);
                float peso = Float.parseFloat(pesoInput.getText().toString());
                // Valor do input de altura
                final EditText alturaInput = (EditText) findViewById(R.id.inputAltura);
                float altura = Float.parseFloat(alturaInput.getText().toString());
                // Resultado IMC
                float resultado = peso / (altura * altura);
                String mensagem = "";

                if (resultado < 17) {
                    mensagem = "Seu IMC é: " + resultado + "\nVocê está muito abaixo do peso ideal!\n\nO peso ideal fica entre 18,5 e 24,99";
                } else if (resultado >= 17 && resultado < 18.5) {
                    mensagem = "Seu IMC é: " + resultado + "\nVocê está abaixo do peso ideal!\n\nO peso ideal fica entre 18,5 e 24,99";
                } else if (resultado >= 18.5 && resultado < 25) {
                    mensagem = "Seu IMC é: " + resultado + "\nVocê está no peso ideal!";
                } else if (resultado >= 25) {
                    mensagem = "Seu IMC é: " + resultado + "\nVocê está acima do peso ideal!\n\nO peso ideal fica entre 18,5 e 24,99";
                }

                // Gerando Alert para mostrar resultado
                AlertDialog.Builder alertIMC;
                alertIMC = new AlertDialog.Builder(CalculoImc.this);
                alertIMC.setTitle("Peso Ideal");
                alertIMC.setMessage(mensagem);
                alertIMC.setIcon(R.drawable.laranjas);
                alertIMC.setNeutralButton("Fechar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        alturaInput.setText("");
                        pesoInput.setText("");
                        dialog.cancel();
                    }
                });
                alertIMC.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.homeMenuOption) {
            Intent homeIntent = new Intent(CalculoImc.this, TelaPrincipal.class);
            startActivity(homeIntent);
        }

        if (id == R.id.calculoMenuOption) {
            Intent calculoIntent = new Intent(CalculoImc.this, CalculoImc.class);
            startActivity(calculoIntent);
        }

        if (id == R.id.emailMenuOption) {
        }

        return super.onOptionsItemSelected(item);
    }

}
