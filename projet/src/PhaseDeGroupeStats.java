    import java.util.ArrayList;

    /**
     * @author ABDELKERIM Daoud.
     * La classe PhaseDeGroupeStats étend la classe abstraite Statistiques et représente les statistiques
     * spécifiques à la phase de groupe d'un tournoi.
     */
    public class PhaseDeGroupeStats extends Statistiques {

        private ArrayList<ArrayList<Equipe>> lesgroupes;
        private ArrayList<ArrayList<Integer>> lespoints;

        /**
         * Constructeur de la classe PhaseDeGroupeStats.
         * Initialise les listes des groupes et des points.
         */
        public PhaseDeGroupeStats() {
            super();
            this.lesgroupes = new ArrayList<>();
            this.lespoints = new ArrayList<>();
        }

        /**
         * Ajoute la liste des groupes à l'ensemble des statistiques.
         *
         * @param ls Liste des groupes à ajouter.
         */
        public void addGroupes(ArrayList<ArrayList<Equipe>> ls) {
            this.lesgroupes.addAll(ls);
        }

        /**
         * Ajoute la liste des points à l'ensemble des statistiques.
         *
         * @param lp Liste des points à ajouter.
         */
        public void addPoints(ArrayList<ArrayList<Integer>> lp) {
            this.lespoints.addAll(lp);
        }

        /**
         * Récupère la liste des groupes.
         *
         * @return Liste des groupes.
         */
        public ArrayList<ArrayList<Equipe>> getGroupes() {
            return this.lesgroupes;
        }

        /**
         * Récupère la liste des points.
         *
         * @return Liste des points.
         */
        public ArrayList<ArrayList<Integer>> getPoints() {
            return this.lespoints;
        }
    }
