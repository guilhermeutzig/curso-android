package mainapplication.com.listas;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by guilherme.utzig on 27/04/2017.
 */

public class AdaptadorDescricao extends ArrayAdapter {

    public AdaptadorDescricao(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row;

        row = convertView;
        DataHandler handler;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.celula_descricao, parent, false);
            handler = new DataHandler();
            handler.imagemIcone = (ImageView)row.findViewById(R.id.imagemIcone);
            handler.nome = (TextView)row.findViewById(R.id.textName);
            handler.descricao = (TextView)row.findViewById(R.id.textDescription);
            row.setTag(handler);
        } else {
            handler = (DataHandler)row.getTag();
        }

        DescriptionDataProvider dataProvider;
        dataProvider = (DescriptionDataProvider) this.getItem(position);
        handler.imagemIcone.setImageResource(dataProvider.getIcone());
        handler.nome.setText(dataProvider.getNome());
        handler.descricao.setText(dataProvider.getDescricao());

        return row;
    }

    private class DataHandler {
        ImageView imagemIcone;
        TextView nome;
        TextView descricao;
    }

}
