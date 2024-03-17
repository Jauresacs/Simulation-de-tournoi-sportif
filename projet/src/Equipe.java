    /**
     * @author PARLIERNE Erwan
     * Classe représentant une équipe dans un tournoi.
     */
    public class Equipe {
        private String name;
        private int performance;

        /**
         * Constructeur de la classe Equipe.
         *
         * @param name Nom de l'équipe.
         * @param performance Performance initiale de l'équipe.
         */
        public Equipe(String name, int performance) {
            this.name = name;
            this.performance = performance;
        }

        /**
         * Méthode permettant de modifier la performance d'une équipe.
         *
         * @param score Nouveau score de performance de l'équipe.
         */
        public void setPerformance(int score) {
            this.performance = score;
        }

        /**
         * Méthode permettant d'obtenir le nom de l'équipe.
         *
         * @return Nom de l'équipe.
         */
        public String getName() {
            return this.name;
        }

        /**
         * Méthode permettant d'obtenir la performance de l'équipe.
         *
         * @return Performance de l'équipe.
         */
        public int getPerformance() {
            return this.performance;
        }
    }
