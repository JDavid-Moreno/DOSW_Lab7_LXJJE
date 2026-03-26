package edu.eci.dosw.TechCup.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class File {
    private Long id;
    private byte[] bytes;
    private String mime;
    private LocalDateTime lastMod;
    public File() {}
    public File(byte[] bytes, String mime, LocalDateTime lastMod) {
        this.bytes = bytes;
        this.mime = mime;
        this.lastMod = lastMod;
    }
    public File(byte[] bytes, String mime) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public void setLastMod(LocalDateTime lastMod) {
        this.lastMod = lastMod;
    }
}
