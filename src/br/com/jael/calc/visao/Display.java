package br.com.jael.calc.visao;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.jael.calc.modelo.Memoria;

public class Display extends JPanel {

    private final JLabel label;

    public Display() {
        label = new JLabel(Memoria.getInstancia().getTextoAtual());
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
        add(label);
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 45));
        setBackground(new Color(46, 49, 50));
    }

}
