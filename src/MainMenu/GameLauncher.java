package MainMenu;

import Chapter1.PauseMenu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import java.nio.file.Paths;

import java.awt.*;
import java.nio.file.Paths;

public class GameLauncher extends Application {
    // Stage chung cho toàn bộ chương trình
    private Stage primaryStage;
    private MediaPlayer musicPlayer;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initializeBackgroundMusic();
        showIntro(); // Hiển thị phần intro khi bắt đầu
    }

    public MediaPlayer getMusicPlayer(){
        return musicPlayer;
    }

    // cai dat nhac nen
    private void initializeBackgroundMusic(){
        String musicPath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/Sound/background_music.mp3";
        Media backgroundMusic = new Media(Paths.get(musicPath).toUri().toString());
        musicPlayer = new MediaPlayer(backgroundMusic);
        musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        musicPlayer.setVolume(0.5);
        musicPlayer.play();
    }

    // Hiển thị màn hình menu chính
    public void showMainMenu() {
        if (musicPlayer != null && musicPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
            musicPlayer.play(); // Ensure music is playing when showing the main menu
        }

        // Tạo Media và MediaPlayer cho video background
        String menuPath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/MenuImage/background.mp4";
        Media backgroundMedia = new Media(Paths.get(menuPath).toUri().toString());
        MediaPlayer backgroundMediaPlayer = new MediaPlayer(backgroundMedia);
        MediaView backgroundMediaView = new MediaView(backgroundMediaPlayer);

        // Lặp lại video liên tục
        backgroundMediaPlayer.setOnReady(() -> {
            backgroundMediaPlayer.play();
        });
        backgroundMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // Tạo start button
        String startImagePath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/MenuImage/start_button.png";
        Image startButtonImage = new Image(Paths.get(startImagePath).toUri().toString());
        ImageView startButtonView = new ImageView(startButtonImage);
        Button startButton = new Button();
        startButton.setGraphic(startButtonView);
        startButton.setStyle("-fx-background-color: transparent;");
        AnchorPane.setLeftAnchor(startButton, 72.0);
        AnchorPane.setTopAnchor(startButton, 625.0);

        // Chuyển sang Chapter menu khi nhấn vào start button
        startButton.setOnAction(e -> {
            ChapterMenu chapterMenu = new ChapterMenu(this);
            chapterMenu.showChapterMenu(primaryStage);
        });

        // Tạo setting button
        String settingImagePath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/MenuImage/setting_button.png";
        Image settingButtonImage = new Image(Paths.get(settingImagePath).toUri().toString());
        ImageView settingButtonView = new ImageView(settingButtonImage);
        Button settingButton = new Button();
        settingButton.setGraphic(settingButtonView);
        settingButton.setStyle("-fx-background-color: transparent;");
        AnchorPane.setLeftAnchor(settingButton, 399.0);
        AnchorPane.setTopAnchor(settingButton, 625.0);
        //settingButtonView.setTranslateX(-50);
        //settingButtonView.setTranslateY(-5);

        // Chuyển sang setting menu khi nhấn vào setting button
        SettingMenu settingMenu = new SettingMenu(this, musicPlayer);
        settingButton.setOnAction(e -> {
            settingMenu.showSettingMenu(primaryStage);
        });

        // Tạo tutorial button
        String tutorialImagePath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/MenuImage/tutorial_button.png";
        Image tutorialButtonImage = new Image(Paths.get(tutorialImagePath).toUri().toString());
        ImageView tutorialButtonView = new ImageView(tutorialButtonImage);
        Button tutorialButton = new Button();
        tutorialButton.setGraphic(tutorialButtonView);
        tutorialButton.setStyle("-fx-background-color: transparent;");
        AnchorPane.setLeftAnchor(tutorialButton, 726.0);
        AnchorPane.setTopAnchor(tutorialButton, 625.0);

        // Chuyển sang tutorial menu khi nhấn vào tutorial button
        TutorialMenu tutorialMenu = new TutorialMenu(this);
        tutorialButton.setOnAction(e -> {
            tutorialMenu.showTutorialMenu(primaryStage);
        });

        // Tạo exit button
        String exitImagePath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/MenuImage/exit_button.png";
        Image exitButtonImage = new Image(Paths.get(exitImagePath).toUri().toString());
        ImageView exitButtonView = new ImageView(exitButtonImage);
        Button exitButton = new Button();
        exitButton.setGraphic(exitButtonView);
        exitButton.setStyle("-fx-background-color: transparent;");
        AnchorPane.setLeftAnchor(exitButton, 1053.0);
        AnchorPane.setTopAnchor(exitButton, 625.0);

        // Gắn sự kiện thoát game cho nút
        exitButton.setOnAction(e -> Platform.exit());

        // Tạo Scene cho menu chính
        AnchorPane menuPane = new AnchorPane(startButton, settingButton, tutorialButton, exitButton);
        StackPane root = new StackPane(backgroundMediaView, menuPane);
        Scene mainMenuScene = new Scene(root, 1380, 800);

        // Thiết lập Scene cho Stage
        primaryStage.setScene(mainMenuScene);
        primaryStage.setTitle("Main Menu");
        primaryStage.show();
    }



    // Hiển thị video intro
    private void showIntro() {
        // Tạo Media và MediaPlayer cho video intro
        String mediaPath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/MenuImage/intro.mp4";
        Media media = new Media(Paths.get(mediaPath).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        // Khi video kết thúc, chuyển sang menu chính
        mediaPlayer.setOnReady(() -> {
            mediaPlayer.play();
        });
        mediaPlayer.setOnEndOfMedia(() -> showMainMenu());
        //mediaPlayer.play();

        // Tạo Scene cho video intro
        StackPane root = new StackPane(mediaView);
        Scene introScene = new Scene(root, 1380, 800);
        primaryStage.setScene(introScene);
        primaryStage.setTitle("Game Intro");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}