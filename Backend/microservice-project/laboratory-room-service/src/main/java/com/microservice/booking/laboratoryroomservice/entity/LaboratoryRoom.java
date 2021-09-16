package com.microservice.booking.laboratoryroomservice.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaboratoryRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(0)
    private int floor;

    @NotBlank
    @Size(min = 2, max = 30)
    private String roomSupervisor;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name="LR_ID")
    private List<Desk> desks = new ArrayList<>();

    @Override
    public String toString() {
        return "LaboratoryRoom{" +
                "id=" + id +
                ", floor=" + floor +
                ", roomSupervisor='" + roomSupervisor + '\'' +
                ", desks=" + desks +
                '}';
    }
}
