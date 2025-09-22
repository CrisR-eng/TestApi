package com.example.demo.web

import com.example.demo.business.PersonaBusiness
import com.example.demo.exception.BusinessException
import com.example.demo.exception.NotFoundException
import com.example.demo.model.Persona
import com.example.demo.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Constants.URL_BASE_PERSONAS)
class PersonaRestController {

    @Autowired
    val personaBusiness: PersonaBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Persona>>{
        return try {
            ResponseEntity(personaBusiness!!.list(), HttpStatus.OK)
        }catch (ex: Exception){
            ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun load(@PathVariable("id") id: Long): ResponseEntity<Persona>{
        return try {
            ResponseEntity(personaBusiness!!.load(id), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(null, HttpStatus.NOT_FOUND)
        }
    }


    @PostMapping()
    fun insert (@RequestBody persona: Persona): ResponseEntity<Persona>{
        return try {
            personaBusiness!!.save(persona)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_PERSONAS+"/"+persona.id)
            ResponseEntity(responseHeader, HttpStatus.CREATED)
        }catch (e: BusinessException){
            ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update (@RequestBody persona: Persona): ResponseEntity<Persona>{
        return try {
            personaBusiness!!.save(persona)
            ResponseEntity(null, HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Any>{
        return try{
            personaBusiness!!.remove(id)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(null, HttpStatus.NOT_FOUND)
        }
    }

}