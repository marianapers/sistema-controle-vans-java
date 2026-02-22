package controlevans;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class TelaMinhasVans extends JFrame {
    private Usuario usuario;
    
    private final Color AZUL_PRINCIPAL = new Color(0, 70, 135);
    private final Color AZUL_MEDIO = new Color(0, 101, 179);
    private final Color AZUL_CLARO_LINHA = new Color(235, 245, 255);
    
    public TelaMinhasVans(Usuario usuario) {
        this.usuario = usuario;
        
        setTitle("Minhas Vans - " + usuario.getNome());
        setSize(1100, 650);
        setLocationRelativeTo(null);
        
        // Ícone da janela
        ImageIcon iconeJanela = Icones.getJanelaIcon(32);
        if (iconeJanela != null) {
            setIconImage(iconeJanela.getImage());
        }
        
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelPrincipal.setBackground(Color.WHITE);
        
        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBackground(Color.WHITE);
        
        JLabel titulo = new JLabel("MINHAS VANS");
        if (Icones.getVanIcon(40) != null) {
            titulo.setIcon(Icones.getVanIcon(40));
            titulo.setIconTextGap(10);
        }
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(AZUL_PRINCIPAL);
        painelTitulo.add(titulo, BorderLayout.WEST);
        
        JLabel totalVans = new JLabel("Total: " + usuario.getVans().size() + " vans");
        if (Icones.getPessoasIcon(20) != null) {
            totalVans.setIcon(Icones.getPessoasIcon(20));
            totalVans.setIconTextGap(5);
        }
        totalVans.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        totalVans.setForeground(AZUL_MEDIO);
        painelTitulo.add(totalVans, BorderLayout.EAST);
        
        painelPrincipal.add(painelTitulo, BorderLayout.NORTH);
        
        String[] colunas = {"Identificação", "Placa", "Motorista", "Arrecadação Hoje", "Passageiros Estimados"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        for (Van van : usuario.getVans()) {
            Object[] linha = {
                van.getIdentificacao(),
                van.getPlaca(),
                van.getMotorista(),
                String.format("R$ %.2f", van.getArrecadacaoHoje()),
                van.estimarPassageiros()
            };
            modelo.addRow(linha);
        }
        
        JTable tabela = new JTable(modelo) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : AZUL_CLARO_LINHA);
                }
                return c;
            }
        };
        
        tabela.setRowHeight(40);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabela.setShowGrid(false);
        tabela.setIntercellSpacing(new Dimension(0, 0));
        
        JTableHeader header = tabela.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.setBackground(AZUL_PRINCIPAL);
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 50));
        
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(centralizado);
        }
        
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createLineBorder(AZUL_MEDIO, 1));
        scrollPane.getViewport().setBackground(Color.WHITE);
        
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);
        
        JPanel painelInfo = new JPanel(new GridLayout(1, 3, 15, 0));
        painelInfo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 0, 0, 0, AZUL_MEDIO),
            BorderFactory.createEmptyBorder(15, 0, 0, 0)
        ));
        painelInfo.setBackground(Color.WHITE);
        
        String[][] infos = {
            {"Valor Passagem", "R$ 3,50"},
            {"Distância", "19 km por viagem"},
            {"Tempo Médio", "35 minutos"}
        };
        
        for (String[] info : infos) {
            JPanel card = new JPanel(new GridLayout(2, 1));
            card.setBackground(new Color(250, 250, 250));
            card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
            ));
            
            JLabel lblTitulo = new JLabel(info[0]);
            if (info[0].equals("Valor Passagem") && Icones.getDinheiroIcon(16) != null) {
                lblTitulo.setIcon(Icones.getDinheiroIcon(16));
            } else if (info[0].equals("Distância") && Icones.getLocalIcon(16) != null) {
                lblTitulo.setIcon(Icones.getLocalIcon(16));
            } else if (info[0].equals("Tempo Médio") && Icones.getCalendarioIcon(16) != null) {
                lblTitulo.setIcon(Icones.getCalendarioIcon(16));
            }
            lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            lblTitulo.setForeground(Color.GRAY);
            
            JLabel lblValor = new JLabel(info[1]);
            lblValor.setFont(new Font("Segoe UI", Font.BOLD, 16));
            lblValor.setForeground(AZUL_PRINCIPAL);
            
            card.add(lblTitulo);
            card.add(lblValor);
            painelInfo.add(card);
        }
        
        painelPrincipal.add(painelInfo, BorderLayout.SOUTH);
        
        add(painelPrincipal);
    }
}