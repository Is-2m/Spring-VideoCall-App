package com.ensamc.mbdio.VideoCallApp.repositories;

import com.ensamc.mbdio.VideoCallApp.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMessageLogRepository extends JpaRepository<Message,Long> {
}
