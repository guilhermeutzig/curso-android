package mainapplication.com.listas;

/**
 * Created by guilherme.utzig on 27/04/2017.
 */

public class DescriptionDataProvider {

    private int icone;
    private String nome;
    private String descricao;

    public DescriptionDataProvider(int icone, String nome, String descricao) {
        this.icone = icone;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getIcone() {
        return icone;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
