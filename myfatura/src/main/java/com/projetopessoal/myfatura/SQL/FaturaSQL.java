package com.projetopessoal.myfatura.SQL;

public class FaturaSQL {
    public static String CriacaoFatura = "INSERT INTO myfatura.fatura(valor_fatura, mes, ano, mes_ano)" +
                                          "VALUES (?, ?, ?, ?)";
    public static String ListarFaturas = "SELECT * FROM myfatura.fatura ORDER BY mes_ano DESC";
    public static String ListarFaturasPorAno = "SELECT * FROM myfatura.fatura WHERE ano = ? ORDER BY mes_ano ASC";
    public static String ExcluirFatura = "DELETE FROM myfatura.fatura WHERE id = ?";
    public static String EditarFatura = "UPDATE myfatura.fatura " +
                                        "SET valor_fatura = ?, " +
                                        "mes = ?, " +
                                        "ano = ?, " +
                                        "mes_ano = ? " +
                                        "WHERE id = ?";
}
