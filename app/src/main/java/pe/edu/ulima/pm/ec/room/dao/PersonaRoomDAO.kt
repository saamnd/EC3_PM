package pe.edu.ulima.pm.ec.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pe.edu.ulima.pm.ec.models.beans.Persona
import pe.edu.ulima.pm.ec.room.models.DepartamentoRoom
import pe.edu.ulima.pm.ec.room.models.FechasRoom
import pe.edu.ulima.pm.ec.room.models.PersonaRoom

@Dao
interface PersonaRoomDAO {
    @Query("SELECT * FROM PersonaRoom")
    fun getAll() : List<PersonaRoom>

    @Query("SELECT * FROM PersonaRoom WHERE id=:id")
    fun findById(id : Int) : PersonaRoom

    @Query("SELECT departamento, count(*) as cantidad FROM PersonaRoom WHERE fecha_res=:fecha_res GROUP BY DEPARTAMENTO")
    fun PorDepa(fecha_res:String) : List<DepartamentoRoom>
/*
    @Query("SELECT DISTINCT fecha_corte FROM PersonaRoom")
    fun ObtenerFechas(fecha_corte:String) : List<FechasRoom>*/

    @Insert
    fun insert(persona : PersonaRoom)

    @Delete
    fun delete(persona: PersonaRoom)
}