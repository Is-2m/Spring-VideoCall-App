package com.ensamc.mbdio.VideoCallApp.repositories;

import com.ensamc.mbdio.VideoCallApp.entities.Chat;
import com.ensamc.mbdio.VideoCallApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface IChatRepository extends JpaRepository<Chat,Long> {
    @Query(value = "SELECT * FROM chat c WHERE c.id_sender = :userId OR c.id_receiver = :userId",nativeQuery = true)
    List<Chat> findChatsByUserId(@Param("userId") Long userId);

    Chat findBySenderEqualsAndReceiverEquals(User sender, User receiver);
}
