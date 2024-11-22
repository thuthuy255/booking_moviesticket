/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_ticketmovie;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

/**
 *
 * @author ngthuthuy
 */
public class dashboardController implements Initializable {

    @FXML
    private Button Customers_clearBtn;

    @FXML
    private TableColumn<customerData, String> Customers_col_dateChecked;

    @FXML
    private TableColumn<customerData, String> Customers_col_movieTitle;

    @FXML
    private TableColumn<customerData, String> Customers_col_ticketNumber;

    @FXML
    private TableColumn<customerData, String> Customers_col_timeChecked;

    @FXML
    private TableColumn<customerData, String> Customers_col_totalPayment;

    @FXML
    private Label Customers_dateChecked;

    @FXML
    private Button Customers_deleteBtn;

    @FXML
    private AnchorPane Customers_form;

    @FXML
    private Label Customers_movietitle;

    @FXML
    private TextField Customers_search;

    @FXML
    private TableView<customerData> Customers_tableView;

    @FXML
    private Label Customers_ticketNumber;

    @FXML
    private Label Customers_timeChecked;

    @FXML
    private Label Customers_totalPayment;

    @FXML
    private Button addMovies_btn;

    @FXML
    private Button addMovies_clear;

    @FXML
    private TableColumn<moviesData, String> addMovies_col_duration;

    @FXML
    private TableColumn<moviesData, String> addMovies_col_genre;

    @FXML
    private TableColumn<moviesData, String> addMovies_col_movietitle;

    @FXML
    private TableColumn<moviesData, String> addMovies_col_publisheddate;

    @FXML
    private Button addMovies_delete;

    @FXML
    private TextField addMovies_duration;

    @FXML
    private AnchorPane addMovies_form;

    @FXML
    private TextField addMovies_genre;

    @FXML
    private ImageView addMovies_imageView;

    @FXML
    private Button addMovies_import;

    @FXML
    private Button addMovies_insert;

    @FXML
    private TextField addMovies_movietitle;

    @FXML
    private DatePicker addMovies_publishedDate;

    @FXML
    private TableView<moviesData> addMovies_tableView;

    @FXML
    private Button addMovies_update;

    @FXML
    private Button availableMovies_btn;

    @FXML
    private Button availableMovies_buyBtn;

    @FXML
    private Button availableMovies_cleartBtn;

    @FXML
    private TableColumn<moviesData, String> availableMovies_col_genre;

    @FXML
    private TableColumn<moviesData, String> availableMovies_col_movietitle;

    @FXML
    private TableColumn<moviesData, String> availableMovies_col_showdate;

    @FXML
    private TextField availableMovies_date;

    @FXML
    private AnchorPane availableMovies_form;

    @FXML
    private TextField availableMovies_genre;

    @FXML
    private ImageView availableMovies_imgView;

    @FXML
    private TextField availableMovies_movietitle;

    @FXML
    private Spinner<Integer> availableMovies_normalclass_Quanlity;

    @FXML
    private Label availableMovies_normalclass_price;

    @FXML
    private Button availableMovies_receiptBtn;

    @FXML
    private Button availableMovies_selectmoviebtn;

    @FXML
    private Spinner<Integer> availableMovies_specialclass_Quanlity;

    @FXML
    private Label availableMovies_specialclass_price;

    @FXML
    private TableView<moviesData> availableMovies_tableView;

    @FXML
    private Label availableMovies_title;

    @FXML
    private Label availableMovies_total;

    @FXML
    private Button close;

    @FXML
    private Button customers_btn;

    @FXML
    private Label dashboard_availableMovie;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_totalEarn;

    @FXML
    private Label dashboard_totalSoldTicket;

    @FXML
    private Button editScreening_btn;

    @FXML
    private TableColumn<moviesData, String> editScreening_col_current;

    @FXML
    private TableColumn<moviesData, String> editScreening_col_duration;

    @FXML
    private TableColumn<moviesData, String> editScreening_col_genre;

    @FXML
    private TableColumn<moviesData, String> editScreening_col_movietitle;

    @FXML
    private ComboBox<?> editScreening_current;

    @FXML
    private Button editScreening_deleteBtn;

    @FXML
    private AnchorPane editScreening_form;

    @FXML
    private ImageView editScreening_imgView;

    @FXML
    private TextField editScreening_search;

    @FXML
    private TableView<moviesData> editScreening_tableView;

    @FXML
    private Label editScreening_title;

    @FXML
    private Button editScreening_updateBtn;

    @FXML
    private Button minus;

    @FXML
    private Label username;

    @FXML
    private AnchorPane topForm;
    @FXML
    private Button signout;
    @FXML
    private TextField addMovies_search;

    public void close() {
        System.exit(0);
    }

    public void minus() {
        Stage stage = (Stage) topForm.getScene().getWindow();
        stage.setIconified(true);
    }

    public void displayUsername() {
        username.setText(getData.username);
    }

    public void switchForm(ActionEvent event) {
        System.out.print(event.getSource());
        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            Customers_form.setVisible(false);
            displayTotalSoldTicket();
            displayTotalIncomeToday();
            displayTotalAvailableMovies();
        } else if (event.getSource() == addMovies_btn) {
            dashboard_form.setVisible(false);
            addMovies_form.setVisible(true);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            Customers_form.setVisible(false);
            showAddMoviesList();
        } else if (event.getSource() == availableMovies_btn) {
            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(true);
            editScreening_form.setVisible(false);
            Customers_form.setVisible(false);
            showAvailabelMovies();
        } else if (event.getSource() == editScreening_btn) {
            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(true);
            Customers_form.setVisible(false);
            showEditScreeningList();
        } else if (event.getSource() == customers_btn) {
            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            Customers_form.setVisible(true);

            showCustomerList();
        }

    }
    private double x = 0;
    private double y = 0;
    private Image image;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private int soldTicket;

    public void countTicket() {
        String sql = "Select count(id) from customer ";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                soldTicket = result.getInt("count(id)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayTotalSoldTicket() {
        countTicket();
        dashboard_totalSoldTicket.setText(String.valueOf(soldTicket));
    }
    private double totalIncome;

    public void totalIncomeToday() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String sql = "Select sum(total) from customer where date='"
                + String.valueOf(sqlDate) + "'";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                totalIncome = result.getDouble("SUM(total)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void displayTotalIncomeToday() {
        totalIncomeToday();
        dashboard_totalEarn.setText("$" + String.valueOf(totalIncome));
    }

    private int totalMovies;

    public void totalAvailableMovies() {
        String sql = "Select count(id) from movie where current='Showing'";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                totalMovies = result.getInt("count(id)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayTotalAvailableMovies() {
        totalAvailableMovies();
        dashboard_availableMovie.setText(String.valueOf(totalMovies));
    }
    private String[] currentList = {"Showing", "End Showing"};

    public void comBox() {
        List<String> listCurrent = new ArrayList<>();
        for (String data : currentList) {
            listCurrent.add(data);
        }
        ObservableList listC = FXCollections.observableArrayList(listCurrent);
        editScreening_current.setItems(listC);
    }

    public void importImage() {
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");

        // Bộ lọc cho các tệp hình ảnh
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));

        // Lấy cửa sổ hiện tại (Stage) từ form
        Stage stage = (Stage) addMovies_form.getScene().getWindow();

        // Hiển thị hộp thoại chọn tệp
        File file = open.showOpenDialog(stage);

        // Nếu tệp không null, tải và hiển thị ảnh
        if (file != null) {
            // Lưu đường dẫn hình ảnh vào getData.path
            getData.path = file.getAbsolutePath(); // Lưu đường dẫn để sử dụng sau này

            // Chuyển đổi tệp thành URI và hiển thị ảnh trong ImageView
            image = new Image(file.toURI().toString(), 118, 150, false, true);
            addMovies_imageView.setImage(image);

            // In ra đường dẫn để kiểm tra
            System.out.println("Đường dẫn hình ảnh được thiết lập: " + getData.path);
        }
    }

    public ObservableList<moviesData> addMoviesList() {
        ObservableList<moviesData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM movie";

        connect = database.connectDb();
        if (connect == null) {
            System.out.println("Database connection failed!");
            return listData; // Trả về danh sách rỗng nếu kết nối thất bại
        }

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                moviesData mvD = new moviesData(
                        result.getInt("id"), // Đảm bảo rằng id là Integer
                        result.getString("movieTitle"),
                        result.getString("genre"),
                        result.getString("duration"),
                        result.getString("image"),
                        result.getDate("date"),
                        result.getString("current")
                );
                listData.add(mvD);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error fetching movies: " + e.getMessage());
            alert.showAndWait();
        }
        return listData;
    }

    private ObservableList<moviesData> listAddMovies;

    public void movieId() {
        String sql = "Select count(id) from movie";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                getData.movieId = result.getInt("count(id)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchAddMoviesList() {
        FilteredList<moviesData> filter = new FilteredList(listAddMovies, e -> true);
        addMovies_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicateMoviesData -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String keySearch = newValue.toLowerCase();
                if (predicateMoviesData.getTitle().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateMoviesData.getGenre().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateMoviesData.getDuration().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateMoviesData.getDate().toString().contains(keySearch)) {
                    return true;
                }

                return false;
            });
            SortedList<moviesData> sortData = new SortedList<>(filter);
            sortData.comparatorProperty().bind(addMovies_tableView.comparatorProperty());
            addMovies_tableView.setItems(sortData);
        });
    }

    public void showAddMoviesList() {
        listAddMovies = addMoviesList();
        addMovies_col_movietitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        addMovies_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        addMovies_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        addMovies_col_publisheddate.setCellValueFactory(new PropertyValueFactory<>("date"));

        addMovies_tableView.setItems(listAddMovies);
    }

    public void selectAddMoviesList() {
        // Lấy đối tượng đã chọn từ TableView
        moviesData mvD = addMovies_tableView.getSelectionModel().getSelectedItem();
        int num = addMovies_tableView.getSelectionModel().getSelectedIndex();

        // Kiểm tra nếu không có hàng nào được chọn hoặc mvD là null
        if (num == -1 || mvD == null) {
            System.out.println("No movie selected or invalid selection.");
            return;
        }
        getData.path = mvD.getImage();
        getData.movieId = mvD.getId();
        // Gán các giá trị từ mvD vào các trường giao diện
        addMovies_movietitle.setText(mvD.getTitle());
        addMovies_genre.setText(mvD.getGenre());
        addMovies_duration.setText(mvD.getDuration());

        // Chuyển đổi ngày thành chuỗi
        // Chuyển đổi java.sql.Date sang java.time.LocalDate
        LocalDate localDate = mvD.getDate().toLocalDate();

        // Gán LocalDate vào DatePicker
        addMovies_publishedDate.setValue(localDate);

        // Đảm bảo rằng đường dẫn hình ảnh có định dạng đúng
        String uri = "file:/" + mvD.getImage(); // Thêm dấu gạch chéo sau "file:"
        try {
            image = new Image(uri, 118, 150, false, true);
            addMovies_imageView.setImage(image);
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getMessage());
        }
    }

    public void insertMovies() {
        String sql1 = "SELECT * FROM movie WHERE movieTitle = '"
                + addMovies_movietitle.getText() + "'";
        connect = database.connectDb();
        Alert alert;

        try {
            if (connect == null) {
                System.out.println("Database connection failed!");
                return;
            }

            prepare = connect.prepareStatement(sql1);
            result = prepare.executeQuery();

            if (result.next()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(addMovies_movietitle.getText() + " already exists");
                alert.showAndWait();
            } else {
                if (addMovies_movietitle.getText().isEmpty() || addMovies_genre.getText().isEmpty()
                        || addMovies_duration.getText().isEmpty() || addMovies_imageView.getImage() == null
                        || addMovies_publishedDate.getValue() == null) {

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank fields");
                    alert.showAndWait();
                } else {
                    // Check if the image path is not null
                    if (getData.path == null) {
                        System.out.println("Image path is null.");
                        return;
                    }

                    String sql = "INSERT INTO movie(id, movieTitle, genre, duration, image, date) VALUES(?,?,?,?,?,?)";
                    String uri = getData.path;

                    // Format URI path for the image
                    uri = uri.replace("\\", "\\\\");

                    movieId();  // Ensure movieId increments correctly
                    String mID = String.valueOf(getData.movieId + 1);

                    prepare = connect.prepareStatement(sql);

                    prepare.setString(1, mID);  // Ensure correct order for ID
                    prepare.setString(2, addMovies_movietitle.getText());
                    prepare.setString(3, addMovies_genre.getText());
                    prepare.setString(4, addMovies_duration.getText());
                    prepare.setString(5, uri);
                    prepare.setString(6, String.valueOf(addMovies_publishedDate.getValue()));

                    prepare.execute(); // Execute INSERT
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully added new movie");
                    alert.showAndWait();

                    clearAddMoviesList();
                    showAddMoviesList();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAddMoviesList() {
        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");
        String sql = "UPDATE movie SET movieTitle = '" + addMovies_movietitle.getText()
                + "', genre = '" + addMovies_genre.getText()
                + "', duration = '" + addMovies_duration.getText()
                + "', image ='" + uri
                + "', date = '" + addMovies_publishedDate.getValue()
                + "' WHERE id= '" + String.valueOf(getData.movieId) + "'";
        connect = database.connectDb();
        Alert alert;
        try {
            statement = connect.createStatement();
            if (addMovies_movietitle.getText().isEmpty() || addMovies_genre.getText().isEmpty()
                    || addMovies_duration.getText().isEmpty() || addMovies_imageView.getImage() == null
                    || addMovies_publishedDate.getValue() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first");
                alert.showAndWait();

            } else {
                statement.executeUpdate(sql);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully updated " + addMovies_movietitle.getText());
                alert.showAndWait();
                showAddMoviesList();
                clearAddMoviesList();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void DeleteAddMoviesList() {
        String sql = "Delete from movie where movieTitle='" + addMovies_movietitle.getText() + "'";

        connect = database.connectDb();
        try {
            statement = connect.createStatement();
            Alert alert;
            if (addMovies_movietitle.getText().isEmpty() || addMovies_genre.getText().isEmpty()
                    || addMovies_duration.getText().isEmpty() || addMovies_imageView.getImage() == null
                    || addMovies_publishedDate.getValue() == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confimation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete" + addMovies_movietitle.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (ButtonType.OK.equals(option.get())) {
                    statement.executeUpdate(sql);
                    showAddMoviesList();
                    clearAddMoviesList();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully delete!");
                    alert.showAndWait();
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearAddMoviesList() {
        addMovies_movietitle.setText(" ");
        addMovies_genre.setText(" ");
        addMovies_duration.setText(" ");
        addMovies_imageView.setImage(null);
        addMovies_publishedDate.setValue(null);

    }

    public void selectEditScreening() {
        moviesData mvD = editScreening_tableView.getSelectionModel().getSelectedItem();
        int num = editScreening_tableView.getSelectionModel().getSelectedIndex();

        // Kiểm tra nếu không có hàng nào được chọn hoặc mvD là null
        if (num == -1 || mvD == null) {
            System.out.println("No movie selected or invalid selection.");
            return;
        }
        String uri = "file:/" + mvD.getImage(); // Thêm dấu gạch chéo sau "file:"
        try {
            image = new Image(uri, 140, 161, false, true);
            editScreening_imgView.setImage(image);
            editScreening_title.setText(mvD.getTitle());
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getMessage());
        }
    }

    public ObservableList<moviesData> editScreeningList() {
        ObservableList<moviesData> editsList = FXCollections.observableArrayList();
        connect = database.connectDb();
        try {
            String sql = "Select * from movie";
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                moviesData mvD = new moviesData(
                        result.getInt("id"), // Đảm bảo rằng id là Integer
                        result.getString("movieTitle"),
                        result.getString("genre"),
                        result.getString("duration"),
                        result.getString("image"),
                        result.getDate("date"),
                        result.getString("current")
                );
                editsList.add(mvD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return editsList;
    }
    private ObservableList<moviesData> editSceeningL;

    public void showEditScreeningList() {
        editSceeningL = editScreeningList();
        editScreening_col_movietitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        editScreening_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        editScreening_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        editScreening_col_current.setCellValueFactory(new PropertyValueFactory<>("current"));
        editScreening_tableView.setItems(editSceeningL);

    }

    public void updateEditScreening() {
        String sql = "UPDATE movie SET current='"
                + editScreening_current.getSelectionModel().getSelectedItem()
                + "' WHERE movieTitle= '" + editScreening_title.getText() + "'";
        connect = database.connectDb();
        Alert alert;
        try {
            statement = connect.createStatement();
            if (editScreening_title.getText().isEmpty()
                    || editScreening_imgView.getImage() == null
                    || editScreening_current.getSelectionModel().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first");
                alert.showAndWait();
            } else {
                statement.executeUpdate(sql);
                statement.executeUpdate(sql);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully updated !");
                alert.showAndWait();
                showEditScreeningList();
                clearEditScreening();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearEditScreening() {
        editScreening_title.setText(" ");
        editScreening_imgView.setImage(null);
//        editScreening_current.setSelectionModel(null);
    }

    public void searchEditMoviesList() {
        FilteredList<moviesData> filter = new FilteredList<>(editSceeningL, e -> true);
        editScreening_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicateMoviesData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String keySearch = newValue.toLowerCase();
                if (predicateMoviesData.getTitle().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateMoviesData.getGenre().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateMoviesData.getDuration().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateMoviesData.getCurrent().toString().contains(keySearch)) {
                    return true;
                }

                return false;
            });
            SortedList<moviesData> sortData = new SortedList<>(filter);
            sortData.comparatorProperty().bind(editScreening_tableView.comparatorProperty());
            editScreening_tableView.setItems(sortData);
        });
    }

    public ObservableList<moviesData> availableMoviesList() {
        ObservableList<moviesData> listAvMovies = FXCollections.observableArrayList();
        String sql = "Select * from movie where current='Showing'";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            moviesData mvD;
            while (result.next()) {
                mvD = new moviesData(
                        result.getInt("id"), // Đảm bảo rằng id là Integer
                        result.getString("movieTitle"),
                        result.getString("genre"),
                        result.getString("duration"),
                        result.getString("image"),
                        result.getDate("date"),
                        result.getString("current")
                );
                listAvMovies.add(mvD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAvMovies;
    }
    private ObservableList<moviesData> availableMoviesList;

    public void showAvailabelMovies() {
        availableMoviesList = availableMoviesList();
        availableMovies_col_movietitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        availableMovies_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        availableMovies_col_showdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        availableMovies_tableView.setItems(availableMoviesList);

    }

    public void selectAvailableMovies() {
        moviesData mvD = availableMovies_tableView.getSelectionModel().getSelectedItem();
        int num = availableMovies_tableView.getSelectionModel().getSelectedIndex();

        // Kiểm tra nếu không có hàng nào được chọn hoặc mvD là null
        if (num == -1 || mvD == null) {
            System.out.println("No movie selected or invalid selection.");
            return;
        }

        try {
            availableMovies_movietitle.setText(mvD.getTitle());
            availableMovies_genre.setText(mvD.getGenre());
            availableMovies_date.setText(String.valueOf(mvD.getDate()));
            getData.path = mvD.getImage();
            getData.title = mvD.getTitle();

        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getMessage());
        }
    }

    public void selectMovies() {
        String uri = "file:/" + getData.path; // Thêm dấu gạch chéo sau "file:"
        Alert alert;
        if (availableMovies_movietitle.getText().isEmpty()
                || availableMovies_genre.getText().isEmpty()
                || availableMovies_date.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the movie first");
            alert.showAndWait();
        } else {
            image = new Image(uri, 148, 166, false, true);
            availableMovies_imgView.setImage(image);
            availableMovies_title.setText(getData.title);
            availableMovies_movietitle.setText("");
            availableMovies_genre.setText("");
            availableMovies_date.setText("");
        }

    }
    private SpinnerValueFactory<Integer> spinner1;
    private SpinnerValueFactory<Integer> spinner2;
    private float price1 = 0;
    private float price2 = 0;
    private float total = 0;
    private int qty1;
    private int qty2;
    private int num;
    private int qty;
    private int customerId;

    public void buy() {
        String sql = "INSERT INTO customer (type, quantity, total, date, movieTitle, time) VALUES (?, ?, ?, ?, ?, ?)";
        connect = database.connectDb();
        String type = " ";
        if (price1 > 0 && price2 == 0) {
            type = "Special Class";
        } else if (price2 > 0 && price1 == 0) {
            type = "Normal Class";
        } else if (price1 > 0 && price2 > 0) {
            type = "Special & Normal Class";
        }
        Date date = new Date();
        java.sql.Date setDate = new java.sql.Date(date.getTime());
        LocalDateTime setDateTime = LocalDateTime.now();
        try {
            qty = qty1 + qty2;
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, type); // Type
            prepare.setString(2, String.valueOf(qty)); // Quantity
            prepare.setString(3, String.valueOf(total)); // Total
            prepare.setDate(4, setDate); // Date
            prepare.setString(5, availableMovies_title.getText()); // Movie Title
            prepare.setTimestamp(6, java.sql.Timestamp.valueOf(setDateTime));

            // Check validation
            Alert alert;
            if (availableMovies_imgView.getImage() == null || availableMovies_total.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first");
                alert.showAndWait();
            } else if (price1 == 0 && price2 == 0) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please indicate the quantity of ticket");
                alert.showAndWait();
            } else {
                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully updated!");
                alert.showAndWait();

                // Retrieve customer id and store it in the class variable
                String sql1 = "SELECT MAX(id) AS last_id FROM customer";
                prepare = connect.prepareStatement(sql1);
                result = prepare.executeQuery();
                if (result.next()) {
                    customerId = result.getInt("last_id"); // Gán giá trị customer_id vào biến toàn cục
                }

                // Insert details into customer_info
                String sql2 = "INSERT INTO customer_info (customer_id, type, total, quantity, movieTitle) VALUES (?, ?, ?, ?, ?)";
                prepare = connect.prepareStatement(sql2);
                prepare.setInt(1, customerId); // Set customer_id from last inserted row
                prepare.setString(2, type); // Type
                prepare.setInt(3, qty); // Quantity
                prepare.setDouble(4, total); // Total (make sure total is the correct type, double or float)
                prepare.setString(5, availableMovies_title.getText()); // Movie title
                prepare.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @FXML
//    private void exportInvoiceToPDF() {
//        try {
//            // Kết nối cơ sở dữ liệu để lấy thông tin hóa đơn
//            String sql = "SELECT * FROM customer_info WHERE customer_id = ?"; // Lấy thông tin từ bảng customer_info
//            PreparedStatement prepare = connect.prepareStatement(sql);
//            prepare.setInt(1, customerId); // customerId là id của khách hàng hiện tại
//            ResultSet result = prepare.executeQuery();
//            // Tạo file PDF
//            Document document = new Document(PageSize.A4);
//            String filePath = "Invoice.pdf"; // Đường dẫn đến file PDF
//
//            // Tạo PdfWriter
//            PdfWriter.getInstance(document, new FileOutputStream(filePath));
//
//            document.open();
//
//            // Thêm tiêu đề với kiểu chữ đậm, kích thước lớn
//            Font titleFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
//            Paragraph title = new Paragraph("Invoice", titleFont);
//            title.setAlignment(Element.ALIGN_CENTER);
//            document.add(title);
//
//            // Thêm các thông tin hóa đơn với font chuẩn
//            Font labelFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
//            Font valueFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
//
//            // Thêm thông tin hóa đơn (lấy từ cơ sở dữ liệu)
//            if (result.next()) {
//                String invoiceCode = "HD" + result.getInt("customer_id");
//                String customerName = result.getString("customer_name");
//                String movieTitle = result.getString("movieTitle");
//                int qty = result.getInt("quantity");
//                double total = result.getDouble("total");
//                LocalDate invoiceDate = LocalDate.now(); // Ngày hiện tại
//
//                // Thêm các thông tin này vào document
//                document.add(new Paragraph("Invoice code: " + invoiceCode, labelFont));
//                document.add(new Paragraph("Date: " + invoiceDate, labelFont));
//                document.add(new Paragraph("Customer: " + customerName, labelFont));
//                document.add(new Paragraph("Movie Title: " + movieTitle, labelFont));
//                document.add(new Paragraph("Total: " + total + " VND", labelFont));
//
//                // Tạo một khoảng cách
//                document.add(new Chunk("\n"));
//
//                // Tạo bảng chi tiết sản phẩm (vé)
//                PdfPTable table = new PdfPTable(3); // 3 cột: Mô tả, Số lượng, Giá
//                table.setWidthPercentage(100);
//                table.setSpacingBefore(10f);
//                table.setSpacingAfter(10f);
//
//                // Cột tiêu đề của bảng
//                table.addCell("Describe");
//                table.addCell("Quantity");
//                table.addCell("Price");
//
//                // Thêm chi tiết sản phẩm (vé)
//                table.addCell("Special Class");
//                table.addCell(String.valueOf(qty));  // Số lượng vé
//                table.addCell(String.format("%.0f VND", total));  // Giá vé
//
//                // Thêm bảng vào document
//                document.add(table);
//            } else {
//                System.out.println("customerId: " + customerId);
//
//            }
//
//            // Đóng document
//            document.close();
//
//            // Mở file PDF trong Chrome (hoặc trình duyệt mặc định)
//            if (Desktop.isDesktopSupported()) {
//                Desktop desktop = Desktop.getDesktop();
//                File pdfFile = new File(filePath);
//                desktop.open(pdfFile); // Mở file PDF trong trình duyệt mặc định (nếu hỗ trợ)
//            }
//
//            // Nếu không mở được bằng Desktop, thử với trình duyệt Google Chrome
//            String chromePath = "C:/Program Files/Google/Chrome/Application/chrome.exe"; // Đường dẫn đến Chrome
//            String url = "file:///" + new File(filePath).getAbsolutePath();
//            Runtime.getRuntime().exec(new String[]{chromePath, url});
//
//            System.out.println("Invoice PDF created successfully!");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    @FXML
    private void exportInvoiceToPDF() {
        try {
            // Sử dụng customerId đã được lưu từ buy()
            String sql = "SELECT * FROM customer_info WHERE customer_id = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, customerId); // Sử dụng customerId từ biến toàn cục
            result = prepare.executeQuery();

            // Tiến hành tạo PDF
            Document document = new Document(PageSize.A4);
            String filePath = "Invoice.pdf"; // Đường dẫn đến file PDF
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            document.open();

            // Thêm tiêu đề và các thông tin hóa đơn
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Paragraph title = new Paragraph("Invoice", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Thêm các thông tin hóa đơn với font chuẩn
            
           Font font = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);



            // Thêm thông tin hóa đơn (lấy từ cơ sở dữ liệu)
            if (result.next()) {
                String invoiceCode = "HD" + result.getInt("customer_id");
                String movieTitle = result.getString("movieTitle");
                int qty = result.getInt("quantity");
                double total = result.getDouble("total");
                String type = result.getString("type");  // Lấy giá trị type từ cơ sở dữ liệu
                LocalDate invoiceDate = LocalDate.now(); // Ngày hiện tại

                // Thêm các thông tin này vào document
                document.add(new Paragraph("Invoice code: " + invoiceCode, font));
                document.add(new Paragraph("Date: " + invoiceDate, font));
                document.add(new Paragraph("Movie Title: " + movieTitle, font));
                document.add(new Paragraph("Total: " + qty +".000 VND", font));

                // Tạo một khoảng cách
                document.add(new Chunk("\n"));

                // Tạo bảng chi tiết sản phẩm (vé)
                PdfPTable table = new PdfPTable(4); 
                table.setWidthPercentage(100);
                table.setSpacingBefore(12f);
                table.setSpacingAfter(11f);

                // Cột tiêu đề của bảng
                table.addCell("Movie Title");
                table.addCell("Describe");
                table.addCell("Quantity");
                table.addCell("Total");

                // Thêm chi tiết sản phẩm (vé)
                table.addCell(movieTitle); 
                table.addCell(type);  // Thêm giá trị type lấy từ cơ sở dữ liệu
                table.addCell(String.valueOf(total));  
                table.addCell(String.format("%d.000 VND", qty));  
                // Thêm bảng vào document
                document.add(table);
            } else {
                System.out.println("customerId: " + customerId);
            }

            // Đóng document
            document.close();

            // Mở file PDF trong Chrome (hoặc trình duyệt mặc định)
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                File pdfFile = new File(filePath);
                desktop.open(pdfFile); // Mở file PDF trong trình duyệt mặc định (nếu hỗ trợ)
            }

            // Nếu không mở được bằng Desktop, thử với trình duyệt Google Chrome
            String chromePath = "C:/Program Files/Google/Chrome/Application/chrome.exe"; // Đường dẫn đến Chrome
            String url = "file:///" + new File(filePath).getAbsolutePath();
            Runtime.getRuntime().exec(new String[]{chromePath, url});

            System.out.println("Invoice PDF created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void clearPurchaseTicketInfo() {
        spinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        availableMovies_specialclass_Quanlity.setValueFactory(spinner1);
        availableMovies_normalclass_Quanlity.setValueFactory(spinner2);
        availableMovies_specialclass_price.setText("$0.0");
        availableMovies_normalclass_price.setText("$0.0");
        availableMovies_total.setText("$0.0");
        availableMovies_title.setText("");
        availableMovies_imgView.setImage(null);
    }

    public void showSpinnerValue() {                                   //MINIMUM, MAXNIMUM, VALUE 
        spinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        availableMovies_specialclass_Quanlity.setValueFactory(spinner1);
        availableMovies_normalclass_Quanlity.setValueFactory(spinner2);

    }

    public void getSpinnerValue() {
        qty1 = availableMovies_specialclass_Quanlity.getValue();
        qty2 = availableMovies_normalclass_Quanlity.getValue();
        price1 = (qty1 * 25);
        price2 = (qty2 * 10);
        total = (price1 + price2);
        availableMovies_specialclass_price.setText("$" + String.valueOf(price1));
        availableMovies_normalclass_price.setText("$" + String.valueOf(price2));
        availableMovies_total.setText("$" + String.valueOf(total));
    }

    public ObservableList<customerData> customerList() {
        ObservableList<customerData> customerL = FXCollections.observableArrayList();
        String sql = "Select * from customer";
        connect = database.connectDb();
        try {
            customerData customerD;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                customerD = new customerData(
                        result.getInt("id"),
                        result.getString("type"),
                        result.getString("movieTitle"),
                        result.getDouble("total"),
                        result.getDate("date"),
                        result.getTime("time")
                );
                customerL.add(customerD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerL;
    }
    private ObservableList<customerData> custList;

    public void showCustomerList() {
        custList = customerList();
        Customers_col_ticketNumber.setCellValueFactory(new PropertyValueFactory<>("id"));
        Customers_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        Customers_col_dateChecked.setCellValueFactory(new PropertyValueFactory<>("date"));
        Customers_col_timeChecked.setCellValueFactory(new PropertyValueFactory<>("time"));
        Customers_tableView.setItems(custList);
    }

    public void selectCustomerList() {
        customerData custD = Customers_tableView.getSelectionModel().getSelectedItem();
        int num = Customers_tableView.getSelectionModel().getSelectedIndex();

        // Kiểm tra nếu không có hàng nào được chọn hoặc mvD là null
        if (num == -1 || custD == null) {
            System.out.println("No movie selected or invalid selection.");
            return;
        }

        try {
            Customers_ticketNumber.setText(String.valueOf(custD.getId()));
            Customers_movietitle.setText(custD.getTitle());
            Customers_dateChecked.setText(String.valueOf(custD.getDate()));
            Customers_timeChecked.setText(String.valueOf(custD.getTime()));

        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getMessage());
        }
    }

    public void clearCustomerList() {
        Customers_ticketNumber.setText("");
        Customers_movietitle.setText("");
        Customers_dateChecked.setText("");
        Customers_timeChecked.setText("");

    }

    public void DeleteCustomerList() {
        String sql = "DELETE FROM customer WHERE id=?"; // Sử dụng PreparedStatement

        connect = database.connectDb();
        try {
            Alert alert;
            if (connect == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Database Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to connect to the database.");
                alert.showAndWait();
                return; // Dừng việc tiếp tục thao tác
            }
            if (Customers_ticketNumber == null || Customers_ticketNumber.getText().isEmpty()
                    || Customers_dateChecked == null || Customers_dateChecked.getText().isEmpty()
                    || Customers_timeChecked == null || Customers_timeChecked.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the customer first");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete " + Customers_movietitle.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.isPresent() && option.get() == ButtonType.OK) {
                    prepare = connect.prepareStatement(sql);
                    prepare.setInt(1, Integer.parseInt(Customers_ticketNumber.getText()));
                    prepare.executeUpdate(); // Thực hiện xóa

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully deleted!");
                    alert.showAndWait();
                    showCustomerList();
                    clearCustomerList();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchCustomer() {
        FilteredList<customerData> filter = new FilteredList<>(custList, e -> true);
        Customers_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicatecustomerData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String keySearch = newValue.toLowerCase();
                if (predicatecustomerData.getId().toString().contains(keySearch)) {
                    return true;
                } else if (predicatecustomerData.getTitle().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicatecustomerData.getDate().toString().contains(keySearch)) {
                    return true;
                } else if (predicatecustomerData.getTime().toString().contains(keySearch)) {
                    return true;
                }

                return false;
            });
            SortedList<customerData> sortData = new SortedList<>(filter);
            sortData.comparatorProperty().bind(Customers_tableView.comparatorProperty());
            Customers_tableView.setItems(sortData);
        });
    }

    public void logout() {
        signout.getScene().getWindow().hide();
        try {

            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });

            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayUsername();
        showAddMoviesList();
        showEditScreeningList();
        comBox();
        showAvailabelMovies();
        showSpinnerValue();
        showCustomerList();
        displayTotalIncomeToday();
        displayTotalAvailableMovies();
    }

}
