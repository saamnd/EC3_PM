package pe.edu.ulima.pm.swapp.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pe.edu.ulima.pm.swapp.room.models.PersonaRoom

@Dao
interface PersonaRoomDAO {
    @Query("SELECT * FROM PersonaRoom")
    fun getAll() : List<PersonaRoom>

    @Query("SELECT * FROM PersonaRoom WHERE id_persona=:id")
    fun findById(id : Int) : PersonaRoom

    @Insert
    fun insert(persona : PersonaRoom)

    @Delete
    fun delete(persona: PersonaRoom)
}