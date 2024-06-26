package com.ensamc.mbdio.VideoCallApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor public class MessageLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "id_chat")
    @JsonBackReference
    private Chat chat;

    private long id_sender;

    private long id_receiver;


    private String content;

    private Long date;

}

