package net.chensee.platform.erp.action.file.mapper;

import net.chensee.platform.erp.action.file.po.FileInfoPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ah
 * @title: FileDao
 * @date 2019/11/11 10:34
 */
@Repository
public interface FileDao {

    FileInfoPo isExistFile(@Param(value = "document") String document);

    List<FileInfoPo> isExistDocs(@Param(value = "id") String id);

    void saveFileInfo(FileInfoPo fileInfoPo);

    FileInfoPo getFileInfo(@Param(value = "id") String id);

    void deleteFile(@Param(value = "id") String id);

    void removeFileByOpr(@Param(value = "id") String id,
                         @Param(value = "opr") Integer opr);

    void setFileStatus(@Param(value = "id") String id,
                       @Param(value = "status") Integer status);
}
