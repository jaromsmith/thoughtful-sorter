public class MainTest {

    public static void main(String[] args) {
        System.out.println("--- Starting Thoughtful AI Package Sorter Tests ---");

        try {
            // 1. Parameterized-style tests for categories
            testSort(10, 10, 10, 10, "STANDARD", "Small and light");
            testSort(200, 10, 10, 10, "SPECIAL", "Bulky (dimension), not heavy");
            testSort(100, 100, 100, 10, "SPECIAL", "Bulky (volume), not heavy");
            testSort(10, 10, 10, 25, "SPECIAL", "Not bulky, heavy");
            testSort(200, 100, 100, 25, "REJECTED", "Bulky and heavy");

            // 2. Boundary tests
            testSort(150, 10, 10, 10, "SPECIAL", "Boundary: Dimension at 150cm");
            testSort(100, 100, 100, 10, "SPECIAL", "Boundary: Volume at 1,000,000cm³");
            testSort(10, 10, 10, 20, "SPECIAL", "Boundary: Mass at 20kg");

            // 3. Exception tests (Non-positive inputs)
            testException(0, 10, 10, 10, "Width 0");
            testException(10, -1, 10, 10, "Height -1");
            testException(10, 10, 0, 10, "Length 0");
            testException(10, 10, 10, -5, "Mass -5");

            System.out.println("\n✅ SUCCESS: All tests passed!");
        } catch (Exception e) {
            System.err.println("\n❌ FAILURE: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void testSort(double w, double h, double l, double m, String expected, String desc) {
        String actual = Main.sort(w, h, l, m);
        if (!expected.equals(actual)) {
            throw new RuntimeException(desc + " Failed: Expected " + expected + " but got " + actual);
        }
        System.out.println("Pass: " + desc);
    }

    private static void testException(double w, double h, double l, double m, String desc) {
        try {
            Main.sort(w, h, l, m);
            throw new RuntimeException("Validation Failed for " + desc + ": No exception thrown.");
        } catch (IllegalArgumentException e) {
            System.out.println("Pass: Caught expected exception for " + desc);
        }
    }
}