package net.chensee.platform.erp.action.file.service;

import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.file.po.FileInfoPo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ah
 * @title: FileService
 * @date 2019/11/11 9:33
 */
public interface FileService {

    FileInfoPo getFileInfo(String id);

    ObjectResponse upload(MultipartFile uploadFile) throws Exception;

    void download(HttpServletResponse response, String fileName) throws Exception;

    ObjectResponse deleteFile(String id) throws Exception;

    void previewFile(String id, HttpServletResponse response) throws IOException;

    ObjectResponse setFileStatus(String id) throws Exception;
}
