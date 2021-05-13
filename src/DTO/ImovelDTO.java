package DTO;
/**
 * 
 * @author André
 * 
 */
import java.util.ArrayList;
public class ImovelDTO {
	private int id;
	private float frente, fundo, areaQuadrada; 
	private double preco;
	private String enderco, estado, status;
	private TipoImovelDTO tipo;
	private ArrayList<ImovelDTO> arrayDTO;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ArrayList<ImovelDTO> getArrayDTO() {
		return arrayDTO;
	}
	public void setArrayDTO(ArrayList<ImovelDTO> arrayDTO) {
		this.arrayDTO = arrayDTO;
	}
	public TipoImovelDTO getTipo() {
		return tipo;
	}
	public void setTipo(TipoImovelDTO tipo) {
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
	public void setFrente(float tamanho) {
		this.frente = tamanho;
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
	
	
	
}
