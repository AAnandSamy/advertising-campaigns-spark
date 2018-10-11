package com.org.sspark.load
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._
import org.apache.log4j.Logger

/**
  * This class is used to measure advertising campaigns are showing the same ad to users too many times (a high frequency)
  * Drive class expected two arg(0)=input file path, arg(1)= output file path
  * Created By : Anand Ayyasamy
  * Date : Apr-20-2018
  */
object AdCampDriver {


  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder()
      .appName("advertising_campaigns")
      .master("local[*]")
      .getOrCreate();

    /*  read source file */

    val adDF = spark.read
      .option("delimiter", "\t")
      .option("header", "true")
      .csv("ad.event.data");

    /*  validate GUID,AD_ID */
    val adFilterCaseDF = adDF
      .withColumn("NO_GUID_ID",
        expr(
          "CASE WHEN GUID IN ('','null','NULL','Unknown','-') THEN 1 ELSE 0 END"))
      .withColumn(
        "NO_AD_ID",
        expr(
          "CASE WHEN GUID IN ('','null','NULL','Unknown','-') THEN 1 ELSE 0 END"))
    val vldGUDdf =
      adFilterCaseDF
        .where(adFilterCaseDF("NO_GUID_ID") === "0" and adFilterCaseDF(
          "NO_AD_ID") === "0")
        .drop("NO_GUID_ID", "NO_AD_ID")

    /*Perform aggregation */

    val adW = Window.partitionBy("Ad_ID", "Site_ID").orderBy(asc("GUID"));
    val adFrq = vldGUDdf.withColumn("Frequency", rank().over(adW))
    val adFrqFltr = adFrq
      .filter("Frequency >5")
      .select("Ad_ID", "Site_ID", "Frequency", "GUID")

    val adVs = adFrqFltr
      .groupBy("Ad_ID", "Site_ID", "GUID")
      .agg(sum("Frequency").as("Frequency"), count("GUID").as("TotalUser"))
      .drop("GUID")
      .orderBy(desc("Frequency"))


    spark.stop()

  }

}
