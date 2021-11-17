package br.com.jael.calc.visao;

import java.awt.*;
import javax.swing.JFrame;

public class Calculadora extends JFrame {

    public Calculadora() {

        orgazinarLayout();

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(325, 400);

    }

    private void orgazinarLayout() {

        setLayout(new BorderLayout());
        Display display = new Display();
        Teclado teclado = new Teclado();
        add(display, BorderLayout.NORTH);
        add(teclado, BorderLayout.CENTER);
        display.setPreferredSize(new Dimension(325, 80)
    }

    public static void main(String[] args) {
        new Calculadora();
    }

}
