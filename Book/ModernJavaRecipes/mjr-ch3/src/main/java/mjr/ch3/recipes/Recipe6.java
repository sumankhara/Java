package mjr.ch3.recipes;

import java.util.Locale;
import java.util.stream.Stream;

//Converting strings to streams and back
public class Recipe6 {
    public static void main(String[] args) {
        boolean result = Stream.of("Madam, in Eden, I'm Adam",
                "malayalam",
                "Flee to me, remote elf!")
                .allMatch(Recipe6::isPalindrome);

        System.out.println(result);
    }

    public static boolean isPalindrome(String s) {
        String forward = s.toLowerCase().codePoints()
                .filter(Character::isLetterOrDigit)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();

        String reverse = new StringBuilder(forward).reverse().toString();

        return forward.equals(reverse);
    }
}
