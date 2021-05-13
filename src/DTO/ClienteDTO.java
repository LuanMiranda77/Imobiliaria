package DTO;
/**
 * 
 * @author André
 * 
 */
import java.util.ArrayList;

/**
 * 
 * @author André
 * 
 */

public class ClienteDTO {
	private int id;
	private String nome, cpf, endereco, telefone;
	private DividaDTO DividaDTO;
	private ArrayList<ClienteDTO> arrayDTO;
	
	
	
	public ArrayList<ClienteDTO> getArrayDTO() {
		return arrayDTO;
	}

	public void setArrayDTO(ArrayList<ClienteDTO> arrayDTO) {
		this.arrayDTO = arrayDTO;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DividaDTO getDivida() {
		return DividaDTO;
	}

	public void setDivida(DividaDTO DividaDTO) {
		this.DividaDTO = DividaDTO;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	

}
