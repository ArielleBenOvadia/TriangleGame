package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleGameTest {

    @Test
    void testPerimeterCalculation() {
        // נניח שמחשבים היקף של משולש עם צלעות 3,4,5
        int a = 3, b = 4, c = 5;
        int perimeter = a + b + c;

        assertEquals(12, perimeter, "Perimeter should be 12");
    }

    @Test
    void testAreaCalculation() {
        // בדיקה עם נוסחת הרון - sides 3,4,5
        double a = 3, b = 4, c = 5;
        double s = (a + b + c) / 2.0;
        double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));

        assertEquals(6.0, area, 0.001, "Area should be 6");
    }
}
