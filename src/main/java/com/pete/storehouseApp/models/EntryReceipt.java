package com.pete.storehouseApp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "entry_receipt")
public class EntryReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "date_of_entry")
    private Date dateOfEntry;

    @Column(name = "description")
    private String description;

    @Column(name = "recipient")
    private String recipient;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entryReceipt")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<EntryRegistration> entryRegistrations;

    public EntryReceipt(){

    }

    public EntryReceipt(Date dateOfEntry, String description, String recipient) {
        this.dateOfEntry = dateOfEntry;
        this.description = description;
        this.recipient = recipient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(Date dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public List<EntryRegistration> getEntryRegistrations() {
        return entryRegistrations;
    }

    public void setEntryRegistrations(List<EntryRegistration> entryRegistrations) {
        this.entryRegistrations = entryRegistrations;
    }

    @Override
    public String toString() {
        return "EntryReceipt{" +
                "id=" + id +
                ", dateOfEntry=" + dateOfEntry +
                ", description='" + description + '\'' +
                ", recipient='" + recipient + '\'' +
                '}';
    }
}
