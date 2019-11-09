package cn.xiuminglee.component;

import javafx.scene.control.Alert;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/11/9 12:37
 * @Version 1.0
 * @Describe: 弹窗提示组件
 */
public class AlertComponent {
    public static void errorAlert(String errMsg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.titleProperty().set("错误信息");
        alert.headerTextProperty().set(errMsg);
        alert.showAndWait();
    }
}
