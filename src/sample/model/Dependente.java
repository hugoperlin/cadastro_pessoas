package sample.model;

public class Dependente {

    private int id;
    private String nome;
    private int idade;
    private Pessoa pessoa;


    public Dependente(int id, String nome, int idade, Pessoa pessoa){
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.pessoa = pessoa;
    }

    public Dependente(String nome, int idade, Pessoa pessoa){
        this(-1,nome,idade,pessoa);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String toString() {
        return "Dependente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", pessoa=" + pessoa +
                '}';
    }
}
