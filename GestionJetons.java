class Reserve {
    private int jetons;
    private int totalAjoutes;
    private int totalRetires;

    public Reserve() {
        jetons = 0;
        totalAjoutes = 0;
        totalRetires = 0;
    }

    public synchronized void ajouter() {
        jetons++;
        totalAjoutes++;
        System.out.println("JETON AJOUTÉ : " + jetons);
        notifyAll();
    }

    public synchronized void retirer() throws InterruptedException {
        while (jetons == 0) {
            System.out.println("RESERVE VIDE. Attente d'un jeton...");
            wait();
        }
        jetons--;
        totalRetires++;
        System.out.println("JETON RETIRÉ : " + jetons);
    }

    public synchronized int getQuantite() {
        return jetons;
    }

    public synchronized int getTotalAjoutes() {
        return totalAjoutes;
    }

    public synchronized int getTotalRetires() {
        return totalRetires;
    }
}


class Producteur extends Thread {
    private Reserve reserve;

    public Producteur(Reserve reserve) {
        this.reserve = reserve;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            reserve.ajouter();
            try { Thread.sleep(500); } catch (InterruptedException e) {}
        }
    }
}


class Consommateur extends Thread {
    private Reserve reserve;

    public Consommateur(Reserve reserve) {
        this.reserve = reserve;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                reserve.retirer();
                Thread.sleep(700);
            } catch (InterruptedException e) {}
        }
    }
}


public class GestionJetons {
    public static void main(String[] args) {
        Reserve reserve = new Reserve();

        Producteur producteur = new Producteur(reserve);
        Consommateur consommateur = new Consommateur(reserve);

        Thread superviseur = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println(
                        "[SUPERVISEUR] Etat réserve : " + reserve.getQuantite()
                        + " | Total ajoutés : " + reserve.getTotalAjoutes()
                        + " | Total retirés : " + reserve.getTotalRetires()
                    );
                } catch (InterruptedException e) {}
            }
        });

        producteur.start();
        consommateur.start();
        superviseur.start();
    }
}