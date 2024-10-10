package furb.web2;



public class Categoria {
    
    private String nomeCategoria;
    private String tipo;
   

    // Construtor
    public Categoria(String nomeCategoria, String tipo) {
        this.nomeCategoria = nomeCategoria;
        this.tipo = tipo;
        
    }

   
    
   
    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public String getTipo() {
        return tipo;
    }
    
    
    // Setters
    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
