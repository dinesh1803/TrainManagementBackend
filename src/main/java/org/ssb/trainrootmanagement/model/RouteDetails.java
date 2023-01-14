package org.ssb.trainrootmanagement.model;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Data;

@Entity
@Table(name = "route_details")
@Data
public class RouteDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Train train;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Station station;

    @JsonFormat(pattern = "HH:mm:ss")
    private Time scheduleTime;

}