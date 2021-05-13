package View;

/**
 * @author Agemiro
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Control.ImovelControl;
import Control.TipoControl;
import DTO.ImovelDTO;
import DTO.TipoImovelDTO;

public class TelaCadastroImoveis extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton botaoCadastrar;
	private JComboBox<String> tipoImovel;
	private JFormattedTextField tamanhoImovel;
	private JTextField area;
	private JTextField precoImovel;
	private JTextArea  estadoImovel;
	private JTextField cidade;
	private JTextField rua; 
	private JTextField bairro; 
	private JTextField numero;
	
	public TelaCadastroImoveis(boolean condicao, ImovelDTO dto) {
		
		setTitle("Cadastro de Imóveis");
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		getContentPane().setBackground(new Color(240, 240, 220));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		fields();
		botoes(condicao, dto);
		labels();
						
		this.setVisible(true);
		
	}
	
	public void labels() {
		//titulo da tela
		JLabel titulo = new LabelPadrao("CADASTRO DE IMÓVEIS", 230, -5,500, 50);
        titulo.setFont(new Font("Masque",Font.BOLD,25));
        titulo.setForeground(Color.darkGray);
		add(titulo);
		
		JLabel barra = new JLabel();
		barra.setBounds(1,-20,800,60);
		barra.setBackground(Color.GRAY);
		barra.setOpaque(isBackgroundSet());
		add(barra);	
		
		JLabel logo = new JLabel(new ImageIcon("Icon/logo.png"));
        logo.setBounds(520, 350, 250, 200);
        add(logo);

		JLabel tipo = new LabelPadrao("TIPO", 20, 40, 50, 30);
		add(tipo);
		JLabel tamanho = new LabelPadrao("TAMANHO", 380, 100, 110, 30);
		add(tamanho);		
		JLabel area = new LabelPadrao("AREA²", 550, 100, 110, 30);
		add(area);
		JLabel estado = new LabelPadrao("DESCRICAO", 20, 100, 80, 30);
		add(estado);		
		JLabel preco = new LabelPadrao("PREÇO (R$)", 380, 190, 70, 30);
		add(preco);		
		JLabel cidade = new LabelPadrao("CIDADE", 575, 280, 60, 30);
		add(cidade);		
		JLabel rua = new LabelPadrao("RUA / AVENIDA", 20, 280, 110, 30);
		add(rua);		
		JLabel numero = new LabelPadrao("NÚMERO", 275, 280, 70, 30);	
		add(numero);		
		JLabel bairro = new LabelPadrao("BAIRRO", 350, 280, 70, 30);
		add(bairro);		
		JLabel imagem = new JLabel(new ImageIcon("icon/casa.png"));
		imagem.setBounds(-5, 273, 512, 512);
		add(imagem);
		
	}
	
	public void fields() {
		
		String [] arrayTipos = new String[TipoControl.listaTipoImovelDTO().getLista().size()];
		int cont=0;
		for (TipoImovelDTO tipo : TipoControl.listaTipoImovelDTO().getLista()) {
			arrayTipos[cont]=tipo.getNome();
			cont++;
		}
		tipoImovel = new JComboBox<String>(arrayTipos);
		tipoImovel.setBounds(20, 70, 140, 35);
		add(tipoImovel);
		
		MaskFormatter masc;
		try {
			masc = new MaskFormatter("**.**X**.**");
			tamanhoImovel = new JFormattedTextField(masc);
		} catch (ParseException e) {}
		
		tamanhoImovel.setBounds(380, 130, 140, 35);
		add(tamanhoImovel);
		tamanhoImovel.addKeyListener(new KeyListener() {
			
		
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				try {
				String []t = tamanhoImovel.getText().split("X");
				float frente = Float.parseFloat(t[0]);
				float fundo =Float.parseFloat(t[1]);
				area.setText(""+frente*fundo);
				}catch (Exception e2) {
					
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		area= new TextFieldPadrao(550, 130, 140, 35);
		add(area);
		area.setEditable(false);
		
		precoImovel = new JTextFieldSoNumero();
		precoImovel.setBounds(380, 220, 140, 35);
		add(precoImovel);
			
		estadoImovel = new JTextArea();
		estadoImovel.setBounds(20, 130, 300, 130);
		add(estadoImovel);
	
		rua = new TextFieldPadrao(20, 310, 200, 35);
		add(rua);
		
		numero = new TextFieldPadrao(275, 310, 40, 35);
		add(numero);
		
		bairro = new TextFieldPadrao(350, 310, 200, 35);
		add(bairro);
		
		cidade = new TextFieldPadrao(575, 310, 200, 35);
		add(cidade);
		
	}

	public void botoes(boolean condicao, ImovelDTO dto) {
		
		if(condicao) {

			botaoCadastrar = new JButton("CADASTRAR", new ImageIcon("icon/ok.png"));
			botaoCadastrar.setBounds(195, 380, 150, 35);
			botaoCadastrar.setToolTipText("Clique para cadastrar o imóvel");
	
			OuvinteInternoCadastroImovel ouvinteImovel = new OuvinteInternoCadastroImovel();
	
			botaoCadastrar.addActionListener(ouvinteImovel);
			add(botaoCadastrar);
		
		}else {
				setTitle("Edição de Cadastro de Imóvel");
				
				//seta com o dto de imovel
				String[] endereçoDivisao = dto.getEnderco().split("-");
				int i = 0;
		        for (String s: endereçoDivisao) {
		            endereçoDivisao[i] = s;
		            i++;
		        }

		        String pegaTipo = "";
				for (TipoImovelDTO tipo : TipoControl.listaTipoImovelDTO().getLista()) {
			        if(dto.getTipo().getId() == tipo.getId())
			        	pegaTipo = tipo.getNome();
				}
				
				tipoImovel.setSelectedItem(pegaTipo);
				estadoImovel.setText(dto.getEstado());
				tamanhoImovel.setText(""+dto.getFrente()+dto.getFundo()); 
				area.setText(""+dto.getAreaQuadrada());
				precoImovel.setText(Double.toString(dto.getPreco()));
				rua.setText(endereçoDivisao[0]);
				numero.setText(endereçoDivisao[1]);
				bairro.setText(endereçoDivisao[2]);
				cidade.setText(endereçoDivisao[3]);
				
				JButton botaoEditar = new JButton("SALVAR", new ImageIcon("icon/ok.png"));
				botaoEditar.setBounds(195, 380, 150, 35);
				botaoEditar.setToolTipText("Clique para salvar a edição do cadastro do imóvel");
				
				botaoEditar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
					    TipoImovelDTO tipo = new TipoImovelDTO();
						tipo.setNome((String) getTipo().getSelectedItem());
						tipo=TipoControl.pesquisar(tipo);
						dto.setTipo(tipo);
						dto.setEstado(estadoImovel.getText().toUpperCase());
						String []lista = tamanhoImovel.getText().split("X");
			            dto.setFrente(Float.parseFloat(lista[0]));
			            dto.setFundo(Float.parseFloat(lista[1]));
			            dto.setAreaQuadrada(Float.parseFloat(area.getText()));
			            dto.setPreco(Float.parseFloat(precoImovel.getText()));
			            dto.setEnderco(rua.getText().toUpperCase() + "-" + numero.getText().toUpperCase() + "-" + bairro.getText().toUpperCase()
			            		+"-"+cidade.getText().toUpperCase());
			        	ImovelControl.editar(dto);
						JOptionPane.showMessageDialog(null, "Edição realizada com sucesso!", "Feito", JOptionPane.INFORMATION_MESSAGE);
			            dispose();
			            new TelaGerenciarImovel();
					}
				});
				add(botaoEditar);
		}
		
		JButton botaoVoltar = new JButton("VOLTAR", new ImageIcon("icon/voltar.png"));
		botaoVoltar.setBounds(430, 380, 150, 35);
		botaoVoltar.setToolTipText("Clique para voltar");
		botaoVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaGerenciarImovel();
			}
		});
		add(botaoVoltar);

	}
	
	public class OuvinteInternoCadastroImovel implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			
			String label = evento.getActionCommand();
			
			switch (label) {
			
				case "CADASTRAR":

			
					boolean confere = true;
					
					String tamanho = tamanhoImovel.getText().toUpperCase();
		            String pegaArea = area.getText();
					String descricao = estadoImovel.getText().toUpperCase();
					String preco = precoImovel.getText();
					String pegaRua = rua.getText().toUpperCase();
					String pegaNumero = numero.getText().toUpperCase();
					String pegaBairro = bairro.getText().toUpperCase();
					String pegaCidade = cidade.getText().toUpperCase();
					String endereco = pegaRua + "-" + pegaNumero + "-" + pegaBairro + "-" + pegaCidade;
					
					if (tamanho.equals("  .  X  .  ")) {
						JOptionPane.showMessageDialog(null, "Por favor, digite o tamanho do Imóvel!", "Aviso", JOptionPane.WARNING_MESSAGE);
						confere = false;
						break;
					}
					if (pegaArea.equals("")) {
						JOptionPane.showMessageDialog(null, "Por favor, digite o área do Imóvel!", "Aviso", JOptionPane.WARNING_MESSAGE);
						confere = false;
						break;
					}
					if (descricao.equals("")) {
						JOptionPane.showMessageDialog(null, "Por favor, digite a descrição do Imóvel!", "Aviso", JOptionPane.WARNING_MESSAGE);
						confere = false;
						break;
					}
					if(preco.equals("")) {
						JOptionPane.showMessageDialog(null, "Por favor, digite o preço do Imóvel!", "Aviso", JOptionPane.WARNING_MESSAGE);
						confere = false;
						break;
					}	
					if(pegaRua.equals("")) {
						JOptionPane.showMessageDialog(null, "Por favor, digite a rua do Imóvel!", "Aviso", JOptionPane.WARNING_MESSAGE);
						confere = false;
						break;
					}	
					if(pegaNumero.equals("")) {
						JOptionPane.showMessageDialog(null, "Por favor, digite o numero do Imóvel!", "Aviso", JOptionPane.WARNING_MESSAGE);
						confere = false;
						break;
					}
					if(pegaBairro.equals("")) {
						JOptionPane.showMessageDialog(null, "Por favor, digite o bairro do Imóvel!", "Aviso", JOptionPane.WARNING_MESSAGE);
						confere = false;
						break;
					}
					if(pegaCidade.equals("")) {
						JOptionPane.showMessageDialog(null, "Por favor, digite a cidade do Imóvel!", "Aviso", JOptionPane.WARNING_MESSAGE);
						confere = false;
						break;
					}									

					if (confere) {
						ImovelDTO dto = new ImovelDTO();
						TipoImovelDTO t = new TipoImovelDTO();
						t.setNome((String) getTipo().getSelectedItem());
						t=TipoControl.pesquisar(t);
						dto.setTipo(t);
						String []lista = tamanhoImovel.getText().split("X");
						dto.setEstado(descricao);
			            dto.setFrente(Float.parseFloat(lista[0]));
			            dto.setFundo(Float.parseFloat(lista[1]));
			            dto.setPreco(Float.parseFloat(preco));
			            dto.setAreaQuadrada(Float.parseFloat(pegaArea));
			            dto.setEnderco(endereco);
			            dto.setStatus("Vende-se");
				
			            ImovelControl.cadastrarImovel(dto);
						JOptionPane.showMessageDialog(null, "Imóvel cadastrado com sucesso!", "Cadastrado", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new TelaGerenciarImovel();
				
					}
					break;
			}

		}
		
	}
	
	public JComboBox<String> getTipo() {
		return tipoImovel;
	}

	public void setTipo(JComboBox<String> tipo) {
		this.tipoImovel = tipo;
	}
	
	public JTextField getPrecoImovel() {
		return precoImovel;
	}

	public void setPrecoImovel(JTextField precoImovel) {
		this.precoImovel = precoImovel;
	}
	public static void main(String[] args) {
		new TelaCadastroImoveis(false, null);
	}

}

