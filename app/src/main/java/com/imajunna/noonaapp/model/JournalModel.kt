package com.imajunna.noonaapp.model

class JournalModel(var aliran:String, var cairan:String, var email:String, var gejala:String, var hari:String, var mens:String, var minum:String, var mood:String, var olahraga:String, var suhu:String) {
    companion object{
        fun fromMap(map: Map<String, Any>):JournalModel{
            val aliran = map["aliran"] as? String ?: throw IllegalArgumentException("Missing or Invalid 'aliran'")
            val cairan = map["cairan"] as? String ?: throw IllegalArgumentException("Missing or Invalid 'cairan'")
            val email = map["email"] as? String ?: throw IllegalArgumentException("Missing or Invalid 'email'")
            val gejala = map["gejala"] as? String ?: throw IllegalArgumentException("Missing or Invalid 'gejala'")
            val hari = map["hari"] as? String ?: throw IllegalArgumentException("Missing or Invalid 'hari'")
            val mens = map["mens"] as? String ?: throw IllegalArgumentException("Missing or Invalid 'mens'")
            val minum = map["minum"] as? String ?: throw IllegalArgumentException("Missing or Invalid 'minum'")
            val mood = map["mood"] as? String ?: throw IllegalArgumentException("Missing or Invalid 'mood'")
            val olahraga = map["olahraga"] as? String ?: throw IllegalArgumentException("Missing or Invalid 'olahraga'")
            val suhu = map["suhu"] as? String ?: throw IllegalArgumentException("Missing or Invalid 'suhu'")



            return JournalModel(aliran,cairan,email,gejala,hari,mens,minum,mood,olahraga,suhu)
        }
    }
}