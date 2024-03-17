    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

    /**
     * @author Tous les membres du groupe.
     * Cette classe représente l'interface graphique principale de la simulation de tournoi.
     */
    public class Interface extends JFrame implements ActionListener {

        private JPanel pan;
        private JLabel acceuil, texte, realisateursLabel;
        private JButton boutonCommencer;

        /**
         * Constructeur de la classe Interface.
         */
        public Interface() {
            // Configuration de la fenêtre principale
            this.setTitle("Simulation de tournoi");
            this.setSize(1100, 700);

            pan = new JPanel();
            pan.setBackground(Color.WHITE);

            // Police
            Font policeHuge = new Font("Fipps", Font.PLAIN, 20);
            Font police = new Font("Serif", Font.BOLD, 30);
            Font police_1 = new Font("Serif", Font.PLAIN, 25);

            // Création des composants de l'interface
            acceuil = new JLabel();
            acceuil.setFont(police);
            acceuil.setText("Bienvenue dans votre partie de simulation de tournoi");

            JPanel title = new JPanel();
            title.setLayout(new FlowLayout(FlowLayout.CENTER));
            title.add(acceuil);

            texte = new JLabel();
            texte.setFont(police_1);
            texte.setText("<html>" +
                    "<p>Bienvenue sur notre interface de simulation de tournoi sportif !</p>"+
                    "<p>Nous sommes ravis de vous accueillir pour cette passionnante aventure où vous aurez l'opportunité de créer et gérer votre propre tournoi sportif.</p>"+
                    "<p>Dans cette interface, vous pourrez configurer les paramètres de votre tournoi, ajouter des équipes, modifier les équipes ajoutées ou même en supprimer, définir les phases de la compétition et vivre l'excitation du sport à travers la simulation.</p>"+
                    "<p>Assurez-vous de prendre le temps de bien ajouter vos équipes participantes et que le meilleur gagne !</p>"+
                    "<p>Préparez-vous à plonger dans l'univers compétitif du sport et à vivre des moments palpitants. Que le tournoi commence !</p>"
                    + "</html>");

            realisateursLabel = new JLabel();
            realisateursLabel.setFont(police_1);
            realisateursLabel.setText("<html><b>Réalisateur :</b> ABDELKERIM Daoud Hassan, AZANDOSSESSI Jaurès, LIAMIDI Mohamed, PALIERNE Erwan.</html>");

            JPanel infosPanel = new JPanel();
            infosPanel.setLayout(new BoxLayout(infosPanel, BoxLayout.Y_AXIS));
            infosPanel.add(texte);
            infosPanel.add(realisateursLabel);

            boutonCommencer = new JButton("Commencer le tournoi");
            boutonCommencer.addActionListener(this);
            boutonCommencer.setFont(policeHuge);

            JPanel button = new JPanel(new FlowLayout(FlowLayout.CENTER));
            button.add(boutonCommencer);

            // Utilisation d'un gestionnaire de disposition BorderLayout pour le contenu de la fenêtre
            this.setLayout(new BorderLayout());

            // Ajout des composants à la fenêtre
            this.add(title, BorderLayout.NORTH);
            this.add(infosPanel, BorderLayout.CENTER);
            this.add(button, BorderLayout.SOUTH);

            // Ferme la fenetre dès que l'utilisateur clique sur la croix.
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Assure la visibilité de la fenêtre.
            this.setVisible(true);
            this.setLocationRelativeTo(null);
        }
        /**
         * Méthode invoquée lorsque le bouton commencer est cliqué.
         *
         * @param e L'événement d'action.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()== boutonCommencer){
                FenetreTournoi fen = new FenetreTournoi();
                this.setVisible(false);
                dispose();
            }
        }
    }
