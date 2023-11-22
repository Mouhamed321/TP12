package bowling;

public class PartieMultiJoueurs implements IPartieMultiJoueurs{
	private String[] nomsDesJoueurs;
	private int tourActuel;
	private int bouleActuelle;
	private int joueurActuel;
	private int[][] scores;


	@Override
	public String demarreNouvellePartie(String[] nomsDesJoueurs) throws IllegalArgumentException {
		if (nomsDesJoueurs == null || nomsDesJoueurs.length == 0) {
			throw new IllegalArgumentException("il faut au moins un joueur");
		}

		this.nomsDesJoueurs = nomsDesJoueurs;
		this.tourActuel = 1;
		this.bouleActuelle = 1;
		this.joueurActuel = 0;
		this.scores = new int[nomsDesJoueurs.length][3];

		return prochainTir();
	}

	@Override
	public String enregistreLancer(int nombreDeQuillesAbattues) throws IllegalStateException {
		if (nomsDesJoueurs == null) {
			throw new IllegalStateException("La partie n'est pas démarrée.");
		}

		joueurActuel++;
		if (joueurActuel >= nomsDesJoueurs.length) {
			joueurActuel = 0;
			bouleActuelle++;
			if (bouleActuelle > 3) {
				bouleActuelle = 1;
				tourActuel++;
			}
		}

		return prochainTir();
	}

	@Override
	public int scorePour(String nomDuJoueur) throws IllegalArgumentException {
		int joueurIndex = getJoueurIndex(nomDuJoueur);
		if (joueurIndex == -1) {
			throw new IllegalArgumentException("Le joueur " + nomDuJoueur + " ne joue pas dans cette partie.");
		}

		int scoreTotal = 0;
		for (int tour = 0; tour < scores[joueurIndex].length; tour++) {
			scoreTotal += scores[joueurIndex][tour];
		}

		return scoreTotal;
	}

	private String prochainTir() {
		if (tourActuel > 10) {
			return "Partie terminée";
		}

		String prochainJoueur = nomsDesJoueurs[joueurActuel];
		return "Prochain tir : joueur " + prochainJoueur + ", tour n° " + tourActuel + ", boule n° " + bouleActuelle;
	}

	private int getJoueurIndex(String nomDuJoueur) {
		for (int i = 0; i < nomsDesJoueurs.length; i++) {
			if (nomsDesJoueurs[i].equals(nomDuJoueur)) {
				return i;
			}
		}
		return -1;
	}
}