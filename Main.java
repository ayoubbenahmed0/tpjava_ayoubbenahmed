
import java.util.ArrayList;

class Citoyen {
    String cin,nom; int age;
    static int nbreCitoyens = 0;
    void show(){
        System.out.println (cin+nom+String.valueOf(age));
    }

    public Citoyen(String cin, String nom, int age) {
        this.cin = cin;
        this.nom = nom;
        this.age = age;
        nbreCitoyens ++;
    }

    @Override
    public String toString() {
        return cin + '\t' + nom + '\t' + age ;
    }
}
class Etudiant extends Citoyen{
    float mg;

    public Etudiant(String cin, String nom, int age, float mg) {
        super(cin, nom, age);
        this.mg = mg;
    }

    @Override
    public String toString() {
        return "ETUDIANT : " + super.toString() + "\t" + mg ;
    }
}
class Prof extends Citoyen{
    float sal;

    public Prof(String cin, String nom, int age, float sal) {
        super(cin, nom, age);
        this.sal = sal;
    }
    @Override
    public String toString() {
        return "PROF : " + super.toString() + "\t" + sal ;
    }
}
class Registre{
    static ArrayList<Citoyen> list_citoyens = new ArrayList<Citoyen>();
    static public void add_citoyen(Citoyen c){
        list_citoyens.add(c);
    }
    static public float calculMoyMg(){
        int tot=0;int nb_etuds=0;
        for (Citoyen c : list_citoyens) {
            if (c instanceof Etudiant) {
                tot += ((Etudiant) c).mg;
                nb_etuds++;
            }
        }
        return tot/nb_etuds;
    }
    static public void lister(){
        System.out.println("LISTE DE TOUS LES CITOYENS");
        for (Citoyen c : list_citoyens)
            System.out.println(c.toString());
    }
    static public void listerE(){
        System.out.println("LISTE DE TOUS LES ETUDIANTS");
        for (Citoyen c : list_citoyens)
            if (c instanceof Etudiant)
                System.out.println(c.toString());
    }
    static public float calculMoyAge(){
        int tot=0;
        for (Citoyen c : list_citoyens) {
            tot += c.age;
        }
        return tot/list_citoyens.size();
    }
}
class C1 extends C2{

}
class C2 {

}
public class Main {
    public static void main(String[] args) {
        Registre.add_citoyen(new Etudiant("11111","A",19,(float)18.5));
        Registre.add_citoyen(new Etudiant("22222","B",20,(float)18.5));
        Registre.add_citoyen(new Etudiant("33333","C",21,(float)18.5));
        Registre.add_citoyen(new Prof("11111","D",35,(float)2500));
        Registre.add_citoyen(new Prof("11111","E",32,(float)2350));
        Registre.lister();
        Registre.listerE();
        System.out.println("Moy. des ages : "+Registre.calculMoyAge());
        System.out.println("Moy. des moy. gén. : "+Registre.calculMoyMg());
        ArrayList<C1> t = new ArrayList<C1>();
        for (C2 c: t)
            System.out.print(c.toString());

    }
}
