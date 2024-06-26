package com.example.setup.object;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="task")
@NoArgsConstructor
@Setter
@Getter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Boolean status;

    private Long userId;

    public Task(String title, Boolean status,Long userId) {
        this.title = title;
        this.status = status;
        this.userId = userId;
    }
}
