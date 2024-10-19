package com.comu.comunity.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.awt.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "friend")
public class Friend extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String status;
}
