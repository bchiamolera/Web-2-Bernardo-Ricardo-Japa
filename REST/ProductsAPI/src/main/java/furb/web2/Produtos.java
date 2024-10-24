package furb.web2;

import java.util.ArrayList;
import java.util.List;

public class Produtos {
    
    private String nomeProduto;
    private double preco;
    private String marca;
    private int id;
    private List<Categoria> categorias; // Lista de categorias

    // Construtor
    public Produtos(String nomeProduto, String marca, double preco, int id) {
        this.nomeProduto = nomeProduto;
        this.marca = marca;
        this.preco = preco;
        this.id = id;
        this.categorias = new ArrayList<>(); // Inicializa a lista
    }

    // Getters
    public String getNome() {
        return nomeProduto;
    }

    public double getPreco() {
        return preco;
    }

    public String getMarca() {
        return marca;
    }

    public int getId() {
        return id;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    // Adicionar uma categoria ao produto
    public void adicionarCategoria(Categoria categoria) {
        if (!categorias.contains(categoria)) {
            categorias.add(categoria);
            categoria.adicionarProduto(this); // Associa o produto à categoria
        }
    }

    // Remover uma categoria do produto
    public void removerCategoria(Categoria categoria) {
        if (categorias.contains(categoria)) {
            categorias.remove(categoria);
            categoria.removerProduto(this); // Remove o produto da categoria também
        }
    }

    // Setters
    public void setNome(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setId(int id) {
        this.id = id;
    }
}