package turing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main implements ActionListener{
	
	private Function function;
	private String[] str, aux;

	private FileReader fr;
	private BufferedReader br;
	
	private JPanel painel;
	private JButton btOpen;
	private JButton btRun;
	private JFrame janela;
	private JTextField entrada;
	private JLabel label, cadeia;
	//private Container container;
	//private SpringLayout layout;
	
	public void escolherArquivo(){
		
		JFileChooser chooser = new JFileChooser();
		int retorno = chooser.showOpenDialog(null);
		
		if (retorno == JFileChooser.APPROVE_OPTION){
		
			try {				
				function = new Function();
				fr = new FileReader(chooser.getSelectedFile());
				br = new BufferedReader(fr);
				
				//Reading first line with the elements of the machine
				if (br.ready()){
					str = br.readLine().split("-");
					
					for (int i = 0; i < str.length; i++){
						str[i] = str[i].replace("{", "");
						str[i] = str[i].replace("}", "");
						str[i] = str[i].replace(" ", "");
					}
					
					aux = str[0].split(",");
					
					for (int i = 0; i < aux.length; i++){
						function.getAlpha().add(aux[i]);
					}
					
					aux = str[1].split(",");
					
					for (int i = 0; i < aux.length; i++){
						State sta = new State(aux[i]);
						function.getStates().add(sta);
					}
					
					State sta = new State(str[2]);
					
					function.setInicial(sta);
					
					aux = str[3].split(",");
					
					for (int i = 0; i < aux.length; i++){
						State s = new State(aux[i]);
						function.getFinalStates().add(s);
					}
					
					aux = str[4].split(",");
					
					for (int i = 0; i < aux.length; i++){
						function.getAlphaAux().add(aux[i]);
					}
				}
				
				while (br.ready()){
					
					str = br.readLine().split("-");
					
					Key key = new Key(str[0], str[2]);
					
					State std = new State(str[0]);
					State std2 = new State(str[1]);
					
					std.setDestiny(std2);
					std.setEntry(str[2]);
					std.setPrint(str[3]);
					std.setMove(str[4]);
					
					Transition trans = new Transition(key, std);
					
					function.getTransitions().add(trans);
				}
				
			} catch (FileNotFoundException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
	}	
	
	public void openJanela(){
		montaTela();
	}
	
	public void montaTela(){
		preparaJanela();
		preparaPainelPrincipal();
		preparaEntrada();
		preparaBotaoOpen();
		preparaBotaoRun();
		preparaLabel();
		mostraJanela();
	}
	
	private void preparaJanela() {
		janela = new JFrame("Máquina de Turing");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//container = janela.getContentPane();
		//layout = new SpringLayout();
		//container.setLayout(layout);
	}

	private void preparaPainelPrincipal() {
		painel = new JPanel();
		painel.setBorder(BorderFactory.createTitledBorder("Turing"));
		janela.add(painel);
	}

	private void preparaBotaoOpen() {
		btOpen = new JButton("Carregar Arquivo");
		btOpen.addActionListener(this);
		painel.add(btOpen);
		//layout.putConstraint(SpringLayout.SOUTH, btOpen, 5, SpringLayout.SOUTH, container);
	}
	
	private void preparaEntrada(){
		entrada = new JTextField("", 20);
		cadeia = new JLabel("Cadeia: ");
		painel.add(cadeia);
		painel.add(entrada);
	}
	
	private void preparaLabel(){
		label = new JLabel();
		painel.add(label);
	}

	private void mostraJanela() {
		janela.pack();
		janela.setSize(350, 300);
		janela.setVisible(true);
	}

	private void preparaBotaoRun() {
		btRun = new JButton("Run");
		btRun.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (function == null){
					label.setText("");
					JOptionPane.showMessageDialog(janela, "Nenhum arquivo foi carrega.");
				}else{
					function.setChain(entrada.getText() + " ");
					if (function.processChain())
						label.setText("Cadeia Aceita.      ");
					else
						label.setText("Cadeia Rejeitada.       ");
				}
			}
		});
		painel.add(btRun);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		escolherArquivo();
	}
	
	public static void main(String[] args){		
		new Main().openJanela();
	}

}