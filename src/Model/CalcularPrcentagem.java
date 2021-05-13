package Model;

public class CalcularPrcentagem implements PorcentagemStrategy{

	public double calcularPorcentagem(double p) {
			if(p<10000 && p>0) {
				return p*0.05;
			}
			else if(p>=10000 && p<30000) {
				return p*0.10;
			}
			else if(p>=30000 && p<50000) {
				return p*0.15;
			}
			return p*0.20;
		}
	
	public double calcularPorcent(double p) {
		if(p<10000 && p>0) {
			return 0.05;
		}
		else if(p>=10000 && p<30000) {
			return 0.10;
		}
		else if(p>=30000 && p<50000) {
			return 0.15;
		}
		return 0.20;
	}

}
