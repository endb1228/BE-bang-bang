package com.bangbang.heritage.repository;

import com.bangbang.heritage.domain.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StampRepository extends JpaRepository<Stamp, Long> {
}
