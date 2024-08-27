import java.util.Random;

class Aleatorio { // Gera um número aleatório de 0 até o limite - 1.
  public static int GeraNoLimite(int Limite) {
      Random random = new Random();
      return random.nextInt(Limite); 
  }
}

class centralizador{ // Centralza os caracteres no texto do menu.
  public static String centralizaTexto(String Texto, int Largura) {
    int EspacosEsquerda = (Largura - Texto.length()) / 2;
    int EspacosDireita = Largura - Texto.length() - EspacosEsquerda;
    return " ".repeat(EspacosEsquerda) + Texto + " ".repeat(EspacosDireita);
  }
}

abstract class Pokemon {
  String Nome, Tipo;
  int Nivel, HP, VidaMax;

  public Pokemon(String nome, int nivel, int VidaMax, String tipo){ // Construtor.
    this.Nome = nome;
    this.Tipo = tipo;
    this.Nivel = nivel;
    this.HP = VidaMax;
    this.VidaMax = VidaMax;
  }

  public abstract void Atacar(Pokemon pokemon); // Metodo que será implementado dentro das classes herdeiras.
  
  public void RecebeDano(int dano){ // Tira vida do pokemon atacado.
    if(HP - dano <= 0)HP = 0;
    else HP -= dano;
  }

  public int RecuperaHP(){  // Restaura uma porcentagem da vida do pokemon escolhido.
    int Porcentagem = Aleatorio.GeraNoLimite(100)+1; // Gera um número aleatório de 1 até 100 para definir a porcentagem de vida que será curada.
    RecuperaHP(VidaMax * Porcentagem / 100);
    return Porcentagem;
  }
  
  public void RecuperaHP(int quantidade){ // Restaura a vida do pokemon escolhido.
    if(HP + quantidade >= VidaMax) HP = VidaMax;
    else HP += quantidade;
  }

  public Boolean estaVivo(){ // Verifica se o pokemon está vivo.
    return HP > 0;
  }
}

class pokemonAgua extends Pokemon{ // Classe do pokemon agua que tem vantagem contra o pokemon fogo.
  public pokemonAgua(String nome, int nivel, int VidaMax, String tipo) {
		super(nome, nivel, VidaMax, tipo);
	}

  public void Atacar(Pokemon pokemon){
    if(pokemon instanceof pokemonFogo)pokemon.RecebeDano(15);
    else pokemon.RecebeDano(10);
  }

}

class pokemonFogo extends Pokemon{ // Classe do pokemon fogo que tem vantagem contra o pokemon planta.
  public pokemonFogo(String nome, int nivel, int VidaMax, String tipo) {
		super(nome, nivel, VidaMax, tipo);
	}

  public void Atacar(Pokemon pokemon){
    if(pokemon instanceof pokemonPlanta)pokemon.RecebeDano(15);
    else pokemon.RecebeDano(10);
  }
}

class pokemonPlanta extends Pokemon{ // Classe do pokemon planta que tem vantagem contra o pokemon agua.
  public pokemonPlanta(String nome, int nivel, int VidaMax, String tipo) {
		super(nome, nivel, VidaMax, tipo);
	}

  public void Atacar(Pokemon pokemon){
    if(pokemon instanceof pokemonAgua)pokemon.RecebeDano(15);
    else pokemon.RecebeDano(10);
  }
}


public class BatalhaPokemon{ // Classe onde ocorrerá o verdadeiro código do jogo.
  public static void main(String[] args){
    // Definindo os argumentos aleatórios.
    String[] nome = {"Pikachu", "Charmander", "Bulbassauro", "Squirtle", "Caterpie", "Rattata", "Pidgey", "Zubat", "Nidoran"};
    
    Pokemon pokemon1, pokemon2;
    int Tipo1 = Aleatorio.GeraNoLimite(3);
    int Tipo2 = Aleatorio.GeraNoLimite(3);
    while(Tipo2 == Tipo1)Tipo2 = Aleatorio.GeraNoLimite(3);
    
    // definindo o tipo dos pokemons e inicializando os mesmos.
    if(Tipo1 == 0)pokemon1 = new pokemonAgua(nome[Aleatorio.GeraNoLimite(9)], Aleatorio.GeraNoLimite(10)+1, Aleatorio.GeraNoLimite(901)+1, "Agua");
    else if(Tipo1 == 1)pokemon1 = new pokemonFogo(nome[Aleatorio.GeraNoLimite(9)], Aleatorio.GeraNoLimite(10)+1, Aleatorio.GeraNoLimite(901)+1, "Fogo");
    else pokemon1 = new pokemonPlanta(nome[Aleatorio.GeraNoLimite(9)], Aleatorio.GeraNoLimite(10)+1, Aleatorio.GeraNoLimite(901)+1, "Planta");

    if(Tipo2 == 0)pokemon2 = new pokemonAgua(nome[Aleatorio.GeraNoLimite(9)], Aleatorio.GeraNoLimite(10)+1, Aleatorio.GeraNoLimite(901)+1, "Agua");
    else if(Tipo2 == 1)pokemon2 = new pokemonFogo(nome[Aleatorio.GeraNoLimite(9)], Aleatorio.GeraNoLimite(10)+1, Aleatorio.GeraNoLimite(901)+1, "Fogo");
    else pokemon2 = new pokemonPlanta(nome[Aleatorio.GeraNoLimite(9)], Aleatorio.GeraNoLimite(10)+1, Aleatorio.GeraNoLimite(901)+1, "Planta");

    Pokemon Escolha = pokemon1;
    Pokemon Espera = pokemon2;
    Pokemon aux;
    int Cont = 0;
    int Tam2Colunas = 48; // Tamanho de uma coluna em uma tabela dividida em 2.
    int Tam1Coluna = 97; // Tamanho de uma coluna em uma tabela completa.

    // Batalha
    System.out.println();
    System.out.println("+-------------------------------------------------------------------------------------------------+");
    System.out.println("|" + centralizador.centralizaTexto("BATALHA POKEMON", Tam1Coluna) + "|");
    System.out.println("+------------------------------------------------+------------------------------------------------+");
    System.out.println("|" + centralizador.centralizaTexto("POKEMON ATACANDO", Tam2Colunas) + "|"  + centralizador.centralizaTexto("POKEMON DEFENDENDO", Tam2Colunas) + "|");
    System.out.println("|" + centralizador.centralizaTexto("NOME: " + Escolha.Nome, Tam2Colunas) + "|"  + centralizador.centralizaTexto("NOME: " + Espera.Nome, Tam2Colunas) + "|");
    System.out.println("|" + centralizador.centralizaTexto("NIVEL: " + Escolha.Nivel, Tam2Colunas) + "|"  + centralizador.centralizaTexto("NIVEL: " + Espera.Nivel, Tam2Colunas) + "|");
    System.out.println("|" + centralizador.centralizaTexto("TIPO: " + Escolha.Tipo, Tam2Colunas) + "|"  + centralizador.centralizaTexto("TIPO: " + Espera.Tipo, Tam2Colunas) + "|");
    System.out.println("|" + centralizador.centralizaTexto("HP INICIAL: " + Escolha.VidaMax, Tam2Colunas) + "|"  + centralizador.centralizaTexto("HP INICIAL: " + Espera.VidaMax, Tam2Colunas) + "|");
    System.out.println("+------------------------------------------------+------------------------------------------------+");

    while(pokemon1.estaVivo() && pokemon2.estaVivo()){
      if(Aleatorio.GeraNoLimite(100) < 12){ // Escolhe com base numa porcenagem, a chance que o pokemon da vez tem de ser curado aleatóriamente.
        System.out.println("+------------------------------------------------+------------------------------------------------+");
        System.out.println("|" + centralizador.centralizaTexto("Pokemon " + Escolha.Nome + " recebeu " + Escolha.RecuperaHP() + "% de cura", Tam1Coluna) + "|");
        System.out.println("+------------------------------------------------+------------------------------------------------+");

      }

      Escolha.Atacar(Espera); // Iniciado o ataque contra o oponente.

      // Exibe as informações durante o decorrer da Batalha.
      System.out.println("|" + centralizador.centralizaTexto("ROUND " + ++Cont, Tam1Coluna) + "|");
      System.out.println("+------------------------------------------------+------------------------------------------------+");
      System.out.println("|" + centralizador.centralizaTexto("POKEMON ATACANDO", Tam2Colunas) + "|"  + centralizador.centralizaTexto("POKEMON DEFENDENDO", Tam2Colunas) + "|");
      System.out.println("|" + centralizador.centralizaTexto("NOME: " + Escolha.Nome, Tam2Colunas) + "|"  + centralizador.centralizaTexto("NOME: " + Espera.Nome, Tam2Colunas) + "|");
      System.out.println("|" + centralizador.centralizaTexto("HP: " + Escolha.HP, Tam2Colunas) + "|"  + centralizador.centralizaTexto("HP: " + Espera.HP, Tam2Colunas) + "|");
      System.out.println("+------------------------------------------------+------------------------------------------------+");
      
      
      // Troca da vez.
      aux = Escolha;
      Escolha = Espera;
      Espera = aux;
    }

    // Troca final.
    aux = Escolha;
    Escolha = Espera;
    Espera = aux;

    // Exibe as informações ao fim da Batalha.
    System.out.println("|" + centralizador.centralizaTexto("BATALHA FINALIZADA", Tam1Coluna) + "|");
    System.out.println("+------------------------------------------------+------------------------------------------------+");
    System.out.println("|" + centralizador.centralizaTexto("POKEMON VENCEDOR", Tam2Colunas) + "|"  + centralizador.centralizaTexto("POKEMON PERDEDOR", Tam2Colunas) + "|");
    System.out.println("|" + centralizador.centralizaTexto("NOME: " + Escolha.Nome, Tam2Colunas) + "|"  + centralizador.centralizaTexto("NOME: " + Espera.Nome, Tam2Colunas) + "|");
    System.out.println("|" + centralizador.centralizaTexto("NIVEL: " + Escolha.Nivel, Tam2Colunas) + "|"  + centralizador.centralizaTexto("NIVEL: " + Espera.Nivel, Tam2Colunas) + "|");
    System.out.println("|" + centralizador.centralizaTexto("TIPO: " + Escolha.Tipo, Tam2Colunas) + "|"  + centralizador.centralizaTexto("TIPO: " + Espera.Tipo, Tam2Colunas) + "|");
    System.out.println("|" + centralizador.centralizaTexto("HP INICIAL: " + Escolha.VidaMax, Tam2Colunas) + "|"  + centralizador.centralizaTexto("HP INICIAL: " + Espera.VidaMax, Tam2Colunas) + "|");
    System.out.println("|" + centralizador.centralizaTexto("HP FINAL: " + Escolha.HP, Tam2Colunas) + "|"  + centralizador.centralizaTexto("HP FINAL: " + Espera.HP, Tam2Colunas) + "|");
    System.out.println("+------------------------------------------------+------------------------------------------------+");
    System.out.println("|" + centralizador.centralizaTexto("NÚMERO DE RONDS:" + ++Cont, Tam1Coluna) + "|");
    System.out.println("+------------------------------------------------+------------------------------------------------+");
    
  }
  
}