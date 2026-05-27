package util;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Clase para guardar logs del sistema.
 */
public class LoggerUtil {

    private static final String RUTA = "logs.txt";

    public static void log(String mensaje) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(RUTA, true))) {

            pw.println(
                "[" + LocalDateTime.now() + "] " + mensaje
            );

        } catch (Exception e) {
            System.out.println("Error escribiendo log");
        }
    }

	public static void logError(String string) {
		// TODO Auto-generated method stub
		
	}
}

