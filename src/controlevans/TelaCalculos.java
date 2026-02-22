package controlevans;

import javax.swing.*;
import java.awt.*;

public class TelaCalculos extends JFrame {
    
    public TelaCalculos() {
        setTitle("Cálculos");
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JButton btnLucro = new JButton("Calcular Lucro Mensal");
        JButton btnViagens = new JButton("Viagens Necessárias");
        JButton btnCustos = new JButton("Custos Mensais");
        
        btnLucro.addActionListener(e -> calcularLucro());
        btnViagens.addActionListener(e -> calcularViagens());
        btnCustos.addActionListener(e -> calcularCustos());
        
        panel.add(btnLucro);
        panel.add(btnViagens);
        panel.add(btnCustos);
        
        add(panel);
    }
    
    private void calcularLucro() {
        String lucroViagem = JOptionPane.showInputDialog("Lucro por viagem (R$):");
        String viagensDia = JOptionPane.showInputDialog("Viagens por dia:");
        
        try {
            double lucro = Double.parseDouble(lucroViagem);
            int viagens = Integer.parseInt(viagensDia);
            double lucroMensal = lucro * viagens * 22; // 22 dias úteis
            
            JOptionPane.showMessageDialog(this, 
                "Lucro mensal estimado: R$ " + String.format("%.2f", lucroMensal));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Digite números válidos!");
        }
    }
    
    private void calcularViagens() {
        String meta = JOptionPane.showInputDialog("Meta de lucro (R$):");
        String lucroViagem = JOptionPane.showInputDialog("Lucro por viagem (R$):");
        
        try {
            double metaValor = Double.parseDouble(meta);
            double lucro = Double.parseDouble(lucroViagem);
            int viagens = (int) Math.ceil(metaValor / lucro);
            
            JOptionPane.showMessageDialog(this, 
                "Precisa de " + viagens + " viagens para atingir a meta");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Digite números válidos!");
        }
    }
    
    private void calcularCustos() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JTextField combustivel = new JTextField("1200");
        JTextField manutencao = new JTextField("300");
        JTextField salario = new JTextField("2500");
        JTextField outros = new JTextField("500");
        
        panel.add(new JLabel("Combustível:"));
        panel.add(combustivel);
        panel.add(new JLabel("Manutenção:"));
        panel.add(manutencao);
        panel.add(new JLabel("Salário:"));
        panel.add(salario);
        panel.add(new JLabel("Outros:"));
        panel.add(outros);
        
        int result = JOptionPane.showConfirmDialog(this, panel, 
            "Custos Mensais", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                double total = Double.parseDouble(combustivel.getText()) +
                             Double.parseDouble(manutencao.getText()) +
                             Double.parseDouble(salario.getText()) +
                             Double.parseDouble(outros.getText());
                
                JOptionPane.showMessageDialog(this, 
                    "Custo mensal total: R$ " + String.format("%.2f", total));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Digite números válidos!");
            }
        }
    }
}