package controller;
import service.LlmService;
import view.IAView;

public class IAController {

    private IAView view;
    private LlmService ia;

    public IAController(IAView view) {

        this.view = view;
        this.ia = new LlmService();

        initEvents();
    }

    // =========================
    // EVENTOS
    // =========================

    private void initEvents() {

        view.getBtnEnviar().addActionListener(e -> enviarPregunta());

        view.getBtnLimpiar().addActionListener(e ->
                view.limpiarChat()
        );

        view.getTxtPregunta().addActionListener(e ->
                enviarPregunta()
        );
    }

    // =========================
    // IA
    // =========================

    private void enviarPregunta() {

        String pregunta = view.getTxtPregunta().getText().trim();

        if (pregunta.isEmpty()) {
            return;
        }

        view.agregarMensajeUsuario(pregunta);

        view.getTxtPregunta().setText("");

        // Mensaje temporal
        view.agregarMensajeIA("Pensando...");

        // Hilo para no congelar Swing
        new Thread(() -> {

            try {

                String respuesta = ia.enviarPrompt(pregunta);

                javax.swing.SwingUtilities.invokeLater(() -> {

                    // eliminar "Pensando..."
                    view.agregarMensajeIA(respuesta);
                });

            } catch (Exception e) {

                javax.swing.SwingUtilities.invokeLater(() -> {

                    view.agregarMensajeIA(
                            "Error al conectar con la IA"
                    );
                });
            }

        }).start();
    }
}