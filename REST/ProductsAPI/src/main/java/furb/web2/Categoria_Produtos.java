package furb.web2;

public class Categoria_Produtos {
	public Categoria categoria;
	public Produtos produto;
 
	// Construtor
    public Categoria_Produtos(Produtos produto, Categoria categoria) {
        this.produto = produto;
        this.categoria = categoria;
    }
    
    

    // Getters
    public Produtos getProduto() {
        return produto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    //Setters
    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    
    
    
    public void exibirInformacoes() {
        System.out.println("Produto: " + produto.getNome());
        System.out.println("Marca: " + produto.getMarca());
        System.out.println("Preço: " + produto.getPreco());
        System.out.println("ID: " + produto.getId());
        System.out.println("Categoria: " + categoria.getNomeCategoria());
        System.out.println("Tipo de Categoria: " + categoria.getTipo());
    }
}