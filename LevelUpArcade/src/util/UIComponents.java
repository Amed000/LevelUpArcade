
package util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class UIComponents {

    // =========================
    // HEADER
    // =========================

    public static JPanel header(String title) {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panel.setBackground(UITheme.DARK);
        panel.setBorder(new EmptyBorder(15,15,15,15));

        JLabel lbl = new JLabel(title);

        lbl.setForeground(Color.WHITE);
        lbl.setFont(UITheme.TITLE);

        panel.add(lbl);

        return panel;
    }

    // =========================
    // BOTONES
    // =========================

    public static JButton button(String text, Color color) {

        JButton btn = new JButton(text);

        btn.setBackground(color);
        btn.setForeground(Color.WHITE);

        btn.setFont(UITheme.BUTTON);

        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.setOpaque(true);
        btn.setContentAreaFilled(true);

        btn.setPreferredSize(new Dimension(150,40));

        return btn;
    }

    // =========================
    // TABLAS
    // =========================

    public static void styleTable(JTable table) {

        table.setRowHeight(30);

        table.setFont(UITheme.TEXT);

        table.setSelectionBackground(UITheme.PRIMARY);
        table.setGridColor(Color.LIGHT_GRAY);

        JTableHeader header = table.getTableHeader();

        header.setBackground(UITheme.DARK);
        header.setForeground(Color.WHITE);

        header.setFont(UITheme.BUTTON);

        table.setBackground(Color.WHITE);
    }

    // =========================
    // CHAT IA
    // =========================

    public static JTextArea createChatArea() {

        JTextArea area = new JTextArea();

        area.setEditable(false);

        area.setFont(UITheme.TEXT);

        area.setBackground(Color.WHITE);

        area.setLineWrap(true);

        area.setWrapStyleWord(true);

        area.setMargin(new Insets(10,10,10,10));

        return area;
    }
}