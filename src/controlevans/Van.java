package controlevans;

public class Van {
    private String identificacao;
    private String placa;
    private String motorista;
    private double arrecadacaoHoje;
    private static final double VALOR_PASSAGEM = 3.50;
    private static final double COMISSAO_MOTORISTA = 0.25;
    
    public Van(String identificacao, String placa, String motorista) {
        this.identificacao = identificacao;
        this.placa = placa;
        this.motorista = motorista;
        this.arrecadacaoHoje = 0;
    }
    
    public String getIdentificacao() { return identificacao; }
    public String getPlaca() { return placa; }
    public String getMotorista() { return motorista; }
    public double getArrecadacaoHoje() { return arrecadacaoHoje; }
    
    public void setArrecadacaoHoje(double valor) { 
        this.arrecadacaoHoje = valor; 
    }
    
    public int estimarPassageiros() {
        if (arrecadacaoHoje == 0) return 0;
        return (int) Math.round(arrecadacaoHoje / VALOR_PASSAGEM);
    }
    
    public double calcularPagamentoMotorista() {
        return arrecadacaoHoje * COMISSAO_MOTORISTA;
    }
    
    public double calcularLucroDono() {
        return arrecadacaoHoje - calcularPagamentoMotorista();
    }
    
    public double calcularDistanciaTotal() {
        return estimarPassageiros() * 19;
    }
    
    public double calcularTempoTotalHoras() {
        return estimarPassageiros() * (35.0 / 60.0);
    }
}