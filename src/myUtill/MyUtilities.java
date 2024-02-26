package myUtill;
// Alon Livne (ID: 208688762)

import java.awt.Color;
import java.util.Random;

/**
 * A utility class providing various methods for working with doubles, colors, and arrays.
 */
public class MyUtilities {
    // Threshold for comparing double values
    private static final double THRESHOLD = 1e-10;

    /**
     * Checks if two double values are approximately equal within the defined threshold.
     *
     * @param num1 The first double value.
     * @param num2 The second double value.
     * @return True if the values are approximately equal, false otherwise.
     */
    public static boolean isEqual(double num1, double num2) {
        return Math.abs(num1 - num2) < THRESHOLD;
    }

    /**
     * Checks if the first double value is less than the second within the defined threshold.
     *
     * @param num1 The first double value.
     * @param num2 The second double value.
     * @return True if the first value is less than the second, false otherwise.
     */
    public static boolean lessThan(double num1, double num2) {
        return num1 - num2 < -THRESHOLD;
    }

    /**
     * Checks if the first double value is less than or equal to the second within the defined threshold.
     *
     * @param num1 The first double value.
     * @param num2 The second double value.
     * @return True if the first value is less than or equal to the second, false otherwise.
     */
    public static boolean lessThanOrEqual(double num1, double num2) {
        return num1 - num2 <= THRESHOLD;
    }

    /**
     * Checks if the first double value is greater than the second within the defined threshold.
     *
     * @param num1 The first double value.
     * @param num2 The second double value.
     * @return True if the first value is greater than the second, false otherwise.
     */
    public static boolean greaterThan(double num1, double num2) {
        return num1 - num2 > THRESHOLD;
    }

    /**
     * Checks if the first double value is greater than or equal to the second within the defined threshold.
     *
     * @param num1 The first double value.
     * @param num2 The second double value.
     * @return True if the first value is greater than or equal to the second, false otherwise.
     */
    public static boolean greaterThanOrEqual(double num1, double num2) {
        return num1 - num2 >= -THRESHOLD;
    }


    /**
     * Generates a random color (Blue, Red, or Black).
     *
     * @return A randomly selected Color object.
     */
    public static Color getRandomColor() {
        // Generate a random index to select a color
        Random random = new Random();
        int randomIndex = random.nextInt(3);
        if (randomIndex == 0) {
            return Color.BLUE;
        } else if (randomIndex == 1) {
            return Color.RED;
        } else {
            return Color.BLACK;
        }
    }
}

