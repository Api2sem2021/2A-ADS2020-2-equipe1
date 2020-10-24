package control;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.Motorista;
import model.Viagem;
import view.Main;
import view.Viagens;

public class ControlesPerfilMotViagemAtual implements Initializable {
	 
	 Viagem viagemAtual = null;
	
	 public static boolean cronometrarTempoTotal = false;
	 public static boolean cronometrarExpDiario = false;
	 public static boolean cronometrarDescDiario = false;
	 public static boolean cronometrarAlimDiaria = false;
	 public static boolean carregarViagem = false;
    
	 @FXML
	 private Label labelDestino;
	 @FXML
	 private Label labelPlaca;
	 @FXML
	 private Label labelPrazo;
	 @FXML
	 private Label labelHorasTotais;
	 @FXML
	 private Label labelCarga;
	 @FXML
	 private Label labelSituacao;
	 @FXML
	 private Label labelAtencao;
	 @FXML
	 private Button botaoExpediente;
	 @FXML
	 private Label labelExpMax;
	 @FXML
	 private Label labelExpHoje;
	 @FXML
	 private Button botaoAlimentacao;
	 @FXML
	 private Label labelAlimMax;
	 @FXML
	 private Label labelAlimHoje;
	 @FXML
	 private Button botaoDescanso;
	 @FXML
	 private Label labelDescMax;
	 @FXML
	 private Label labelDescHoje;
	 @FXML
	 private Button botaoFinalizarViagem;

    
	 
	 
	 


    @FXML
    public void iniciarExpediente(ActionEvent event) {	
    	cronometrarTempoTotal = true;
    	cronometrarExpDiario = true;
    	botaoExpediente.setText("Pausar expediente");
    	botaoExpediente.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			finalizarExpediente(event);
    		}
    	});
    	
    	botaoAlimentacao.setDisable(true);
    	botaoDescanso.setDisable(true);
    }
    @FXML
    public void finalizarExpediente(ActionEvent event) {	
    	cronometrarTempoTotal = false;
    	cronometrarExpDiario = false;
    	botaoExpediente.setText("Iniciar expediente");
    	botaoExpediente.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			iniciarExpediente(event);
    		}
    	});
    	
    	botaoAlimentacao.setDisable(false);
    	botaoDescanso.setDisable(false);
    }
    
    
    @FXML
    public void pausaDescanso(ActionEvent event) {	
    	cronometrarDescDiario = true;
    	
    	botaoDescanso.setText("Pausar descanso");
    	botaoDescanso.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			voltaDescanso(event);
    		}
    	});
    	
    	botaoExpediente.setDisable(true);
    	botaoAlimentacao.setDisable(true);
    	
    }
    @FXML
    public void voltaDescanso(ActionEvent event) {	
    	cronometrarDescDiario = false;
    	
    	botaoDescanso.setText("Pausa para descanso");
    	botaoDescanso.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			pausaDescanso(event);
    		}
    	});
    	
    	botaoExpediente.setDisable(false);
    	botaoAlimentacao.setDisable(false);
    	
    }
    
    
    @FXML
    public void pausaAlimentacao(ActionEvent event) {	
    	cronometrarAlimDiaria = true;
    	
    	botaoAlimentacao.setText("Pausar alimenta��o");
    	botaoAlimentacao.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			voltaAlimentacao(event);
    		}
    	});
    	
    	botaoExpediente.setDisable(true);
    	botaoDescanso.setDisable(true);
    	
    }
    @FXML
    public void voltaAlimentacao(ActionEvent event) {	
    	cronometrarAlimDiaria = false;
    	
    	botaoAlimentacao.setText("Pausa para alimenta��o");
    	botaoAlimentacao.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			pausaAlimentacao(event);
    		}
    	});
    	
    	botaoExpediente.setDisable(false);
    	botaoDescanso.setDisable(false);
    	
    }
    
    
    @FXML
    public void finalizarViagem(ActionEvent event) {	
    	Viagem viagem = new Viagem();
    	Motorista motorista = new Motorista();
    	
    	viagem = viagem.encontrarViagem(viagemAtual.getId());
    	motorista = motorista.encontrarMotorista(ControlesLogin.cpfLogado);
    	
    	botaoAlimentacao.setDisable(true);
    	botaoDescanso.setDisable(true);
    	botaoExpediente.setDisable(true);
		botaoFinalizarViagem.setDisable(true);
		
		labelSituacao.setText("Situa��o: Finalizado");
		labelAtencao.setText("Viagem finalizada! Voc� pode selecionar uma nova para come�ar o monitoramento em " + '"' + "Minhas Viagens" + '"');
    
		cronometrarExpDiario = false;
		cronometrarTempoTotal = false;
		cronometrarAlimDiaria = false;
		cronometrarDescDiario = false;
		
  	  	String tempoAtual[] = labelHorasTotais.getText().split(":");
  	  	String segundos = tempoAtual[3];
  	  	String minutos = tempoAtual[2];
  	  	String horas = tempoAtual[1].substring(1);
  	  	String tempoTotal = horas + ":" + minutos + ":" + segundos;
  	  	
  	  	String expHojeAtual[] = labelExpHoje.getText().split(":");
  	  	String segundosExpHoje = expHojeAtual[3];
  	  	String minutosExpHoje = expHojeAtual[2];
  	  	String horasExpHoje = expHojeAtual[1].substring(1);
  	  	String tempoTotalExpHoje = horasExpHoje + ":" + minutosExpHoje + ":" + segundosExpHoje;
  	  	
  	  	String alimHojeAtual[] = labelAlimHoje.getText().split(":");
  	  	String segundosAlimHoje = alimHojeAtual[3];
  	  	String minutosAlimHoje = alimHojeAtual[2];
  	  	String horasAlimHoje = alimHojeAtual[1].substring(1);
  	  	String tempoTotalAlimHoje = horasAlimHoje + ":" + minutosAlimHoje + ":" + segundosAlimHoje;
  	  	
  	  	String descHojeAtual[] = labelDescHoje.getText().split(":");
  	  	String segundosDescHoje = descHojeAtual[3];
  	  	String minutosDescHoje = descHojeAtual[2];
  	  	String horasDescHoje = descHojeAtual[1].substring(1);
  	  	String tempoTotalDescHoje = horasDescHoje + ":" + minutosDescHoje + ":" + segundosDescHoje;
		
		viagem.finalizarViagem(tempoTotal);
		motorista.atualizarTempos(tempoTotalExpHoje, tempoTotalAlimHoje, tempoTotalDescHoje);
		
		
		ControlesPerfilMotViagens.carregarTableView = true;
    }
    
    
    
    public void carregarViagem() {
    	Viagem viagem = new Viagem();
    	viagemAtual = null;
    	Motorista motorista = new Motorista().encontrarMotorista(ControlesLogin.cpfLogado);

    	List<Viagens> listaDeViagens = new ArrayList<>();
    	
    	listaDeViagens = viagem.listarViagens();
    	
    	listaDeViagens.forEach(item -> {
    		if(item.getMotorista().getCpf().equals(ControlesLogin.cpfLogado)) {
    			if(item.getSituacao().equals("Em andamento")) {
    				viagemAtual = viagem.encontrarViagem(item.getId());
    			}
    		}
    	});
    	
    	if(viagemAtual != null) {        	
    		labelDestino.setText("Destino: " + viagemAtual.getDestino());
    		labelPlaca.setText("Placa do ve�culo: " + viagemAtual.getVeiculo().getPlaca());
    		labelPrazo.setText("Prazo de entrega: " + viagemAtual.getFim());
    		labelHorasTotais.setText("Horas totais dirigidas: " + viagemAtual.getTotal());
    		labelCarga.setText("Carga: " + viagemAtual.getCarga());
    		labelSituacao.setText("Situa��o: " + viagemAtual.getSituacao());
    		
    		labelAtencao.setVisible(false);
    		
    		labelExpMax.setText("Expediente m�ximo di�rio: " + motorista.getCargaHoraria() + 'h');
       		if(motorista.getTrabalhado_hoje().equals("0")){
       			labelExpHoje.setText("Total de hoje: " + motorista.getTrabalhado_hoje() + 'h');
       		}else {
       			labelExpHoje.setText("Total de hoje: " + motorista.getTrabalhado_hoje());
       		}
    		
       		labelAlimMax.setText("Tempo m�ximo de alimenta��o: " + "1h");
       		if(motorista.getAlimentacao_hoje().equals("0")){
        		labelAlimHoje.setText("Total de hoje: " + motorista.getAlimentacao_hoje() + 'h');
       		}else {
       			labelAlimHoje.setText("Total de hoje: " + motorista.getAlimentacao_hoje());
       		}
       		
       		labelDescMax.setText("Tempo m�ximo de descanso: " + "1h");
       		if(motorista.getDescansado_hoje().equals("0")){
        		labelDescHoje.setText("Total de hoje: " + motorista.getDescansado_hoje() + 'h');
       		}else {
       			labelDescHoje.setText("Total de hoje: " + motorista.getDescansado_hoje());
       		}

    		
    		
    		
    		
    		
    		// Essa parte logo abaixo talvez esteja confusa, mas resumindo, ela verifica se a entrega est� atrasada ou n�o, e avisa o motorista caso esteja
        	String prazo = viagemAtual.getFim();
        	
        	int ano = LocalDate.now().getYear();
        	int dia = LocalDate.now().getDayOfMonth();
        	int mes = LocalDate.now().getMonthValue();
        	
        	String desmontado[] = prazo.split("/");
        	
        	if(Integer.parseInt(desmontado[2]) < ano) {
        		labelAtencao.setVisible(true);
        		labelAtencao.setText("Sua entrega est� atrasada! Verifique junto a filial para mais informa��es.");
        	}else if(Integer.parseInt(desmontado[1]) < mes) {
        		labelAtencao.setVisible(true);
        		labelAtencao.setText("Sua entrega est� atrasada! Verifique junto a filial para mais informa��es.");
        	}else if(Integer.parseInt(desmontado[0]) < dia) {
        		labelAtencao.setVisible(true);
        		labelAtencao.setText("Sua entrega est� atrasada! Verifique junto a filial para mais informa��es.");
        	}
        	

        	// Ativa os bot�es
        	botaoAlimentacao.setDisable(false);
        	botaoDescanso.setDisable(false);
        	botaoExpediente.setDisable(false);
    		botaoFinalizarViagem.setDisable(false);
        	
        	
        	carregarViagem = false;
    	}else {
    		labelDestino.setText("Destino: ");
    		labelPlaca.setText("Placa do ve�culo: ");
    		labelPrazo.setText("Prazo de entrega: ");
    		labelHorasTotais.setText("Horas totais dirigidas: ");
    		labelCarga.setText("Carga: ");
    		labelSituacao.setText("Situa��o: ");
    		
    		labelAtencao.setVisible(true);
    		String aviso = "Nenhuma viagem selecionada no momento, por favor selecione na se��o " + '"' + "Minhas viagens" + '"';
    		labelAtencao.setText(aviso);
    		
    		labelExpMax.setText("Expediente m�ximo di�rio: ");
    		labelExpHoje.setText("Total de hoje: ");
    		
       		labelAlimMax.setText("Tempo m�ximo de alimenta��o: ");
       		labelAlimHoje.setText("Total de hoje: ");
       		
       		labelDescMax.setText("Tempo m�ximo de descanso: ");
    		labelDescHoje.setText("Total de hoje: ");
    		
    		
        	// Desativa os bot�es
        	botaoAlimentacao.setDisable(true);
        	botaoDescanso.setDisable(true);
        	botaoExpediente.setDisable(true);
    		botaoFinalizarViagem.setDisable(true);
    		
    		carregarViagem = false;
    	}
    	
    	
    	
    	
    }
    
    
    @FXML
    void abrirCriarAviso(MouseEvent event) {
    	Main.trocarTela("Criar Aviso");
    }

    @FXML
    void abrirMinhasViagens(MouseEvent event) {
    	Main.trocarTela("Mot Viagens");
    }
    
    @FXML
    void voltar(ActionEvent event) {
    	Main.trocarTela("Tela Login");
    }
	@FXML
    void minimizarJanela(ActionEvent event) {
    	Main.minimizar();
    }
    @FXML
    void fecharJanela(ActionEvent event) {
    	System.exit(0);
    }

    
    
    void tarefasEmLoop() {
    	// Considere que cada if aqui dentro � uma "fun��o"

  	  	
  	  	
  	  	// "Fun��o" para cronometrar o tempo
  	  	if(cronometrarTempoTotal) {
	  	  	String texto = "Horas totais dirigidas: ";
	  	  	
	  	  	String tempoAtual[] = labelHorasTotais.getText().split(":");
	  	  	
	  	  	String segundos = tempoAtual[3];
	  	  	String minutos = tempoAtual[2];
	  	  	String horas = tempoAtual[1].substring(1);  // Nessa linha, horas inicialmente vem como " 00", ent�o essas opera��es retiram o " "
	  	  	
	  	  	// Incrementa o tempo
	  	  	segundos = String.valueOf(Integer.parseInt(segundos) + 1);
	  	  	if(Integer.parseInt(segundos) >= 60) {
	  	  		segundos = "00";
	  	  	  	minutos = String.valueOf(Integer.parseInt(minutos) + 1);
	  	  	}
	  	  	if(Integer.parseInt(minutos) >= 60) {
	  	  		minutos = "00";
	  	  		horas = String.valueOf(Integer.parseInt(horas) + 1);
	  	  	}
	  	  	
	  	  	// Formata o tempo
	  	  	if(Integer.parseInt(segundos) < 10 && segundos.length() < 2) {
	  	  		segundos = "0" + segundos;
	  	  	}
	  	  	if(Integer.parseInt(minutos) < 10 && minutos.length() < 2) {
	  	  		minutos = "0" + minutos;
	  	  	}
	  	  	if(Integer.parseInt(horas) < 10 && horas.length() < 2) {
	  	  		horas = "0" + horas;
	  	  	}
	  	  	
	  	  	// Formata o texto e coloca na label
	  	  	texto = texto + horas + ":" + minutos + ":" + segundos;	
	  	  	labelHorasTotais.setText(texto);
	  	  	
  	  	}
  	  	
  	  	if(cronometrarExpDiario) {
  	  		String texto = "Total de hoje: ";
  	  		String totalDiario = labelExpHoje.getText();
  	  		
  	  		if(!totalDiario.contains("0h")) {
  		  	  	String tempoAtual[] = labelExpHoje.getText().split(":");
  		  	  	
  		  	  	String segundos = tempoAtual[3];
  		  	  	String minutos = tempoAtual[2];
  		  	  	String horas = tempoAtual[1].substring(1);
  		  	  	
  		  	  	// Incrementa o tempo
  		  	  	segundos = String.valueOf(Integer.parseInt(segundos) + 1);
  		  	  	if(Integer.parseInt(segundos) >= 60) {
  		  	  		segundos = "00";
  		  	  	  	minutos = String.valueOf(Integer.parseInt(minutos) + 1);
  		  	  	}
  		  	  	if(Integer.parseInt(minutos) >= 60) {
  		  	  		minutos = "00";
  		  	  		horas = String.valueOf(Integer.parseInt(horas) + 1);
  		  	  	}
  		  	  	
  		  	  	// Formata o tempo
  		  	  	if(Integer.parseInt(segundos) < 10 && segundos.length() < 2) {
  		  	  		segundos = "0" + segundos;
  		  	  	}
  		  	  	if(Integer.parseInt(minutos) < 10 && minutos.length() < 2) {
  		  	  		minutos = "0" + minutos;
  		  	  	}
  		  	  	if(Integer.parseInt(horas) < 10 && horas.length() < 2) {
  		  	  		horas = "0" + horas;
  		  	  	}
  		  	  	
  		  	  	// Formata o texto e coloca na label
  		  	  	texto = texto + horas + ":" + minutos + ":" + segundos;	
  		  	  	labelExpHoje.setText(texto);
  		  	  	
  	  		}else {
  		  	  	// Formata o texto e coloca na label
  		  	  	texto = texto + "00:00:01";	//Aqui precisa iniciar em 1s para n�o ficar atrasado o tempo
  		  	  	labelExpHoje.setText(texto);
  		  	  	
  	  		}
  	  		
  	  	}
  	  	
  	  	
  	  	
  	  	if(cronometrarAlimDiaria) {
  	  		String texto = "Total de hoje: ";
  	  		String totalDiario = labelAlimHoje.getText();
  	  		
  	  		if(!totalDiario.contains("0h")) {
  		  	  	String tempoAtual[] = labelAlimHoje.getText().split(":");
  		  	  	
  		  	  	String segundos = tempoAtual[3];
  		  	  	String minutos = tempoAtual[2];
  		  	  	String horas = tempoAtual[1].substring(1);
  		  	  	
  		  	  	// Incrementa o tempo
  		  	  	segundos = String.valueOf(Integer.parseInt(segundos) + 1);
  		  	  	if(Integer.parseInt(segundos) >= 60) {
  		  	  		segundos = "00";
  		  	  	  	minutos = String.valueOf(Integer.parseInt(minutos) + 1);
  		  	  	}
  		  	  	if(Integer.parseInt(minutos) >= 60) {
  		  	  		minutos = "00";
  		  	  		horas = String.valueOf(Integer.parseInt(horas) + 1);
  		  	  	}
  		  	  	
  		  	  	// Formata o tempo
  		  	  	if(Integer.parseInt(segundos) < 10 && segundos.length() < 2) {
  		  	  		segundos = "0" + segundos;
  		  	  	}
  		  	  	if(Integer.parseInt(minutos) < 10 && minutos.length() < 2) {
  		  	  		minutos = "0" + minutos;
  		  	  	}
  		  	  	if(Integer.parseInt(horas) < 10 && horas.length() < 2) {
  		  	  		horas = "0" + horas;
  		  	  	}
  		  	  	
  		  	  	// Formata o texto e coloca na label
  		  	  	texto = texto + horas + ":" + minutos + ":" + segundos;	
  		  	  	labelAlimHoje.setText(texto);
  		  	  	
  	  		}else {
  		  	  	// Formata o texto e coloca na label
  		  	  	texto = texto + "00:00:01";	//Aqui precisa iniciar em 1s para n�o ficar atrasado o tempo
  		  	  	labelAlimHoje.setText(texto);
  		  	  	
  	  		}
  	  		
  	  	}
  	  	
  	  	
  	  	if(cronometrarDescDiario) {
  	  		String texto = "Total de hoje: ";
  	  		String totalDiario = labelDescHoje.getText();
  	  		
  	  		if(!totalDiario.contains("0h")) {
  		  	  	String tempoAtual[] = labelDescHoje.getText().split(":");
  		  	  	
  		  	  	String segundos = tempoAtual[3];
  		  	  	String minutos = tempoAtual[2];
  		  	  	String horas = tempoAtual[1].substring(1);
  		  	  	
  		  	  	// Incrementa o tempo
  		  	  	segundos = String.valueOf(Integer.parseInt(segundos) + 1);
  		  	  	if(Integer.parseInt(segundos) >= 60) {
  		  	  		segundos = "00";
  		  	  	  	minutos = String.valueOf(Integer.parseInt(minutos) + 1);
  		  	  	}
  		  	  	if(Integer.parseInt(minutos) >= 60) {
  		  	  		minutos = "00";
  		  	  		horas = String.valueOf(Integer.parseInt(horas) + 1);
  		  	  	}
  		  	  	
  		  	  	// Formata o tempo
  		  	  	if(Integer.parseInt(segundos) < 10 && segundos.length() < 2) {
  		  	  		segundos = "0" + segundos;
  		  	  	}
  		  	  	if(Integer.parseInt(minutos) < 10 && minutos.length() < 2) {
  		  	  		minutos = "0" + minutos;
  		  	  	}
  		  	  	if(Integer.parseInt(horas) < 10 && horas.length() < 2) {
  		  	  		horas = "0" + horas;
  		  	  	}
  		  	  	
  		  	  	// Formata o texto e coloca na label
  		  	  	texto = texto + horas + ":" + minutos + ":" + segundos;	
  		  	  	labelDescHoje.setText(texto);
  		  	  	
  	  		}else {
  		  	  	// Formata o texto e coloca na label
  		  	  	texto = texto + "00:00:01";	//Aqui precisa iniciar em 1s para n�o ficar atrasado o tempo
  		  	  	labelDescHoje.setText(texto);
  		  	  	
  	  		}
  	  		
  	  	}
  	  	
  	  	// Carrega a viagem do motorista
  	  	if(carregarViagem) {
  	  		carregarViagem();
  	  	}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask(){
       

          @Override
          public void run() {
        	  Platform.runLater(() -> tarefasEmLoop());
          }
        }, 0, 1000);
	} 
}
