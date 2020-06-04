package cn.xiuminglee;

import cn.xiuminglee.component.GUIComponent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/11/8 8:51
 * @Version 1.0
 * @Describe:
 */
public class QiniuToolApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("七牛云工具");
        stage.getIcons().add(new Image("/m.png"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/qiniutool.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        GUIComponent guiComponent = GUIComponent.getInstance(loader);
        guiComponent.guiBuilder();
        // 设置快捷键
        guiComponent.setShortcuts(scene);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
