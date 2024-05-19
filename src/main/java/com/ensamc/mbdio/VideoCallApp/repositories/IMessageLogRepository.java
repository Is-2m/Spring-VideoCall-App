package com.ensamc.mbdio.VideoCallApp.repositories;

import com.ensamc.mbdio.VideoCallApp.entities.MessageLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMessageLogRepository extends JpaRepository<MessageLog,Long> {
}
