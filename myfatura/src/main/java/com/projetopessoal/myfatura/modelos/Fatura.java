package com.projetopessoal.myfatura.modelos;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Fatura {
    private long id;
    private BigDecimal valor_fatura;
    private int mes;
    private int ano;
    private Timestamp mes_ano;

    public long getId(){ return this.id; }
    public BigDecimal getValor_fatura() { return this.valor_fatura; }
    public int getMes() { return this.mes; }
    public int getAno() {return this.ano; }
    public Timestamp getMes_ano() { return mes_ano; }
    public void setId(long id) { this.id = id; }
    public void setValor_fatura(BigDecimal valor_fatura){ this.valor_fatura = valor_fatura; }
    public void setMes(int mes){ this.mes = mes; }
    public void setAno(int ano){ this.ano = ano; }
    public void setMes_ano(Timestamp mes_ano) { this.mes_ano = mes_ano; }
}
