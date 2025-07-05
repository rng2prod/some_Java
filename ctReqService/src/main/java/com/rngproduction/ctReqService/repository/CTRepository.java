package com.rngproduction.ctReqService.repository;

import com.rngproduction.ctReqService.models.CT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CTRepository extends JpaRepository<CT, String> {

    @Modifying
    @Query("update CT t set t.needSend = 0")
    void firstUpdate();

    @Modifying
    @Query(value = "update CT t set t.needSend = 0 where t.id in :ids")
    void setAllNeedSendFalse(@Param("ids") Collection<String> ids);

    @Modifying
    @Query(value = "update CT t set t.needSend = 0 where t.id = :id")
    void setNeedSendFalse(@Param("id") String id);

    @Query("select t from CT t where t.needSend = 1")
    List<CT> findListNeedSend();

    @Query(value = "SELECT CASE WHEN EXISTS (SELECT top 1 1 FROM CTNOTE)\n" +
            "THEN 'true' ELSE 'false' END",
            nativeQuery = true)
    boolean anyExist();

}
