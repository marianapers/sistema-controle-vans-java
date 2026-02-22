package controlevans;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {
    private JTextField campoNome;
    private JPasswordField campoSenha;
    
    private final Color AZUL_PRINCIPAL = new Color(0, 70, 135);
    private final Color AZUL_MEDIO = new Color(0, 101, 179);
    private final Color AZUL_CLARO_FUNDO = new Color(240, 248, 255);
    private final Color VERDE_SUCESSO = new Color(0, 120, 86);
    private final Color VERMELHO_SAIR = new Color(180, 0, 0);
    
    public TelaLogin() {
        setTitle("Controle de Vans - Login");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Ícone da janela
        ImageIcon iconeJanela = Icones.getJanelaIcon(32);
        if (iconeJanela != null) {
            setIconImage(iconeJanela.getImage());
        }
        
        JPanel painelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, AZUL_CLARO_FUNDO, w, h, Color.WHITE);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        painelPrincipal.setLayout(new GridBagLayout());
        
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setOpaque(false);
        painel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(AZUL_MEDIO, 2),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Ícone grande da van
        JLabel iconeVan = new JLabel();
        ImageIcon vanIcon = Icones.getVanIcon(80);
        if (vanIcon != null) {
            iconeVan.setIcon(vanIcon);
        } else {
            iconeVan.setText("VAN");
            iconeVan.setFont(new Font("Segoe UI", Font.BOLD, 40));
        }
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        painel.add(iconeVan, gbc);
        
        // Título
        JLabel titulo = new JLabel("CONTROLE DE VANS", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(AZUL_PRINCIPAL);
        gbc.gridy = 1;
        painel.add(titulo, gbc);
        
        // Rota
        JLabel rota = new JLabel("Rota: Travessão x Centro", JLabel.CENTER);
        rota.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        rota.setForeground(AZUL_MEDIO);
        gbc.gridy = 2;
        painel.add(rota, gbc);
        
        gbc.gridy = 3;
        painel.add(new JLabel(" "), gbc);
        
        // Usuário com ícone
        gbc.gridwidth = 1;
        gbc.gridy = 4;
        gbc.gridx = 0;
        
        JPanel painelUsuario = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        painelUsuario.setOpaque(false);
        
        JLabel iconeUsuario = new JLabel();
        ImageIcon userIcon = Icones.getUsuarioIcon(20);
        if (userIcon != null) {
            iconeUsuario.setIcon(userIcon);
        } else {
            iconeUsuario.setText("👤");
            iconeUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        }
        painelUsuario.add(iconeUsuario);
        
        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblUsuario.setForeground(AZUL_PRINCIPAL);
        painelUsuario.add(lblUsuario);
        
        painel.add(painelUsuario, gbc);
        
        campoNome = new JTextField(15);
        campoNome.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        campoNome.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(AZUL_MEDIO),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        gbc.gridx = 1;
        painel.add(campoNome, gbc);
        
        // Senha com ícone
        gbc.gridy = 5;
        gbc.gridx = 0;
        
        JPanel painelSenha = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        painelSenha.setOpaque(false);
        
        JLabel iconeCadeado = new JLabel();
        ImageIcon cadeadoIcon = Icones.getCadeadoIcon(20);
        if (cadeadoIcon != null) {
            iconeCadeado.setIcon(cadeadoIcon);
        } else {
            iconeCadeado.setText("🔒");
            iconeCadeado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        }
        painelSenha.add(iconeCadeado);
        
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblSenha.setForeground(AZUL_PRINCIPAL);
        painelSenha.add(lblSenha);
        
        painel.add(painelSenha, gbc);
        
        campoSenha = new JPasswordField(15);
        campoSenha.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        campoSenha.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(AZUL_MEDIO),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        gbc.gridx = 1;
        painel.add(campoSenha, gbc);
        
        gbc.gridy = 6;
        painel.add(new JLabel(" "), gbc);
        
        // Botões SEM ÍCONES
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelBotoes.setOpaque(false);
        
        JButton btnEntrar = criarBotao("ENTRAR", VERDE_SUCESSO);
        JButton btnSair = criarBotao("SAIR", VERMELHO_SAIR);
        
        btnEntrar.addActionListener(e -> fazerLogin());
        btnSair.addActionListener(e -> System.exit(0));
        
        painelBotoes.add(btnEntrar);
        painelBotoes.add(btnSair);
        
        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        painel.add(painelBotoes, gbc);
        
        painelPrincipal.add(painel);
        add(painelPrincipal);
    }
    
    private JButton criarBotao(String texto, Color cor) {
        JButton botao = new JButton(texto);
        
        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setPreferredSize(new Dimension(160, 45));
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder());
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
    
    private void fazerLogin() {
        String nome = campoNome.getText().trim();
        String senha = new String(campoSenha.getPassword());
        
        if (SistemaDados.validarLogin(nome, senha)) {
            JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");
            this.dispose();
            
            Usuario usuarioLogado = SistemaDados.getUsuario(nome);
            TelaPrincipal telaPrincipal = new TelaPrincipal(usuarioLogado);
            telaPrincipal.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, 
                "Usuário ou senha incorretos!\n\n" +
                "Usuários disponíveis:\n" +
                "- Marcos (senha: 123)\n" +
                "- Jhonatan (senha: 123)\n" +
                "- Pedro (senha: 123)\n" +
                "- Mariana (senha: 123)",
                "Erro no Login",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}