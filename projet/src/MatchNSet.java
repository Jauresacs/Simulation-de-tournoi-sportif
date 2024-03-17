    import java.util.ArrayList;

    /**
     * @author ABDELKERIM Daoud.
     * La classe MatchNSet étend la classe Match et représente un match avec un nombre spécifié de sets.
     */
    public class MatchNSet extends Match {
        private int nset;
        private int score1;
        private int score2;

        /**
         * Constructeur d'un match avec un nombre spécifié de sets.
         *
         * @param e1    Première équipe du match.
         * @param e2    Deuxième équipe du match.
         * @param nset  Nombre de sets dans le match.
         */
        public MatchNSet(Equipe e1, Equipe e2, int nset) {
            super(e1, e2);
            this.nset = nset;
            this.score1 = 0;
            this.score2 = 0;
        }

        /**
         * Méthode permettant de simuler un match avec un nombre spécifié de sets.
         * Le résultat du match détermine le vainqueur et le perdant en fonction du nombre de sets remportés.
         */
        @Override
        public void jouer() {
            ArrayList<Integer> tirage = new ArrayList<Integer>();
            for (int j = 0; j < this.equipe1.getPerformance(); j++) {
                tirage.add(1);
            }
            for (int j = 0; j < this.equipe2.getPerformance(); j++) {
                tirage.add(0);
            }

            for (int i = 0; i < this.nset; i++) {
                int rand = (int) (Math.random() * (this.equipe1.getPerformance() + this.equipe2.getPerformance()));
                if (tirage.get(rand) == 1) {
                    this.vainqueur = this.equipe1;
                    this.perdant = this.equipe2;
                } else {
                    this.vainqueur = this.equipe2;
                    this.perdant = this.equipe1;
                }

                if (this.vainqueur == this.equipe1) {
                    score1++;
                } else {
                    score2++;
                }

                if(score1 == nset -1 || score2 == nset-1){
                    break;
                }
            }

            if (this.score1 > this.score2) {
                this.vainqueur = this.equipe1;
            } else {
                this.vainqueur = this.equipe2;
            }
        }
        
        public int getScore1(){
            return score1;
        }

        public int getScore2(){
            return score2;
        }
    }
