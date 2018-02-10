package mainapplication.com.quizz;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private TextView pergunta;

    private RadioButton opcaoA;
    private RadioButton opcaoB;
    private RadioButton opcaoC;

    private Button buttonOk;

    String Perguntas[] = {
            "Primeira pergunta?",
            "Segunda pergunta?",
            "Terceira pergunta?",
            "Quarta pergunta?",
            "Quinta pergunta?"
    };

    String OpcaoA[] = {
          "Resposta A",
          "Resposta A",
          "Resposta A",
          "Resposta A",
          "Resposta A"
    };

    String OpcaoB[] = {
            "Resposta B",
            "Resposta B",
            "Resposta B",
            "Resposta B",
            "Resposta B"
    };

    String OpcaoC[] = {
            "Resposta C",
            "Resposta C",
            "Resposta C",
            "Resposta C",
            "Resposta C"
    };

    int[] listaRespostas = new int[Perguntas.length];
    int listaGabarito[] = {1, 2, 3, 1, 2};
    int respostasCorretas = 0;
    int numeroPergunta = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOk = (Button) findViewById(R.id.btnOk);
        buttonOk.setEnabled(false);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        pergunta = (TextView) findViewById(R.id.questionText);

        opcaoA = (RadioButton) findViewById(R.id.optionA);
        opcaoB = (RadioButton) findViewById(R.id.optionB);
        opcaoC = (RadioButton) findViewById(R.id.optionC);

        atualizaPerguntas(buttonOk);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.optionA:
                        listaRespostas[numeroPergunta-1] = 1;
                        break;
                    case R.id.optionB:
                        listaRespostas[numeroPergunta-1] = 2;
                        break;
                    case R.id.optionC:
                        listaRespostas[numeroPergunta-1] = 3;
                        break;
                }
                buttonOk.setEnabled(true);
            }
        });

    }

    public void atualizaPerguntas(View view) {

        if (numeroPergunta == Perguntas.length) {

            confereResultado();
            radioGroup.clearCheck();
            opcaoA.setEnabled(false);
            opcaoB.setEnabled(false);
            opcaoC.setEnabled(false);
            buttonOk.setEnabled(false);

        } else {
            pergunta.setText(Perguntas[numeroPergunta]);

            opcaoA.setText(OpcaoA[numeroPergunta]);
            opcaoB.setText(OpcaoB[numeroPergunta]);
            opcaoC.setText(OpcaoC[numeroPergunta]);

            radioGroup.clearCheck();
            buttonOk.setEnabled(false);
            numeroPergunta++;
        }


    }

    public void confereResultado() {
        int contadorLista = 0;
        for (int numero : listaRespostas) {
            System.out.println(numero);

            if (numero == listaGabarito[contadorLista]) {
                respostasCorretas++;
                System.out.println("Resposta correta.");
            } else {

            }

            contadorLista++;
        }
        alertaResultado(buttonOk);
    }

    public void alertaResultado(View view) {
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();

        String alertTitle;

        if (respostasCorretas < 3) {
            alertTitle = "Você tem que melhorar!";
        } else if (respostasCorretas >= 3 && respostasCorretas < 5) {
            alertTitle = "Parabéns!";
        } else {
            alertTitle = "Perfeito! Você acertou todas!";
        }

        alertDialog.setTitle(alertTitle);
        alertDialog.setMessage("Você acertou " + respostasCorretas + " questões!");
        alertDialog.show();
    }

}
