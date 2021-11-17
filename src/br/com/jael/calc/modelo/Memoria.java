package br.com.jael.calc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

    private enum TipoComando {
        ZERAR, NUMERO, DIV, MULT, SUB, SOMA, IGUAL, VIRGULA, SIMBOLO;
    }

    private static final Memoria instancia = new Memoria();

    private final List<MemoriaObservador> observadores = new ArrayList<>();

    private TipoComando ultimaOperacao = null;
    private TipoComando peultimaOperacao = null;
    private boolean substituir = false;
    private String textoAtual = "";
    private String textoBuffer = "";
    private double resultado = 0.0;
    private double fatorIgual = 0.0;
    private int igualCount = 0;

    private Memoria() {

    }

    public static Memoria getInstancia() {
        return instancia;
    }

    public void adicionarObservador(MemoriaObservador observador) {
        observadores.add(observador);
    }

    public String getTextoAtual() {
        return textoAtual.isEmpty() ? "0" : textoAtual;
    }

    public void processarComando(String valor) {

        TipoComando tipoComando = detectarTipoComando(valor);

        if (tipoComando == null) {
            return;
        } else if (tipoComando == TipoComando.ZERAR) {
            textoAtual = "";
            textoBuffer = "";
            substituir = false;
            ultimaOperacao = null;
        } else if (tipoComando == TipoComando.NUMERO || tipoComando == TipoComando.VIRGULA) {
            textoAtual = substituir ? valor : textoAtual + valor;
            substituir = false;
        } else {
            substituir = true;
            textoAtual = obterResultadoOperacao();
            textoBuffer = textoAtual;
            ultimaOperacao = tipoComando;
        }

        observadores.forEach(o -> o.valorAlterado(getTextoAtual()));
    }

    private String obterResultadoOperacao() {
        if (ultimaOperacao == TipoComando.IGUAL) {
            if (igualCount == 0) {
                igualCount++;
            } else {
                if (peultimaOperacao == TipoComando.SOMA) {
                    resultado = resultado + fatorIgual;
                    igualCount++;
                } else if (peultimaOperacao == TipoComando.SUB) {
                    resultado = resultado - fatorIgual;
                    igualCount++;
                } else if (peultimaOperacao == TipoComando.MULT) {
                    resultado = resultado * fatorIgual;
                    igualCount++;
                } else if (peultimaOperacao == TipoComando.DIV) {
                    resultado = resultado / fatorIgual;
                    igualCount++;
                }
            }
        }

        if (ultimaOperacao == null) {
            return textoAtual;
        }

        double numeroBuffer = Double.parseDouble(textoBuffer.replace(",", "."));
        double numeroAtual = Double.parseDouble(textoAtual.replace(",", "."));

        if (ultimaOperacao == TipoComando.SOMA) {
            resultado = numeroBuffer + numeroAtual;
            peultimaOperacao = ultimaOperacao;
            fatorIgual = numeroAtual;
            igualCount = 0;
        } else if (ultimaOperacao == TipoComando.SUB) {
            resultado = numeroBuffer - numeroAtual;
            peultimaOperacao = ultimaOperacao;
            fatorIgual = numeroAtual;
            igualCount = 0;
        } else if (ultimaOperacao == TipoComando.MULT) {
            resultado = numeroBuffer * numeroAtual;
            peultimaOperacao = ultimaOperacao;
            fatorIgual = numeroAtual;
            igualCount = 0;
        } else if (ultimaOperacao == TipoComando.DIV) {
            resultado = numeroBuffer / numeroAtual;
            peultimaOperacao = ultimaOperacao;
            fatorIgual = numeroAtual;
            igualCount = 0;
        } else if (ultimaOperacao == TipoComando.SIMBOLO) {
            if (numeroBuffer > 0) {
                resultado = numeroBuffer - (numeroBuffer * 2);
            } else {
                resultado = numeroBuffer + (numeroBuffer * 2);
            }
        }

        String resultadoString = Double.toString(resultado).replace(".", ",");
        boolean inteiro = resultadoString.endsWith(",0");
        return inteiro ? resultadoString.replace(",0", "") : resultadoString;
    }

    private TipoComando detectarTipoComando(String valor) {

        if (textoAtual.isEmpty() && valor.equals("0")) {
            return null;
        }

        try {
            Integer.parseInt(valor);
            return TipoComando.NUMERO;
        } catch (NumberFormatException e) {
            if ("AC".equals(valor)) {
                return TipoComando.ZERAR;
            } else if ("/".equals(valor)) {
                return TipoComando.DIV;
            } else if ("*".equals(valor)) {
                return TipoComando.MULT;
            } else if ("+".equals(valor)) {
                return TipoComando.SOMA;
            } else if ("-".equals(valor)) {
                return TipoComando.SUB;
            } else if ("=".equals(valor)) {
                return TipoComando.IGUAL;
            } else if (",".equals(valor) && !textoAtual.contains(",")) {
                return TipoComando.VIRGULA;
            } else if ("±".equals(valor)) {
                return TipoComando.SIMBOLO;
            }
        }

        return null;
    }

}
