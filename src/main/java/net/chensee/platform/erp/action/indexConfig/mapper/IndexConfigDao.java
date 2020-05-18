package net.chensee.platform.erp.action.indexConfig.mapper;

import net.chensee.platform.erp.action.indexConfig.po.IndexConfigPo;
import net.chensee.platform.erp.action.indexConfig.po.IndexInfoPo;
import net.chensee.platform.erp.action.indexConfig.po.UserToIndexPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author ah
 * @title: IndexConfigDao
 * @date 2019/12/5 11:42
 */
@Repository
public interface IndexConfigDao {

    void saveIndexInfoPo(IndexInfoPo indexInfoPo);

    void saveIndexConfigPo(IndexConfigPo indexConfigPo);

    /**
     * 保存用户对应的首页
     *
     * @param userToIndexPo
     * @return
     */
    void insertUserToIndexPo(UserToIndexPo userToIndexPo);

    /**
     * 修改用户对应的首页
     *
     * @param userToIndexPo
     */
    void updateUserToIndexPo(UserToIndexPo userToIndexPo);

    void delIndexInfo(@Param(value = "indexId") Long indexId);

    void delIndexConfigPo(@Param(value = "indexId") Long indexId);

    List<IndexConfigPo> getIndexConfigPosByIndexId(@Param(value = "indexId") Long indexId);

    /**
     * 得到用户对应的首页
     * @param userId
     * @return
     */
    UserToIndexPo getUserIndex(@Param(value = "userId") Long userId);

    /**
     * 通过首页id获取首页信息
     *
     * @param indexId
     * @return
     */
    IndexInfoPo getIndexInfoPoByIndexId(@Param(value = "indexId") Long indexId);

    /**
     * 通过用户ID获得首页信息
     *
     * @param userId
     * @return
     */
    List<IndexInfoPo> getIndexInfosByUser(@Param(value = "userId") Long userId);

    /**
     * 通过用户ID,用户所在角色,用户所在部门获得最近的一条首页信息
     *
     * @param userId
     * @param roleIds
     * @param deptIds
     * @return
     */
    IndexInfoPo getUserIndexConfig(@Param(value = "userId") Long userId,
                                   @Param(value = "roleIds") Set<Long> roleIds,
                                   @Param(value = "deptIds") Set<Long> deptIds);

    /**
     * 通过角色id集合获得首页信息
     *
     * @param roleIds
     * @return
     */
    List<IndexInfoPo> getIndexInfosByRole(@Param(value = "roleIds") Set<Long> roleIds);

    /**
     * 通过部门id集合获得首页信息
     *
     * @param deptIds
     * @return
     */
    List<IndexInfoPo> getIndexInfosByDept(@Param(value = "deptIds") Set<Long> deptIds);


    List<IndexInfoPo> getIndexInfoPage(@Param(value = "pageNumber") Integer pageNumber,
                                       @Param(value = "pageSize") Integer pageSize);

    Integer getIndexInfoCount();
}
