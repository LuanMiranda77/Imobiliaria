package Model.contabilidade;
/**
 * 
 * @author André
 * 
 */
public class FacadeContabilidade {
	private ContabilidadeBairrosMaisVendidos cb = new ContabilidadeBairrosMaisVendidos();
	private ContabilidadeFuncionarioDoMes cf = new ContabilidadeFuncionarioDoMes();
	private ContabilidadeDespesasDosFuncionarios cdf = new ContabilidadeDespesasDosFuncionarios();
	private ContabilidadeCompraCliente cp = new ContabilidadeCompraCliente();
	private ContabilidadeVendaComMaiorPorcentagem cmp = new ContabilidadeVendaComMaiorPorcentagem();
	private ContabilidadeImoveisVendidos iv = new ContabilidadeImoveisVendidos();
	private ContabilidadeClienteDivida cd = new ContabilidadeClienteDivida();
	private ContabilidadeLucroMes lm = new ContabilidadeLucroMes();
	private ContabilidadeCartas cartaD = new ContabilidadeCartas();
	private ContabilidadeCartaCobranca cartaC = new ContabilidadeCartaCobranca();
	
	public void cartaDemissao() {
		cartaD.cartaDemissao();
	}
	public void cartaCobranca() {
		cartaC.cartaCobraca();
	}
	public void bairrosMaisVendidos(String x) {
		cb.bairros(x);
	}
	public void funcionarioMes(String x) {
		cf.FuncionarioVendas(x);
	}
	public void despesasDosFuncionarios() {
		cdf.despesas();
	}
	public void compraCliente(String cpf) {
		cp.compraCliente(cpf);
	}
	public void lucroMes(String data) {
		lm.lucroNoMes(data);
	}
	public void vendaMaiorPorcentagem(String data) {
		cmp.vendaComMaiorPorcentagem(data);
	}
	public void imoveisVendidos(String data) {
		iv.imoveisVendidos(data);
	}
	public void clienteDivida() {
		cd.ClienteDivida();
	}
}
