package com.wakaka.mq.mapper;

import com.wakaka.mq.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Crysmart
 * @date 2021/2/26 16:48
 */
@Repository
public interface TestMapper extends JpaRepository<TestEntity,Long> {
}
