package models;

import javax.enterprise.inject.Model;
import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Embeddable
public class Common {
    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean status;

    @Column(name = "date_added", nullable = false, updatable=false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Temporal(value = TemporalType.TIMESTAMP)
    private java.util.Date dateAdded;

    @Column(name = "date_modified", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Temporal(value = TemporalType.TIMESTAMP)
    private java.util.Date dateModified;

    @Transient
    private final String datePattern = "dd MMMM yyyy";

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public String status(){
        return this.status ? "Active" : "Inactive";
    }

    public String creationDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.datePattern);
        return simpleDateFormat.format(this.getDateAdded());
    }

    public String modifiedDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.datePattern);
        return simpleDateFormat.format(this.getDateModified());
    }
}
