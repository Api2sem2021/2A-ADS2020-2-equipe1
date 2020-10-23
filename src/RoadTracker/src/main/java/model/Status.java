package model;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.swing.JOptionPane;

@Entity
@Table(name="status")
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String tipo;
	private String inicio;
	private String fim;
	private String total;

	
	//um ou mais status pertence a um funcion�rio
	@ManyToOne
	@JoinColumn(name = "motorista", nullable = false, foreignKey = @ForeignKey(name = "fk_motoristas_cpf")) //coluna da tabela pai
	private Motorista motorista = new Motorista();
	
	//um ou mais status pertence a uma viagem
	@ManyToOne
	@JoinColumn(name = "viagem", nullable = false, foreignKey = @ForeignKey(name = "fk_viagens_id")) //coluna da tabela pai
	private Viagem viagem = new Viagem();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getInicio() {
		return inicio;
	}
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}
	public String getFim() {
		return fim;
	}
	public void setFim(String fim) {
		this.fim = fim;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	public Motorista getMotorista() {
		return motorista;
	}
	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}
	public Viagem getViagem() {
		return viagem;
	}
	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}
	
	// aqui o par�metro tipo deve depender do bot�o acionado
	public void enviarStatus(String inicio, String fim, String tipo, String total, String cpfMotorista, String idViagem) {
		EntityManager con = new ConnectionFactory().getConnection();
		

		this.setInicio("0"); // pegar pegar o hor�rio atual ainda
		this.setFim("0"); //acho que isso � setado s� depois n�
		this.setTipo(tipo);
		this.setTotal("0"); //acho que isso � setado s� depois n�
		
		motorista.setCpf(cpfMotorista);
		this.setMotorista(motorista);
		
		viagem.setId(Integer.parseInt(idViagem));
		this.setViagem(viagem);
		
		try {
			con.getTransaction().begin();
			con.persist(this);
			con.getTransaction().commit();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um problema ao enviar o aviso. Tente novamente.\nErro: "+ e, "Erro", JOptionPane.ERROR_MESSAGE);
			con.getTransaction().rollback();
		}
		finally {
			con.close();
		}
		
	}
	
}
