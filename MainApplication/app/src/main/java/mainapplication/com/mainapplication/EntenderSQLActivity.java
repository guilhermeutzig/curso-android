package mainapplication.com.mainapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class EntenderSQLActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entender_sql);

        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbarTop);
        toolbarTop.setTitle("Menu");
        setSupportActionBar(toolbarTop);

        Button backToHome = (Button) findViewById(R.id.backHomeBtn);

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(EntenderSQLActivity.this, TelaPrincipal.class);
                startActivity(homeIntent);
            }
        });

        // Try catch para caso dê erro, não fechar a aplicação
        try {

            SQLiteDatabase bancoDados = openOrCreateDatabase("MeuApp", MODE_PRIVATE, null);

            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS cadastropessoas (nome VARCHAR, idade INT(3))");

//        bancoDados.execSQL("INSERT INTO cadastropessoas (nome, idade) VALUES ('Guilherme Leonardo Utzig', 19)");
//        bancoDados.execSQL("INSERT INTO cadastropessoas (nome, idade) VALUES ('Vinicius Padilha', 19)");
//        bancoDados.execSQL("INSERT INTO cadastropessoas (nome, idade) VALUES ('Felipe Gomes', 22)");

            bancoDados.execSQL("DELETE FROM cadastropessoas WHERE nome = 'Felipe Gomes'");

            Cursor cursor = bancoDados.rawQuery("SELECT nome, idade FROM cadastropessoas", null);

            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();

            while(cursor != null) {
                Log.i("LogCursor", cursor.getString(indiceNome));
                Log.i("LogCursor", cursor.getString(indiceIdade));
                cursor.moveToNext();
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
