package com.example.horfay123


// List ordered collection with access to elements by indices

data class Data(val firstName: String, val lastName: String)
val da= listOf(Data("muthu","arasan"))

val name= listOf<String>("muthu","Arasan","mutharasan","muthu","arasan")
val nameList= mutableListOf<String>("muthu","Arasan","mutharasan","muthu","arasan","muthu","Arasan")

//Set collection of unique elements
val user= setOf<String>("muthu","Arasan","mutharasan","muthu","arasan")
val userSet= mutableSetOf<String>("muthu","Arasan","mutharasan","muthu","arasan")

//map key-value pairs
val userData= mapOf(1 to "muthu",2 to "arasan",3 to "mutharasan",4 to "arasan")
val userDataMap= mutableMapOf(1 to "muthu",2 to "arasan",3 to "mutharasan",4 to "arasan")

fun main(){
    //List
    println(name)
    println(name.size)
    println(name.subList(1,3))
    println(name.get(2))
    println(name.distinct())
    println(name.indexOf("Arasan"))
    val numbers = listOf(2,12,1, 2, 3, 4, 5, 6, 7)
    println("fir" +
            "st odd :"+ numbers.find {
        it % 2 != 0
    })
    println("--------mutableListOf--------")
    println(nameList)
    println(nameList.size)
    nameList[0]="Muthu"
    println(nameList[0]=="Muthu")
    println(nameList.removeAt(2))
    println(nameList.contains("Muthu"))
    println(nameList)

    // set
    println("--------setOf--------")
    println(user)
    println(user.elementAt(2))
    println(da.associate {
        Pair(it.firstName,it.lastName)
    })
    println("--------mapOf--------")
    // map
    println(userData)
    userDataMap[5] = "welcome"
    println(userData.getOrDefault(6,"hello"))
    println(userDataMap)

}