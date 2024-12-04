package entity;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandlers {

    public boolean leftPressed, rightPressed, jPressed, kPressed, wPressed;

    // Phương thức để xử lý sự kiện khi một phím được nhấn
    public void handleKeyPressed(KeyEvent e) {
        KeyCode code = e.getCode();

        if (code == KeyCode.W) {
            wPressed = true;
        }
        if (code == KeyCode.A) {
            leftPressed = true;
        }
        if (code == KeyCode.D) {
            rightPressed = true;
        }
        if (code == KeyCode.S) {
            jPressed = true;
        }
        if (code == KeyCode.K) {
            kPressed = true;
        }
    }

    // Phương thức để xử lý sự kiện khi một phím được thả ra
    public void handleKeyReleased(KeyEvent e) {
        KeyCode code = e.getCode();

        if (code == KeyCode.W) {
            wPressed = false;
        }
        if (code == KeyCode.A) {
            leftPressed = false;
        }
        if (code == KeyCode.D) {
            rightPressed = false;
        }
        if (code == KeyCode.S) {
            jPressed = false;
        }
        if (code == KeyCode.K) {
            kPressed = false;
        }
    }
}
