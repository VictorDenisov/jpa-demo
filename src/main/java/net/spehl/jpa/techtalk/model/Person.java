package net.spehl.jpa.techtalk.model;

import net.spehl.jpa.techtalk.hibernate.JsonbType;

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

    @Column(nullable = false, name="tenant_id")
    @org.hibernate.annotations.Type(type="pg-uuid")
    private UUID tenantId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
    @org.hibernate.annotations.Type(type="JsonbType")
    private String json;

    public Person() {}

    public Person(String name,
                  Double balance,
                  UUID tenantId,
                  String json) {
        this.name = name;
        this.balance = balance;
        this.tenantId = tenantId;
        this.json = json;
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

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", tenantId=" + tenantId +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", json='" + json + '\'' +
                '}';
    }
}


