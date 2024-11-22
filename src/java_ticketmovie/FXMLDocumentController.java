/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package java_ticketmovie;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javax.swing.JOptionPane;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author ngthuthuy
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button email_btn;

    @FXML
    private Button email_close;

    @FXML
    private AnchorPane email_form;

    @FXML
    private Button email_minus;
    @FXML
    private TextField txt_email;
    @FXML
    private Button register_close;

    @FXML
    private TextField register_email;

    @FXML
    private AnchorPane register_form;

    @FXML
    private Hyperlink register_haveAnaccount;

    @FXML
    private Hyperlink signin_createnewaccount;

    @FXML
    private Button register_minus;

    @FXML
    private PasswordField register_password;

    @FXML
    private TextField register_username;

    @FXML
    private Button signin_close;

    @FXML
    private Hyperlink signin_forgotpassword;

    @FXML
    private AnchorPane signin_form;

    @FXML
    private Button signin_minus;

    @FXML
    private PasswordField signin_password;

    @FXML
    private TextField signin_username;

    @FXML
    private Button signin_btnlogin;

    @FXML
    private Button otp_btn;

    @FXML
    private AnchorPane otp_form;

    @FXML
    private TextField txt_otp;

    @FXML
    private Button reset_close;

    @FXML
    private PasswordField reset_confirmpass;

    @FXML
    private Button reset_minus;

    @FXML
    private PasswordField reset_newpass;

    @FXML
    private AnchorPane resetpass_form;

    @FXML
    private Button reset_btn;
    private double xOffset;
    private double yOffset;

    public void signIn_close() {
        System.exit(0);
    }

    public void signIn_minus() {
        Stage stage = (Stage) signin_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void register_close() {
        System.exit(0);
    }

    public void register_minus() {
        Stage stage = (Stage) register_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void email_close() {
        System.exit(0);
    }

    public void email_minus() {
        Stage stage = (Stage) email_form.getScene().getWindow();
        stage.setIconified(true);
    }

    private String otpSent; // Biến để lưu OTP đã gửi

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return email != null && email.matches(emailRegex);
    }

    public void signin() throws SQLException {
    String query = "SELECT id, username, password FROM admin WHERE username=? AND password=?";
    connect = database.connectDb();
    try {
        prepare = connect.prepareStatement(query);
        prepare.setString(1, signin_username.getText());
        
        // Mã hóa mật khẩu nhập vào trước khi thực hiện so sánh
        String hashedPassword = hashPassword(signin_password.getText());
        prepare.setString(2, hashedPassword);

        Alert alert;
        result = prepare.executeQuery();

        if (signin_username.getText().isEmpty() || signin_password.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            if (result.next()) {
                // Cập nhật giá trị cho các thuộc tính trong lớp getData
                getData.id = result.getInt("id"); // Lưu id người dùng vào getData
                getData.username = signin_username.getText(); // Lưu username vào getData
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Login");
                alert.showAndWait();

                // Ẩn cửa sổ đăng nhập và mở dashboard
                signin_btnlogin.getScene().getWindow().hide();
Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
Stage stage = new Stage();
Scene scene = new Scene(root);

// Dùng StageStyle.TRANSPARENT để có cửa sổ trong suốt
stage.initStyle(StageStyle.TRANSPARENT);

stage.setScene(scene);
stage.show();

// Thêm mã xử lý kéo cửa sổ
root.setOnMousePressed(event -> {
                    double xOffset = event.getSceneX();
                    double yOffset = event.getSceneY();
});

root.setOnMouseDragged(event -> {
    stage.setX(event.getScreenX() - xOffset);
    stage.setY(event.getScreenY() - yOffset);
});

            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Username or Password");
                alert.showAndWait();
                System.out.println("Username nhập vào: " + signin_username.getText());
                System.out.println("Password nhập vào: " + signin_password.getText());
            }
            signin_username.setText("");
            signin_password.setText("");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    public void handleSendOtp(ActionEvent event) {
        String email = txt_email.getText().trim(); // Lấy email từ trường

        if (isValidEmail(email)) {
            try (Connection connect = database.connectDb();
                    PreparedStatement prepare = connect.prepareStatement("SELECT id, email FROM Admin WHERE email = ?")) {

                prepare.setString(1, email);  // Sử dụng email để tìm id
                try (ResultSet result = prepare.executeQuery()) {
                    if (result.next()) {
                        int userId = result.getInt("id");
                        String storedEmail = result.getString("email");

                        if (email.equals(storedEmail)) {
                            String otpSent = generateOtp();  // Tạo OTP
                            sendOtpToEmail(email, otpSent);  // Gửi OTP đến email
                            System.out.println("OTP đã được gửi đến email: " + email);
                            otp_form.setVisible(true);
                            txt_otp.setVisible(true);
                            otp_btn.setVisible(true);
                        } else {
                            showAlert("Error Message", "Email không khớp với email đã đăng ký. Vui lòng thử lại.", Alert.AlertType.ERROR);
                        }
                    } else {
                        showAlert("Error Message", "Không tìm thấy người dùng với email này.", Alert.AlertType.ERROR);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Error Message", "Email không hợp lệ. Vui lòng nhập lại.", Alert.AlertType.ERROR);
        }
    }

    private String generateOtp() {
        return String.valueOf((int) (Math.random() * 9000) + 1000); // Tạo OTP 4 chữ số
    }

    private void sendOtpToEmail(String email, String otp) {
        final String username = "thuychi251004@gmail.com"; // Địa chỉ email của bạn
        final String password = "lisz ocxo jqtm xmcb"; // Mật khẩu ứng dụng hoặc mật khẩu của bạn
        this.sentOtp = otp; // Lưu mã OTP đã gửi
        // Thiết lập các thuộc tính SMTP cho Gmail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Tạo phiên làm việc với các thuộc tính SMTP và xác thực
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Tạo thông điệp email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Mã xác thực đặt lại mật khẩu");
            message.setText("Mã xác thực của bạn là: " + otp);
            JOptionPane.showMessageDialog(null, "Mã xác thực đã được gửi tới email của bạn. Vui lòng kiểm tra email.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            // Gửi email
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();

        }
    }
    private String sentOtp; // Biến để lưu mã OTP đã gửi

    @FXML
    private void checkOtp() {
        String enteredOtp = txt_otp.getText(); // Lấy mã OTP từ TextField

        if (enteredOtp.equals(sentOtp)) {
            // Thông báo thành công
            JOptionPane.showMessageDialog(null, "Mã OTP chính xác!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            // Tiến hành các hành động khác (ví dụ: cho phép người dùng thay đổi mật khẩu)
            resetpass_form.setVisible(true);
        } else {
            // Thông báo thất bại
            JOptionPane.showMessageDialog(null, "Mã OTP không chính xác. Vui lòng thử lại.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == signin_createnewaccount) {
            signin_form.setVisible(false);
            register_form.setVisible(true);
            signin_forgotpassword.setVisible(false);

        } else if (event.getSource() == register_haveAnaccount) {
            signin_form.setVisible(true);
            register_form.setVisible(false);
            signin_forgotpassword.setVisible(false);
        } else if (event.getSource() == signin_forgotpassword) {
            signin_form.setVisible(false);
            register_form.setVisible(false);
            signin_forgotpassword.setVisible(true);
        }

    }
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public boolean validEmail() {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher match = pattern.matcher(register_email.getText());
        Alert alert;
        if (match.find() && match.group().matches(register_email.getText())) {
            return true;
        } else {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Email");
            alert.showAndWait();
            return false;
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void register() {
        // Câu truy vấn kiểm tra username đã tồn tại hay chưa
        String sql1 = "SELECT username FROM Admin WHERE username = ?";
        // Câu truy vấn kiểm tra email đã tồn tại hay chưa
        String sql2 = "SELECT email FROM Admin WHERE email = ?";
        // Câu truy vấn thêm tài khoản mới
        String sql = "INSERT INTO Admin(email, username, password) VALUES (?, ?, ?)";

        // Kết nối tới cơ sở dữ liệu
        connect = database.connectDb();

        try {
            Alert alert;

            // Kiểm tra các trường nhập có bị bỏ trống không
            if (register_email.getText().isEmpty()
                    || register_username.getText().isEmpty()
                    || register_password.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

                // Kiểm tra mật khẩu có ít hơn 8 ký tự hay không
            } else if (register_password.getText().length() < 8) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Password");
                alert.showAndWait();

                // Kiểm tra email hợp lệ và username có tồn tại không
            } else {
                if (validEmail()) {
                    // Chuẩn bị câu lệnh SELECT để kiểm tra username
                    prepare = connect.prepareStatement(sql1);
                    prepare.setString(1, register_username.getText());
                    result = prepare.executeQuery();

                    // Kiểm tra nếu username đã tồn tại
                    if (result.next()) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText(register_username.getText() + " already exists");
                        alert.showAndWait();
                    } else {
                        // Kiểm tra nếu email đã tồn tại
                        prepare = connect.prepareStatement(sql2);
                        prepare.setString(1, register_email.getText());
                        result = prepare.executeQuery();

                        if (result.next()) {
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Email " + register_email.getText() + " already exists");
                            alert.showAndWait();
                        } else {
                            // Mã hóa mật khẩu trước khi lưu
                            String hashedPassword = hashPassword(register_password.getText());

                            // Chuẩn bị câu lệnh INSERT cho việc tạo tài khoản mới
                            prepare = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                            prepare.setString(1, register_email.getText());
                            prepare.setString(2, register_username.getText());
                            prepare.setString(3, hashedPassword);
                            prepare.executeUpdate();

                            // Lấy ID mới tạo từ cơ sở dữ liệu
                            ResultSet generatedKeys = prepare.getGeneratedKeys();
                            if (generatedKeys.next()) {
                                int newId = generatedKeys.getInt(1); // Lấy `id` mới tạo
                                System.out.println("ID mới tạo: " + newId);

                                // Lưu ID vào getData để sử dụng trong các phần khác của ứng dụng
                                getData.id = newId;
                            }

                            // Thông báo thành công
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Successfully created a new account");
                            alert.showAndWait();

                            // Reset các trường nhập sau khi tạo tài khoản thành công
                            register_email.setText("");
                            register_username.setText("");
                            register_password.setText("");
                            register_form.setVisible(false);
                            signin_form.setVisible(true);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlePasswordReset() {
        String newPassword = reset_newpass.getText();
        String confirmPassword = reset_confirmpass.getText();
        String email = txt_email.getText().trim(); // Lấy email đã nhập vào
        Alert alert;

        // Kiểm tra nếu các trường bị trống
        if (newPassword.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập email, mật khẩu mới và xác nhận mật khẩu");
            alert.showAndWait();
            return;
        }

        // Kiểm tra mật khẩu và xác nhận mật khẩu có khớp không
        if (!newPassword.equals(confirmPassword)) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Mật khẩu xác nhận không khớp");
            alert.showAndWait();
            return;
        }

        // Mã hóa mật khẩu mới
        String hashedPassword = hashPassword(newPassword);

        try {
            // Câu truy vấn cập nhật mật khẩu dựa trên email
            String sql = "UPDATE Admin SET password = ? WHERE email = ?";
            connect = database.connectDb();

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, hashedPassword);
            prepare.setString(2, email); // Sử dụng email đã nhập để xác định người dùng

            int rowsUpdated = prepare.executeUpdate();

            if (rowsUpdated > 0) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Mật khẩu đã được cập nhật thành công");
                alert.showAndWait();

                // Xóa các trường sau khi đổi mật khẩu thành công
                reset_newpass.setText("");
                reset_confirmpass.setText("");
                txt_email.setText("");
                resetpass_form.setVisible(false);  // Ẩn form đặt lại mật khẩu
                otp_form.setVisible(false);        // Đảm bảo ẩn form OTP nếu nó đang hiện
                email_form.setVisible(false);
                signin_form.setVisible(true);      // Hiển thị form đăng nhập
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Không tìm thấy người dùng với email này");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                if (prepare != null) {
                    prepare.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Ẩn anchorPane2 khi ứng dụng khởi động
        email_form.setVisible(false);

        // Xử lý sự kiện click vào Hyperlink
        signin_forgotpassword.setOnAction(event -> {
            // Ẩn anchorPane hiện tại
            signin_form.setVisible(false);
            // Hiển thị anchorPane mới
            email_form.setVisible(true);
        });
//    email_btn.setOnAction(event -> {
//        // Ẩn anchorPane hiện tại
//        email_form.setVisible(false);
//        // Hiển thị anchorPane mới
//        otp_form.setVisible(true);
//    });
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        // Tạo một đối tượng Alert với loại thông báo
        Alert alert = new Alert(alertType);

        // Đặt tiêu đề cho Alert
        alert.setTitle(title);

        // Không có tiêu đề phụ
        alert.setHeaderText(null);

        // Đặt nội dung thông báo
        alert.setContentText(content);

        // Hiển thị Alert và chờ người dùng tương tác
        alert.showAndWait();
    }

}
