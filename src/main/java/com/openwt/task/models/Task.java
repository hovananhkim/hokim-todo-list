package com.openwt.task.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    @NotBlank(message = "Title is mandatory")
    private String title;

    @NonNull
    @NotBlank(message = "Detail is mandatory")
    private String detail;

    private boolean status;
}
