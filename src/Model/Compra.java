package Model;

import DAO.Sql.TestConexao;
import DTO.CompraDTO;


public class Compra {
	private String pagamento, porcentagemCompra, dataCompra;
	private int id;
	private Cliente cpf;
	private Funcionario idFuncionario;
	private double valor;
	
	public Cliente getCpf() {
		return cpf;
	}
	public void setCpf(Cliente cpf) {
		this.cpf = cpf;
	}
	public Funcionario getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(Funcionario idFuncionario) {
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
	
	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void cadastrarImovel(CompraDTO cdto) {
		TestConexao.retornar().fabricarComp().cadastrar(cdto);
	}
	public void excluirImovel(CompraDTO cdto) {
		TestConexao.retornar().fabricarComp().excluir(cdto.getId());
	}
	public CompraDTO listaCompraDTO(){
		return TestConexao.retornar().fabricarComp().listaCompraDTO();
	}
	public CompraDTO pesquisarImovel(CompraDTO cdto) {
		return TestConexao.retornar().fabricarComp().pesquisar(cdto.getId());
	}

}
