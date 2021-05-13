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
import View.ExceptionCliente;
import View.ExceptionDivida;

/*
 * @ autor dessa luan miranda 
 */

public class ClienteDAO implements IClienteDAO {
	
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
	private   DividaDTO divida= new DividaDTO();
	private   DividaDAO d = new DividaDAO();
    
	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como parametros.
	 * O try catch converte a Central em Xml, abre um novo arquivo e escreve os dados da central nele 
	 */
	 private ArrayList<ArrayList<String>>lista=new ArrayList<>();
	 
	 public ClienteDAO() {
		 lista = recuperarCentral("xml/cliente.xml");
		
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
	public void cadastrar(ClienteDTO novo)  throws ExceptionCliente {
		ArrayList<String> cliente = new ArrayList<>();
		
		if(lista.size()==0) {
			cliente.add(""+getId());
			cliente.add(novo.getNome());
			cliente.add(novo.getEndereco());
			cliente.add(novo.getCpf());
			cliente.add(novo.getTelefone());
			cliente.add(""+novo.getDivida().getId());
			lista.add(cliente);
		    salvarCentral(lista, "xml/cliente.xml");
	}
		else if(lista.size()>0) {
			for(int cont= 0; cont<lista.size();cont++) {
				for(int i=0;i<5;i++) {
						if(novo.getCpf().equals(lista.get(cont).get(3))) {
							throw new ExceptionCliente();
					}
				}
				
			}
			cliente.add(""+getId());
			cliente.add(novo.getNome());
			cliente.add(novo.getEndereco());
			cliente.add(novo.getCpf());
			cliente.add(novo.getTelefone());
			cliente.add(""+novo.getDivida().getId());
			
			lista.add(cliente);
		    salvarCentral(lista, "xml/cliente.xml");
		}
	}

	@Override
	public void excluir(int id) throws ExceptionDivida{
		ClienteDTO cliente = pesquisar(id);
		DividaDAO divida = new DividaDAO();
		boolean band =false;
        for(DividaDTO e : divida.lista().getLista()) {
        	if(e.getCliente().getId()==cliente.getId()) {
    			throw new ExceptionDivida();
    		}
        	
        }
		lista.remove(id-1);
	    salvarCentral(lista, "xml/cliente.xml");
	}

	@Override
	public ClienteDTO lista() {
		ClienteDTO listaCliente = new ClienteDTO();
	
		ArrayList<ClienteDTO> lista1 = new ArrayList<>();

		
		for(int cont= 0; cont<lista.size();cont++) {
			ClienteDTO cliente = new ClienteDTO();
			//cont++;
			cliente.setId(Integer.parseInt(lista.get(cont).get(0)));
			cliente.setNome((lista.get(cont).get(1)));
			cliente.setEndereco((lista.get(cont).get(2)));
			cliente.setCpf((lista.get(cont).get(3)));
			cliente.setTelefone((lista.get(cont).get(4)));
			divida=d.pesquisar(Integer.parseInt((lista.get(cont).get(5))));
			cliente.setDivida(divida);
			lista1.add(cliente);
			}
			//cont--;
		listaCliente.setArrayDTO(lista1);
		return listaCliente;
	}

	@Override
	public ClienteDTO pesquisar(int cod) {
		  ClienteDTO cliente = new ClienteDTO();
		  cliente.setNome("");
		  String id = ""+cod;
			
			for(int cont= 0; cont<lista.size();cont++) {
						if(id.equals(lista.get(cont).get(0))) {
							cliente.setId(Integer.parseInt(lista.get(cont).get(0)));
							cliente.setNome((lista.get(cont).get(1)));
							cliente.setEndereco((lista.get(cont).get(2)));
							cliente.setCpf((lista.get(cont).get(3)));
							cliente.setTelefone((lista.get(cont).get(4)));
							divida=d.pesquisar(Integer.parseInt((lista.get(cont).get(5))));
							cliente.setDivida(divida);
						}
				}
			return cliente;
	}

	@Override
	public void editar(ClienteDTO editado) {
		
		 String id=""+editado.getId();
			ArrayList<ArrayList<String>> lista = recuperarCentral("xml/cliente.xml");
			
			for(int cont= 0; cont<lista.size();cont++) {
				for(int i=0;i<1;i++) {
						if(id.equals(lista.get(cont).get(0))) {
							lista.get(cont).set(1, editado.getNome());
							lista.get(cont).set(2, editado.getEndereco());
							lista.get(cont).set(3, editado.getCpf());
							lista.get(cont).set(4, editado.getTelefone());
							lista.get(cont).set(5,""+editado.getDivida().getId());
						    salvarCentral(lista, "xml/cliente.xml");
						}
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
