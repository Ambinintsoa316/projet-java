import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class Ecole extends JFrame{

	Statement stat;
	ResultSet resultat;
	JLabel  lbtitre, lnumEc, ldesignEc,ladresseEc, ldetailes, ltitreProjet;
	JTextField  insertNumEc, insertDesignEc, insertAdresseEc, insertDetailes;
	JButton btnAjoutt,btnSupp,blocation, btnSuppression, btnAnnuler;
	JTable tab1;
	JScrollPane scrol1;
	Connection con;

	public  Ecole(){

		this.setTitle("Ecoles");
		this.setSize(1200,700);
		this.setLocationRelativeTo(null);
		
		JPanel navBar = new JPanel();
		navBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		navBar.setBackground(new Color(100,101,102));
		navBar.setVisible(true);
		navBar.setBounds(200,0,1600,40);
		add(navBar);

		JPanel div = new JPanel();
		div.setLayout(new FlowLayout(FlowLayout.CENTER));
		div.setBackground(new Color(100,101,102));
		div.setBounds(620, 130, 5,520);

		JPanel page = new JPanel();
		page.setLayout(new FlowLayout(FlowLayout.LEFT));
		page.setBackground(new Color(84, 79, 79));
		page.setBounds(0, 0, 200, 700);

		ltitreProjet = new JLabel("Gestion d'une Session  CEPE");
		ltitreProjet.setBounds(50,50 , 10, 5);
		ltitreProjet.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		ltitreProjet.setForeground(new Color(232, 236, 239));
		navBar.add(ltitreProjet);

		JPanel pn = new JPanel(new BorderLayout());
		pn.setLayout(null);
		pn.setBackground(new Color(53, 53, 51));
		pn.setSize(100,200);
		add(pn);
		pn.add(navBar);
		pn.add(div);
		pn.add(page);
		
		lbtitre=new JLabel("Enregistrement des ecoles");
		lbtitre.setBounds(280,80,600,30);
		lbtitre.setFont(new Font("Trebuchet MS",Font.PLAIN,18));
		lbtitre.setForeground(new Color(61, 61, 193));
		pn.add(lbtitre);
		
		lnumEc = new JLabel("Numero:");
		lnumEc.setBounds(280,160,130,25);
		lnumEc.setFont(new Font("Open Sans",Font.PLAIN,13));
		lnumEc.setForeground(new Color(232, 236, 239));
		pn.add(lnumEc);
		
		insertNumEc = new JTextField();
		insertNumEc.setBounds(390,160,150,25);
		insertNumEc.setForeground(new Color(10, 10, 10));
		insertNumEc.setFont(new Font("Open Sans",Font.PLAIN,13));
		pn.add(insertNumEc);
		
		ldesignEc = new JLabel("Design:");
		ldesignEc.setBounds(280,200,150,25);
		ldesignEc.setFont(new Font("Open Sans",Font.PLAIN,13));
		ldesignEc.setForeground(new Color(232, 236, 239));
		pn.add(ldesignEc);

		btnSupp = new JButton("SUPPRIMER");
		btnSupp.setBounds(280,200,130,25);
		btnSupp.setForeground(new Color(232, 236, 239));
		btnSupp.setBackground(new Color(245, 12, 12));
		btnSupp.setFont(new Font("Open Sans",Font.PLAIN,13));
		btnSupp.setVisible(false);
		pn.add(btnSupp);

		btnAnnuler = new JButton("ANNULER");
		btnAnnuler.setBounds(440,200,100,25);
		btnAnnuler.setForeground(new Color(232, 236, 239));
		btnAnnuler.setBackground(new Color(143, 143, 57));
		btnAnnuler.setFont(new Font("Open Sans",Font.PLAIN,13));
		btnAnnuler.setVisible(false);
		pn.add(btnAnnuler);
		
		insertDesignEc = new JTextField();
		insertDesignEc.setBounds(390,200,150,25);
		insertDesignEc.setForeground(new Color(10, 10, 10));
		insertDesignEc.setFont(new Font("Open Sans",Font.PLAIN,13));
		pn.add(insertDesignEc);
		
		
		
		ladresseEc = new JLabel("Adresse:");
		ladresseEc.setBounds(280,240,200,25);
		ladresseEc.setFont(new Font("Open Sans",Font.PLAIN,13));
		ladresseEc.setForeground(new Color(232, 236, 239));
		pn.add(ladresseEc);
		
		insertAdresseEc = new JTextField();
		insertAdresseEc.setBounds(390,240,150,25);
		insertAdresseEc.setForeground(new Color(10, 10, 10));
		insertAdresseEc.setFont(new Font("Open Sans",Font.PLAIN,13));
		pn.add(insertAdresseEc);

		//bouton enregistrement des Ecoles

				ldetailes = new JLabel("Num Ecole:");
				ldetailes.setBounds(840, 80, 100, 25);
				ldetailes.setFont(new Font("Open Sans",Font.PLAIN,13));
				ldetailes.setForeground(new Color(232, 236, 239));
				pn.add(ldetailes);

				insertDetailes = new JTextField();
				insertDetailes.setBounds(920,80,100,25);
				insertDetailes.setForeground(new Color(10, 10, 10));
				insertDetailes.setFont(new Font("Open Sans",Font.PLAIN,13));
				
				pn.add(insertDetailes);
				blocation = new JButton("Details");
				blocation.setBounds(1060,80,100,25);
				blocation.setForeground(new Color(232, 236, 239));
				blocation.setBackground(new Color(70, 70, 223));
				blocation.setFont(new Font("Open Sans",Font.PLAIN,13));
				blocation.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String detailNumEc = insertDetailes.getText();
						dispose();
						Eleve lt = new Eleve(detailNumEc);
						lt.setVisible(true);
						
					}
				});
				pn.add(blocation);
		//ajout

		/*Connection au db */
		String host = "jdbc:postgresql://localhost:5432/cepe";
		String user = "postgres";
		String mdp = "#322*63#";

		btnAjoutt = new JButton("AJOUT");
		btnAjoutt.setBounds(280,280,100,25);
		btnAjoutt.setBackground(new Color(8,133,8));
		btnAjoutt.setForeground(new Color(232,236,239));
		btnAjoutt.setFont(new Font("Open Sans",Font.PLAIN,13));
		btnAjoutt.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		   String a = insertNumEc.getText(), b = insertDesignEc.getText(), c = insertAdresseEc.getText();

				try{
					con = DriverManager.getConnection(host, user,mdp );
					String qq="insert into ecole(numecole,design,adresse) values (?,?,?)";
					PreparedStatement pstmt = con.prepareStatement(qq);
					pstmt.setString(1, a);
					pstmt.setString(2, b);
					pstmt.setString(3, c);

					pstmt.executeUpdate();
					JOptionPane.showMessageDialog(null,"Enregistrement reussi!");
				}
				catch(SQLException ex){
					JOptionPane.showMessageDialog(null,"Impossible d'enregistrer!",null,JOptionPane.ERROR_MESSAGE);
				}
				dispose();
				Ecole vr=new Ecole();
				vr.setVisible(true);
				
			}
		});
		pn.add(btnAjoutt);


		// page de suppression
		btnSuppression = new JButton("SUPPRESSION");
		btnSuppression.setBounds(410,280,130,25);
		btnSuppression.setBackground(new Color(245, 12, 12));
		btnSuppression.setFont(new Font("Open Sans",Font.PLAIN,13));
		btnSuppression.setForeground(new Color(232,236,239));
		btnSuppression.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){

				ldesignEc.setVisible(false);
				ladresseEc.setVisible(false);
				insertDesignEc.setVisible(false);
				insertAdresseEc.setVisible(false);
				btnAjoutt.setVisible(false);
				btnSuppression.setVisible(false);
				btnSupp.setVisible(true);
				btnAnnuler.setVisible(true);
				
			}
		});
		pn.add(btnSuppression);

		/*Action de la bouton supprimer */

		btnSupp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String a = insertNumEc.getText();
		
				String qq = "delete from ecole where numecole = '"+a+"'";
				try{
					Statement stat = con.createStatement();
					if(JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){	
					stat.executeUpdate(qq);
					JOptionPane.showMessageDialog(null,"Suppr ssion  ffectu e!");
					}
				}
				catch(SQLException ex){
					JOptionPane.showMessageDialog(null,"Impossible de supprimer!",null,JOptionPane.ERROR_MESSAGE);
				}
				dispose();
				Ecole eg = new Ecole();
				eg.setVisible(true);
			}
		});

		/*Action de la bouton Annuler */

		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				ldesignEc.setVisible(true);
				ladresseEc.setVisible(true);
				insertDesignEc.setVisible(true);
				insertAdresseEc.setVisible(true);
				btnAjoutt.setVisible(true);
				btnSuppression.setVisible(true);
				btnSupp.setVisible(false);
				btnAnnuler.setVisible(false);

			}
		});

		////
		DefaultTableModel df = new DefaultTableModel();
		init();
		df.addColumn("NumEcole");
		df.addColumn("Design");
		df.addColumn("Adresse");
		
		tab1.setModel(df);
		pn.add(scrol1);
		

		try{
			con = DriverManager.getConnection(host, user, mdp);
			String sql="select * from ecole";
			Statement stat = con.createStatement();
			resultat = stat.executeQuery(sql);

			while(resultat.next()){
		df.addRow(new Object[]{
				resultat.getString("numecole"),
				resultat.getString("design"),
				resultat.getString("adresse"),
		});
			}
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null,"Impossible d'afficher la table eleve!" + ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
		}
		///
	}
	private void init(){
		tab1 = new JTable();
		tab1.setFont(new Font("Open Sans",Font.PLAIN,13));
		tab1.setRowMargin(0);
		tab1.setRowHeight(25);
		tab1.setShowVerticalLines(false);
		tab1.setShowHorizontalLines(false);
		scrol1 = new JScrollPane();
		scrol1.setViewportView(tab1);
		//scrl1.setBounds(20,290,540,160);
		scrol1.setBounds(660,130,500,520);
	}

	public static void main(String[] args) {
		/*  TODO Auto-generated method stub*/
		Ecole vt = new Ecole();
		vt.setVisible(true);

	}

}
