package com.daviddela.peluapi.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column()
    private String phone;

    /** con cascade ALL decimos que cuando se borre un customer se borraran todas las Appointment asociadas a ese cliente**/
    /** con mappedBy le decimos que en la clase Customer nos referimos a la propiedad con nombre customer*/
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Appointment> appointmentList;
}
