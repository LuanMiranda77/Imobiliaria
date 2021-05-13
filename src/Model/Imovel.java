package Model;



import DAO.IImovelDAO;
import DAO.ImovelDAO;
import DAO.Sql.TestConexao;
import DTO.ImovelDTO;

public class Imovel {
	private int id;
	private float frente, fundo, areaQuadrada; 
	private double preco;
	private String enderco, estado, status;
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	private TipoImovel tipo;
	private IImovelDAO bd;
	
	public Imovel() {
		bd = new ImovelDAO();
	}
	
	public TipoImovel getTipo() {
		return tipo;
	}
	public void setTipo(TipoImovel tipo) {
		this.tipo = tipo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getFrente() {
		return frente;
	}
	public void setFrente(float frente) {
		this.frente = frente;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getEnderco() {
		return enderco;
	}
	public void setEnderco(String enderco) {
		this.enderco = enderco;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public float getFundo() {
		return fundo;
	}

	public void setFundo(float fundo) {
		this.fundo = fundo;
	}
	public float getAreaQuadrada() {
		return areaQuadrada;
	}

	public void setAreaQuadrada(float areaQuadrada) {
		this.areaQuadrada = areaQuadrada;
	}

	public void cadastrarImovel(ImovelDTO cdto) {
		TestConexao.retornar().fabricarImovel().cadastrarImovel(cdto);
	}
	
	public void editarImovel(ImovelDTO cdto) {
		TestConexao.retornar().fabricarImovel().editarImovel(cdto);
	}
	public void excluirImovel(ImovelDTO cdto) {
		TestConexao.retornar().fabricarImovel().excluirImovel(cdto.getId());
	}
	public ImovelDTO listaImovelDTO(){
		return TestConexao.retornar().fabricarImovel().listaImovelDTO();
	}
	public ImovelDTO pesquisarImovel(ImovelDTO cdto) {
		return TestConexao.retornar().fabricarImovel().pesquisarImovel(cdto.getId());
	}
	
}
