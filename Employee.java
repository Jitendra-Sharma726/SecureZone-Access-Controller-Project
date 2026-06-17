package model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String department;

    // One-to-One
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "rfid_card_id")
    private RfidCard rfidCard;

    // Many-to-Many
    @ManyToMany
    @JoinTable(
            name = "employee_zones",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "zone_id")
    )
    private Set<SecureZone> allowedZones = new HashSet<>();

    public Employee() {}

    public void addZone(SecureZone zone) {
        this.allowedZones.add(zone);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public RfidCard getRfidCard() {
        return rfidCard;
    }

    public void setRfidCard(RfidCard rfidCard) {
        this.rfidCard = rfidCard;
    }

    public Set<SecureZone> getAllowedZones() {
        return allowedZones;
    }

    public void setAllowedZones(Set<SecureZone> allowedZones) {
        this.allowedZones = allowedZones;
    }
}
