package com.wkcto.fdfs.springboot.fdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

/**
 * ClassName:FastDFS
 * package:com.wkcto.fdfs
 * Description:
 *
 * @Data:2018/7/31 9:21
 * @Auther:wangxin
 */
public class FastDFS {
    private static final String CONF_FILE="fastdfs_client.conf";
    public  static  TrackerServer trackerServer = null;
    public  static  StorageServer storageServer = null;
    /**
     * 获取storage客户端对象
     * @return
     */
    public static StorageClient getStorageClient() {
        StorageClient storageClient = null;
        try {

            //1.初始化FastDFS的连接信息
            ClientGlobal.init(CONF_FILE);

            //2.创建一个tracker的客户端对象
            TrackerClient trackerClient = new TrackerClient();

            //3.通过tracker客户端对象获取一个tracker服务器对象
            trackerServer = trackerClient.getConnection();

            //4.通过tracker客户端对象和tracker服务器对象获取一个storage服务器对象;
            storageServer = trackerClient.getStoreStorage(trackerServer);

            //5.通过tracker服务器对象和storage服务器对象，获取一个storage的客户端独享
            storageClient = new StorageClient(trackerServer,storageServer);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

        return storageClient;
    }

    /**
     * 关闭FastDFS
     */
    public static void closeFastDFS(){
        try {
            if (storageServer != null) {

                storageServer.close();

            }
            if (trackerServer != null) {
                trackerServer.close();
            }
        }catch (IOException e) {
                e.printStackTrace();
            }
    }


}
