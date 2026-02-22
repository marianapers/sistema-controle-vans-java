package controlevans;

import javax.swing.*;
import java.awt.*;

public class TelaRelatorios extends JFrame {
    private Usuario usuario;
    
    private final Color AZUL_PRINCIPAL = new Color(0, 70, 135);
    private final Color AZUL_MEDIO = new Color(0, 101, 179);
    private final Color AZUL_CLARO_FUNDO = new Color(245, 250, 255);
    private final Color VERDE_POSITIVO = new Color(0, 120, 86);
    private final Color LARANJA_DESTAQUE = new Color(237, 108, 2);
    
    public TelaRelatorios(Usuario usuario) {
        this.usuario = usuario;
        
        setTitle("Relatórios - " + usuario.getNome());
        setSize(1200, 750);
        setLocationRelativeTo(null);
        
        // Ícone da janela
        ImageIcon iconeJanela = Icones.getJanelaIcon(32);
        if (iconeJanela != null) {
            setIconImage(iconeJanela.getImage());
        }
        
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelPrincipal.setBackground(Color.WHITE);
        
        JLabel titulo = new JLabel("RELATÓRIOS E ANÁLISES");
        if (Icones.getGraficoIcon(40) != null) {
            titulo.setIcon(Icones.getGraficoIcon(40));
            titulo.setIconTextGap(10);
        }
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(AZUL_PRINCIPAL);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        painelPrincipal.add(titulo, BorderLayout.NORTH);
        
        JTabbedPane abas = new JTabbedPane();
        abas.setFont(new Font("Segoe UI", Font.BOLD, 16));
        abas.setBackground(AZUL_CLARO_FUNDO);
        
        abas.addTab("Por Van", criarPainelPorVan());
        abas.addTab("Geral", criarPainelGeral());
        abas.addTab("Eficiência", criarPainelEficiencia());
        
        painelPrincipal.add(abas, BorderLayout.CENTER);
        
        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.setBackground(Color.WHITE);
        painelBotoes.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, AZUL_MEDIO));
        
        JButton btnFechar = criarBotaoFechar();
        painelBotoes.add(btnFechar);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
        
        add(painelPrincipal);
    }
    
    private JButton criarBotaoFechar() {
        JButton btnFechar = new JButton("FECHAR");
        
        btnFechar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnFechar.setForeground(Color.WHITE);
        btnFechar.setBackground(new Color(180, 0, 0));
        btnFechar.setFocusPainted(false);
        btnFechar.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
        btnFechar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnFechar.addActionListener(e -> dispose());
        
        btnFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFechar.setBackground(new Color(150, 0, 0));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFechar.setBackground(new Color(180, 0, 0));
            }
        });
        
        return btnFechar;
    }
    
    private JPanel criarPainelPorVan() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(Color.WHITE);
        
        for (Van van : usuario.getVans()) {
            JPanel painelVan = new JPanel(new GridLayout(6, 2, 10, 10));
            painelVan.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AZUL_MEDIO, 2),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
            ));
            painelVan.setBackground(Color.WHITE);
            
            Font fonteLabel = new Font("Segoe UI", Font.BOLD, 16);
            Font fonteValor = new Font("Segoe UI", Font.PLAIN, 16);
            
            JLabel lblVanTitulo = new JLabel("Van " + van.getIdentificacao() + " - " + van.getMotorista());
            if (Icones.getVanIcon(20) != null) {
                lblVanTitulo.setIcon(Icones.getVanIcon(20));
            }
            lblVanTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
            lblVanTitulo.setForeground(AZUL_PRINCIPAL);
            painelVan.add(lblVanTitulo);
            painelVan.add(new JLabel(""));
            
            JLabel lblId = new JLabel("Identificação:");
            lblId.setFont(fonteLabel);
            lblId.setForeground(AZUL_PRINCIPAL);
            painelVan.add(lblId);
            
            JLabel valId = new JLabel(van.getIdentificacao());
            valId.setFont(fonteValor);
            valId.setForeground(AZUL_MEDIO);
            painelVan.add(valId);
            
            JLabel lblPlaca = new JLabel("Placa:");
            lblPlaca.setFont(fonteLabel);
            lblPlaca.setForeground(AZUL_PRINCIPAL);
            painelVan.add(lblPlaca);
            
            JLabel valPlaca = new JLabel(van.getPlaca());
            valPlaca.setFont(fonteValor);
            valPlaca.setForeground(AZUL_MEDIO);
            painelVan.add(valPlaca);
            
            JLabel lblMotorista = new JLabel("Motorista:");
            lblMotorista.setFont(fonteLabel);
            lblMotorista.setForeground(AZUL_PRINCIPAL);
            painelVan.add(lblMotorista);
            
            JLabel valMotorista = new JLabel(van.getMotorista());
            valMotorista.setFont(fonteValor);
            valMotorista.setForeground(AZUL_MEDIO);
            painelVan.add(valMotorista);
            
            JLabel lblArrecadacao = new JLabel("Arrecadação Hoje:");
            lblArrecadacao.setFont(fonteLabel);
            lblArrecadacao.setForeground(AZUL_PRINCIPAL);
            painelVan.add(lblArrecadacao);
            
            JLabel valArrecadacao = new JLabel(String.format("R$ %.2f", van.getArrecadacaoHoje()));
            valArrecadacao.setFont(fonteValor);
            valArrecadacao.setForeground(AZUL_MEDIO);
            painelVan.add(valArrecadacao);
            
            JLabel lblPassageiros = new JLabel("Passageiros Estimados:");
            lblPassageiros.setFont(fonteLabel);
            lblPassageiros.setForeground(AZUL_PRINCIPAL);
            painelVan.add(lblPassageiros);
            
            JLabel valPassageiros = new JLabel(String.valueOf(van.estimarPassageiros()));
            valPassageiros.setFont(fonteValor);
            valPassageiros.setForeground(AZUL_MEDIO);
            painelVan.add(valPassageiros);
            
            painel.add(painelVan);
            painel.add(Box.createVerticalStrut(15));
        }
        
        JScrollPane scrollPane = new JScrollPane(painel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(Color.WHITE);
        container.add(scrollPane, BorderLayout.CENTER);
        
        return container;
    }
    
    private JPanel criarPainelGeral() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        double totalArrecadado = usuario.calcularTotalArrecadadoDia();
        double totalMotoristas = usuario.calcularTotalPagarMotoristas();
        double lucroLiquido = usuario.calcularLucroLiquidoDia();
        int totalPassageiros = 0;
        
        for (Van van : usuario.getVans()) {
            totalPassageiros += van.estimarPassageiros();
        }
        
        Font fonteLabel = new Font("Segoe UI", Font.BOLD, 18);
        Font fonteValor = new Font("Segoe UI", Font.PLAIN, 18);
        
        String[][] dados = {
            {"Total Arrecadado:", String.format("R$ %.2f", totalArrecadado)},
            {"Total a Pagar aos Motoristas:", String.format("R$ %.2f", totalMotoristas)},
            {"Lucro Líquido do Dia:", String.format("R$ %.2f", lucroLiquido)},
            {"Passageiros Estimados:", String.valueOf(totalPassageiros)},
            {"Média por Van:", String.format("R$ %.2f", totalArrecadado / usuario.getVans().size())},
            {"Vans Ativas:", String.valueOf(usuario.getVans().size())}
        };
        
        for (String[] dado : dados) {
            JLabel lblLabel = new JLabel(dado[0]);
            lblLabel.setFont(fonteLabel);
            lblLabel.setForeground(AZUL_PRINCIPAL);
            
            JLabel lblValor = new JLabel(dado[1]);
            lblValor.setFont(fonteValor);
            lblValor.setForeground(AZUL_MEDIO);
            
            gbc.gridx = 0;
            gbc.weightx = 0.7;
            gbc.anchor = GridBagConstraints.WEST;
            painel.add(lblLabel, gbc);
            
            gbc.gridx = 1;
            gbc.weightx = 0.3;
            gbc.anchor = GridBagConstraints.EAST;
            painel.add(lblValor, gbc);
            
            gbc.gridy++;
        }
        
        return painel;
    }
    
    private JPanel criarPainelEficiencia() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        double totalDistancia = 0;
        double totalTempo = 0;
        int totalPassageiros = 0;
        
        for (Van van : usuario.getVans()) {
            totalDistancia += van.calcularDistanciaTotal();
            totalTempo += van.calcularTempoTotalHoras();
            totalPassageiros += van.estimarPassageiros();
        }
        
        Font fonteLabel = new Font("Segoe UI", Font.BOLD, 18);
        Font fonteValor = new Font("Segoe UI", Font.PLAIN, 18);
        
        double mediaPassageiros = totalPassageiros > 0 ? 
            (double) totalPassageiros / usuario.getVans().size() : 0;
        
        double rendimentoKm = totalDistancia > 0 ? 
            usuario.calcularTotalArrecadadoDia() / totalDistancia : 0;
        
        double eficienciaHora = totalTempo > 0 ? 
            usuario.calcularLucroLiquidoDia() / totalTempo : 0;
        
        String[][] dados = {
            {"Distância Total Percorrida:", String.format("%.1f km", totalDistancia)},
            {"Tempo Total em Viagem:", String.format("%.1f horas", totalTempo)},
            {"Passageiros Transportados:", String.valueOf(totalPassageiros)},
            {"Média de Passageiros por Van:", String.format("%.1f", mediaPassageiros)},
            {"Rendimento por Km:", String.format("R$ %.2f/km", rendimentoKm)},
            {"Eficiência Financeira:", String.format("R$ %.2f/hora", eficienciaHora)}
        };
        
        for (String[] dado : dados) {
            JLabel lblLabel = new JLabel(dado[0]);
            lblLabel.setFont(fonteLabel);
            lblLabel.setForeground(AZUL_PRINCIPAL);
            
            JLabel lblValor = new JLabel(dado[1]);
            lblValor.setFont(fonteValor);
            lblValor.setForeground(LARANJA_DESTAQUE);
            
            gbc.gridx = 0;
            gbc.weightx = 0.7;
            gbc.anchor = GridBagConstraints.WEST;
            painel.add(lblLabel, gbc);
            
            gbc.gridx = 1;
            gbc.weightx = 0.3;
            gbc.anchor = GridBagConstraints.EAST;
            painel.add(lblValor, gbc);
            
            gbc.gridy++;
        }
        
        return painel;
    }
}