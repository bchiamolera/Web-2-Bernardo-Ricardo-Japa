package furb.web2;



public class Produtos {
    
    private String nomeProduto;
    private double preco;
    private String marca;
    private int id;
    
    
    // Construtor
    public Produtos(String Produtonome, String marca, double preco, int id) {
        this.nomeProduto = Produtonome;
        this.marca = marca;
        this.preco = preco;
        this.id = id;
        
    }


    public String getMarca() {
        return marca;
    }

    public String getNome() {
        return nomeProduto;
    }

    public double getPreco() {
        return preco;
    }

    public int getId() {
        return id;
    }
    
   
    // Setters
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setNome(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setId(int id) {
        this.id = id;
    }
}
