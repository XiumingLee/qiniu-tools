package cn.xiuminglee.util;

import cn.xiuminglee.component.AlertComponent;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/11/8 15:49
 * @Version 1.0
 * @Describe: 七牛图片上传和删除工具类。
 */
public class QiniuUtil {
    //设置需要操作的账号的AK和SK
    private static final String ACCESS_KEY = PropertiesUtil.getProperty("access-key");
    private static final String SECRET_KEY = PropertiesUtil.getProperty("secret-key");
    public static final String QINIU_PATH = PropertiesUtil.getProperty("qiniu-path");
    /** 要上传的空间 */
    private static final String BUCKET_NAME = PropertiesUtil.getProperty("bucket-name");

    private static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    private static String upToken = auth.uploadToken(BUCKET_NAME);
    private static Configuration cfg = new Configuration(Region.region1());
    private static UploadManager uploadManager = new UploadManager(cfg);

    /**
     * 普通上传
     * @param bytes  文件字节组
     * @param extName 扩展名，不带点。  jpg  png  gif
     * @return
     */
    public static String upload(byte[] bytes, String extName)  {
        String fileName = System.currentTimeMillis() + (int)(Math.random() * 100) + "." + extName;
        Response response = null;
        DefaultPutRet putRet = null;
        try {
            response = uploadManager.put(bytes, fileName, upToken);
            putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException e) {
            AlertComponent.errorAlert("图片上传失败：" + e.getMessage());
        }
        String key = putRet.key;
        String url = QINIU_PATH + key;
        return url;
    }

    /**
     * 删除文件
     * @param key
     * @return
     */
    public static void deleteByKey(String key)  {
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(BUCKET_NAME, key);
        } catch (QiniuException e) {
            AlertComponent.errorAlert("图片删除失败：" + e.getMessage());
        }
    }
}
