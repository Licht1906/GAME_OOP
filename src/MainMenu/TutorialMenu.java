package MainMenu;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class TutorialMenu {
    private GameLauncher gameLauncher;

    public TutorialMenu(GameLauncher gameLauncher){
        this.gameLauncher = gameLauncher;
    }

    public void showTutorialMenu(Stage stage){
        // Tạo background cho menu
        String imagePath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/MenuImage/menu_wallpaper.jpg";
        Image background = new Image(Paths.get(imagePath).toUri().toString());
        ImageView backgroundView = new ImageView(background);

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


        // tao tutorial menu
        String tutorialPath = "C:/Users/ADMIN/Downloads/ProjectGame2D/Project_OOP_IT3100/res/MenuImage/tutorial_menu.png";
        Image menu = new Image(Paths.get(tutorialPath).toUri().toString());
        ImageView menuView = new ImageView(menu);

        // Tạo giao diện chọn setting
        AnchorPane tutorialPane = new AnchorPane(backButton);
        StackPane root = new StackPane(backgroundView, menuView, tutorialPane);
        Scene tutorialScene = new Scene(root, 1380, 800);
        stage.setScene(tutorialScene);
        stage.show();
    }
}
