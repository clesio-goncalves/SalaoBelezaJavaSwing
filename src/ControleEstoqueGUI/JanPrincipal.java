package ControleEstoqueGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import ControleEstoqueBanco.VendaDAO;
import ControleEstoqueBean.ItemVenda;
import ControleEstoqueBean.Venda;

public class JanPrincipal extends JFrame {
	String inicioData = null;
	MaskFormatter maskData;
 	Venda venda = new Venda();
	VendaDAO dao = new VendaDAO();
	int codigoVenda = 0;
	String nomeProduto = null;
	String nomeServico = null;
	String tipoItemVenda = null;
	private JTextField textCodigoVenda;
	private JTextField textData;
	private JTextField textQuantidade;
	private JTextField textPreco;
	private JComboBox comboCliente;
	private JComboBox comboProduto;
	private JComboBox comboServico;
	private JButton buttonOk;
	private JButton buttonAdicionar;
	private JButton buttonFaturarPedido;
	private JButton buttonFechar;
	private JLabel labelCodigoVenda;
	private JLabel labelData;
	private JLabel labelCliente;
	private JLabel labelProduto;
	private JLabel labelServico;
	private JLabel labelQuantidade;
	private JLabel labelPreco;
	private JLabel labelTotalVenda;
	private JMenuBar menuBar;
	private JMenu menuCadastrar;
	private JMenuItem subCadCliente;
	private JMenuItem subCadFornecedor;
	private JMenuItem subCadFuncionario;
	private JMenuItem subCadProduto;
	private JMenuItem subCadServico;
	private JMenuItem subCadUsuario;
	private JMenu menuEstoque;
	private JMenuItem subModificarEstoque;
	private JMenu menuRelatorios;
	private JMenuItem subRelFinanceiro;
	private JMenu menuFechar;
	private JPanel panelItensVenda;
	private JPanel panelVenda;
	private JPanel panelTotal;
	private JScrollPane scrollPaneInformacaoes;
	private JTable tableInformacaoes;
	

	public JanPrincipal() {
		super("Salao de beleza");
		getContentPane().setBackground(Color.MAGENTA);
		Componentes();
		setLocationRelativeTo(null);
		this.setResizable(false);
		
		preencherJTable();
		Invisivel();
		
		this.textCodigoVenda.setEnabled(false);
		
		//comboCliente
 		this.comboCliente.removeAllItems();
 		this.comboCliente.addItem("");
 		ArrayList<String> cliente = this.dao.comboCliente();
 		Iterator<String> i = cliente.iterator();
 				  
 		while(i.hasNext()) {  
 			this.comboCliente.addItem(String.valueOf(i.next()));  
 		}
		
		// comboServico
		this.comboServico.removeAllItems(); 
		this.comboServico.addItem("");
		ArrayList<String> servico = this.dao.comboServico();
		Iterator<String> j = servico.iterator();
		  
		while(j.hasNext()) {  
			this.comboServico.addItem(String.valueOf(j.next()));  
		}
		
		// comboProduto.
		this.comboProduto.removeAllItems();
		this.comboProduto.addItem("");
		ArrayList<String> produto = this.dao.comboProduto();
		Iterator<String> x = produto.iterator();
				  
		while(x.hasNext()) {  
			this.comboProduto.addItem(String.valueOf(x.next()));  
		}	
		
		// retorna auto-incremento
		this.textCodigoVenda.setText(Long.toString(this.dao.retornaAutoIncrement()));
	}
	
	public void Invisivel() {
		this.comboProduto.setEnabled(false);
		this.comboServico.setEnabled(false);
		this.textPreco.setEditable(false);
		this.textQuantidade.setEditable(false);
		this.tableInformacaoes.setEnabled(false);
		this.buttonAdicionar.setEnabled(false);
		this.buttonFaturarPedido.setEnabled(false);
	}

	public void Visivel() {
		this.comboProduto.setEnabled(true);
		this.comboServico.setEnabled(true);
		this.textQuantidade.setEditable(true);
		this.tableInformacaoes.setEnabled(true);
		this.buttonAdicionar.setEnabled(true);
	}
	
	public void limpaTabela(){  
		DefaultTableModel tabela = (DefaultTableModel)this.tableInformacaoes.getModel();  
	    if (tabela.getRowCount() > 0){  
	        for (int i=tabela.getRowCount()-1;i>=0;i--){
	        	tabela.removeRow(i);  
	        }
	    }
	}
	
	public void preencherJTable() {
		this.tableInformacaoes.getColumnModel().getColumn(0).setPreferredWidth(100);
		this.tableInformacaoes.getColumnModel().getColumn(1).setPreferredWidth(30);
		this.tableInformacaoes.getColumnModel().getColumn(2).setPreferredWidth(20);
		this.tableInformacaoes.getColumnModel().getColumn(3).setPreferredWidth(30);
		
		DefaultTableModel tabela = (DefaultTableModel)this.tableInformacaoes.getModel();
		tabela.setNumRows(0);
		
		// seta os valores
		for (int i = 0; i < this.venda.getItensVenda().size(); i++) {
			tabela.addRow(new Object[] {
				this.venda.getItensVenda().get(i).getProduto().getNome() + this.venda.getItensVenda().get(i).getServico().getNome(),
				this.venda.getItensVenda().get(i).getPreco(),
				this.venda.getItensVenda().get(i).getQuantidade(),
				this.venda.getItensVenda().get(i).getQuantidade()*this.venda.getItensVenda().get(i).getPreco()
			});
		}
	}

	private void Componentes() {
		try {
		      this.maskData = new MaskFormatter("##/##/####");
		} catch (Exception e) {
		      JOptionPane.showMessageDialog(null, "Nao foi possivel inserir a mascara");
		}
		this.textCodigoVenda = new JTextField();
		this.textData = new JFormattedTextField(this.maskData);
		this.textQuantidade = new JTextField();
		this.textPreco = new JTextField();
		this.comboCliente = new JComboBox();
		this.comboProduto = new JComboBox();
		this.comboServico = new JComboBox();
		this.buttonOk = new JButton();
		this.buttonAdicionar = new JButton();
		this.buttonFaturarPedido = new JButton();
		this.buttonFechar = new JButton();
		this.labelCodigoVenda = new JLabel();
		this.labelData = new JLabel();
		this.labelCliente = new JLabel();
		this.labelProduto = new JLabel();
		this.labelServico = new JLabel();
		this.labelQuantidade = new JLabel();
		this.labelPreco = new JLabel();
		this.labelTotalVenda = new JLabel();
		this.menuBar = new JMenuBar();
		this.menuCadastrar = new JMenu();		
		this.subCadCliente = new JMenuItem();
		this.subCadFornecedor = new JMenuItem();
		this.subCadFuncionario = new JMenuItem();		
		this.subCadProduto = new JMenuItem();
		this.subCadServico = new JMenuItem();
		this.subCadUsuario = new JMenuItem();
		this.menuEstoque = new JMenu();
		this.subModificarEstoque = new JMenuItem();
		this.menuRelatorios = new JMenu();
		this.subRelFinanceiro = new JMenuItem();
		this.menuFechar = new JMenu();
		this.panelItensVenda = new JPanel();
		panelItensVenda.setBackground(Color.PINK);
		this.panelVenda = new JPanel();
		panelVenda.setBackground(new Color(221, 160, 221));
		this.panelTotal = new JPanel();
		panelTotal.setBackground(new Color(255, 192, 203));
		this.scrollPaneInformacaoes = new JScrollPane();
		scrollPaneInformacaoes.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.tableInformacaoes = new JTable();
		tableInformacaoes.setColumnSelectionAllowed(true);
		this.inicioData = this.textData.getText();

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    
		// ---------- PEDIDO ----------- //
		this.panelItensVenda.setBorder(BorderFactory.createTitledBorder("Itens da Venda"));
	    
	    // Venda
	    this.labelCodigoVenda.setFont(new Font("Tahoma", 1, 11));
	    this.labelCodigoVenda.setText("C�d venda:");
	    this.textCodigoVenda.setFont(new Font("Tahoma", 1, 11));
	    
	    // Data
	    this.labelData.setFont(new Font("Tahoma", 1, 11));
	    this.labelData.setText("Data:");
	    this.textData.setFont(new Font("Tahoma", 1, 11));
	    
	    // Cliente.
	    this.labelCliente.setFont(new Font("Tahoma", 1, 11));
	    this.labelCliente.setText("Cliente:");	    
	 	this.comboCliente.setFont(new Font("Tahoma", 1, 11));	 	
	 	
	 	// buttonOk.
	    this.buttonOk.setBackground(new Color(255, 255, 255));
	    this.buttonOk.setText("OK");
	    this.buttonOk.setFont(new Font("Tahoma", 1, 11));
	    this.buttonOk.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanPrincipal.this.buttonOkActionPerformed(evt);
	    	}
	    });

	    GroupLayout gl_panelItensVenda = new GroupLayout(panelItensVenda);
	    gl_panelItensVenda.setHorizontalGroup(
	    	gl_panelItensVenda.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_panelItensVenda.createSequentialGroup()
	    			.addGap(24)
	    			.addGroup(gl_panelItensVenda.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_panelItensVenda.createSequentialGroup()
	    					.addComponent(panelTotal, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.UNRELATED)
	    					.addComponent(buttonFaturarPedido)
	    					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
	    					.addComponent(buttonFechar))
	    				.addComponent(scrollPaneInformacaoes, GroupLayout.PREFERRED_SIZE, 449, GroupLayout.PREFERRED_SIZE))
	    			.addGap(23))
	    		.addGroup(gl_panelItensVenda.createSequentialGroup()
	    			.addGap(65)
	    			.addGroup(gl_panelItensVenda.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_panelItensVenda.createSequentialGroup()
	    					.addComponent(labelProduto, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
	    					.addGap(150)
	    					.addComponent(labelQuantidade, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_panelItensVenda.createSequentialGroup()
	    					.addGroup(gl_panelItensVenda.createParallelGroup(Alignment.TRAILING, false)
	    						.addComponent(comboProduto, Alignment.LEADING, 0, 213, Short.MAX_VALUE)
	    						.addComponent(comboServico, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    						.addComponent(labelServico, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
	    					.addGap(56)
	    					.addGroup(gl_panelItensVenda.createParallelGroup(Alignment.LEADING)
	    						.addComponent(labelPreco, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
	    						.addComponent(textPreco, 95, 95, 95)
	    						.addComponent(textQuantidade, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))))
	    			.addGap(67))
	    		.addGroup(gl_panelItensVenda.createSequentialGroup()
	    			.addGap(187)
	    			.addComponent(buttonAdicionar, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
	    			.addGap(189))
	    );
	    gl_panelItensVenda.setVerticalGroup(
	    	gl_panelItensVenda.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_panelItensVenda.createSequentialGroup()
	    			.addGap(13)
	    			.addGroup(gl_panelItensVenda.createParallelGroup(Alignment.LEADING)
	    				.addComponent(labelQuantidade)
	    				.addComponent(labelProduto))
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addGroup(gl_panelItensVenda.createParallelGroup(Alignment.LEADING)
	    				.addComponent(comboProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(textQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addGroup(gl_panelItensVenda.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_panelItensVenda.createSequentialGroup()
	    					.addComponent(labelServico)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(comboServico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_panelItensVenda.createSequentialGroup()
	    					.addComponent(labelPreco)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(textPreco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(buttonAdicionar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
	    			.addGap(11)
	    			.addComponent(scrollPaneInformacaoes, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
	    			.addGroup(gl_panelItensVenda.createParallelGroup(Alignment.TRAILING)
	    				.addGroup(gl_panelItensVenda.createSequentialGroup()
	    					.addGap(18)
	    					.addComponent(panelTotal, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
	    					.addContainerGap())
	    				.addGroup(gl_panelItensVenda.createSequentialGroup()
	    					.addGap(32)
	    					.addGroup(gl_panelItensVenda.createParallelGroup(Alignment.BASELINE)
	    						.addComponent(buttonFechar, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
	    						.addComponent(buttonFaturarPedido, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    					.addGap(23))))
	    );
	    panelItensVenda.setLayout(gl_panelItensVenda);
	    
	   // ITENS VENDA
	   this.panelVenda.setBorder(BorderFactory.createTitledBorder("Venda"));
	    
	    // Produto.
	    this.labelProduto.setText("Selecione o produto:");
	 	this.comboProduto.setFont(new Font("Tahoma", 1, 11));
	 	
	 	this.comboProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JanPrincipal.this.comboProdutoActionPerformed(evt);
			}
		});
	 	 	
	    // Servi�o.
	 	this.labelServico.setText("Selecione o serviço:");
	 	this.comboServico.setFont(new Font("Tahoma", 1, 11));
	 	
	 	this.comboServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JanPrincipal.this.comboServicoActionPerformed(evt);
			}
		});

	 	// Quantidade.
	 	this.labelQuantidade.setText("Quantidade:");
	 	this.textQuantidade.setFont(new Font("Tahoma", 1, 11));
	 	this.textQuantidade.setText("1");
	 	
	 	// Valor.
	 	this.labelPreco.setText("Valor:");
	 	this.textPreco.setFont(new Font("Tahoma", 1, 11));
		
	 	// Bot�o Adicionar
	 	this.buttonAdicionar.setBackground(new Color(255, 255, 255));
	 	this.buttonAdicionar.setText("Adicionar a lista");
	 	this.buttonAdicionar.addActionListener(new ActionListener() {
	 	  	public void actionPerformed(ActionEvent evt) {
	 	   		JanPrincipal.this.buttonAdicionarActionPerformed(evt);
	 	   	}
	 	});
	 	
	 	// Bot�o Finalizar Venda
	 	this.buttonFaturarPedido.setBackground(new Color(255, 255, 255));
	    this.buttonFaturarPedido.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/dinheiro.png")));
	    this.buttonFaturarPedido.setText("Faturar Pedido");
	    this.buttonFaturarPedido.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanPrincipal.this.buttonFaturarPedidoActionPerformed(evt);
	    	}
	    });

	    // Bot�o Fechar 
	    this.buttonFechar.setBackground(new Color(255, 255, 255));
	    this.buttonFechar.setIcon(new ImageIcon(getClass().getResource("/ControleEstoqueGUI/fechar.gif")));
	    this.buttonFechar.setText("Fechar");
	    this.buttonFechar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		JanPrincipal.this.buttonFecharActionPerformed(evt);
	    	}
	    });
	    
	   // TOTAL VENDA
	   this.panelTotal.setBorder(BorderFactory.createTitledBorder("Total Venda"));
	   this.labelTotalVenda.setFont(new Font("Tahoma", Font.BOLD, 25));
	   panelTotal.add(labelTotalVenda);

	    GroupLayout gl_panelVenda = new GroupLayout(this.panelVenda);
	    gl_panelVenda.setHorizontalGroup(
	    	gl_panelVenda.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_panelVenda.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_panelVenda.createParallelGroup(Alignment.LEADING, false)
	    				.addComponent(panelItensVenda, GroupLayout.PREFERRED_SIZE, 508, GroupLayout.PREFERRED_SIZE)
	    				.addGroup(gl_panelVenda.createSequentialGroup()
	    					.addComponent(labelCodigoVenda)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(textCodigoVenda, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.UNRELATED)
	    					.addComponent(labelData, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(textData, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.UNRELATED)
	    					.addComponent(labelCliente, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(comboCliente, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(buttonOk)))
	    			.addGap(10))
	    );
	    gl_panelVenda.setVerticalGroup(
	    	gl_panelVenda.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_panelVenda.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_panelVenda.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(labelCodigoVenda)
	    				.addComponent(textCodigoVenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(labelData)
	    				.addComponent(textData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(comboCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(buttonOk, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(labelCliente))
	    			.addGap(18)
	    			.addComponent(panelItensVenda, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE)
	    			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    );
	    this.panelVenda.setLayout(gl_panelVenda);
	    
	 // ---------- TABLE INFORMA��ES ----------- //
	    this.tableInformacaoes.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, { null, null, null, null  }, { null, null, null, null  }, { null, null, null, null  } }, new String[] {"Descri��o", "Pre�o Unit", "Quantidade", "Total" }));
	    
	    this.tableInformacaoes.setFocusable(false);
	    this.scrollPaneInformacaoes.setViewportView(this.tableInformacaoes);

		
		// ---------- LAYOUT ----------- //
		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelVenda, GroupLayout.PREFERRED_SIZE, 540, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addComponent(panelVenda, GroupLayout.PREFERRED_SIZE, 485, Short.MAX_VALUE)
					.addContainerGap())
		);
	    getContentPane().setLayout(layout);

	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds((screenSize.width - 531) / 2, (screenSize.height - 545) / 2, 567, 545);

	    		
	 // ---------- BARRA DE FERRAMENTAS ----------- //
	 		final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

	 		// Menu Cadastro.
	 		this.menuCadastrar.setMnemonic('C');
	 		this.menuCadastrar.setText("Cadastrar");
	 		
	 		// Sub-menu Cliente.
	 		this.subCadCliente.setText("Cliente");
	 		subCadCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,SHORTCUT_MASK));
	 		this.subCadCliente.addActionListener(new ActionListener() {
	 			public void actionPerformed(ActionEvent evt) {
	 				JanPrincipal.this.subCadClienteActionPerformed(evt);
	 			}
	 		});
	 		this.menuCadastrar.add(this.subCadCliente);
	 		
	 		// Sub-menu Fornecedor.
	 		this.subCadFornecedor.setText("Fornecedor");
	 		subCadFornecedor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,SHORTCUT_MASK));
	 		this.subCadFornecedor.addActionListener(new ActionListener() {
	 			public void actionPerformed(ActionEvent evt) {
	 				JanPrincipal.this.subCadFornecedorActionPerformed(evt);
	 			}
	 		});
	 		this.menuCadastrar.add(this.subCadFornecedor);
	 		
	 		// Sub-menu Funcionario
	 		this.subCadFuncionario.setText("Funcion�rio");
	 		subCadFuncionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,SHORTCUT_MASK));
	 		this.subCadFuncionario.addActionListener(new ActionListener() {
	 			public void actionPerformed(ActionEvent evt) {
	 					JanPrincipal.this.subCadFuncionarioActionPerformed(evt);
	 			}
	 		});
	 		this.menuCadastrar.add(this.subCadFuncionario);

	 		// Sub-menu Produto.
	 		this.subCadProduto.setText("Produto");
	 		subCadProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,SHORTCUT_MASK));
	 		this.subCadProduto.addActionListener(new ActionListener() {
	 			public void actionPerformed(ActionEvent evt) {
	 				JanPrincipal.this.subCadProdutoActionPerformed(evt);
	 			}
	 		});
	 		this.menuCadastrar.add(this.subCadProduto);
	 		
	 		// Sub-menu Servi�o
	 		this.subCadServico.setText("Servi�o");
	 		subCadServico.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,SHORTCUT_MASK));
	 		this.subCadServico.addActionListener(new ActionListener() {
	 			public void actionPerformed(ActionEvent evt) {
	 				JanPrincipal.this.subCadServicoActionPerformed(evt);
	 			}
	 		});
	 		this.menuCadastrar.add(this.subCadServico);
	 		
	 		// Sub-menu Usu�rio
	 		this.subCadUsuario.setText("Usu�rio");
	 		subCadUsuario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,SHORTCUT_MASK));
	 		this.subCadUsuario.addActionListener(new ActionListener() {
	 			public void actionPerformed(ActionEvent evt) {
	 				JanPrincipal.this.subCadUsuarioActionPerformed(evt);
	 			}
	 		});
	 		this.menuCadastrar.add(this.subCadUsuario);
	 		
	 		this.menuBar.add(this.menuCadastrar);

	 		// Menu Estoque. 
	 		this.menuEstoque.setMnemonic('E');
	 		this.menuEstoque.setText("Estoque");

	 		// Sub-item subModificarEstoque.
	 		this.subModificarEstoque.setText("Modificar o Estoque");
	 		subModificarEstoque.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,SHORTCUT_MASK));
	 		this.subModificarEstoque.addActionListener(new ActionListener() {
	 			public void actionPerformed(ActionEvent evt) {
	 				JanPrincipal.this.subModificarEstoqueActionPerformed(evt);
	 			}
	 		});
	 		this.menuEstoque.add(this.subModificarEstoque);
	 		this.menuBar.add(this.menuEstoque);
	 		
	 		// Menu Relat�rios. 
	 		this.menuRelatorios.setMnemonic('R');
	 		this.menuRelatorios.setText("Relat�rios");
	 		
	 		// Sub-item subRelFinanceiro.
	 		this.subRelFinanceiro.setText("Financeiro");
	 		subRelFinanceiro.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,SHORTCUT_MASK));
	 		this.subRelFinanceiro.addActionListener(new ActionListener() {
	 			public void actionPerformed(ActionEvent evt) {
	 				JanPrincipal.this.subRelFinanceiroActionPerformed(evt);
	 			}
	 		});
	 		this.menuRelatorios.add(this.subRelFinanceiro);
	 		this.menuBar.add(this.menuRelatorios);

	 		// Menu Fechar.
	 		this.menuFechar.setMnemonic('F');
	 		this.menuFechar.setText("Fechar");
	 		this.menuFechar.addMouseListener(new MouseAdapter() {
	 			public void mouseClicked(MouseEvent evt) {
	 				JanPrincipal.this.menuFecharMouseClicked(evt);
	 			}
	 		});
	 		this.menuFechar.addActionListener(new ActionListener() {
	 			public void actionPerformed(ActionEvent evt) {
	 				JanPrincipal.this.menuFecharActionPerformed(evt);
	 			}
	 		});
	 		this.menuBar.add(this.menuFechar);

	 		// Insere MenuBar.
	 		setJMenuBar(this.menuBar);	

	 	}
	 	
	 	// buttonAdicionar.
	 	private void buttonAdicionarActionPerformed(ActionEvent evt) {
	 		this.nomeProduto = (String) this.comboProduto.getSelectedItem();
	 		this.nomeServico = (String) this.comboServico.getSelectedItem();
	 		if ((this.nomeProduto.equals("")) && (nomeServico.equals(""))) {
	 			JOptionPane.showMessageDialog(null, "Verifique os campos!");
	 	    } else {
	 	    	
	 			this.buttonFaturarPedido.setEnabled(true);
	 	    	
	 			ItemVenda itemVenda = new ItemVenda();
	 			itemVenda.getCodigoVenda().setCodigo(this.codigoVenda);	
	 			
	 			itemVenda.setQuantidade(Integer.parseInt(this.textQuantidade.getText()));
	 			itemVenda.setPreco(Float.parseFloat(this.textPreco.getText()));
	 			
	 			// comboProduto selecionado
	 	    	if ((!(this.nomeProduto.equals(""))) && (this.nomeServico.equals(""))) {
	 	    		itemVenda.getProduto().setCodigo(this.dao.consultarCodigoProduto((String)this.comboProduto.getSelectedItem()));
	 	    		itemVenda.getProduto().setNome((String) this.comboProduto.getSelectedItem());
	 	    		itemVenda.getServico().setNome((String) this.comboServico.getSelectedItem());
	 	    		this.tipoItemVenda = "produto";
	 	    	}
	 	    	
	 	    	// comboServico selecionado
	 	    	if ((!(this.nomeServico.equals(""))) && (this.nomeProduto.equals(""))) {
	 	    		itemVenda.getServico().setCodigo(this.dao.consultarCodigoServico((String)this.comboServico.getSelectedItem()));
	 	    		itemVenda.getServico().setNome((String) this.comboServico.getSelectedItem());
	 	    		itemVenda.getProduto().setNome((String) this.comboProduto.getSelectedItem());
	 	    		this.tipoItemVenda = "servi�o";
	 	    	}
	 	    	
	 	    	/*/ comboReserva selecionado
	 	    	if (((this.comboProduto.getSelectedItem().equals(""))==true) && ((this.comboServico.getSelectedItem().equals(""))==true) && ((this.comboReserva.getSelectedItem().equals(""))==false)) {
	 				
	 	    	}*/
	 	    	
	 	    	if ((!(this.nomeProduto.equals(""))) && (!(this.nomeServico.equals("")))) {
	 	    		JOptionPane.showMessageDialog(null, "Selecione apenas um campo!");
	 	    	} else {
	 	    		this.venda.getItensVenda().add(itemVenda);
		 			
		 		    // grave nessa conex�o!!        
		 		    this.dao.adicionarLista(this.venda,this.tipoItemVenda);
		 		    
		 		    preencherJTable();
	 	    
		 		    this.labelTotalVenda.setText("R$ " + this.venda.getValorTotal());
	 	    
	 	    	}
	 		    
	 	    }
	 	}
	 	
	 	//comboProduto.
	 	private void comboProdutoActionPerformed(ActionEvent evt) {
	 		this.nomeProduto = (String) this.comboProduto.getSelectedItem();
	 		if (!(this.nomeProduto.equals(""))) {
	 			ItemVenda itemVenda = new ItemVenda();
	 			itemVenda.getProduto().setNome((String) this.comboProduto.getSelectedItem());
	 	        
	 	        // Atualiza TextQntEstoque!!!
	 	        this.textPreco.setText(Float.toString(this.dao.atualizaTextPrecoProduto(itemVenda)));
	 		} else {
	 			this.textPreco.setText("");
	 		} 
	 	}
	 	
	 	//comboServico.
	 	private void comboServicoActionPerformed(ActionEvent evt) {
	 		this.nomeServico = (String) this.comboServico.getSelectedItem();
	 		if (!(this.nomeServico.equals(""))) {
	 			ItemVenda itemVenda = new ItemVenda();
	 			itemVenda.getServico().setNome((String) this.comboServico.getSelectedItem());
	 	        
	 	        // Atualiza TextQntEstoque!!!
	 	        this.textPreco.setText(Float.toString(this.dao.atualizaTextPrecoServico(itemVenda)));
	 		} else {
	 			this.textPreco.setText("");
	 		} 
	 	}
	 	
	 	// buttonOk.
	 	private void buttonOkActionPerformed(ActionEvent evt) {
 	    	if ((this.textData.getText().equals(this.inicioData)) || (this.comboCliente.getSelectedItem().equals(""))) {
 		        JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
 		    } else {
	 			this.venda.setCodigo(Integer.parseInt(Long.toString(this.dao.retornaAutoIncrement())));
	 			this.venda.setData(this.textData.getText());
	 			this.venda.getCliente().setCodigo(this.dao.consultarCodigoCliente((String)this.comboCliente.getSelectedItem()));
	 			
	 			//grava nesta conex�o!!!
	 			this.codigoVenda = this.dao.inserir(this.venda);
	 			
		 		Visivel();
		 		this.buttonOk.setEnabled(false);
 		    }
	 	}
	 	
	 	// buttonFaturarPedido.
	 	private void buttonFaturarPedidoActionPerformed(ActionEvent evt) {
	 		Invisivel();
	 		String digitar = null;
	 		float total = 0;
	 		do {
	 			digitar = JOptionPane.showInputDialog("Informe o valor pago: ");
	 			
	 			if(digitar == null){
	 				this.buttonFaturarPedido.setEnabled(true);
	 				Visivel();
	 				return;
	 			}
		 			try{
		 				total = Float.parseFloat(digitar);
		 			} catch (java.lang.NumberFormatException numberFormatException){
		 				JOptionPane.showMessageDialog(null, "O valor informado n�o � v�lido. ");
		 			}
		 			
		 			if (total < this.venda.getValorTotal()){
			 			JOptionPane.showMessageDialog(null, "O valor informado � menor que o valor da venda. ");
			 		} 
		 			else{
		 				JOptionPane.showMessageDialog(null, "Troco: R$ " + (total - this.venda.getValorTotal()));
		 			}
	 		} while (total < this.venda.getValorTotal());
	 		this.dao.faturar(this.venda);
	 		
	 	
	 		// retorna auto-incremento
			this.textCodigoVenda.setText(Long.toString(this.dao.retornaAutoIncrement()));
			
			limpaTabela();
			this.venda.getItensVenda().clear();
	 		this.labelTotalVenda.setText("");
	 		this.comboCliente.setSelectedItem("");
	 		this.buttonOk.setEnabled(true);	 		
	 		this.comboProduto.setSelectedItem("");
 			this.comboServico.setSelectedItem("");
 			this.textData.setText("");
	 	}
	 	
	 	// buttonFechar.
	 	private void buttonFecharActionPerformed(ActionEvent evt) {
	 		this.dao.excluirVenda(Integer.parseInt(this.textCodigoVenda.getText()));
	 		this.dao.excluirItensVenda(Integer.parseInt(this.textCodigoVenda.getText()));
	 		dispose();
	 	}
	 		
	 	// SubCadCliente.
	 	private void subCadClienteActionPerformed(ActionEvent evt) {
	 		JanCadCliente cadC = new JanCadCliente();
	 		cadC.setVisible(true);
	 		dispose();
	 	}
	 	
	 	// SubCadFornecedor.
	 	private void subCadFornecedorActionPerformed(ActionEvent evt) {
	 		JanCadFornecedor cadF = new JanCadFornecedor();
	 		cadF.setVisible(true);
	 		dispose();
	 	}

	 	// SubCadFuncion�rio.
	 	private void subCadFuncionarioActionPerformed(ActionEvent evt) {
	 		JanCadFuncionario cadF = new JanCadFuncionario();
	 		cadF.setVisible(true);
	 		dispose();
	 	}
	 	
	 	// SubCadProduto.
	 	private void subCadProdutoActionPerformed(ActionEvent evt) {
	 		JanCadProduto cadP = new JanCadProduto();
	 		cadP.setVisible(true);
	 		dispose();
	 	}
	 	
	 	// SubCadServi�o.
	 	private void subCadServicoActionPerformed(ActionEvent evt) {
	 		JanCadServico cadS = new JanCadServico();
	 		cadS.setVisible(true);
	 		dispose();
	 	}
	 	
	 	// SubCadReserva.
	 	private void subCadUsuarioActionPerformed(ActionEvent evt) {
	 		JanCadUsuario cadU = new JanCadUsuario();
	 		cadU.setVisible(true);
	 		dispose();
	 	}
	 	
	 	// SubModificarEstoque.
	 	private void subModificarEstoqueActionPerformed(ActionEvent evt) {
	 		JanEstoque estoque = new JanEstoque();
	 		estoque.setVisible(true);
	 		dispose();
	 	}
	 	
	 	// SubRelFinanceiro.
	 	private void subRelFinanceiroActionPerformed(ActionEvent evt) {
	 		JanRelFinanceiro relFinanceiro = new JanRelFinanceiro();
	 		relFinanceiro.setVisible(true);
	 		dispose();
	 	}

	 	// Menu Fechar.
	 	private void menuFecharActionPerformed(ActionEvent evt) {
	 		this.dao.excluirVenda(Integer.parseInt(this.textCodigoVenda.getText()));
	 		this.dao.excluirItensVenda(Integer.parseInt(this.textCodigoVenda.getText()));
	 		dispose();
	 	}

	 	private void menuFecharMouseClicked(MouseEvent evt) {
	 		this.dao.excluirVenda(Integer.parseInt(this.textCodigoVenda.getText()));
	 		this.dao.excluirItensVenda(Integer.parseInt(this.textCodigoVenda.getText()));
	 		dispose();
	 	}
	 }