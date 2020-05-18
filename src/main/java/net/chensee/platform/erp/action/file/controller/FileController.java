package net.chensee.platform.erp.action.file.controller;

import io.swagger.annotations.ApiOperation;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ah
 * @title: 文件上传、下载、预览、删除
 * @date 2019/11/11 16:30
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ObjectResponse upload(MultipartFile file) throws Exception {
        return fileService.upload(file);
    }

    @RequestMapping(value = "/{id}/download", method = RequestMethod.GET)
    public void download(@PathVariable String id, HttpServletResponse response) throws Exception {
        fileService.download(response,id);
    }

    @ApiOperation(value = "删除指定上传文件")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ObjectResponse deleteFile(@PathVariable String id) throws Exception {
        return fileService.deleteFile(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void previewFile(@PathVariable String id, HttpServletResponse response) throws Exception {
        fileService.previewFile(id,response);
    }

    @ApiOperation(value = "将上传的临时文件置为永久文件")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ObjectResponse setFileStatus(@PathVariable String id) throws Exception {
        return fileService.setFileStatus(id);
    }
}
