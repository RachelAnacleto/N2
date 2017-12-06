/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n2.controle;

import n2.jpa.TipoImovelJpaController;
import n2.modelo.TipoImovel;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JList;
import javax.swing.JTable;

/**
 *
 * @author Lourdes
 */
public class TipoImovelControler {

    public void gravar(TipoImovel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public TipoImovel getTipoImovelPorId(Long aLong) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void excluir(TipoImovel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void atualizarLista(JTable tblTipoImovels) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
public class TipoImovelControle {
    
    public void gravar(TipoImovel t, JList listaTipoImovels) throws SQLException{
        
        if(t.getId()==null){
        
            cadastrar(t, listaTipoImovels);
            
        }else{
            alterar(t, listaTipoImovels);
        
        }
    }
    private void cadastrar(TipoImovel t, JList listaTipoImovels) throws SQLException{
        TipoImovelJpaController jpa = new TipoImovelJpaController();
        jpa.cadastrar(t);
        List receitas = jpa.listarTodos();
        listaTipoImovels.setListData(receitas.toArray());
    }
    private void alterar(TipoImovel t, JList listaTipoImovels) throws SQLException{
        TipoImovelJpaController jpa = new TipoImovelJpaController();
        jpa.alterar(t);
        List tipoImovels = jpa.listarTodos();
        listaTipoImovels.setListData(tipoImovels.toArray());
    }
    public void atualizarListaTipoImovel(JList listaTipoImovels) throws SQLException{
        TipoImovelJpaController jpa = new TipoImovelJpaController();        
        List tipoImovels = jpa.listarTodos();
        listaTipoImovels.setListData(tipoImovels.toArray());
    }

    public TipoImovel getTipoImovelPorId(Long aLong) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void excluir(TipoImovel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void atualizarLista(JTable tblTipoImovels) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void gravar(TipoImovel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
}
