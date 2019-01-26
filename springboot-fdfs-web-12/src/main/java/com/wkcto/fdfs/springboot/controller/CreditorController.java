package com.wkcto.fdfs.springboot.controller;

import com.sun.corba.se.spi.orbutil.fsm.FSM;
import com.wkcto.fdfs.springboot.fdfs.FastDFS;
import com.wkcto.fdfs.springboot.model.Creditor_info;
import com.wkcto.fdfs.springboot.service.CreditorService;
import org.csource.common.MyException;
import org.csource.fastdfs.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * ClassName:CreditorController
 * package:com.wkcto.fdfs.springboot.controller
 * Description:
 *
 * @Data:2018/8/1 8:27
 * @Auther:wangxin
 */
@Controller
public class CreditorController {

    @Value("${contract_url_prefix}")
    private String contractURLPrefix;

    @Autowired
    private CreditorService creditorService;

    /**
     * 债券信息列表
     * @param model
     * @return
     */
    @GetMapping("/fdfs/creditors")
    public String Creditors(Model model){
        List<Creditor_info> creditorInfoList = creditorService.queryCreditors();
        model.addAttribute("creditorList",creditorInfoList);

        return "creditors";
    }

    /**
     * 跳转至上传页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/fdfs/creditor/{id}")
    public String toUpload(Model model, @PathVariable("id") Integer id){
        Creditor_info creditorInfo = creditorService.queryCreditorByKey(id);
        model.addAttribute("creditorInfo",creditorInfo);
        return "creditor";
    }

    @PostMapping("/fdfs/creditor")
    public @ResponseBody  Object uploadFile(@RequestParam("id") Integer id, @RequestParam("fileContract") MultipartFile file){
        int result = 1; //1失败，0成功
        try {
            //文件的字节数组
            byte[] fileBytes = file.getBytes();
            //获取文件扩展名
            String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            //获取storage客户端对象并上传文件
            StorageClient storageClient = FastDFS.getStorageClient();
            String[] strArray = storageClient.upload_file(fileBytes, fileExt, null);

            //更新合同路径
            if(strArray != null && strArray.length == 2){
                Creditor_info creditorInfo = new Creditor_info();
                creditorInfo.setId(id);
                creditorInfo.setGroupname(strArray[0]);
                creditorInfo.setRemotefilename(strArray[1]);
                creditorInfo.setContracturl(contractURLPrefix + strArray[0] +"/" + strArray[1]);
                int update = creditorService.updateCreditorInfo(creditorInfo);
                if(update > 0 ){
                    result = 0;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }catch (MyException e){
            e.printStackTrace();
        }

        return  "<script>window.parent.upload('"+result+"');</script>";
    }

    @GetMapping("/fdfs/creditor/{id}/contract")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") Integer id){
        ResponseEntity<byte[]> responseEntity = null;
        Creditor_info creditorInfo = creditorService.queryCreditorByKey(id);

        String fileExt = creditorInfo.getRemotefilename().substring(creditorInfo.getRemotefilename().lastIndexOf("."));

        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeader.setContentDispositionFormData("atattachment",System.currentTimeMillis() + fileExt);

        try {
            byte[] file = FastDFS.getStorageClient().download_file(creditorInfo.getGroupname(), creditorInfo.getRemotefilename());
            responseEntity = new ResponseEntity<byte[]>(file,httpHeader, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

    return responseEntity;
    }

    @DeleteMapping("fdfs/creditor/{id}")
    public String deleteFile(@PathVariable("id") Integer id){
        int delete = creditorService.deleteCreditor(id);

        return "redirect:/fdfs/creditors";
    }
}
