import java.io._
import io.circe.generic.auto._
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
  val out = "C:/Users/aldie/Desktop/out.txt"
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
  val lines = Source.fromFile(filename).getLines().toList //lines as list for iterating considerations
  var inProcess = true // while break lever
  var i = 1//initial iteration
  var cashbox = Array[String]() // for int - int sequence
  val PatternDef = "(\\d{1,})".r.unanchored // simple 'property' 'value(int)' pattern
  val PatternDig = "\\d{1,}\\.".r //simple int with fullstop(.) pattern
  val PatternCashbox = "(\\d{1,})-(\\d{1,})".r.unanchored// pattern for int - int sequence
  while ( inProcess ) {
    val line = lines(i)
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

  var doc = Receipt(lines(1),//place
    data(0),//Bin
    data(1),//NDS
    lines(4).replaceAll("\\s", ""),//SerialNum
    cashbox(0),//Cashboxrange
    data(3),//change
    data(4),//receiptserialnum
    data(5),//receiptId
    cashbox(1),//salesman
    prods,//products
    data(data.size - 10),//cashorbank
    data(data.size - 9),//total
    lines(lines.size-9).slice(7,lines(lines.size-9).size),//date and time
    lines(lines.size-8),//address
    lines(lines.size-7).slice(27,lines(lines.size-7).size),//operator
    data(data.size - 3),//ink - ofd
    data(data.size - 2),//rnm
    lines(lines.size-2).slice(5,lines(lines.size-2).size),//znm
    lines(lines.size-1))//platform

  println(doc.asJson)

  val file = new File(out)
  val bw = new BufferedWriter(new FileWriter(file))
  bw.write(doc.asJson.toString())
  bw.close()
}
