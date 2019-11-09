package cn.xiuminglee.controller;

import cn.xiuminglee.component.AlertComponent;
import cn.xiuminglee.util.CommonUtils;
import cn.xiuminglee.util.QiniuUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/11/8 10:13
 * @Version 1.0
 * @Describe:
 */
public class QiniuToolController {

    @FXML
    private ImageView imageView;
    @FXML
    private TextField textField;
    @FXML
    private Button delButton;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        String textValue = textField.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认消息");
        alert.setHeaderText("你确定删除此图片吗");
        alert.setContentText("图片地址：" + textValue);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            delImage(textValue);
        } else {
            // ... 不删除
        }
    }

    public void initialize() {
        // 初始化时调用
        delButton.getStyleClass().setAll("btn","btn-primary","btn-lg");
    }


    public ImageView getImageView() {
        return imageView;
    }

    public TextField getTextField() {
        return textField;
    }

    public Button getDelButton() {
        return delButton;
    }

    private void delImage(String imageMd){
        String pattern = "^!\\[(.*)\\]\\((.*)\\)$";
        if(Pattern.matches(pattern, imageMd)){
            String imageUrl = CommonUtils.mdToStr(imageMd);
            String qiniuKey = CommonUtils.getQiniuKey(imageUrl);
            QiniuUtil.deleteByKey(qiniuKey);
            // 刷新页面。
            Image image = new Image(getClass().getClassLoader().getResource("area.jpg").toString());
            imageView.setImage(image);
            textField.setText("图片地址");
        }else {
            AlertComponent.errorAlert("文本框的内容必须是MD格式！");
        }
    }

}
