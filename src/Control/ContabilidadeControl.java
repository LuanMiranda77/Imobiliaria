package Control;
/**
 * 
 * @author André
 * 
 */
import Model.contabilidade.FacadeContabilidade;

public class ContabilidadeControl {
	FacadeContabilidade f = new FacadeContabilidade();
	
	public void bairrosMaisVendidos(String x) {
		f.bairrosMaisVendidos(x);
	}
	public void cartaCobraca() {
		f.cartaCobranca();
	}
	public void cartaDemissao() {
		f.cartaDemissao();
	}
	public void clienteComDivida() {
		f.clienteDivida();
	}
	public void compraCliente(String cpf) {
		f.compraCliente(cpf);
	}
	public void funcionarioDoMes(String data) {
		f.funcionarioMes(data);
	}
	public void despesasFuncionarios() {
		f.despesasDosFuncionarios();
	}
	public void imoveisVendidos(String data) {
		f.imoveisVendidos(data);
	}
	public void lucroDoMes(String data) {
		f.lucroMes(data);
	}
	public void vendaComMaiorPorcentagem(String data) {
		f.vendaMaiorPorcentagem(data);
	}
}