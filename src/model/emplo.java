package model;

public class emplo {

	private int id;
    private String nom;
    private String prenom;
    private String age;
    private String tel;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public emplo(int id, String nom, String prenom, String age, String tel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.tel = tel;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
   

   
}
