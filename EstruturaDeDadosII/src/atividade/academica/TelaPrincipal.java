package atividade.academica;



import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import comum.classes.Pedido;


public class TelaPrincipal extends JFrame{
	
	private JLabel lblLegend;
	private JComboBox<String> jCMenu;
	private JButton btnNext;
	private JPanel jPLegend;
	private JPanel jPCombo;
	private DefaultListModel listModel;
	private JList list;
	private JScrollPane JSList;
	
	
	public TelaPrincipal(){
		
		initComponents();
		
	}
	
	private void initComponents(){
		
		
		GridBagConstraints c = new GridBagConstraints();
		
		Dimension tamanhoFrame = new Dimension(700, 100);
		Dimension tamanhoPainelLegend = new Dimension(300, 30);
		Dimension tamanhoPainelCombo = new Dimension(800, 50);
		Dimension tamanhoList = new Dimension(800, 400);
		
		this.setPreferredSize(tamanhoFrame);
		this.setLayout(new GridBagLayout());
		this.setTitle("Pizzaria - Estrutura de dados II");
		
		jPLegend = new JPanel();
		jPLegend.setPreferredSize(tamanhoPainelLegend);
				
		lblLegend = new JLabel("Selecione uma opção:");
		lblLegend.setFont(new Font(null, Font.BOLD, 13));
		lblLegend.setMaximumSize(new Dimension(120,30));
		
		jPCombo = new JPanel();
		jPCombo.setPreferredSize(tamanhoPainelCombo);
		jPCombo.setLayout(new FlowLayout(FlowLayout.LEFT));

		
		listModel = new DefaultListModel();
		list = new JList(); 
		list.setSize(tamanhoList);
		list.setModel(listModel);
		
		
		JSList = new JScrollPane();
		JSList.setViewportView(list);
		JSList.setPreferredSize(tamanhoList);
		
		list.setVisible(false);
		
		jCMenu = new JComboBox();
		jCMenu.setPreferredSize(new Dimension(700,30));
		
		
		
		btnNext = new JButton("Ok");
		btnNext.setPreferredSize(new Dimension(60, 30));
		
		jCMenu.addItem("O endereço de um cliente (telefone informado como parâmetro):");
		jCMenu.addItem("Todos os pedidos de um cliente (telefone informado como parâmetro), em ordem cronológica:");
		jCMenu.addItem("Nomes dos clientes que fizeram pedidos em uma data específica (data informada como parâmetro, hora opcional)");
		jCMenu.addItem("As pizzas mais consumidas por um determinado cliente (telefone informado como parâmetro)");
		jCMenu.addItem("As pizzas que contém um determinado ingrediente (nome do ingrediente passado como parâmetro)");
		
		jPLegend.add(lblLegend);
		
		jPCombo.add(jCMenu);
		jPCombo.add(btnNext);
		
		c.anchor = GridBagConstraints.WEST;
		
		this.add(jPLegend, c);
		
		c.gridy = 1;
		
		this.add(jPCombo, c);
		
		c.gridy = 2;
		
		this.add(JSList, c);
		/*int l=0;
		while(l<8){
			System.out.println(mt.getFita().getIndexFita(l));
			l++;
		}*/
		

		btnNext.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				ArrayList<Pedido> pedidos;
				listModel.clear();
				list.setVisible(false);
				String telefone = "";
				String ingrediente = "";
				String data = "";
				String hora = "";
				int op = jCMenu.getSelectedIndex();
				//chamar as consultas dependendo do indice selecionado
				switch(op){
					case 0:
						
						telefone = JOptionPane.showInputDialog("Por favor, informe o telefone: ");
						String endereco = Consults.firstConsult(telefone);
						listModel.addElement("Endereco: "+endereco);
						list.setVisible(true);
						break;
						
					case 1:
						
						telefone = JOptionPane.showInputDialog("Por favor, informe o telefone: ");
						pedidos = Consults.secondConsult(telefone);
						
						for (Pedido p : pedidos) {
							listModel.addElement("Data: "+p.date+" | Hora: "+p.time+" | Sabor: "+p.pizza+" | Quantidade: "+p.qtd);
							list.setVisible(true);
						}
						
						break;
						
					case 2:
						String[] array;
						data = JOptionPane.showInputDialog("Por favor, informe a data: ");
						hora = null;
						hora = JOptionPane.showInputDialog("Por favor, informe a hora(opcional): ");
						
						if(hora==null){
							array = new String[2];
							array[0] = "pedido_data";
							array[1] = data;
						}else{
							array = new String[4];
							array[0] = "pedido_data";
							array[1] = data;
							array[2] = "pedido_hora";
							array[3] = hora;
						}
						
						ArrayList<String> clientes = Consults.thirthConsult(array);
						
						for (String c : clientes) {
							listModel.addElement(c);
							list.setVisible(true);
						}
						
						break;
						
					case 3:
						telefone = JOptionPane.showInputDialog("Por favor, informe o telefone: ");
						ArrayList<String> pizzas = Consults.forthConsult(telefone);
						
						for (String c : pizzas) {
							listModel.addElement("Sabor: "+ c);
							list.setVisible(true);
						}
						break;
						
					case 4:
						ingrediente = JOptionPane.showInputDialog("Por favor, informe o nome do ingrediente: ");
						ArrayList<String> pizzass = Consults.fifthConsult(ingrediente);
						for (String c : pizzass) {
							listModel.addElement("Sabor: "+c);
							list.setVisible(true);
						}
						break;
				}
				
			}
				
		});
		
		pack();
		
		this.setSize(900,600);
		
					  
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
	
	}
	
	
	private static void lookAndFeel(){
		try {  
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");  
        }  
  
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException Erro) {  
            JOptionPane.showMessageDialog(null, "Não foi possível iniciar com a"  
                    + " interface Nimbus."+"\nErro: "+Erro,  
                    "Erro de look and feel",  
                    JOptionPane.INFORMATION_MESSAGE);  
             
        }  
		
		

	}
	
	
	public static void main(String args[]){
		lookAndFeel();
		new TelaPrincipal();
	}
}
