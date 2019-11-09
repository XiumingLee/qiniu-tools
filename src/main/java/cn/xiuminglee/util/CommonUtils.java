package cn.xiuminglee.util;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/11/9 13:22
 * @Version 1.0
 * @Describe: 公共工具类
 */
public class CommonUtils {
    public static String mdToStr(String md){
        return md.substring(4, md.length() - 1);
    }

    public static String getQiniuKey(String imageUrl){
        return imageUrl.replace(QiniuUtil.QINIU_PATH, "");
    }

}
