package mainapplication.com.listas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class DescriptionsLists extends AppCompatActivity {

    ListView listaDescription;

    private String[] listaDeNomes = {
            "Gui",
            "Gabi",
            "Mati",
            "Mãe",
            "Pai"
    };

    private String[] listaDescricao = {
            "É um guri saudável",
            "É uma guria ciumenta",
            "É um guri gamer",
            "É uma mulher reclamona",
            "É um homem reinento"
    };

    int[] listaIcones = {
            R.drawable.bart,
            R.drawable.bart,
            R.drawable.bart,
            R.drawable.bart,
            R.drawable.bart
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descriptions_lists);

        // Redirect to home
        Button goToHome = (Button) findViewById(R.id.goToHome);
        goToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(DescriptionsLists.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });

        // Lista description
        listaDescription = (ListView) findViewById(R.id.listaDescriptions);
        ArrayAdapter<String> descriptionAdapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                listaDeNomes
        );

        AdaptadorDescricao adaptador;
        int i = 0;
        adaptador = new AdaptadorDescricao(getApplicationContext(), R.layout.celula_descricao);

        for ( String nome:listaDeNomes ) {
            DescriptionDataProvider dataProvider = new DescriptionDataProvider(
                    listaIcones[i], listaDeNomes[i], listaDescricao[i]
            );
            adaptador.add(dataProvider);
            i++;
        }

        listaDescription.setAdapter(adaptador);

    }

}
