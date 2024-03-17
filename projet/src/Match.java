    import java.util.ArrayList;

    /**
     * @author AZANDOSSESSI Jaurès et LIAMIDI Deen.
     * La classe Match représente un match entre deux équipes.
     */
    public class Match {
        protected Equipe equipe1, equipe2, vainqueur, perdant;

        /**
         * Constructeur d'un match.
         *
         * @param e1 Première équipe du match.
         * @param e2 Deuxième équipe du match.
         */
        public Match(Equipe e1, Equipe e2) {
            this.equipe1 = e1;
            this.equipe2 = e2;
        }

        /**
         * Méthode permettant de simuler un match entre deux équipes.
         * Le résultat du match détermine le vainqueur et le perdant.
         */
        public void jouer() {
            // On essaie de simuler un tirage aléatoire
            ArrayList<Integer> tirage = new ArrayList<Integer>();
            for (int i = 0; i < this.equipe1.getPerformance(); i++) {
                tirage.add(1);
            }
            for (int i = 0; i < this.equipe2.getPerformance(); i++) {
                tirage.add(0);
            }

            int rand = (int) (Math.random() * (this.equipe1.getPerformance() + this.equipe2.getPerformance()));
            if (tirage.get(rand) == 1) {
                this.vainqueur = this.equipe1;
                this.perdant = this.equipe2;
            } else {
                this.vainqueur = this.equipe2;
                this.perdant = this.equipe1;
            }
        }

        /**
         * Récupère l'équipe vainqueur du match.
         *
         * @return L'équipe vainqueur.
         */
        public Equipe getVainqueur() {
            return this.vainqueur;
        }

        /**
         * Récupère l'équipe perdante du match.
         *
         * @return L'équipe perdante.
         */
        public Equipe getPerdant() {
            return this.perdant;
        }
    }
