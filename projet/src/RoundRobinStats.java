    import java.util.ArrayList;

    /**
     * @author PALIERNE Erwan.
     * La classe RoundRobinStats représente les statistiques de la phase à la ronde d'un tournoi.
     */
    public class RoundRobinStats extends Statistiques {

        private ArrayList<Integer> lesPoints;

        /**
         * Constructeur de la classe RoundRobinStats.
         */
        public RoundRobinStats() {
            super();
            this.lesPoints = new ArrayList<>();
        }

        /**
         * Récupère la liste des points.
         *
         * @return Liste des points.
         */
        public ArrayList<Integer> getList() {
            return this.lesPoints;
        }

        /**
         * Ajoute une liste de points aux statistiques.
         *
         * @param ls Liste des points à ajouter.
         */
        public void addList(ArrayList<Integer> ls) {
            this.lesPoints.addAll(ls);
        }
    }
