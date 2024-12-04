package Chapter1;

import MainMenu.ChapterMenu;
import MainMenu.GameLauncher;
import entity.KeyHandlers;
import entity.Player;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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

import java.io.File;
import java.nio.file.Paths;










public class Chapter1 extends Pane {
    private GraphicsContext gc;
    private final GameLauncher gameLauncher;
    private MediaPlayer chapter1MusicPlayer;
    private boolean isPlaying;
    private boolean isPaused;
    private ChapterMenu chapterMenu;
    private float playerHealth = 100;
    private float bossHealth = 100;
    private Stage stage;
    private StackPane root;
    private StackPane overlayPane;
    private int count = 0;


    private final int originalTileSize = 20;  // Kích thước gốc của tile
    private final int scale = 3;              // Tỷ lệ scale
    private final int tileSize = originalTileSize * scale;  // Kích thước của tile sau khi scale

    private final int maxScreenCol = 23;
    private final int maxScreenRow = 13;
    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;

    public Image backgroundImage;
    private Player player;
    KeyHandlers keyH = new KeyHandlers();




    public Chapter1(GameLauncher gameLauncher, ChapterMenu chapterMenu){
        this.gameLauncher = gameLauncher;
        this.chapterMenu = chapterMenu;






    }



    public void showChapter1(Stage stage) {
        isPlaying = true;
        isPaused = false;
        this.stage = stage;
        String musicPath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/Sound/Chapter1_sound.mp3";
        Media music = new Media(Paths.get(musicPath).toUri().toString());
        chapter1MusicPlayer = new MediaPlayer(music);

        // Bat dau phat nhac cua Chapter 1
        chapter1MusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        chapter1MusicPlayer.setVolume(0.5);
        chapter1MusicPlayer.play();

        // tao man hinh cot truyen
        Label storyLabel1 = new Label("Ngộ Không bắt đầu cuộc hành trình của mình và gặp tên Azure trên Thiên Đình.");
        storyLabel1.setFont(new Font("Arial", 24));
        storyLabel1.setTextAlignment(TextAlignment.CENTER);
        storyLabel1.setStyle("-fx-text-fill: white;");

        Label storyLabel2 = new Label("Sau khi đấu khẩu, cả hai bên đã quyết định lao vào nhau và đánh một trận chiến nảy lửa...");
        storyLabel2.setFont(new Font("Arial", 24));
        storyLabel2.setTextAlignment(TextAlignment.CENTER);
        storyLabel2.setStyle("-fx-text-fill: white;");
        AnchorPane.setLeftAnchor(storyLabel2, 225.0);
        AnchorPane.setTopAnchor(storyLabel2, 410.0);

        AnchorPane story = new AnchorPane(storyLabel2);
        StackPane storyPane = new StackPane(storyLabel1, story);
        storyPane.setStyle("-fx-background-color: black;");

        Scene storyScene = new Scene(storyPane, 1380, 800);

        PauseTransition delay = new PauseTransition(Duration.seconds(10));
        delay.setOnFinished(e -> {
            showChapter1Background(stage);
        });
        delay.play();
        // thêm thao tac nhan nut space de co the bo qua
        storyScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                delay.stop();
                showChapter1Background(stage);
            }
        });

        stage.setScene(storyScene);
        stage.setTitle("Chapter 1: Story Intro");
        stage.show();



    }

    public void showChapter1Background(Stage stage) {
        // Tạo giao diện cho Chapter 1
        this.stage = stage;

        /*// Đọc ảnh nền
        String backgroundPath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/Background/background_chap1.jpg";
        Image background = new Image(Paths.get(backgroundPath).toUri().toString());
        ImageView backgroundView = new ImageView(background);

        // Cập nhật tỷ lệ ảnh nền
        backgroundView.setFitWidth(screenWidth);   // Đặt chiều rộng của ảnh nền
        backgroundView.setFitHeight(screenHeight); // Đặt chiều cao của ảnh nền
        backgroundView.setPreserveRatio(true);     // Giữ tỷ lệ gốc của hình ảnh
        getChildren().add(backgroundView);*/

        Canvas canvas = new Canvas(screenWidth, screenHeight);
        getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        //Vẽ background

        try {
            String backgroundPath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/background_1.jpg";
            File file = new File(backgroundPath);
            if (file.exists()) {
                backgroundImage = new Image(file.toURI().toString());
            } else {
                System.out.println("Image not found at path: " + backgroundPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Vẽ background đã scale
        gc.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight);

        // Khởi tạo player
        player = new Player(this, keyH);


        // Bạn có thể thêm mã để vẽ player và các đối tượng khác tại đây
        player.draw(gc);  // Giả sử bạn có phương thức draw trong lớp Player để vẽ người chơi

        // Tạo một vòng lặp game hoặc sử dụng AnimationTimer để cập nhật và vẽ lại
        startGameLoop(gc);

        // Nut pause
        String pauseButtonPath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/ChapterImage/pause_button.png";
        Image pauseButtonImage = new Image(Paths.get(pauseButtonPath).toUri().toString());
        ImageView pauseButtonView = new ImageView(pauseButtonImage);
        Button pauseButton = new Button();
        pauseButton.setGraphic(pauseButtonView);
        pauseButton.setStyle("-fx-background-color: transparent;");
        AnchorPane.setTopAnchor(pauseButton, 10.0);
        AnchorPane.setLeftAnchor(pauseButton, 665.0);

        PauseMenu pauseMenu = new PauseMenu(this, chapter1MusicPlayer, gameLauncher, player);
        pauseButton.setOnAction(e -> {
            isPaused = !isPaused;  // Chuyển đổi trạng thái tạm dừng
            if (isPaused) {
                count++;
                pauseMenu.showPauseMenu(stage);  // Hiển thị menu Pause
                chapter1MusicPlayer.pause();    // Dừng nhạc khi tạm dừng
            } else {
                  // Ẩn menu Pause
                chapter1MusicPlayer.play();  // Phát lại nhạc khi tiếp tục
            }
        });


        // Tạo Canvas và GraphicsContext
        /*Canvas canvas = new Canvas(screenWidth, screenHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();*/

        if (count == 0){
            // tao man hinh fight
            StackPane fightPane = new StackPane();
            String fightPath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/ChapterImage/fight_screen.png";
            Image fightImage = new Image(Paths.get(fightPath).toUri().toString());
            ImageView fightView = new ImageView(fightImage);
            fightPane.getChildren().add(fightView);
            fightPane.setVisible(true);
            PauseTransition fightscene = new PauseTransition(Duration.seconds(1.2));
            fightscene.setOnFinished(e -> {
                fightPane.setVisible(false);     
            });
            fightscene.play();
            // Tạo giao diện chính
            AnchorPane pausePane = new AnchorPane(pauseButton);
            ImageView backgroundView = new ImageView(backgroundImage);
            AnchorPane backgroundPane = new AnchorPane(backgroundView);
            System.out.println("djt me may");
            root = new StackPane(pausePane,backgroundPane, fightPane, canvas);  // Thêm canvas vào root
            createOverlayPane(stage);
            // Tạo scene và gắn vào stage
            Scene scene = new Scene(root, screenWidth, screenHeight);
            stage.setScene(scene);
            stage.setTitle("Chapter 1");
            stage.show();

            // Bắt đầu các hành động game (gọi thread game loop)
            startGameLoop(gc);  // Truyền GraphicsContext vào game loop
            scene.setOnKeyPressed(event -> keyH.handleKeyPressed(event));
            scene.setOnKeyReleased(event -> keyH.handleKeyReleased(event));
            canvas.setFocusTraversable(true);
            startGameLoop(gc);
        } else {
            // Tạo giao diện chính
            AnchorPane pausePane = new AnchorPane(pauseButton);
            ImageView backgroundView = new ImageView(backgroundImage);
            AnchorPane backgroundPane = new AnchorPane(backgroundView);
            root = new StackPane(backgroundPane, pausePane, canvas);  // Thêm canvas vào root
            createOverlayPane(stage);
            // Tạo scene và gắn vào stage
            Scene scene = new Scene(root, screenWidth, screenHeight);
            stage.setScene(scene);
            stage.setTitle("Chapter 1");
            stage.show();

            // Bắt đầu các hành động game (gọi thread game loop)
            startGameLoop(gc);  // Truyền GraphicsContext vào game loop
            scene.setOnKeyPressed(event -> keyH.handleKeyPressed(event));
            scene.setOnKeyReleased(event -> keyH.handleKeyReleased(event));
            canvas.setFocusTraversable(true);
            startGameLoop(gc);
        }
    }



    public void resumeGameActions() {
        // Đánh dấu game không còn ở trạng thái Pause
        isPaused = false;

        // Tiếp tục game loop
        startGameLoop(gc);  // Đảm bảo gc đã được khởi tạo từ trước
    }


    private void createOverlayPane(Stage stage){
        overlayPane = new StackPane();
        overlayPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");
        overlayPane.setVisible(false);

        ImageView resultView = new ImageView();
        resultView.setFitWidth(400);
        resultView.setFitHeight(400);

        String continueButtonPath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/ChapterImage/continue_button.png";
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
            if (chapter1MusicPlayer != null){
                chapter1MusicPlayer.stop();
            }

            // lay hinh anh thang/ thua
            String resultPath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/ChapterImage/" + result.toLowerCase() + ".png";
            Image resultImage = new Image(Paths.get(resultPath).toUri().toString());
            ImageView resultView = (ImageView) overlayPane.getChildren().get(0);
            resultView.setImage(resultImage);
            overlayPane.setVisible(true);
        });
    }
    private void startGameLoop(GraphicsContext gc) {
        // Sử dụng AnimationTimer để cập nhật và vẽ lại game mỗi khung hình
        new javafx.animation.AnimationTimer() {
            @Override
            public void handle(long now) {
                if (isPaused) {
                    return; // Dừng vẽ khi game bị tạm dừng
                }

                // Cập nhật trạng thái game
                player.update();

                // Xóa màn hình và vẽ lại background
                gc.clearRect(0, 0, screenWidth, screenHeight);
                gc.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight);

                // Vẽ lại nhân vật
                player.draw(gc);
            }
        }.start();
        new Thread(() -> {
            try {
                while (playerHealth > 0 && bossHealth > 0) {
                    Thread.sleep(1000);
                    // Kiểm tra tình trạng sức khỏe của nhân vật và boss mỗi giây
                    // Có thể xử lý thêm logic trong này nếu cần
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }






}