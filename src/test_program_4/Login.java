package test_program_4;

import java.io.File;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ivy
 */
public class Login extends Application {

    private BorderPane root = new BorderPane();
    private StackPane stackPane = new StackPane();
    private HBox blackScene = new HBox();

    /**
     * Generate a login interface, the size is 1024*668, background color is
     * #000000, the top is @addtop(); the center is @addcenter();
     */
    public void start(Stage primaryStage) throws MalformedURLException {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Log in - 0");
        Scene s = new Scene(root, 1024, 668);
        s.getStylesheets().add(getClass().getResource("test_css.css").toExternalForm());
        primaryStage.setScene(s);
        root.setStyle("-fx-background-color: #000000;");
        root.setTop(addtop());
        root.setCenter(addcenter());
        primaryStage.show();
    }

    /**
     * split the top up into three part, the left is null, the center is time,
     * the right is power button,
     *
     * @param setdate() set time function,
     * @param addpower() include exitButton and cancelButton, then put them in a
     * HBox,
     * @param add2topPane() put time and @powerbox to @topPane,
     * @return GridPane,
     */
    private HBox addBlackScene(StackPane s) {
        Rectangle r = new Rectangle();
        r.heightProperty().bind(s.heightProperty());
        r.widthProperty().bind(s.widthProperty());
        r.setFill(Color.rgb(0, 0, 0, 0.8));
        //r.setStyle("-fx-background-radius: 3%;");
        blackScene.getChildren().add(r);
        return blackScene;
    }

    private GridPane addtop() throws MalformedURLException {
        // Ivy start
        GridPane topPane = new GridPane();
        Text time = setdate();
        HBox powerbox = addpower();
        Button powerBtn = setpowerbutton(powerbox);
        add2topPane(topPane, time, powerBtn);
        settopPaneformat(topPane, time, powerBtn);
        // Ivy end
        return topPane;
    }

    /**
     * set the time now, but it is static now should be change to dynamic,
     *
     * @return Text,
     */
    private Text setdate() {
        // Ivy start
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        Text time = new Text(dateFormat.format(date));
        time.setFill(Color.WHITE);
        time.setFont(Font.font("新細明體", FontWeight.BOLD, 30));
        // Ivy end
        return time;
    }

    private HBox addpower() {
        // Ivy start
        GridPane powerPane = setpowerPaneformat();
        Button exitBtn = setexitbutton();
        Button cancelBtn = setcancelbutton();
        add2powerPane(powerPane, exitBtn, cancelBtn);
        HBox powerbox = setpowerbox(powerPane);
        return powerbox;
    }

    private GridPane setpowerPaneformat() {
        //Lillian start
        GridPane g = new GridPane();
        g.setHgap(10);
        g.setVgap(10);
        g.setStyle("-fx-background-color: rgba(221,221,221,0.7);-fx-background-radius:10;-fx-border-color:white;-fx-border-radius:10;-fx-border-size:0.5;");
        g.setMaxSize(300, 120);
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setAlignment(Pos.CENTER);
//        g.setGridLinesVisible(true);

        //Lillian end
        return g;
    }

    /**
     * if you click the exitButton, the System will be closed
     *
     * @return Button
     */
    private Button setexitbutton() {
        // Ivy start
        Button exitBtn = new Button("結束");
        exitBtn.setFont(Font.font("新細明體", FontWeight.NORMAL, 30));
        exitBtn.setOnAction((a) -> {
            Platform.exit();
        });
        // Ivy end
        //Lillian start
        exitBtn.setPrefSize(100, 100);
        exitBtn.getStyleClass().add("button_power");
        //Lillian end
        return exitBtn;
    }

    private Button setcancelbutton() {
        // Ivy start
        Button cancelBtn = new Button("取消");
        cancelBtn.setFont(Font.font("新細明體", FontWeight.NORMAL, 30));
        cancelBtn.setOnAction((a) -> {
            stackPane.getChildren().remove(2);
            stackPane.getChildren().remove(1);
        });
        // Ivy end
        //Lillian start
        cancelBtn.setMinSize(100, 100);
        cancelBtn.getStyleClass().add("button_power");
        //Lillian end

        return cancelBtn;
    }

    private Button setpowerbutton(HBox b) throws MalformedURLException {
        //Lillian start
        File power_File = new File("src/test_program_4/icon/power.png");
        String power_LocalUrl = power_File.toURI().toURL().toString();
        ImageView power = new ImageView(new Image(power_LocalUrl));
        //Lillian end

        // Ivy start
        Button powerBtn = new Button("on/off", power);
        //Lillian start
        powerBtn.setCursor(Cursor.HAND);
        powerBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        powerBtn.setBackground(Background.EMPTY);
        //Lillian end
        powerBtn.setOnAction((a) -> {
            if (stackPane.getChildren().size() == 1) {
                stackPane.getChildren().add(blackScene);
                stackPane.getChildren().add(b);
            }
        });
        powerBtn.setFont(Font.font("新細明體", FontWeight.BOLD, 20));
        // Ivy end

        return powerBtn;
    }

    private void add2powerPane(GridPane g, Button e, Button c) {
        // Ivy start
        g.add(e, 0, 0);
        g.add(c, 1, 0);
        // Ivy end
    }

    private HBox setpowerbox(GridPane g) {
        // Ivy start
        HBox b = new HBox(10);
        b.getChildren().add(g);
        b.setAlignment(Pos.CENTER);
        // Ivy end
        return b;
    }

    private void add2topPane(GridPane g, Text t, Button b) {
        // Ivy start
        g.add(t, 1, 0);
        g.add(b, 2, 0);
        // Ivy end
    }

    private void settopPaneformat(GridPane g, Text t, Button b) {
        // Ivy start
        g.setPrefHeight(30);
        g.setGridLinesVisible(true);
        g.getColumnConstraints().add(new ColumnConstraints(256));
        g.getColumnConstraints().add(new ColumnConstraints(510));
        g.getColumnConstraints().add(new ColumnConstraints(250));
        GridPane.setHalignment(t, HPos.CENTER);
        GridPane.setHalignment(b, HPos.RIGHT);
        GridPane.setValignment(g, VPos.CENTER);
        // Ivy end
    }

    /**
     * @param loginBtnbox include @okBtn,@registerBtn,@forgetBtn
     * @param centerbox put the whole centerPane into @centerbox then add
     * @centerbox into @stackPane
     * @return StackPane
     */
    private StackPane addcenter() throws MalformedURLException {
        // Ivy start
        GridPane centerPane = new GridPane();
        setcenterPaneformat(centerPane);
        Text sceneTitleText = setSceneTitle("  Welcome\n     線上測驗系統");
        sceneTitleText.setFont(Font.font("華康皮皮體W5", FontWeight.BLACK, 40));

        Text idText = setText("帳號:");
        TextField idTextField = setTextField();
        Text pwText = setText("密碼 :");
        PasswordField passwordField = setPWField();
        Button okBtn = setokButton("登入", idTextField);
        Button registerBtn = setRegisterButton("註冊");
        Button forgetBtn = setForgetButton("忘記密碼");
        HBox loginBtnbox = setLoginBtnBox(registerBtn, forgetBtn, okBtn);
        add2LoginCenterPane(centerPane, sceneTitleText, idText, idTextField, pwText, passwordField, loginBtnbox);
        HBox centerbox = setCenterBox(centerPane);
        stackPane.getChildren().add(centerbox);
        // Ivy end
        //Lillian start
        addBlackScene(stackPane);
        idText.setFont(Font.font("華康皮皮體W5", FontWeight.BLACK, 17));
        idTextField.setPromptText("輸入帳號...");
        idTextField.setStyle("-fx-prompt-text-fill:dimgray;");
        pwText.setFont(Font.font("華康皮皮體W5", FontWeight.BLACK, 17));
        passwordField.setPromptText("輸入密碼...");
        passwordField.setStyle("-fx-prompt-text-fill:dimgray;");
        okBtn.getStyleClass().add("button_center");
        registerBtn.getStyleClass().add("button_center");
        forgetBtn.getStyleClass().add("button_center");
        //Lillian end
        return stackPane;
    }

    private GridPane setcenterPaneformat(GridPane g) {
        // Lillian start
        g.setMaxSize(400, 360);
//        g.setGridLinesVisible(true);
        g.setVgap(12);
        g.setHgap(10);
        g.setPadding(new Insets(0, 30, 0, 10));
        g.setStyle("-fx-background-color: rgba(170,170,170,0.3);-fx-background-radius:10;-fx-border-color:darkgray;-fx-border-size:0.5;-fx-border-radius:10;");
//        rgba(170,170,170) #aaaaaa
        // Lillian end
        return g;
    }

    private Text setSceneTitle(String s) {
        // Lillian start
        Text t = new Text(s);
        t.setFill(Color.WHITE);
        t.setFont(Font.font("新細明體", FontWeight.BOLD, 45));
        // Lillian  end
        return t;
    }

    private Text setText(String s) {
        // Lillian start
        Text t = new Text(s);
        t.setFont(Font.font("新細明體", FontWeight.NORMAL, 30));
        t.setFill(Color.WHITE);
        // Lillian end
        return t;
    }

    private TextField setTextField() {
        // Ivy start
        TextField t = new TextField();
        t.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        t.setPrefWidth(250);
        // Ivy end
        return t;
    }

    private PasswordField setPWField() {
        // Ivy start
        PasswordField p = new PasswordField();
        p.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        p.setPrefWidth(250);
        // Ivy end
        return p;
    }

    private Button setokButton(String s, TextField t) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("Tahoma", FontWeight.NORMAL, 24));
        b.setOnAction((a) -> {
            okClicked(b, t);
        });
        // Ivy end
        return b;
    }

    private Button setRegisterButton(String s) throws MalformedURLException {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("Tahoma", FontWeight.NORMAL, 24));
        FlowPane registerPane = addregister();
        b.setOnAction((a) -> {
            stackPane.getChildren().add(blackScene);
            stackPane.getChildren().add(registerPane);
            //root.setCenter(registerPane);
        });
        // Ivy end
        return b;
    }

    private void okClicked(Button b, TextField t) {
        // Ivy start
        System.out.println(" " + t.getText() + " ");
        if (t.getText().equals("1")) {
            Sdesktop s = new Sdesktop(t.getText());
            Stage stage = (Stage) b.getScene().getWindow();
            stage.close();
        } else if (t.getText().equals("2")) {
            TDesktop d = new TDesktop(t.getText());
            Stage stage = (Stage) b.getScene().getWindow();
            stage.close();
        }
        // Ivy end
    }

    private Button setForgetButton(String s) throws MalformedURLException {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("Tahoma", FontWeight.NORMAL, 24));
        FlowPane forgetPane = addForgetPane();
        b.setOnAction((a) -> {
            stackPane.getChildren().add(blackScene);
            stackPane.getChildren().add(forgetPane);
            //root.setCenter(forgetPane);
        });
        // Ivy end
        return b;
    }

    private HBox setLoginBtnBox(Button registerBtn, Button forgetBtn, Button okBtn) {
        // Ivy start
        HBox b = new HBox(20);
        b.setAlignment(Pos.BOTTOM_CENTER);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.getChildren().addAll(registerBtn, forgetBtn, okBtn);
        // Ivy end
        return b;
    }

    private void add2LoginCenterPane(GridPane centerPane, Text sceneTitleText, Text idText, TextField idTextField, Text pwText, PasswordField passwordField, HBox loginBtnbox) {
        //Lillian start
        Separator sep = new Separator();
        sep.setPadding(new Insets(100, -20, 0, 0));
        
        centerPane.add(sceneTitleText, 0, 0, 6, 1);
         centerPane.add(sep, 0, 0, 6, 1);
        centerPane.add(idText, 2, 1);
        centerPane.add(idTextField, 2, 2);
        centerPane.add(pwText, 2, 3);
        centerPane.add(passwordField, 2, 4);
        centerPane.add(loginBtnbox, 0, 5, 6, 1);
        GridPane.setHalignment(sceneTitleText, HPos.LEFT);
        GridPane.setHalignment(idTextField, HPos.CENTER);
        GridPane.setHalignment(passwordField, HPos.CENTER);
        //Lillian end
        
    }

    private HBox setCenterBox(GridPane g) {
        // Ivy start
        HBox b = new HBox(10);
        b.setAlignment(Pos.CENTER);
        b.setPadding(new Insets(20, 20, 20, 20));
        b.getChildren().add(g);
        // Ivy end
        return b;
    }

    /**
     * new a register interface which is a FlowPane
     *
     * @return FlowPane
     */
    private FlowPane addregister() throws MalformedURLException {
        // Ivy start
        FlowPane p = new FlowPane();

        GridPane centerPane = new GridPane();
        Text sceneTitleText = setSceneTitle(" 會員註冊 -0.2");
        Text ridText = setText("帳號：");
        TextField ridTextField = setTextField();
        Text pwText = setText("密碼：");
        PasswordField rpasswordField = setPWField();
        Text nameText = setText("姓名：");
        TextField rnameTextField = setTextField();
        Text roleText = setText("身份：");
        ComboBox roletool = setRoolComboBox();
        Text gradeText = setText("年班：");
        TextField rgradeTextField = setTextField();
        Text mailText = setText("E-MAIL：");
        TextField rmailTextField = setTextField();
        Text photoText = setText("大頭貼：");
        TextField rphotoTextField = setTextField();
        Button searchbtn = setearchbuBtton("...");
        HBox photobox = setPhotoBox(rphotoTextField, searchbtn);
        Button sentBtn = setSentButton("送出");
        Button backBtn = setBackButton("返回");
        HBox btnbox = setRegisterButtonbox(sentBtn, backBtn);
        add2RegisterCenterPane(centerPane, sceneTitleText, ridText, ridTextField, pwText, rpasswordField, nameText, rnameTextField, roleText, roletool, gradeText, rgradeTextField, mailText, rmailTextField, photoText, photobox, btnbox);
        setRegisterCenterPaneformat(centerPane);

        HBox registerbox = setRegisterBox(centerPane);
        p.setAlignment(Pos.CENTER);
        p.getChildren().add(registerbox);
        // Ivy end

        //Lillian start
        sceneTitleText.setFont(Font.font("華康皮皮體W5", FontWeight.LIGHT, 21));
        ridText.setFont(Font.font("華康皮皮體W5", FontWeight.BLACK, 17));
        ridTextField.setPromptText("請輸入帳號...");
        ridTextField.setStyle("-fx-prompt-text-fill:dimgray;");
        pwText.setFont(Font.font("華康皮皮體W5", FontWeight.BLACK, 17));
        rpasswordField.setPromptText("請輸入密碼...");
        rpasswordField.setStyle("-fx-prompt-text-fill:dimgray;");
        nameText.setFont(Font.font("華康皮皮體W5", FontWeight.BLACK, 17));
        rnameTextField.setPromptText("請輸入姓名...");
        rnameTextField.setStyle("-fx-prompt-text-fill:dimgray;");
        roleText.setFont(Font.font("華康皮皮體W5", FontWeight.BLACK, 17));
        rnameTextField.setPromptText("請輸入年班...");
        rnameTextField.setStyle("-fx-prompt-text-fill:dimgray;");
        gradeText.setFont(Font.font("華康皮皮體W5", FontWeight.BLACK, 17));
        mailText.setFont(Font.font("華康皮皮體W5", FontWeight.BLACK, 17));
        rmailTextField.setPromptText("請輸入信箱...");
        rmailTextField.setStyle("-fx-prompt-text-fill:dimgray;");
        photoText.setFont(Font.font("華康皮皮體W5", FontWeight.BLACK, 17));
        searchbtn.getStyleClass().add("button_search");
        searchbtn.setMaxSize(8, 4);
        searchbtn.setTextAlignment(TextAlignment.CENTER);
        sentBtn.getStyleClass().add("button_center");
        backBtn.getStyleClass().add("button_center");
        //Lillian end
        return p;
    }

    private GridPane setRegisterCenterPaneformat(GridPane g) {
        // Ivy start
//        g.setGridLinesVisible(true);
        g.setVgap(5);
        g.setHgap(10);
        // Ivy end
        //Lillian start
        g.setStyle("-fx-background-color: rgba(71,71,71,0.9);-fx-background-radius:10;-fx-border-color:white;-fx-border-size:0.5;-fx-border-radius:10;");
        //Lillian end
        return g;
    }

    private ComboBox setRoolComboBox() {
        // Ivy start
        ObservableList<String> role = FXCollections.observableArrayList("請選擇", "老師", "學生");
        ComboBox c = new ComboBox<String>(role);
        c.setValue("請選擇");
        c.setMinSize(100, 30);
        // Ivy end
        return c;
    }

    private HBox setPhotoBox(TextField t, Button b) {
        // Ivy start
        HBox hb = new HBox(10);
        hb.setAlignment(Pos.BOTTOM_RIGHT);
        hb.getChildren().addAll(t, b);
        // Ivy end
        return hb;
    }

    private Button setearchbuBtton(String s) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        // Ivy end
        return b;
    }

    private Button setSentButton(String s) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        return b;
    }

    private Button setBackButton(String s) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
            backClicked();
        });
        // Ivy end
        return b;
    }

    private HBox setRegisterButtonbox(Button s, Button b) {
        // Ivy start
        HBox hb = new HBox(10);
        hb.setAlignment(Pos.BOTTOM_RIGHT);
        hb.setPadding(new Insets(10, 10, 10, 10));
        hb.getChildren().addAll(s, b);
        // Ivy end
        return hb;
    }

    private void add2RegisterCenterPane(GridPane g, Text sceneTitleText, Text ridText, TextField ridTextField, Text pwText, PasswordField rpasswordField, Text nameText, TextField rnameTextField, Text roleText, ComboBox roletool, Text gradeText, TextField rgradeTextField, Text mailText, TextField rmailTextField, Text photoText, HBox photobox, HBox btnbox) throws MalformedURLException {
        //Lillian start
        File arrowfile = new File("src/test_program_4/icon/arrow.png");
        String arrowlocalUrl = arrowfile.toURI().toURL().toString();
        ImageView arrow = new ImageView(new Image(arrowlocalUrl));

        Separator sep = new Separator();
        sep.setPadding(new Insets(40, 10, 0, 10));

        HBox title = new HBox();
        title.setPadding(new Insets(10, 5, 5, 10));
        title.getChildren().add(arrow);
        title.getChildren().add(sceneTitleText);

        g.add(title, 0, 0, 6, 1);
        g.add(sep, 0, 0, 6, 1);
        GridPane.setValignment(title, VPos.BOTTOM);
        GridPane.setHalignment(title, HPos.CENTER);
        g.add(ridText, 2, 1);
        g.add(ridTextField, 2, 2);
        g.add(pwText, 2, 3);
        g.add(rpasswordField, 2, 4);
        g.add(nameText, 2, 5);
        g.add(rnameTextField, 2, 6);
        g.add(roleText, 2, 7);
        g.add(roletool, 2, 8);
        g.add(gradeText, 2, 9);
        g.add(rgradeTextField, 2, 10);
        g.add(mailText, 2, 11);
        g.add(rmailTextField, 2, 12);
        g.add(photoText, 2, 13);
        g.add(photobox, 2, 14);
        g.add(btnbox, 2, 15);
        //Lillian end
    }

    private HBox setRegisterBox(GridPane g) {
        // Ivy start
        HBox b = new HBox(10);
        b.setAlignment(Pos.CENTER);
        b.setPadding(new Insets(20, 20, 20, 20));
        b.getChildren().add(g);
        // Ivy end
        return b;
    }

    /**
     * new a forget interface which is a FlowPane
     *
     * @return FlowPane
     */
    private FlowPane addForgetPane() throws MalformedURLException {
        // Ivy start
        FlowPane bottomPane = new FlowPane();
        bottomPane.setAlignment(Pos.CENTER);
        GridPane forgetcenterPane = new GridPane();
        Text sceneTitleText = setSceneTitle(" 忘記密碼 -0.3");
        Text idText = setText("帳號 ：");
        TextField fidTextField = setTextField();
        Text noticeText = setText("＊＊系統將發送通知信至您的信箱");
        Button fsentBtn = setForgetandSentButton("送出");
        Button fbackBtn = setForgetandBackButton("返回");
        HBox forgetBtnbox = setForgetButtonBox(fsentBtn, fbackBtn);
        add2ForgetCenterPane(forgetcenterPane, sceneTitleText, idText, fidTextField, noticeText, forgetBtnbox);
        setforgetCenterPaneformat(forgetcenterPane, noticeText);
        HBox forgetbox = setForgetBox(forgetcenterPane);
        bottomPane.getChildren().add(forgetbox);
        // Ivy end
        //Lillian start
        sceneTitleText.setFont(Font.font("華康皮皮體W5", FontWeight.LIGHT, 21));
        idText.setFont(Font.font("華康皮皮體W5", FontWeight.BLACK, 17));
        noticeText.setFont(Font.font("華康皮皮體W5", FontWeight.BLACK, 17));
        fidTextField.setPromptText("請輸入帳號...");
        fidTextField.setStyle("-fx-prompt-text-fill:dimgray;");
        fsentBtn.getStyleClass().add("button_center");
        fbackBtn.getStyleClass().add("button_center");
        //Lillian end
        return bottomPane;
    }

    private Button setForgetandSentButton(String s) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        return b;
    }

    private Button setForgetandBackButton(String s) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
            backClicked();
        });
        // Ivy end
        return b;
    }

    private HBox setForgetButtonBox(Button fsentBtn, Button fbackBtn) {
        // Ivy start
        HBox b = new HBox(10);
        b.setAlignment(Pos.BOTTOM_RIGHT);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.getChildren().addAll(fsentBtn, fbackBtn);
        // Ivy end
        return b;
    }

    private void add2ForgetCenterPane(GridPane forgetcenterPane, Text sceneTitleText, Text idText, TextField fidTextField, Text noticeText, HBox forgetBtnbox) throws MalformedURLException {
        //Lillian start
        File arrowfile = new File("src/test_program_4/icon/arrow.png");
        String arrowlocalUrl = arrowfile.toURI().toURL().toString();
        ImageView arrow = new ImageView(new Image(arrowlocalUrl));

        Separator sep = new Separator();
        sep.setPadding(new Insets(40, 10, 0, 10));
        HBox title = new HBox();
        title.setPadding(new Insets(10, 5, 5, 10));
        title.getChildren().add(arrow);
        title.getChildren().add(sceneTitleText);

        forgetcenterPane.add(title, 0, 0, 6, 1);
        forgetcenterPane.add(sep, 0, 0, 6, 1);

        GridPane.setValignment(title, VPos.BOTTOM);
        GridPane.setHalignment(title, HPos.CENTER);

        forgetcenterPane.add(idText, 2, 1);
        forgetcenterPane.add(fidTextField, 2, 2);
        forgetcenterPane.add(noticeText, 1, 3, 2, 1);
        forgetcenterPane.add(forgetBtnbox, 3, 4);
        //Lillian end

    }

    private GridPane setforgetCenterPaneformat(GridPane g, Text t) {
        // Lillian start
//        g.setGridLinesVisible(true);
        g.setVgap(10);
        g.setHgap(15);
        g.setStyle("-fx-background-color: rgba(71,71,71,0.9);-fx-background-radius:10;-fx-border-color:white;-fx-border-size:0.5;-fx-border-radius:10;");
//        linear-gradient(#c0c0c0,#afafaf,#cacaca, #6d6d6d);

        GridPane.setHalignment(t, HPos.CENTER);
        // Lillian end
        return g;
    }

    private HBox setForgetBox(GridPane forgetcenterPane) {
        // Ivy start
        HBox b = new HBox(10);
        b.setAlignment(Pos.CENTER);
        b.setPadding(new Insets(20, 20, 20, 20));
        b.getChildren().add(forgetcenterPane);
        // Ivy end
        return b;
    }

    private void backClicked() {
        // Ivy start
        stackPane.getChildren().remove(2);
        stackPane.getChildren().remove(1);
        // Ivy end
    }

    public static void main(String[] args) {
        launch(args);
    }
}
