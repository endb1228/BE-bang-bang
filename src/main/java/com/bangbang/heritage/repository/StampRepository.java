package com.bangbang.heritage.repository;

import com.bangbang.heritage.domain.Heritage;
import com.bangbang.heritage.domain.Stamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StampRepository extends JpaRepository<Stamp, Long> {
    List<Stamp> findAllByMemberIdAndHeritageIn(Long userId, List<Heritage> heritages);
}
