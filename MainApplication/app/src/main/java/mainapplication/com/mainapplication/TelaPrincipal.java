package mainapplication.com.mainapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

public class TelaPrincipal extends AppCompatActivity {

    // Load Image
    private Button carregarImagem;
    private ImageView imagemCarregada;
    String urlImagem = "https://meusanimais.com.br/wp-content/uploads/2016/02/lobos.jpg";

    // Sendo declarada aqui para ser vista em todas as funções
    TextView objetoTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbarTop);
        toolbarTop.setTitle("Menu");
        setSupportActionBar(toolbarTop);

        FloatingActionButton emailFloatingBtn = (FloatingActionButton) findViewById(R.id.emailFloatingBtn);
        emailFloatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Envie uma mensagem!", Snackbar.LENGTH_LONG)
                        .setAction("Fechar", null).show();
            }
        });

        // Chamei os objetos e declarei em uma variável
        objetoTexto = (TextView) findViewById(R.id.textView);
        final Button botaozinho = (Button) findViewById(R.id.meuBotao);
        final Button botaoIdade = (Button) findViewById(R.id.idadeTeste);
        final Button calcularImcHref = (Button) findViewById(R.id.calcularImcHref);
        final Button entenderSQLHref = (Button) findViewById(R.id.entenderSQLHref);
        final Button gravarDadosHref = (Button) findViewById(R.id.gravarDadosHref);

        // Mudar Activity para GravarDados
        gravarDadosHref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gravarDadosIntent = new Intent(TelaPrincipal.this, GravarDadosActivity.class);
                startActivity(gravarDadosIntent);
            }
        });

        // Mudar Activity para EntenderSQL
        entenderSQLHref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sqlIntent = new Intent(TelaPrincipal.this, EntenderSQLActivity.class);
                startActivity(sqlIntent);
            }
        });

        // Mudar Activity para a CalcularImc
        calcularImcHref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Passando parâmetro para CalculoImc
                Intent calculoIntent = new Intent(TelaPrincipal.this, CalculoImc.class);
                calculoIntent.putExtra("meuTexto", "Novo texto que foi passado por parâmetro!");
                startActivity(calculoIntent);
            }
        });

        // Evento de click
        botaoIdade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder construtorAlerta;
                construtorAlerta = new AlertDialog.Builder(TelaPrincipal.this);
                construtorAlerta.setTitle("Alerta!");
                construtorAlerta.setMessage("Coloque sua idade");

                final EditText input = new EditText(TelaPrincipal.this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                construtorAlerta.setView(input);

                // Adicionar botão positivo
                construtorAlerta.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int idadeInput = Integer.parseInt(input.getText().toString());
                        Toast.makeText(TelaPrincipal.this, "Sua idade é de " + idadeInput, Toast.LENGTH_SHORT).show();
                    }
                });

                // Adicionar botão negativo
                construtorAlerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog meuAlerta = construtorAlerta.create();
                meuAlerta.show();
            }
        });

        // Evento de click
        botaozinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Declarando variável Toast e Snackbar
                Toast toastBotao;
                Snackbar meuSnack;

                // Atribuindo a view, mensagem e duração
                toastBotao = Toast.makeText(TelaPrincipal.this, "Toast", Toast.LENGTH_SHORT);
                meuSnack = Snackbar.make(v, "Snackbar", Snackbar.LENGTH_SHORT);

                // Fazendo mensagem aparecer
                toastBotao.show();
                meuSnack.show();

                // Criando uma ação de click no Snack
                meuSnack.setAction("Fechar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Escondendo botão
                        botaozinho.setVisibility(View.GONE);
                    }
                });
            }
        });

        // Load Image
        carregarImagem = (Button) findViewById(R.id.loadImage);
        imagemCarregada = (ImageView) findViewById(R.id.loadedImage);

        carregarImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagemDownloader imagemDownloader = new ImagemDownloader();
                imagemDownloader.execute(urlImagem);
            }
        });
    }

    public class ImagemDownloader extends AsyncTask<String, String, Bitmap> {

        AlertDialog alerta = new AlertDialog.Builder(TelaPrincipal.this)
                .setMessage("Carregando imagem...")
                .show();

        @Override
        protected Bitmap doInBackground(String... args) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                imagemCarregada.setImageBitmap(bitmap);
                alerta.hide();
            }
        }
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
            Intent homeIntent = new Intent(TelaPrincipal.this, TelaPrincipal.class);
            startActivity(homeIntent);
        }

        if (id == R.id.calculoMenuOption) {
            Intent calculoIntent = new Intent(TelaPrincipal.this, CalculoImc.class);
            startActivity(calculoIntent);
        }

        if (id == R.id.sqliteOption) {
            Intent sqlIntent = new Intent(TelaPrincipal.this, EntenderSQLActivity.class);
            startActivity(sqlIntent);
        }

        if (id == R.id.recordDataOption) {
            Intent recordDataIntent = new Intent(TelaPrincipal.this, GravarDadosActivity.class);
            startActivity(recordDataIntent);
        }

        if (id == R.id.emailMenuOption) {
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("LogX: ", "START");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LogX: ", "STOP");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("LogX: ", "DESTROY");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("LogX: ", "PAUSE");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LogX: ", "RESUME");
    }
}
