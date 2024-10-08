package com.bangbang.repository;

import com.bangbang.domain.Heritage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeritageRepository extends JpaRepository<Heritage, Long> {

    List<Heritage> findByNameIn(List<String> heritages);

}
