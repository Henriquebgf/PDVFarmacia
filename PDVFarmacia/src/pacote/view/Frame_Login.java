package pacote.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pacote.dao.FuncionariosDAO;
import pacote.view.Frame_Menu_Principal;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Frame_Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField textSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch(Exception e) {
			  System.out.println("Error setting native LAF: " + e);
			}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_Login frame = new Frame_Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame_Login() {
		setTitle("Seja bem vindo ao sistema - Autentica\u00E7\u00E3o");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 246);
		contentPane = new JPanel();
		this.setLocationRelativeTo(null);
		contentPane.setBackground(new Color(47, 79, 79));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_top = new JPanel();
		panel_top.setBackground(new Color(0, 128, 128));
		panel_top.setBounds(0, 0, 567, 48);
		contentPane.add(panel_top);
		panel_top.setLayout(null);

		JLabel label_TelaLogin = new JLabel("Tela Login");
		label_TelaLogin.setBounds(222, 11, 133, 26);
		label_TelaLogin.setForeground(new Color(255, 255, 255));
		label_TelaLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_top.add(label_TelaLogin);

		JLabel label_EmailLogin = new JLabel("E-mail:");
		label_EmailLogin.setForeground(new Color(255, 255, 255));
		label_EmailLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_EmailLogin.setBounds(25, 87, 57, 14);
		contentPane.add(label_EmailLogin);

		JTextArea textEmail = new JTextArea();
		textEmail.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textEmail.setBounds(85, 83, 389, 22);
		contentPane.add(textEmail);

		textSenha = new JPasswordField();
		textSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textSenha.setBounds(85, 128, 389, 20);
		contentPane.add(textSenha);

		JLabel label_SenhaLogin = new JLabel("Senha:");
		label_SenhaLogin.setForeground(new Color(255, 255, 255));
		label_SenhaLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_SenhaLogin.setBounds(25, 131, 57, 14);
		contentPane.add(label_SenhaLogin);

		JButton buttonEntrar = new JButton("Entrar");
		buttonEntrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				//Botao entrar
				try {
					String email,senha;
					email = textEmail.getText();
					senha = textSenha.getText();
					
					FuncionariosDAO dao = new FuncionariosDAO();
					
					dao.efetuaLogin(email, senha);
					
				}catch(Exception a) {
					JOptionPane.showMessageDialog(null,a);
				}
			}
		});
		buttonEntrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonEntrar.setBounds(179, 168, 89, 23);
		contentPane.add(buttonEntrar);

		JButton btnSair = new JButton("Sair");
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int op;

				Object[] options = { "Confirmar", "Cancelar" };

				op = JOptionPane.showOptionDialog(null, "Você tem certeza que deseja sair?", "Selecionar uma opção",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				if (op == 0) {
					System.exit(0);
				}
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSair.setBounds(279, 168, 89, 23);
		contentPane.add(btnSair);

	}
}
