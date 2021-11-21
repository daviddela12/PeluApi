package com.daviddela.peluapi.domain;

import com.daviddela.peluapi.validator.PasswordConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column()
    private String phone;

    @Column()
    @NotBlank(message = "{customer.email.not_blank}" )
    @Email()
    private String email;

    @PasswordConstraint(message = "{customer.password.not_blank}")
    private String password;

    /** con cascade ALL decimos que cuando se borre un customer se borraran todas las Appointment asociadas a ese cliente**/
    /** con mappedBy le decimos que en la clase Customer nos referimos a la propiedad con nombre customer*/
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Appointment> appointmentList;
}
