package edu.eci.dosw.TechCup.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private byte[] bytes;
    @Column(nullable = false)
    private String mime;
    @Column(nullable = false)
    private LocalDateTime lastMod;
    public FileEntity() {}
    public FileEntity(byte[] bytes, String mime, LocalDateTime lastMod) {
        this.bytes = bytes;
        this.mime = mime;
        this.lastMod = lastMod;
    }
    public FileEntity(byte[] bytes, String mime) {
        this.bytes = bytes;
        this.mime = mime;
        this.lastMod = LocalDateTime.now();
    }
    public LocalDateTime getLastMod() {
        return lastMod;
    }
    public byte[] getBytes() {
        return bytes;
    }
    public String getMime() {
        return mime;
    }
    public void setBytes(byte[] bytes, String mime) {
        this.bytes = bytes;
        this.mime = mime;
    }
}
