package DAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import DTO.ImovelDTO;
import DTO.TipoImovelDTO;

public class ImovelDAO implements IImovelDAO {
	
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
    
	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como parametros.
	 * O try catch converte a Central em Xml, abre um novo arquivo e escreve os dados da central nele 
	 */
	 private ArrayList<ArrayList<String>>lista=new ArrayList<>();
	 
	 public ImovelDAO() {
		 lista = recuperarCentral("xml/imovel.xml");
		
	}
		
		public void salvarCentral(ArrayList<ArrayList<String>> c, String a) {


		try {
			String xml = xstream.toXML(c);

			File arquivo = new File(a);
			arquivo.createNewFile();
			PrintWriter gravar = new PrintWriter(arquivo);
			gravar.print(xml);
			gravar.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		/**
		 * Ela retorna um objeto do tipo central
		 * recebe uma String com o nome do arquivo
		 * Se o arquivo já esxistir ele tranforma o xml em objeto
		 * E se não existir ele cria uma nova central
		 */
	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<String>> recuperarCentral(String n) {
		File arquivo = new File(n);

			try {
				if (arquivo.exists()) {
					FileInputStream arq = new FileInputStream(arquivo);
					return ((ArrayList<ArrayList<String>>) xstream.fromXML(arq));
				}
			} catch (FileNotFoundException a) {
				a.printStackTrace();
			}
		return new ArrayList<ArrayList<String>>();
	}


	@Override
	public void cadastrarImovel(ImovelDTO novo) {
ArrayList<String> imovel = new ArrayList<>();
		
			imovel.add(""+getId());
			imovel.add(""+novo.getTipo().getId());//
			imovel.add(novo.getEstado());
			imovel.add(""+novo.getFrente());
			imovel.add(""+novo.getFundo());
			imovel.add(""+novo.getAreaQuadrada());
			imovel.add(novo.getEnderco());
			imovel.add(""+novo.getPreco());
			imovel.add(""+novo.getStatus());
			lista.add(imovel);
		    salvarCentral(lista, "xml/imovel.xml");
	}

	@Override
	public void excluirImovel(int id) {
		lista.remove(id-1);
	    salvarCentral(lista, "xml/imovel.xml");
			}

	@Override
	public ImovelDTO listaImovelDTO() {
		ImovelDTO listaImovel = new ImovelDTO();
		ArrayList<ImovelDTO> lista1 = new ArrayList<>();
		
		for(int cont= 0; cont<lista.size();cont++) {
			ImovelDTO imovel = new ImovelDTO();
			imovel.setId(Integer.parseInt(lista.get(cont).get(0)));
			TipoImovelDTO tipo = new TipoImovelDTO();
			tipo.setId(Integer.parseInt(lista.get(cont).get(1)));
			imovel.setTipo(tipo);
			imovel.setEstado((lista.get(cont).get(2)));
			imovel.setFrente(Float.parseFloat(lista.get(cont).get(3)));
			imovel.setFundo(Float.parseFloat(lista.get(cont).get(4)));
			imovel.setAreaQuadrada(Float.parseFloat(lista.get(cont).get(5)));
			imovel.setEnderco(lista.get(cont).get(6));
			imovel.setPreco(Float.parseFloat(lista.get(cont).get(7)));
			imovel.setStatus(lista.get(cont).get(8));
			lista1.add(imovel);

		}
		listaImovel.setArrayDTO(lista1);
		return listaImovel;
	}

	@Override
	public ImovelDTO pesquisarImovel(int id) {
		 ImovelDTO imovel = new ImovelDTO();
			String cod = ""+id;
			
			for(int cont= 0; cont<lista.size();cont++) {
						if(cod.equals(lista.get(cont).get(0))) {
							imovel.setId(Integer.parseInt(lista.get(cont).get(0)));
							TipoImovelDTO tipo = new TipoImovelDTO();
							tipo.setId(Integer.parseInt(lista.get(cont).get(1)));
							imovel.setTipo(tipo);
							imovel.setEstado((lista.get(cont).get(2)));
							imovel.setFrente(Float.parseFloat(lista.get(cont).get(3)));
							imovel.setFundo(Float.parseFloat(lista.get(cont).get(4)));
							imovel.setAreaQuadrada(Float.parseFloat(lista.get(cont).get(5)));
							imovel.setEnderco(lista.get(cont).get(6));
							imovel.setPreco(Float.parseFloat(lista.get(cont).get(7)));
							imovel.setStatus(lista.get(cont).get(8));
						}
				}
			return imovel;

	}

	@Override
	public void editarImovel(ImovelDTO editado) {
		String id=""+editado.getId();
		
		for(int cont= 0; cont<lista.size();cont++) {
					if(id.equals(lista.get(cont).get(0))) {
						lista.get(cont).set(1,""+editado.getTipo().getId());
						lista.get(cont).set(2, editado.getEstado());
						lista.get(cont).set(3,""+editado.getFrente());
						lista.get(cont).set(4,""+editado.getFundo());
						lista.get(cont).set(5,""+editado.getAreaQuadrada());
						lista.get(cont).set(6, editado.getEnderco());
						lista.get(cont).set(7,""+editado.getPreco());
						lista.get(cont).set(8, editado.getStatus());
						
					    salvarCentral(lista, "xml/imovel.xml");
			}
		}
		
	}
	public int tamanho() {
		return lista.size();
	}
	public int getId() {
		 int id =tamanho()+1;
		return id;
	}

}
