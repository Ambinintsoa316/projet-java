import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Eleve extends JFrame {

	ResultSet resultat;
	JLabel  lbtitre,lbtitre2,lbtitre3,lnumEc, lnumEl, lnomEl,lprenomEl, ltitreProjet, ladmisCepe, lechouCepe, ladmis6;
	JTextField insertNumEc, insertNumEl, insertNomEl, insertPrenomEl, insertModMat, insertSearchEl;
	JButton btnAjout , btnSupp, btnEleve,btnEcole, btnModEl, btnterm, btnAjoutMat, btnModMat, btnDetailMatiere, btnDetailNote,
	btnModification, btnAnnuler, btnSave, btnSearch, btnAdmisCepe, btnAdmis6eme, btnEchouCepe;
	JTable tab1, tab2, tab3;
	JScrollPane scrl1,scrl2;
	Connection con;
	String Valdetail;
	

	public Eleve(String a){
		Valdetail = a;
		this.setTitle("Az'artCode_appli");
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
		
		lbtitre=new JLabel("Enregistrement des eleves");
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
		lbtitre2=new JLabel("Listes des Eleves ");
		lbtitre2.setBounds(210,350,600,30);
		lbtitre2.setFont(new Font("Trebuchet MS",Font.PLAIN,18));
		lbtitre2.setForeground(new Color(61, 61, 193));
		pn.add(lbtitre2);

		DefaultTableModel df2=new DefaultTableModel();
		init2();
		df2.addColumn("Numero Eleve");
		df2.addColumn("Numero Ecole");
		df2.addColumn("Nom Eleve");
		df2.addColumn("Prenom Eleve");
		
		tab2.setModel(df2);
		pn.add(scrl2);

		insertSearchEl = new JTextField("Nom ou Prenom");
		insertSearchEl.setBounds(840,350,200,25);
		insertSearchEl.setFont(new Font("Open Sans",Font.PLAIN,13));
		insertSearchEl.setForeground(Color.gray);
		pn.add(insertSearchEl);

		btnSearch = new JButton("Cherche");
		btnSearch.setBounds(1055,350,110,25);
		btnSearch.setForeground(new Color(232,236,239));
		btnSearch.setFont(new Font("Open Sans",Font.PLAIN,13));
		btnSearch.setBackground(new Color(70, 70, 223));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				int rowCount = df2.getRowCount();
				for(int i = rowCount -1; i >= 0; i--){
					df2.removeRow(i);
				}
				try{
					String val = insertSearchEl.getText();
					String sql = "Select*from eleve where nom like '%"+val+"%' or prenom like '%"+val+"%'";					Statement stmt = con.createStatement();
					resultat = stmt.executeQuery(sql);
					while(resultat.next()){

						df2.addRow(new Object[]{
							resultat.getString("numeleve"),
							resultat.getString("numecole"),
							resultat.getString("nom"),
							resultat.getString("prenom")
						
					});

				}
			
				} catch(SQLException ex){
					JOptionPane.showMessageDialog(null,"Impossible d'afficher !"+ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		pn.add(btnSearch);

		//Listes des eleves admis en cepe apres la deliberation
		ladmisCepe = new JLabel("Admis en CEPE:");
		ladmisCepe.setBounds(815,120,130,25);
		ladmisCepe.setFont(new Font("Open Sans",Font.PLAIN,13));
		ladmisCepe.setForeground(new Color(232, 236, 239));
		pn.add(ladmisCepe);
		
		btnAdmisCepe = new JButton("Admis");
		btnAdmisCepe.setBounds(1015,120,150,25);
		btnAdmisCepe.setFont(new Font("Open Sans",Font.PLAIN,13));
		btnAdmisCepe.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
			int rowCount = df2.getRowCount();
			for(int i = rowCount -1; i >= 0; i--){
				df2.removeRow(i);
			}
			try{
				String sql = "Select*from eleve where moyenne >= 9.75 and numecole = '"+Valdetail+"'";
				Statement stmt = con.createStatement();
				resultat = stmt.executeQuery(sql);
				while(resultat.next()){

					df2.addRow(new Object[]{
						resultat.getString("numeleve"),
						resultat.getString("numecole"),
						resultat.getString("nom"),
						resultat.getString("prenom")
					
				});

			}
		
			} catch(SQLException ex){
				JOptionPane.showMessageDialog(null,"Impossible d'afficher !:"+ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
			}
		}
		});
		pn.add(btnAdmisCepe);

		//listes des etudiants qui ont echouée
		lechouCepe = new JLabel("Listes des non Admis:");
		lechouCepe.setBounds(815,160,160,25);
		lechouCepe.setFont(new Font("Open Sans",Font.PLAIN,13));
		lechouCepe.setForeground(new Color(232, 236, 239));
		pn.add(lechouCepe);
				
		btnEchouCepe = new JButton("Echouée");
		btnEchouCepe.setBounds(1015,160,150,25);
		btnEchouCepe.setFont(new Font("Open Sans",Font.PLAIN,13));
		btnEchouCepe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				int rowCount = df2.getRowCount();
				for(int i = rowCount -1; i >= 0; i--){
					df2.removeRow(i);
				}
				try{
					String sql = "Select*from eleve where moyenne < 9.75 and numecole = '"+Valdetail+"'";
					Statement stmt = con.createStatement();
					resultat = stmt.executeQuery(sql);
					while(resultat.next()){
	
						df2.addRow(new Object[]{
							resultat.getString("numeleve"),
							resultat.getString("numecole"),
							resultat.getString("nom"),
							resultat.getString("prenom")
						
					});
	
				}
			
				} catch(SQLException ex){
					JOptionPane.showMessageDialog(null,"Impossible d'afficher !:"+ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		pn.add(btnEchouCepe);
		
		//listes des etudiant admis en 6eme (moyenne > 12)
		ladmis6 = new JLabel("Admis en 6 em:");
		ladmis6.setBounds(815,200,130,25);
		ladmis6.setFont(new Font("Century",Font.BOLD,13));
		ladmis6.setFont(new Font("Open Sans",Font.PLAIN,13));
		ladmis6.setForeground(new Color(232, 236, 239));
		pn.add(ladmis6);
		
		btnAdmis6eme = new JButton("Admis6eme");
		btnAdmis6eme.setBounds(1015,200,150,25);
		btnAdmis6eme.setFont(new Font("Open Sans",Font.PLAIN,13));
		btnAdmis6eme.setVisible(true);
		btnAdmis6eme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				int rowCount = df2.getRowCount();
				for(int i = rowCount -1; i >= 0; i--){
					df2.removeRow(i);
				}
				try{
					String sql = "Select*from eleve where moyenne >= 12 and numecole = '"+Valdetail+"'";
					Statement stmt = con.createStatement();
					resultat = stmt.executeQuery(sql);
					while(resultat.next()){
	
						df2.addRow(new Object[]{
							resultat.getString("numeleve"),
							resultat.getString("numecole"),
							resultat.getString("nom"),
							resultat.getString("prenom")
						
					});
	
				}
			
				} catch(SQLException ex){
					JOptionPane.showMessageDialog(null,"Impossible d'afficher !:"+ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		pn.add(btnAdmis6eme);

		//titre3
		lbtitre3 = new JLabel("Liste des Matieres");
		lbtitre3.setBounds(720,80,600,30);
		lbtitre3.setFont(new Font("Trebuchet MS",Font.PLAIN,18));
		lbtitre3.setForeground(new Color(61, 61, 193));
		lbtitre3.setVisible(false);
		pn.add(lbtitre3);
		
		lnumEl = new JLabel("Numero de l'Eleve:");
		lnumEl.setBounds(280,120,130,25);
		lnumEl.setFont(new Font("Open Sans",Font.PLAIN,13));
		lnumEl.setForeground(new Color(232, 236, 239));
		pn.add(lnumEl);
		
		insertNumEl = new JTextField();
		insertNumEl.setBounds(420,120,150,25);
		pn.add(insertNumEl);
		
		lnumEc = new JLabel("Numero de l'ecole:");
		lnumEc.setBounds(280,160,130,25);
		lnumEc.setFont(new Font("Open Sans",Font.PLAIN,13));
		lnumEc.setForeground(new Color(232, 236, 239));
		pn.add(lnumEc);
		
		insertNumEc = new JTextField();
		insertNumEc.setBounds(420,160,150,25);
		insertNumEc.setFont(new Font("Open Sans",Font.PLAIN,13));
		pn.add(insertNumEc);
		
		lnomEl = new JLabel("Nom de l'eleve:");
		lnomEl.setBounds(280,200,130,25);
		lnomEl.setFont(new Font("Open Sans",Font.PLAIN,13));
		lnomEl.setForeground(new Color(232, 236, 239));
		pn.add(lnomEl);
		
		insertNomEl = new JTextField();
		insertNomEl.setBounds(420,200,150,25);
		insertNomEl.setFont(new Font("Open Sans",Font.PLAIN,13));
		pn.add(insertNomEl);

		lprenomEl = new JLabel("Prenom de l'eleve:");
		lprenomEl.setBounds(280,240,130,25);
		lprenomEl.setFont(new Font("Century",Font.BOLD,13));
		lprenomEl.setFont(new Font("Open Sans",Font.PLAIN,13));
		lprenomEl.setForeground(new Color(232, 236, 239));
		pn.add(lprenomEl);
		
		insertPrenomEl = new JTextField();
		insertPrenomEl.setBounds(420,240,150,25);
		insertPrenomEl.setFont(new Font("Open Sans",Font.PLAIN,13));
		pn.add(insertPrenomEl);
		

		String host = "jdbc:postgresql://localhost:5432/cepe";
    	String user = "postgres";
    	String mdp = "#322*63#";
		
    	//insertion d'un eleve
		btnAjout = new JButton("Enregistrer");
		btnAjout.setBounds(280,280,110,25);
		btnAjout.setForeground(new Color(232,236,239));
		btnAjout.setFont(new Font("Open Sans",Font.PLAIN,13));
		btnAjout.setBackground(new Color(8,133,8));
		btnAjout.addActionListener(new ActionListener(){
			
		public void actionPerformed(ActionEvent e){
		String NE = insertNumEc.getText(), NEL = insertNumEl.getText(), NOEL = insertNomEl.getText(), PREL = insertPrenomEl.getText();
				   
			try{
					con = DriverManager.getConnection(host, user, mdp);
					String sql1="insert into eleve(numeleve,numecole,nom,prenom) values (?,?,?,?)";
					PreparedStatement pstmt = con.prepareStatement(sql1);

					pstmt.setString(1, NEL);
					pstmt.setString(2, NE);
					pstmt.setString(3, NOEL);
					pstmt.setString(4, PREL);


					pstmt.executeUpdate();
					JOptionPane.showMessageDialog(null,"Enregistrement réussi!");

					}
					catch(SQLException ex){
						JOptionPane.showMessageDialog(null,"Impossible d'enregistrer!"+ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
					}
					dispose();
					Eleve vr =new Eleve(a);
					vr.setVisible(true);
						
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
		String NE = insertNumEc.getText(), NEL = insertNumEl.getText(), NOEL = insertNomEl.getText(), PREL = insertPrenomEl.getText();
				   
			try{
					con = DriverManager.getConnection(host, user, mdp);
					String sql1="update eleve set numeleve = ?, numecole = ?, nom = ?, prenom = ? where numeleve = ?";
					PreparedStatement pstmt = con.prepareStatement(sql1);

					pstmt.setString(1, NEL);
					pstmt.setString(2, NE);
					pstmt.setString(3, NOEL);
					pstmt.setString(4, PREL);
					pstmt.setString(5, NE);


					pstmt.executeUpdate();
					JOptionPane.showMessageDialog(null,"Enregistrement réussi!");

					}
					catch(SQLException ex){
						JOptionPane.showMessageDialog(null,"Impossible d'enregistrer!"+ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
					}
					dispose();
					Eleve vr =new Eleve(a);
					vr.setVisible(true);
						
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
						Eleve  el = new Eleve(Valdetail);
						el.setVisible(true);
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

						String valMod = insertNumEl.getText();
						lnumEc.setVisible(true);
						lnomEl.setVisible(true);
						lprenomEl.setVisible(true);
						insertNumEc.setVisible(true);
						insertNomEl.setVisible(true);
						insertPrenomEl.setVisible(true);
						btnModification.setVisible(true);
						btnSupp.setVisible(true);
						btnAjout.setVisible(false);
						btnAnnuler.setVisible(true);
						btnModEl.setVisible(true);
						btnModification.setVisible(false);
						btnAnnuler.setBounds(450,280,120,25);
						btnSave.setVisible(true);

						try{
							String sql = "select*from eleve where numeleve = '"+valMod+"'";
							Statement stmt = con.createStatement();
							resultat = stmt.executeQuery(sql);

							while(resultat.next()){
								insertNumEc.setText(resultat.getString("numecole"));
								insertNomEl.setText(resultat.getString("nom"));
								insertPrenomEl.setText(resultat.getString("prenom"));
							}
						} catch(SQLException ex){
							JOptionPane.showMessageDialog(null,"Impossible de modifier!: "+ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
				pn.add(btnModification);

				//bouton de suppression d'un eleve

				btnSupp = new JButton("Supprimer");
				btnSupp.setBounds(280,320,290,25);
				btnSupp.setFont(new Font("Open Sans",Font.PLAIN,13));
				btnSupp.setForeground(new Color(232,236,239));
				btnSupp.setBackground(new Color(245, 12, 12));
				btnSupp.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e){
						
						try{
							String numEl = insertNumEl.getText();
							String supprEl = "delete from eleve where numeleve = '"+numEl+"'";
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
						Eleve eg = new Eleve(a);
						eg.setVisible(true);
					}
				});
				pn.add(btnSupp);

		//page de modification
		btnModEl = new JButton("Modification");
		btnModEl.setBounds(450,280,120,25);
		btnModEl.setForeground(new Color(232, 236, 239));
		btnModEl.setBackground(new Color(70, 70, 223));
		btnModEl.setFont(new Font("Open Sans",Font.PLAIN,13));
		btnModEl.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){

						lnumEc.setVisible(false);
						lnomEl.setVisible(false);
						lprenomEl.setVisible(false);
						insertNumEc.setVisible(false);
						insertNomEl.setVisible(false);
						insertPrenomEl.setVisible(false);
						btnModification.setVisible(true);
						btnSupp.setVisible(false);
						btnAjout.setVisible(false);
						btnAnnuler.setVisible(true);
						btnModEl.setVisible(false);
						lbtitre.setText("Modification d'un Eleve");
					}
				});
				pn.add(btnModEl);
	
				DefaultTableModel df = new DefaultTableModel();
				init();
				df.addColumn("Numero de l'Ecole");
				df.addColumn("Design");
				df.addColumn("Adresse");
				
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
				
				////
				// DefaultTableModel df2=new DefaultTableModel();
				// init2();
				// df2.addColumn("Numero Eleve");
				// df2.addColumn("Numero Ecole");
				// df2.addColumn("Nom Eleve");
				// df2.addColumn("Prenom Eleve");
				
				// tab2.setModel(df2);
				// pn.add(scrl2);

		//insertion de matiere

		btnAjoutMat = new JButton("Ajouter");
		btnAjoutMat.setBounds(890, 80, 100, 25);
		btnAjoutMat.setVisible(false);
		pn.add(btnAjoutMat);

		//Modification des matieres
		
		insertModMat = new JTextField("Num");
		insertModMat.setBounds(1000, 80, 60, 25);
		insertModMat.setVisible(false);
		pn.add(insertModMat);

		btnModMat = new JButton("Modifier");
		btnModMat.setBounds(1069, 80, 90, 25);
		btnModMat.setVisible(false);
		pn.add(btnModMat);

				//affichage des eleves
				
				try{
					con = DriverManager.getConnection(host, user, mdp);
					String sql2= "select*from eleve where numecole = '"+Valdetail+"'";
					Statement stat = con.createStatement();
					resultat = stat.executeQuery(sql2);
					while(resultat.next()){
				df2.addRow(new Object[]{
						resultat.getString("numeleve"),
						resultat.getString("numecole"),
						resultat.getString("nom"),
						resultat.getString("prenom")
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
