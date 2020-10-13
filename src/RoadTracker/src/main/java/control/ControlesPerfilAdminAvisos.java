package control;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.*;
import view.*;

public class ControlesPerfilAdminAvisos implements Initializable{

    @FXML
    private Label labelAvisos;
    @FXML
    private Pane paneVisualizarAvisos;
    @FXML
    private TableView<Avisos> tabelaAvisos;
    @FXML
    private TableColumn<?, ?> colunaTituloAviso;
    @FXML
    private TableColumn<?, ?> colunaFuncDestino;
    @FXML
    private TableColumn<?, ?> colunaDataAviso;
    @FXML
    private Button btSelecionarAviso;
    @FXML
    private TextField campoIDAviso;
    @FXML
    private TextField destinatario;
    @FXML
    private TextField campoTipoAviso;
    @FXML
    private TextArea msg;
    @FXML
    private DatePicker dataDoAviso;
    @FXML
    private TextField remetente;
    @FXML
    private CheckBox visualizado;
    @FXML
    private Pane paneAvisoSelecionado;
    @FXML
    private Pane escolherAcaoAvisos;
    @FXML
    private TextField addDestinatario;
    @FXML
    private TextArea addMsg;
    @FXML
    private Pane paneCadastrarAviso;
    @FXML
    private ComboBox<Aviso> cbTipoAviso;

	private List<Avisos> listaAvisos = new ArrayList<>();
	private ObservableList<Avisos> obsListAvisos;
	private int idAviso;

    @FXML
    void abrirTelaCadEnt(MouseEvent event) {
    	Main.trocarTela("Tela Cadastrar Funcionarios");
    }
    
    @FXML
    void abrirTelaFunc(MouseEvent event) {
    	Main.trocarTela("Tela Funcionarios");
    }
    
    @FXML
    void abrirTelaHistEntregas(MouseEvent event) {
    	Main.trocarTela("Tela Historico de Entregas");
    }
    
    @FXML
    void irCadastrarAvisos(MouseEvent event) {
		paneAvisoSelecionado.setDisable(true);
		paneAvisoSelecionado.setVisible(false);
		paneVisualizarAvisos.setDisable(true);
		paneVisualizarAvisos.setVisible(false);
		escolherAcaoAvisos.setDisable(true);
		escolherAcaoAvisos.setVisible(false);
		paneCadastrarAviso.setDisable(false);
		paneCadastrarAviso.setVisible(true);
		limparCampos();
    }

    @FXML
    void irVisualizarAvisos(MouseEvent event) {
		paneAvisoSelecionado.setDisable(true);
		paneAvisoSelecionado.setVisible(false);
		paneVisualizarAvisos.setDisable(false);
		paneVisualizarAvisos.setVisible(true);
		escolherAcaoAvisos.setDisable(true);
		escolherAcaoAvisos.setVisible(false);
		paneCadastrarAviso.setDisable(true);
		paneCadastrarAviso.setVisible(false);
		limparCampos();
    }
    
    @FXML
    void voltar(ActionEvent event) {
    	if (escolherAcaoAvisos.isVisible()) {
    		Main.trocarTela("Tela Boas Vindas");
    	}
    	else if(paneAvisoSelecionado.isVisible()) {
    		paneAvisoSelecionado.setDisable(true);
    		paneAvisoSelecionado.setVisible(false);
    		paneVisualizarAvisos.setDisable(false);
    		paneVisualizarAvisos.setVisible(true);
    		escolherAcaoAvisos.setDisable(true);
    		escolherAcaoAvisos.setVisible(false);
    		paneCadastrarAviso.setDisable(true);
    		paneCadastrarAviso.setVisible(false);
    	}
    	else if(paneVisualizarAvisos.isVisible()) {
    		paneAvisoSelecionado.setDisable(true);
    		paneAvisoSelecionado.setVisible(false);
    		paneVisualizarAvisos.setDisable(true);
    		paneVisualizarAvisos.setVisible(false);
    		escolherAcaoAvisos.setDisable(false);
    		escolherAcaoAvisos.setVisible(true);
    		paneCadastrarAviso.setDisable(true);
    		paneCadastrarAviso.setVisible(false);
    	}
    	else {
    		paneAvisoSelecionado.setDisable(true);
    		paneAvisoSelecionado.setVisible(false);
    		paneVisualizarAvisos.setDisable(false);
    		paneVisualizarAvisos.setVisible(true);
    	}
    	limparCampos();
    }  

    @FXML
    void minimizarJanela(ActionEvent event) {
    	Main.minimizar();
    }
    @FXML
    void fecharJanela(ActionEvent event) {
    	System.exit(0);
    }  

    @FXML
    public void selecionarAviso(ActionEvent event) {
		Avisos selecionado = tabelaAvisos.getSelectionModel().getSelectedItem();
		idAviso = selecionado.getId();
		carregarInfoAviso();
		paneAvisoSelecionado.setDisable(false);
		paneAvisoSelecionado.setVisible(true);
		paneVisualizarAvisos.setDisable(true);
		paneVisualizarAvisos.setVisible(false);
    }
    
    @FXML
    public void confirmarAviso(ActionEvent event) {
    	Aviso aviso = new Aviso();
    	aviso.cadastrarAviso(cbTipoAviso.getValue().getTipo(), addDestinatario.getText(), addMsg.getText(), ControlesLogin.cpfLogado);
    }
    
    public void carregarInfoAviso() {
    	Aviso aviso = new Aviso();
    	aviso = aviso.encontrarAviso(idAviso);
		
		campoIDAviso.setText(String.valueOf(aviso.getId()));
		campoTipoAviso.setText(aviso.getTipo());
		remetente.setText(aviso.getFuncionario().getCpf());
		destinatario.setText(aviso.getFuncionario_destino());
		visualizado.setSelected(aviso.isVisualizado());
		msg.setText(aviso.getMensagem());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	LocalDate localDate = LocalDate.parse(aviso.getData(), formatter);
    	dataDoAviso.setValue(localDate);
    }
    
	@FXML
	void excluir(ActionEvent event) {
		Aviso aviso = new Aviso();
//		aviso.excluir();
	}
	
	public void limparCampos() {
		addDestinatario.setText("");
		addMsg.setText("");
		cbTipoAviso.getSelectionModel().clearSelection();
	}
    
	public void carregarTableViews() {
		Aviso aviso = new Aviso();
		listaAvisos = aviso.listarAvisos();
		obsListAvisos = FXCollections.observableArrayList(listaAvisos);
		colunaTituloAviso.setCellValueFactory(new PropertyValueFactory<>("tituloAviso"));
		colunaFuncDestino.setCellValueFactory(new PropertyValueFactory<>("funcDestino"));
		colunaDataAviso.setCellValueFactory(new PropertyValueFactory<>("dataAviso"));
		tabelaAvisos.setItems(obsListAvisos);
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarTableViews();		
	}
}
