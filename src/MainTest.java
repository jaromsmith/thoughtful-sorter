import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @ParameterizedTest
    @DisplayName("Should correctly categorize packages based on rules")
    @CsvSource({
            "10, 10, 10, 10, STANDARD",    // Small and light
            "200, 10, 10, 10, SPECIAL",   // Bulky (dimension), not heavy
            "100, 100, 100, 10, SPECIAL", // Bulky (volume), not heavy
            "10, 10, 10, 25, SPECIAL",    // Not bulky, heavy
            "200, 100, 100, 25, REJECTED" // Bulky and heavy
    })
    void testSortCategories(double w, double h, double l, double m, String expected) {
        assertEquals(expected, Main.sort(w, h, l, m));
    }

    @Test
    @DisplayName("Boundary: Exactly at the bulky dimension limit (150cm)")
    void testDimensionBoundary() {
        assertEquals("SPECIAL", Main.sort(150, 10, 10, 10));
    }

    @Test
    @DisplayName("Boundary: Exactly at the bulky volume limit (1,000,000cm³)")
    void testVolumeBoundary() {
        // 100 * 100 * 100 = 1,000,000
        assertEquals("SPECIAL", Main.sort(100, 100, 100, 10));
    }

    @Test
    @DisplayName("Boundary: Exactly at the heavy mass limit (20kg)")
    void testMassBoundary() {
        assertEquals("SPECIAL", Main.sort(10, 10, 10, 20));
    }

    @ParameterizedTest
    @DisplayName("Should throw exception for non-positive inputs")
    @CsvSource({
            "0, 10, 10, 10",
            "10, -1, 10, 10",
            "10, 10, 0, 10",
            "10, 10, 10, -5"
    })
    void testInvalidInputs(double w, double h, double l, double m) {
        assertThrows(IllegalArgumentException.class, () -> Main.sort(w, h, l, m));
    }
}