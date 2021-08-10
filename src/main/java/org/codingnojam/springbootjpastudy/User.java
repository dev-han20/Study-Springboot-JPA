package org.codingnojam.springbootjpastudy;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @Setter
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

}
