import java.io._

import io.circe.{Decoder, Json}
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

import scala.io._
object JsonParse{
  val DefFilename = "C:/Users/aldie/Desktop/raw.txt"
}
class JsonParse(var filename:String) {

  //constructor for default
  def this() = {
    this(JsonParse.DefFilename)
  }
  //case class for receipt document
  case class Receipt (place:String,BIN:String,NDS:String,serialNum:String,cashBoxRange:String,
                      change:String,receiptSerialNum: String,receiptID: String,Salesman:String, products: Array[Product],
                     moneyGiven:String,total:String, DateAndTime: String, Address:String, operator:String, INK_OFD:String,
                     codeRNM:String,ZNM:String,Platform:String)
  //case class for product to store in array
  case class Product (productName: String, amountAndCost: String, totalCost: Double){


  }
  var data = Array[String]() //array for filtered data storage
  var prods = Array[Product]() //array for products to store
  val lines = Source.fromFile(filename).getLines().toList
  var inProcess = true
  var i = 1
  var cashbox = Array[String]()

  val PatternDef = "(\\d{1,})".r.unanchored
  val PatternDig = "\\d{1,}\\.".r
  val PatternCashbox = "(\\d{1,})-(\\d{1,})".r.unanchored
  while ( inProcess ) {
    var line = lines(i)
    line match {
      case PatternDig() => {
        prods:+=Product(lines(i+1),lines(i+2),lines(i+3)
          .replace(',','.').replaceAll("\\s", "").toDouble)
        i+=3
      }
      case PatternCashbox(e1,e2) =>{
        cashbox= cashbox:+ e1+" - "+e2
        i+=1
      }
      case PatternDef(extracted) => {
        data= data :+ extracted
        i+=1
    }

      case "Банковская карта:" =>{
        data= data :+ lines(i+1)
        i+=2
      }

      case "ИТОГО:" => {
        data= data :+ lines(i+1)
        i+=2
      }

      case "WEBKASSA.KZ" => {
        inProcess= false
      }

      case _ => {
        i+=1}
    }
  }

  var doc = Receipt(lines(1),data(0),data(1),lines(4),cashbox(0),data(3),data(4),data(5),cashbox(1), prods,

  data(data.size - 10),data(data.size - 9),lines(lines.size-9).slice(7,lines(lines.size-9).size),
    lines(lines.size-8),
    lines(lines.size-7).slice(27,lines(lines.size-7).size),
    data(data.size - 3),data(data.size - 2),
    lines(lines.size-2).slice(5,lines(lines.size-2).size),lines(lines.size-1))
  println(doc.asJson)
  val file = new File("C:/Users/aldie/Desktop/out.txt")
  val bw = new BufferedWriter(new FileWriter(file))
  bw.write(doc.asJson.toString())
  bw.close()


}
