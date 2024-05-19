package com.ensamc.mbdio.VideoCallApp.repositories;

import com.ensamc.mbdio.VideoCallApp.entities.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFriendshipRepository extends JpaRepository<Friendship,Long> {
}
