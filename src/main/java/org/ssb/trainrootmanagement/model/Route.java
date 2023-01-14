package org.ssb.trainrootmanagement.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;

@Entity
@Table(name = "route")
@Data
public class Route {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int routeId;

  private String routeName;

  @Column(nullable = false)
  private String source;

  @Column(nullable = false)
  private String destination;


  @OneToMany(mappedBy = "route")
  @JsonIgnore
  private List<Train> train;

}