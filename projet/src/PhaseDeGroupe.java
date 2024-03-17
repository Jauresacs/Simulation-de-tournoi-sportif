    import java.util.ArrayList;
    import java.util.Collections;

    /**
     * @author LIAMIDI Deen et ABDELKERIM Daoud.
     * La classe PhaseDeGroupe représente la phase de groupe d'un tournoi.
     */
    public class PhaseDeGroupe implements Phase {

        private ArrayList<Equipe> lesequipes;
        private ArrayList<ArrayList<Integer>> pointsGroupes;
        private ArrayList<ArrayList<Equipe>> lesgroupes;
        private PhaseDeGroupeStats stats;

        /**
         * Constructeur de la phase de groupe.
         *
         * @param es Liste des équipes participant à la phase.
         */
        public PhaseDeGroupe(ArrayList<Equipe> es) {
            this.lesequipes = es;
            this.pointsGroupes = new ArrayList<>();
            this.lesgroupes = new ArrayList<>();
            this.stats = new PhaseDeGroupeStats();
        }

        /**
         * Répartit les équipes en groupes de quatre.
         *
         * @param es Liste des équipes à répartir.
         * @return Liste des groupes formés.
         */
        public ArrayList<ArrayList<Equipe>> formation(ArrayList<Equipe> es) {
            Collections.shuffle(es);
            int nbgroupes;
            if (es.size() % 4 == 0) {
                nbgroupes = es.size() / 4;
            } else {
                nbgroupes = es.size() / 4 + 1;
            }

            for (int i = 0; i < nbgroupes; i++) {
                lesgroupes.add(new ArrayList<>());
            }

            for (int j = 0; j < lesgroupes.size(); j++) {
                for (int k = 0; k < 4 && !es.isEmpty(); k++) {
                    lesgroupes.get(j).add(es.get(0));
                    es.remove(0);
                }
            }
            return lesgroupes;
        }

        @Override
        public ArrayList<Match> programation(ArrayList<Equipe> es) {
            ArrayList<Match> ms = new ArrayList<>();
            Equipe e1, e2;
            for (int i = 0; i < es.size() - 1; i++) {
                for (int j = i + 1; j < es.size(); j++) {
                    e1 = es.get(i);
                    e2 = es.get(j);
                    ms.add(new Match(e1, e2));
                }
            }
            return ms;
        }

        @Override
        public ArrayList<Equipe> run() {
            ArrayList<Equipe> gagnants = new ArrayList<>();
            ArrayList<ArrayList<Equipe>> groupes = formation(lesequipes);
            ArrayList<Match> m = new ArrayList<>();

            for (ArrayList<Equipe> groupe : groupes) {
                ArrayList<Integer> points = new ArrayList<>();
                ArrayList<Equipe> vainqueurs = new ArrayList<>();
                ArrayList<Match> matches = programation(groupe);

                // Jouer les matches et récupérer les vainqueurs
                for (Match match : matches) {
                    match.jouer();
                    m.add(match);
                    vainqueurs.add(match.getVainqueur());
                }
                stats.addRound(m);

                // Calculer les points
                for (int i = 0; i < groupe.size(); i++) {
                    int pointActuel = 0;
                    for (Equipe vainqueur : vainqueurs) {
                        if (vainqueur == groupe.get(i)) {
                            pointActuel++;
                        }
                    }
                    points.add(pointActuel);
                }
                // Tri des équipes dans chaque groupe en fonction des points décroissants
                for (int i = 0; i < groupe.size(); i++) {
                    for (int j = i + 1; j < groupe.size(); j++) {
                        if (points.get(j) > points.get(i)) {
                            Collections.swap(groupe, i, j);
                            Collections.swap(points, i, j);
                        }
                    }
                }
                if (groupe.size() > 1) {
                    gagnants.add(groupe.get(0));
                    gagnants.add(groupe.get(1));
                } else {
                    gagnants.add(groupe.get(0));
                }
                pointsGroupes.add(points);
            }
            stats.addGroupes(lesgroupes);
            stats.addPoints(pointsGroupes);
            return gagnants;
        }

        /**
         * Récupère les statistiques de la phase de groupe.
         *
         * @return Statistiques de la phase de groupe.
         */
        public PhaseDeGroupeStats getStatsGroupes() {
            return this.stats;
        }
    }
