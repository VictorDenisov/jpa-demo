package net.spehl.jpa.techtalk.model;

import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Person {

    @Column @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @org.hibernate.annotations.Type(type="pg-uuid")
    private UUID id;

    @Column(nullable = false)
    @org.hibernate.annotations.Type(type="pg-uuid")
    private UUID tenantId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double balance;

    public Person() {}

    public Person(String name,
                  Double balance,
                  UUID tenantId) {
        this.name = name;
        this.balance = balance;
        this.tenantId = tenantId;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public void setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
    }
}

