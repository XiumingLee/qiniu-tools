package cn.xiuminglee.component;

import cn.xiuminglee.controller.QiniuToolController;
import cn.xiuminglee.util.QiniuUtil;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/11/8 13:41
 * @Version 1.0
 * @Describe:
 */
public class GUIComponent {
    private static final GUIComponent guiComponent = new GUIComponent();
    private static FXMLLoader loader;
    private static QiniuToolController controller;
    private static final Clipboard clipboard = Clipboard.getSystemClipboard();


    public static GUIComponent getInstance(FXMLLoader fxmlLoader){
        loader = fxmlLoader;
        controller = loader.getController();
        return  guiComponent;
    }

    public void guiBuilder(){
        ImageView imageView = controller.getImageView();
        Image image = new Image(getClass().getClassLoader().getResource("area.jpg").toString());
        imageView.setImage(image);
    }

    /**
     * 设置快捷键
     */
    public void setShortcuts(Scene scene){
        KeyCombination keyCombination = new KeyCodeCombination(KeyCode.V,KeyCombination.CONTROL_DOWN,KeyCombination.SHIFT_DOWN);
        scene.getAccelerators().put(keyCombination, new Runnable() {
            @Override
            public void run() {
                if (clipboard.hasFiles()){ // 复制本地的图片。如果是文件只取最后一个文件
                    List<File> files = clipboard.getFiles();
                    File file = files.get(files.size() - 1);
                    String fileName = file.getName();
                    String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
                    byte[] bytes = new byte[1024];
                    try {
                        bytes = Files.readAllBytes(file.toPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String imageUrl = QiniuUtil.upload(bytes, fileExtName);
                    System.out.println("File：图片地址：" + imageUrl);
                    // 更新页面图片和图片地址并将图片地址添加到粘贴板
                    updateUI(imageUrl);
                }
                if (clipboard.hasImage()){ // 页面复制的图片
                    Image image = clipboard.getImage();
                    BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
                    ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
                    try {
                        // 如果是页面右键复制图片都会转成png格式，如果是gif格式的图片最好保存到本地再复制上传。
                        ImageIO.write(bufferedImage, "png", byteArrayInputStream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    byte[] bytes = byteArrayInputStream.toByteArray();
                    String imageUrl = QiniuUtil.upload(bytes, "png");
                    System.out.println("image：图片地址：" + imageUrl);
                    // 更新页面图片和图片地址并将图片地址添加到粘贴板
                    updateUI(imageUrl);
                }

            }
        });
    }

    private void updateUI(String imageUrl){
        String md = "![](" + imageUrl + ")";
        ImageView imageView = controller.getImageView();
        TextField textField = controller.getTextField();
        Image image = new Image(imageUrl);
        imageView.setImage(image);
        textField.setText(md);
        // 把文本内容设置到系统剪贴板
        Map<DataFormat, Object> content = new HashMap<>();
        content.put(DataFormat.PLAIN_TEXT,md);
        clipboard.setContent(content);
    }


    private GUIComponent(){};
}
