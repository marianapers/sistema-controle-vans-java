package controlevans;

import java.util.ArrayList;

public class Usuario {
    private String nome;
    private String senha;
    private ArrayList<Van> vans;
    
    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.vans = new ArrayList<>();
    }
    
    public String getNome() { return nome; }
    public String getSenha() { return senha; }
    public ArrayList<Van> getVans() { return vans; }
    
    public void adicionarVan(Van van) {
        vans.add(van);
    }
    
    public double calcularTotalArrecadadoDia() {
        double total = 0;
        for (Van van : vans) {
            total += van.getArrecadacaoHoje();
        }
        return total;
    }
    
    public double calcularTotalPagarMotoristas() {
        double total = 0;
        for (Van van : vans) {
            total += van.calcularPagamentoMotorista();
        }
        return total;
    }
    
    public double calcularLucroLiquidoDia() {
        return calcularTotalArrecadadoDia() - calcularTotalPagarMotoristas();
    }
}