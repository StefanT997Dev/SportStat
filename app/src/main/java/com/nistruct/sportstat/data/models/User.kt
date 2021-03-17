package com.nistruct.sportstat.data.models

import java.sql.Blob
import java.util.*

data class User(
    var id:Int,
    var name:String,
    var photo:String,
    var position:String,
    var email:String,
    var phoneNumber:String,
    var team:String,
    var sport:String,
    var isTrainer:Boolean
)