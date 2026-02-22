package controlevans;

import javax.swing.*;
import java.net.URL;

public class Icones {
    
    public static ImageIcon carregarIcone(String nomeArquivo, int largura, int altura) {
        try {
            URL url = Icones.class.getResource(nomeArquivo);
            
            if (url != null) {
                ImageIcon icon = new ImageIcon(url);
                java.awt.Image imagem = icon.getImage();
                java.awt.Image novaImagem = imagem.getScaledInstance(largura, altura, java.awt.Image.SCALE_SMOOTH);
                return new ImageIcon(novaImagem);
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar: " + nomeArquivo);
        }
        return null;
    }
    
    public static ImageIcon getJanelaIcon(int tamanho) {
        return carregarIcone("janela.png", tamanho, tamanho);
    }
    
    public static ImageIcon getVanIcon(int tamanho) {
        return carregarIcone("van.png", tamanho, tamanho);
    }
    
    public static ImageIcon getUsuarioIcon(int tamanho) {
        return carregarIcone("usuario.png", tamanho, tamanho);
    }
    
    public static ImageIcon getCadeadoIcon(int tamanho) {
        return carregarIcone("cadeado.png", tamanho, tamanho);
    }
    
    public static ImageIcon getDinheiroIcon(int tamanho) {
        return carregarIcone("dinheiro.png", tamanho, tamanho);
    }
    
    public static ImageIcon getGraficoIcon(int tamanho) {
        return carregarIcone("grafico.png", tamanho, tamanho);
    }
    
    public static ImageIcon getSairIcon(int tamanho) {
        return carregarIcone("sair.png", tamanho, tamanho);
    }
    
    public static ImageIcon getInfoIcon(int tamanho) {
        return carregarIcone("info.png", tamanho, tamanho);
    }
    
    public static ImageIcon getLocalIcon(int tamanho) {
        return carregarIcone("local.png", tamanho, tamanho);
    }
    
    public static ImageIcon getCalendarioIcon(int tamanho) {
        return carregarIcone("calendario.png", tamanho, tamanho);
    }
    
    public static ImageIcon getPorcentagemIcon(int tamanho) {
        return carregarIcone("porcentagem.png", tamanho, tamanho);
    }
    
    public static ImageIcon getPessoasIcon(int tamanho) {
        return carregarIcone("pessoas.png", tamanho, tamanho);
    }
}