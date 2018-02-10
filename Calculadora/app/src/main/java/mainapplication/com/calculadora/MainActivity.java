package mainapplication.com.calculadora;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView resultado;
    private TextView numeroPequeno;
    float numeroA = 0;
    String operacao = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultado = (TextView) findViewById(R.id.resultado);
        numeroPequeno = (TextView) findViewById(R.id.firstNumber);

    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.clear:
                resultado.setText("0");
                numeroA = 0;
                numeroPequeno.setText("");
                operacao = "";
                break;
            case R.id.mais:
                calculaNumeros("+");
                break;
            case R.id.menos:
                calculaNumeros("-");
                break;
            case R.id.dividido:
                calculaNumeros("/");
                break;
            case R.id.vezes:
                calculaNumeros("*");
                break;
            case R.id.igual:
                mostraResultado();
                numeroPequeno.setText("");
                break;
            default:
                String number;
                number = ((Button)view).getText().toString();
                getKeyboard(number);
                break;
        }
    }

    public void calculaNumeros(String tipoOperacao) {
        numeroA = Integer.parseInt(resultado.getText().toString());
        operacao = tipoOperacao;
        resultado.setText("0");
        numeroPequeno.setText(String.valueOf(numeroA) + " " + tipoOperacao);
    }

    public void getKeyboard(String str) {
        String ScrCurrent = resultado.getText().toString();
        if (resultado.getText().equals("0")) {
            ScrCurrent = str;
        } else {
            ScrCurrent += str;
        }
        resultado.setText(ScrCurrent);
    }

    public void mostraResultado() {

        float numeroB = Integer.parseInt(resultado.getText().toString());
        float result = 0;

        if (operacao.equals("+")) {
            result = numeroB + numeroA;
        } else if (operacao.equals("-")) {
            result = numeroA - numeroB;
        } else if (operacao.equals("*")) {
            result = numeroA * numeroB;
        } else if (operacao.equals("/")) {
            result = numeroA / numeroB;
        }

        resultado.setText(String.valueOf(result));

    }

}
