package mainapplication.com.contadorpessoas;

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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int totalPessoas = 0;
    int totalHomens = 0;
    int totalMulheres = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button botaoHomem = (Button) findViewById(R.id.botaoHomem);
        final Button botaoMulher = (Button) findViewById(R.id.botaoMulher);
        final Button botaoReset = (Button) findViewById(R.id.botaoReset);

        final TextView textWomen = (TextView) findViewById(R.id.textWomen);
        final TextView textMen = (TextView) findViewById(R.id.textMen);
        final TextView textTotal = (TextView) findViewById(R.id.textTotal);

        botaoReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalPessoas = 0;
                totalHomens = 0;
                totalMulheres = 0;

                botaoHomem.setText(Integer.toString(totalHomens));
                botaoMulher.setText(Integer.toString(totalMulheres));

                textMen.setText("Homens: " + totalHomens);
                textWomen.setText("Mulheres: " + totalHomens);
                textTotal.setText("Pessoas: " + totalPessoas);
            }
        });

        botaoHomem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalHomens++;
                totalPessoas++;
                textMen.setText("Homens: " + totalHomens);
                textTotal.setText("Pessoas: " + totalPessoas);
                botaoHomem.setText(Integer.toString(totalHomens));
            }
        });

        botaoMulher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalPessoas++;
                totalMulheres++;
                textWomen.setText("Mulheres: " + totalMulheres);
                textTotal.setText("Pessoas: " + totalPessoas);
                botaoMulher.setText(Integer.toString(totalMulheres));
            }
        });


    }
}
