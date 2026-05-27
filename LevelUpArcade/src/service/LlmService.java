package service;

import config.ApiConfig;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LlmService {

    private static final String API_URL =
            "https://openrouter.ai/api/v1/chat/completions";

    public String enviarPrompt(String prompt) {

        try {

            // =========================
            // CONEXIÓN HTTP
            // =========================
            URL url = new URL(API_URL);

            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization",
                    "Bearer " + ApiConfig.API_KEY);

            conn.setRequestProperty("Content-Type",
                    "application/json");

            conn.setDoOutput(true);

            // =========================
            // JSON REQUEST
            // =========================
            String body = """
            {
              "model": "openai/gpt-3.5-turbo",
              "messages": [
                {
                  "role": "user",
                  "content": "%s"
                }
              ]
            }
            """.formatted(prompt);

            OutputStream os = conn.getOutputStream();
            os.write(body.getBytes("utf-8"));
            os.close();

            // =========================
            // RESPUESTA HTTP
            // =========================
            int code = conn.getResponseCode();

            InputStream is;

            if (code >= 200 && code < 300) {
                is = conn.getInputStream();
            } else {
                is = conn.getErrorStream();
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is, "utf-8")
            );

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            br.close();

            // =========================
            // EXTRAER TEXTO IA
            // =========================
            return extraerContenido(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return "Error IA: " + e.getMessage();
        }
    }

    // =========================
    // PARSEO SIMPLE JSON
    // =========================
    private String extraerContenido(String json) {

        try {
            String key = "\"content\":\"";

            int start = json.indexOf(key);

            if (start == -1) {
                return "No se pudo leer respuesta IA";
            }

            start += key.length();

            int end = json.indexOf("\"", start);

            if (end == -1) {
                return "No se pudo leer respuesta IA";
            }

            return json.substring(start, end);

        } catch (Exception e) {
            return "Error parseando respuesta IA";
        }
    }
}