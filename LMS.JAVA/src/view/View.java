package view;
import Controller.DBController;
import controller.AdminController;
import controller.BookController;
import controller.MemberController;
import controller.MemberManagementPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Book;
import model.Member;
import model.MembershipCard;

public class View extends javax.swing.JFrame {

    private DBController dbController;
    private AdminController adc;
    private MemberController mdc;
    private BookController bdc;
    private MemberManagementPanel memberManagementPanel;

    public View() throws ParseException {
        try {
            initComponents();
            initializeControllers();
            setupInitialLayout();
            centerWindow();
        } catch (SQLException e) {
            handleInitializationError(e);
        }
    }

    private void initializeControllers() throws SQLException, ParseException {
        // Initialize database and controllers
        dbController = DBController.getInstance();
        fetchAndDisplayStudents();
        adc = new AdminController();
        bdc = new BookController();
        mdc = new MemberController(dbController); // Create MemberController instance
        memberManagementPanel = new MemberManagementPanel(dbController); // Create MemberManagementPanel instance
    }

    private void setupInitialLayout() {
        // Set initial visibility of panels
        Book_Manager.setVisible(false);
        Memeber_manager.setVisible(true);
        setResizable(true);
        Memeber_manager.add(memberManagementPanel, BorderLayout.CENTER);

    }

    private void fetchAndDisplayMembers() {
        try {
            if (dbController == null) {
                throw new SQLException("Database controller not initialized");
            }

            try (var connection = dbController.getConnection(); var pstmt = connection.prepareStatement(
                    "SELECT name, contact, address, course, expire_date FROM members"); var rs = pstmt.executeQuery()) {
                // Get the table model and clear existing rows
                var model = (javax.swing.table.DefaultTableModel) Member_table.getModel();
                model.setRowCount(0);

                // Set column headers
                String[] columnHeaders = {
                    "Name", "Contact", "Address", "Course", "Expiry Date"
                };
                model.setColumnIdentifiers(columnHeaders);

                // Add rows to table
                while (rs.next()) {
                    Object[] row = {
                        rs.getString("name"),
                        rs.getString("contact"),
                        rs.getString("address"),
                        rs.getString("course"),
                        rs.getString("expire_date")
                    };
                    model.addRow(row);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error fetching data: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
            // Log the error
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void centerWindow() {
        // Get screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 1356;
        int height = 659;

        // Calculate the position to center the window
        int x = (screenSize.width - width) / 2;
        int y = (screenSize.height - height) / 2;

        // Set window bounds (position and size)
        setBounds(x, y, width, height);
    }

    private void handleInitializationError(SQLException e) {
        JOptionPane.showMessageDialog(
                this,
                "Error initializing application: " + e.getMessage(),
                "Initialization Error",
                JOptionPane.ERROR_MESSAGE
        );
        // Log the error
        System.err.println("Initialization error: " + e.getMessage());
        e.printStackTrace();
        // Close the application since we couldn't initialize properly
        System.exit(1);
    }

    private void clearData() {
        //BOOK MANAGER
        book_title_txt.setText("");
        author_txt.setText("");
        publication_year_jDateChooser1.setDate(null);
        copies_book_title_txt1.setText("");

        if (language_field.getItemCount() > 0) {
            language_field.setSelectedIndex(0);
        }
        if (genre_item_combox.getItemCount() > 0) {
            genre_item_combox.setSelectedIndex(0);
        }
        if (format_jComboBox1.getItemCount() > 0) {
            format_jComboBox1.setSelectedIndex(0);
        }
        //MEMEBER MANAGER
//        id_txt1.setText("");
        name_txt1.setText("");
        contact_txt1.setText("");
        address_txt1.setText("");
        memCardNo_txt1.setSelectedIndex(0);
        expireDate_txt1.setDate(null);
    }

    private void fetchAndDisplayStudents() {
        try {
            if (dbController == null) {
                throw new SQLException("Database controller not initialized");
            }

            try (var connection = dbController.getConnection(); var pstmt = connection.prepareStatement(
                    "SELECT book_id, book_title, author, publication_year, "
                    + "language, genre, copies_available, format FROM books"); var rs = pstmt.executeQuery()) {

                // Get the table model and clear existing rows
                var model = (javax.swing.table.DefaultTableModel) Book_table.getModel();
                model.setRowCount(0);

                // Set column headers
                String[] columnHeaders = {
                    "ID", "Title", "Author", "Year", "Language",
                    "Genre", "Copies", "Format"
                };
                model.setColumnIdentifiers(columnHeaders);

                // Add rows to table
                while (rs.next()) {
                    Object[] row = {
                        rs.getInt("book_id"),
                        rs.getString("book_title"),
                        rs.getString("author"),
                        rs.getString("publication_year"),
                        rs.getString("language"),
                        rs.getString("genre"),
                        rs.getInt("copies_available"),
                        rs.getString("format")
                    };
                    model.addRow(row);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error fetching data: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
            // Log the error
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        Book_Manager = new javax.swing.JPanel();
        Book_panel = new javax.swing.JPanel();
        BookManger_lbl = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Members_btn1 = new javax.swing.JButton();
        Books_btn1 = new javax.swing.JButton();
        book_table_panel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Book_table = new javax.swing.JTable();
        book_tile_lbl = new javax.swing.JLabel();
        book_title_txt = new javax.swing.JTextField();
        boo_author_lbl = new javax.swing.JLabel();
        author_txt = new javax.swing.JTextField();
        publication_year_lbl = new javax.swing.JLabel();
        genre_item_combox = new javax.swing.JComboBox<>();
        language_field = new javax.swing.JComboBox<>();
        language_lbl = new javax.swing.JLabel();
        genre_lbl = new javax.swing.JLabel();
        format_jComboBox1 = new javax.swing.JComboBox<>();
        book_tile_lbl1 = new javax.swing.JLabel();
        book_tile_lbl2 = new javax.swing.JLabel();
        copies_book_title_txt1 = new javax.swing.JTextField();
        Add_Book_btn = new javax.swing.JButton();
        View_Book_btn = new javax.swing.JButton();
        Reset_book_btn1 = new javax.swing.JButton();
        delete_book_btn = new javax.swing.JButton();
        publication_year_jDateChooser1 = new de.wannawork.jcalendar.JCalendarComboBox();
        Memeber_manager = new javax.swing.JPanel();
        member_panel = new javax.swing.JPanel();
        Member_Mnger_lbl = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        name_txt1 = new javax.swing.JTextField();
        name_lbl1 = new javax.swing.JLabel();
        age_lbl1 = new javax.swing.JLabel();
        contact_txt1 = new javax.swing.JTextField();
        address_txt1 = new javax.swing.JTextField();
        address_lbl1 = new javax.swing.JLabel();
        memCardNo_txt1 = new javax.swing.JComboBox<>();
        id_lbl2 = new javax.swing.JLabel();
        id_lbl3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Member_table = new javax.swing.JTable();
        delete_member_btn = new javax.swing.JButton();
        reset_member_btn = new javax.swing.JButton();
        View_member_btn = new javax.swing.JButton();
        Add_member_btn = new javax.swing.JButton();
        expireDate_txt1 = new de.wannawork.jcalendar.JCalendarComboBox();
        Books_btn2 = new javax.swing.JButton();
        Members_btn2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(60, 61, 55));

        jPanel2.setBackground(new java.awt.Color(60, 61, 55));

        Book_Manager.setBackground(new java.awt.Color(60, 61, 55));

        Book_panel.setBackground(new java.awt.Color(60, 61, 55));

        BookManger_lbl.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        BookManger_lbl.setForeground(new java.awt.Color(23, 155, 174));
        BookManger_lbl.setText("Book");

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Manager");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 255, 255));
        jLabel5.setText("Library Management System");

        Members_btn1.setBackground(new java.awt.Color(23, 155, 174));
        Members_btn1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        Members_btn1.setForeground(new java.awt.Color(255, 255, 255));
        Members_btn1.setText("Books");
        Members_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Members_btn1ActionPerformed(evt);
            }
        });

        Books_btn1.setBackground(new java.awt.Color(23, 155, 174));
        Books_btn1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        Books_btn1.setForeground(new java.awt.Color(255, 255, 255));
        Books_btn1.setText("Members");
        Books_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Books_btn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Book_panelLayout = new javax.swing.GroupLayout(Book_panel);
        Book_panel.setLayout(Book_panelLayout);
        Book_panelLayout.setHorizontalGroup(
            Book_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Book_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Book_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Book_panelLayout.createSequentialGroup()
                        .addComponent(Members_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(166, 166, 166)
                        .addComponent(jLabel5))
                    .addComponent(Books_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 276, Short.MAX_VALUE)
                .addGroup(Book_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BookManger_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        Book_panelLayout.setVerticalGroup(
            Book_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Book_panelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(BookManger_lbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Book_panelLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(Book_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Members_btn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Books_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        book_table_panel.setBackground(new java.awt.Color(60, 61, 55));

        Book_table.setBackground(new java.awt.Color(60, 61, 55));
        Book_table.setForeground(new java.awt.Color(255, 255, 255));
        Book_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Book Title", "Author", "Publication year", "Genre", "Language", "Title 7", "Title 8"
            }
        ));
        Book_table.setSelectionBackground(new java.awt.Color(255, 247, 209));
        Book_table.setSelectionForeground(new java.awt.Color(51, 51, 51));
        Book_table.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                Book_tableAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane2.setViewportView(Book_table);

        book_tile_lbl.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        book_tile_lbl.setForeground(new java.awt.Color(255, 255, 255));
        book_tile_lbl.setText("Book Title");

        boo_author_lbl.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        boo_author_lbl.setForeground(new java.awt.Color(255, 255, 255));
        boo_author_lbl.setText("Author");

        publication_year_lbl.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        publication_year_lbl.setForeground(new java.awt.Color(255, 255, 255));
        publication_year_lbl.setText("Publication year");

        genre_item_combox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Adventure", "African Literature", "Anthology", "Art", "Autobiography", "Biography", "Business", "Children's", "Classic", "Comic Book", "Coming of Age", "Contemporary", "Cookbook", "Crime", "Drama", "Dystopian", "Education", "Epic Poetry", "Erotica", "Essay", "Fairy Tale", "Fantasy", "Feminist Literature", "Folklore", "Gothic", "Graphic Novel", "Health", "Historical Fiction", "History", "Horror", "Humor", "Inspirational", "Journal", "Literary Fiction", "LGBTQ+", "Magic Realism", "Manga", "Medical", "Memoir", "Metaphysical", "Military", "Multicultural", "Music", "Mystery", "Mythology", "New Adult", "Noir", "Paranormal", "Personal Development", "Philosophy", "Photography", "Play", "Poetry", "Political", "Psychology", "Religion", "Romance", "Satire", "Science", "Science Fiction", "Self-help", "Short Story", "Social Science", "Spirituality", "Sports", "Steampunk", "Superhero", "Suspense", "Technology", "Textbook", "Thriller", "Travel", "True Crime", "Urban Fantasy", "Utopian", "War", "Western", "Women's Fiction", "Young Adult" }));

        language_field.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "English", "Sinhala", "Mandarin Chinese", "Spanish", "Hindi", "Arabic", "Bengali", "Portuguese", "Russian", "Japanese", "Punjabi", "German", "Korean", "French", "Turkish", "Italian", "Thai", "Dutch", "Greek", "Swedish", "Polish", "Czech", "Hebrew", "Danish", "Finnish", " " }));

        language_lbl.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        language_lbl.setForeground(new java.awt.Color(255, 255, 255));
        language_lbl.setText("Language");

        genre_lbl.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        genre_lbl.setForeground(new java.awt.Color(255, 255, 255));
        genre_lbl.setText("Genre");

        format_jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PDF", "Printed Book", "Kindle", "Online reader", "News paper", "Leaflet", "Paper" }));

        book_tile_lbl1.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        book_tile_lbl1.setForeground(new java.awt.Color(255, 255, 255));
        book_tile_lbl1.setText("Format");

        book_tile_lbl2.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        book_tile_lbl2.setForeground(new java.awt.Color(255, 255, 255));
        book_tile_lbl2.setText("Copies Availabe");

        Add_Book_btn.setBackground(new java.awt.Color(23, 155, 174));
        Add_Book_btn.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        Add_Book_btn.setForeground(new java.awt.Color(255, 255, 255));
        Add_Book_btn.setText("Add");
        Add_Book_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_Book_btnActionPerformed(evt);
            }
        });

        View_Book_btn.setBackground(new java.awt.Color(23, 155, 174));
        View_Book_btn.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        View_Book_btn.setForeground(new java.awt.Color(255, 255, 255));
        View_Book_btn.setText("View");
        View_Book_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                View_Book_btnActionPerformed(evt);
            }
        });

        Reset_book_btn1.setBackground(new java.awt.Color(23, 155, 174));
        Reset_book_btn1.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        Reset_book_btn1.setForeground(new java.awt.Color(255, 255, 255));
        Reset_book_btn1.setText("Reset");
        Reset_book_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Reset_book_btn1ActionPerformed(evt);
            }
        });

        delete_book_btn.setBackground(new java.awt.Color(23, 155, 174));
        delete_book_btn.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        delete_book_btn.setForeground(new java.awt.Color(255, 255, 255));
        delete_book_btn.setText("Delete");
        delete_book_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_book_btnActionPerformed(evt);
            }
        });

        publication_year_jDateChooser1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                publication_year_jDateChooser1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout book_table_panelLayout = new javax.swing.GroupLayout(book_table_panel);
        book_table_panel.setLayout(book_table_panelLayout);
        book_table_panelLayout.setHorizontalGroup(
            book_table_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, book_table_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Add_Book_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(View_Book_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(Reset_book_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(delete_book_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
            .addGroup(book_table_panelLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(book_table_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boo_author_lbl)
                    .addComponent(book_tile_lbl)
                    .addComponent(publication_year_lbl)
                    .addComponent(genre_lbl)
                    .addComponent(language_lbl)
                    .addComponent(book_tile_lbl1)
                    .addComponent(book_tile_lbl2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(book_table_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(publication_year_jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(genre_item_combox, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(language_field, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(format_jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(copies_book_title_txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(book_title_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(author_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        book_table_panelLayout.setVerticalGroup(
            book_table_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, book_table_panelLayout.createSequentialGroup()
                .addGroup(book_table_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(book_table_panelLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(book_table_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(book_tile_lbl)
                            .addComponent(book_title_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(book_table_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(author_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boo_author_lbl))
                        .addGap(11, 11, 11)
                        .addGroup(book_table_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(publication_year_lbl)
                            .addComponent(publication_year_jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(book_table_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(genre_lbl)
                            .addComponent(genre_item_combox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(book_table_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(book_table_panelLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(language_lbl))
                            .addGroup(book_table_panelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(language_field, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(book_table_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(book_table_panelLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(book_tile_lbl1))
                            .addGroup(book_table_panelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(format_jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29)
                        .addGroup(book_table_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(book_tile_lbl2)
                            .addComponent(copies_book_title_txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(book_table_panelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(book_table_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delete_book_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Reset_book_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(View_Book_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Add_Book_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Book_ManagerLayout = new javax.swing.GroupLayout(Book_Manager);
        Book_Manager.setLayout(Book_ManagerLayout);
        Book_ManagerLayout.setHorizontalGroup(
            Book_ManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Book_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Book_ManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Book_ManagerLayout.createSequentialGroup()
                    .addComponent(book_table_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 14, Short.MAX_VALUE)))
        );
        Book_ManagerLayout.setVerticalGroup(
            Book_ManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Book_ManagerLayout.createSequentialGroup()
                .addComponent(Book_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(731, Short.MAX_VALUE))
            .addGroup(Book_ManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Book_ManagerLayout.createSequentialGroup()
                    .addGap(161, 161, 161)
                    .addComponent(book_table_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(207, Short.MAX_VALUE)))
        );

        Memeber_manager.setBackground(new java.awt.Color(60, 61, 55));

        member_panel.setBackground(new java.awt.Color(60, 61, 55));

        Member_Mnger_lbl.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        Member_Mnger_lbl.setForeground(new java.awt.Color(23, 155, 174));
        Member_Mnger_lbl.setText("Member");

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Manager");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 255, 255));
        jLabel6.setText("Library Management System");

        javax.swing.GroupLayout member_panelLayout = new javax.swing.GroupLayout(member_panel);
        member_panel.setLayout(member_panelLayout);
        member_panelLayout.setHorizontalGroup(
            member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, member_panelLayout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Member_Mnger_lbl)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        member_panelLayout.setVerticalGroup(
            member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(member_panelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(Member_Mnger_lbl, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(jLabel4))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, member_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        name_txt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_txt1ActionPerformed(evt);
            }
        });

        name_lbl1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        name_lbl1.setForeground(new java.awt.Color(255, 255, 255));
        name_lbl1.setText("Name");

        age_lbl1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        age_lbl1.setForeground(new java.awt.Color(255, 255, 255));
        age_lbl1.setText("Contact");

        contact_txt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contact_txt1ActionPerformed(evt);
            }
        });

        address_txt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                address_txt1ActionPerformed(evt);
            }
        });

        address_lbl1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        address_lbl1.setForeground(new java.awt.Color(255, 255, 255));
        address_lbl1.setText("Address");

        memCardNo_txt1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "High National Diploma Information Technology", "High National Diploma Accounting", "High National Diploma Business Studies", "High National Diploma Tourism & Hospitality", "High National Diploma English" }));
        memCardNo_txt1.setMinimumSize(new java.awt.Dimension(68, 26));
        memCardNo_txt1.setPreferredSize(new java.awt.Dimension(68, 26));
        memCardNo_txt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memCardNo_txt1ActionPerformed(evt);
            }
        });

        id_lbl2.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        id_lbl2.setForeground(new java.awt.Color(255, 255, 255));
        id_lbl2.setText("Course");

        id_lbl3.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        id_lbl3.setForeground(new java.awt.Color(255, 255, 255));
        id_lbl3.setText("Expire date");

        Member_table.setBackground(new java.awt.Color(60, 61, 55));
        Member_table.setForeground(new java.awt.Color(255, 255, 255));
        Member_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        Member_table.setSelectionForeground(new java.awt.Color(0, 0, 204));
        Member_table.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                Member_tableAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(Member_table);

        delete_member_btn.setBackground(new java.awt.Color(23, 155, 174));
        delete_member_btn.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        delete_member_btn.setForeground(new java.awt.Color(255, 255, 255));
        delete_member_btn.setText("Delete");
        delete_member_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_member_btnActionPerformed(evt);
            }
        });

        reset_member_btn.setBackground(new java.awt.Color(23, 155, 174));
        reset_member_btn.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        reset_member_btn.setForeground(new java.awt.Color(255, 255, 255));
        reset_member_btn.setText("Reset");
        reset_member_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_member_btnActionPerformed(evt);
            }
        });

        View_member_btn.setBackground(new java.awt.Color(23, 155, 174));
        View_member_btn.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        View_member_btn.setForeground(new java.awt.Color(255, 255, 255));
        View_member_btn.setText("View");
        View_member_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                View_member_btnActionPerformed(evt);
            }
        });

        Add_member_btn.setBackground(new java.awt.Color(23, 155, 174));
        Add_member_btn.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        Add_member_btn.setForeground(new java.awt.Color(255, 255, 255));
        Add_member_btn.setText("Add");
        Add_member_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_member_btnActionPerformed(evt);
            }
        });

        Books_btn2.setBackground(new java.awt.Color(23, 155, 174));
        Books_btn2.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        Books_btn2.setForeground(new java.awt.Color(255, 255, 255));
        Books_btn2.setText("Members");
        Books_btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Books_btn2ActionPerformed(evt);
            }
        });

        Members_btn2.setBackground(new java.awt.Color(23, 155, 174));
        Members_btn2.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        Members_btn2.setForeground(new java.awt.Color(255, 255, 255));
        Members_btn2.setText("Books");
        Members_btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Members_btn2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Memeber_managerLayout = new javax.swing.GroupLayout(Memeber_manager);
        Memeber_manager.setLayout(Memeber_managerLayout);
        Memeber_managerLayout.setHorizontalGroup(
            Memeber_managerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Memeber_managerLayout.createSequentialGroup()
                .addGroup(Memeber_managerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Memeber_managerLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(Memeber_managerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Books_btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Members_btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(member_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Memeber_managerLayout.createSequentialGroup()
                        .addContainerGap(55, Short.MAX_VALUE)
                        .addGroup(Memeber_managerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Memeber_managerLayout.createSequentialGroup()
                                .addGroup(Memeber_managerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(name_lbl1)
                                    .addComponent(age_lbl1)
                                    .addComponent(id_lbl2)
                                    .addComponent(address_lbl1)
                                    .addComponent(id_lbl3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(Memeber_managerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(expireDate_txt1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(name_txt1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(contact_txt1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(address_txt1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(memCardNo_txt1, 0, 192, Short.MAX_VALUE))
                                .addGap(29, 29, 29)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Memeber_managerLayout.createSequentialGroup()
                                .addComponent(Add_member_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(delete_member_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(123, 123, 123)
                                .addComponent(View_member_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)
                                .addComponent(reset_member_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(78, 78, 78)))))
                .addContainerGap())
        );
        Memeber_managerLayout.setVerticalGroup(
            Memeber_managerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Memeber_managerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Memeber_managerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(member_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Memeber_managerLayout.createSequentialGroup()
                        .addComponent(Members_btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Books_btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(Memeber_managerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Memeber_managerLayout.createSequentialGroup()
                        .addGroup(Memeber_managerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(name_txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name_lbl1))
                        .addGap(18, 18, 18)
                        .addGroup(Memeber_managerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(contact_txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(age_lbl1))
                        .addGap(18, 18, 18)
                        .addGroup(Memeber_managerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(address_lbl1)
                            .addComponent(address_txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(Memeber_managerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(memCardNo_txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_lbl2))
                        .addGap(27, 27, 27)
                        .addGroup(Memeber_managerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(id_lbl3)
                            .addComponent(expireDate_txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40)
                .addGroup(Memeber_managerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Add_member_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete_member_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(View_member_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reset_member_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(202, 202, 202))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Memeber_manager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(Book_Manager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Memeber_manager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(Book_Manager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Add_Book_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_Book_btnActionPerformed
        String title = book_title_txt.getText(); // Book title
        String authorName = author_txt.getText(); // Author name
        String authorEmail = "default@example.com"; // If you have an email field, use it, else set a default email
        java.util.Date date = publication_year_jDateChooser1.getDate();
        String publishedYear = "";
        if (date != null) {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy");
            publishedYear = sdf.format(date);
        }
        String language = (String) language_field.getSelectedItem(); // Assuming a text field for language
        String genre = (String) genre_item_combox.getSelectedItem(); // Genre from combo box
        String format = (String) format_jComboBox1.getSelectedItem(); // Format from combo box
        int copiesAvailable;
        try {
            copiesAvailable = Integer.parseInt(copies_book_title_txt1.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number for copies available.");
            return; // Exit the method if invalid input is entered
        }
        try {
            // Create Book object
            Book book = new Book(authorName, authorEmail, title, publishedYear, language, genre, copiesAvailable, format);

            // Add book to the database using BookController
            BookController bookController = new BookController();
            bookController.add(book);

            // Confirmation message
            JOptionPane.showMessageDialog(null, "Book added successfully!");

            // Clear input fields
            clearData();
            if (language_field.getItemCount() > 0) {
                language_field.setSelectedIndex(0);
            }
            if (genre_item_combox.getItemCount() > 0) {
                genre_item_combox.setSelectedIndex(0);
            }
            if (format_jComboBox1.getItemCount() > 0) {
                format_jComboBox1.setSelectedIndex(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding book: " + e.getMessage());
        }
    }//GEN-LAST:event_Add_Book_btnActionPerformed

    private void View_Book_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_View_Book_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_View_Book_btnActionPerformed

    private void Reset_book_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Reset_book_btn1ActionPerformed
        book_title_txt.setText("");
        author_txt.setText("");
    }//GEN-LAST:event_Reset_book_btn1ActionPerformed

    private void delete_book_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_book_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_delete_book_btnActionPerformed

    private void address_txt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_address_txt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_address_txt1ActionPerformed

    private void contact_txt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contact_txt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contact_txt1ActionPerformed

    private void name_txt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_txt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_name_txt1ActionPerformed

    private void Add_member_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_member_btnActionPerformed
        String name = name_txt1.getText();
        String contact = contact_txt1.getText();
        String address = address_txt1.getText();
        String memberNo = memCardNo_txt1.getSelectedItem().toString();
        Date expireDate = expireDate_txt1.getDate();
        try {
            MembershipCard memberCard = new MembershipCard(memberNo, expireDate);
//            Member member1 = new Member(name, contact, address, memberCard);
            adc.addMember(new Member(name, contact, address, memberCard));

        } catch (NumberFormatException e) {
            System.out.println("Invalid member number. Please enter a numeric value.");
        }

        clearData();
    }//GEN-LAST:event_Add_member_btnActionPerformed

    private void View_member_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_View_member_btnActionPerformed
        try {
            adc.viewMemberDetails();
        } catch (ParseException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_View_member_btnActionPerformed

    private void reset_member_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_member_btnActionPerformed
        book_title_txt.setText("");
        author_txt.setText("");
    }//GEN-LAST:event_reset_member_btnActionPerformed

    private void delete_member_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_member_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_delete_member_btnActionPerformed

    private void memCardNo_txt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memCardNo_txt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_memCardNo_txt1ActionPerformed

    private void Book_tableAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_Book_tableAncestorAdded
        fetchAndDisplayStudents();
    }//GEN-LAST:event_Book_tableAncestorAdded

    private void Member_tableAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_Member_tableAncestorAdded
        fetchAndDisplayMembers();

    }//GEN-LAST:event_Member_tableAncestorAdded

    private void Members_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Members_btn1ActionPerformed
        if (Memeber_manager.isVisible()) {
            Memeber_manager.setVisible(false);
            Book_Manager.setVisible(true);
        }
    }//GEN-LAST:event_Members_btn1ActionPerformed

    private void Books_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Books_btn1ActionPerformed
         if (Book_Manager.isVisible()) {
            Book_Manager.setVisible(false);
            Memeber_manager.setVisible(true);
        }
    }//GEN-LAST:event_Books_btn1ActionPerformed

    private void Books_btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Books_btn2ActionPerformed
        if (Book_Manager.isVisible()) {
            Book_Manager.setVisible(false);
            Memeber_manager.setVisible(true);
        }
    }//GEN-LAST:event_Books_btn2ActionPerformed

    private void Members_btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Members_btn2ActionPerformed
        if (Memeber_manager.isVisible()) {
            Memeber_manager.setVisible(false);
            Book_Manager.setVisible(true);
        }
    }//GEN-LAST:event_Members_btn2ActionPerformed

    private void publication_year_jDateChooser1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_publication_year_jDateChooser1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_publication_year_jDateChooser1AncestorAdded

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                View view = null;
                try {
                    view = new View();
                } catch (ParseException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
                view.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add_Book_btn;
    private javax.swing.JButton Add_member_btn;
    private javax.swing.JLabel BookManger_lbl;
    private javax.swing.JPanel Book_Manager;
    private javax.swing.JPanel Book_panel;
    private javax.swing.JTable Book_table;
    private javax.swing.JButton Books_btn1;
    private javax.swing.JButton Books_btn2;
    private javax.swing.JLabel Member_Mnger_lbl;
    private javax.swing.JTable Member_table;
    private javax.swing.JButton Members_btn1;
    private javax.swing.JButton Members_btn2;
    private javax.swing.JPanel Memeber_manager;
    private javax.swing.JButton Reset_book_btn1;
    private javax.swing.JButton View_Book_btn;
    private javax.swing.JButton View_member_btn;
    private javax.swing.JLabel address_lbl1;
    private javax.swing.JTextField address_txt1;
    private javax.swing.JLabel age_lbl1;
    private javax.swing.JTextField author_txt;
    private javax.swing.JLabel boo_author_lbl;
    private javax.swing.JPanel book_table_panel;
    private javax.swing.JLabel book_tile_lbl;
    private javax.swing.JLabel book_tile_lbl1;
    private javax.swing.JLabel book_tile_lbl2;
    private javax.swing.JTextField book_title_txt;
    private javax.swing.JTextField contact_txt1;
    private javax.swing.JTextField copies_book_title_txt1;
    private javax.swing.JButton delete_book_btn;
    private javax.swing.JButton delete_member_btn;
    private de.wannawork.jcalendar.JCalendarComboBox expireDate_txt1;
    private javax.swing.JComboBox<String> format_jComboBox1;
    private javax.swing.JComboBox<String> genre_item_combox;
    private javax.swing.JLabel genre_lbl;
    private javax.swing.JLabel id_lbl2;
    private javax.swing.JLabel id_lbl3;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> language_field;
    private javax.swing.JLabel language_lbl;
    private javax.swing.JComboBox<String> memCardNo_txt1;
    private javax.swing.JPanel member_panel;
    private javax.swing.JLabel name_lbl1;
    private javax.swing.JTextField name_txt1;
    private de.wannawork.jcalendar.JCalendarComboBox publication_year_jDateChooser1;
    private javax.swing.JLabel publication_year_lbl;
    private javax.swing.JButton reset_member_btn;
    // End of variables declaration//GEN-END:variables

}
