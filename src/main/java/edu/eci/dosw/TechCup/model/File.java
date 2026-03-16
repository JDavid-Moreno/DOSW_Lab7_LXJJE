package edu.eci.dosw.TechCup.model;

import java.time.LocalDate;

public class File {
    private Long id;
    private byte[] bytes;
    private String mime;
    private LocalDate lastMod;
    public File() {}
    public File(byte[] bytes, String mime, LocalDate lastMod) {
        this.bytes = bytes;
        this.mime = mime;
        this.lastMod = lastMod;
    }
    public File(byte[] bytes, String mime) {
        this.bytes = bytes;
        this.mime = mime;
        this.lastMod = LocalDate.now();
    }
    public LocalDate getLastMod() {
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
