package com.ensamc.mbdio.VideoCallApp.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_caller")
    private User caller;

    @ManyToOne
    @JoinColumn(name = "id_receiver")
    private User receiver;

    private Long date;

    private int duration;

    private CallState state;

}