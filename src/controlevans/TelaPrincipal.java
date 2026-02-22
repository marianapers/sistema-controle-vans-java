package controlevans;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {
    private Usuario usuarioLogado;
    
    private final Color AZUL_PRINCIPAL = new Color(0, 70, 135);
    private final Color AZUL_MEDIO = new Color(0, 101, 179);
    private final Color AZUL_CLARO_FUNDO = new Color(240, 248, 255);
    private final Color VERDE_SUCESSO = new Color(0, 120, 86);
    private final Color LARANJA_DESTAQUE = new Color(237, 108, 2);
    private final Color VERMELHO_SAIR = new Color(180, 0, 0);
    
    public TelaPrincipal(Usuario usuario) {
        this.usuarioLogado = usuario;
        
        setTitle("Controle de Vans - " + usuario.getNome());
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Ícone da janela
        ImageIcon iconeJanela = Icones.getJanelaIcon(32);
        if (iconeJanela != null) {
            setIconImage(iconeJanela.getImage());
        }
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menuAjuda = new JMenu("Ajuda");
        menuAjuda.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JMenuItem itemSobre = new JMenuItem("Sobre o Sistema");
        if (Icones.getInfoIcon(16) != null) {
            itemSobre.setIcon(Icones.getInfoIcon(16));
        }
        itemSobre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        itemSobre.addActionListener(e -> mostrarSobre());
        menuAjuda.add(itemSobre);
        
        menuBar.add(menuAjuda);
        setJMenuBar(menuBar);
        
        JPanel painelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, AZUL_CLARO_FUNDO, w, 0, Color.WHITE);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        painelPrincipal.setLayout(new BorderLayout(15, 15));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel painelCabecalho = new JPanel(new BorderLayout());
        painelCabecalho.setOpaque(false);
        
        JLabel titulo = new JLabel("SISTEMA DE CONTROLE DE VANS");
        if (Icones.getVanIcon(30) != null) {
            titulo.setIcon(Icones.getVanIcon(30));
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
        
        JPanel painelConfig = new JPanel(new GridLayout(1, 3, 15, 0));
        painelConfig.setOpaque(false);
        painelConfig.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, AZUL_MEDIO),
            BorderFactory.createEmptyBorder(10, 0, 15, 0)
        ));
        
        String[][] configs = {
            {"ROTA", "Travessão x Centro"},
            {"PASSAGEM", "R$ 3,50"},
            {"COMISSÃO", "25% motorista"}
        };
        
        for (String[] config : configs) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AZUL_MEDIO, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            
            JLabel lblTitulo = new JLabel(config[0]);
            if (config[0].equals("ROTA") && Icones.getLocalIcon(16) != null) {
                lblTitulo.setIcon(Icones.getLocalIcon(16));
            } else if (config[0].equals("PASSAGEM") && Icones.getDinheiroIcon(16) != null) {
                lblTitulo.setIcon(Icones.getDinheiroIcon(16));
            } else if (config[0].equals("COMISSÃO") && Icones.getPorcentagemIcon(16) != null) {
                lblTitulo.setIcon(Icones.getPorcentagemIcon(16));
            }
            lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
            lblTitulo.setForeground(AZUL_PRINCIPAL);
            
            JLabel lblValor = new JLabel(config[1]);
            lblValor.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            lblValor.setForeground(AZUL_MEDIO);
            
            card.add(lblTitulo, BorderLayout.NORTH);
            card.add(lblValor, BorderLayout.CENTER);
            painelConfig.add(card);
        }
        
        painelPrincipal.add(painelConfig, BorderLayout.NORTH);
        
        JPanel painelBemVindo = new JPanel();
        painelBemVindo.setOpaque(false);
        painelBemVindo.setLayout(new BoxLayout(painelBemVindo, BoxLayout.Y_AXIS));
        
        JLabel iconeUser = new JLabel();
        if (Icones.getUsuarioIcon(80) != null) {
            iconeUser.setIcon(Icones.getUsuarioIcon(80));
        } else {
            iconeUser.setText("USUÁRIO");
            iconeUser.setFont(new Font("Segoe UI", Font.BOLD, 24));
        }
        iconeUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblBemVindo = new JLabel("Bem-vindo, " + usuario.getNome() + "!");
        lblBemVindo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblBemVindo.setForeground(AZUL_PRINCIPAL);
        lblBemVindo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblInfo = new JLabel("Você possui " + usuario.getVans().size() + " vans cadastradas");
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblInfo.setForeground(AZUL_MEDIO);
        lblInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel painelStats = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        painelStats.setOpaque(false);
        painelStats.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        String[][] stats = {
            {"Total Vans", String.valueOf(usuario.getVans().size())},
            {"Motoristas", String.valueOf(usuario.getVans().size())},
            {"Capacidade Total", String.valueOf(usuario.getVans().size() * 15)},
            {"Rota", "Travessão"}
        };
        
        for (String[] stat : stats) {
            JPanel statCard = new JPanel(new BorderLayout());
            statCard.setBackground(Color.WHITE);
            statCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
            ));
            
            JLabel lblStatTitulo = new JLabel(stat[0]);
            if (stat[0].equals("Total Vans") && Icones.getVanIcon(16) != null) {
                lblStatTitulo.setIcon(Icones.getVanIcon(16));
            } else if (stat[0].equals("Motoristas") && Icones.getUsuarioIcon(16) != null) {
                lblStatTitulo.setIcon(Icones.getUsuarioIcon(16));
            } else if (stat[0].equals("Capacidade Total") && Icones.getPessoasIcon(16) != null) {
                lblStatTitulo.setIcon(Icones.getPessoasIcon(16));
            } else if (stat[0].equals("Rota") && Icones.getLocalIcon(16) != null) {
                lblStatTitulo.setIcon(Icones.getLocalIcon(16));
            }
            lblStatTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            lblStatTitulo.setForeground(Color.GRAY);
            
            JLabel lblStatValor = new JLabel(stat[1]);
            lblStatValor.setFont(new Font("Segoe UI", Font.BOLD, 18));
            lblStatValor.setForeground(AZUL_PRINCIPAL);
            
            statCard.add(lblStatTitulo, BorderLayout.NORTH);
            statCard.add(lblStatValor, BorderLayout.CENTER);
            painelStats.add(statCard);
        }
        
        painelBemVindo.add(Box.createVerticalStrut(20));
        painelBemVindo.add(iconeUser);
        painelBemVindo.add(Box.createVerticalStrut(10));
        painelBemVindo.add(lblBemVindo);
        painelBemVindo.add(Box.createVerticalStrut(5));
        painelBemVindo.add(lblInfo);
        painelBemVindo.add(painelStats);
        
        painelPrincipal.add(painelBemVindo, BorderLayout.CENTER);
        
        // Botões SEM ÍCONES
        JPanel painelBotoesContainer = new JPanel(new BorderLayout());
        painelBotoesContainer.setOpaque(false);
        
        JPanel painelBotoesLinha1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        painelBotoesLinha1.setOpaque(false);
        
        JPanel painelBotoesLinha2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        painelBotoesLinha2.setOpaque(false);
        
        JButton btnMinhasVans = criarBotao("MINHAS VANS", AZUL_PRINCIPAL);
        JButton btnRegistrar = criarBotao("REGISTRAR", VERDE_SUCESSO);
        JButton btnRelatorios = criarBotao("RELATÓRIOS", LARANJA_DESTAQUE);
        JButton btnSair = criarBotao("SAIR", VERMELHO_SAIR);
        
        btnMinhasVans.addActionListener(e -> new TelaMinhasVans(usuarioLogado).setVisible(true));
        btnRegistrar.addActionListener(e -> new TelaRegistroDiario(usuarioLogado).setVisible(true));
        btnRelatorios.addActionListener(e -> new TelaRelatorios(usuarioLogado).setVisible(true));
        
        btnSair.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Deseja realmente sair?", "Confirmação", 
                JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        
        painelBotoesLinha1.add(btnMinhasVans);
        painelBotoesLinha1.add(btnRegistrar);
        painelBotoesLinha1.add(btnRelatorios);
        
        painelBotoesLinha2.add(btnSair);
        
        painelBotoesContainer.add(painelBotoesLinha1, BorderLayout.NORTH);
        painelBotoesContainer.add(painelBotoesLinha2, BorderLayout.CENTER);
        
        painelPrincipal.add(painelBotoesContainer, BorderLayout.SOUTH);
        
        add(painelPrincipal);
    }
    
    private JButton criarBotao(String texto, Color cor) {
        JButton botao = new JButton(texto);
        
        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setForeground(Color.WHITE);
        botao.setBackground(cor);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
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
    
    private void mostrarSobre() {
        JPanel painelSobre = new JPanel();
        painelSobre.setLayout(new BoxLayout(painelSobre, BoxLayout.Y_AXIS));
        painelSobre.setBackground(new Color(245, 245, 250));
        painelSobre.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        JLabel titulo = new JLabel("SOBRE O SISTEMA", JLabel.CENTER);
        if (Icones.getInfoIcon(24) != null) {
            titulo.setIcon(Icones.getInfoIcon(24));
        }
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(new Color(0, 70, 135));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        painelSobre.add(titulo);
        painelSobre.add(Box.createVerticalStrut(15));
        
        String[] linhas = {
            "SISTEMA DE CONTROLE DE VANS",
            "",
            "© 2026 - Mariana dos Santos Pereira",
            "Todos os direitos reservados",
            "",
            "Versão 1.0",
            "Controle de Vans - Rota Travessão",
            "",
            "mariana.santos@email.com",
            "",
            "Sistema desenvolvido com Java Swing"
        };
        
        for (String linha : linhas) {
            if (linha.isEmpty()) {
                painelSobre.add(Box.createVerticalStrut(5));
            } else {
                JLabel label = new JLabel(linha, JLabel.CENTER);
                if (linha.equals("SISTEMA DE CONTROLE DE VANS")) {
                    label.setFont(new Font("Segoe UI", Font.BOLD, 16));
                    label.setForeground(new Color(0, 70, 135));
                } else if (linha.contains("@")) {
                    label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                    label.setForeground(new Color(0, 101, 179));
                } else if (linha.equals("Sistema desenvolvido com Java Swing")) {
                    label.setFont(new Font("Segoe UI", Font.ITALIC, 11));
                    label.setForeground(Color.GRAY);
                } else {
                    label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                }
                label.setAlignmentX(Component.CENTER_ALIGNMENT);
                painelSobre.add(label);
            }
        }
        
        JOptionPane.showMessageDialog(this, painelSobre, "Sobre o Sistema", JOptionPane.PLAIN_MESSAGE);
    }
}