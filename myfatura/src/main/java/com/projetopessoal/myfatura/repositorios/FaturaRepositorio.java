package com.projetopessoal.myfatura.repositorios;

import com.projetopessoal.myfatura.SQL.FaturaSQL;
import com.projetopessoal.myfatura.modelos.Fatura;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class FaturaRepositorio {
    private JdbcTemplate _jdbcTemplate;

    //region FaturaRepositorio
    public FaturaRepositorio(JdbcTemplate jdbcTemplate){
        this._jdbcTemplate = jdbcTemplate;
    }
    //endregion

    //region CriarFatura
    public Fatura CriarFatura(Fatura faturaDTO) throws Exception
    {
        LocalDateTime data = LocalDate.of(faturaDTO.getAno(), faturaDTO.getMes(), 1).atStartOfDay();

        var parametros = new Object[]{
            faturaDTO.getValor_fatura(),
            faturaDTO.getMes(),
            faturaDTO.getAno(),
            Timestamp.valueOf(data)
        };

        if(_jdbcTemplate.update(FaturaSQL.CriacaoFatura, parametros) <= 0)
            throw new Exception("Falha inesperada ao inserir nova fatura");

        return faturaDTO;
    }
    //endregion

    //region CapturarFaturasPorAno
    public List<Fatura> CapturarFaturasPorAno(long id){
        return _jdbcTemplate.query(FaturaSQL.ListarFaturasPorAno, new Object[]{ id }, new BeanPropertyRowMapper<>(Fatura.class));
    }
    //endregion

    //region CapturarTodasFaturas
    public List<Fatura> CapturarTodasFaturas(){
        return _jdbcTemplate.query(FaturaSQL.ListarFaturas, new BeanPropertyRowMapper<>(Fatura.class));
    }
    //endregion

    //region ExlcuirFatura
    public boolean ExcluirFatura(long id) throws Exception{
        var parametros = new Object[]{ id };

        if(_jdbcTemplate.update(FaturaSQL.ExcluirFatura, parametros) <= 0){
            throw new Exception("Falha ao excluir fatura");
        }

        return true;
    }
    //endregion

    //region EditarFatura
    public Fatura EditarFatura(Fatura faturaDTO) throws Exception{
        LocalDateTime data = LocalDate.of(faturaDTO.getAno(), faturaDTO.getMes(), 1).atStartOfDay();

        var parametros = new Object[]{
                faturaDTO.getValor_fatura(),
                faturaDTO.getMes(),
                faturaDTO.getAno(),
                Timestamp.valueOf(data),
                faturaDTO.getId()
        };

        if(_jdbcTemplate.update(FaturaSQL.EditarFatura, parametros) <= 0){
            throw new Exception("Falha ao Editar exceção");
        }

        return faturaDTO;
    }
    //endregion
}
