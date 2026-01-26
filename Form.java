import java.util.Vector;

abstract class Forme {

    private String nom;
    private int couleur;
    static int nbObj;

    public Forme(String nom, int couleur) {
        this.nom = nom;
        this.couleur = couleur;
        nbObj++;
    }

    @Override
    public String toString() {
        return nom + '\t' + couleur;
    }

    abstract public double surface();

    static public double calculSumSurf(Vector<Forme> dessain) {
        double tot = 0;
        int nbrSurf = 0;

        for (Forme f : dessain) {
            tot += f.surface();
            nbrSurf++;
        }

        return tot / nbrSurf;
    }
}

class Cercle extends Forme {

    private float r;

    public Cercle(String nom, int couleur, float r) {
        super(nom, couleur);
        this.r = r;
    }

    public double surface() {
        return 3.14 * r * r;
    }

    public String toString() {
        return super.toString() + "  " + r;
    }
}

class Triangle extends Forme {

    private float b, h;

    public Triangle(String nom, int couleur, float b, float h) {
        super(nom, couleur);
        this.b = b;
        this.h = h;
    }

    public double surface() {
        return (b * h) / 2;
    }

    public String toString() {
        return super.toString() + "  " + b + "  " + h;
    }
}

class Rect extends Forme {

    private float L, l;

    public Rect(String nom, int couleur, float L, float l) {
        super(nom, couleur);
        this.L = L;
        this.l = l;
    }

    public double surface() {
        return L * l;
    }

    public String toString() {
        return super.toString() + "  " + L + "  " + l;
    }
}

public class Form {

    public static void main(String[] args) {

        Vector<Forme> dessain = new Vector<>();

        dessain.add(new Cercle("c1", 3, 5));
        dessain.add(new Triangle("t1", 3, 5, 6));
        dessain.add(new Rect("r1", 3, 5, 6));

        for (Forme f : dessain) {
            System.out.println(f.toString() + " " + f.surface());
        }

        System.out.println("la somme de surface est egal a  : "+Forme.calculSumSurf(dessain));
    }
}
