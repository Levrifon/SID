import scala.io.Source
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
object Script{
 def main(args: Array[String]) {
  val sparkMasterHost = "127.0.0.1"
  val cassandraHost = "127.0.0.1"
  val conf = new SparkConf(true).set("spark.cassandra.connection.host", cassandraHost)
  val sc = new SparkContext("spark://" + sparkMasterHost + ":7077", "example", conf)
  /* fin config */
  val textFile = sc.textFile("JanusaryClinton.csv")
  val myfilter = textFile.filter(line => line.contains("BURGLAR"))
  val filtersplitted = myfilter.map(_.split(","))
  val mappedfilteredsplitted = filtersplitted.map(a => (a(0), 1))

  val date = mappedfilteredsplitted.map(_._1.split(" ")(0))
  val count = mappedfilteredsplitted.map(_._2)
  val mapop = date zip count

  val myreduce = mapop.reduceByKey(_ + _)
  /* myreduce.collect.foreach(println) */

 }
}
