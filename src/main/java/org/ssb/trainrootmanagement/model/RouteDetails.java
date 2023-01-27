package org.ssb.trainrootmanagement.model;







import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



import lombok.Data;

@Entity
@Table(name = "route_details")
@Data
public class RouteDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trainScheduleId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Trains train;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Station station;

    private String scheduleTime;

    private int Kilometers;

    private String haltTime;

    private int platformNumber;



}