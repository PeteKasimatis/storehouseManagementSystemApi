package com.pete.storehouseApp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "exit_receipt")
public class ExitReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "date_of_exit")
    private Date dateOfExit;

    @Column(name = "reason_for_exit")
    private String reasonForExit;

    @Column(name = "sender")
    private String sender;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "exitReceipt")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<ExitRegistration> exitRegistrations;

    public ExitReceipt() {
    }

    public ExitReceipt(Date dateOfExit, String reasonForExit, String sender) {
        this.dateOfExit = dateOfExit;
        this.reasonForExit = reasonForExit;
        this.sender = sender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfExit() {
        return dateOfExit;
    }

    public void setDateOfExit(Date dateOfExit) {
        this.dateOfExit = dateOfExit;
    }

    public String getReasonForExit() {
        return reasonForExit;
    }

    public void setReasonForExit(String reasonForExit) {
        this.reasonForExit = reasonForExit;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<ExitRegistration> getExitRegistrations() {
        return exitRegistrations;
    }

    public void setExitRegistrations(List<ExitRegistration> exitRegistrations) {
        this.exitRegistrations = exitRegistrations;
    }

    @Override
    public String toString() {
        return "ExitReceipt{" +
                "id=" + id +
                ", dateOfExit=" + dateOfExit +
                ", reasonForExit='" + reasonForExit + '\'' +
                ", sender='" + sender + '\'' +
                '}';
    }
}
