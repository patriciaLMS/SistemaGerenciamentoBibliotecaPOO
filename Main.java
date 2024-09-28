import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Biblioteca biblio = new Biblioteca();
    static Scanner input = new Scanner(System.in);



    private static int inputNumerico(String mensagem){
        
        int valor = 0;
        boolean entradaValida = false;
        System.out.println(mensagem);

        do {
        String valorStr = input.nextLine();
        try {
            valor = Integer.parseInt(valorStr);
            entradaValida = true;
        } catch (Exception e) {
            System.out.println("Erro. Por favor informe um número inteiro");
        }
    }while(!entradaValida);
        return valor;
    }

    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void tempoVisualizar(){
       try {
        Thread.sleep(2000);
       } catch (Exception e) {
        //
       }
    }

    private static void listar(){
        //List<Livro> livros = biblio.pesquisarTodos();
        var livros = biblio.pesquisarTodos();
        livros.sort(Comparator.comparing(Livro::getTitulo));
        System.out.println("======== LISTA DE LIVROS =======");
        for (Livro livro : livros) {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Ano: " + livro.getAnoPublicacao());
            System.out.println("N. Páginas: " + livro.getnPaginas());
            System.out.println("--------------------------------");
            
        }
        if(livros.isEmpty()){
            System.out.println("A lista está vazia.");
        }
        System.out.println("Pressione Enter para continuar...");
        input.nextLine(); 
    }

    private static void adicionar(){
        Livro novoLivro = new Livro();
        System.out.println("========= ADICIONANDO NOVO LIVRO ========");
        System.out.print("Informe o título do livro: "); // não quero quebra de linha então tirar ln do println
        String titulo = input.nextLine();
        novoLivro.setTitulo(titulo);
        
        System.out.print("Informe o nome do autor: "); 
        novoLivro.setAutor(input.nextLine());
        //input.nextLine();
                
        

        // System.out.println("Informe o ano de publicação: ");
        //novoLivro.setAnoPublicacao(input.nextInt());
        //input.nextLine(); //limpar buffer
        
        int anoPublicacao = 0;
        boolean entradaValida = false;
        do {
            System.out.print("Informe o ano de publicação: ");
            String anoStr = input.nextLine();

        
        try {
            anoPublicacao = Integer.parseInt(anoStr);
            if(anoPublicacao  <= 1400 || anoPublicacao > LocalDate.now().getYear()){
                System.out.println("Erro: por favor insira um ano de publicação maior ou igual 1400. O ano de publicação inserido precisa ser menor que o ano atual.");
                
            }else{
                novoLivro.setAnoPublicacao(anoPublicacao);
                entradaValida = true; //mudei aqui
               // input.nextLine();
                
            }
            
            
            
            } 
         catch (Exception e) { //mudei aqui tbm
           
            System.out.println("Erro: Por favor, informe um ano válido (número inteiro).");
        }
    }while (!entradaValida);
    

        

    int nPaginas = 0;
    entradaValida = false;
    do{
        System.out.print("Informe o número de páginas: ");
        String paginasStr = input.nextLine();

       
        try {
            nPaginas = Integer.parseInt(paginasStr);
            if (nPaginas <= 0) {
                System.out.println("Erro: O número de páginas deve ser maior que zero (não são permitidos números negativos).");
            } else {
                novoLivro.setnPaginas(nPaginas);
                entradaValida = true; 
            }
        } catch (Exception e) { //mudei aqui tbm
           
            System.out.println("Erro: Por favor, informe um número válido para páginas.");
        }
    }while (!entradaValida);

       
    try {
            biblio.adicionar(novoLivro);
            System.out.println(" ********** Livro adicionado com Sucesso! ************");
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
           
        }
        
        input.nextLine(); // espera o usuário dar enter 
    }

    public static void pesquisarPorTitulo(){
        System.out.println("======== PESQUISANDO LIVROS ========");
        System.out.println("Informe o título do livro que você quer pesquisar: ");
        String titulo = input.nextLine();  // A pessoa digita o livro(s) que quer encontrar.
        
        var livrosEncontrados = biblio.pesquisarPorTitulo(titulo);

        if (livrosEncontrados.isEmpty() || livrosEncontrados == null ) {
            System.out.println("Nenhum livro encontrado contendo o título informado.");
            System.out.println("------------------------------------");
        }
        else {
            System.out.println("======== LIVROS ENCONTRADOS: ========");
            
            for (Livro livro : livrosEncontrados) {
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Ano: " + livro.getAnoPublicacao());
                System.out.println("N. Páginas: " + livro.getnPaginas());
                System.out.println("-----------------------------");
            }
        // É printado os livros encontrados.
        }
        System.out.println("Pressione Enter para continuar...");
        input.nextLine(); 
    }
        
        
        
        
        

    

    public static void removerPorTitulo(){
        System.out.println("======= REMOVENDO LIVRO ======");
        System.out.println("Informe o título do livro que você quer remover: ");
        String titulo = input.nextLine();
        var livrosEncontrados = biblio.pesquisarPorTitulo(titulo);
        
        
        try {
            //boolean livroRemovido = false;
            //biblio.removerPorTitulo(titulo);
            if (livrosEncontrados.isEmpty()) {
                System.out.println("Nenhum livro encontrado com o título: " + titulo);
            }else{
                biblio.removerPorTitulo(titulo);
                System.out.println("Livro removido com Sucesso!");
                System.out.println("Todos os livros com o título \"" + titulo + "\" foram removidos.");
            }

            

           
           
           
            
      
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
        

         

        }
        
       
       
        
        // encontrar o livro para remover ele




    



    public static void main(String[] args) {
        
       //aqui está o start da nossa aplicação
       String menu = """
       ###################################################

               SISTEMA DE GERENCIAMENTO DE BIBLIOTECA
               Escolha uma das opções:
               1 - Adicionar novo livro;
               2 - Listar todos os livros;
               3 - Pesquisar livro;
               4 - Remover livro;
               0 - Sair
               
        ###################################################
            
               """;
        int opcao;
      
        do { 
            
            
            clear();
            
           // System.out.println(menu);
            //opcao = input.nextInt();
            //input.nextLine(); //limpar buffer
            
            opcao = inputNumerico(menu);
            
            
            switch (opcao) {
                
                case 0:
                    clear();
                    System.out.println("VOLTE SEMPRE!!!");
                    break;
                case 1:
                    clear();
                    adicionar();
                    break;
                case 2:
                    
                    listar();
                    break;
                case 3:
                    clear();
                    pesquisarPorTitulo();
                    break;
                    
                case 4:
                    clear();
                    removerPorTitulo();
                    break;
            
                default:
                System.out.println("Opção Inválida!!!");
                input.nextLine();
                    break;
            }
            tempoVisualizar();
        } while (opcao != 0);
          clear();
            

        Livro novoLivro = new Livro();
        novoLivro.setTitulo("O Senhor dos Anéis");
        novoLivro.setAutor("J.R.R Tolkien");
        novoLivro.setAnoPublicacao(2010);
        novoLivro.setnPaginas(340);

       // biblio.adicionar(novoLivro);

        System.out.println("");
    }
}
