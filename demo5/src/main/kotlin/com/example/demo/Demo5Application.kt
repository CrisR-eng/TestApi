package com.example.demo

import com.example.demo.DAO.PersonaRepository
import com.example.demo.model.Persona
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.time.format.DateTimeFormatter


@SpringBootApplication
class Demo5Application: CommandLineRunner {

    @Autowired
    val personaRepository: PersonaRepository? = null
    override fun run(vararg args: String?) {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val localDate = LocalDate.parse("29-05-1995", formatter)
        val date: Date? = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
        val persona1 = Persona(38732234,"Gaston","Sailen", date)

        personaRepository!!.save(persona1)
    }
}

fun main(args: Array<String>) {
    runApplication<Demo5Application>(*args)
}
