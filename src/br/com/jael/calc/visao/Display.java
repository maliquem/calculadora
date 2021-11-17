package br.com.jael.calc.visao;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.jael.calc.modelo.Memoria;
import br.com.jael.calc.modelo.MemoriaObservador;

public class Display extends JPanel implements MemoriaObservador {

    private final JLabel label;

    public Display() {
        Memoria.getInstancia().adicionarObservador(this);
        label = new JLabel(Memoria.getInstancia().getTextoAtual());
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
        add(label);
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 45));
        setBackground(new Color(46, 49, 50));
    }

    @Override
    public void valorAlterado(String novoValor) {
        label.setText(novoValor);
    }

}
