package com.banking.application.repository;


import com.banking.application.entity.User;
import com.banking.application.entity.UserBehaviour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserBehaviouRepository extends JpaRepository<UserBehaviour, Integer> {
    @Transactional @Modifying @Query("update UserBehaviour u set u.count = ?1 where u.id = ?2")
    int updateCountById(int count, Integer id);
    List<UserBehaviour> findByUserAndDayAndMonthAndYear(User user, int day, int month, int year);
}