package MainMenu;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SettingMenu {
    private GameLauncher gameLauncher;
    private MediaPlayer mediaPlayer;

    public SettingMenu(GameLauncher gameLauncher, MediaPlayer mediaPlayer){
        this.gameLauncher = gameLauncher;
        this.mediaPlayer = mediaPlayer;
    }

    public void showSettingMenu(Stage stage){
        // Tạo background cho menu
        String imagePath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/MenuImage/menu_wallpaper.jpg";
        Image background = new Image(Paths.get(imagePath).toUri().toString());
        ImageView backgroundView = new ImageView(background);
        // tao setting menu
        String settingPath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/MenuImage/setting_menu.png";
        Image menu = new Image(Paths.get(settingPath).toUri().toString());
        ImageView menuView = new ImageView(menu);

        // Tạo nút back về menu chính
        String backButtonPath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/MenuImage/back_button.png";
        Image backButtonImage = new Image(Paths.get(backButtonPath).toUri().toString());
        ImageView backButtonView = new ImageView(backButtonImage);
        Button backButton = new Button();
        backButton.setGraphic(backButtonView);
        backButton.setStyle("-fx-background-color: transparent;");
        AnchorPane.setLeftAnchor(backButton, 562.5);
        AnchorPane.setTopAnchor(backButton, 650.0);

        // Hoat dong chuyen canh khi nhan vao nut back
        backButton.setOnAction(e -> {
            gameLauncher.showMainMenu();
        });

        // tao icon volume va mute
        String volumeImagePath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/MenuImage/volume.png";
        String muteImagePath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/MenuImage/mute.png";
        Image volumeIconImage = new Image(Paths.get(volumeImagePath).toUri().toString());
        Image muteIconImage = new Image(Paths.get(muteImagePath).toUri().toString());

        // tao imageview cho nhan
        ImageView volumeLabel = new ImageView(volumeIconImage);
        AnchorPane.setLeftAnchor(volumeLabel, 665.0);
        AnchorPane.setTopAnchor(volumeLabel, 345.0);

        // tao volume slider
        Slider volumeSlider = new Slider(0, 100, 50);
        volumeSlider.setShowTickLabels(true);
        volumeSlider.setShowTickMarks(true);
        volumeSlider.setMajorTickUnit(25);
        volumeSlider.setBlockIncrement(10);
        volumeSlider.setPrefWidth(200);

        // ap dung css vao nut dieu chinh am luong
        volumeSlider.getStylesheets().add("file:/C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/MenuImage/styles.css");

        // vi tri cua slider
        AnchorPane.setLeftAnchor(volumeSlider, 590.0);
        AnchorPane.setTopAnchor(volumeSlider, 400.0);

        // tao hieu ung khi cho am luong bang 0 thi hien mute
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume = newValue.doubleValue() / 100;
            mediaPlayer.setVolume(volume);

            if (volume == 0){
                volumeLabel.setImage(muteIconImage);
            } else {
                volumeLabel.setImage(volumeIconImage);
            }
        });

        // Tạo giao diện chọn setting
        AnchorPane settingPane = new AnchorPane(backButton, volumeLabel, volumeSlider);
        StackPane root = new StackPane(backgroundView, menuView, settingPane);
        Scene chapterScene = new Scene(root, 1380, 800);
        stage.setScene(chapterScene);
        stage.show();
    }
}
