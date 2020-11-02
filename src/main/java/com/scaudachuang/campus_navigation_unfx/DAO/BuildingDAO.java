package com.scaudachuang.campus_navigation_unfx.DAO;

import com.scaudachuang.campus_navigation_unfx.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface BuildingDAO extends JpaRepository<Building,Integer> {

    @Transactional
    @Query(value = "select b.longitude from building b where b.id = ?1")
    double findLongitudeById(int id);

    @Transactional
    @Query(value = "select b.latitude from building b where b.id = ?1")
    double findLatitudeById(int id);
}
