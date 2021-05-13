package DAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import DTO.TipoImovelDTO;


/*
 * @ autor dessa luan miranda 
 */

public class TipoImovelDAO implements ITipoimovel {
	
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
    
	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como parametros.
	 * O try catch converte a Central em Xml, abre um novo arquivo e escreve os dados da central nele 
	 */
	 private ArrayList<ArrayList<String>>lista=new ArrayList<>();
	 
	 public TipoImovelDAO() {
		 lista = recuperarCentral("xml/tipo.xml");
		
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
	public void cadastrar(TipoImovelDTO novo)  {
		ArrayList<String> tipo = new ArrayList<>();
		    tipo.add(""+getId());
            tipo.add(novo.getNome());		
			tipo.add(""+novo.getPocetagem());
			lista.add(tipo);
		    salvarCentral(lista, "xml/tipo.xml");
	}

	@Override
	public void excluir(int id) {
		lista.remove(id-1);
	    salvarCentral(lista, "xml/tipo.xml");
	}

	@Override
	public TipoImovelDTO lista() {
		TipoImovelDTO listatipo = new TipoImovelDTO();
		ArrayList<TipoImovelDTO> lista1 = new ArrayList<>();
		
		for(int cont= 0; cont<lista.size();cont++) {
			TipoImovelDTO tipo = new TipoImovelDTO();
			tipo.setId(Integer.parseInt(lista.get(cont).get(0)));
			tipo.setNome((lista.get(cont).get(1)));
			tipo.setPocetagem(Float.parseFloat(lista.get(cont).get(2)));
			lista1.add(tipo);
		}
		listatipo.setLista(lista1);
		return listatipo;
	}

	@Override
	public 	TipoImovelDTO pesquisar(String nome) {
			TipoImovelDTO tipo = new TipoImovelDTO();
			
			for(int cont= 0; cont<lista.size();cont++) {
						if(nome.equals(lista.get(cont).get(1))) {
							tipo.setId(Integer.parseInt(lista.get(cont).get(0)));
							tipo.setNome((lista.get(cont).get(1)));
							tipo.setPocetagem(Float.parseFloat(lista.get(cont).get(2)));
						}
				}
			return tipo;
	}
	@Override
	public 	TipoImovelDTO pesquisarInt(int id) {
		TipoImovelDTO tipo = new TipoImovelDTO();
		String n=""+id;
		for(int cont= 0; cont<lista.size();cont++) {
					if(n.equals(lista.get(cont).get(0))) {
						tipo.setId(Integer.parseInt(lista.get(cont).get(0)));
						tipo.setNome((lista.get(cont).get(1)));
						tipo.setPocetagem(Float.parseFloat(lista.get(cont).get(2)));
					}
			}
		return tipo;
}
	
	
	

	@Override
	public void editar(	TipoImovelDTO editado) {
		
		 String id=""+editado.getId();
			
			for(int cont= 0; cont<lista.size();cont++) {
						if(id.equals(lista.get(cont).get(0))) {
							lista.get(cont).set(1, editado.getNome());
							lista.get(cont).set(2, ""+editado.getPocetagem());
							
						    salvarCentral(lista, "xml/tipo.xml");
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
