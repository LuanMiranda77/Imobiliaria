package DAO;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import Control.TipoControl;
import DTO.ClienteDTO;
import DTO.CompraDTO;
import DTO.FuncionarioDTO;
import DTO.ImovelDTO;
import DTO.TipoImovelDTO;

public class CompraDAO implements ICompraDAO{
	ClienteDAO banco = new ClienteDAO();
	FuncionarioDAO b= new FuncionarioDAO();
	ImovelDAO conImovel = new ImovelDAO();
	
	/**
	 * autor luan miranda
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1")); 
    
	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como parametros.
	 * O try catch converte a Central em Xml, abre um novo arquivo e escreve os dados da central nele 
	 */
	 private ArrayList<ArrayList<String>>lista=new ArrayList<>();
	 
	 public CompraDAO() {
		 lista = recuperarCentral("xml/Compra.xml");
		
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
	public void cadastrar(CompraDTO novo) {
		ArrayList<String> compra = new ArrayList<>();
			compra.add(""+getId());
			compra.add(""+novo.getCliente().getId());
			compra.add(""+novo.getIdImovel().getId());
			compra.add(novo.getDataCompra());
			compra.add(novo.getPagamento());
			compra.add(novo.getPorcentagemCompra());
			compra.add(""+novo.getIdFuncionario().getId());
			lista.add(compra);
		    salvarCentral(lista, "xml/compra.xml");
	}

	@Override
	public void excluir(int id) {
		lista.remove(id-1);
	    salvarCentral(lista, "xml/compra.xml");
	}

	@Override
	public CompraDTO listaCompraDTO() {
		CompraDTO compra = new CompraDTO();
		ArrayList<CompraDTO> listaComp = new ArrayList<>();
		
		for(int cont= 0; cont<lista.size();cont++) {
			CompraDTO novo = new CompraDTO();
			novo.setId(Integer.parseInt(lista.get(cont).get(0)));
			ClienteDTO n = new ClienteDTO();
			n=banco.pesquisar(Integer.parseInt(lista.get(cont).get(1)));
			novo.setCliente(n);
			ImovelDTO imovel = new ImovelDTO();
			imovel=conImovel.pesquisarImovel(Integer.parseInt(lista.get(cont).get(2)));
			TipoImovelDTO tipo = new TipoImovelDTO();
			tipo=TipoControl.pesquisarInt(imovel.getTipo().getId());
			imovel.setTipo(tipo);
			novo.setIdImovel(imovel);
			novo.setDataCompra(lista.get(cont).get(3));
			novo.setPagamento(lista.get(cont).get(4));
			novo.setPorcentagemCompra(lista.get(cont).get(5));
			FuncionarioDTO f = new FuncionarioDTO();
			f.setId(Integer.parseInt(lista.get(cont).get(6)));
			f=b.pesquisarFuncionario(f);
			novo.setIdFuncionario(f);
			listaComp.add(novo);
			   
		}
		compra.setArrayDTO(listaComp);
		return compra;
	}

	@Override
	public CompraDTO pesquisar(int id) {
		CompraDTO novo = new CompraDTO();
		
		ClienteDAO banco = new ClienteDAO();
		FuncionarioDAO b= new FuncionarioDAO();
		
		 String id1=""+id;
		
		for(int cont= 0; cont<lista.size();cont++) {
			if(id1.equals(lista.get(cont).get(0))) {
				novo.setId(Integer.parseInt(lista.get(cont).get(0)));
				ClienteDTO n = new ClienteDTO();
				n=banco.pesquisar(Integer.parseInt(lista.get(cont).get(1)));
				novo.setCliente(n);
				ImovelDTO imovel = new ImovelDTO();
				imovel=conImovel.pesquisarImovel(Integer.parseInt(lista.get(cont).get(2)));
				TipoImovelDTO tipo = new TipoImovelDTO();
				tipo=TipoControl.pesquisarInt(imovel.getTipo().getId());
				imovel.setTipo(tipo);
				novo.setIdImovel(imovel);
				novo.setDataCompra(lista.get(cont).get(3));
				novo.setPagamento(lista.get(cont).get(4));
				novo.setPorcentagemCompra(lista.get(cont).get(5));
				FuncionarioDTO f = new FuncionarioDTO();
				f.setId(Integer.parseInt(lista.get(cont).get(6)));
				f=b.pesquisarFuncionario(f);
				novo.setIdFuncionario(f);
			}
		}
		return novo;
	}

	@Override
	public void editar(CompraDTO editado) {
		 String id=""+editado.getId();
		
		for(int cont= 0; cont<lista.size();cont++) {
			for(int i=0;i<5;i++) {
					if(id.equals(lista.get(cont).get(0))) {
						lista.get(cont).set(1, ""+editado.getCliente().getId());
						lista.get(cont).set(2, editado.getDataCompra());
						lista.get(cont).set(3, editado.getPagamento());
						lista.get(cont).set(4, editado.getPorcentagemCompra());
						lista.get(cont).set(5, ""+editado.getIdFuncionario().getId());
						salvarCentral(lista, "xml/compra.xml");
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
