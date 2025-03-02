package src;

public class WordleFeedBack {

    private static final int WORD_LENGTH = 5;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";

    
//  Método que devuelve un string "reseteado" sin color con la concateniación de color + letra.
    private static String applyColor(String letter, String color) {
        return color + letter + ANSI_RESET;
    }

//  El método feedBackString setea un color a un carácter de la palabra
//  dependiendo de: si está en la posición correcta(verde), si está en la
//  palabra pero no en su posición(amarillo) y si no forma parte de la palabra (rojo)
    public static String feedBackString(String guess, String secretWord) {
        
        // Nueva instancia de StringBuilder para un mejor manejo de Strings
        StringBuilder feedback = new StringBuilder();
        
        // Bucle para ir pasando carácter a carácter de la palabra e ir comprobando si son iguales o no.
        for (int i = 0; i < WORD_LENGTH; i++) {
            char letter = guess.charAt(i); 
            if (letter == secretWord.charAt(i)) {
                feedback.append(applyColor(Character.toString(letter), ANSI_GREEN));
            } else if(secretWord.contains(Character.toString(letter))) {
                feedback.append(applyColor(Character.toString(letter), ANSI_YELLOW));
            } else {
                feedback.append(applyColor(Character.toString(letter), ANSI_RED));
            }
        }
        //  devolvemos el string con el seteo de colores.
        return feedback.toString();
    }
}
