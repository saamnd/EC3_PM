package pe.edu.ulima.pm.ec.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class PersonaRoom(

    @ColumnInfo(name = "fecha_corte")
    val fecha_corte : String,
    @ColumnInfo(name = "departamento")
    val departamento : String,
    @ColumnInfo(name = "provincia")
    val provincia : String,
    @ColumnInfo(name = "distrito")
    val distrito : String,
    @ColumnInfo(name = "metododx")
    val metododx : String,
    @PrimaryKey( )
    val id : Int
)