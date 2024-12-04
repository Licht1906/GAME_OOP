package Chapter1;

import MainMenu.ChapterMenu;
import MainMenu.GameLauncher;
import entity.Player;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import java.nio.file.Paths;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class PauseMenu{
    private final GameLauncher gameLauncher;
    private Chapter1 chapter1;
    private MediaPlayer mediaPlayer;
    private Player player;

    public PauseMenu(Chapter1 chapter1, MediaPlayer mediaPlayer, GameLauncher gameLauncher, Player player){
        this.chapter1 = chapter1;
        this.mediaPlayer = mediaPlayer;
        this.gameLauncher = gameLauncher;
        this.player = player;
    }

    public void showPauseMenu(Stage stage){
        // tao pause menu
        String imagePath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/ChapterImage/pause_screen.png";
        Image background = new Image(Paths.get(imagePath).toUri().toString());
        ImageView backgroundView = new ImageView(background);

        // tao nut quay tro ve chapter menu
        String exitButtonPath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/ChapterImage/exit_button.png";
        Image exitButtonImage = new Image(Paths.get(exitButtonPath).toUri().toString());
        ImageView exitButtonView = new ImageView(exitButtonImage);
        Button exitButton = new Button();
        exitButton.setGraphic(exitButtonView);
        exitButton.setStyle("-fx-background-color: transparent;");
        AnchorPane.setLeftAnchor(exitButton, 710.0);
        AnchorPane.setTopAnchor(exitButton, 500.0);

        ChapterMenu chapterMenu = new ChapterMenu(gameLauncher);
        exitButton.setOnAction(e -> {
            if (mediaPlayer != null){
                mediaPlayer.stop();
            }
            chapterMenu.showChapterMenu(stage);
        });

        // tao nut de tiep tuc game
        String resumeButtonPath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/ChapterImage/resume_button.png";
        Image resumeButtonImage = new Image(Paths.get(resumeButtonPath).toUri().toString());
        ImageView resumeButtonView = new ImageView(resumeButtonImage);
        Button resumeButton = new Button();
        resumeButton.setGraphic(resumeButtonView);
        resumeButton.setStyle("-fx-background-color: transparent;");
        AnchorPane.setLeftAnchor(resumeButton, 560.0);
        AnchorPane.setTopAnchor(resumeButton, 500.0);

        resumeButton.setOnAction(e -> {
            chapter1.resumeGameActions();
            chapter1.showChapter1Background(stage);
        });

        // tao icon volume va mute
        String volumeImagePath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/ChapterImage/volume.png";
        String muteImagePath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/ChapterImage/mute.png";
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
        volumeSlider.getStylesheets().add("file:/C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/ChapterImage/styles.css");

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

        AnchorPane paussingPane = new AnchorPane(exitButton, resumeButton, volumeLabel, volumeSlider);
        StackPane root = new StackPane(backgroundView, paussingPane);
        root.setStyle("-fx-background-color: black;");
        Scene scene = new Scene(root, 1380, 800);
        stage.setScene(scene);
        stage.setTitle("Pause Menu");
        stage.show();
    }
}