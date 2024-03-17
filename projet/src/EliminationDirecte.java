  import java.util.ArrayList;

  /**
   * @author LIAMIDI Deen.
   * La classe EliminationDirecte représente une phase de tournoi à élimination directe.
   * Elle étend la classe abstraite Phase et implémente les méthodes nécessaires
   * pour exécuter cette phase spécifique du tournoi.
   */
  public class EliminationDirecte implements Phase {

    /** Liste des équipes participant à la phase d'élimination directe. */
    private ArrayList<Equipe> equipes;

    /** Nombre de sets par match dans la phase d'élimination directe. */
    private int nset;

    /** Statistiques de la phase d'élimination directe. */
    private EliminationDirecteStats stats;

    /** Statistiques de la phase d'élimination directe avec nset. */
    private EliminationDirecteStatsNset statsNset;

    /**
     * Constructeur de la classe EliminationDirecte.
     *
     * @param es   Liste des équipes participant à la phase d'élimination directe.
     * @param n Nombre de sets par match dans la phase d'élimination directe.
     */
    public EliminationDirecte(ArrayList<Equipe> es, int n) {
      this.equipes = es;
      this.nset = n;
      this.stats = new EliminationDirecteStats();
      if(nset > 1){
        this.statsNset = new EliminationDirecteStatsNset();
      }
    }

    /**
     * Exécute la phase d'élimination directe du tournoi.
     * Organise les matchs et détermine les équipes gagnantes jusqu'à ce qu'il ne reste qu'une équipe.
     *
     * @return Liste des équipes gagnantes de la phase d'élimination directe.
     */
    @Override
    public ArrayList<Equipe> run() {
      ArrayList<Match> ms;
      ArrayList<Equipe> gagnantes = new ArrayList<>(this.equipes);  // Copie de la liste initiale
        while (gagnantes.size() != 1) {
        ms = this.programation(gagnantes);
        ArrayList<Match> matchesJoues;
        ArrayList<MatchNSet> matchesJouesNset;
        if(nset == 1){
          matchesJoues = new ArrayList<>();
          for (Match m : ms) {
            m.jouer();
            matchesJoues.add(m);
            gagnantes.add(m.vainqueur);
        }
        stats.addRound(matchesJoues);
        }else{
          matchesJouesNset = new ArrayList<>();
          for (Match m : ms) {
            m.jouer();
            matchesJouesNset.add((MatchNSet)m);
            gagnantes.add(m.vainqueur);
        }
        statsNset.addRoundNset(matchesJouesNset);
      }
    }
      return gagnantes;
    }

    /**
     * Obtient les statistiques de la phase d'élimination directe.
     *
     * @return Statistiques de la phase d'élimination directe.
     */
    public EliminationDirecteStats getStats() {
      return stats;
    }
      /**
     * Obtient les statistiques de la phase d'élimination directe avec set.
     *
     * @return Statistiques de la phase d'élimination directe avec set.
     */
    public EliminationDirecteStatsNset getStatsNset(){
      return statsNset;
    }

    /**
     * Programme les matchs pour la phase d'élimination directe.
     * Crée des paires d'équipes pour chaque match en utilisant un processus de sélection aléatoire.
     *
     * @param es Liste des équipes participant à la phase d'élimination directe.
     * @return Liste des matchs programmés pour la phase d'élimination directe.
     */
    @Override
    public ArrayList<Match> programation(ArrayList<Equipe> es) {
      ArrayList<Match> ms = new ArrayList<>();
      Equipe e1, e2;
      Match m;
      int rand1, rand2;

      if (es.size() % 2 == 1 && es.size() != 1) {
        EquipeSpecial mS = new EquipeSpecial();
        es.add(mS);
      }

      while (es.size() != 0) {
        do {
          rand1 = (int) (Math.random() * (es.size()));
          rand2 = (int) (Math.random() * (es.size()));
        } while (rand1 == rand2);

        e1 = es.get(rand1);
        e2 = es.get(rand2);

        if (this.nset == 1) {
          m = new Match(e1, e2);
        } else {
          m = new MatchNSet(e1, e2, this.nset);
        }

        es.remove(e1);
        es.remove(e2);
        ms.add(m);
      }

      return ms;
    }
  }
