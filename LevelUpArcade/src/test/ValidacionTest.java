package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ValidacionTest {

    @Test
    public void testPrecioNegativo() {

        double precio = -10;

        assertTrue(precio < 0);
    }

    @Test
    public void testCampoVacio() {

        String nombre = "";

        assertTrue(nombre.isEmpty());
    }
}