//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static final String STANDARD = "STANDARD";
    public static final String SPECIAL = "SPECIAL";
    public static final String REJECTED = "REJECTED";

    public static final double BULKY_VOLUME_LIMIT = 1_000_000.0; // cubic cm
    public static final double BULKY_DIMENSION_LIMIT = 150.0; // cm
    public static final double HEAVY_MASS_LIMIT = 20.0; // kg

    public static void main(String[] args) {
        System.out.println("Standard: " + sort(10, 10, 10, 10));
        System.out.println("Special (Bulky): " + sort(150, 10, 10, 10));
        System.out.println("Special (Heavy): " + sort(10, 10, 10, 20));
        System.out.println("Rejected: " + sort(150, 150, 150, 20));
    }

    public static String sort(double width, double height, double length, double mass) {
        validateInputs(width, height, length, mass);

        final boolean bulky = isBulky(width, height, length);
        final boolean heavy = isHeavy(mass);

        if (!bulky && !heavy) {
            return STANDARD;
        }
        if (bulky && heavy) {
            return REJECTED;
        }
        return SPECIAL;
    }

    public static void validateInputs(double width, double height, double length, double mass) {
        if (width <= 0) {
            throw new IllegalArgumentException("width should be greater than zero");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("height should be greater than zero");
        }
        if (length <= 0) {
            throw new IllegalArgumentException("length should be greater than zero");
        }
        if (mass <= 0) {
            throw new IllegalArgumentException("mass should be greater than zero");
        }
    }

    public static boolean isBulky(double width, double height, double length) {
        if (width >= BULKY_DIMENSION_LIMIT || height >= BULKY_DIMENSION_LIMIT || length >= BULKY_DIMENSION_LIMIT) {
            return true;
        }
        return volume(width, height, length) >= BULKY_VOLUME_LIMIT;
    }

    public static boolean isHeavy(double mass) {
        return mass >= HEAVY_MASS_LIMIT;
    }

    public static double volume(double width, double height, double length) {
        return width * height * length;
    }
}