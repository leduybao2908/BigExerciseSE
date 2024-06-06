package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dao.AdminItemDAO;
import model.Admin;
import model.Employee;
//import com.mysql.cj.jdbc.Driver;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.SystemColor;
//import org.jdesktop.swingx.border.DropShadowBorder;
import java.awt.TextField;

public class RegisterScreen extends JFrame {

	private JPanel contentPane;
	private JPasswordField textFieldConfirmpass;
	private JTextField textFieldUsername;
	private JPasswordField textFieldPassword;
	private JTextField textFieldGmail;
	private JLabel lblNewLabel;
	private JButton rdbtnCashier;
	private JButton rdbtnAdmin;
	private JTextField textFieldPhoneNumber;
	private JPasswordField passwordField;
	private JLabel LblUsername;
	private JLabel lblGmail;
	private JLabel lblPassword;
	private JLabel lblConfirm;
	private JLabel lblPhonenumber_1;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterScreen frame = new RegisterScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RegisterScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ButtonGroup buttonGroup = new ButtonGroup();

        rdbtnCashier =  new JButton();
		rdbtnCashier.setText("Cashier");
        rdbtnCashier.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rdbtnCashier.setBounds(64, 381, 104, 33);
        contentPane.add(rdbtnCashier);
        buttonGroup.add(rdbtnCashier);

        rdbtnAdmin =  new JButton();
		rdbtnAdmin.setText("Admin");
        rdbtnAdmin.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rdbtnAdmin.setBounds(287, 381, 104, 33);
        contentPane.add(rdbtnAdmin);
        buttonGroup.add(rdbtnAdmin);
		
		JLabel LabelUsername = new JLabel("Username");
		LabelUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelUsername.setBounds(10, 67, 90, 55);
		//contentPane.add(LabelUsername);
		
		textFieldConfirmpass = new JPasswordField();
		textFieldConfirmpass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldConfirmpass.setBounds(163, 244, 237, 47);
		contentPane.add(textFieldConfirmpass);
		
		textFieldUsername = new JTextField("");
		textFieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldUsername.setBounds(163, 67, 237, 47);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JButton ButtonLogin = new JButton();
		ButtonLogin.setText("LOGIN");
//		ButtonLogin.setBackground(SystemColor.activeCaption);
		ButtonLogin.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               
	            	openNewScreen();
	            }
	        });
		
		ButtonLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ButtonLogin.setBounds(150, 443, 145, 53);
		contentPane.add(ButtonLogin);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldPassword.setBounds(163, 186, 237, 47);
		contentPane.add(textFieldPassword);
		
		textFieldGmail = new JTextField();
		textFieldGmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldGmail.setBounds(163, 124, 237, 47);
		contentPane.add(textFieldGmail);
		
		JLabel LabelGmail = new JLabel("Gmail");
		LabelGmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelGmail.setBounds(10, 124, 90, 55);
		//contentPane.add(LabelGmail);
		
		JLabel LabelPassword = new JLabel("Password");
		LabelPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelPassword.setBounds(10, 100, 90, 55);
		//contentPane.add(LabelPassword);
		
		JLabel LabelConfirmPass = new JLabel("Confirm Password");
		LabelConfirmPass.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelConfirmPass.setBounds(10, 244, 158, 55);
		//contentPane.add(LabelConfirmPass);
		
		textFieldPhoneNumber = new JTextField("");
		textFieldPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldPhoneNumber.setBounds(163, 302, 237, 47);
		contentPane.add(textFieldPhoneNumber);
		
		JButton ButtonRegister = new JButton();
			ButtonRegister.setText("REGISTER");

		ButtonRegister.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ButtonRegister.setBounds(150, 506, 145, 53);
		ButtonRegister.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	int ID = AdminItemDAO.getEmployeeCount() + 1;
	                String NAME = textFieldUsername.getText();
	                String GMAIL = textFieldGmail.getText();
	                String PASSWORD = textFieldPassword.getText();
	                String CONFIRM = textFieldConfirmpass.getText();
	                float WORKHOUR = 0;
	                String PHONENUMBER = textFieldPhoneNumber.getText();

	                // Modify the username based on selection
	                if (rdbtnCashier.isSelected() && PASSWORD.equals(CONFIRM)) {
	                	 Employee user = new Employee(ID, NAME, GMAIL, PASSWORD , PHONENUMBER, WORKHOUR);
	 	                AdminItemDAO.insertloginCashier(user);
	 	               clearFields();
	 	                } else if (rdbtnAdmin.isSelected() && PASSWORD.equals(CONFIRM)) {
	 	                	 Admin admin = new Admin(NAME, GMAIL, PASSWORD);
	 		                AdminItemDAO.insertloginAdmin(admin);
	 		               clearFields();
	 		                }else {
	 		                	JOptionPane.showMessageDialog(null, "Mật khẩu không khớp!");
	 		                }        
	            }
	        });
		
	        contentPane.add(ButtonRegister);
		
		JLabel LabelRegister = new JLabel(" BADMINTON STORE ");
		LabelRegister.setFont(new Font("Tahoma", Font.PLAIN, 25));
		LabelRegister.setBounds(103, 6, 330, 51);
		contentPane.add(LabelRegister);
		
		JLabel lblPhonenumber = new JLabel("PhoneNumber");
		lblPhonenumber.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPhonenumber.setBounds(10, 302, 158, 55);
	//	contentPane.add(lblPhonenumber);
		
        LblUsername = new JLabel("Username");
        LblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        LblUsername.setBounds(29, 67, 104, 47);
        contentPane.add(LblUsername);
        
        lblGmail = new JLabel("Gmail:");
        lblGmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblGmail.setBounds(29, 124, 104, 47);
        contentPane.add(lblGmail);
        
        lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPassword.setBounds(29, 186, 104, 47);
        contentPane.add(lblPassword);
        
        lblConfirm = new JLabel("Confirm:");
        lblConfirm.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblConfirm.setBounds(29, 244, 104, 47);
        contentPane.add(lblConfirm);
        
        lblPhonenumber_1 = new JLabel("Phonenumber:");
        lblPhonenumber_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPhonenumber_1.setBounds(29, 302, 124, 47);
        contentPane.add(lblPhonenumber_1);
        
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	//	lblNewLabel.setIcon(new ImageIcon(LoginScreenn.class.getResource("badminton3.png")));
		lblNewLabel.setBounds(0, 10, 459, 583);
		contentPane.add(lblNewLabel);	

	JLabel lblNewLabel = new JLabel("");
        ImageIcon originalIcon = new ImageIcon(Paymentscreen.class.getResource("bgregist.jpg"));
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(473, 630, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        lblNewLabel.setIcon(scaledIcon);
        lblNewLabel.setBounds(0, 0, 473, 630);
        contentPane.add(lblNewLabel);

        // Set the background label to the lowest Z order
        contentPane.setComponentZOrder(lblNewLabel, contentPane.getComponentCount() - 1);
        
		setLocationRelativeTo(null);
		
	}
	
	public void openNewScreen() {
      LoginScreen NewScreen = new LoginScreen();      
        NewScreen.setVisible(true);
        dispose();
    }
	
	private void clearFields() {
        textFieldUsername.setText("");
        textFieldGmail.setText("");
        textFieldPassword.setText("");
        textFieldConfirmpass.setText("");
        textFieldPhoneNumber.setText("");

    }
		}
		