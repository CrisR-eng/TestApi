package com.example.demo.DAO

import com.example.demo.model.Persona
import org.springframework.data.jpa.repository.JpaRepository

interface PersonaRepository: JpaRepository<Persona, Long> {
    fun id(id: Long): MutableList<Persona>
}