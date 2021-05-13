package DAO;

/*
 * Autor luan miranda
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.itextpdf.text.log.SysoCounter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import DTO.FuncionarioDTO;
import View.ExceptionFuncionario;

public class FuncionarioDAO implements IFuncionarioDAO{
	
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
    
	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como parametros.
	 * O try catch converte a Central em Xml, abre um novo arquivo e escreve os dados da central nele 
	 */
	 private ArrayList<ArrayList<String>>listaFuncionario=new ArrayList<>();
	 
	 public FuncionarioDAO() {
		 
		 listaFuncionario = recuperarCentral("xml/fun.xml");
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
	public void cadastrarFuncionario(FuncionarioDTO novo) throws ExceptionFuncionario {
		for(FuncionarioDTO a: listaDeFuncionarioDTO().getLista()) {
			if(a.getId()==novo.getId()) {
				throw new ExceptionFuncionario();
			}
		}
		ArrayList<String> funcionario= new ArrayList<>();
		funcionario.add(""+getId());
		funcionario.add(novo.getNome());
		funcionario.add(novo.getCargo());
		funcionario.add(novo.getSenha());
		funcionario.add(""+novo.getSalario());
		
		listaFuncionario.add(funcionario);
	    salvarCentral(listaFuncionario, "xml/fun.xml");
	}

	@Override
	public void excluirFuncionario(FuncionarioDTO fdto) {
		listaFuncionario.remove(fdto.getId()-1);
	    salvarCentral(listaFuncionario, "xml/fun.xml");
	}
	
	@Override
	public void editarFuncionario(FuncionarioDTO editado) {
		
		String id=""+editado.getId();
		ArrayList<ArrayList<String>> lista = recuperarCentral("xml/fun.xml");
		
		for(int cont= 0; cont<lista.size();cont++) {
					if(id.equals(lista.get(cont).get(0))) {
						lista.get(cont).set(1, editado.getNome());
						lista.get(cont).set(2, editado.getCargo());
						lista.get(cont).set(3, editado.getSenha());
						lista.get(cont).set(4, ""+editado.getSalario());
					    salvarCentral(lista, "xml/fun.xml");
			}
		}
	}

	@Override
	public FuncionarioDTO listaDeFuncionarioDTO() {
		FuncionarioDTO fundto = new FuncionarioDTO();
		ArrayList<FuncionarioDTO> listaftdo = new ArrayList<>();
		
		
		for(int cont= 0; cont<listaFuncionario.size();cont++) {
			    ++cont;
			    fundto.setId(cont);
				listaftdo.add(pesquisarFuncionario(fundto));
				cont--;
		}
		fundto.setLista(listaftdo);
		return fundto;
	}

	@Override
	public FuncionarioDTO pesquisarFuncionario(FuncionarioDTO fdto) {
		FuncionarioDTO novo = new FuncionarioDTO();
		
		 String id=""+fdto.getId();
		
		for(int cont= 0; cont<listaFuncionario.size();cont++) {
					if(id.equals(listaFuncionario.get(cont).get(0))) {
						novo.setId(Integer.parseInt(listaFuncionario.get(cont).get(0)));
						novo.setNome(listaFuncionario.get(cont).get(1));
						novo.setCargo(listaFuncionario.get(cont).get(2));
						novo.setSenha(listaFuncionario.get(cont).get(3));
						novo.setSalario(Float.parseFloat(listaFuncionario.get(cont).get(4)));
					}
		}
		return novo;
	}

	@Override
	public FuncionarioDTO verificaLogin(FuncionarioDTO fdto) {
		FuncionarioDTO f2= new FuncionarioDTO(); 
		for (FuncionarioDTO f :listaDeFuncionarioDTO().getLista()) {
			if(f.getNome().equals(fdto.getNome()) && f.getSenha().equals(fdto.getSenha())) {
				return f2=f;
			}
		}
		
		f2.setId(-1);
		return f2;
	}
	public int tamanho() {
		return listaFuncionario.size();
	}
	public int getId() {
		 int id =tamanho()+1;
		return id;
	}

	

}
