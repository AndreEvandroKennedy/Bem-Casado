
package br.com.larimaia.DAO;

import br.com.larimaia.model.TipoEvento;
import br.com.larimaia.util.ConexaoUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TipoEventoDAO {
    Connection conexao;
    
    public TipoEventoDAO(){
        conexao= ConexaoUtil.getConnection();
    }
    
    
    public List<TipoEvento> buscarTodosOsTiposDeEventos(){
        String sql ="SELECT * FROM tipoevento order by descricao";
        try {
            PreparedStatement preparadorsql = conexao.prepareStatement(sql);
            ResultSet resultado = preparadorsql.executeQuery();
            List<TipoEvento> listaTP = new ArrayList<>();
            
            while(resultado.next()){
                TipoEvento tp= new TipoEvento();
                tp.setId(resultado.getInt("idtipoevento"));
                tp.setDescricao(resultado.getString("descricao"));
                listaTP.add(tp);
                
            }
            preparadorsql.close();
            return listaTP;
        } catch (SQLException ex) {
            Logger.getLogger(TipoEventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    public TipoEvento buscarTipoDeEventoPorId(int id){
        String sql ="SELECT * FROM tipoevento WHERE id=?";
        try {
            PreparedStatement preparadorsql = conexao.prepareStatement(sql);
            ResultSet resultado = preparadorsql.executeQuery();
            TipoEvento tp= new TipoEvento();
            while(resultado.next()){
                tp.setId(resultado.getInt("idtipoevento"));
                tp.setDescricao(resultado.getString("descricao"));
            }
            preparadorsql.close();
            return tp;
        } catch (SQLException ex) {
            Logger.getLogger(TipoEventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
    
}
