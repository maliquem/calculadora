package br.com.jael.calc.visao;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Botao extends JButton {

    public Botao(String texto, Color cor) {
        setText(texto);
        setFont(new Font("Bahnschrift", Font.PLAIN, 30));
        setOpaque(true);
        setBackground(cor);
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(46, 49, 50)));
    }

}
