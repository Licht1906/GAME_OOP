package Chapter2;

import MainMenu.ChapterMenu;
import MainMenu.GameLauncher;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
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
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import java.nio.file.Paths;

public class Chapter2 {
    private final GameLauncher gameLauncher;
    private MediaPlayer chapter2MusicPlayer;
    private boolean isPlaying;
    private boolean isPaused;
    private ChapterMenu chapterMenu;
    private float playerHealth = 100;
    private float bossHealth = 100;
    private Stage stage;
    private StackPane root;
    private StackPane overlayPane;
    private int count = 0;

    public Chapter2(GameLauncher gameLauncher, ChapterMenu chapterMenu){
        this.gameLauncher = gameLauncher;
        this.chapterMenu = chapterMenu;
    }

    public void showChapter2(Stage stage) {
        isPlaying = true;
        isPaused = false;
        this.stage = stage;
        String musicPath = "D:/black_meets_wukong/game/res/Sound/chapter2_sound.mp3";
        Media music = new Media(Paths.get(musicPath).toUri().toString());
        chapter2MusicPlayer = new MediaPlayer(music);

        // Bat dau phat nhac cua Chapter 2
        chapter2MusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        chapter2MusicPlayer.setVolume(0.5);
        chapter2MusicPlayer.play();

        // tao man hinh cot truyen
        Label storyLabel1 = new Label("Sau cuộc chạm trán với Azure, Ngộ Không đã bắt hắn khai ra hang ổ của tên trùm cuối.");
        storyLabel1.setFont(new Font("Arial", 24));
        storyLabel1.setTextAlignment(TextAlignment.CENTER);
        storyLabel1.setStyle("-fx-text-fill: white;");

        Label storyLabel2 = new Label("Nơi đấy chính là Hoả Diệm Sơn, Ngộ Không lập tức bay đến đấy và đụng độ thử thách tiếp theo là Crimson...");
        storyLabel2.setFont(new Font("Arial", 24));
        storyLabel2.setTextAlignment(TextAlignment.CENTER);
        storyLabel2.setStyle("-fx-text-fill: white;");
        AnchorPane.setLeftAnchor(storyLabel2, 100.0);
        AnchorPane.setTopAnchor(storyLabel2, 410.0);

        AnchorPane story = new AnchorPane(storyLabel2);
        StackPane storyPane = new StackPane(storyLabel1, story);
        storyPane.setStyle("-fx-background-color: black;");

        Scene storyScene = new Scene(storyPane, 1380, 800);

        PauseTransition delay = new PauseTransition(Duration.seconds(10));
        delay.setOnFinished(e -> {
            showChapter2Background(stage);
        });
        delay.play();
        // thêm thao tac nhan nut space de co the bo qua
        storyScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                delay.stop();
                showChapter2Background(stage);
            }
        });

        stage.setScene(storyScene);
        stage.setTitle("Chapter 2: Story Intro");
        stage.show();
    }

    public void showChapter2Background(Stage stage){
        // Tạo giao diện cho Chapter 2
        this.stage = stage;
        String backgroundPath = "D:/black_meets_wukong/game/res/Background/background_chap2chap3.png";
        Image background = new Image(Paths.get(backgroundPath).toUri().toString());
        ImageView backgroundView = new ImageView(background);

        //nut pause
        String pauseButtonPath = "D:/black_meets_wukong/game/res/ChapterImage/pause_button.png";
        Image pauseButtonImage = new Image(Paths.get(pauseButtonPath).toUri().toString());
        ImageView pauseButtonView = new ImageView(pauseButtonImage);
        Button pauseButton = new Button();
        pauseButton.setGraphic(pauseButtonView);
        pauseButton.setStyle("-fx-background-color: transparent;");
        AnchorPane.setTopAnchor(pauseButton, 10.0);
        AnchorPane.setLeftAnchor(pauseButton, 665.0);

        PauseMenu pauseMenu = new PauseMenu(this, chapter2MusicPlayer, gameLauncher);
        pauseButton.setOnAction(e ->{
            isPaused = true;
            pauseMenu.showPauseMenu(stage);
            count++;
        });

        if (count == 0){
            // tao man hinh fight
            StackPane fightPane = new StackPane();
            String fightPath = "D:/black_meets_wukong/game/res/ChapterImage/fight_screen.png";
            Image fightImage = new Image(Paths.get(fightPath).toUri().toString());
            ImageView fightView = new ImageView(fightImage);
            fightPane.getChildren().add(fightView);
            fightPane.setVisible(true);
            PauseTransition fightscene = new PauseTransition(Duration.seconds(1.2));
            fightscene.setOnFinished(e -> {
                fightPane.setVisible(false);
            });
            fightscene.play();
            AnchorPane pausePane = new AnchorPane(pauseButton);
            root = new StackPane(backgroundView, pausePane, fightPane);
            createOverlayPane(stage);
            // Tạo scene và gắn vào stage
            Scene scene = new Scene(root, 1380, 800);
            stage.setScene(scene);
            stage.setTitle("Chapter 2");
            stage.show();
            startGameActions();
        } else {
            AnchorPane pausePane = new AnchorPane(pauseButton);
            root = new StackPane(backgroundView, pausePane);
            createOverlayPane(stage);
            // Tạo scene và gắn vào stage
            Scene scene = new Scene(root, 1380, 800);
            stage.setScene(scene);
            stage.setTitle("Chapter 2");
            stage.show();
            startGameActions();
        }
    }

    private void startGameActions() {
        new Thread(() -> {
            try {
                while (playerHealth > 0 && bossHealth > 0){
                    Thread.sleep(1000);



                    if (playerHealth <= 0){
                        showEndOverlay("defeat_screen");
                        break;
                    }
                    if (bossHealth <= 0){
                        showEndOverlay("victory_screen");
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void resumeGameActions() {
        isPaused = false;
        startGameActions(); // Tiếp tục các hoạt động trò chơi
    }

    private void createOverlayPane(Stage stage){
        overlayPane = new StackPane();
        overlayPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");
        overlayPane.setVisible(false);

        ImageView resultView = new ImageView();
        resultView.setFitWidth(400);
        resultView.setFitHeight(400);

        String continueButtonPath = "D:/black_meets_wukong/game/res/ChapterImage/continue_button.png";
        Image continueButtonImage = new Image(Paths.get(continueButtonPath).toUri().toString());
        ImageView continueButtonView = new ImageView(continueButtonImage);
        Button continueButton = new Button();
        continueButton.setGraphic(continueButtonView);
        continueButton.setStyle("-fx-background-color: transparent;");
        continueButton.setOnAction(e -> chapterMenu.showChapterMenu(stage));
        AnchorPane.setTopAnchor(continueButton, 505.0);
        AnchorPane.setLeftAnchor(continueButton, 645.0);

        StackPane.setAlignment(resultView, javafx.geometry.Pos.CENTER);
        //StackPane.setAlignment(continueButton, javafx.geometry.Pos.BOTTOM_CENTER);
        StackPane.setMargin(continueButton, new javafx.geometry.Insets(20));

        AnchorPane continuePane = new AnchorPane(continueButton);
        overlayPane.getChildren().addAll(resultView, continuePane);
        root.getChildren().add(overlayPane);
    }

    private void showEndOverlay(String result){
        Platform.runLater(() -> {
            if (chapter2MusicPlayer != null){
                chapter2MusicPlayer.stop();
            }

            // lay hinh anh thang/ thua
            String resultPath = "D:/black_meets_wukong/game/res/ChapterImage/" + result.toLowerCase() + ".png";
            Image resultImage = new Image(Paths.get(resultPath).toUri().toString());
            ImageView resultView = (ImageView) overlayPane.getChildren().get(0);
            resultView.setImage(resultImage);
            overlayPane.setVisible(true);
        });
    }
}