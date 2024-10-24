package furb.web2;


import furb.web2.CategoriaProdutosRepository.CategoriaRepository;
import furb.web2.CategoriaProdutosRepository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoriaProdutosController {

    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Endpoints de Produtos

    @GetMapping("/produtos")
    public List<Produtos> listarProdutos() {
        return produtosRepository.findAll();
    }

    @PostMapping("/produtos")
    public Produtos adicionarProduto(@RequestBody Produtos produto) {
        return produtosRepository.save(produto);
    }

    @PutMapping("/produtos/{id}")
    public Produtos atualizarProduto(@PathVariable int id, @RequestBody Produtos produtoDetalhes) {
        Optional<Produtos> produtoOptional = produtosRepository.findById(id);
        if (produtoOptional.isPresent()) {
            Produtos produto = produtoOptional.get();
            produto.setNome(produtoDetalhes.getNome());
            produto.setMarca(produtoDetalhes.getMarca());
            produto.setPreco(produtoDetalhes.getPreco());
            return produtosRepository.save(produto);
        }
        return null; 
    }

    @DeleteMapping("/produtos/{id}")
    public void deletarProduto(@PathVariable int id) {
        produtosRepository.deleteById(id);
    }

    // Endpoints de Categorias

    @GetMapping("/categorias")
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @PostMapping("/categorias")
    public Categoria adicionarCategoria(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @PutMapping("/categorias/{id}")
    public Categoria atualizarCategoria(@PathVariable int id, @RequestBody Categoria categoriaDetalhes) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();
            categoria.setNomeCategoria(categoriaDetalhes.getNomeCategoria());
            categoria.setTipo(categoriaDetalhes.getTipo());
            return categoriaRepository.save(categoria);
        }
        return null;
    }

    @DeleteMapping("/categorias/{id}")
    public void deletarCategoria(@PathVariable int id) {
        categoriaRepository.deleteById(id);
    }

    // Associar Produtos a Categorias

    @PostMapping("/categorias/{categoriaId}/produtos/{produtoId}")
    public Categoria adicionarProdutoCategoria(@PathVariable int categoriaId, @PathVariable int produtoId) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(categoriaId);
        Optional<Produtos> produtoOptional = produtosRepository.findById(produtoId);

        if (categoriaOptional.isPresent() && produtoOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();
            Produtos produto = produtoOptional.get();
            categoria.adicionarProduto(produto); // Associa o produto à categoria
            return categoriaRepository.save(categoria);
        }
        return null;
    }

    // Remover associação de Produto e Categoria
    @DeleteMapping("/categorias/{categoriaId}/produtos/{produtoId}")
    public Categoria removerProdutoCategoria(@PathVariable int categoriaId, @PathVariable int produtoId) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(categoriaId);
        Optional<Produtos> produtoOptional = produtosRepository.findById(produtoId);

        if (categoriaOptional.isPresent() && produtoOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();
            Produtos produto = produtoOptional.get();
            categoria.removerProduto(produto); // Remove o produto da categoria
            return categoriaRepository.save(categoria);
        }
        return null;
    }
}