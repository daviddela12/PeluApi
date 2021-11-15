package com.daviddela.peluapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Date appointmentDate;

    /** con el JoinColumn le decimos el nombre que queremos que tenga la clave foranea en la tabla. En este caso customer_id"**/
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
