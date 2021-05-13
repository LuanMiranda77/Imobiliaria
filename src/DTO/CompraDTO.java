package DTO;
import java.util.ArrayList;

/**
 * 
 * @author André
 * 
 */


public class CompraDTO {
	private String pagamento, porcentagemCompra, dataCompra;
	private int id;
	private ClienteDTO cliente;
	private FuncionarioDTO idFuncionario;
	private ArrayList<CompraDTO> arrayDTO;
	private ImovelDTO idImovel;
	
	
	public ArrayList<CompraDTO> getArrayDTO() {
		return arrayDTO;
	}
	public void setArrayDTO(ArrayList<CompraDTO> arrayDTO) {
		this.arrayDTO = arrayDTO;
	}
	public FuncionarioDTO getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(FuncionarioDTO idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	
	public String getPagamento() {
		return pagamento;
	}
	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}
	public String getPorcentagemCompra() {
		return porcentagemCompra;
	}
	public void setPorcentagemCompra(String porcentagemCompra) {
		this.porcentagemCompra = porcentagemCompra;
	}
	public String getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ImovelDTO getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(ImovelDTO idImovel) {
		this.idImovel = idImovel;
	}
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	
	
	

}
