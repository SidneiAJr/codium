using System;
    class personagem
    {

       //Atributos atributos são variaives  que minha classe usa
       // Constructor e uma função/metodo definido como os objetos
       // Metodos sao função que representam o que objeto dessa classe pode fazer
       public string nome;
       public int forca;
       public double roubovida;
       public int vida;
       public double velataque;

       public void atacar(){
         Console.WriteLine($"Personagem {nome} Ataca com forca de {forca}, e tem de velocidade de ataque {velataque}, com roubo de vida de {roubovida}");
       }
       public void Andar(){
        Console.WriteLine($"{nome} esta Andando em direcao ao monstro");
       }
       public personagem(string nomepersonagem,int forcaInicial,int VidaInicial, double velataque2,double roubo){
          nome = nomepersonagem;
          forca = forcaInicial;
          vida = VidaInicial;
          velataque = velataque2;
          roubovida = roubo;
       }
}

class inimigo{
    public string nome;
    public int forca;
    public int vida;
    public string tipo;
    public void atacar(personagem meuPersonagem){
         meuPersonagem.vida -= forca;
         Console.WriteLine($"O inimigo do tipo {tipo},ataca Personagem {meuPersonagem.nome} abaixando sua vida, para {meuPersonagem.vida} Pontos de vida");
       }
       public void Andar(){
       }
       public inimigo(string nomeinimigo, int forcaInicial, int vidaIni){
          tipo = "Monstro";
          nome = nomeinimigo;
          forca = forcaInicial;
          vidaIni= vida;
       }
}


class Jogo
{
            static void Main(string[] arg){
                //Criar um personagem
                personagem personagem1 = new personagem("Dama Vermelha",5,100,0.45,0.75);
                personagem1.atacar();
                personagem1.Andar();
                inimigo inimigo1 = new inimigo("Monstro",5,400);
                inimigo1.atacar(personagem1);
                inimigo1.Andar();

                


            }
}    
