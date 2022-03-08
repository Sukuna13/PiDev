package fitnessny.entities;

import java.sql.Date;

public class Utilisateur {
	
	private Integer id;
	private String nom;
	private String prenom;
	private String adresse;
	private String numTel;
	private String password;
	private String mailAddress;
	private Date dateNaissance ;
	private String whoami;
	private String blocRaison;
	private Date unbloc;
	
	public Utilisateur()
	{
		
	}
	
	
	
	
	

	public Utilisateur(Integer id, String nom, String prenom, 
			String mailAddress) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.mailAddress = mailAddress;
		
	}
	
	
	
	
	
	public Utilisateur(int id, String nom, String prenom, String adresse, String numTel, String password,
			String mailAddress, Date dateNaissance, String whoami, String raison, Date unbloc) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numTel = numTel;
		this.password = password;
		this.mailAddress = mailAddress;
		this.dateNaissance = dateNaissance;
		this.whoami = whoami;
		this.blocRaison=raison;
		this.unbloc=unbloc;
	}
	
	public Utilisateur( String nom, String prenom, String adresse, String numTel, String password,
			String mailAddress, Date dateNaissance, String whoami, String raison, Date unbloc) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numTel = numTel;
		this.password = password;
		this.mailAddress = mailAddress;
		this.dateNaissance = dateNaissance;
		this.whoami = whoami;
		this.blocRaison=raison;
		this.unbloc=unbloc;
	}




	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getNumTel() {
		return numTel;
	}
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getWhoami() {
		return whoami;
	}
	public void setWhoami(String whoami) {
		this.whoami = whoami;
	}






	public String getBlocRaison() {
		return blocRaison;
	}


	public void setBlocRaison(String blocRaison) {
		this.blocRaison = blocRaison;
	}


	public Date getUnbloc() {
		return unbloc;
	}


	public void setUnbloc(Date unbloc) {
		this.unbloc = unbloc;
	}



	public void setId(Integer id) {
		this.id = id;
	}






	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", numTel="
				+ numTel + ", password=" + password + ", mailAddress=" + mailAddress + ", dateNaissance="
				+ dateNaissance + ", whoami=" + whoami + ", blocRaison=" + blocRaison + ", unbloc=" + unbloc + "]";
	}

	
	
	

}
