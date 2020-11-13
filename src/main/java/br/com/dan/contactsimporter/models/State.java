package br.com.dan.contactsimporter.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "state")
@ToString(of = { "id", "shortName" })
public class State implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "short_name", length = 2, nullable = false)
    private String shortName;
    
    @Column(name = "search_name", length = 200, nullable = false)
    private String searchName;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY)
    private Set<City> cities = new HashSet<City>();
    
}
