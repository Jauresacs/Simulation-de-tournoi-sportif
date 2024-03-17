    import javax.swing.table.DefaultTableModel;

    /**
     * @author Tous le groupe.
     * La classe NonEditableTableModel étend DefaultTableModel et permet de rendre toutes les cellules du tableau non éditables.
     */
    public class NonEditableTableModel extends DefaultTableModel {

        /**
         * Override de la méthode isCellEditable pour rendre toutes les cellules non éditables.
         *
         * @param row L'index de la ligne.
         * @param column L'index de la colonne.
         * @return false pour indiquer que la cellule n'est pas éditable.
         */
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
