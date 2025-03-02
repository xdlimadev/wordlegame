package src;

/**
  @author Bruno de Lima Melo
  @version 0.1.1 Alpha Beta Chicorita
 */

public class Main {

    public static void main(String[] args) {
        
//      Cargamos el array de palabras usando el método estático loadWords de WordleFileManager.
        String[] words = WordleFileManager.loadWords("src/filewords.txt");
        
//      Instanciamos el objeto WordleGame.
        WordleGame game = new WordleGame(words);
        
//      Ejecutamos el método start() de WordleGame
        game.start();
    }
}