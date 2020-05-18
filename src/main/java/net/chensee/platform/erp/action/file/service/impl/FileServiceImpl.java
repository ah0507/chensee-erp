package net.chensee.platform.erp.action.file.service.impl;

import net.chensee.base.common.ObjectResponse;
import net.chensee.base.exception.BusinessRuntimeException;
import net.chensee.base.utils.UserUtil;
import net.chensee.platform.erp.action.file.mapper.FileDao;
import net.chensee.platform.erp.action.file.po.FileConfig;
import net.chensee.platform.erp.action.file.po.FileInfoPo;
import net.chensee.platform.erp.action.file.service.FileService;
import net.chensee.platform.erp.utils.DateUtil;
import net.chensee.platform.erp.utils.MD5Builder;
import net.chensee.platform.erp.utils.RandomUUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * @author ah
 * @title: FileService
 * @date 2019/11/11 9:33
 */
@Service
public class FileServiceImpl implements FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private FileDao fileDao;

    @Autowired
    private FileConfig fileConfig;

    @Override
    public FileInfoPo getFileInfo(String id) {
        return fileDao.getFileInfo(id);
    }

    @Override
    public ObjectResponse upload(MultipartFile uploadFile){
        try {
            if (uploadFile.isEmpty()) {
                throw new BusinessRuntimeException(501, "文件不能为空！");
            }

            String originalName = uploadFile.getOriginalFilename();
            String document = this.getFileDoc(uploadFile);
            FileInfoPo fileInfoPo = fileDao.isExistFile(document);
            String path;
            if (fileInfoPo == null) {
                path = this.getDateRule() + originalName;
                File file = new File(fileConfig.getUrl() + path);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                }
                uploadFile.transferTo(file);
            }else{
                path = fileInfoPo.getPath();
            }

            String id = RandomUUIDUtil.randomUUID();
            String type = originalName.substring(originalName.lastIndexOf(".") + 1).toLowerCase();
            String fileType = fileConfig.getFormat().get(type);
            FileInfoPo fileInfo = new FileInfoPo();
            fileInfo.setId(id);
            fileInfo.setUserId(UserUtil.getCurrentUser().getId());
            fileInfo.setOriginalName(originalName);
            fileInfo.setFileType(fileType);
            fileInfo.setPath(path);
            fileInfo.setDocument(document);
            fileInfo.setStatus(0);
            fileInfo.setOpr(1);
            fileInfo.setCreateTime(new Date());
            fileInfo.setCreateBy(UserUtil.getCurrentUser().getId());
            fileDao.saveFileInfo(fileInfo);
            return ObjectResponse.ok(id);
        } catch (Exception e) {
            logger.error("文件上传发生异常",e);
            return ObjectResponse.fail("文件上传发生异常");
        }
    }

    private String getDateRule() {
        return "/" + DateUtil.getYear() + "/" + DateUtil.getMonth() + "/" + DateUtil.getDay() + "/";
    }

    @Override
    public void download(HttpServletResponse response, String id) throws Exception {
        FileInfoPo fileInfoPo = this.getFileInfo(id);
        if (fileInfoPo == null) {
            return;
        }
        response.reset();
        response.setContentType(fileInfoPo.getFileType());
        // 设置下载文件
        response.setHeader("Content-Disposition", "attachment;filename=" + fileInfoPo.getOriginalName());
        response.setCharacterEncoding("utf-8");
        this.respFileIo(response,fileInfoPo);
    }

    private void respFileIo(HttpServletResponse response,FileInfoPo fileInfoPo) throws IOException {
        String filePath = fileConfig.getUrl() + fileInfoPo.getPath();
        File loadFile = new File(filePath);
        response.setContentLength((int) loadFile.length());
        OutputStream os = response.getOutputStream();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(loadFile));
        byte[] buff = new byte[1024];
        int i = 0;
        while ((i = bis.read(buff)) != -1) {
            os.write(buff, 0, i);
            os.flush();
        }
        os.close();
        bis.close();
    }

    @Override
    public ObjectResponse deleteFile(String id) throws Exception{
        try {
            List<FileInfoPo> fileInfoPos = fileDao.isExistDocs(id);
            if (fileInfoPos.size() == 0) {
                return ObjectResponse.fail("文件不存在！");
            }
            String path = fileConfig.getUrl() + fileInfoPos.get(0).getPath();
            File file = new File(path);
            if (fileInfoPos.size() == 1) {
                if (file.exists()) {
                    file.delete();
                }else{
                    return ObjectResponse.fail("文件不存在！");
                }
            }
            fileDao.removeFileByOpr(id,0);
            return ObjectResponse.ok();
        } catch (Exception e) {
            logger.error("删除文件发生异常",e);
            return ObjectResponse.fail("删除文件发生异常");
        }
    }

    @Override
    public void previewFile(String id, HttpServletResponse response) throws IOException {
        FileInfoPo fileInfo = this.getFileInfo(id);
        if (fileInfo == null) {
            return;
        }
        String fileType = fileInfo.getFileType();
        response.setContentType("text/html; charset=UTF-8");
        response.setContentType(fileType);
        List<String> view = fileConfig.getView();
        if (fileType.equals(view.get(0))) {
            response.setHeader("Content-Disposition", "attachment;filename=" + fileInfo.getOriginalName());
        }
        this.respFileIo(response,fileInfo);
    }

    @Override
    public ObjectResponse setFileStatus(String id) throws Exception {
        fileDao.setFileStatus(id,1);
        return ObjectResponse.ok();
    }

    private String getFileDoc(MultipartFile uploadFile) throws Exception {
        InputStreamReader isr = new InputStreamReader(uploadFile.getInputStream());
        BufferedReader br = new BufferedReader(isr);
        StringBuffer sbr = new StringBuffer();
        String line = "";
        while ((line = br.readLine()) != null) {
            sbr.append(line);
        }
        String content = new String(sbr);
        isr.close();
        br.close();
        return MD5Builder.getMD5(content);
    }
}
