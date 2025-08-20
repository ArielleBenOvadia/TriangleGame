package org.example;

import javax.swing.*;
import java.awt.*;

public class TriangleGame extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TriangleGame::new);
    }

    public TriangleGame() {
        setTitle("Triangle Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(820, 860);
        setLocationRelativeTo(null);

        TrianglePanel panel = new TrianglePanel();

        JButton reset = new JButton("Reset");
        reset.addActionListener(e -> panel.reset());

        JPanel south = new JPanel(new FlowLayout(FlowLayout.LEFT));
        south.add(new JLabel("Click anywhere to place up to 3 points."));
        south.add(reset);

        add(panel, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);

        setVisible(true);
    }
}
