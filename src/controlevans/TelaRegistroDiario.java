package controlevans;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TelaRegistroDiario extends JFrame {
    private Usuario usuario;
    private ArrayList<JTextField> camposValor;
    
    private final Color AZUL_PRINCIPAL = new Color(0, 70, 135);
    private final Color AZUL_MEDIO = new Color(0, 101, 179);
    private final Color AZUL_CLARO_FUNDO = new Color(245, 250, 255);
    private final Color VERDE_SUCESSO = new Color(0, 120, 86);
    private final Color VERMELHO_SAIR = new Color(180, 0, 0);
    
    public TelaRegistroDiario(Usuario usuario) {
        this.usuario = usuario;
        this.camposValor = new ArrayList<>();
        
        setTitle("Registrar Arrecadação - " + usuario.getNome());
        setSize(900, 700);
        setLocationRelativeTo(null);
        
        // Ícone da janela
        ImageIcon iconeJanela = Icones.getJanelaIcon(32);
        if (iconeJanela != null) {
            setIconImage(iconeJanela.getImage());
        }
        
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelPrincipal.setBackground(Color.WHITE);
        
        JPanel painelCabecalho = new JPanel(new BorderLayout());
        painelCabecalho.setBackground(Color.WHITE);
        
        JLabel titulo = new JLabel("REGISTRAR ARRECADAÇÃO DIÁRIA");
        if (Icones.getDinheiroIcon(30) != null) {
            titulo.setIcon(Icones.getDinheiroIcon(30));
            titulo.setIconTextGap(10);
        }
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(AZUL_PRINCIPAL);
        painelCabecalho.add(titulo, BorderLayout.WEST);
        
        JLabel data = new JLabel(new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()));
        if (Icones.getCalendarioIcon(20) != null) {
            data.setIcon(Icones.getCalendarioIcon(20));
            data.setIconTextGap(5);
        }
        data.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        data.setForeground(AZUL_MEDIO);
        painelCabecalho.add(data, BorderLayout.EAST);
        
        painelPrincipal.add(painelCabecalho, BorderLayout.NORTH);
        
        JPanel painelInstrucao = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelInstrucao.setBackground(AZUL_CLARO_FUNDO);
        painelInstrucao.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(AZUL_MEDIO, 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        
        JLabel instrucao = new JLabel("Digite o valor total arrecadado por cada van hoje:");
        if (Icones.getDinheiroIcon(20) != null) {
            instrucao.setIcon(Icones.getDinheiroIcon(20));
            instrucao.setIconTextGap(8);
        }
        instrucao.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        instrucao.setForeground(AZUL_PRINCIPAL);
        painelInstrucao.add(instrucao);
        
        JPanel painelVans = new JPanel();
        painelVans.setLayout(new BoxLayout(painelVans, BoxLayout.Y_AXIS));
        painelVans.setBackground(Color.WHITE);
        
        for (Van van : usuario.getVans()) {
            JPanel painelVan = new JPanel(new BorderLayout(10, 0));
            painelVan.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(15, 10, 15, 10)
            ));
            painelVan.setBackground(Color.WHITE);
            
            JPanel painelInfoVan = new JPanel(new GridLayout(2, 1));
            painelInfoVan.setBackground(Color.WHITE);
            
            JLabel lblVan = new JLabel("Van " + van.getIdentificacao() + " - " + van.getMotorista());
            if (Icones.getVanIcon(20) != null) {
                lblVan.setIcon(Icones.getVanIcon(20));
                lblVan.setIconTextGap(8);
            }
            lblVan.setFont(new Font("Segoe UI", Font.BOLD, 16));
            lblVan.setForeground(AZUL_PRINCIPAL);
            
            JLabel lblPlaca = new JLabel("Placa: " + van.getPlaca());
            if (Icones.getLocalIcon(16) != null) {
                lblPlaca.setIcon(Icones.getLocalIcon(16));
                lblPlaca.setIconTextGap(5);
            }
            lblPlaca.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            lblPlaca.setForeground(Color.GRAY);
            
            painelInfoVan.add(lblVan);
            painelInfoVan.add(lblPlaca);
            
            JPanel painelValor = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            painelValor.setBackground(Color.WHITE);
            
            JLabel lblValor = new JLabel("R$");
            lblValor.setFont(new Font("Segoe UI", Font.BOLD, 18));
            lblValor.setForeground(AZUL_PRINCIPAL);
            
            JTextField campoValor = new JTextField(10);
            campoValor.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            campoValor.setText(String.format("%.2f", van.getArrecadacaoHoje()));
            campoValor.setHorizontalAlignment(JTextField.RIGHT);
            campoValor.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AZUL_MEDIO),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
            ));
            
            camposValor.add(campoValor);
            
            painelValor.add(lblValor);
            painelValor.add(campoValor);
            
            painelVan.add(painelInfoVan, BorderLayout.WEST);
            painelVan.add(painelValor, BorderLayout.EAST);
            
            painelVans.add(painelVan);
        }
        
        JScrollPane scrollPane = new JScrollPane(painelVans);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);
        
        JPanel painelConteudo = new JPanel(new BorderLayout(0, 15));
        painelConteudo.setBackground(Color.WHITE);
        painelConteudo.add(painelInstrucao, BorderLayout.NORTH);
        painelConteudo.add(scrollPane, BorderLayout.CENTER);
        
        painelPrincipal.add(painelConteudo, BorderLayout.CENTER);
        
        // Botões SEM ÍCONES
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painelBotoes.setBackground(Color.WHITE);
        painelBotoes.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, AZUL_MEDIO));
        
        JButton btnSalvar = criarBotao("SALVAR REGISTROS", VERDE_SUCESSO);
        JButton btnCalcular = criarBotao("CALCULAR TOTAIS", AZUL_MEDIO);
        JButton btnFechar = criarBotao("FECHAR", VERMELHO_SAIR);
        
        btnSalvar.addActionListener(e -> salvarRegistros());
        btnCalcular.addActionListener(e -> calcularTotais());
        btnFechar.addActionListener(e -> dispose());
        
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCalcular);
        painelBotoes.add(btnFechar);
        
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
        
        add(painelPrincipal);
    }
    
    private JButton criarBotao(String texto, Color cor) {
        JButton botao = new JButton(texto);
        
        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setForeground(Color.WHITE);
        botao.setBackground(cor);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor);
            }
        });
        
        return botao;
    }
    
    private void salvarRegistros() {
        try {
            ArrayList<Van> vans = usuario.getVans();
            
            for (int i = 0; i < vans.size(); i++) {
                String texto = camposValor.get(i).getText().replace(",", ".");
                double valor = Double.parseDouble(texto);
                
                if (valor < 0) {
                    JOptionPane.showMessageDialog(this, 
                        "Valor não pode ser negativo!", "Erro", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                vans.get(i).setArrecadacaoHoje(valor);
            }
            
            JOptionPane.showMessageDialog(this, 
                "Registros salvos com sucesso!", "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Digite valores numéricos válidos!\nUse ponto para decimais (ex: 100.50)",
                "Erro de Formato",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void calcularTotais() {
        double totalArrecadado = usuario.calcularTotalArrecadadoDia();
        double totalMotoristas = usuario.calcularTotalPagarMotoristas();
        double lucroLiquido = usuario.calcularLucroLiquidoDia();
        int totalPassageiros = 0;
        
        for (Van van : usuario.getVans()) {
            totalPassageiros += van.estimarPassageiros();
        }
        
        String mensagem = String.format(
            "RESUMO DO DIA - %s\n\n" +
            "Total Arrecadado: R$ %.2f\n" +
            "Total a Pagar aos Motoristas (25%%): R$ %.2f\n" +
            "Lucro Líquido do Dia: R$ %.2f\n\n" +
            "Passageiros Estimados: %d\n" +
            "Valor Médio por Passageiro: R$ 3,50",
            usuario.getNome(),
            totalArrecadado,
            totalMotoristas,
            lucroLiquido,
            totalPassageiros
        );
        
        JOptionPane.showMessageDialog(this, mensagem, "Resumo do Dia", 
            JOptionPane.INFORMATION_MESSAGE);
    }
}