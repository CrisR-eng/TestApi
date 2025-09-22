package com.example.demo.business

import com.example.demo.DAO.PersonaRepository
import com.example.demo.exception.BusinessException
import com.example.demo.exception.NotFoundException
import com.example.demo.model.Persona
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class PersonaBusiness: IPersonaBusiness {

    @Autowired
    val personaRepository: PersonaRepository?=null

    @Throws(BusinessException::class)
    override fun list(): List<Persona> {
        try{
            return personaRepository!!.findAll()
        }catch (ex:Exception){
            throw BusinessException(ex.message)
        }
        //return TODO("Provide the return value")
    }

    @Throws(BusinessException::class)
    override fun load(idPersona: Long): Persona {
        val op: Optional<Persona>
        try {
            op=personaRepository!!.findById(idPersona)
        }catch (ex:Exception){
            throw BusinessException(ex.message)
        }

        if (!op.isPresent){
            throw NotFoundException("Persona not found id $idPersona")
        }
        return op.get()
    }

    @Throws(BusinessException::class)
    override fun save(persona: Persona): Persona {
        try {
            return personaRepository!!.save(persona)
        }catch (ex:Exception){
            throw BusinessException(ex.message)
        }
    }

    @Throws(BusinessException::class)
    override fun remove(idPersona: Long) {
        val op: Optional<Persona>
        try {
            op = personaRepository!!.findById(idPersona)
        }catch (ex:Exception){
            throw BusinessException(ex.message)
        }

        if (!op.isPresent){
            throw NotFoundException("Persona not found id $idPersona")
        }else{
            try {
                personaRepository!!.deleteById(idPersona)
            }catch (ex:Exception){
                throw BusinessException(ex.message)
            }
        }
    }



}