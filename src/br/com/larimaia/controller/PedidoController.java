
package br.com.larimaia.controller;


import br.com.larimaia.DAO.PedidoDAO;
import br.com.larimaia.exception.ServiceException;
import br.com.larimaia.model.Cliente;
import br.com.larimaia.model.ItemPedido;
import br.com.larimaia.model.Pedido;
import br.com.larimaia.model.Produto;
import br.com.larimaia.model.TipoEvento;
import br.com.larimaia.service.PedidoService;
import java.awt.HeadlessException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class PedidoController implements Initializable {
    
    @FXML
    private TextField tfIndicacao;
    
    @FXML
    private TextField tfOrigemPedido;
    
    @FXML 
    private ComboBox<Cliente> comboBoxClientes;

    @FXML
    private TextField tfcerimonial;
    
    @FXML
    private DatePicker dataEvento;
    
    @FXML
    private DatePicker dataPedido;
    
    @FXML 
    private ComboBox<TipoEvento> comboBoxTipo;
    
    @FXML
    private TextField tfHorarioEvento;

    @FXML
    private TextField tfLocalEvento;
    
    @FXML
    private TextField tfEndereco;
    
    @FXML 
    private ComboBox<Produto> comboBoxProduto;
    
    @FXML
    private TextField tfQuantidade;
            
    @FXML
    private TextField tfValor;
    
    @FXML
    private TextField tfObs;
        
    @FXML
    private TableView tabela;
    
    @FXML
    private TableColumn tabColProduto;
    
    @FXML
    private TableColumn tabColQuantidade;
    
    @FXML
    private TableColumn tabColValor;
    
    @FXML
    private Button btexcluir;
    
    private static final List<ItemPedido> prod = new ArrayList<>();
     
    private PedidoService ps = new PedidoService();
    
    private ObservableList<ItemPedido> dados;
    
    
    
   @FXML
   private void excluir(ActionEvent e){
       
//Alguns comandos que encontrei sobre excluir linha da tabela
    // pega a linha da tabela
       //tabela.getSelectionModel().getSelectedItem();
       
      // int idTab = tabela.getSelectionModel().getFocusedIndex();
//       dados.remove(idTab);
       //tabela.getSelectionModel().clearSelection(row, tabColValor);
//       tabela.getItems().remove(idTab);
//       tabela.setItems(dados);
   }

   
    
    @FXML  
    private void btnSal (ActionEvent e){
        
        Pedido pedido = new Pedido();
        
        pedido.setOrigemPedido(tfOrigemPedido.getText());
        pedido.setDataPedido(dataPedido.getValue().toString());
        pedido.setCliente(comboBoxClientes.getValue());
        pedido.setCerimonial(tfcerimonial.getText());
        pedido.setDataEvento(dataEvento.getValue().toString());
        pedido.setTipoEvento(comboBoxTipo.getValue());
        pedido.setHoraEvento(tfHorarioEvento.getText());
        pedido.setIndicacao(tfIndicacao.getText());
        pedido.setLocalEvento(tfLocalEvento.getText());
        pedido.setEnderecoEvento(tfEndereco.getText());
        pedido.setObs(tfObs.getText());
        pedido.setItens(prod);
        try {
            ps.salvar(pedido);
            
            JOptionPane.showMessageDialog(null, "Pedido salvo com sucesso!");
        } catch (ServiceException | HeadlessException exc) {
            JOptionPane.showMessageDialog(null, exc);
        }
        
        
    }
   
    @FXML
    private void btnAdd(ActionEvent e){
        
        ItemPedido ip = new ItemPedido();
        
        ip.setProduto(comboBoxProduto.getValue());
        ip.setQuantidade(Integer.parseInt(tfQuantidade.getText()));
        ip.setValor(comboBoxProduto.getValue().getValor()*ip.getQuantidade());
        System.out.println(ip.getValor());
        
        prod.add(ip);
        
        
        tabColProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        tabColValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tabColQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        
        ObservableList<ItemPedido> dados =
        FXCollections.observableArrayList(
                prod
        );
        
        tabela.setItems(dados);
    }
    
    @FXML  
    private void buscarPedido (ActionEvent e){
        PedidoDAO buscar = new PedidoDAO();
        buscar.buscarTodosPedidos();
        
    }
    
    @FXML  
    private void buscarPedidoPorId (ActionEvent e){
        PedidoDAO buscar = new PedidoDAO();
        buscar.buscarPorId(1);
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxClientes.setItems(PedidoService.buscarClientes());
        comboBoxProduto.setItems(PedidoService.buscarProdutos());
        comboBoxTipo.setItems(PedidoService.buscarTipoEventos());
        tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); 
    }  
    
    @FXML
    public void buscarNomeClientes(){
        
        
    }

    private static class TestObject {

        public TestObject() {
        }
    }
    
    

    
}
