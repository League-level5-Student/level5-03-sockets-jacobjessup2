package _02_Chat_Application;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import _00_Click_Chat.gui.ButtonClicker;
import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame{
JPanel panel = new JPanel();
JButton button = new JButton("send");
JTextField text = new JTextField("                        ");
static JLabel chatS = new JLabel("<html><br/><html>");
static JLabel chatC = new JLabel("<html><br/><html>");
static String chatLog = "";
	Server2 server;
	Client2 client;
	
	
	public static void main(String[] args) {
		new ChatApp();
	}
	
	void log(String s) {
		
	}
	
	public ChatApp(){
		
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Chat!", JOptionPane.YES_NO_OPTION);
		add(panel);
		if(response == JOptionPane.YES_OPTION){
			server = new Server2(8080);
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			button.addActionListener((e)->{
				//System.out.println(text.getText());
				//chatLog = chatLog+"S: "+text.getText()+"<br/>";
				chatS.setText("<html>"+ chatS.getText().substring(6, chatS.getText().length()-7)+ "S: "+text.getText() + "<br/></html>");
				System.out.println(chatS.getText().substring(6, chatS.getText().length()-7));
				server.sendMessage(text.getText());
				text.setText("                        ");
			});
			
			panel.add(button);
			panel.add(text);
			panel.add(chatS);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			server.start();
			
		}else{
			setTitle("CLIENT");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			client = new Client2(ipStr, port);
			button.addActionListener((e)->{
				//System.out.println(text.getText());
				//chatLog = chatLog+"C: "+text.getText()+"<br/>";
				chatC.setText("<html>"+ chatC.getText().substring(6, chatC.getText().length()-7)+ "C: "+text.getText() + "<br/></html>");
				System.out.println(chatC.getText().substring(6, chatC.getText().length()-7));
				
				client.sendMessage(text.getText());
				text.setText("                        ");
			});
			
			panel.add(button);
			panel.add(text);
			panel.add(chatC);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			client.start();
		}
		
	}
}
