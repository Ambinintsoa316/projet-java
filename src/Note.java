import javax.swing.*;
import java.sql.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Note extends JFrame{

    ResultSet resultat;
	JLabel  lbtitre,lbtitre2,lbtitre3,las, lnumEl, lnumMat, lnote, ltitreProjet , ladmisCepe, lechouCepe, ladmis6;
	JTextField insertAs, insertNumEl, insertNumMat, insertNote;
	JButton btnAjout , btnSupp, btnMat, btnModNote, btnEcole, btnterm, btnAjoutNote, btnDetailMatiere, btnDetailNote, btnEleve, btnMoyenne,
	btnModification, btnAnnuler, btnSave , btnAdmisCepe, btnAdmis6eme, btnEchouCepe;
	JTable tab1, tab2;
	JScrollPane scrl1,scrl2;
	Connection con;
	String Valdetail;
	float m;

    public Note(String a){
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

		
		
		lbtitre=new JLabel("Enregistrement des Notes");
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

		las = new JLabel("Annee Scolaire:");
		las.setBounds(280,120,140,25);
		las.setFont(new Font("Open Sans",Font.PLAIN,13));
		las.setForeground(new Color(232, 236, 239));
		pn.add(las);
		
		insertAs = new JTextField();
		insertAs.setBounds(420,120,150,25);
		pn.add(insertAs);
		
		lnumEl = new JLabel("Numero de l'Eleve:");
		lnumEl.setBounds(280,160,130,25);
		lnumEl.setFont(new Font("Open Sans",Font.PLAIN,13));
		lnumEl.setForeground(new Color(232, 236, 239));
		pn.add(lnumEl);
		
		insertNumEl = new JTextField();
		insertNumEl.setBounds(420,160,150,25);
		insertNumEl.setFont(new Font("Open Sans",Font.PLAIN,13));
		pn.add(insertNumEl);
		
		lnumMat = new JLabel("Numero  Matiere:");
		lnumMat.setBounds(280,200,130,25);
		lnumMat.setFont(new Font("Open Sans",Font.PLAIN,13));
		lnumMat.setForeground(new Color(232, 236, 239));
		pn.add(lnumMat);
		
		insertNumMat = new JTextField();
		insertNumMat.setBounds(420,200,150,25);
		insertNumMat.setFont(new Font("Open Sans",Font.PLAIN,13));
		pn.add(insertNumMat);

		lnote = new JLabel("Note:");
		lnote.setBounds(280,240,130,25);
		lnote.setFont(new Font("Century",Font.BOLD,13));
		lnote.setFont(new Font("Open Sans",Font.PLAIN,13));
		lnote.setForeground(new Color(232, 236, 239));
		pn.add(lnote);
		
		insertNote = new JTextField();
		insertNote.setBounds(420,240,150,25);
		insertNote.setFont(new Font("Open Sans",Font.PLAIN,13));
		pn.add(insertNote);

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
            String AS = insertAs.getText(), NEL = insertNumEl.getText(), NM = insertNumMat.getText(), NOTE = insertNote.getText();
            int N = Integer.parseInt(NOTE);
			try{
					con = DriverManager.getConnection(host, user, mdp);
					String sql1="insert into note(anneescolaire,numeleve,nummat,note) values (?,?,?,?)";
					PreparedStatement pstmt = con.prepareStatement(sql1);

					pstmt.setString(1, AS);
					pstmt.setString(2, NEL);
					pstmt.setString(3, NM);
                    pstmt.setInt(4, N);

					pstmt.executeUpdate();
					JOptionPane.showMessageDialog(null,"Enregistrement réussi!");

					}
					catch(SQLException ex){
						JOptionPane.showMessageDialog(null,"Impossible d'enregistrer!"+ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
					}
					dispose();
                    Note note = new Note(Valdetail);
                    note.setVisible(true);
						
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
			String AS = insertAs.getText(), NEL = insertNumEl.getText(), NM = insertNumMat.getText(), NOTE = insertNote.getText();
			int N = Integer.parseInt(NOTE);
	
					   try{
							String modEl = "update note set anneescolaire = ?, numeleve = ?, nummat = ?, note = ? where numeleve = ?";
	
							PreparedStatement pstmtModEl = con.prepareStatement(modEl);
							pstmtModEl.setString(1, AS);
							pstmtModEl.setString(2, NEL);
							pstmtModEl.setString(3, NM);
							pstmtModEl.setInt(4, N);
							pstmtModEl.setString(5,NEL);
							pstmtModEl.executeUpdate();
							JOptionPane.showMessageDialog(null,"Modification éffectuée!");
							}
							catch(SQLException ex){
								JOptionPane.showMessageDialog(null,"Impossible de modifier!: "+ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
							}
							dispose();
							Note note = new Note(Valdetail);
							note.setVisible(true);
							
						}
			});
				pn.add(btnSave);

				//bouton d'annulation

				btnAnnuler = new JButton("Annuler");
				btnAnnuler.setBounds(450, 240, 120, 25);
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
				btnModification.setBounds(280, 240, 120, 25);
				btnModification.setForeground(new Color(232, 236, 239));
				btnModification.setBackground(new Color(70, 70, 223));
				btnModification.setFont(new Font("Open Sans",Font.PLAIN,13));
				btnModification.setVisible(false);
				btnModification.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){

						String valMod = insertNumEl.getText();
						las.setVisible(true);
						lnumMat.setVisible(true);
						lnote.setVisible(true);
						insertAs.setVisible(true);
						insertNumMat.setVisible(true);
						insertNote.setVisible(true);
						btnModification.setVisible(true);
						btnSupp.setVisible(true);
						btnAjout.setVisible(false);
						btnAnnuler.setVisible(true);
						btnModification.setVisible(false);
						btnAnnuler.setBounds(450,280,120,25);
						btnSave.setVisible(true);

						try{
							String sql = "select*from note where numeleve = '"+valMod+"'";
							Statement stmt = con.createStatement();
							resultat = stmt.executeQuery(sql);

							while(resultat.next()){
								insertAs.setText(resultat.getString("anneescolaire"));
								insertNumMat.setText(resultat.getString("nummat"));
								insertNote.setText(resultat.getString("note"));
							}
						} catch(SQLException ex){
							JOptionPane.showMessageDialog(null,"Impossible de modifier!: "+ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
				pn.add(btnModification);


		//page de modification
		btnModNote = new JButton("Modification");
		btnModNote.setBounds(450,280,120,25);
		btnModNote.setForeground(new Color(232, 236, 239));
		btnModNote.setBackground(new Color(70, 70, 223));
		btnModNote.setFont(new Font("Open Sans",Font.PLAIN,13));
		btnModNote.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){

					las.setVisible(false);
					lnumMat.setVisible(false);
					lnote.setVisible(false);
					insertNumMat.setVisible(false);
					insertAs.setVisible(false);
					insertNote.setVisible(false);
					btnModification.setVisible(true);
					btnSupp.setVisible(false);
					btnAjout.setVisible(false);
					btnAnnuler.setVisible(true);
					btnModNote.setVisible(false);
					lbtitre.setText("Modification de Note");
			}
		});
				pn.add(btnModNote);

				//bouton de suppression d'un matiere

				btnSupp = new JButton("Supprimer");
				btnSupp.setBounds(280,320,290,25);
				btnSupp.setFont(new Font("Open Sans",Font.PLAIN,13));
				btnSupp.setForeground(new Color(232,236,239));
				btnSupp.setBackground(new Color(245, 12, 12));
				btnSupp.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e){
						
						try{
							String numEl = insertNumEl.getText();
							String supprEl = "delete from note where numeleve = '"+numEl+"'";
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
                        Note note = new Note(Valdetail);
                        note.setVisible(true);
					}
				});
				pn.add(btnSupp);
				
				//calcul de la moyenne
				try{
					con = DriverManager.getConnection(host, user, mdp);
					String mo = "select coef, note ,numeleve from matiere inner join note on matiere.nummat = note.nummat";
					Statement stat = con.createStatement();
					resultat = stat.executeQuery(mo);
					int i = 0; //valeur de note
					int j = 0;	//valeur de coefficient
					int coefficient = 0;
					int note;
					float moyenne = 0;
					while(resultat.next()){
							String c = resultat.getString("coef");
							coefficient = Integer.parseInt(c);
							String n = resultat.getString("note");
							note = Integer.parseInt(n);
							j = j + coefficient;
							moyenne = moyenne + (note*coefficient);
							
					}
					System.out.println(moyenne / j);
				}
				catch(SQLException ex){
					JOptionPane.showMessageDialog(null,"Impossible de supprimer!: "+ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
				}


				DefaultTableModel df2 = new DefaultTableModel();
				init2();
				df2.addColumn("Annee Scolaire");
				df2.addColumn("NumEleve");
				df2.addColumn("Design Matiere");
                df2.addColumn("Notes");
				
				tab2.setModel(df2);
				pn.add(scrl2);

				//affichage des notes
				try{
					con = DriverManager.getConnection(host, user, mdp);
					String sql2 = "select anneescolaire , numeleve, designmat, note, coef from matiere inner join note on matiere.nummat = note.nummat where numeleve in (select numeleve from eleve where numecole = '"+Valdetail+"')";
					Statement stat = con.createStatement();
					resultat = stat.executeQuery(sql2);
					int coefficient = 0;
					int note;
					float moyenne = 0;
					int i = 0; //valeur de note
					int j = 0;	//valeur de coefficient
					while(resultat.next()){

						String c = resultat.getString("coef");
						coefficient = Integer.parseInt(c);
						String n = resultat.getString("note");
						note = Integer.parseInt(n);
						j = j + coefficient;
						moyenne = moyenne + (note*coefficient);		
						df2.addRow(new Object[]{
						resultat.getString("anneescolaire"),
						resultat.getString("numeleve"),
						resultat.getString("designmat"),
                        resultat.getString("note")
				});
				m = moyenne / j;
				System.out.println(m);
					}
				}
				catch(SQLException ex){
					JOptionPane.showMessageDialog(null,"Impossible d'afficher les resultat note!" + ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
				}

				
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

				
				
	}

	private void init2(){
		tab2 = new JTable();
		tab2.setRowMargin(0);
		tab2.setRowHeight(25);
		tab2.setShowVerticalLines(false);
		tab2.setShowHorizontalLines(false);
		tab2.setFont(new Font("Open Sans",Font.PLAIN,13));
		scrl2 = new JScrollPane();
		scrl2.setViewportView(tab2);
		scrl2.setBounds(210,400,955,250);
	}

	public static void main(String[] args) {
        Ecole ec = new Ecole();
        ec.setVisible(true);
	}
}
