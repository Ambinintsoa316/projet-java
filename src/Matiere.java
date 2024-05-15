import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

public class Matiere extends JFrame {

    ResultSet resultat;
	JLabel  lbtitre,lbtitre2,lbtitre3,lnumMat, ldesignMat, lcoefMat, ltitreProjet;
	JTextField insertNumMat, insertDesignMat, insertCoefMat, insertPrenomEl;
	JButton btnAjout , btnSupp, btnMat,btnEcole, btnModMat, btnterm, btnAjoutMat, btnDetailMatiere, btnDetailNote, btnEleve,
	btnModification, btnAnnuler, btnSave;
	JTable tab1, tab2;
	JScrollPane scrl1,scrl2;
	Connection con;
	String Valdetail;

    public  Matiere (String a){

		Valdetail = a;

		this.setTitle("Matiere");
		this.setSize(1200,700);
		this.setLocationRelativeTo(null);

		JPanel navBar = new JPanel();
		navBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		navBar.setBackground(new Color(100,101,102));
		navBar.setVisible(true);
		navBar.setBounds(200,0,1600,40);
		add(navBar);

		ltitreProjet = new JLabel("Gestion d'une Session  CEPE");
		ltitreProjet.setBounds(200,10 , 10, 5);
		ltitreProjet.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		ltitreProjet.setForeground(new Color(232, 236, 239));
		ltitreProjet.setLayout(new FlowLayout(FlowLayout.CENTER));
		navBar.add(ltitreProjet);

		JPanel page = new JPanel();
		page.setLayout(new FlowLayout(FlowLayout.LEFT));
		page.setBackground(new Color(84, 79, 79));
		page.setBounds(0, 0, 200, 700);

		
		JPanel pn = new JPanel();
		pn.setLayout(null);
		pn.setBackground(new Color(53, 53, 51));
		add(pn);
		pn.add(navBar);
		pn.add(page);

		
		
		lbtitre=new JLabel("Enregistrement des Matieres");
		lbtitre.setBounds(280,80,600,30);
		lbtitre.setFont(new Font("Trebuchet MS",Font.PLAIN,18));
		lbtitre.setForeground(new Color(61, 61, 193));
		pn.add(lbtitre);

		//details des ecoles
		btnEcole = new JButton("Ecoles");
		btnEcole.setBounds(40,160,100,25);
		btnEcole.setFont(new Font("Open Sans",Font.PLAIN,13));
		btnEcole.setForeground(new Color(232, 236, 239));
		btnEcole.setBackground(new Color(70, 70, 223));
		btnEcole.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				Ecole vf = new Ecole();
				vf.setVisible(true);
				
			}
		});
		pn.add(btnEcole);

		//detailes des eleves
		btnEleve = new JButton("Eleves");
		btnEleve.setBounds(40,360,100,25);
		btnEleve.setFont(new Font("Open Sans",Font.PLAIN,13));
		btnEleve.setForeground(new Color(232, 236, 239));
		btnEleve.setBackground(new Color(70, 70, 223));
		btnEleve.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				dispose();
				Eleve el = new Eleve(Valdetail);
				el.setVisible(true);

			}
		});
		pn.add(btnEleve);


		//detail Matiere
		btnDetailMatiere = new JButton("Matiere");
		btnDetailMatiere.setBounds(40,230,100,25);
		btnDetailMatiere.setVisible(true);
		btnDetailMatiere.setFont(new Font("Open Sans",Font.PLAIN,13));
		btnDetailMatiere.setForeground(new Color(232, 236, 239));
		btnDetailMatiere.setBackground(new Color(70, 70, 223));
		btnDetailMatiere.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				Matiere mat = new Matiere(Valdetail);
				mat.setVisible(true);
			}
		});
		pn.add(btnDetailMatiere);

		//detail Note
		btnDetailNote = new JButton("Note");
		btnDetailNote.setBounds(40,300,100,25);
		btnDetailNote.setVisible(true);
		btnDetailNote.setFont(new Font("Open Sans",Font.PLAIN,13));
		btnDetailNote.setForeground(new Color(232, 236, 239));
		btnDetailNote.setBackground(new Color(70, 70, 223));
		btnDetailNote.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				Note note = new Note(Valdetail);
				note.setVisible(true);
			}
		});
		pn.add(btnDetailNote);

		//titre2
		lbtitre2=new JLabel("Listes des Matieres ");
		lbtitre2.setBounds(210,350,600,30);
		lbtitre2.setFont(new Font("Trebuchet MS",Font.PLAIN,18));
		lbtitre2.setForeground(new Color(61, 61, 193));
		pn.add(lbtitre2);
		
		lnumMat = new JLabel("Numero:");
		lnumMat.setBounds(280,120,130,25);
		lnumMat.setFont(new Font("Open Sans",Font.PLAIN,13));
		lnumMat.setForeground(new Color(232, 236, 239));
		pn.add(lnumMat);

		insertNumMat = new JTextField();
		insertNumMat.setBounds(420,120,150,25);
		insertNumMat.setFont(new Font("Open Sans",Font.PLAIN,13));
		pn.add(insertNumMat);
		
		ldesignMat = new JLabel("Design:");
		ldesignMat.setBounds(280,160,130,25);
		ldesignMat.setFont(new Font("Open Sans",Font.PLAIN,13));
		ldesignMat.setForeground(new Color(232, 236, 239));
		pn.add(ldesignMat);
		
		insertDesignMat = new JTextField();
		insertDesignMat.setBounds(420,160,150,25);
		insertDesignMat.setFont(new Font("Open Sans",Font.PLAIN,13));
		pn.add(insertDesignMat);
		
		lcoefMat = new JLabel("Coefficient:");
		lcoefMat.setBounds(280,200,130,25);
		lcoefMat.setFont(new Font("Century",Font.BOLD,13));
		lcoefMat.setFont(new Font("Open Sans",Font.PLAIN,13));
		lcoefMat.setForeground(new Color(232, 236, 239));
		pn.add(lcoefMat);
		
		insertCoefMat = new JTextField();
		insertCoefMat.setBounds(420,200,150,25);
		insertCoefMat.setFont(new Font("Open Sans",Font.PLAIN,13));
		pn.add(insertCoefMat);
		

		String host = "jdbc:postgresql://localhost:5432/cepe";
    	String user = "postgres";
    	String mdp = "#322*63#";
		
    	//insertion d'un matiere
		btnAjout = new JButton("Enregistrer");
		btnAjout.setBounds(280,280,110,25);
		btnAjout.setForeground(new Color(232,236,239));
		btnAjout.setFont(new Font("Open Sans",Font.PLAIN,13));
		btnAjout.setBackground(new Color(8,133,8));
		btnAjout.addActionListener(new ActionListener(){
			
		public void actionPerformed(ActionEvent e){
		String NM = insertNumMat.getText(), DM = insertDesignMat.getText(), CM = insertCoefMat.getText();
        int COEFM = Integer.parseInt(CM); 
			try{
					con = DriverManager.getConnection(host, user, mdp);
					String sql1="insert into matiere(nummat,designmat,coef) values (?,?,?)";
					PreparedStatement pstmt = con.prepareStatement(sql1);

					pstmt.setString(1, NM);
					pstmt.setString(2, DM);
					pstmt.setInt(3, COEFM);

					pstmt.executeUpdate();
					JOptionPane.showMessageDialog(null,"Enregistrement réussi!");

					}
					catch(SQLException ex){
						JOptionPane.showMessageDialog(null,"Impossible d'enregistrer!"+ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
					}
					dispose();
                    Matiere mat = new Matiere(Valdetail);
                    mat.setVisible(true);
						
				}
			});
				pn.add(btnAjout);

		//Enregistrement de modification
		btnSave = new JButton("Enregistrer");
		btnSave.setBounds(280,280,110,25);
		btnSave.setForeground(new Color(232,236,239));
		btnSave.setFont(new Font("Open Sans",Font.PLAIN,13));
		btnSave.setBackground(new Color(8,133,8));
		btnSave.setVisible(false);
		btnSave.addActionListener(new ActionListener(){
			
		public void actionPerformed(ActionEvent e){
			String NM = insertNumMat.getText(), DM = insertDesignMat.getText(), CM = insertCoefMat.getText();
            int COEFM = Integer.parseInt(CM);

				   try{
						String modEl = "update matiere set nummat = ?, designmat = ?, coef = ? where nummat = ?";

						PreparedStatement pstmtModEl = con.prepareStatement(modEl);
						pstmtModEl.setString(1, NM);
						pstmtModEl.setString(2, DM);
						pstmtModEl.setInt(3, COEFM);
                        pstmtModEl.setString(4, NM);
						pstmtModEl.executeUpdate();
						JOptionPane.showMessageDialog(null,"Modification éffectuée!");
						}
						catch(SQLException ex){
							JOptionPane.showMessageDialog(null,"Impossible de modifier!: "+ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
						}
						dispose();
                        Matiere mat = new Matiere(Valdetail);
                        mat.setVisible(true);
						
					}
			});
				pn.add(btnSave);

				//bouton d'annulation

				btnAnnuler = new JButton("Annuler");
				btnAnnuler.setBounds(450, 160, 120, 25);
				btnAnnuler.setForeground(new Color(232, 236, 239));
				btnAnnuler.setBackground(new Color(143, 143, 57));
				btnAnnuler.setFont(new Font("Open Sans",Font.PLAIN,13));
				btnAnnuler.setVisible(false);
				btnAnnuler.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						dispose();
						Matiere mat = new Matiere(Valdetail);
						mat.setVisible(true);
					}
				});
				pn.add(btnAnnuler);

				//page de modification
				btnModification = new JButton("Modifier");
				btnModification.setBounds(280, 160, 120, 25);
				btnModification.setForeground(new Color(232, 236, 239));
				btnModification.setBackground(new Color(70, 70, 223));
				btnModification.setFont(new Font("Open Sans",Font.PLAIN,13));
				btnModification.setVisible(false);
				btnModification.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){

						String valMod = insertNumMat.getText();
						ldesignMat.setVisible(true);
						lcoefMat.setVisible(true);
						insertDesignMat.setVisible(true);
						insertCoefMat.setVisible(true);
						btnModification.setVisible(true);
						btnSupp.setVisible(true);
						btnAjout.setVisible(false);
						btnAnnuler.setVisible(true);
						btnModMat.setVisible(false);
						btnModification.setVisible(false);
						btnAnnuler.setBounds(450,280,120,25);
						btnSave.setVisible(true);

						try{
							String sql = "select*from matiere where nummat = '"+valMod+"'";
							Statement stmt = con.createStatement();
							resultat = stmt.executeQuery(sql);

							while(resultat.next()){
								insertDesignMat.setText(resultat.getString("designmat"));
								insertCoefMat.setText(resultat.getString("coef"));
							}
						} catch(SQLException ex){
							JOptionPane.showMessageDialog(null,"Impossible de modifier!: "+ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
				pn.add(btnModification);


		//page de modification
		btnModMat = new JButton("Modification");
		btnModMat.setBounds(450,280,120,25);
		btnModMat.setForeground(new Color(232, 236, 239));
		btnModMat.setBackground(new Color(70, 70, 223));
		btnModMat.setFont(new Font("Open Sans",Font.PLAIN,13));
		btnModMat.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){

			ldesignMat.setVisible(false);
			lcoefMat.setVisible(false);
			insertDesignMat.setVisible(false);
			insertCoefMat.setVisible(false);
			btnModification.setVisible(false);
			btnSupp.setVisible(true);
			btnAjout.setVisible(false);
			btnAnnuler.setVisible(true);
			btnModMat.setVisible(false);
			btnModification.setVisible(true);
			btnSave.setVisible(false);
			lbtitre.setText("Modification d'un Matiere");

			}
		});
			
				pn.add(btnModMat);

				//bouton de suppression d'un matiere

				btnSupp = new JButton("Supprimer");
				btnSupp.setBounds(280,320,290,25);
				btnSupp.setFont(new Font("Open Sans",Font.PLAIN,13));
				btnSupp.setForeground(new Color(232,236,239));
				btnSupp.setBackground(new Color(245, 12, 12));
				btnSupp.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e){
						
						try{
							String numMat = insertNumMat.getText();
							String supprEl = "delete from matiere where nummat = '"+numMat+"'";
							Statement stat = con.createStatement();
							if(JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer ?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){	
							stat.executeUpdate(supprEl);
							JOptionPane.showMessageDialog(null,"Suppression  efectue !");
							}
						}
						catch(SQLException ex){
							JOptionPane.showMessageDialog(null,"Impossible de supprimer!: "+ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
						}
						dispose();
                        Matiere mat = new Matiere(Valdetail);
                        mat.setVisible(true);
					}
				});
				pn.add(btnSupp);
	
				DefaultTableModel df = new DefaultTableModel();
				init();
				df.addColumn("Numero");
				df.addColumn("Design");
				df.addColumn("Coefficient");
				
				tab1.setModel(df);
				pn.add(scrl1);

				//affichage des matiere		
				try{
					con = DriverManager.getConnection(host, user, mdp);
					String sql = "select * from matiere";
					Statement stat = con.createStatement();

					resultat = stat.executeQuery(sql);

					while(resultat.next()){

					df.addRow(new Object[]{
						resultat.getString("nummat"),
						resultat.getString("designmat"),
						resultat.getString("coef")
				});
					}
				}
				catch(SQLException ex){
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,"Impossible d'afficher les resultat ecole!" + ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
				}
				
				DefaultTableModel df2=new DefaultTableModel();
				init2();
				df2.addColumn("Numero Eleve");
				df2.addColumn("Numero Ecole");
				df2.addColumn("Nom Eleve");
				
				tab2.setModel(df2);
				pn.add(scrl2);

				//affichage des eleves			
				try{
					con = DriverManager.getConnection(host, user, mdp);
					String sql2= "select*from matiere";
					Statement stat = con.createStatement();
					resultat = stat.executeQuery(sql2);
					while(resultat.next()){
				df2.addRow(new Object[]{
						resultat.getString("nummat"),
						resultat.getString("designmat"),
						resultat.getString("coef")
				});
					}
				}
				catch(SQLException ex){
					JOptionPane.showMessageDialog(null,"Impossible d'afficher les resultat ecole!" + ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
				}
				
				
	}
	private void init(){
		tab1=new JTable();
		scrl1=new JScrollPane();
		scrl1.setViewportView(tab1);
		scrl1.setBounds(720,120,440,190);
		scrl1.setVisible(false);
	}
	
	private void init2(){
		tab2=new JTable();
		tab2.setRowMargin(0);
		tab2.setRowHeight(25);
		tab2.setShowVerticalLines(false);
		tab2.setShowHorizontalLines(false);
		tab2.setFont(new Font("Open Sans",Font.PLAIN,13));
		scrl2=new JScrollPane();
		scrl2.setViewportView(tab2);
		scrl2.setBounds(210,400,955,250);
	}

	public static void main(String[] args) {
        Ecole ec = new Ecole();
		ec.setVisible(true);
	}
}
