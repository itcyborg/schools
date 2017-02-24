
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by isaac on 2/18/2017.
 */
public class main extends Application {
    Connection conn = new getDBConnector().getC();

    public static void main(String[] args) {
        launch();
    }

    /**
     * TODO     -create a fees entry form
     * TODO     -create student performance report, add printing support
     * TODO     -create a logo anchor and select option
     */

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     * <p>
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        new createDB();
        new createDB().createTables();
        new getDBConnector();
        primaryStage.setFullScreen(true);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setMinWidth(920);
        //Sidebar
        VBox menu = new VBox();
        menu.getStyleClass().add("custom-sidebar-menu");
        Button btn = new Button("Home");
        btn.getStyleClass().add("custom-menu-button");
        btn.getStyleClass().add("home");
        Button students = new Button("Students");
        students.getStyleClass().add("custom-menu-button");
        Button reports = new Button("Reports");
        reports.getStyleClass().add("custom-menu-button");
        Label space = new Label();
        space.getStyleClass().add("custom-menu-button-space");
        Button exit = new Button("Exit");
        exit.getStyleClass().add("custom-menu-button-exit");
        exit.setOnAction(e -> {
            System.exit(0);
        });
        menu.getChildren().addAll(btn, students, reports, space, exit);

        //topBar
        HBox hBox = new HBox();
        hBox.getStyleClass().add("top-bar");
        Label title = new Label("SCHOOL MANAGEMENT SYSTEM");
        title.getStyleClass().add("title");
        title.setAlignment(Pos.CENTER);
        Button logout = new Button("Log Out");
        logout.getStyleClass().add("logout");
        hBox.getChildren().add(title);
        hBox.setPrefWidth(Double.MAX_VALUE);
        btn.getStyleClass().add("active");

        logout.setOnAction(e -> {
            logoutDialog().show();
        });

        //content grid
        GridPane content = new GridPane();
        content.setHgap(25);
        content.setVgap(20);
        content.add(timeCard(), 0, 0);
        content.add(lastVisit(), 1, 0);
        content.getStyleClass().add("grid");
        content.add(studentsCard(), 2, 0);

        //reports grid
        GridPane reportsV = new GridPane();
        reportsV.setHgap(25);
        reportsV.setVgap(20);
        reportsV.getStyleClass().add("grid");
        reportsV.getChildren().add(reports());

        //student grid
        GridPane student = new GridPane();
        student.setHgap(25);
        student.setVgap(20);
        student.getStyleClass().add("grid");

        Button addNew = new Button("Add Student");
        Button viewExisting = new Button("View Students");
        Label search = new Label("Search");
        TextField searchField = new TextField();
        searchField.setPromptText("Enter student's ADM/RegNo");

        //add styles
        addNew.getStyleClass().add("st-nav-button");
        viewExisting.getStyleClass().add("st-nav-button");
        search.setPadding(new Insets(5, 5, 5, 200));
        search.getStyleClass().add("search-label");
        searchField.getStyleClass().add("search-field");


        //internal frame
        BorderPane studentPane = new BorderPane();

        //internal top Bar
        HBox intopBar = new HBox(10);
        intopBar.getChildren().addAll(addNew, viewExisting, search, searchField);

        //internal content
        HBox intContent = new HBox(10);
        addNew.setOnAction(e -> {
            addNew.setText("Loading...");
            intContent.getChildren().clear();
            intContent.getChildren().add(addStudents());
            addNew.setText("Add Student");
        });
        viewExisting.setOnAction(e -> {
            intContent.getChildren().clear();
            intContent.getChildren().add(viewStudent(""));
        });

        //search cmd
        searchField.setOnKeyReleased(e -> {
            String raw = searchField.getText().trim();
            if (raw.length() > 0) {
                intContent.getChildren().clear();
                intContent.getChildren().addAll(viewStudent(raw));
            } else {
                intContent.getChildren().clear();
            }
        });

        student.add(intopBar, 1, 0, 1, 1);
        student.add(intContent, 1, 1, 1, 1);

        //main grid
        BorderPane layout = new BorderPane();
        layout.setTop(hBox);
        layout.setLeft(menu);
        layout.setCenter(content);

        //button actions
        students.setOnAction(e -> {
            students.getStyleClass().add("active");
            btn.getStyleClass().remove("active");
            reports.getStyleClass().remove("active");
            layout.getChildren().remove(content);
            layout.getChildren().remove(reportsV);
            layout.setCenter(student);
        });

        btn.setOnAction(e -> {
            btn.getStyleClass().add("active");
            students.getStyleClass().remove("active");
            reports.getStyleClass().remove("active");
            layout.getChildren().remove(student);
            layout.getChildren().remove(reportsV);
            layout.setCenter(content);
        });

        reports.setOnAction(e -> {
            reports.getStyleClass().add("active");
            btn.getStyleClass().remove("active");
            students.getStyleClass().remove("active");
            layout.getChildren().remove(student);
            layout.getChildren().remove(content);
            layout.setCenter(reportsV);
        });

        //set the scene
        Scene scene = new Scene(layout, 800, 600);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @return
     */
    private StackPane timeCard() {
        StackPane stackPane = new StackPane();
        stackPane.getStyleClass().add("dashboard-rectangle");
        VBox vBox = new VBox();
        stackPane.getChildren().add(vBox);
        Label time = new Label("23-2-2017");
        Label timeLabel = new Label("TIME");
        time.getStyleClass().add("dash-card-text-label");
        time.setAlignment(Pos.CENTER);
        timeLabel.getStyleClass().add("dash-card-head-label");
        timeLabel.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(time, timeLabel);
        return stackPane;
    }

    /**
     * @return
     */
    private StackPane lastVisit() {
        StackPane stackPane = new StackPane();
        stackPane.getStyleClass().add("dashboard-rectangle");
        VBox vBox = new VBox();
        stackPane.getChildren().add(vBox);
        Label time = new Label("YESTERDAY");
        Label timeLabel = new Label("LAST VISIT");
        time.getStyleClass().add("dash-card-text-label");
        time.setAlignment(Pos.CENTER);
        timeLabel.getStyleClass().add("dash-card-head-label");
        timeLabel.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(time, timeLabel);
        return stackPane;
    }

    /**
     * @return
     */
    private StackPane studentsCard() {
        StackPane stackPane = new StackPane();
        stackPane.getStyleClass().add("dashboard-rectangle");
        VBox vBox = new VBox();
        stackPane.getChildren().add(vBox);
        Label time = new Label("400");
        Label timeLabel = new Label("NO OF STUDENTS");
        time.getStyleClass().add("dash-card-text-label");
        time.setAlignment(Pos.CENTER);
        timeLabel.getStyleClass().add("dash-card-head-label");
        timeLabel.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(time, timeLabel);
        return stackPane;
    }

    /**
     * @return
     */
    private Stage logoutDialog() {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(new Group(new Text("Hello there")), 300, 200);
        stage.setScene(scene);
        return stage;
    }

    /**
     * @return
     */
    private StackPane addStudents() {
        StackPane stackPane = new StackPane();
        //image holder
        VBox imageContainer = new VBox(10);

        //details form
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        //textFields
        TextField adm = new TextField();
        TextField firstName = new TextField();
        TextField lastName = new TextField();
        TextField email = new TextField();
        adm.setPromptText("Admission Number");
        adm.getStyleClass().add("input");
        firstName.setPromptText("First Name");
        firstName.getStyleClass().add("input");
        lastName.setPromptText("Last Name");
        lastName.getStyleClass().add("input");
        email.setPromptText("Email");
        email.getStyleClass().add("input");
        Button btn = new Button("Save");
        btn.getStyleClass().add("st-control-button-lg");
        grid.add(adm, 0, 0, 1, 1);
        grid.add(email, 1, 0, 1, 1);
        grid.add(firstName, 0, 1, 1, 1);
        grid.add(lastName, 1, 1, 1, 1);
        grid.add(btn, 2, 3, 1, 1);

        //button actions
        btn.setOnAction(e -> {
            String admNo = adm.getText();
            String fname = firstName.getText();
            String lname = lastName.getText();
            String emailF = email.getText();
            String query = "INSERT INTO students(ADM,FName,LName,Email) VALUES ('" + admNo + "','" + fname + "','" + lname + "','" + emailF + "')";
            int id = new dbPutter(new getDBConnector().getC(), query).put();
            System.out.println(id);
        });

        stackPane.getChildren().add(grid);
        stackPane.getStyleClass().add("grid");
        return stackPane;
    }

    /**
     * create a view to display students
     *
     * @param searchq
     * @return
     */
    private StackPane viewStudent(String searchq) {
        StackPane stackPane = new StackPane();
        TableColumn<Students, String> adm = new TableColumn("ADM No");
        adm.setCellValueFactory(new PropertyValueFactory<>("adm"));
        TableColumn<Students, String> firstname = new TableColumn("First Name");
        firstname.setMinWidth(150);
        firstname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        TableColumn<Students, String> lastname = new TableColumn("Last Name");
        lastname.setMinWidth(150);
        lastname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        TableColumn<Students, String> email = new TableColumn("Email");
        email.setMinWidth(150);
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableView<Students> table = new TableView<>();
        table.setPrefWidth(600);
        table.getSelectionModel().setCellSelectionEnabled(false);
        table.getColumns().addAll(adm, firstname, lastname, email);
        table.setItems(getTableData(searchq, "students"));

        ObservableList selectedCells = table.getSelectionModel().getSelectedItems();
        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
                Students person = table.getSelectionModel().getSelectedItem();
                System.out.println(person.getAdm());
            }
        });

        stackPane.getChildren().add(table);
        stackPane.getStyleClass().add("content");
        return stackPane;
    }

    /**
     * create a view to show the profile and renders the picture
     *
     * @param filename
     * @return
     */
    private StackPane profile(String filename) {
        StackPane stackPane = new StackPane();
        ImageView imv = new ImageView();
        Image image = new Image("/main/ProfilePhoto.jpg");
        imv.setFitWidth(150);
        imv.setFitHeight(150);

        imv.setImage(image);
        stackPane.getChildren().add(imv);
        return stackPane;
    }

    /**
     * create the reports view
     *
     * @return
     */
    private BorderPane reports() {
        BorderPane stackPane = new BorderPane();
        //top bar
        HBox hBox = new HBox(10);
        Label searchLabel = new Label("Search");
        TextField searchField = new TextField();
        searchField.setPromptText("Search for Records");
        Button viewall = new Button("View All");
        Button viewCleared = new Button("View Cleared");
        Button viewUncleared = new Button("View with Balance");
        searchField.getStyleClass().add("search-field");
        searchLabel.getStyleClass().add("search-label");
        searchLabel.setPadding(new Insets(5, 5, 5, 50));
        viewall.getStyleClass().add("st-nav-button");
        viewCleared.getStyleClass().add("st-nav-button");
        viewUncleared.getStyleClass().add("st-nav-button-lg");
        hBox.getChildren().addAll(viewall, viewCleared, viewUncleared, searchLabel, searchField);

        //content bar

        stackPane.setTop(hBox);
        HBox container = new HBox(10);
        viewall.setOnAction(e -> {
            container.getChildren().clear();
            container.getChildren().add(viewFees("View All"));
        });

        searchField.setOnKeyReleased(e -> {
            String q = searchField.getText().trim();
            if (q.length() > 0) {
                container.getChildren().clear();
                container.getChildren().add(viewFees(q));
            } else {
                container.getChildren().clear();
            }
        });

        viewCleared.setOnAction(e -> {
            container.getChildren().clear();
            container.getChildren().add(viewFees("View Cleared"));
        });

        viewUncleared.setOnAction(e -> {
            container.getChildren().clear();
            container.getChildren().add(viewFees("View Uncleared"));
        });
        stackPane.setCenter(container);

        return stackPane;
    }

    /**
     * create the fees view for showing records
     *
     * @param query
     * @return
     */
    private StackPane viewFees(String query) {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(feesTable(query));
        stackPane.getStyleClass().add("content");
        return stackPane;
    }

    /**
     * @param searchq
     * @return
     */
    private StackPane search(String searchq) {
        StackPane stackPane = new StackPane();
        TableColumn<Students, String> adm = new TableColumn("ADM No");
        adm.setCellValueFactory(new PropertyValueFactory<>("adm"));
        TableColumn<Students, String> firstname = new TableColumn("First Name");
        firstname.setMinWidth(150);
        firstname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        TableColumn<Students, String> lastname = new TableColumn("Last Name");
        lastname.setMinWidth(150);
        lastname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        TableColumn<Students, String> email = new TableColumn("Email");
        email.setMinWidth(150);
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableView<Students> table = new TableView<>();
        table.setPrefWidth(600);
        table.getSelectionModel().setCellSelectionEnabled(false);
        table.getColumns().addAll(adm, firstname, lastname, email);
        table.setItems(getTableData(searchq, "students"));

        ObservableList selectedCells = table.getSelectionModel().getSelectedItems();
        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
                Students person = table.getSelectionModel().getSelectedItem();
                System.out.println(person.getAdm());
            }
        });

        stackPane.getChildren().add(table);
        return stackPane;
    }

    /**
     * generate fees table
     *
     * @param searchq search parameter to generate table
     * @return
     */
    private StackPane feesTable(String searchq) {
        StackPane stackPane = new StackPane();
        TableColumn<Fees, String> adm = new TableColumn("Name");
        adm.setPrefWidth(150);
        adm.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Fees, String> name = new TableColumn("ADM");
        name.setPrefWidth(150);
        name.setCellValueFactory(new PropertyValueFactory<>("admNo"));
        TableColumn<Fees, Double> amount = new TableColumn<>("Amount");
        amount.setPrefWidth(150);
        amount.setCellValueFactory(new PropertyValueFactory<>("feesAmount"));
        TableColumn<Fees, Double> feesPaid = new TableColumn<>("Fees Paid");
        feesPaid.setPrefWidth(150);
        feesPaid.setCellValueFactory(new PropertyValueFactory<>("feesPaid"));
        TableColumn<Fees, Double> balance = new TableColumn<>("Balance");
        balance.setPrefWidth(150);
        balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        TableColumn<Fees, Date> date = new TableColumn<>("Date");
        date.setPrefWidth(150);
        date.setCellValueFactory(new PropertyValueFactory<>("time"));


        TableView<Fees> table = new TableView<Fees>();
        table.getColumns().addAll(adm, name, amount, feesPaid, balance, date);
        table.setItems(getFeesData(""));

        stackPane.getChildren().add(table);
        return stackPane;
    }

    /**
     * Get student data fro table generation
     *
     * @param searchq
     * @param table
     * @return
     */
    private ObservableList<Students> getTableData(String searchq, String table) {
        ObservableList<Students> students = FXCollections.observableArrayList();
        String query = "";
        if (searchq.length() > 0) {
            query = "SELECT * FROM students WHERE ADM LIKE '%" + searchq + "%' OR FName LIKE '%" + searchq + "%' OR LName LIKE '%" + searchq + "%'";
        } else {
            query = "SELECT * FROM students";
        }
        ResultSet rs = new dbGetter(query, new getDBConnector().getC()).getRecords();
        try {
            while (rs.next()) {
                students.add(new Students(rs.getString("ADM"), rs.getString("FName"), rs.getString("LName"), rs.getString("Email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    /**
     * Get fees data for table generation
     *
     * @param searchq
     * @return
     */
    private ObservableList<Fees> getFeesData(String searchq) {
        ObservableList<Fees> fees = FXCollections.observableArrayList();
        String query = "";
        if (searchq.length() > 0) {
            query = "SELECT * FROM fees WHERE ADM LIKE '%" + searchq + "%'";
        } else {
            query = "SELECT * FROM fees";
        }
        ResultSet rs = new dbGetter(query, new getDBConnector().getC()).getRecords();
        try {
            while (rs.next()) {
                fees.add(new Fees("isaac", "kipkorir", rs.getString("ADM"), rs.getString("UPDATED"), rs.getInt("AMOUNT"), rs.getInt("PAID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fees;
    }
}
