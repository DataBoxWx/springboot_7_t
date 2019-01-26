package com.wkcto.fdfs;

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
    public static void main(String[] args) {
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
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
            StorageClient storageClient = new StorageClient(trackerServer,storageServer);

            //操作fastdfs文件系统
            //文件上传
            String[] strings = storageClient.upload_file("e://TEST/Test5.java", "java", null);
            for (String s:strings) {
                System.out.println("上传结果：" + s);
            }

            //文件下载
//            int down = storageClient.download_file(strings[0], strings[1], "e://seven/My.eml");
//            System.out.println("文件下载：" + down);

            //文件删除
//            int delete1 = storageClient.delete_file("group1","M00/00/00/wKgKgVtfxqSAdcgpAAABX8awd3o168.eml");
//            int delete2 = storageClient.delete_file("group1","M00/00/00/wKgKgVtfzfmAQw7EAAABX8awd3o859.eml");
//            int delete3 = storageClient.delete_file("group1","M00/00/00/wKgKgVtfzkuAXtCLAAABX8awd3o744.eml");
//            System.out.println("删除1：" + delete1 + "删除2：" + delete2 + "删除3：" + delete3);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }finally {
            try {
                if(storageServer != null){
                    storageServer.close();
                }
                if(trackerServer != null){
                    trackerServer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
