package fidanhykolli.u5d9friday.entities;

import jakarta.persistence.*;

@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAssignedTo(Worker assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public Worker getAssignedTo() {
        return assignedTo;
    }

    private String status;

    public Device(long id, String name, String status, Worker assignedTo) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.assignedTo = assignedTo;
    }

    @ManyToOne
    @JoinColumn (name = "worker_id")
    private Worker assignedTo;
}
