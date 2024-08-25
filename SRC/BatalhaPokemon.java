import java.util.Random;

class Aleatorio {
  public static int GeraAleatorio() {
      Random random = new Random();
      int limiteSuperior = 100; 
      return random.nextInt(limiteSuperior);
  }
  
  public static int GeraNoLimite(int Limite) {
      Random random = new Random();
      return random.nextInt(Limite);
  }
}

abstract class Pokemon {
  String Nome;
  int Nivel, HP, VidaMax;

  public Pokemon(String nome, int nivel, int VidaMax){
    this.Nome = nome;
    this.Nivel = nivel;
    this.HP = VidaMax;
    this.VidaMax = VidaMax;
  }

  public abstract void Atacar(Pokemon pokemon);
  
  public void RecebeDano(int dano){
    if(HP - dano <= 0)HP = 0;
    else HP -= dano;
  }

  public void RecuperaHP(){
    int Porcentagem = Aleatorio.GeraAleatorio();
    RecuperaHP(VidaMax * Porcentagem / 100);
  }
  
  public void RecuperaHP(int quantidade){
    if(HP + quantidade >= VidaMax) HP = VidaMax;
    else HP += quantidade;
  }

  public Boolean estaVivo(){
    return HP > 0;
  }
}

class pokemonAgua extends Pokemon{
  public pokemonAgua(String nome, int nivel, int VidaMax) {
		super(nome, nivel, VidaMax);
	}

  public void Atacar(Pokemon pokemon){
    if(pokemon instanceof pokemonFogo)pokemon.RecebeDano(15);
    else pokemon.RecebeDano(10);
  }

}

class pokemonFogo extends Pokemon{
  public pokemonFogo(String nome, int nivel, int VidaMax) {
		super(nome, nivel, VidaMax);
	}

  public void Atacar(Pokemon pokemon){
    if(pokemon instanceof pokemonPlanta)pokemon.RecebeDano(15);
    else pokemon.RecebeDano(10);
  }
}

class pokemonPlanta extends Pokemon{
  public pokemonPlanta(String nome, int nivel, int VidaMax) {
		super(nome, nivel, VidaMax);
	}

  public void Atacar(Pokemon pokemon){
    if(pokemon instanceof pokemonAgua)pokemon.RecebeDano(15);
    else pokemon.RecebeDano(10);
  }
}

public class BatalhaPokemon{
  public static void main(String[] args){
    // Definindo os argumentos aleatórios
    String[] nome = {"Pikachu", "Charmander", "Bulbassauro", "Squirtle", "Caterpie", "Rattata", "Pidgey", "Zubat", "Nidoran"};
    int[] nivel = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int[] VidaMax = {100, 200, 150, 80, 450, 300, 250, 400, 500};
    int Acao;
    
    Pokemon pokemon1;
    Pokemon pokemon2;
    int Tipo1 = Aleatorio.GeraNoLimite(3);
    int Tipo2 = Aleatorio.GeraNoLimite(3);
    while(Tipo2 == Tipo1)Tipo2 = Aleatorio.GeraNoLimite(3);
    
    // definindo o tipo dos pokemons
    if(Tipo1 == 0)pokemon1 = new pokemonAgua(nome[Aleatorio.GeraNoLimite(9)], nivel[Aleatorio.GeraNoLimite(9)], VidaMax[Aleatorio.GeraNoLimite(9)]);
    else if(Tipo1 == 1)pokemon1 = new pokemonFogo(nome[Aleatorio.GeraNoLimite(9)], nivel[Aleatorio.GeraNoLimite(9)], VidaMax[Aleatorio.GeraNoLimite(9)]);
    else pokemon1 = new pokemonPlanta(nome[Aleatorio.GeraNoLimite(9)], nivel[Aleatorio.GeraNoLimite(9)], VidaMax[Aleatorio.GeraNoLimite(9)]);

    if(Tipo2 == 0)pokemon2 = new pokemonAgua(nome[Aleatorio.GeraNoLimite(9)], nivel[Aleatorio.GeraNoLimite(9)], VidaMax[Aleatorio.GeraNoLimite(9)]);
    else if(Tipo2 == 1)pokemon2 = new pokemonFogo(nome[Aleatorio.GeraNoLimite(9)], nivel[Aleatorio.GeraNoLimite(9)], VidaMax[Aleatorio.GeraNoLimite(9)]);
    else pokemon2 = new pokemonPlanta(nome[Aleatorio.GeraNoLimite(9)], nivel[Aleatorio.GeraNoLimite(9)], VidaMax[Aleatorio.GeraNoLimite(9)]);

    // Batalha
    while(pokemon1.estaVivo() && pokemon2.estaVivo()){
      Pokemon Escolha = pokemon1;
      Pokemon Espera = pokemon2;
      
      Acao = Aleatorio.GeraNoLimite(2);
      if(Acao == 0)Escolha.Atacar(Espera);
      else pokemon1.RecuperaHP();
    }
    
  }
  
}