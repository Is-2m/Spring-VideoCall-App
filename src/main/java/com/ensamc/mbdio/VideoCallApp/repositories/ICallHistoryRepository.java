package com.ensamc.mbdio.VideoCallApp.repositories;

import com.ensamc.mbdio.VideoCallApp.entities.CallHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICallHistoryRepository extends JpaRepository<CallHistory,Long> {
}
