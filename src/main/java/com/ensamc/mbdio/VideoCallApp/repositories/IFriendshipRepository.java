package com.ensamc.mbdio.VideoCallApp.repositories;

import com.ensamc.mbdio.VideoCallApp.entities.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@EnableJpaRepositories
public interface IFriendshipRepository extends JpaRepository<Friendship,Long> {

    @Query(value = "SELECT * FROM friendship f WHERE (f.id_sender = :userId OR f.id_receiver = :userId) and f.state='ACCEPTED'",nativeQuery = true)
    List<Friendship> findByUserID(@Param("userId") Long id);
}
