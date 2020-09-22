package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ControlesPerfilAdminFuncionarioSelecionado implements Initializable {

    @FXML
    private Button botaoBuscar;

    @FXML
    void abrirTelaCadFunc(MouseEvent event) {
    	Main.trocarTela("Tela Cadastrar Funcionarios");
    }

    @FXML
    void abrirTelaHistEntregas(MouseEvent event) {
    	Main.trocarTela("Tela Historico de Entregas");
    }

    @FXML
    void fecharJanela(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void voltar(ActionEvent event) {
    	Main.trocarTela("Tela Login");
    }
    
    
    
    public static void carregarInfosFuncionario(String cpfFuncionario) {
    	// ---- fun��o para ler as infos do funcion�rio pelo cpf aqui ---
    	// --- textField.setText(coisa do funcionario) ---- (uma linha dessas pra cada textField)
    	//(ainda vou setar para os campos estarem desativados para edi��o no come�o)
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
