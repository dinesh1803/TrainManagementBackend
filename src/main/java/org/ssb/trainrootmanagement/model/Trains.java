package org.ssb.trainrootmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "train_details")
@AllArgsConstructor
@NoArgsConstructor
public class Trains {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Train_number")
    private int trainNumber;

    @Column(name = "Train_name")
    private String trainName;

    @ManyToOne
    private Route route;

}