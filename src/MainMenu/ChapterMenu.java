package MainMenu;

import Chapter1.Chapter1;
import Chapter2.Chapter2;
import Chapter3.Chapter3;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class ChapterMenu {
    private final GameLauncher gameLauncher;
    private ChapterMenu chapterMenu;

    public ChapterMenu(GameLauncher gameLauncher){
        this.gameLauncher = gameLauncher;
    }

    public void showChapterMenu(Stage stage) {
        // Tạo giao diện chọn chapter
        AnchorPane chapterPane = new AnchorPane();

        // Tạo background cho menu chọn chapter
        String imagePath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/MenuImage/menu_wallpaper.jpg";
        Image background = new Image(Paths.get(imagePath).toUri().toString());
        ImageView backgroundView = new ImageView(background);

        // Tạo nút Chapter 1
        String chapter1Path = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/MenuImage/chapter1_button.png";
        Image chapter1Image = new Image(Paths.get(chapter1Path).toUri().toString());
        ImageView chapter1View = new ImageView(chapter1Image);
        Button chapter1Button = new Button();
        chapter1Button.setGraphic(chapter1View);
        chapter1Button.setStyle("-fx-background-color: transparent;");
        AnchorPane.setLeftAnchor(chapter1Button, 157.5);
        AnchorPane.setTopAnchor(chapter1Button, 475.0);


        // Tạo nút Chapter 2
        String chapter2Path = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/MenuImage/chapter2_button.png";
        Image chapter2Image = new Image(Paths.get(chapter2Path).toUri().toString());
        ImageView chapter2View = new ImageView(chapter2Image);
        Button chapter2Button = new Button();
        chapter2Button.setGraphic(chapter2View);
        chapter2Button.setStyle("-fx-background-color: transparent;");
        AnchorPane.setLeftAnchor(chapter2Button, 565.0);
        AnchorPane.setTopAnchor(chapter2Button, 475.0);

        // Tạo nút Chapter 3
        String chapter3Path = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/MenuImage/chapter3_button.png";
        Image chapter3Image = new Image(Paths.get(chapter3Path).toUri().toString());
        ImageView chapter3View = new ImageView(chapter3Image);
        Button chapter3Button = new Button();
        chapter3Button.setGraphic(chapter3View);
        chapter3Button.setStyle("-fx-background-color: transparent;");
        AnchorPane.setLeftAnchor(chapter3Button, 972.5);
        AnchorPane.setTopAnchor(chapter3Button, 475.0);

        // Sự kiện nút chọn chapter
        chapter1Button.setOnAction(e -> {
            if (gameLauncher.getMusicPlayer() != null){
                gameLauncher.getMusicPlayer().stop();
            }
            Chapter1 chapter1 = new Chapter1(gameLauncher, this);
            chapter1.showChapter1(stage);
        });
        chapter2Button.setOnAction(e -> {
            if (gameLauncher.getMusicPlayer() != null){
                gameLauncher.getMusicPlayer().stop();
            }
            Chapter2 chapter2 = new Chapter2(gameLauncher, this);
            chapter2.showChapter2(stage);
        });
        chapter3Button.setOnAction(e -> {
            if (gameLauncher.getMusicPlayer() != null){
                gameLauncher.getMusicPlayer().stop();
            }
            Chapter3 chapter3 = new Chapter3(gameLauncher, this);
            chapter3.showChapter3(stage);
        });

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

        // Cảnh cho menu chọn chapter
        chapterPane.getChildren().addAll(chapter1Button, chapter2Button, chapter3Button, backButton);
        StackPane root = new StackPane(backgroundView, chapterPane);
        Scene chapterScene = new Scene(root, 1380, 800);
        stage.setScene(chapterScene);
        stage.show();
    }
}
