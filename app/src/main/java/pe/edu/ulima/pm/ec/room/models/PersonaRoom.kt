package pe.edu.ulima.pm.ec.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class PersonaRoom(

    @ColumnInfo(name = "fecha_res")
    val fecha_res : String,
    @ColumnInfo(name = "departamento")
    val departamento : String,
    @ColumnInfo(name = "provincia")
    val provincia : String,
    @ColumnInfo(name = "distrito")
    val distrito : String,
    @ColumnInfo(name = "id_persona")
    val id_persona: String,
    @PrimaryKey( autoGenerate = true)
    val id : Int
)