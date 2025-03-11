package com.monit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.monit.model.Notice;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {

    @Query(value = "from Notice n where CURDATE() BETWEEN n.noticBegDt AND n.noticEndDt")
    List<Notice> findAllActiveNotices();

}