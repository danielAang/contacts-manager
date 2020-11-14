package br.com.dan.contactsimporter.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
@ToString(of = { "id", "name" })
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "age", length = 2, nullable = true)
    private Integer age;

    @Column(name = "social_id", length = 10, nullable = true)
    private String socialId;

    @Column(name = "cpf", length = 11, nullable = true)
    private String cpf;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @NotNull
    @Column(name = "phone_number", length = 20, nullable = false)
    private String phoneNumber;

    @Column(name = "gender", length = 10, nullable = false)
    private String gender;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.PERSIST)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "import_file_id")
    private ImportFile importFile;



}
