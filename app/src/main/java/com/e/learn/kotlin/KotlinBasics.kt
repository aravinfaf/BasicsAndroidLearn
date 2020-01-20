package com.e.learn.kotlin


/*

        Statically typed programming - Jetbrains.
        Interoperable.
        Cincise.
        Open source.
        Feature rich - Operator overloading,Lambda expression,String templates.
        Less error prone - catch erros at compile time.

    KEYWORDS(Mutable(var),Immutable(val)) - Hardkeyword,Softkeyword

            TypeInference:
                    var name="Aravind"(Normal declaration)
                    var name:String="Aravind(TyeInference)

    DATA TYPES

       1.Numbers - Byte,Short,Int,Long,Float,Double
       2.Boolean - true,false
       3.Arrays
       4.Characters
       5.Strings

       TYPE CONVERSION
           toChar().toInt(),toLong(),toDouble(),toByte(),toShort(),toFloat()

     STRING

            var:String: s1="Aravind"
            var:String: s2="AK"

        1.Concatenation - s1+" "+s2
        2.Length        - s1.length  and  ("length is ${s1.length}")
        3.Compare       -("Output ${s1.equals(s2)}") and ("Output ${s1.compareTo(s2)}")
                                    (True or False)                 (32 0r -32)

        4.charAt        -("Index ${s1.get(2)}") and ("Index ${s1[2]}")
        5.Substring     - s1.subSequence(2,5)
        6.contains      - ("contains ${s1.contains("av")}")

     ARRAYS - Store multiple values of different datatype.

        Different data type
            var arr=arrayof(2,4,"Aravind",7.7,'A')
        Same data type
            var arr=arrayof<Int>(1,2,3,4,5)

        Get - arr.get(3)
        Set - arr.set(3,20)
        Size- ("Size ${arr.size}")
        Contains- ("contains ${arr.contains("Aravind"}")
        First -("First ${arr.first()}")
        Last - ("Last ${arr.last()}")
        Index - ("Index ${arr.indexof(4)}")

     Ranges
         val onetoten - 1..10
            ("3 in OneToTen ${3 in onetoten}") //true
            ("13 in OneToTen ${3 in onetoten}") //false

            RangeTo
                val oneToFive = 1.rangeTo(5)
                println("rangeTo:")
                for(x in oneToFive){
                    println(x)
                }

            DownTo
                val sixToThree = 6.downTo(3)
                    println("downTo")
                    for(n in sixToThree){
                        println(n)
                    }

            Step
                val oneToTen = 1..10
                val odd = oneToTen.step(2)
                for(n in odd){
                    println(n)
                }

            Reverse
                 val oneToFive = 1..5
                for (n in oneToFive.reversed()){
                    println(n)
                }

    HIGHER ORDER FUNCTION

        Higher order function or higher level function can have another function as a parameter or return a function or can do both.

            fun main(args: Array<String>) {
                func("BeginnersBook", ::demo)
            }
            fun func(str: String, myfunc: (String) -> Unit) {
                print("Welcome to Kotlin tutorial at ")
                myfunc(str)
            }
            fun demo(str: String) {
                println(str)
            }

*/

class KotlinBasics {


}