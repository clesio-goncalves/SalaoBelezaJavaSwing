package ControleEstoqueGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import ControleEstoqueBanco.ClienteDAO;
import ControleEstoqueBean.Cliente;

public class JanCadCliente extends JFrame {
	String inicioTelefone = null;
	MaskFormatter maskTelefone;
	private JTextField textPesquisar;
	private JTextField textCodigo;
	private JTextField textNome;
	private JTextField textTelefone;
	private JTextField textEndereco;
	private JButton buttonPesquisar;
	private JButton buttonNovo;
	private JButton buttonAlterar;
	private JButton buttonExcluir;
	private JButton buttonAtualizar;
	private JButton buttonSalvar;
	private JButton buttonFechar;
	private JLabel labelCodigo;
	private JLabel labelNome;
	private JLabel labelTelefone;
	private JLabel labelEndereco;
	private JMenuBar menuBar;
	private JMenu menuArquivo;
	private JMenuItem subNovo;
	private JMenuItem subFechar;
	private JPanel panelPesquisarCliente;
	private JPanel panelDadosCadastrais;
	private JPanel panelBotoes;
	private JScrollPane scrollPaneInformacaoes;
	private JTable tableInformacaoes;

	public JanCadCliente() {
		super("Cadastro - Cliente");
		Componentes();
		setLocationRelativeTo(null);
		this.setResizable(false);

		preencherJTable();
		Invisivel();
	}
	
	public void Invisivel() {
		this.textNome.setEditable(false);
		this.textTelefone.setEditable(false);
		this.textEndereco.setEditable(false);
	}

	public void Visivel() {
		this.textNome.setEditable(true);
		this.textTelefone.setEditable(true);
		this.textEndereco.setEditable(true);
	}

	public void preencherJTable() {
		this.tableInformacaoes.getColumnModel().getColumn(0).setPreferredWidth(5);
		this.tableInformacaoes.getColumnModel().getColumn(1).setPreferredWidth(50);
		this.tableInformacaoes.getColumnModel().getColumn(2).setPreferredWidth(150);
		this.tableInformacaoes.getColumnModel().getColumn(3).setPreferredWidth(15);
		
		DefaultTableModel tabela = (DefaultTableModel)this.tableInformacaoes.getModel();
		tabela.setNumRows(0);
		
		ClienteDAO dao = new ClienteDAO();
		List<Cliente> listaCliente = dao.consultar();
		
		for (Cliente  cliente: listaCliente) {
			tabela.addRow(new Object[] { cliente.getCodigo(), cliente.getNome(), cliente.getEndereco(), cliente.getTelefone() });
		}
	}
	
	private void Componentes() {
		try {
		      this.maskTelefone = new MaskFormatter("(##)####-####");
		} catch (Exception e) {
		      JOptionPane.showMessageDialog(null, "Nao foi possivel inserir a mascara");
		}
		this.textPesquisar = new JTextField();
		this.textCodigo = new JTextField();
		this.textNome = new JTextField();
		this.textTelefone = new JFormattedTextField(this.maskTelefone);
		this.textEndereco = new JTextField();
		this.buttonPesquisar = new JButton();
		this.buttonNovo = new JButton();
		this.buttonAlterar = new JButton();
		this.buttonExcluir = new JButton();
		this.buttonAtualizar = new JButton();
		this.buttonSalvar = new JButton();
		this.buttonFechar = new JButton();
		this.labelCodigo = new JLabel();
		this.labelNome = new JLabel();
		this.labelTelefone = new JLabel();
		this.labelEndereco = new JLabel();
		this.menuBar = new JMenuBar();
		this.menuArquivo = new JMenu();
		this.subNovo = new JMenuItem();
		this.subFechar = new JMenuItem();
		this.panelPesquisarCliente = new JPanel();
		this.panelDadosCadastrais = new JPanel();
		this.panelBotoes = new JPanel();
		this.scrollPaneInformacaoes = new JScrollPane();
		scrollPaneInformacaoes.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.tableInformacaoes = new JTable();
		tableInformacaoes.setColumnSelectionAllowed(true);
		this.inicioTelefone = this.textTelefone.getText();
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//setDefaultCloseOperation(2);
		
		// ---------- PANEL PESQUISAR CLIENTES ----------- //
	    this.panelPesquisarCliente.setBorder(BorderFactory.createTitledBorder("Pesquisar Clientes"));
	    
	    this.textPesquisar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadCliente.this.textPesquisarActionPerformed(evt);
	    	}
	    });
	    this.buttonPesquisar.setBackground(new Color(255, 255, 255));
	    this.buttonPesquisar.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/localizar.gif")));
	    this.buttonPesquisar.setText("Pesquisar");
	    this.buttonPesquisar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadCliente.this.buttonPesquisarActionPerformed(evt);
	    	}
	    });
	    GroupLayout layoutPesquisarCliente = new GroupLayout(this.panelPesquisarCliente);
	    layoutPesquisarCliente.setHorizontalGroup(
	    	layoutPesquisarCliente.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(layoutPesquisarCliente.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(textPesquisar, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
	    			.addGap(18)
	    			.addComponent(buttonPesquisar, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
	    			.addContainerGap())
	    );
	    layoutPesquisarCliente.setVerticalGroup(
	    	layoutPesquisarCliente.createParallelGroup(Alignment.LEADING)
	    		.addGroup(layoutPesquisarCliente.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(layoutPesquisarCliente.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(textPesquisar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(buttonPesquisar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    			.addContainerGap())
	    );
	    this.panelPesquisarCliente.setLayout(layoutPesquisarCliente);
		
		// ---------- PANEL DADOS CADASTRAIS ----------- //
		this.panelDadosCadastrais.setBorder(BorderFactory.createTitledBorder("Dados Cadastrais"));

		// Codigo.
		this.labelCodigo.setText("C�digo:");
		this.textCodigo.setFont(new Font("Tahoma", 1, 11));
		this.textCodigo.setEnabled(false);
		
		// Nome.
		this.labelNome.setText("Nome:");
		this.textNome.setFont(new Font("Tahoma", 1, 11));
				
		// Telefone.
		this.labelTelefone.setText("Telefone:");
		this.textTelefone.setFont(new Font("Tahoma", 1, 11));
				
		// Endere�o.
		this.labelEndereco.setText("Endere�o:");
		this.textEndereco.setFont(new Font("Tahoma", 1, 11));

	    GroupLayout layoutDadosCadastrais = new GroupLayout(this.panelDadosCadastrais);
	    layoutDadosCadastrais.setHorizontalGroup(
	    	layoutDadosCadastrais.createParallelGroup(Alignment.LEADING)
	    		.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(layoutDadosCadastrais.createParallelGroup(Alignment.LEADING)
	    				.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    					.addComponent(labelTelefone, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.UNRELATED)
	    					.addComponent(textTelefone, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
	    					.addGap(18))
	    				.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    					.addComponent(labelCodigo)
	    					.addPreferredGap(ComponentPlacement.UNRELATED)
	    					.addComponent(textCodigo, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
	    					.addGap(18)
	    					.addComponent(labelNome)
	    					.addGap(10)))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(layoutDadosCadastrais.createParallelGroup(Alignment.TRAILING)
	    				.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    					.addComponent(labelEndereco, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
	    					.addPreferredGap(ComponentPlacement.UNRELATED)
	    					.addComponent(textEndereco, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
	    				.addComponent(textNome, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
	    			.addContainerGap())
	    );
	    layoutDadosCadastrais.setVerticalGroup(
	    	layoutDadosCadastrais.createParallelGroup(Alignment.LEADING)
	    		.addGroup(layoutDadosCadastrais.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(layoutDadosCadastrais.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(labelCodigo)
	    				.addComponent(textCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(textNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(labelNome))
	    			.addGap(18)
	    			.addGroup(layoutDadosCadastrais.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(labelTelefone)
	    				.addComponent(textEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(textTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(labelEndereco))
	    			.addGap(26))
	    );
	    this.panelDadosCadastrais.setLayout(layoutDadosCadastrais);

	    // ---------- PANEL DOS BOT�ES ----------- //
	    this.panelBotoes.setBorder(BorderFactory.createCompoundBorder());	    
	    
	    // Bot�es.
	    this.buttonNovo.setBackground(new Color(255, 255, 255));
	    this.buttonNovo.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/novo_registro.gif")));
	    this.buttonNovo.setText("Novo");
	    this.buttonNovo.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadCliente.this.buttonNovoActionPerformed(evt);
	    	}
	    });
	    this.buttonAlterar.setBackground(new Color(255, 255, 255));
	    this.buttonAlterar.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/alterar_registro.gif")));
	    this.buttonAlterar.setText("Alterar");
	    this.buttonAlterar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadCliente.this.buttonAlterarActionPerformed(evt);
	    	}
	    });
	    
	    this.buttonExcluir.setBackground(new Color(255, 255, 255));
	    this.buttonExcluir.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/excluir.png")));
	    this.buttonExcluir.setText("Excluir");
	    this.buttonExcluir.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadCliente.this.buttonExcluirActionPerformed(evt);
	    	}
	    });
	    
	    this.buttonAtualizar.setBackground(new Color(255, 255, 255));
	    this.buttonAtualizar.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/atualizar.png")));
	    this.buttonAtualizar.setText("Atualizar");
	    this.buttonAtualizar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadCliente.this.buttonAtualizarActionPerformed(evt);
	    	}
	    });
	    this.buttonSalvar.setBackground(new Color(255, 255, 255));
	    this.buttonSalvar.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/gravar_registro.gif")));
	    this.buttonSalvar.setText("Salvar");
	    this.buttonSalvar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadCliente.this.buttonSalvarActionPerformed(evt);
	    	}
	    });
	    this.buttonFechar.setBackground(new Color(255, 255, 255));
	    this.buttonFechar.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/sair.gif")));
	    this.buttonFechar.setText("Fechar");
	    this.buttonFechar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadCliente.this.buttonFecharActionPerformed(evt);
	    	}
	    });
	    
	    GroupLayout layoutBotoes = new GroupLayout(this.panelBotoes);
	    layoutBotoes.setHorizontalGroup(
	    	layoutBotoes.createParallelGroup(Alignment.LEADING)
	    		.addGroup(layoutBotoes.createSequentialGroup()
	    			.addComponent(buttonNovo, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(buttonAlterar, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(buttonExcluir, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(buttonAtualizar, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(buttonSalvar, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(buttonFechar, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
	    );
	    layoutBotoes.setVerticalGroup(
	    	layoutBotoes.createParallelGroup(Alignment.LEADING)
	    		.addGroup(layoutBotoes.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(layoutBotoes.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(buttonNovo)
	    				.addComponent(buttonFechar)
	    				.addComponent(buttonAlterar)
	    				.addComponent(buttonSalvar)
	    				.addComponent(buttonAtualizar)
	    				.addComponent(buttonExcluir, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
	    			.addGap(0, 0, Short.MAX_VALUE))
	    );
	    this.panelBotoes.setLayout(layoutBotoes);

	    // ---------- TABLE INFORMA��ES ----------- //
	    this.tableInformacaoes.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, { null, null, null, null  }, { null, null, null, null  }, { null, null, null, null  } }, new String[] { "C�digo", "Nome", "Endere�o", "Telefone" }));
	    
	    this.tableInformacaoes.setFocusable(false);
	    this.scrollPaneInformacaoes.setViewportView(this.tableInformacaoes);
	    
	    // ---------- BARRA DE FERRAMENTAS ----------- //	    
	    this.menuArquivo.setText("Arquivo");

	    this.subNovo.setText("Novo");
	    this.subNovo.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadCliente.this.subNovoActionPerformed(evt);
	    	}
	    });
	    this.menuArquivo.add(this.subNovo);

	    this.subFechar.setText("Fechar");
	    this.subFechar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanCadCliente.this.subFecharActionPerformed(evt);
	    	}
	    });
	    this.menuArquivo.add(this.subFechar);

	    this.menuBar.add(this.menuArquivo);

	    setJMenuBar(this.menuBar);
	    
	    // ---------- LAYOULT ----------- //
	    GroupLayout layout = new GroupLayout(getContentPane());
	    layout.setHorizontalGroup(
	    	layout.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(layout.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
	    				.addComponent(scrollPaneInformacaoes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
	    				.addComponent(panelDadosCadastrais, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
	    				.addComponent(panelPesquisarCliente, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
	    				.addComponent(panelBotoes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE))
	    			.addContainerGap())
	    );
	    layout.setVerticalGroup(
	    	layout.createParallelGroup(Alignment.LEADING)
	    		.addGroup(layout.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(panelPesquisarCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addGap(15)
	    			.addComponent(panelDadosCadastrais, GroupLayout.PREFERRED_SIZE, 106, Short.MAX_VALUE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(panelBotoes, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(scrollPaneInformacaoes, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
	    			.addContainerGap())
	    );
	    getContentPane().setLayout(layout);

	    pack();
	}
	
	// TextPesquisar.
	private void textPesquisarActionPerformed(ActionEvent evt) {
		buttonPesquisarActionPerformed(evt);
	}
	
	// ButtonPesquisar.
	private void buttonPesquisarActionPerformed(ActionEvent evt) {
		if (!(this.textPesquisar.getText().equals(""))){
			
			ClienteDAO dao = new ClienteDAO();
			dao.pesquisar(this.textPesquisar.getText());
			
			try{
				List<String> listaCliente = dao.pesquisar(this.textPesquisar.getText());
					this.textCodigo.setText(listaCliente.get(0));
					this.textNome.setText(listaCliente.get(1));
					this.textTelefone.setText(listaCliente.get(2));
					this.textEndereco.setText(listaCliente.get(3));
					
					this.buttonAtualizar.setEnabled(false);
					this.buttonSalvar.setEnabled(false);
			} catch (java.lang.IndexOutOfBoundsException indexOutOfBoundsException){
				JOptionPane.showMessageDialog(null, "O cliente " + this.textPesquisar.getText() + " n�o foi encontrado");
			}
		}
	}
	
	// ButtonNovo.
	private void buttonNovoActionPerformed(ActionEvent evt) {
		this.buttonSalvar.setEnabled(true);
		this.buttonExcluir.setEnabled(false);
		this.buttonAtualizar.setEnabled(false);
		this.buttonAlterar.setEnabled(false);
		
		Visivel();
		ClienteDAO dao = new ClienteDAO();
		this.textCodigo.setText(Long.toString(dao.retornaAutoIncrement()));
		this.textNome.setText("");
	    this.textTelefone.setText("");
	    this.textEndereco.setText("");
	}
	
	// ButtonAlterar.
	private void buttonAlterarActionPerformed(ActionEvent evt) {		
		if ((this.textCodigo.getText().equals("") || this.textNome.getText().equals("")) || (this.textTelefone.getText().equals(this.inicioTelefone)) || (this.textEndereco.getText().equals(""))) {
			JOptionPane.showMessageDialog(null, "Nenhum cliente selecionado!");
		} else{
			Visivel();
			this.buttonAtualizar.setEnabled(true);
			this.buttonExcluir.setEnabled(false);
		}
	}
	
	// ButtonExcluir.
	private void buttonExcluirActionPerformed(ActionEvent evt) {
		if ((this.textCodigo.getText().equals("") || this.textNome.getText().equals("")) || (this.textTelefone.getText().equals(this.inicioTelefone)) || (this.textEndereco.getText().equals(""))) {
			JOptionPane.showMessageDialog(null, "Nenhum cliente selecionado!");
		} else{
			Visivel();
			
			Cliente cliente = new Cliente();
	        cliente.setCodigo(Integer.parseInt((this.textCodigo.getText())));
	        
			ClienteDAO dao = new ClienteDAO();
			dao.excluir(cliente);
			
			JOptionPane.showMessageDialog(null, "Exclu�do com sucesso!");
	        preencherJTable();
	        
			this.textCodigo.setText("");
			this.textNome.setText("");
		    this.textTelefone.setText("");
		    this.textEndereco.setText("");
		    
		    Invisivel();
		    
			this.buttonSalvar.setEnabled(true);
		}
	}
	
	// ButtonAtualisar.
	private void buttonAtualizarActionPerformed(ActionEvent evt) {
	    if ((this.textNome.getText().equals("")) || (this.textTelefone.getText().equals(this.inicioTelefone)) || (this.textEndereco.getText().equals(""))) {
	        JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
	    } else {
	    	  
	        Cliente cliente = new Cliente();
	        cliente.setCodigo(Integer.parseInt((this.textCodigo.getText())));
	        cliente.setNome(this.textNome.getText());
	        cliente.setTelefone(this.textTelefone.getText());
	        cliente.setEndereco(this.textEndereco.getText());
	        
	        // Atualiza!!!
	        ClienteDAO dao = new ClienteDAO();
	        dao.atualizar(cliente);
	        
	        JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
	        preencherJTable();
		    this.textCodigo.setText("");
			this.textNome.setText("");
		    this.textTelefone.setText("");
		    this.textEndereco.setText("");
		    
		    Invisivel();
		    
		    this.buttonSalvar.setEnabled(true);
			this.buttonExcluir.setEnabled(true);
	    }
	}
	
	// ButtonSalvar.
	private void buttonSalvarActionPerformed(ActionEvent evt) {
	    if ((this.textNome.getText().equals("")) || (this.textTelefone.getText().equals(this.inicioTelefone)) || (this.textEndereco.getText().equals(""))) {
	        JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
	    } else {
	    	  
	        Cliente cliente = new Cliente();
	        cliente.setNome(this.textNome.getText());
	        cliente.setTelefone(this.textTelefone.getText());
	        cliente.setEndereco(this.textEndereco.getText());
	        
	        // grave nessa conex�o!!!
	        ClienteDAO dao = new ClienteDAO();
	        dao.inserir(cliente);
	        
	        JOptionPane.showMessageDialog(null, "O cadastro do cliente:\n" + cliente.getCodigo() + " - " + this.textNome.getText() + "\nFoi realizado com sucesso!");
	        preencherJTable();
	        
		    this.textCodigo.setText("");
			this.textNome.setText("");
		    this.textTelefone.setText("");
		    this.textEndereco.setText("");
		    
			Invisivel();
		    
			this.buttonExcluir.setEnabled(true);
			this.buttonAtualizar.setEnabled(true);
			this.buttonAlterar.setEnabled(true);
	    }
	}
	
	// ButtonFechar.
	private void buttonFecharActionPerformed(ActionEvent evt) {
	    dispose();
	    JanPrincipal janP = new JanPrincipal();
	    janP.setVisible(true);
	    
	}
	
	// JMenuItemNovo.
	private void subNovoActionPerformed(ActionEvent evt) {
		buttonNovoActionPerformed(evt);
	}
	
	// JMenuItemFechar.
	private void subFecharActionPerformed(ActionEvent evt) {
	    dispose();
	    JanPrincipal janP = new JanPrincipal();
	    janP.setVisible(true);
	}
}
