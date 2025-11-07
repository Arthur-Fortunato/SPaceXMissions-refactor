package org.example.reembolso;

import lombok.ToString;

@ToString
public class Viagem {
    private String origem;
    private String destino;
    private int distanciaEmKm;
    private TipoTransporte tipoTransporte;
    private double custoTotal;
    private boolean funcionarioUsouCarroProprio;

    public Viagem(String origem, String destino, int distanciaEmKm, TipoTransporte tipoTransporte, double custoTotal, boolean funcionarioUsouCarroProprio) {
        this.origem = origem;
        this.destino = destino;
        this.distanciaEmKm = distanciaEmKm;
        this.tipoTransporte = tipoTransporte;
        this.custoTotal = custoTotal;
        this.funcionarioUsouCarroProprio = funcionarioUsouCarroProprio;
    }

    public String getOrigem() {return origem;}

    public void setOrigem(String origem) {this.origem = origem;}

    public String getDestino() {return destino;}

    public void setDestino(String destino) {this.destino = destino;}

    public int getDistanciaEmKm() {return distanciaEmKm;}

    public void setDistanciaEmKm(int distanciaEmKm) {this.distanciaEmKm = distanciaEmKm;}

    public TipoTransporte getTransporte() {return tipoTransporte;}

    public void setTransporte(TipoTransporte tipoTransporte) {this.tipoTransporte = tipoTransporte;}

    public double getCustoTotal() {return custoTotal;}

    public void setCustoTotal(double custoTotal) {this.custoTotal = custoTotal;}

    public boolean getFuncionarioUsouCarroProprio() { return funcionarioUsouCarroProprio;}

    public void setFuncionarioUsouCarroProprio(boolean funcionarioUsouCarroProprio) {this.funcionarioUsouCarroProprio = funcionarioUsouCarroProprio;}
}
