package mainapplication.com.listas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView minhaLista;

    private String[] listaDeNomes = { "Gui", "Gabi", "Mati", "Mãe", "Pai" };
    private String[] listaNomeCompleto = { "Guilherme Leonardo Utzig", "Gabriela Bauer Scherer", "Mateus Utzig", "Lisiane Silva da Cunha", "Algenor Luiz Utzig" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Redirect para DescriptionsLists
        Button goToDescriptions = (Button) findViewById(R.id.goToDescriptions);

        goToDescriptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent descriptionIntent = new Intent(MainActivity.this, DescriptionsLists.class);
                startActivity(descriptionIntent);
            }
        });

        // Lista da home
        minhaLista = (ListView) findViewById(R.id.listaNomes);
        ArrayAdapter<String> meuAdaptador = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                listaDeNomes
        );

        minhaLista.setAdapter(meuAdaptador);

        minhaLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String textNome = listaNomeCompleto[position];
                Toast.makeText(getApplicationContext(), textNome, Toast.LENGTH_SHORT).show();

            }
        });

    }

}
