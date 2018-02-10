package mainapplication.com.mainapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GravarDadosActivity extends AppCompatActivity {

    // ToRemember
    private Button buttonRemember;
    private TextView resultRemember;
    private EditText nameRemember;
    private EditText ageRemember;
    private static String MINHAS_PREFERENCIAS = "Minhas Preferências";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravar_dados);

        // ToRemember
        buttonRemember = (Button) findViewById(R.id.buttonToRemember);
        nameRemember = (EditText) findViewById(R.id.nameToRemember);
        ageRemember = (EditText) findViewById(R.id.ageToRemember);
        resultRemember = (TextView) findViewById(R.id.resultToRemember);

        SharedPreferences sharedPreferences = getSharedPreferences(MINHAS_PREFERENCIAS, 0);

        if (sharedPreferences.contains("nome") && sharedPreferences.contains("idade")) {
            String nomeUsuario = sharedPreferences.getString("nome", "");
            int idadeUsuario = sharedPreferences.getInt("idade", 0);

            resultRemember.setText(nomeUsuario + ", " + idadeUsuario + " anos.");

        }

        buttonRemember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences(MINHAS_PREFERENCIAS, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                String nameValue = nameRemember.getText().toString();
                String ageValue = ageRemember.getText().toString();

                if (nameValue != "" && ageValue != "") {
                    editor.putString("nome", nameRemember.getText().toString());
                    editor.putInt("idade", Integer.parseInt(ageRemember.getText().toString()));

                    editor.commit();

                    if (sharedPreferences.contains("nome") && sharedPreferences.contains("idade")) {
                        String nomeUsuario = sharedPreferences.getString("nome", "");
                        int idadeUsuario = sharedPreferences.getInt("idade", 0);
                        resultRemember.setText(nomeUsuario + ", " + idadeUsuario + " anos.");
                    } else {
                        resultRemember.setText("Preencha os dados corretamente");
                    }
                }

            }
        });

    }

}
