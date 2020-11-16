package br.com.dan.contactsimporter.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
@ToString(of = { "id", "street" })
@EqualsAndHashCode(of = { "id" })
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "state", length = 300)
    private String state;

    @Column(name = "city", length = 300)
    private String city;

    @Column(name = "street", length = 200, nullable = true)
    private String street;

    @Column(name = "number", nullable = true)
    private Long number;

    @Column(name = "zip_code", length = 10, nullable = false)
    private String zipCode;

    @Column(name = "district", length = 100, nullable = true)
    private String district;

    @Builder.Default
    @Column(name = "updated", columnDefinition = "boolean default false")
    private Boolean updated = Boolean.FALSE;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;

}
