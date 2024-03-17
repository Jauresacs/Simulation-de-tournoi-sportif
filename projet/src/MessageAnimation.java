    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

    /**
     * @author AZANDOSSESSI Jaurès et LIAMIDI Deen.
     * La classe MessageAnimation représente une fenêtre d'animation affichant un message avec une image pendant une durée spécifiée.
     */
    public class MessageAnimation extends JFrame {

        private JLabel messageLabel;
        private JProgressBar progressBar;

        /**
         * Constructeur de la fenêtre d'animation avec un message spécifié et une image.
         */
        public MessageAnimation() {
            setTitle("Votre tournoi est en cours......................................");
            setSize(300, 150);
            setLocationRelativeTo(null);

            // Chargement de l'image depuis un fichier (ajustez le chemin d'accès en conséquence)
            ImageIcon icon = new ImageIcon("C:\\Users\\jaure\\Documents\\essaie\\european-championship-1431236_640.jpg");
            JLabel imageLabel = new JLabel(icon);

            // Message label avec une icône
            messageLabel = new JLabel("<html><div style='text-align: center;'>Votre tournoi est en cours. Appuyez sur le bouton 'Afficher les résultats' pour vivre votre tournoi.</div></html>");
            messageLabel.setForeground(Color.WHITE);

            // Barre de progression indéterminée
            progressBar = new JProgressBar();
            progressBar.setIndeterminate(true);

            // Panel contenant le message, l'image et la barre de progression
            JPanel panel = new JPanel();
            panel.setLayout(new OverlayLayout(panel));
            panel.add(imageLabel);
            panel.add(messageLabel);
            panel.add(progressBar);

            add(panel, BorderLayout.CENTER);

            // Création d'un timer pour fermer automatiquement la fenêtre après 5 secondes
            Timer timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Fermer la fenêtre après 5 secondes (5000 millisecondes)
                    dispose();
                }
            });

            this.setLocationRelativeTo(null);
            timer.setRepeats(false);
            timer.start();

            setVisible(true);
        }
    }
