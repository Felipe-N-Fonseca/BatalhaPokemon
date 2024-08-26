import java.util.Random;

class Aleatorio { // Gera um número aleatório de 0 até o limite - 1.
  public static int GeraNoLimite(int Limite) {
      Random random = new Random();
      return random.nextInt(Limite); 
  }
}

abstract class Pokemon {
  String Nome;
  int Nivel, HP, VidaMax;

  public Pokemon(String nome, int nivel, int VidaMax){ // Construtor.
    this.Nome = nome;
    this.Nivel = nivel;
    this.HP = VidaMax;
    this.VidaMax = VidaMax;
  }

  public abstract void Atacar(Pokemon pokemon); // Metodo que será implementado dentro das classes herdeiras.
  
  public void RecebeDano(int dano){ // Tira vida do pokemon atacado.
    if(HP - dano <= 0)HP = 0;
    else HP -= dano;
  }

  public void RecuperaHP(){  // Restaura uma porcentagem da vida do pokemon escolhido.
    int Porcentagem = Aleatorio.GeraNoLimite(100)+1; // Gera um número aleatório de 1 até 100 para definir a porcentagem de vida que será curada.
    RecuperaHP(VidaMax * Porcentagem / 100);
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
  public pokemonAgua(String nome, int nivel, int VidaMax) {
		super(nome, nivel, VidaMax);
	}

  public void Atacar(Pokemon pokemon){
    if(pokemon instanceof pokemonFogo)pokemon.RecebeDano(15);
    else pokemon.RecebeDano(10);
  }

}

class pokemonFogo extends Pokemon{ // Classe do pokemon fogo que tem vantagem contra o pokemon planta.
  public pokemonFogo(String nome, int nivel, int VidaMax) {
		super(nome, nivel, VidaMax);
	}

  public void Atacar(Pokemon pokemon){
    if(pokemon instanceof pokemonPlanta)pokemon.RecebeDano(15);
    else pokemon.RecebeDano(10);
  }
}

class pokemonPlanta extends Pokemon{ // Classe do pokemon planta que tem vantagem contra o pokemon agua.
  public pokemonPlanta(String nome, int nivel, int VidaMax) {
		super(nome, nivel, VidaMax);
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
    
    Pokemon pokemon1;
    Pokemon pokemon2;
    int Tipo1 = Aleatorio.GeraNoLimite(3);
    int Tipo2 = Aleatorio.GeraNoLimite(3);
    while(Tipo2 == Tipo1)Tipo2 = Aleatorio.GeraNoLimite(3);
    
    // definindo o tipo dos pokemons e inicializando os mesmos.
    if(Tipo1 == 0)pokemon1 = new pokemonAgua(nome[Aleatorio.GeraNoLimite(9)], Aleatorio.GeraNoLimite(10)+1, Aleatorio.GeraNoLimite(901)+1);
    else if(Tipo1 == 1)pokemon1 = new pokemonFogo(nome[Aleatorio.GeraNoLimite(9)], Aleatorio.GeraNoLimite(10)+1, Aleatorio.GeraNoLimite(901)+1);
    else pokemon1 = new pokemonPlanta(nome[Aleatorio.GeraNoLimite(9)], Aleatorio.GeraNoLimite(10)+1, Aleatorio.GeraNoLimite(901)+1);

    if(Tipo2 == 0)pokemon2 = new pokemonAgua(nome[Aleatorio.GeraNoLimite(9)], Aleatorio.GeraNoLimite(10)+1, Aleatorio.GeraNoLimite(901)+1);
    else if(Tipo2 == 1)pokemon2 = new pokemonFogo(nome[Aleatorio.GeraNoLimite(9)], Aleatorio.GeraNoLimite(10)+1, Aleatorio.GeraNoLimite(901)+1);
    else pokemon2 = new pokemonPlanta(nome[Aleatorio.GeraNoLimite(9)], Aleatorio.GeraNoLimite(10)+1, Aleatorio.GeraNoLimite(901)+1);

    Pokemon Escolha = pokemon1;
    Pokemon Espera = pokemon2;
    Pokemon aux;

    // Batalha
    while(pokemon1.estaVivo() && pokemon2.estaVivo()){
      if(Aleatorio.GeraNoLimite(100) < 12){ // Escolhe com base numa porcenagem, a chance que o pokemon da vez tem de ser curado aleatóriamente.
        Escolha.RecuperaHP();
      }

      Escolha.Atacar(Espera); // Iniciado o ataque contra o oponente.

      // Troca da vez
      aux = Escolha;
      Escolha = Espera;
      Espera = aux;
    }
    
  }
  
}