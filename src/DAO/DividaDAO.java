package DAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import DTO.ClienteDTO;
import DTO.DividaDTO;


/*
 * @ autor dessa luan miranda 
 */

public class DividaDAO implements IDividaDAO {
	
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
    
	/**
	 * O metodo salvar Central recebe um objeto do divida "Central" e uma String como parametros.
	 * O try catch converte a Central em Xml, abre um novo arquivo e escreve os dados da central nele 
	 */
	 private ArrayList<ArrayList<String>>lista=new ArrayList<>();
	 
	 public DividaDAO() {
		 lista = recuperarCentral("xml/divida.xml");
		
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
		 * Ela retorna um objeto do divida central
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
	public void cadastrar(DividaDTO novo)  {
		ArrayList<String> divida = new ArrayList<>();
		    divida.add(""+getId());
            divida.add(novo.getData());		
			divida.add(""+novo.getPreco());
			divida.add(""+novo.getCliente().getId());
			lista.add(divida);
		    salvarCentral(lista, "xml/divida.xml");
	}

	@Override
	public void excluir(int cod) {
		lista.remove(cod-1);
	    salvarCentral(lista, "xml/divida.xml");
	}

	@Override
	public DividaDTO lista() {
		DividaDTO listaDivida = new DividaDTO();
		ArrayList<DividaDTO> lista1 = new ArrayList<>();
		ClienteDAO c = new ClienteDAO();
	
		
		for(int cont= 0; cont<lista.size();cont++) {
			DividaDTO divida = new DividaDTO();
			divida.setId(Integer.parseInt(lista.get(cont).get(0)));
			divida.setData((lista.get(cont).get(1)));
			divida.setPreco(Float.parseFloat(lista.get(cont).get(2)));
			ClienteDTO cliente;
			cliente=c.pesquisar(Integer.parseInt(lista.get(cont).get(3)));
			divida.setCliente(cliente);
			lista1.add(divida);
		}
		listaDivida.setLista(lista1);
		return listaDivida;
	}

	@Override
	public 	DividaDTO pesquisar(int cod) {
			DividaDTO divida = new DividaDTO();
			ClienteDAO c = new ClienteDAO();
			ClienteDTO cliente;
			String id = ""+cod;
			
			for(int cont= 0; cont<lista.size();cont++) {
						if(id.equals(lista.get(cont).get(0))) {
							divida.setId(Integer.parseInt(lista.get(cont).get(0)));
							divida.setData((lista.get(cont).get(1)));
							divida.setPreco(Float.parseFloat(lista.get(cont).get(2)));
							cliente=c.pesquisar(Integer.parseInt(lista.get(cont).get(3)));
							divida.setCliente(cliente);
						}
				}
			return divida;
	}

	@Override
	public void editar(	DividaDTO editado) {
		
		 String id=""+editado.getId();
			
			for(int cont= 0; cont<lista.size();cont++) {
						if(id.equals(lista.get(cont).get(0))) {
							lista.get(cont).set(1, editado.getData());
							lista.get(cont).set(2, ""+editado.getPreco());
							lista.get(cont).set(3,""+ editado.getCliente().getId());
						    salvarCentral(lista, "xml/divida.xml");
						}
				}
			
	}
	
	public int tamanho() {
		return lista.size();
	}

	public int getId() {
	return tamanho()+1;
	}
}
