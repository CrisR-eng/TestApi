package com.example.demo.model

import java.util.*
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table
class Persona(val dni:Long=0, val nombre:String="", val apellido:String="", val fechaNac:Date?=null) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null;
}