import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    // BD em memória
    private List<Livro> acervo = new ArrayList<>();

    

    public void adicionar(Livro livro) throws Exception {
        if(livro.getTitulo() == null || livro.getTitulo().isEmpty())
            throw new Exception("Não é permitido cadastrar livro sem título");

        if(livro.getAutor() == null || livro.getAutor().isEmpty())
            throw new Exception("Não é permitido cadastrar livros sem o nome do autor");

        
        if(livro.getnPaginas() <= 0){
            throw new Exception("Não é permitido cadastrar livros com número de páginas zerado ou com número de páginas negativo");}
        
      
    
        if (livro.getAnoPublicacao() <= 1400 || livro.getAnoPublicacao() > LocalDate.now().getYear()){
        
                throw new Exception("Não é permitido cadastrar livros com ano de publicação menor ou igual a 1400, ou com o ano de publicação maior que o ano atual.");}
          
        
        

        

        

            for (Livro livroAcervo : acervo) {
                if(livroAcervo.getTitulo().equalsIgnoreCase(livro.getTitulo()))
                    throw new Exception("Já existe livro cadastrado com este título");
                
            }
            acervo.add(livro);
    }

    public List<Livro> pesquisarPorTitulo(String titulo) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro1 : acervo) {
           

            
            if (livro1.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                livrosEncontrados.add(livro1);
            }
        
        }
            return livrosEncontrados;
        }
        
   


    public void removerPorTitulo(String titulo) throws Exception {
        if (titulo != null && !titulo.isEmpty()) {
           boolean livroRemovido = false;
    
        for (Livro livro : acervo) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                acervo.remove(livro);
                livroRemovido = true;
                break; } }

    
    }


    }

    

    public List<Livro> pesquisarTodos() {
        return this.acervo;
    }
}

