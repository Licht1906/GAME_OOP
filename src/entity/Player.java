package entity;

import java.io.File;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import javafx.scene.image.Image;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


import Chapter1.Chapter1;
import entity.KeyHandlers;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class Player extends Entity{
    
    


    public int maxHealth = 300; // Máu tối đa, giả sử là 3.000 để phù hợp với số trên hình ảnh
    private int currentHealth = 300; // Máu hiện tại
    private int barWidth = 400; // Chiều rộng của thanh máu
    private int barHeight = 20; // Chiều cao của thanh máu
    





    private int animationFrame = 0; // Biến đếm khung hình hiện tại
    private int animationCounter = 0; // Biến đếm thời gian chuyển đổi khung hình
    private final int animationSpeed = 30; // Tốc độ chuyển đổi khung hình
    private boolean isMoving = false; // Biến để kiểm tra xem nhân vật có di chuyển hay không
    private boolean isUsingSkill = false;
    private boolean isUsingJump = false;
    private int skillCounter = 0; // Đếm thời gian để điều khiển hoạt ảnh skill
    private boolean isUsingSkillK = false;
    private int skillKCounter = 0; // Đếm thời gian để điều khiển hoạt ảnh skill qua phím K
    Chapter1 gp;
    public KeyHandlers keyH;


    public Player(Chapter1 gp, KeyHandlers keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
        updateHealth(currentHealth);
    }
    public void setDefaultValues(){
        x = 0;
        y = 500;
        speed = 4;
        direction = "down";
    }


    public void drawHealthBar(GraphicsContext gc) {
        // Đảm bảo currentHealth nằm trong khoảng từ 0 đến maxHealth
        double healthRatio = Math.max(0, Math.min(1, (double) currentHealth / maxHealth));

        // Vẽ viền ngoài của thanh máu
        gc.setFill(Color.WHITE); // Màu viền trắng
        gc.fillRoundRect(0, 0, barWidth + 4, barHeight + 4, 10, 10);

        // Vẽ nền thanh máu (màu xám)
        gc.setFill(Color.GRAY);
        gc.fillRoundRect(0, 0, barWidth, barHeight, 8, 8);

        // Vẽ thanh máu (với gradient)
        int healthBarWidth = (int) (barWidth * healthRatio);
        LinearGradient gradient = new LinearGradient(
                0, 0, barWidth, 0, // Vị trí của gradient
                false,
                null,
                new Stop(0, Color.RED),   // Màu đỏ khi máu thấp
                new Stop(0.5, Color.ORANGE),  // Màu cam khi máu ở mức trung bình
                new Stop(1, Color.GREEN)  // Màu xanh khi máu đầy
        );
        gc.setFill(gradient);
        gc.fillRoundRect(2, 2, healthBarWidth, barHeight, 8, 8);
    }



    public void getPlayerImage() {
        try {
            // standing
            stand1 = new Image(new File("res/standard1.PNG").toURI().toString());
            stand2 = new Image(new File("res/standard2.PNG").toURI().toString());
            stand3 = new Image(new File("res/standard3.PNG").toURI().toString());
            stand4 = new Image(new File("res/standard4.PNG").toURI().toString());
            stand5 = new Image(new File("res/standard5.PNG").toURI().toString());

            // right
            right1 = new Image(new File("res/right1.PNG").toURI().toString());
            right2 = new Image(new File("res/right2.PNG").toURI().toString());
            right3 = new Image(new File("res/right3.PNG").toURI().toString());

            // jump_right
            jump1 = new Image(new File("res/jump_right/jump1.PNG").toURI().toString());
            jump2 = new Image(new File("res/jump_right/jump2.PNG").toURI().toString());
            jump3 = new Image(new File("res/jump_right/jump3.PNG").toURI().toString());

            // jump_left
            jump1_1 = new Image(new File("res/jump_left/jump1.PNG").toURI().toString());
            jump2_1 = new Image(new File("res/jump_left/jump2.PNG").toURI().toString());
            jump3_1 = new Image(new File("res/jump_left/jump3.PNG").toURI().toString());

            // skill j
            j1 = new Image(new File("res/quaytay1.PNG").toURI().toString());
            j2 = new Image(new File("res/quaytay2.PNG").toURI().toString());
            j3 = new Image(new File("res/quaytay3.PNG").toURI().toString());
            j4 = new Image(new File("res/quaytay4.PNG").toURI().toString());
            j5 = new Image(new File("res/quaytay5.PNG").toURI().toString());
            j6 = new Image(new File("res/quaytay6.PNG").toURI().toString());

            // skill k
            k2 = new Image(new File("res/skill2.PNG").toURI().toString());
            k3 = new Image(new File("res/skill3.PNG").toURI().toString());
            k4 = new Image(new File("res/skill4.PNG").toURI().toString());
            k5 = new Image(new File("res/skill5.PNG").toURI().toString());
            k6 = new Image(new File("res/skill6.PNG").toURI().toString());
            k7 = new Image(new File("res/skill8.PNG").toURI().toString());
            k8 = new Image(new File("res/skill7.PNG").toURI().toString());
            k9 = new Image(new File("res/skill9.PNG").toURI().toString());

            // left
            left1 = new Image(new File("res/left1.PNG").toURI().toString());
            left2 = new Image(new File("res/left2.PNG").toURI().toString());
            left3 = new Image(new File("res/left3.PNG").toURI().toString());

            // j_left
            j1_l = new Image(new File("res/left/quaytay1_left.PNG").toURI().toString());
            j2_l = new Image(new File("res/left/quaytay2_left.PNG").toURI().toString());
            j3_l = new Image(new File("res/left/quaytay3_left.PNG").toURI().toString());
            j4_l = new Image(new File("res/left/quaytay4_left.PNG").toURI().toString());
            j5_l = new Image(new File("res/left/quaytay5_left.PNG").toURI().toString());
            j6_l = new Image(new File("res/left/quaytay6_left.PNG").toURI().toString());

            // k_left
            k2_l = new Image(new File("res/left/skill2_left.PNG").toURI().toString());
            k3_l = new Image(new File("res/left/skill3_left.PNG").toURI().toString());
            k4_l = new Image(new File("res/left/skill4_left.PNG").toURI().toString());
            k5_l = new Image(new File("res/left/skill5_left.PNG").toURI().toString());
            k6_l = new Image(new File("res/left/skill6_left.PNG").toURI().toString());
            k7_l = new Image(new File("res/left/skill7_left.PNG").toURI().toString());
            k8_l = new Image(new File("res/left/skill8_left.PNG").toURI().toString());
            k9_l = new Image(new File("res/left/skill9_left.PNG").toURI().toString());

            // standing_left
            stand1_l = new Image(new File("res/left/standard1_left.PNG").toURI().toString());
            stand2_l = new Image(new File("res/left/standard2_left.PNG").toURI().toString());
            stand3_l = new Image(new File("res/left/standard3_left.PNG").toURI().toString());
            stand4_l = new Image(new File("res/left/standard4_left.PNG").toURI().toString());
            stand5_l = new Image(new File("res/left/standard5_left.PNG").toURI().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int getHealth(){
        return currentHealth;
    }


    public void updateHealth(int newHealth) {
        currentHealth = newHealth;
        // Gọi hàm vẽ lại thanh máu tại đây hoặc từ vòng lặp game
    }







    public void update() {
        // Kiểm tra xem phím "J" có được nhấn không
        if (keyH.jPressed) {
            isUsingSkill = true; // Bắt đầu sử dụng skill
            skillCounter = 0; // Reset bộ đếm thời gian cho skill
        }
        if (keyH.kPressed) {
            isUsingSkillK = true; // Bắt đầu sử dụng skill qua phím K
            skillKCounter = 0; // Reset bộ đếm thời gian cho skill K
        }
        if (keyH.wPressed) {
            isUsingJump = true;
            skillCounter = 0;
        }

    
        // Chỉ cho phép di chuyển nếu không sử dụng skill
        if (!isUsingSkill) {
            // Giả định ban đầu là nhân vật không di chuyển
            isMoving = false;
    
            
            if (keyH.leftPressed) {
                direction = "left";
                x -= speed;
                isMoving = true;
            } 
            else if (keyH.rightPressed) {
                direction = "right";
                x += speed;
                isMoving = true;
            }
        }
    }

    public void draw(GraphicsContext gc) {
        Image image = null;
        drawHealthBar(gc);  // Giả sử bạn đã có phương thức vẽ thanh máu

        // Tăng animationCounter mỗi lần vẽ
        animationCounter++;
        if (animationCounter > animationSpeed) {
            animationCounter = 0;

            // Xử lý khi đang sử dụng skill qua phím J
            if (isUsingSkill) {
                animationFrame = (animationFrame + 1) % 6; // Chuyển đổi giữa 6 khung hình skill J
                skillCounter++;

                if (skillCounter >= 6) {
                    isUsingSkill = false; // Dừng sử dụng skill J
                }
            }
            // Xử lý khi đang sử dụng skill qua phím K
            else if (isUsingSkillK) {
                animationFrame = (animationFrame + 1) % 8; // Chuyển đổi giữa 8 khung hình skill K
                skillKCounter++;

                if (skillKCounter >= 8) {
                    isUsingSkillK = false; // Dừng sử dụng skill K
                }
            }
            // Xử lý khi sử dụng skill nhảy
            else if (isUsingJump) {
                animationFrame = (animationFrame + 1) % 3;
                skillCounter++;
                if (skillCounter >= 3) {
                    isUsingJump = false;
                }
            }
            // Xử lý di chuyển hoặc đứng yên
            else if (isMoving) {
                switch (direction) {
                    case "left":
                    case "right":
                        animationFrame = (animationFrame + 1) % 3; // Chuyển đổi giữa 3 khung hình
                        break;
                }
            } else {
                animationFrame = (animationFrame + 1) % 5; // Chuyển đổi giữa 5 khung hình đứng yên
            }
        }

        // Vẽ hình ảnh cho skill J
        if (isUsingSkill) {
            if (direction.equals("left")) {
                switch (animationFrame) {
                    case 0: image = j1_l; break;
                    case 1: image = j2_l; break;
                    case 2: image = j3_l; break;
                    case 3: image = j4_l; break;
                    case 4: image = j5_l; break;
                    case 5: image = j6_l; break;
                }
            } else {
                switch (animationFrame) {
                    case 0: image = j1; break;
                    case 1: image = j2; break;
                    case 2: image = j3; break;
                    case 3: image = j4; break;
                    case 4: image = j5; break;
                    case 5: image = j6; break;
                }
            }
        }
        // Vẽ hình ảnh cho skill K
        else if (isUsingSkillK) {
            if (direction.equals("left")) {
                switch (animationFrame) {
                    case 0: image = k2_l; break;
                    case 1: image = k3_l; break;
                    case 2: image = k4_l; break;
                    case 3: image = k5_l; break;
                    case 4: image = k6_l; break;
                    case 5: image = k7_l; x -= 2 * speed; break;
                    case 6: image = k8_l; break;
                    case 7: image = k9_l; break;
                }
            } else {
                switch (animationFrame) {
                    case 0: image = k2; break;
                    case 1: image = k3; break;
                    case 2: image = k4; break;
                    case 3: image = k5; break;
                    case 4: image = k6; break;
                    case 5: image = k7; x += 2 * speed; break;
                    case 6: image = k8; break;
                    case 7: image = k9; break;
                }
            }
        }
        // Vẽ hình ảnh cho skill nhảy
        else if (isUsingJump) {

            if (direction.equals("left")) {
                switch (animationFrame) {
                    case 0: image = jump1_1; x -= 1 * speed; break;
                    case 1: image = jump2_1; x -= 1 * speed; break;
                    case 2: image = jump3_1; x -= 1 * speed; break;
                }
            } else {
                switch (animationFrame) {
                    case 0: image = jump1; x += 1 * speed; break;
                    case 1: image = jump2; x += 1 * speed; break;
                    case 2: image = jump3; x += 1 * speed; break;
                }
            }
        }
        // Di chuyển hoặc đứng yên
        else if(!isMoving) {
            if (direction.equals("left")) {
                switch (animationFrame) {
                    case 0: image = stand1_l; break;
                    case 1: image = stand2_l; break;
                    case 2: image = stand3_l; break;
                    case 3: image = stand4_l; break;
                    case 4: image = stand5_l; break;
                }
            } else {
                switch (animationFrame) {
                    case 0: image = stand1; break;
                    case 1: image = stand2; break;
                    case 2: image = stand3; break;
                    case 3: image = stand4; break;
                    case 4: image = stand5; break;
                }
            }
        } else  {
            if (direction.equals("left")) {
                switch (animationFrame) {
                    case 0: image = left1; break;
                    case 1: image = left2; break;
                    case 2: image = left3; break;
                }
            }
            // Xử lý di chuyển sang phải
            else if (direction.equals("right")) {
                switch (animationFrame) {
                    case 0: image = right1; break;
                    case 1: image = right2; break;
                    case 2: image = right3; break;
                }
            }
        }
//        double scaleFactor = 0.8;  // Tỷ lệ thu nhỏ
//        double newWidth = image.getWidth() * scaleFactor;
//        double newHeight = image.getHeight() * scaleFactor;
        // Vẽ hình ảnh lên Canvas
        gc.drawImage(image,  x, y); // x, y là tọa độ vẽ hình ảnh
    }
    public int getX() {
        return x;
    }



}
