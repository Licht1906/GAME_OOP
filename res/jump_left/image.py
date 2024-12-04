from PIL import Image

# Mở ảnh PNG

image = Image.open(r"C:\Users\ADMIN\Downloads\game2D\res\jump_right\jump1.PNG")

# Lật ảnh theo chiều ngang (như nhìn qua gương)
mirrored_image = image.transpose(Image.FLIP_LEFT_RIGHT)

# Lưu ảnh đã lật
mirrored_image.save("jump1.PNG")

# Hiển thị ảnh đã lật
mirrored_image.show()
