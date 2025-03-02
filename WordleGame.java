package src;

import java.util.Random;
import java.util.Scanner;

public class WordleGame {

    private static final int MAX_TRIES = 6;
    private static final int WORD_LENGTH = 5;
    private String[] wordArray;
    private String secretWord;
    private int remainingAttempts;
    private String[] triesHistory;
    private int historyIndex;

//  Constructor
    public WordleGame(String[] wordArray) {
        this.wordArray = wordArray;
        this.secretWord = selectRandomWord();
        this.remainingAttempts = MAX_TRIES;
        this.triesHistory = new String[MAX_TRIES];
        this.historyIndex = 0;
    }

//  Método para seleccionar una palabra random del array.
    private String selectRandomWord() {
        Random rand = new Random();
        return wordArray[rand.nextInt(wordArray.length)];
    }
    
//  En éste método se pasa por parametro un scanner, para que así podamos
//  comprobar que lo introducido por teclado por el jugador tenga 5 letras
//  en caso de no ser así, se avisa y se vuelve a pedir una entrada. Éste método devuelve un string.
    public String getUserInput(Scanner scanner) {
        String guess = null;
        do {
            System.out.print("Introduzca una palabra de 5 letras: ");
            guess = scanner.nextLine().trim().toUpperCase();
            if (guess.length() != WORD_LENGTH) {
                System.out.println("La palabra debe de tener 5 letras. Intenta de nuevo.");
            }
        } while (guess.length() != WORD_LENGTH);
        return guess;
    }
    
//  Método en el cual imprimimos los intentos restantes y las palabras del array triesHistory
    public void showTriesHistory() {
        System.out.println("Intentos: " + remainingAttempts);
        for (String words : triesHistory) {
            if (words != null) {
                System.out.println(WordleFeedBack.feedBackString(words, secretWord));
            }
        }
    }

//    Método principal con la lógica del juego.
    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido a Wordle!");
        System.out.println("Debes adivinar la palabra secreta, siendo ésta con un máximo de 5 letras. MUCHA SUERTE!");

//      Mientras intentos sea mayor de 0 el juego estará en funcionamiento.
        while (remainingAttempts > 0) {
            showTriesHistory();
            
//          Añadimos a la variable playerWord la palabra introducida por el jugador y previamente comprobada si es válida en el método getUserInput.
            String playerWord = getUserInput(scanner);

//          Comprobación de si la palabra del jugador es igual que la palabra secreta. En caso de que si, se termina con una WIN en toda la regla.
//          En caso de que no, restamos 1 al intento.
            if (playerWord.equals(secretWord)) {
                System.out.println("¡FELICIDADES, HAS ADIVINADO  LA PALABRA! ->" + WordleFeedBack.feedBackString(playerWord, secretWord));
                System.out.println("EN TAN SOLO " + (MAX_TRIES - (remainingAttempts - 1)) + " INTENTOS");
                return;
            } else {
                remainingAttempts--;
            }
//          Añadimos la palabra introducida por el jugador en el array triesHistory y añadimos +1 al indice del array en cada vuelta del while.
            triesHistory[historyIndex++] = playerWord;
        }

//      Al salir del bucle significa que el jugador no tiene mas intentos, con lo cual, el jugador ha pedido... y revelamos la palabra secreta.
        System.out.println("UPS, LA PALABRA SECRETA ERA: " + secretWord);

//      Llamamos al método estático saveHIstory para guardar el historial en un archivo. En éste caso, pasamos por parametros
//      el array y el indice del historial.
        WordleFileManager.saveHistory(triesHistory, historyIndex);
        
//      "Cerramos" el scanner
        scanner.close();
    }

}
