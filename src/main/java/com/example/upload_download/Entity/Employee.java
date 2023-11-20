package com.example.upload_download.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "upload_download")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String profile;
    private long size;
    private byte[] content;
}
