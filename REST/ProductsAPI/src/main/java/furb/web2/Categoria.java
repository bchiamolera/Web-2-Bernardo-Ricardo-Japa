package furb.web2;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    
    private String nomeCategoria;
    private String tipo;
    private List<Produtos> produtos; // Lista de produtos

    // Construtor
    public Categoria(String nomeCategoria, String tipo) {
        this.nomeCategoria = nomeCategoria;
        this.tipo = tipo;
        this.produtos = new ArrayList<>(); // Inicializa a lista
    }

    // Getters
    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public String getTipo() {
        return tipo;
    }

    public List<Produtos> getProdutos() {
        return produtos;
    }

    // Adicionar um produto à categoria
    public void adicionarProduto(Produtos produto) {
        if (!produtos.contains(produto)) {
            produtos.add(produto);
            produto.adicionarCategoria(this); // Associa a categoria ao produto
        }
    }

    // Remover um produto da categoria
    public void removerProduto(Produtos produto) {
        if (produtos.contains(produto)) {
            produtos.remove(produto);
            produto.removerCategoria(this); // Remove a categoria do produto também
        }
    }

    // Setters
    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}